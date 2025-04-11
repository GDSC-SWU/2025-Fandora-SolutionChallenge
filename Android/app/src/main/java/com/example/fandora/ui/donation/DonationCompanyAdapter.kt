package com.example.fandora.ui.donation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.R
import com.example.fandora.data.model.Company
import com.example.fandora.databinding.ItemDonationCompanyBinding
import com.example.fandora.ui.common.CompanyClickListener

class DonationCompanyAdapter(private val clickListener: CompanyClickListener) : ListAdapter<Company, DonationCompanyAdapter.DonationCompanyViewHolder>(
    DonationCompanyCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationCompanyViewHolder {
        return DonationCompanyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DonationCompanyViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class DonationCompanyViewHolder private constructor(private val binding: ItemDonationCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company, clickListener: CompanyClickListener) {
            itemView.setOnClickListener {
                clickListener.onCompanyClick(company.id)
            }
            binding.ivDonationCompany.setBackgroundResource(R.color.gray_50)
            binding.tvDonationCompanyName.text = company.companyName
            binding.tvDonationCompanyContent.text = company.content
        }

        companion object {
            fun from(parent: ViewGroup): DonationCompanyViewHolder {
                return DonationCompanyViewHolder(
                    ItemDonationCompanyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class DonationCompanyCallback: DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }

}