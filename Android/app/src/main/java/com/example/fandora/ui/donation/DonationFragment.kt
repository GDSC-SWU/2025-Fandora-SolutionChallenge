package com.example.fandora.ui.donation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fandora.R
import com.example.fandora.data.model.Company
import com.example.fandora.databinding.FragmentDonationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DonationFragment : Fragment() {

    private var _binding: FragmentDonationBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    private val donationCompanyAdapter = DonationCompanyAdapter(object : CompanyClickListener {
        override fun onCompanyClick(companyId: Int) {
            findNavController().navigate(R.id.action_donation_to_donation_detail)
        }
    })

    val dummyCompany = listOf(
        Company(1, "MusicSmile Foundation", "", "", "A non-profit organization established to support underprivileged youth who ha ㆍㆍㆍ ", "2024.12.28"),
        Company(2, "Idol Dream Center", "", "", "A welfare facility for children and adolescents with developmental disabilities who lovㆍㆍㆍ ", "2024.10.1"),
        Company(3, "MusicBridge", "", "", "A social enterprise that supports underprivileged communities through ㆍㆍㆍ", "2024.7.20"),
        Company(4, "SoulHarmony", "", "", "A nonprofit organization that provides psychological and emotional support fo ㆍㆍㆍ", "2024.10.2"),
        Company(5, "Green Eco Store", "", "", "Green Eco Store is a social enterprise recycling store that promotes eco-friendly culture. They collect K-POP albums, merchandise, and books, then resell them or donate them to local children's centers and underprivileged youth. All proceeds support environmental protection activities.", "2024.5.5"),
        Company(6, "Grow Care Center", "", "", "Grow Care Center is a social welfare facility dedicated to supporting children and youth in need. They provide various programs to help children and adolescents grow up healthy and independent. Donated K-POP albums are gifted to the children or made available in community spaces.", "2025.4.10"),
    )

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
        donationCompanyAdapter.submitList(dummyCompany)
    }

    private fun setGoogleMap(savedInstanceState: Bundle?) {
        mapView = binding.mapDonationCenter
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            googleMap = map
            googleMap?.apply {
                mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            requestLocationPermission()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                // 권한 거부 시 처리 (예: 사용자에게 메시지 표시)
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
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
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}