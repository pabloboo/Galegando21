package com.galegando21.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.galegando21.MainActivity
import com.galegando21.R

class OnboardingStep2Fragment : Fragment() {
    private lateinit var anteriorButton: Button
    private lateinit var comezarButton: Button
    private lateinit var editTextNome: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anteriorButton = view.findViewById(R.id.anteriorButton)
        comezarButton = view.findViewById(R.id.comezarButton)
        editTextNome = view.findViewById(R.id.editTextNome)

        anteriorButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingStep2Fragment_to_onboardingStep1Fragment)
        }

        comezarButton.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("onboarding", AppCompatActivity.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("is_onboarding_completed", true)
                putString("name", editTextNome.text.toString())
                commit()
            }

            val isOnboardingCompleted = sharedPreferences.getBoolean("is_onboarding_completed", false)
            Log.d("OnboardingStep2Fragment", "is_onboarding_completed: $isOnboardingCompleted")

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}