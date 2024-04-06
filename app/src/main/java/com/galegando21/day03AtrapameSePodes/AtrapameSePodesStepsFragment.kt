package com.galegando21.day03AtrapameSePodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.galegando21.R

class AtrapameSePodesStepsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atrapame_se_podes_steps, container, false)

    }

    fun setStepsImage(level: Int) {
        val atrapameSePodesLevelImage : ImageView = requireView().findViewById(R.id.atrapame_se_podes_level_image)
        when(level) {
            0 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl0)
            1 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl1)
            2 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl2)
            3 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl3)
            4 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl4)
            5 -> atrapameSePodesLevelImage.setImageResource(R.drawable.atrapame_se_podes_steps_lvl5)
        }
    }
}