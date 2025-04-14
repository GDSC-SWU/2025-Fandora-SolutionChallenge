package com.example.fandora.ui.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fandora.R
import com.example.fandora.data.model.Review
import com.example.fandora.databinding.FragmentDonationDetailBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration

class DonationDetailFragment : Fragment() {

    private var _binding: FragmentDonationDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DonationDetailFragmentArgs by navArgs()

    private val donationReviewAdapter = DonationReviewAdapter()

    val dummyDonationReview = listOf(
        Review(1, "", "I’m so happy to receive this album! Than"),
        Review(2, "", "I never thought I’d own this album—thank"),
        Review(3, "", "This means so much to me! I truly apate"),
        Review(4, "", "This means so much to me! I truly apate"),
        Review(5, "", "This means so much to me! I truly apate"),
        Review(6, "", "This means so much to me! I truly apate")
    )

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
        val company = args.company
        with(binding) {
            tvDonationDetailCompanyName.text = company.companyName
            tvDonationDetailCompanyLocation.text = company.location
            tvDonationDetailCompanyContent.text = company.content
            btnDonationDetailApply.setOnClickListener {
                findNavController().navigate(R.id.action_donation_detail_to_donation_apply)
            }
            btnDonationDetailBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        setAdapter()
        setMargin()
    }

    private fun setAdapter() {
        binding.rvDonationDetailReview.adapter = donationReviewAdapter
        donationReviewAdapter.submitList(dummyDonationReview)
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