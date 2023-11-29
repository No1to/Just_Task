package com.example.just_task.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.just_task.R
import com.example.just_task.data.local.Pref
import com.example.just_task.databinding.FragmentOnBoardingBinding
import com.example.just_task.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private val adapter = OnBoardingAdapter(this::onClick)
    private lateinit var binding: FragmentOnBoardingBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager)
    }

    private fun onClick() {
        pref.onBoardingShow()
        findNavController().navigate(R.id.navigation_home)
    }

}