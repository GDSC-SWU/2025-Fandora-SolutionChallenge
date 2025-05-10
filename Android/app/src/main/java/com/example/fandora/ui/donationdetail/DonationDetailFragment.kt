package com.example.fandora.ui.donationdetail

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
import androidx.navigation.fragment.navArgs
import com.example.fandora.R
import com.example.fandora.data.source.network.RetrofitApiPool
import com.example.fandora.data.source.repository.DonationDetailRepository
import com.example.fandora.databinding.FragmentDonationDetailBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration
import com.example.fandora.ui.extensions.load
import kotlinx.coroutines.launch

class DonationDetailFragment : Fragment() {

    private var _binding: FragmentDonationDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DonationDetailFragmentArgs by navArgs()

    private val viewModel: DonationDetailViewModel by viewModels {
        DonationDetailViewModelFactory(DonationDetailRepository(RetrofitApiPool.retrofitService))
    }

    private val donationReviewAdapter = DonationReviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDonationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
    }

    private fun setLayout() {
        binding.btnDonationDetailBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnDonationDetailApply.setOnClickListener {
            val companyId = args.company?.companyId ?: args.donation?.companyId ?: return@setOnClickListener
            val action = DonationDetailFragmentDirections.actionDonationDetailToDonationApply(companyId = companyId, albumTitle = null, artistName = null)
            findNavController().navigate(action)
        }
        setViewModel()
        setMargin()
    }

    private fun setViewModel() {
        binding.rvDonationDetailReview.adapter = donationReviewAdapter
        val companyId = args.company?.companyId ?: args.donation?.companyId
        companyId?.let {
            viewModel.loadCompanyDetail("", it)
            viewModel.loadCompanyReview("", it)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.companyDetail.collect { companyDetail ->
                        if (companyDetail != null) {
                            binding.tvDonationDetailCompanyName.text = companyDetail.companyName
                            binding.tvDonationDetailCompanyContent.text = companyDetail.description
                            binding.tvDonationDetailCompanyLocation.text = companyDetail.address
                            binding.ivDonationDetailCompany.load(companyDetail.companyImage)
                        }
                    }
                }
                launch {
                    viewModel.companyReview.collect { companyReviews ->
                        donationReviewAdapter.submitList(companyReviews)
                    }
                }
            }
        }
    }

    private fun setMargin() {
        binding.rvDonationDetailReview.addItemDecoration(
            FirstLastMarginDecoration(
                sideMargin = resources.getDimensionPixelSize(R.dimen.margin_14)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}