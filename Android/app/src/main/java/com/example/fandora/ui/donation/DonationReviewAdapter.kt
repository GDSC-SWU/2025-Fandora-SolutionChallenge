package com.example.fandora.ui.donation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.R
import com.example.fandora.data.model.Review
import com.example.fandora.databinding.ItemDonationDetailReviewBinding

class DonationReviewAdapter : ListAdapter<Review, DonationReviewAdapter.DonationReviewViewHolder>(
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
        fun bind(review: Review) {
            binding.ivDonationDetailReview.setBackgroundResource(R.color.gray_50)
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
class DonationReviewDiffCallback: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}