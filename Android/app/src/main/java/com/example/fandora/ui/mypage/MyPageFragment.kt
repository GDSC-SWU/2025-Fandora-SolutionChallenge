package com.example.fandora.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.fandora.R
import com.example.fandora.data.RetrofitApiPool
import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.data.source.MyPageRepository
import com.example.fandora.databinding.FragmentMypageBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyPageViewModel by viewModels {
        MyPageViewModelFactory(MyPageRepository(RetrofitApiPool.retrofitService))
    }

    private val donationClickListener = object : DonationClickListener {
        override fun onDonationClick(donation: DonationResponse) {
            val action = MyPageFragmentDirections
                .actionMypageToDonationDetail(company = null, donation = donation)
            findNavController().navigate(action)
        }
    }

    private val totalDonateAdapter = TotalDonateAdapter(donationClickListener)
    private val onGoingAdapter = OngoingAdapter(donationClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        setMargin()
        setViewModel()
    }

    private fun setViewModel() {
        viewModel.loadUserName("")
        viewModel.loadOngoing("")
        viewModel.loadDonated("")

        binding.rvMypageOngoing.adapter = onGoingAdapter
        binding.rvMypageDonateTotal.adapter = totalDonateAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userName.collect { userName ->
                        userName?.let {
                            binding.tvMypageNickname.text =
                                getString(R.string.mypage_nickname, it.name)
                        }
                    }
                }
                launch {
                    viewModel.ongoing.collect { onGoings ->
                        onGoingAdapter.submitList(onGoings)
                    }
                }
                launch {
                    viewModel.donated.collect { donated ->
                        totalDonateAdapter.submitList(donated)
                    }
                }
            }
        }
    }

    private fun setMargin() {
        binding.rvMypageDonateTotal.addItemDecoration(
            FirstLastMarginDecoration(
                sideMargin = resources.getDimensionPixelSize(R.dimen.margin_12)
            )
        )
        binding.rvMypageOngoing.addItemDecoration(
            FirstLastMarginDecoration(
                sideMargin = resources.getDimensionPixelSize(R.dimen.margin_13)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}