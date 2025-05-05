package com.example.fandora.ui.donation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fandora.R
import com.example.fandora.data.RetrofitApiPool
import com.example.fandora.data.model.response.CompanyResponse
import com.example.fandora.data.source.DonationRepository
import com.example.fandora.databinding.FragmentDonationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class DonationFragment : Fragment() {

    private var _binding: FragmentDonationBinding? = null
    private val binding get() = _binding!!

    private var mapView: MapView? = null
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel: DonationViewModel by viewModels {
        DonationViewModelFactory(DonationRepository(RetrofitApiPool.retrofitService))
    }

    private val donationCompanyAdapter = DonationCompanyAdapter(object : CompanyClickListener {
        override fun onCompanyClick(company: CompanyResponse) {
            val action = DonationFragmentDirections.actionDonationToDonationDetail(company = company, donation = null)
            findNavController().navigate(action)
        }
    })

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getCurrentLocation()
        } else {
            Toast.makeText(requireContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDonationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout(savedInstanceState)
    }

    private fun setLayout(savedInstanceState: Bundle?) {
        setGoogleMap(savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        binding.rvDonationCompany.adapter = donationCompanyAdapter
        viewModel.loadCompanies("")

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.companies
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { companies ->
                    donationCompanyAdapter.submitList(companies)
                }
        }
    }

    private fun setGoogleMap(savedInstanceState: Bundle?) {
        mapView = binding.mapDonationCenter
        mapView?.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        setupTouchBlocker()
        mapView?.getMapAsync { map ->
            googleMap = map
            setupMapUi()
            requestLocationPermission()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchBlocker() {
        binding.touchBlocker.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> v.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
    }

    private fun setupMapUi() {
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.setOnMapClickListener {
            findNavController().navigate(R.id.action_donation_to_map)
        }
    }


    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getCurrentLocation()
            }
            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                googleMap?.apply {
                    moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                    addMarker(
                        MarkerOptions()
                            .position(currentLatLng)
                            .title("현재 위치")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroyView() {
        mapView?.onDestroy()
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
}