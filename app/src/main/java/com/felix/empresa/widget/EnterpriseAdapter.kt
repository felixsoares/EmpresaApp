package com.felix.empresa.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felix.empresa.R
import com.felix.empresa.data.vo.response.EnterpriseVO
import com.felix.empresa.util.ItemViewClick
import kotlinx.android.synthetic.main.enterprise_item.view.*

class EnterpriseAdapter(
    private val mContext: Context,
    private val mClick: ItemViewClick
) : RecyclerView.Adapter<EnterpriseAdapter.EnterpriseViewHolder>() {

    private val mEnterprises = mutableListOf<EnterpriseVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterpriseViewHolder {
        return EnterpriseViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.enterprise_item, parent, false)
        )
    }

    override fun getItemCount() = mEnterprises.size

    override fun onBindViewHolder(holder: EnterpriseViewHolder, position: Int) {
        holder.binding(mEnterprises[position])
    }

    fun updateList(enterprises: List<EnterpriseVO>) {
        mEnterprises.clear()
        mEnterprises.addAll(enterprises)
        notifyDataSetChanged()
    }

    inner class EnterpriseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun binding(enterprise: EnterpriseVO) {
            with(itemView) {
                txtCompanyName.text = enterprise.name
                txtZone.text = enterprise.zone
                txtCountry.text = enterprise.country
                Glide.with(this).load(enterprise.image).into(imageView)
                setOnClickListener {
                    mClick.clickItem(enterprise.id)
                }
            }
        }

    }
}