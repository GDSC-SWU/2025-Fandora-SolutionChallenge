package com.example.fandora.ui.apply

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fandora.R
import com.example.fandora.data.source.network.RetrofitApiPool
import com.example.fandora.data.model.request.DonationApplyRequest
import com.example.fandora.data.source.repository.DonationApplyRepository
import com.example.fandora.databinding.FragmentDonationApplyBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DonationApplyFragment : Fragment() {

    private var _binding: FragmentDonationApplyBinding? = null
    private val binding get() = _binding!!

    private val args: DonationApplyFragmentArgs by navArgs()

    private val viewModel: ApplyViewModel by viewModels {
        ApplyViewModelFactory(DonationApplyRepository(RetrofitApiPool.retrofitService))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDonationApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDatePicker()
        setLayout()
    }

    private fun setLayout() {
        binding.btnDonationApplyBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnDonationApplyCamera.setOnClickListener {
            val action = DonationApplyFragmentDirections.actionDonationApplyToCamera(args.companyId)
            findNavController().navigate(action)
        }
        setTodayDate()
        setBtnColorChange()
        setGeminiData()

    }

    private fun setGeminiData() {
        val albumTitle = args.albumTitle ?: ""
        val artistName = args.artistName ?: ""

        binding.etDonationApplyAlbumName.setText(albumTitle)
        binding.etDonationApplyArtistName.setText(artistName)
    }

    private fun setTodayDate() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        binding.etDonationApplyDate.setText(currentDate)
    }


    private fun setDatePicker() {
        val openDatePicker = {
            showDatePicker()
        }
        binding.etDonationApplyDate.setOnClickListener { openDatePicker() }
        binding.btnDonationApplyDate.setOnClickListener { openDatePicker() }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("")
            .setTheme(R.style.DatePicker)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.show(parentFragmentManager, "DATE_PICKER")

        datePicker.addOnPositiveButtonClickListener { selection ->
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val selectedDate = sdf.format(Date(selection))
            binding.etDonationApplyDate.setText(selectedDate)
        }
    }

    private fun checkFormAndUpdateButton() {
        val isArtistNameNotEmpty = binding.etDonationApplyArtistName.text.toString().trim().isNotEmpty()
        val isAlbumNotEmpty = binding.etDonationApplyAlbumName.text.toString().trim().isNotEmpty()
        val isAlbumCountNotEmpty = binding.etDonationApplyAlbumCount.text.toString().trim().isNotEmpty()
        val isRadioBtnNotEmpty = binding.radioGroupDonationApply.checkedRadioButtonId != -1

        with(binding.btnDonationApplyComplete) {
            isEnabled = isArtistNameNotEmpty && isAlbumNotEmpty && isAlbumCountNotEmpty && isRadioBtnNotEmpty
            setBackgroundResource(
                if (isEnabled) R.drawable.background_pink400_10
                else R.drawable.background_gray200_10
            )
            setOnClickListener {
                if (isEnabled) {
                    findNavController().navigate(R.id.action_donation_apply_to_home)
                    setViewModel()
                }
            }
        }
    }

    private fun setBtnColorChange() {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = checkFormAndUpdateButton()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
        }
        binding.etDonationApplyArtistName.addTextChangedListener(watcher)
        binding.etDonationApplyAlbumName.addTextChangedListener(watcher)
        binding.etDonationApplyAlbumCount.addTextChangedListener(watcher)
        binding.radioGroupDonationApply.setOnCheckedChangeListener { _, _ ->
            checkFormAndUpdateButton()
        }
    }

    private fun setViewModel() {
        val donationApplyRequest = DonationApplyRequest(
            companyId = args.companyId,
            artistName = binding.etDonationApplyArtistName.text.toString(),
            albumName = binding.etDonationApplyAlbumName.text.toString(),
            quantity = binding.etDonationApplyAlbumCount.text.toString().toInt(),
            donationDate = binding.etDonationApplyDate.text.toString(),
            donationType =
            if (binding.btnDonationApplyDelivery.isSelected) {
                "delivery"
            } else {
                "In-person"
            }
        )

        viewModel.apply("", donationApplyRequest)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.applyResponse
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { applyResponse ->
                    if (applyResponse != null) {
                        Toast.makeText(requireContext(), applyResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}