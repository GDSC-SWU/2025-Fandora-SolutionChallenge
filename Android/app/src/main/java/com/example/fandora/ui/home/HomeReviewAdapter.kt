package com.example.fandora.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.R
import com.example.fandora.data.model.Review
import com.example.fandora.databinding.ItemHomeFanGiftBinding

class HomeReviewAdapter : ListAdapter<Review, HomeReviewAdapter.HomeReviewViewHolder>(
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
        fun bind(review: Review) {
            binding.ivHomeFanGift.setBackgroundResource(R.color.gray_50)
            binding.tvHomeFanGiftContent.text = review.content
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

class HomeReviewDiffCallback: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

}