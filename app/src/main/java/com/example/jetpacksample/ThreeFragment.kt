package com.example.jetpacksample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jetpacksample.databinding.FragmentOneBinding
import com.example.jetpacksample.databinding.FragmentThreeBinding


class ThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThreeBinding.inflate(layoutInflater)

        binding.btnOneByThree.setOnClickListener{
            findNavController().navigate(R.id.action_threeFragment_to_oneFragment)
        }
        binding.btnTwoByThree.setOnClickListener{
            findNavController().navigate(R.id.action_threeFragment_to_twoFragment)
        }
        return binding.root
    }


}