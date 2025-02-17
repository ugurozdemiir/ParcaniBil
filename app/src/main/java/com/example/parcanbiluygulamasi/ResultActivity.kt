package com.example.parcanbiluygulamasi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcanbiluygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var tasarim:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim=ActivityResultBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(tasarim.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dogruSayac = intent.getIntExtra("dogruSayac",0)

        tasarim.textViewSonuc.text = "$dogruSayac DOĞRU ${5-dogruSayac} YANLIŞ"

        tasarim.textViewYuzdeSonuc.text = "% ${(dogruSayac*100)/5} Başarı"


        tasarim.buttonTekrar.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }

    }
}