package com.example.fandora.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.fandora.R
import com.example.fandora.data.source.network.RetrofitApiPool
import com.example.fandora.data.source.repository.HomeRepository
import com.example.fandora.databinding.FragmentHomeBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(HomeRepository(RetrofitApiPool.retrofitService))
    }

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
        setMargin()
        setAdapter()
    }

    private fun setMargin() {
        binding.rvHomeFanGiftDelivered.addItemDecoration(
            FirstLastMarginDecoration(
                sideMargin = resources.getDimensionPixelSize(R.dimen.margin_13)
            )
        )
    }

    private fun setAdapter() {
        val adapter = HomeReviewAdapter()
        binding.rvHomeFanGiftDelivered.adapter = adapter
        viewModel.loadTotalReviews("")

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalReviews
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { totalReviews ->
                    adapter.submitList(totalReviews)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}