package com.example.fandora.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.data.model.response.TotalReviewResponse
import com.example.fandora.databinding.ItemHomeFanGiftBinding
import com.example.fandora.ui.extensions.load

class HomeReviewAdapter : ListAdapter<TotalReviewResponse, HomeReviewAdapter.HomeReviewViewHolder>(
    HomeReviewDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeReviewViewHolder {
        return HomeReviewViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeReviewViewHolder private constructor(private val binding: ItemHomeFanGiftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(totalReview: TotalReviewResponse) {
            binding.ivHomeFanGift.load(totalReview.reviewImage)
            binding.tvHomeFanGiftContent.text = totalReview.content
        }

        companion object {
            fun from(parent: ViewGroup): HomeReviewViewHolder {
                return HomeReviewViewHolder(
                    ItemHomeFanGiftBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class HomeReviewDiffCallback: DiffUtil.ItemCallback<TotalReviewResponse>() {
    override fun areItemsTheSame(oldItem: TotalReviewResponse, newItem: TotalReviewResponse): Boolean {
        return oldItem.reviewId == newItem.reviewId
    }

    override fun areContentsTheSame(oldItem: TotalReviewResponse, newItem: TotalReviewResponse): Boolean {
        return oldItem == newItem
    }

}