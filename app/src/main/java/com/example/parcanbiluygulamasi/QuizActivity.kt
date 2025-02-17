package com.example.parcanbiluygulamasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.parcanbiluygulamasi.databinding.ActivityMainBinding
import com.example.parcanbiluygulamasi.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var tasarim: ActivityQuizBinding

    private lateinit var sorular:ArrayList<Parcalar>
    private lateinit var yanlisSecenekler:ArrayList<Parcalar>
    private lateinit var dogruSoru:Parcalar
    private lateinit var tumSecenekler:HashSet<Parcalar>
    private lateinit var vt:VeritabaniYardimcisi

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityQuizBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(tasarim.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tasarim.buttonA.setOnClickListener {
            startActivity(Intent(this@QuizActivity,ResultActivity::class.java))
            finish()
        }
        vt = VeritabaniYardimcisi(this)

        sorular = Parcalardao().rasgele5ParcaGetir(vt)

        soruYukle()

        tasarim.buttonA.setOnClickListener {
            dogruKontrol(tasarim.buttonA)
            soruSayacKontrol()

        }

        tasarim.buttonB.setOnClickListener {
            dogruKontrol(tasarim.buttonB)
            soruSayacKontrol()

        }

        tasarim.buttonC.setOnClickListener {
            dogruKontrol(tasarim.buttonC)
            soruSayacKontrol()

        }

        tasarim.buttonD.setOnClickListener {
            dogruKontrol(tasarim.buttonD)
            soruSayacKontrol()

        }
    }

    fun soruYukle(){
        tasarim.textViewSoruSayi.text = "${soruSayac+1}. Soru"

        dogruSoru = sorular.get(soruSayac)

        tasarim.imageViewParca.setImageResource(resources.getIdentifier(dogruSoru.parca_resim,"drawable",packageName))

        yanlisSecenekler = Parcalardao().rasgele3YanlisSecenekGetir(vt,dogruSoru.parca_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        tasarim.buttonA.text = tumSecenekler.elementAt(0).parca_ad
        tasarim.buttonB.text = tumSecenekler.elementAt(1).parca_ad
        tasarim.buttonC.text = tumSecenekler.elementAt(2).parca_ad
        tasarim.buttonD.text = tumSecenekler.elementAt(3).parca_ad

    }

    fun soruSayacKontrol(){
        soruSayac++

        if(soruSayac != 5){
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button: Button){
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.parca_ad

        if(buttonYazi == dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }

        tasarim.textViewDogru.text = "Doğru : $dogruSayac"
        tasarim.textViewYanlis.text = "Yanlış : $yanlisSayac"
    }

    }


