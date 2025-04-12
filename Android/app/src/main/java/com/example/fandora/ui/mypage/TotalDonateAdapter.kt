package com.example.fandora.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.R
import com.example.fandora.data.model.Company
import com.example.fandora.databinding.ItemDonationTotalBinding
import com.example.fandora.ui.common.CompanyClickListener

class TotalDonateAdapter(private val clickListener: CompanyClickListener) : ListAdapter<Company, TotalDonateAdapter.TotalDonateViewHolder>(
    TotalDonateCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalDonateViewHolder {
        return TotalDonateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TotalDonateViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class TotalDonateViewHolder private constructor(private val binding: ItemDonationTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company, clickListener: CompanyClickListener) {
            itemView.setOnClickListener {
                clickListener.onCompanyClick(company.id)
            }
            binding.ivDonationTotal.setBackgroundResource(R.color.gray_50)
            binding.tvDonationTotalCompanyName.text = company.companyName
            binding.tvDonationTotalDate.text = company.date
        }

        companion object {
            fun from(parent: ViewGroup): TotalDonateViewHolder {
                return TotalDonateViewHolder(
                    ItemDonationTotalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class TotalDonateCallback: DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }

}