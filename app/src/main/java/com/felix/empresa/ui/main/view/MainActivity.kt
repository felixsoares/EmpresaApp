package com.felix.empresa.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.empresa.R
import com.felix.empresa.data.vo.response.EnterpriseVO
import com.felix.empresa.ui.detail.DetailActivity
import com.felix.empresa.ui.main.business.MainContract
import com.felix.empresa.util.Constants
import com.felix.empresa.util.ItemViewClick
import com.felix.empresa.widget.CustomDialog
import com.felix.empresa.widget.EnterpriseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), MainContract.View, ItemViewClick {

    private var transitionImageView: ImageView? = null
    lateinit var mDialog: CustomDialog
    private val mAdapter by lazy {
        this.currentScope.get<EnterpriseAdapter> {
            parametersOf(this)
        }
    }
    private val mPresenter by lazy {
        this.currentScope.get<MainContract.Presenter> {
            parametersOf(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        mDialog = CustomDialog(this)
        recycleView.let {
            it.adapter = mAdapter
            it.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val mSearch = menu?.findItem(R.id.action_search)
        val mSearchView = mSearch?.actionView as SearchView
        mSearchView.queryHint = getString(R.string.search)
        mSearchView.isIconified = true
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.doSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        mSearchView.setOnSearchClickListener {
            showHideLogo(false)
            txtInitMessage.visibility = View.GONE
        }
        mSearchView.setOnCloseListener {
            showHideLogo(true)
            mPresenter.verifyMessageVisbility()
            false
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun showInitialMessage() {
        txtInitMessage.visibility = View.VISIBLE
    }

    override fun clickItem(id: Int, imageView: ImageView) {
        this.transitionImageView = imageView
        mPresenter.getEnterprise(id)
    }

    private fun showHideLogo(isCollapsed: Boolean) {
        imgNav.visibility = if (isCollapsed) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        mPresenter.clear()
        super.onDestroy()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun setupEnterprises(enterprises: List<EnterpriseVO>) {
        recycleView.visibility = View.VISIBLE
        mAdapter.updateList(enterprises)
    }

    override fun showMessage(message: String) {
        recycleView.visibility = View.GONE
        txtEmpty.text = message
        txtEmpty.visibility = View.VISIBLE
    }

    override fun hideMessage() {
        txtEmpty.visibility = View.GONE
    }

    override fun showFullLoading() {
        mDialog.show()
    }

    override fun hideFullLoading() {
        mDialog.hide()
    }

    override fun openEnterpriseDetail(enterprise: EnterpriseVO) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.ENTERPRISE, enterprise)
        val pair = Pair(transitionImageView as View, "imageEnterprise")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)
        startActivity(intent, options.toBundle())
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Ops! tente novamente", Toast.LENGTH_LONG).show()
    }
}
