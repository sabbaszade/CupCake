package com.example.redstackapp.frg

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.redstackapp.R
import com.example.redstackapp.adapter.MyPagerAdapter
import com.example.redstackapp.databinding.FragmentBaseBinding
import com.google.android.material.tabs.TabLayout


class BaseFragment : Fragment() {
    private lateinit var binding: FragmentBaseBinding
    var time_cur_back: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)

        set_up_viewPager(binding.tabLayout, binding.viewPager)

        set_up_navigation()
        setBacking()
        return binding.root
    }

    private fun setBacking() {
        requireActivity().onBackPressedDispatcher.addCallback(this)
        {
            if (time_cur_back < System.currentTimeMillis()) {
                Toast.makeText(requireContext(), getString(R.string.exit_des), Toast.LENGTH_SHORT)
                    .show()

                time_cur_back = System.currentTimeMillis() + 2000
            } else {
                requireActivity().finish()
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun set_up_viewPager(
        tabLayout: TabLayout,
        viewPager: ViewPager
    ) {
        tabLayout.addTab(tabLayout.newTab().setText("برای شما"))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.commerce))
        tabLayout.tabGravity = TabLayout.TAB_LABEL_VISIBILITY_LABELED

        val adapter = MyPagerAdapter(
            childFragmentManager,
            tabLayout.tabCount
        )
        viewPager.adapter = adapter
        viewPager.setCurrentItem(0, true)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    private fun set_up_navigation() {
        binding.imgWrite.setOnClickListener {
            val action = BaseFragmentDirections.actionGlobalWriteArticleFragment()
            findNavController().navigate(action)
        }
        binding.userProfile.setOnClickListener {
            val action = BaseFragmentDirections.actionGlobalProfileFragment(false)
            findNavController().navigate(action)
        }

    }


}