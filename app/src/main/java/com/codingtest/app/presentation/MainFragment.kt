package com.codingtest.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codingtest.app.R
import com.codingtest.app.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logicTestButton.setOnClickListener {
            navigation(R.id.action_mainFragment_to_logicTestFragment)
        }
        binding.codingTestButton.setOnClickListener {
            navigation(R.id.action_mainFragment_to_codingFragment)
        }
    }

    private fun navigation(actionToFragment: Int) {
        findNavController().navigate(actionToFragment)
    }
}