package com.example.fandora.ui.donationdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.data.model.response.CompanyReviewResponse
import com.example.fandora.databinding.ItemDonationDetailReviewBinding
import com.example.fandora.ui.extensions.load

class DonationReviewAdapter : ListAdapter<CompanyReviewResponse, DonationReviewAdapter.DonationReviewViewHolder>(
    DonationReviewDiffCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationReviewViewHolder {
        return DonationReviewViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DonationReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DonationReviewViewHolder private constructor(private val binding: ItemDonationDetailReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: CompanyReviewResponse) {
            binding.ivDonationDetailReview.load(review.reviewImage)
            binding.tvDonationDetailReviewContent.text = review.content
        }

        companion object {
            fun from(parent: ViewGroup): DonationReviewViewHolder {
                return DonationReviewViewHolder(
                    ItemDonationDetailReviewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }
}
class DonationReviewDiffCallback: DiffUtil.ItemCallback<CompanyReviewResponse>() {
    override fun areItemsTheSame(oldItem: CompanyReviewResponse, newItem: CompanyReviewResponse): Boolean {
        return oldItem.reviewId == newItem.reviewId
    }

    override fun areContentsTheSame(oldItem: CompanyReviewResponse, newItem: CompanyReviewResponse): Boolean {
        return oldItem == newItem
    }
}