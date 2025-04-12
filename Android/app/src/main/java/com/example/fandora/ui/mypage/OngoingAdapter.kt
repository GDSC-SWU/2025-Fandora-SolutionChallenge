package com.example.fandora.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fandora.R
import com.example.fandora.data.model.Company
import com.example.fandora.databinding.ItemOngoingBinding
import com.example.fandora.ui.common.CompanyClickListener

class OngoingAdapter(private val clickListener: CompanyClickListener) : ListAdapter<Company, OngoingAdapter.OngoingViewHolder>(
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
        fun bind(company: Company, clickListener: CompanyClickListener) {
            itemView.setOnClickListener {
                clickListener.onCompanyClick(company.id)
            }
            binding.ivOngoing.setBackgroundResource(R.color.gray_50)
            binding.tvOngoingCompanyName.text = company.companyName
            binding.tvOngoingDate.text = company.date
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

class OngoingCallback: DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }

}