package com.galegando21.day04AtrapaUnMillon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapaUnMillonConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapaUnMillonResultsActivity : AppCompatActivity() {
    private lateinit var atrapaUnMillonResultsTv : TextView
    private lateinit var atrapaUnMillonFinishButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapa_un_millon_results)

        atrapaUnMillonResultsTv = findViewById(R.id.atrapa_un_millon_results_textView)
        atrapaUnMillonFinishButton = findViewById(R.id.atrapa_un_millon_finish_btn)

        setBanner(this, R.string.atrapa_un_millon)

        val cash = intent.getIntExtra(AtrapaUnMillonConstants.SCORE, 0).toString()
        if (cash.toInt() == 0) {
            atrapaUnMillonResultsTv.text = "Non gañaches nada. \n Mellor sorte a próxima vez!"
        } else {
            atrapaUnMillonResultsTv.text = "Parabéns! gañaches unha cantidade \n total de $cash€"
        }

        atrapaUnMillonFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, AtrapaUnMillonInicioActivity::class.java)
    }
}