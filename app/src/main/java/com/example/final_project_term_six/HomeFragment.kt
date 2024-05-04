package com.example.final_project_term_six

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project_term_six.AdapterClass.HomeAdapter
import com.example.final_project_term_six.databinding.FragmentHomeBinding
import com.example.final_project_term_six.modelClass.HomeModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<HomeModel>()
    private lateinit var adapter: HomeAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var menu=view.findViewById<ImageView>(R.id.menu)

        menu.setOnClickListener{
            Toast.makeText(requireContext(),"Successfull saved", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), SwipeTabExample::class.java)
            startActivity(intent)
        }


        loadData()
    }


    private fun loadData() {
        binding.rcv.layoutManager = LinearLayoutManager(context)
        list.clear()
        list.add(HomeModel("Animal Riddles","Can you guess these riddles about animals?"))
        list.add(HomeModel("Food Riddles","Test your knowledge with these riddles about food!"))
        list.add(HomeModel("Nature Riddles","Explore the wonders of nature with these riddles!"))
        list.add(HomeModel("Math Riddles","Challenge your mind with these tricky math riddles!"))
        list.add(HomeModel("Science Riddles","Discover fascinating science facts with these riddles!"))
        list.add(HomeModel("History Riddles","Travel back in time with these historical riddles!"))
        list.add(HomeModel("Space Riddles","Explore the mysteries of the universe with these space-themed riddles!"))
        list.add(HomeModel("Fantasy Riddles","Embark on a magical journey with these fantasy-themed riddles!"))
        list.add(HomeModel("Sports Riddles","Get in the game with these sports-themed riddles!"))
        list.add(HomeModel("Music Riddles","Test your musical knowledge with these riddles about music!"))


        adapter = HomeAdapter(requireContext(), list)
        binding.rcv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}