package com.example.fandora.ui.donation

import android.os.Bundle
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}