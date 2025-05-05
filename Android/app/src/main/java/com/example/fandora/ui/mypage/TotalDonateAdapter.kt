package com.example.fandora.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.databinding.ItemDonationTotalBinding
import com.example.fandora.ui.common.DonationClickListener
import com.example.fandora.ui.extensions.load

class TotalDonateAdapter(private val clickListener: DonationClickListener) : ListAdapter<DonationResponse, TotalDonateAdapter.TotalDonateViewHolder>(
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
        fun bind(donation: DonationResponse, clickListener: DonationClickListener) {
            itemView.setOnClickListener {
                clickListener.onDonationClick(donation)
            }
            binding.ivDonationTotal.load(donation.companyImage)
            binding.tvDonationTotalCompanyName.text = donation.companyName
            binding.tvDonationTotalDate.text = donation.donationDate
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

class TotalDonateCallback: DiffUtil.ItemCallback<DonationResponse>() {
    override fun areItemsTheSame(oldItem: DonationResponse, newItem: DonationResponse): Boolean {
        return oldItem.donationId == newItem.donationId
    }

    override fun areContentsTheSame(oldItem: DonationResponse, newItem: DonationResponse): Boolean {
        return oldItem == newItem
    }

}