package com.example.redstackapp.frg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.redstackapp.R
import com.example.redstackapp.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false)

        setup_nextView()
        return binding.root
    }

    private fun setup_nextView() {
        binding.btnEnter.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToNavigation()
            findNavController()?.navigate(action)
        }
        binding.tvEnter1.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController()?.navigate(action)
        }

    }

}