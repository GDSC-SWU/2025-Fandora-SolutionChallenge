package com.example.fandora.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fandora.R
import com.example.fandora.data.model.Review
import com.example.fandora.databinding.FragmentHomeBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeReviewAdapter = HomeReviewAdapter()

    val dummyHomeReview = listOf(
        Review(1, "", "I’m so happy to receive this album! Than"),
        Review(2, "", "I never thought I’d own this album—thank"),
        Review(3, "", "This means so much to me! I truly apate")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
    }

    private fun setLayout() {
        setAdapter()
        setMargin()
    }

    private fun setMargin() {
        binding.rvHomeFanGiftDelivered.addItemDecoration(
            FirstLastMarginDecoration(
                sideMargin = resources.getDimensionPixelSize(R.dimen.margin_13)
            )
        )
    }

    private fun setAdapter() {
        binding.rvHomeFanGiftDelivered.adapter = homeReviewAdapter
        homeReviewAdapter.submitList(dummyHomeReview)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}