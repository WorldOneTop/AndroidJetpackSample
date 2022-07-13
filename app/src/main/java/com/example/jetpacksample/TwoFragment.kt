package com.example.jetpacksample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jetpacksample.databinding.FragmentOneBinding
import com.example.jetpacksample.databinding.FragmentTwoBinding


class TwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTwoBinding.inflate(layoutInflater)

        binding.btnOneByTwo.setOnClickListener{
            findNavController().navigate(R.id.action_twoFragment_to_oneFragment)
        }
        binding.btnThreeByTwo.setOnClickListener{
            findNavController().navigate(R.id.action_twoFragment_to_threeFragment)
        }
        return binding.root
    }


}