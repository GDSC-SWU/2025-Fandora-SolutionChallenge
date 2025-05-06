package com.example.fandora.ui.donation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.data.model.response.CompanyResponse
import com.example.fandora.databinding.ItemDonationCompanyBinding
import com.example.fandora.ui.extensions.load

class DonationCompanyAdapter(private val clickListener: CompanyClickListener) : ListAdapter<CompanyResponse, DonationCompanyAdapter.DonationCompanyViewHolder>(
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
        fun bind(company: CompanyResponse, clickListener: CompanyClickListener) {
            itemView.setOnClickListener {
                clickListener.onCompanyClick(company)
            }
            binding.ivDonationCompany.load(company.companyImage)
            binding.tvDonationCompanyName.text = company.companyName
            binding.tvDonationCompanyContent.text = company.description
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

class DonationCompanyCallback: DiffUtil.ItemCallback<CompanyResponse>() {
    override fun areItemsTheSame(oldItem: CompanyResponse, newItem: CompanyResponse): Boolean {
        return oldItem.companyId == newItem.companyId
    }

    override fun areContentsTheSame(oldItem: CompanyResponse, newItem: CompanyResponse): Boolean {
        return oldItem == newItem
    }

}