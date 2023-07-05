package com.example.ec2_sl74906795

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ec2_sl74906795.databinding.ActivityCamaraBinding

class CamaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamaraBinding
    private lateinit var openCameraLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)


        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPhoto.setOnClickListener{
            if(permissionValidated()){
                openCamera()
            }
        }



        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK){
                val photo: Bitmap = result.data?.extras?.get("data") as Bitmap
                binding.imgPhoto.setImageBitmap(photo)
            }
        }
        binding.btnAddAddress.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            openGoogleMaps()
        }


    }

        private fun openGoogleMaps(){
            val intentUri = Uri.parse("geo:0,0?q=-12.066877,-77.035721(IDAT-Lima centro)")
            val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager).let {
                startActivity(mapIntent)
            }
        }

    private fun openCamera(){
        val intent = Intent()
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }

    private fun permissionValidated(): Boolean{

        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()
        if(cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.CAMERA)
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 1000)
            return false
        }

        return true
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> {
                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }else{
                    Toast.makeText(this, "Permiso de camara denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }




}