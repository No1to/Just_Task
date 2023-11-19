package com.example.just_task.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.just_task.R
import com.example.just_task.databinding.FragmentOnBoardingBinding
import com.example.just_task.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private val adapter = OnBoardingAdapter(this::onClick)

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.vp2Onboarding.adapter = adapter
        val viewPager2 = binding.vp2Onboarding
        viewPager2.adapter = adapter
        binding.wormDotsIndicator.attachTo(viewPager2)
    }

    private fun onClick() {
        findNavController().navigate(R.id.navigation_home)
    }
}