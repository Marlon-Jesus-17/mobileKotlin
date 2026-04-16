package com.marlebas.conversortemperatura

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marlebas.conversortemperatura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonConverter.setOnClickListener {
            if(!binding.editTemperatura.text.toString().isEmpty()){
                val celsius = binding.editTemperatura.text.toString().toDouble()
                val kelvin = String.format("%.2f", celsius + 273)
                binding.textResultado.setText("Valor em Kelvins: ${kelvin}")
            }else{
                binding.textResultado.text = "Temperatura inválida!"
            }
        }
    }
}