package com.marlebas.doisinputs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marlebas.doisinputs.databinding.ActivityMainBinding

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

        binding.buttonCadastrar.setOnClickListener {
            val nome = binding.editNome.text.toString().trim()
            val sobreNome = binding.editSobreNome.text.toString().trim()

            if(nome.isEmpty() || sobreNome.isEmpty()){
                Toast.makeText(this, "Nome ou sobrenome não inserido!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Olá ${nome} ${sobreNome}", Toast.LENGTH_LONG).show()
            }
        }
    }
}