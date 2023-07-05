package com.example.ec2_sl74906795

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.ec2_sl74906795.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

    }



    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            val usuario = "ejemplo@idat.edu.pe"
            val contrasenia = "123456"

            val txtUsuario = binding.txtUsuario.editText?.text.toString().trim()
            val txtPass = binding.txtPassword.editText?.text.toString().trim()

            if (txtUsuario == usuario && txtPass == contrasenia) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CamaraActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }

    }




}