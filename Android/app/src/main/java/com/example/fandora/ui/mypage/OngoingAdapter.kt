package com.example.fandora.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.databinding.ItemOngoingBinding
import com.example.fandora.ui.extensions.load

class OngoingAdapter(private val clickListener: DonationClickListener) : ListAdapter<DonationResponse, OngoingAdapter.OngoingViewHolder>(
    OngoingCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngoingViewHolder {
        return OngoingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OngoingViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class OngoingViewHolder private constructor(private val binding: ItemOngoingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donation: DonationResponse, clickListener: DonationClickListener) {
            itemView.setOnClickListener {
                clickListener.onDonationClick(donation)
            }
            binding.ivOngoing.load(donation.companyImage)
            binding.tvOngoingCompanyName.text = donation.companyName
            binding.tvOngoingDate.text = donation.donationDate
        }

        companion object {
            fun from(parent: ViewGroup): OngoingViewHolder {
                return OngoingViewHolder(
                    ItemOngoingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class OngoingCallback: DiffUtil.ItemCallback<DonationResponse>() {
    override fun areItemsTheSame(oldItem: DonationResponse, newItem: DonationResponse): Boolean {
        return oldItem.donationId == newItem.donationId
    }

    override fun areContentsTheSame(oldItem: DonationResponse, newItem: DonationResponse): Boolean {
        return oldItem == newItem
    }

}