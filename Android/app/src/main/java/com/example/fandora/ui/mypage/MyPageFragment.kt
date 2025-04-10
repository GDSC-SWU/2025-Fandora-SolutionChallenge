package com.example.fandora.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fandora.R
import com.example.fandora.data.model.Company
import com.example.fandora.databinding.FragmentMypageBinding
import com.example.fandora.ui.common.FirstLastMarginDecoration
import com.example.fandora.ui.donation.CompanyClickListener
import com.example.fandora.ui.donation.DonationCompanyAdapter

class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val totalDonateAdapter = TotalDonateAdapter(object : CompanyClickListener {
        override fun onCompanyClick(companyId: Int) {
            findNavController().navigate(R.id.action_mypage_to_donation_detail)
        }
    })

    private val onGoingAdapter = OngoingAdapter(object : CompanyClickListener {
        override fun onCompanyClick(companyId: Int) {
            findNavController().navigate(R.id.action_mypage_to_donation_detail)
        }
    })

    val dummyCompanies = listOf(
        Company(1, "MusicSmile Foundation", "", "", "A non-profit organization established to support underprivileged youth who ha ㆍㆍㆍ ", "2024.12.28"),
        Company(2, "Idol Dream Center", "", "", "A welfare facility for children and adolescents with developmental disabilities who lovㆍㆍㆍ ", "2024.10.1"),
        Company(3, "MusicBridge", "", "", "A social enterprise that supports underprivileged communities through ㆍㆍㆍ", "2024.7.20"),
        Company(4, "SoulHarmony", "", "", "A nonprofit organization that provides psychological and emotional support fo ㆍㆍㆍ", "2024.10.2"),
        Company(5, "Green Eco Store", "", "", "Green Eco Store is a social enterprise recycling store that promotes eco-friendly culture. They collect K-POP albums, merchandise, and books, then resell them or donate them to local children's centers and underprivileged youth. All proceeds support environmental protection activities.", "2024.5.5"),
        Company(6, "Grow Care Center", "", "", "Grow Care Center is a social welfare facility dedicated to supporting children and youth in need. They provide various programs to help children and adolescents grow up healthy and independent. Donated K-POP albums are gifted to the children or made available in community spaces.", "2025.4.10"),
    )

    val dummyOngoingCompanies = listOf(
        Company(1, "MusicSmile Foundation", "", "", "A non-profit organization established to support underprivileged youth who ha ㆍㆍㆍ ", "2024.12.28"),
        Company(2, "Idol Dream Center", "", "", "A welfare facility for children and adolescents with developmental disabilities who lovㆍㆍㆍ ", "2024.10.1")
    )

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
        setAdapter()
        setMargin()
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

    private fun setAdapter() {
        binding.rvMypageDonateTotal.adapter = totalDonateAdapter
        binding.rvMypageOngoing.adapter = onGoingAdapter

        totalDonateAdapter.submitList(dummyCompanies)
        onGoingAdapter.submitList(dummyOngoingCompanies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}