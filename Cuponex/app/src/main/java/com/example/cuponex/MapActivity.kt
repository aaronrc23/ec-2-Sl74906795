package com.example.cuponex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuponex.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var googleMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentMap = supportFragmentManager.findFragmentById(R.id.fcv_map) as SupportMapFragment
        fragmentMap.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val idatCentroLocation = LatLng(-12.066877, -77.035721)
        googleMap.addMarker(MarkerOptions().position(idatCentroLocation).title("Idat Centro"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(idatCentroLocation, 16f))
    }
}