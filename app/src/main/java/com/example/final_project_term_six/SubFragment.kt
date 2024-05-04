package com.example.final_project_term_six

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project_term_six.AdapterClass.SubAdapter
import com.example.final_project_term_six.databinding.FragmentSubBinding
import com.example.final_project_term_six.modelClass.HomeModel


class SubFragment(private val title: String? = null) : Fragment() {
    private var _binding: FragmentSubBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<HomeModel>()
    private lateinit var adapter: SubAdapter






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        binding.rcv.layoutManager = LinearLayoutManager(context)
        list.clear()
        if(title=="Animal Riddles"){
            list.add(HomeModel("Mammal Riddles","Guess riddles about furry friends with four legs!"))
            list.add(HomeModel("Bird Riddles","Test your knowledge about feathered creatures!"))
            list.add(HomeModel("Reptile Riddles","Learn about scaly and fascinating reptiles through riddles!"))
            list.add(HomeModel("Aquatic Animal Riddles","Dive into the world of underwater animals with riddles!"))
            list.add(HomeModel("Insect Riddles","Explore the tiny but mighty world of insects with riddles!"))


        }
        if(title=="Food Riddles"){
            list.add(HomeModel("Fruit Riddles","Can you guess riddles about juicy and delicious fruits?"))
            list.add(HomeModel("Vegetable Riddles","Test your knowledge about healthy and colorful vegetables!"))
            list.add(HomeModel("Dessert Riddles","Indulge in riddles about sweet and tempting desserts!"))
            list.add(HomeModel("Fast Food Riddles","Challenge yourself with riddles about popular fast food items!"))
            list.add(HomeModel("Beverage Riddles","Unmask the riddles behind refreshing and tasty beverages!"))

        }
        if(title=="Nature Riddles"){
            list.add(HomeModel("Plant Riddles","Test your knowledge about trees, flowers, and other flora!"))
            list.add(HomeModel("Weather Riddles","Can you guess riddles about sunshine, rain, and other weather phenomena?"))
            list.add(HomeModel("Geography Riddles","Explore the world through riddles about mountains, oceans, and other landmarks!"))
            list.add(HomeModel("Animal Riddles","Test your knowledge with these riddles about animals!"))
            list.add(HomeModel("Landform Riddles","Can you solve these riddles about landforms?"))


        }
        if(title=="Math Riddles")
        {
            list.add(HomeModel("Number Riddles","Challenge yourself with riddles about different numbers and their properties!"))
            list.add(HomeModel("Operation Riddles","Test your knowledge about addition, subtraction, multiplication, and division through riddles!"))
            list.add(HomeModel("Geometry Riddles","Explore shapes, angles, and spatial reasoning with riddles!"))
            list.add(HomeModel("Reasoning Puzzles","Test your logical reasoning with puzzles involving deduction, induction, pattern recognition, and lateral thinking."))
            list.add(HomeModel("Logic Riddles","Exercise your mind with logic riddles that challenge your reasoning and problem-solving skills!"))


        }
        if(title=="Science Riddles")
        {
            list.add(HomeModel("Biology Riddles","Test your knowledge about living things, cells, and the human body through riddles!"))
            list.add(HomeModel("Physics Riddles","Explore riddles about motion, force, energy, and other physical phenomena!"))
            list.add(HomeModel("Chemistry Riddles","Can you guess riddles about elements, compounds, and chemical reactions?"))
            list.add(HomeModel("Environmental Riddles","Challenge yourself with these riddles about the environment!"))
            list.add(HomeModel("Space Riddles","Can you solve these riddles about space?"))



        }
        if(title=="History Riddles")
        {
            list.add(HomeModel("Ancient History Riddles","Travel back in time to solve riddles about civilizations like Egypt, Greece, and Rome!"))
            list.add(HomeModel("Medieval History Riddles","Explore riddles about the Middle Ages, knights, and castles!"))
            list.add(HomeModel("World War Riddles","Test your knowledge with these riddles about World Wars!"))
            list.add(HomeModel("Famous Figures Riddles","Explore the mysteries of history with these riddles about famous figures!"))
            list.add(HomeModel("Historical Events Riddles","Challenge yourself with these riddles about historical events!"))


        }
        if(title=="Sports Riddles")
        {
            list.add(HomeModel("Football Riddles","Can you guess these riddles about football?"))
            list.add(HomeModel("Basketball Riddles","Test your knowledge with these riddles about basketball!"))
            list.add(HomeModel("Cricket Riddles","Explore the mysteries of cricket with these riddles!"))
            list.add(HomeModel("Tennis Riddles","Can you solve these riddles about tennis?"))
            list.add(HomeModel("Golf Riddles","Challenge yourself with these riddles about golf!"))


        }



        if(title=="Space Riddles")
        {
            list.add(HomeModel("Space Exploration Riddles","Guess the riddles about space exploration!"))
            list.add(HomeModel("Planetary Riddles","Can you solve these riddles about planets?"))
            list.add(HomeModel("Astronomy Riddles","Test your knowledge with these riddles about astronomy!"))
            list.add(HomeModel("Galaxy Riddles","Explore the mysteries of galaxies with these riddles!"))
            list.add(HomeModel("Alien Riddles","Challenge yourself with these riddles about aliens!"))




        }
        if(title=="Fantasy Riddles")
        {
            list.add(HomeModel("Magical Creatures Riddles","Guess the riddles about magical creatures!"))
            list.add(HomeModel("Wizardry Riddles","Can you solve these riddles about wizards and witches?"))
            list.add(HomeModel("Enchanted Places Riddles","Test your knowledge with these riddles about enchanted places!"))
            list.add(HomeModel("Fantasy Adventure Riddles","Explore the mysteries of fantasy worlds with these riddles!"))
            list.add(HomeModel("Legendary Artifacts Riddles","Challenge yourself with these riddles about legendary artifacts!"))



        }
        if(title=="Music Riddles")
        {
            list.add(HomeModel("Instrument Riddles","Guess the riddles about musical instruments!"))
            list.add(HomeModel("Song Lyrics Riddles","Can you solve these riddles about song lyrics?"))
            list.add(HomeModel("Musician Riddles","Test your knowledge with these riddles about famous musicians!"))
            list.add(HomeModel("Music Genres Riddles","Explore the mysteries of music genres with these riddles!"))
            list.add(HomeModel("Concert Riddles","Challenge yourself with these riddles about music concerts!"))



        }





        adapter = SubAdapter(requireContext(), list)
        binding.rcv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}