package com.felix.empresa.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.felix.empresa.R
import com.felix.empresa.data.vo.response.EnterpriseVO
import com.felix.empresa.util.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val extra = intent.extras
        if (extra != null) {
            val enterprise = extra.getSerializable(Constants.ENTERPRISE) as EnterpriseVO
            supportActionBar?.title = enterprise.name
            txtDescription.text = enterprise.description
            Glide.with(this).load(enterprise.image).into(imageView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
