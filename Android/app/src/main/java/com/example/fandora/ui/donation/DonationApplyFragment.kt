package com.example.fandora.ui.donation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fandora.R
import com.example.fandora.databinding.FragmentDonationApplyBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DonationApplyFragment : Fragment() {

    private var _binding: FragmentDonationApplyBinding? = null
    private val binding get() = _binding!!

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
            findNavController().navigate(R.id.action_donation_apply_to_camera)
        }
        setTodayDate()
        setBtnColorChange()
    }

    private fun setTodayDate() {
        val currentDate = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date())
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
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
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
                if (isEnabled) findNavController().navigate(R.id.action_donation_apply_to_home)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}