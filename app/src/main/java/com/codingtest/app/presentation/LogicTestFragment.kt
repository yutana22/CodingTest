package com.codingtest.app.presentation

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codingtest.app.R
import com.codingtest.app.databinding.FragmentLogicTestBinding


class LogicTestFragment : Fragment() {

    private lateinit var binding: FragmentLogicTestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogicTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        binding.resultQuestionOneButton.setOnClickListener {
            binding.resultMiddleIndexTextView.text =
                middleIndex(binding.inputQuestionOneEditText.text).toString()
        }
        binding.resultQuestionTwoButton.setOnClickListener {
            binding.resultPalindromeTextView.text =
                checkPalindrome(binding.inputQuestionTwoEditText.text.toString())
        }
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun middleIndex(text: Editable): String {
        val regex = Regex("^(\\d+)(,[0-9]+)*$")
        if (regex.matches(text)) {
            val arr = text.split(",").toList()
            for (i in arr.indices) {
                var sumOfLeft = 0
                var sumOfRight = 0

                for (j in 0 until i) {
                    sumOfLeft += arr[j].toInt()
                }

                for (k in i + 1 until arr.size) {
                    sumOfRight += arr[k].toInt()
                }

                if (sumOfLeft == sumOfRight) {
                    return "middle index is $i = [${arr[i]}]"
                }
            }
            return "no middle index found"
        } else {
            return "Format Error!! ex. 1,2,3,4"
        }
    }

    private fun checkPalindrome(input: String): String {
        val ans: String = if (input.isNotEmpty()) {
            if (input[0].lowercase() == input[input.length - 1].lowercase()) {
                "$input is a palindrome"
            } else {
                "$input isn't a palindrome"
            }
        } else {
            "$input Please input String."
        }
        return ans
    }
}