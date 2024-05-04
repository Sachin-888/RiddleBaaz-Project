package com.example.final_project_term_six

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader

class Fragment4 : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var counterTextView: TextView
    private lateinit var accurate: TextView
    private lateinit var reset: TextView
    private val counterKey = "counter"
    private val totalKey = "counter"
    private lateinit var correctTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var bt: Button
    private lateinit var incrementButton: Button
    private lateinit var progressBar: ProgressBar
//    private lateinit var progressBar2: ProgressBar
    private val fileName = "mydata.txt"
    private val data = "0"
    private lateinit var sharedPreferences1: SharedPreferences
    private lateinit var sharedPreferences2: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_4, container, false)
        sharedPreferences = requireContext().getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        sharedPreferences1 = requireContext().getSharedPreferences("correct_pref", Context.MODE_PRIVATE)

        sharedPreferences2 = requireContext().getSharedPreferences("total_pref", Context.MODE_PRIVATE)
        progressBar = view.findViewById(R.id.progress)
//        progressBar2 = view.findViewById(R.id.progress)
        // Find TextView and Button
        counterTextView = view.findViewById(R.id.correct)
        totalTextView = view.findViewById(R.id.total)

        accurate = view.findViewById(R.id.accuracy)
        reset = view.findViewById(R.id.reset)
        // Set initial counter value
        val counter = sharedPreferences1.getInt(counterKey, 0)
        counterTextView.text = counter.toString()
        val counter2 = sharedPreferences2.getInt(totalKey, 0)
        totalTextView.text = counter2.toString()
        val percentage = (counter.toDouble() / counter2.toDouble()) * 100
        // Display the percentage
        accurate.text = "%.2f%%".format(percentage)
        progressBar.max = counter2
        progressBar.progress = counter
//        progressBar2.max = counter2
//        progressBar2.progress = counter
        reset.setOnClickListener{
            saveCounter(0)
            saveCounter2(0)

        }


        return view
    }
    private fun saveCounter(counter: Int) {
        val editor = sharedPreferences1.edit()
        editor.putInt(counterKey, counter)
        editor.apply()


    }
    private fun saveCounter2(counter: Int) {
        val editor2 = sharedPreferences2.edit()
        editor2.putInt(totalKey, counter)
        editor2.apply()
    }
}
