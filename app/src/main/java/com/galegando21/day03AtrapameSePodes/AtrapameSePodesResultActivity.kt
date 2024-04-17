package com.galegando21.day03AtrapameSePodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.galegando21.MainActivity
import com.galegando21.R
import com.galegando21.utils.AtrapameSePodesConstants
import com.galegando21.utils.setBanner
import com.galegando21.utils.setOnBackPressed

class AtrapameSePodesResultActivity : AppCompatActivity() {
    private lateinit var stepsFragment: AtrapameSePodesStepsFragment
    private lateinit var atrapameSePodesResultTV: TextView
    private lateinit var atrapameSePodesFinishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapame_se_podes_result)

        atrapameSePodesResultTV = findViewById(R.id.atrapame_se_podes_result_tv)
        atrapameSePodesFinishButton = findViewById(R.id.atrapame_se_podes_finish_btn)

        setBanner(this, R.string.atrapame_se_podes)

        // Settear el steps fragment
        stepsFragment = supportFragmentManager.findFragmentById(R.id.atrapame_se_podes_level_fragment_container_result) as AtrapameSePodesStepsFragment
        supportFragmentManager.beginTransaction().runOnCommit {
            stepsFragment.setStepsImage(5)
        }.commit()

        val score = intent.getIntExtra(AtrapameSePodesConstants.SCORE, 0)
        atrapameSePodesResultTV.text = "Necesitaches un total de $score preguntas para subir 5 escalóns."

        atrapameSePodesFinishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        setOnBackPressed(this, AtrapameSePodesInicioActivity::class.java)
    }
}