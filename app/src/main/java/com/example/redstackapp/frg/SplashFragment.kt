package com.example.redstackapp.frg

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.redstackapp.R
import kotlinx.android.synthetic.main.activity_main.*


class SplashFragment : Fragment() {
    val SPLASH_TIME_OUT: Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            val action= SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            findNavController().navigate(action)
        }, SPLASH_TIME_OUT)
        return view

    }
}