package com.example.jetpacksample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jetpacksample.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOneBinding.inflate(layoutInflater)

        binding.btnTwoByOne.setOnClickListener{
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment)
        }
        binding.btnThreeByOne.setOnClickListener{
            findNavController().navigate(R.id.action_oneFragment_to_threeFragment)
        }
        return binding.root
    }
}