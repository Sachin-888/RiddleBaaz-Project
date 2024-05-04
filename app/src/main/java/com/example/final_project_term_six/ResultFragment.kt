package com.example.final_project_term_six

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project_term_six.databinding.FragmentResultBinding
import com.example.final_project_term_six.databinding.FragmentRiddleBinding


class ResultFragment(private val right:Int, private val allQues: Int) : Fragment() {
    private var _binding: com.example.final_project_term_six.databinding.FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val r=right.toString()
        val w=(allQues-right).toString()
        binding.correct.setText("$r Correct")
        binding.incorrect.setText("$w Wrong")
        binding.score.setText("You got the $right out of 5")
        binding.footRes.setText("$w Wrong!")

    }

}