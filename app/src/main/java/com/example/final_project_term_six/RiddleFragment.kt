package com.example.final_project_term_six

import android.Manifest


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.final_project_term_six.databinding.FragmentRiddleBinding

import com.example.final_project_term_six.modelClass.QuizModel
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random


class RiddleFragment(private val title:String) : Fragment() {

    private var _binding: com.example.final_project_term_six.databinding.FragmentRiddleBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<QuizModel>()
    private var allQuestion = 5
    private var position = 0
    private var right = 0
    var positionNo=""
    private var answer=""
    var quizModel: QuizModel =QuizModel()
    private lateinit var sharedPreferences1: SharedPreferences
    private lateinit var sharedPreferences2: SharedPreferences
    private val correct = "counter"
    private val total = "counter"

    lateinit var fstream: FileOutputStream
    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    lateinit var myExternalFile: File
    var mPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPreferences1 = requireContext().getSharedPreferences("correct_pref", Context.MODE_PRIVATE)

        sharedPreferences2 = requireContext().getSharedPreferences("total_pref", Context.MODE_PRIVATE)
        _binding = FragmentRiddleBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadQuestion()
        enableOption()
        clearOption()

        binding.nextBtn.setOnClickListener {
            position++
            binding.qNo.setText((position+1).toString())
            loadQuestion()
            enableOption()
            clearOption()
            checknext()

        }


    }
    private fun checknext(){
        if(position==allQuestion){
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.wrapper, ResultFragment(right,allQuestion))
                .commit()
            list.clear()
            position = 0


        }
    }


    private fun loadQuestion() {

        if (title == "Mammal Riddles") {list.add(QuizModel("I have a pouch and hop around. What am I?", "Kangaroo", "Koala", "Wombat", "Echidna", "Kangaroo"))
            list.add(QuizModel("I have a black and white coat and love to swim. What am I?", "Panda", "Zebra", "Dolphin", "Whale", "Panda"))
            list.add(QuizModel("I have a long snout and love to dig in the mud. What am I?", "Pig", "Hippopotamus", "Elephant", "Warthog", "Pig"))
            list.add(QuizModel("I climb trees and swing from branches. What am I?", "Monkey", "Gorilla", "Chimpanzee", "Orangutan", "Monkey"))
            list.add(QuizModel("I am the largest land animal alive. What am I?", "Elephant", "Rhinoceros", "Giraffe", "Lion", "Elephant"))}
        if (title == "Bird Riddles") {list.add(QuizModel("I am known for my bright red crest and loud call. What am I?", "Cardinal", "Robin", "Blue Jay", "Parrot", "Cardinal"))
            list.add(QuizModel("I can soar high in the sky and have a keen eyesight. What am I?", "Eagle", "Hawk", "Owl", "Vulture", "Eagle"))
            list.add(QuizModel("I am a social bird known for building elaborate nests. What am I?", "Crow", "Raven", "Sparrow", "Magpie", "Crow"))
            list.add(QuizModel("I am a colorful bird with a long, elegant neck. What am I?", "Flamingo", "Peacock", "Swan", "Heron", "Flamingo"))
            list.add(QuizModel("I am a small brown bird that loves to chirp and hop around. What am I?", "Sparrow", "Robin", "Finch", "Wren", "Sparrow"))}
        if (title == "Reptile Riddles") {list.add(QuizModel("I have a hard shell and can carry my home on my back. What am I?", "Turtle", "Tortoise", "Lizard", "Snake", "Turtle"))
            list.add(QuizModel("I am long and slithery and can shed my skin. What am I?", "Snake", "Lizard", "Crocodile", "Alligator", "Snake"))
            list.add(QuizModel("I am a cold-blooded amphibian that spends part of its life in water. What am I (Trick question - not a reptile)?", "Frog", "Toad", "Salamander", "Newt", "Frog"))
            list.add(QuizModel("I have a spiky body and can camouflage myself. What am I?", "Chameleon", "Gecko", "Iguana", "Komodo Dragon", "Chameleon"))
            list.add(QuizModel("I am a large aquatic reptile with sharp teeth. What am I?", "Crocodile", "Alligator", "Caiman", "Gharial", "Crocodile"))}
        if (title == "Aquatic Animal Riddles") {list.add(QuizModel("I have gills and fins and breathe underwater. What am I?", "Fish", "Dolphin", "Whale", "Shark", "Fish"))
            list.add(QuizModel("I am a large mammal that lives in the ocean and sprays water from my blowhole. What am I?", "Whale", "Dolphin", "Seal", "Sea lion", "Whale"))
            list.add(QuizModel("I am a playful mammal known for my acrobatic jumps. What am I?", "Dolphin", "Porpoise", "Seal", "Sea lion", "Dolphin"))
            list.add(QuizModel("I have eight arms and tentacles and live on the ocean floor. What am I?", "Octopus", "Squid", "Cuttlefish", "Crab", "Octopus"))
            list.add(QuizModel("I have a hard shell and live on the bottom of the ocean. What am I?", "Crab", "Lobster", "Shrimp", "Hermit crab", "Crab"))}
        if (title == "Insect Riddles") {list.add(QuizModel("I have beautiful wings and collect pollen from flowers. What am I?", "Butterfly", "Bee", "Fly", "Mosquito", "Butterfly"))
            list.add(QuizModel("I have a painful sting and live in hives. What am I?", "Bee", "Wasp", "Hornet", "Yellowjacket", "Bee"))
            list.add(QuizModel("I have six legs and can jump very high. What am I?", "Grasshopper", "Cricket", "Locust", "Flea", "Grasshopper"))
            list.add(QuizModel("I have a long, thin body and suck blood. What am I?", "Mosquito", "Fly", "Gnat", "Midge", "Mosquito"))
            list.add(QuizModel("I have a hard shell and carry my food on my back. What am I?", "Beetle", "Ant", "Ladybug", "Roach", "Beetle"))}
        if (title == "Magical Creatures Riddles") {list.add(QuizModel("What mythical creature is known for its ability to breathe fire?", "Unicorn", "Dragon", "Griffin", "Phoenix", "Dragon"))
            list.add(QuizModel("What legendary creature is said to have the body of a lion and the head of an eagle?", "Hippogriff", "Basilisk", "Chimera", "Harpy", "Griffin"))
            list.add(QuizModel("Which creature from Celtic mythology is said to resemble a horse with a single spiraling horn on its forehead?", "Centaur", "Pegasus", "Banshee", "Unicorn", "Unicorn"))
            list.add(QuizModel("What creature from Greek mythology is depicted as having the upper body of a woman and the lower body of a bird?", "Medusa", "Siren", "Harpy", "Sphinx", "Harpy"))
            list.add(QuizModel("Which mythical creature is often portrayed as a large, serpentine sea monster?", "Leviathan", "Kraken", "Hydra", "Cerberus", "Kraken"))}
        if (title == "Wizardry Riddles") {list.add(QuizModel("What is the term for a magical object used to conceal or reveal secret messages?", "Portkey", "Howler", "Invisibility Cloak", "Sneakoscope", "Sneakoscope"))
            list.add(QuizModel("What is the name of the magical school where Harry Potter learns to become a wizard?", "Durmstrang", "Beauxbatons", "Hogwarts", "Ilvermorny", "Hogwarts"))
            list.add(QuizModel("What is the name of the spell used to summon objects to the caster's hand?", "Expelliarmus", "Accio", "Lumos", "Expecto Patronum", "Accio"))
            list.add(QuizModel("What is the name of the magical creature that serves as the messenger of Hagrid in Harry Potter?", "Buckbeak", "Fawkes", "Hedwig", "Norbert", "Hedwig"))
            list.add(QuizModel("What is the name of the village where wizards and witches live in hiding from Muggles?", "Diagon Alley", "Hogsmeade", "Godric's Hollow", "Ottery St. Catchpole", "Hogsmeade"))}
        if (title == "Enchanted Places Riddles") {list.add(QuizModel("What is the name of the hidden alleyway in London where wizards and witches buy magical supplies?", "Diagon Alley", "Hogsmeade", "Knockturn Alley", "Ottery St. Catchpole", "Diagon Alley"))
            list.add(QuizModel("What is the name of the wizarding school located in France?", "Beauxbatons", "Durmstrang", "Hogwarts", "Ilvermorny", "Beauxbatons"))
            list.add(QuizModel("What is the name of the forest near Hogwarts where many dangerous creatures dwell?", "Forbidden Forest", "Dark Forest", "Enchanted Forest", "Whispering Woods", "Forbidden Forest"))
            list.add(QuizModel("What is the name of the wizard prison guarded by Dementors?", "Azkaban", "Alcatraz", "Devil's Island", "Sing Sing", "Azkaban"))
            list.add(QuizModel("What is the name of the village where the Weasley family lives?", "Ottery St. Catchpole", "Godric's Hollow", "Little Hangleton", "Upper Flagley", "Ottery St. Catchpole"))}
        if (title == "Fantasy Adventure Riddles") {list.add(QuizModel("What is the name of the magical object that grants the wielder the power of invisibility?", "Elder Wand", "Philosopher's Stone", "Cloak of Invisibility", "Resurrection Stone", "Cloak of Invisibility"))
            list.add(QuizModel("What is the name of the underground chamber at Hogwarts where the Philosopher's Stone was hidden?", "Chamber of Secrets", "Room of Requirement", "Forbidden Forest", "Mirror of Erised", "Chamber of Secrets"))
            list.add(QuizModel("What is the name of the magical creature that is half horse, half eagle?", "Hippogriff", "Basilisk", "Thestral", "Sphinx", "Hippogriff"))
            list.add(QuizModel("What is the name of the dragon species that has black scales and breathes fire?", "Hungarian Horntail", "Chinese Fireball", "Swedish Short-Snout", "Norwegian Ridgeback", "Hungarian Horntail"))
            list.add(QuizModel("What is the name of the ancient and powerful wizard who founded Hogwarts School of Witchcraft and Wizardry?", "Godric Gryffindor", "Salazar Slytherin", "Rowena Ravenclaw", "Helga Hufflepuff", "Godric Gryffindor"))}
        if (title == "Legendary Artifacts Riddles") {list.add(QuizModel("What is the name of the magical artifact that grants the possessor unlimited power?", "Elder Wand", "Philosopher's Stone", "Cloak of Invisibility", "Resurrection Stone", "Elder Wand"))
            list.add(QuizModel("What is the name of the magical mirror that shows the deepest desires of the viewer?", "Mirror of Erised", "Time-Turner", "Pensieve", "Vanishing Cabinet", "Mirror of Erised"))
            list.add(QuizModel("What is the name of the magical map that reveals the layout of Hogwarts and its grounds?", "Marauder's Map", "Sorcerer's Stone", "Goblet of Fire", "Triwizard Cup", "Marauder's Map"))
            list.add(QuizModel("What is the name of the magical object that grants the possessor the power of immortality?", "Philosopher's Stone", "Invisibility Cloak", "Resurrection Stone", "Elder Wand", "Philosopher's Stone"))
            list.add(QuizModel("What is the name of the magical artifact that allows the possessor to travel through time?", "Time-Turner", "Elder Wand", "Philosopher's Stone", "Invisibility Cloak", "Time-Turner"))}
        if (title == "Fruit Riddles") {list.add(QuizModel("I'm red and round, and I grow on a tree. I'm juicy and sweet, a delicious treat for you and me. What am I?", "Apple", "Cherry", "Strawberry", "Watermelon", "Apple"))
            list.add(QuizModel("I'm purple and long, with a crown on my head. I'm a tropical fruit, enjoyed all over the world instead. What am I?", "Mango", "Grapefruit", "Pineapple", "Banana", "Pineapple"))
            list.add(QuizModel("I'm orange and juicy, with a thick peel to protect me. I'm full of vitamin C, and good for your health, you see. What am I?", "Orange", "Peach", "Mango", "Grapefruit", "Orange"))
            list.add(QuizModel("I'm green and bumpy, but sweet inside. I can be eaten raw or made into a pie, a delicious hide. What am I?", "Honeydew melon", "Kiwi", "Watermelon", "Lime", "Honeydew melon"))
            list.add(QuizModel("I'm small and red, and grow in clusters on a vine. I'm a sweet and juicy snack, enjoyed anytime. What am I?", "Grape", "Cherry", "Strawberry", "Raspberry", "Grape"))}
        if (title == "Vegetable Riddles") {list.add(QuizModel("I'm orange and crunchy, and grow underground. I'm a root vegetable, a tasty and healthy crown. What am I?", "Carrot", "Potato", "Onion", "Radish", "Carrot"))
            list.add(QuizModel("I'm green and leafy, and good in a salad. I come in many shapes and sizes, not one to be ballad. What am I?", "Lettuce", "Spinach", "Kale", "Cabbage", "Lettuce"))
            list.add(QuizModel("I'm red and round, and grow on a vine. I'm a juicy vegetable, perfect for a summer time. What am I?", "Tomato", "Bell pepper", "Eggplant", "Radish", "Tomato"))
            list.add(QuizModel("I'm green and long, with a yellow cob inside. I'm a summer vegetable, enjoyed when cooked or fried. What am I?", "Corn", "Green bean", "Asparagus", "Celery", "Corn"))
            list.add(QuizModel("I'm brown and bumpy, with a fuzzy head on top. I'm a root vegetable, often mashed and served in a slop. What am I?", "Potato", "Onion", "Garlic", "Turnip", "Potato"))}
        if (title == "Dessert Riddles") {list.add(QuizModel("I'm round and fried, with a hole in the middle. I'm dusted with sugar, a delicious riddle. What am I?", "Donut", "Doughnut (alternate spelling)", "Pastry", "Muffin", "Donut"))
            list.add(QuizModel("I'm flaky and buttery, with layers of fruit inside. I'm perfect for breakfast or a sweet afternoon ride. What am I?", "Pastry", "Croissant", "Pie", "Strudel", "Pastry"))
            list.add(QuizModel("I'm cold and creamy, and come in a cup or cone. I'm blended with fruits or nuts, a delicious treat all on my own. What am I?", "Milkshake", "Smoothie", "Frozen yogurt", "Ice cream", "Milkshake"))
            list.add(QuizModel("I'm baked in a pan, and frosted on top. I can be chocolate, vanilla, or any flavor you won't stop. What am I?", "Cake", "Brownie", "Cupcake", "Cookie", "Cake"))
            list.add(QuizModel("I'm a small, individual cake, perfect for a party or a bake. I come in many flavors, and a delicious treat to take. What am I?", "Cupcake", "Muffin", "Brownie", "Cookie", "Cupcake"))}
        if (title == "Fast Food Riddles") {list.add(QuizModel("I'm a juicy all-beef patty on a sesame seed bun, often served with fries for fun. What am I?", "Hamburger", "Cheeseburger", "Hot dog", "Chicken sandwich", "Hamburger"))
            list.add(QuizModel("I come in buckets or meals, with crispy golden fried appeal. I'm a finger lickin good kind of treat, enjoyed on the go or at your seat. What am I?", "Fried chicken", "Chicken nuggets", "Chicken wings", "Popcorn chicken", "Fried chicken"))
            list.add(QuizModel("I'm a breakfast sandwich on an English muffin, with a round egg, cheese, and Canadian bacon within. What am I?", "Egg McMuffin", "Sausage McMuffin", "Bacon, Egg & Cheese", "Breakfast burrito", "Egg McMuffin"))
            list.add(QuizModel("I'm a flame-grilled burger with a secret sauce so special. I'm customizable with many toppings, a delicious and convenient essential. What am I?", "Whopper", "Big Mac", "Cheeseburger", "In-N-Out Burger", "Whopper"))
            list.add(QuizModel("I'm a bed of fries, salty and golden, perfect for dipping in ketchup or other delights beholden. What am I?", "French fries", "Onion rings", "Tater tots", "Mozzarella sticks", "French fries"))}
        if (title == "Beverage Riddles") {list.add(QuizModel("I'm dark, bitter, and brewed with hot water. I come in many flavors, enjoyed morning, noon, or later. What am I?", "Coffee", "Tea", "Hot chocolate", "Soda", "Coffee"))
            list.add(QuizModel("I'm fizzy and sweet, with many flavors to choose. I'm a popular drink, enjoyed by many to lose their blues. What am I?", "Soda", "Juice", "Pop (regional term)", "Sparkling water", "Soda"))
            list.add(QuizModel("I'm a creamy and cold beverage, made with blended fruit and ice. I'm a refreshing drink, perfect in a summery vice. What am I?", "Smoothie", "Milkshake", "Frappe", "Protein shake", "Smoothie"))
            list.add(QuizModel("I'm a hot beverage made with steamed milk and espresso. I come in many varieties, with a creamy, delicious flow. What am I?", "Latte", "Cappuccino", "Mocha", "Americano", "Latte"))
            list.add(QuizModel("I'm a clear and refreshing drink, perfect for quenching your thirst. I come in bottles or cans, enjoyed by all from first to worst. What am I?", "Water", "Sparkling water", "Juice", "Sports drink", "Water"))}
        if (title == "Ancient History Riddles") {list.add(QuizModel("Which ancient civilization built the Great Pyramid of Giza?", "Sumerians", "Egyptians", "Greeks", "Romans", "Egyptians"))
            list.add(QuizModel("What is the name of the ancient city known for its Hanging Gardens?", "Babylon", "Athens", "Rome", "Alexandria", "Babylon"))
            list.add(QuizModel("Which ancient empire was ruled by Julius Caesar and Augustus Caesar?", "Persian Empire", "Roman Empire", "Ottoman Empire", "Byzantine Empire", "Roman Empire"))
            list.add(QuizModel("Who was the legendary founder of Rome?", "Romulus", "Alexander the Great", "Julius Caesar", "Augustus Caesar", "Romulus"))
            list.add(QuizModel("Which ancient civilization developed the world's first system of writing called cuneiform?", "Egyptians", "Sumerians", "Greeks", "Chinese", "Sumerians"))}
        if (title == "Medieval History Riddles") {list.add(QuizModel("What was the name of the period of cultural and economic revival in Europe during the Middle Ages?", "Renaissance", "Dark Ages", "Enlightenment", "Industrial Revolution", "Renaissance"))
            list.add(QuizModel("Who was the English king who signed the Magna Carta in 1215?", "Henry VIII", "Richard the Lionhearted", "William the Conqueror", "King John", "King John"))
            list.add(QuizModel("Which famous English battle in 1066 marked the Norman conquest of England?", "Battle of Agincourt", "Battle of Hastings", "Battle of Bannockburn", "Battle of Thermopylae", "Battle of Hastings"))
            list.add(QuizModel("What was the name of the series of religious wars between Christians and Muslims in the Holy Land?", "Crusades", "Viking Raids", "Hundred Years' War", "Reconquista", "Crusades"))
            list.add(QuizModel("Who was the Mongol conqueror who founded the largest contiguous empire in history?", "Genghis Khan", "Attila the Hun", "Alexander the Great", "Julius Caesar", "Genghis Khan"))}
        if (title == "World War Riddles") {list.add(QuizModel("What was the name of the alliance system that divided Europe into two opposing sides before World War I?", "NATO", "Warsaw Pact", "Triple Alliance", "Triple Entente", "Triple Alliance"))
            list.add(QuizModel("What event sparked the beginning of World War II in Europe?", "Pearl Harbor", "Invasion of Poland", "Battle of Stalingrad", "D-Day", "Invasion of Poland"))
            list.add(QuizModel("Which country was the first to use atomic bombs in warfare during World War II?", "Germany", "Italy", "Japan", "United States", "United States"))
            list.add(QuizModel("What was the code name for the Allied invasion of Normandy during World War II?", "Operation Overlord", "Operation Barbarossa", "Operation Market Garden", "Operation Torch", "Operation Overlord"))
            list.add(QuizModel("What was the name of the treaty that ended World War I?", "Treaty of Versailles", "Treaty of Paris", "Treaty of Vienna", "Treaty of Trianon", "Treaty of Versailles"))}
        if (title == "Famous Figures Riddles") {list.add(QuizModel("Who was the first President of the United States?", "Thomas Jefferson", "George Washington", "John Adams", "Abraham Lincoln", "George Washington"))
            list.add(QuizModel("Who was the leader of the Soviet Union during World War II?", "Joseph Stalin", "Vladimir Lenin", "Mikhail Gorbachev", "Nikita Khrushchev", "Joseph Stalin"))
            list.add(QuizModel("Who was the philosopher known for his ideas on democracy and his dialogues with Socrates?", "Plato", "Socrates", "Aristotle", "Confucius", "Plato"))
            list.add(QuizModel("Who was the Italian explorer who sailed for Spain and discovered the Americas in 1492?", "Christopher Columbus", "Vasco da Gama", "Ferdinand Magellan", "Marco Polo", "Christopher Columbus"))
            list.add(QuizModel("Who was the Roman general and statesman who played a critical role in the rise of the Roman Republic?", "Julius Caesar", "Marcus Aurelius", "Augustus Caesar", "Constantine the Great", "Julius Caesar"))}
        if (title == "Historical Events Riddles") {list.add(QuizModel("What was the name of the conflict between the United States and the Soviet Union during the Cold War?", "Cuban Missile Crisis", "Vietnam War", "Korean War", "Berlin Airlift", "Cuban Missile Crisis"))
            list.add(QuizModel("What event marked the beginning of the French Revolution in 1789?", "Storming of the Bastille", "Reign of Terror", "Execution of Louis XVI", "Tennis Court Oath", "Storming of the Bastille"))
            list.add(QuizModel("What was the name of the naval battle that effectively ended Napoleon's ambitions of invading Britain?", "Battle of Trafalgar", "Battle of Waterloo", "Battle of Austerlitz", "Battle of Jutland", "Battle of Trafalgar"))
            list.add(QuizModel("What was the name of the famous speech delivered by Martin Luther King Jr. during the Civil Rights Movement?", "I Have a Dream", "We Shall Overcome", "Give Me Liberty", "Free at Last", "I Have a Dream"))
            list.add(QuizModel("What was the name of the peace treaty signed in 1783 that ended the American Revolutionary War?", "Treaty of Paris", "Treaty of Versailles", "Treaty of Utrecht", "Treaty of Ghent", "Treaty of Paris"))}
        if (title == "Number Riddles") {list.add(QuizModel("I am an odd number. Take away one letter, and I become even. What number am I?", "Seven", "Nine", "Five", "Three", "Seven"))
            list.add(QuizModel("What comes after a million, billion, and trillion?", "Quadrillion", "Quintillion", "Billion", "Zillion", "Quadrillion"))
            list.add(QuizModel("I am a three-digit number. My hundreds digit is the sum of my other two digits.", "261", "423", "396", "132", "396"))
            list.add(QuizModel("What is the only number that is spelled with the same number of letters as its value?", "Four", "Six", "Seven", "Eight", "Four"))
            list.add(QuizModel("I am an even number. I am greater than 500 and less than 700. What number am I?", "520", "630", "600", "640", "520"))}
        if (title == "Operation Riddles") {list.add(QuizModel("If you add 8 to my number, you get 27. What is my number?", "17", "18", "19", "20", "19"))
            list.add(QuizModel("I start with 30, then add 20, then subtract 10. What do I end up with?", "50", "60", "40", "70", "40"))
            list.add(QuizModel("If you multiply my number by 4, then subtract 8, you get 20. What is my number?", "6", "7", "8", "9", "7"))
            list.add(QuizModel("I am a number. If you add 5, then subtract 3, then multiply by 2, you get 14. What am I?", "3", "4", "5", "6", "4"))
            list.add(QuizModel("If you divide my number by 2, then add 7, then multiply by 3, you get 39. What is my number?", "10", "11", "12", "13", "11"))}
        if (title == "Geometry Riddles") {list.add(QuizModel("What is the sum of the angles in a triangle?", "90°", "180°", "270°", "360°", "180°"))
            list.add(QuizModel("What is the name of a three-sided polygon?", "Circle", "Square", "Triangle", "Rectangle", "Triangle"))
            list.add(QuizModel("Which shape has six equal sides and six equal angles?", "Pentagon", "Hexagon", "Octagon", "Rectangle", "Hexagon"))
            list.add(QuizModel("What is the longest side of a right-angled triangle called?", "Hypotenuse", "Adjacent", "Opposite", "Perimeter", "Hypotenuse"))
            list.add(QuizModel("What is the name of a four-sided polygon with opposite sides equal and parallel?", "Square", "Rhombus", "Trapezoid", "Parallelogram", "Parallelogram"))}
        if (title == "Reasoning Puzzles") {list.add(QuizModel("How many letters are in the word Puzzle?", "4", "5", "6", "7", "6"))
            list.add(QuizModel("If two hours ago it was as long after one o'clock in the afternoon as it was before one o'clock, what time would it be now?", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "15:00:00"))
            list.add(QuizModel("A doctor gives you three pills and tells you to take one every half-hour. How long will the pills last?", "1 hour", "1.5 hours", "2 hours", "3 hours", "1 hour"))
            list.add(QuizModel("I have keys but no locks. I have a space but no room. You can enter, but can't go outside. What am I?", "Keyboard", "Calculator", "Telephone", "Website", "Keyboard"))
            list.add(QuizModel("What can travel around the world while staying in a corner?", "Stamp", "Airplane", "Boat", "Stamp", "Stamp"))}
        if (title == "Logic Riddles") {list.add(QuizModel("What occurs once in every minute, twice in every moment, yet never in a thousand years?", "Letter 'M'", "Letter 'E'", "Letter 'A'", "Letter 'O'", "Letter 'M'"))
            list.add(QuizModel("What has keys but can't open locks?", "Keyboard", "Piano", "Guitar", "Calculator", "Piano"))
            list.add(QuizModel("The more you take, the more you leave behind. What am I?", "Footsteps", "Breath", "Time", "Money", "Footsteps"))
            list.add(QuizModel("What is as light as a feather, but even the world's strongest man couldn't hold it for long?", "Breath", "Air", "Soul", "Feather", "Breath"))
            list.add(QuizModel("You see a boat filled with people. It has not sunk, but when you look again, you don't see a single person on the boat. Why?", "All were married", "All were standing", "All went for swimming", "All were sleeping", "All were married"))}
        if (title == "Musician Riddles") {list.add(QuizModel("Who is known as the 'King of Pop'?", "Michael Jackson", "Elvis Presley", "Prince", "David Bowie", "Michael Jackson"))
            list.add(QuizModel("Which iconic guitarist is known for his rendition of 'The Star-Spangled Banner' at Woodstock?", "Jimi Hendrix", "Eric Clapton", "Jimmy Page", "Stevie Ray Vaughan", "Jimi Hendrix"))
            list.add(QuizModel("Who was the lead singer of the band Queen?", "Freddie Mercury", "David Bowie", "Elton John", "Mick Jagger", "Freddie Mercury"))
            list.add(QuizModel("Which musician is known for playing the piano and composing classical music such as 'Fr Elise' and 'Moonlight Sonata'?", "Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Franz Schubert", "Ludwig van Beethoven"))
            list.add(QuizModel("Which American singer-songwriter is known for hits like 'Blowin' in the Wind' and 'Like a Rolling Stone'?", "Bob Dylan", "Johnny Cash", "Bruce Springsteen", "Neil Young", "Bob Dylan"))}
        if (title == "Concert Riddles") {list.add(QuizModel("What is the term for the performance by a musician or group of musicians before the main act?", "Opening act", "Encore", "Intermission", "Opening act", "Opening act"))
            list.add(QuizModel("What is the area directly in front of the stage where fans stand during a concert called?", "Pit", "Balcony", "Box seats", "Pit", "Pit"))
            list.add(QuizModel("What is the term for the printed list of songs that a band or artist intends to perform during a concert?", "Setlist", "Playlist", "Tracklist", "Setlist", "Setlist"))
            list.add(QuizModel("What is the term for the event where a band or artist returns to perform more songs after the audience calls for it?", "Encore", "Interlude", "Revival", "Encore", "Encore"))
            list.add(QuizModel("What is the area where sound and audio engineers control the sound levels during a concert called?", "Sound booth", "Control room", "Mixing desk", "Sound booth", "Sound booth"))}
        if (title == "Song Lyrics Riddles") {list.add(QuizModel("What song includes the lyrics: 'Yesterday, all my troubles seemed so far away'?", "Yesterday by The Beatles", "Hotel California by Eagles", "Bohemian Rhapsody by Queen", "Imagine by John Lennon", "Yesterday by The Beatles"))
            list.add(QuizModel("Which song begins with the lyrics: 'Somebody once told me the world is gonna roll me'?", "All Star by Smash Mouth", "Wonderwall by Oasis", "Smells Like Teen Spirit by Nirvana", "Bohemian Rhapsody by Queen", "All Star by Smash Mouth"))
            list.add(QuizModel("What song features the lyrics: 'I see a little silhouetto of a man, Scaramouche, Scaramouche, will you do the Fandango?'", "Bohemian Rhapsody by Queen", "Hotel California by Eagles", "Stairway to Heaven by Led Zeppelin", "Smells Like Teen Spirit by Nirvana", "Bohemian Rhapsody by Queen"))
            list.add(QuizModel("Which song contains the lyrics: 'I'm blue da ba dee da ba daa'?", "Blue (Da Ba Dee) by Eiffel 65", "I Will Always Love You by Whitney Houston", "My Heart Will Go On by Celine Dion", "Billie Jean by Michael Jackson", "Blue (Da Ba Dee) by Eiffel 65"))
            list.add(QuizModel("What song starts with the lyrics: 'I came in like a wrecking ball'?", "Wrecking Ball by Miley Cyrus", "Shape of You by Ed Sheeran", "Uptown Funk by Mark Ronson ft. Bruno Mars", "Rolling in the Deep by Adele", "Wrecking Ball by Miley Cyrus"))}
        if (title == "Music Genres Riddles") {list.add(QuizModel("Which music genre originated in Jamaica and features heavy bass lines and offbeat rhythms?", "Reggae", "Jazz", "Blues", "Rock", "Reggae"))
            list.add(QuizModel("What music genre is characterized by its fast tempo, syncopated rhythms, and improvisation?", "Jazz", "Classical", "Country", "Hip Hop", "Jazz"))
            list.add(QuizModel("Which music genre originated in the Southern United States and often features acoustic instruments and vocal harmonies?", "Country", "Metal", "Pop", "Rap", "Country"))
            list.add(QuizModel("What genre of music emerged in the 1970s, featuring a distorted guitar sound, loud volume, and energetic performances?", "Punk", "Disco", "Funk", "R&B", "Punk"))
            list.add(QuizModel("Which music genre combines elements of hip hop and electronic dance music (EDM), characterized by its fast-paced beats and futuristic sound?", "Techno", "Dubstep", "Trance", "House", "Dubstep"))}
        if (title == "Instrument Riddles") {list.add(QuizModel("What instrument has 88 keys?", "Violin", "Piano", "Guitar", "Drums", "Piano"))
            list.add(QuizModel("Which instrument is known as the king of instruments?", "Accordion", "Trumpet", "Clarinet", "Pipe organ", "Pipe organ"))
            list.add(QuizModel("What instrument produces sound by blowing air through a series of pipes?", "Flute", "Trombone", "Clarinet", "Organ", "Flute"))
            list.add(QuizModel("Which instrument has strings and is played with a bow?", "Flute", "Clarinet", "Violin", "Saxophone", "Violin"))
            list.add(QuizModel("What instrument is often called a woodwind, even though it's typically made of metal?", "Flute", "Trumpet", "Saxophone", "Clarinet", "Saxophone"))}
        if (title == "Plant Riddles") {list.add(QuizModel("I have a green stem and colorful flowers that attract bees. What am I?", "Sunflower", "Rose", "Cactus", "Grass", "Sunflower"))
            list.add(QuizModel("I climb up walls and fences, with leaves that can change color in the fall. What am I?", "Tree", "Vine", "Bush", "Flower", "Vine"))
            list.add(QuizModel("I have a big, round head and long, green leaves. I grow underground and can be eaten raw or cooked. What am I?", "Cabbage", "Onion", "Carrot", "Potato", "Potato"))
            list.add(QuizModel("I am a giant tree that can live for thousands of years. I have red, woody bark and grow in sequoia groves. What am I?", "Oak tree", "Redwood tree", "Maple tree", "Palm tree", "Redwood tree"))
            list.add(QuizModel("I am a small, green plant that thrives in moist places. I grow flat and wide, often found on forest floors or near water. What am I?", "Moss", "Fern", "Grass", "Clover", "Moss"))}
        if (title == "Weather Riddles") {list.add(QuizModel("I fall from the sky in white flakes and blanket the ground in a cold hug. What am I?", "Rain", "Snow", "Hail", "Wind", "Snow"))
            list.add(QuizModel("I bring strong winds and heavy rain, sometimes even causing floods. What am I?", "Hurricane", "Tornado", "Thunderstorm", "Blizzard", "Hurricane"))
            list.add(QuizModel("I bring hot, dry weather with bright sunshine. What am I?", "Summer", "Spring", "Fall", "Winter", "Summer"))
            list.add(QuizModel("I am a thick fog that reduces visibility and makes travel difficult. What am I?", "Cloud", "Mist", "Fog", "Rain", "Fog"))
            list.add(QuizModel("I am a bright circle in the sky that appears during the day. It provides warmth and light. What am I?", "Sun", "Moon", "Star", "Cloud", "Sun"))}
        if (title == "Geography Riddles") {list.add(QuizModel("I am a large body of saltwater that covers most of the Earth's surface. What am I?", "Ocean", "Sea", "Lake", "River", "Ocean"))
            list.add(QuizModel("I am a high, rocky landmass that is much larger than a hill. What am I?", "Mountain", "Valley", "Plain", "Plateau", "Mountain"))
            list.add(QuizModel("I am a wide area of flat land with few trees or other vegetation. What am I?", "Desert", "Forest", "Jungle", "Grassland", "Desert"))
            list.add(QuizModel("I am a large area of land that is surrounded by water on all sides. What am I?", "Island", "Continent", "Peninsula", "Isthmus", "Island"))
            list.add(QuizModel("I am a line drawn on a map that divides the Earth into two halves: northern and southern. What am I?", "Equator", "Prime Meridian", "Tropic of Cancer", "Arctic Circle", "Equator"))}
        if (title == "Animal Riddles") {list.add(QuizModel("I'm born pink and turn black and white. I eat bamboo and am known for my good night. What am I?", "Panda", "Bear", "Koala", "Elephant", "Panda"))
            list.add(QuizModel("I have a long neck and a long tongue. I live in Africa and eat leaves all day long. What am I?", "Giraffe", "Zebra", "Lion", "Elephant", "Giraffe"))
            list.add(QuizModel("I have a shell and carry my home on my back. I'm slow and steady, and leave a slimy track. What am I?", "Snail", "Turtle", "Slug", "Worm", "Snail"))
            list.add(QuizModel("I have sharp teeth and a fierce roar. I'm the king of the jungle, and what I want I explore. What am I?", "Lion", "Tiger", "Cheetah", "Leopard", "Lion"))
            list.add(QuizModel("I swim in the ocean and can be quite grand. I spout water high and have flippers for a hand. What am I?", "Whale", "Dolphin", "Shark", "Seal", "Whale"))}
        if (title == "Landform Riddles") {list.add(QuizModel("I am a very high mountain, often capped with snow. I can be dormant or active, and sometimes cause a fiery flow. What am I?", "Volcano", "Mountain", "Hill", "Plateau", "Volcano"))
            list.add(QuizModel("I am a wide, flat area of land, often covered in grass and used for farming. What am I?", "Plain", "Valley", "Plateau", "Desert", "Plain"))
            list.add(QuizModel("I am a deep valley with steep sides, often carved by a river over time. What am I?", "Canyon", "Gorge", "Trench", "Ravine", "Canyon"))
            list.add(QuizModel("I am a large, natural body of water surrounded by land. I can be freshwater or saltwater. What am I?", "Lake", "Sea", "Ocean", "River", "Lake"))
            list.add(QuizModel("I am a long, narrow strip of land that juts out into a body of water. What am I?", "Peninsula", "Isthmus", "Cape", "Archipelago", "Peninsula"))}
        if (title == "Biology Riddles") {list.add(QuizModel("What is the powerhouse of the cell?", "Nucleus", "Ribosome", "Mitochondria", "Chloroplast", "Mitochondria"))
            list.add(QuizModel("Which organ in the human body produces insulin?", "Liver", "Pancreas", "Kidney", "Heart", "Pancreas"))
            list.add(QuizModel("What is the largest organ in the human body?", "Liver", "Brain", "Skin", "Heart", "Skin"))
            list.add(QuizModel("Which gas is produced by plants during photosynthesis?", "Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen", "Oxygen"))
            list.add(QuizModel("What is the pigment that gives human skin its color?", "Hemoglobin", "Melanin", "Chlorophyll", "Keratin", "Melanin"))}
        if (title == "Physics Riddles") {list.add(QuizModel("What is the SI unit of force?", "Watt", "Volt", "Newton", "Joule", "Newton"))
            list.add(QuizModel("What is the acceleration due to gravity on Earth?", "9.8 m/s²", "6.7 m/s²", "12.3 m/s²", "5.5 m/s²", "9.8 m/s²"))
            list.add(QuizModel("What is the conservation law that states that energy cannot be created or destroyed?", "Law of Inertia", "Law of Motion", "Law of Gravity", "Law of Conservation of Energy", "Law of Conservation of Energy"))
            list.add(QuizModel("What is the bending of light as it passes through different mediums called?", "Reflection", "Diffraction", "Refraction", "Dispersion", "Refraction"))
            list.add(QuizModel("Which type of electromagnetic radiation has the shortest wavelength?", "Infrared", "Ultraviolet", "Gamma rays", "Radio waves", "Gamma rays"))}
        if (title == "Chemistry Riddles") {list.add(QuizModel("What is the chemical symbol for water?", "H2O", "CO2", "NaCl", "O2", "H2O"))
            list.add(QuizModel("What is the atomic number of oxygen?", "8", "6", "10", "12", "8"))
            list.add(QuizModel("Which gas is known as the laughing gas?", "Nitrous oxide", "Carbon dioxide", "Oxygen", "Nitrogen", "Nitrous oxide"))
            list.add(QuizModel("What is the lightest element on the periodic table?", "Hydrogen", "Helium", "Lithium", "Neon", "Hydrogen"))
            list.add(QuizModel("What is the process of a solid directly turning into a gas without passing through the liquid phase?", "Sublimation", "Evaporation", "Condensation", "Melting", "Sublimation"))}
        if (title == "Environmental Riddles") {list.add(QuizModel("What gas do plants take in during photosynthesis?", "Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen", "Carbon dioxide"))
            list.add(QuizModel("What is the primary cause of global warming?", "Deforestation", "Greenhouse gases", "Ozone depletion", "Ocean acidification", "Greenhouse gases"))
            list.add(QuizModel("What is the layer of the atmosphere that protects Earth from harmful UV radiation?", "Stratosphere", "Mesosphere", "Troposphere", "Exosphere", "Stratosphere"))
            list.add(QuizModel("What is the process of breaking down rocks, soil, and minerals through exposure to water, air, and biological organisms?", "Erosion", "Weathering", "Deposition", "Sublimation", "Weathering"))
            list.add(QuizModel("Which gas is responsible for the depletion of the ozone layer?", "Carbon dioxide", "Methane", "CFCs", "Nitrous oxide", "CFCs"))}
        if (title == "Space Riddles") {list.add(QuizModel("What is the closest planet to the Sun?", "Venus", "Earth", "Mercury", "Mars", "Mercury"))
            list.add(QuizModel("What is the name of the largest moon of Jupiter?", "Europa", "Titan", "Ganymede", "Callisto", "Ganymede"))
            list.add(QuizModel("Which planet is known as the Red Planet?", "Mars", "Jupiter", "Saturn", "Venus", "Mars"))
            list.add(QuizModel("What is the name of the closest galaxy to the Milky Way?", "Andromeda", "Orion", "Triangulum", "Pinwheel", "Andromeda"))
            list.add(QuizModel("What is the process by which a star expels its outer layers, resulting in a beautiful glowing cloud known as a planetary nebula?", "Supernova", "Nova", "Nebula", "Pulsar", "Nebula"))}
        if (title == "Space Exploration Riddles") {list.add(QuizModel("What was the name of the first artificial satellite launched into space by the Soviet Union?", "Explorer 1", "Hubble Space Telescope", "Voyager 1", "Sputnik 1", "Sputnik 1"))
            list.add(QuizModel("Which space agency launched the Apollo missions to the Moon?", "ESA", "CNSA", "Roscosmos", "NASA", "NASA"))
            list.add(QuizModel("What was the name of the first human to journey into outer space?", "Yuri Gagarin", "Neil Armstrong", "Buzz Aldrin", "Alan Shepard", "Yuri Gagarin"))
            list.add(QuizModel("Which spacecraft was the first to land humans on the Moon?", "Soyuz", "Apollo", "Space Shuttle", "Vostok", "Apollo"))
            list.add(QuizModel("What is the name of the longest continuously inhabited space station in orbit around Earth?", "Mir", "Tiangong", "Skylab", "International Space Station", "International Space Station"))}
        if (title == "Planetary Riddles") {list.add(QuizModel("Which planet in our solar system is known as the Red Planet?", "Jupiter", "Venus", "Mars", "Saturn", "Mars"))
            list.add(QuizModel("What is the largest planet in our solar system by mass?", "Jupiter", "Earth", "Mars", "Saturn", "Jupiter"))
            list.add(QuizModel("Which planet is known as the Morning Star or the Evening Star depending on its appearance in the sky?", "Venus", "Mercury", "Mars", "Jupiter", "Venus"))
            list.add(QuizModel("What is the name of the dwarf planet that was formerly considered the ninth planet in our solar system?", "Pluto", "Eris", "Ceres", "Haumea", "Pluto"))
            list.add(QuizModel("Which planet has the most moons in our solar system?", "Saturn", "Jupiter", "Uranus", "Neptune", "Jupiter"))}
        if (title == "Astronomy Riddles") {list.add(QuizModel("What is the name of the closest star to the Earth?", "Sirius", "Proxima Centauri", "Alpha Centauri", "Betelgeuse", "Proxima Centauri"))
            list.add(QuizModel("What is the term for a group of stars forming a recognizable pattern in the night sky?", "Constellation", "Nebula", "Galaxy", "Asterism", "Constellation"))
            list.add(QuizModel("What is the name of the galaxy that contains our solar system?", "Andromeda", "Milky Way", "Orion", "Triangulum", "Milky Way"))
            list.add(QuizModel("Which celestial body is known as the Red Planet?", "Jupiter", "Venus", "Mars", "Saturn", "Mars"))
            list.add(QuizModel("What is the name of the phenomenon where the moon partially or completely blocks the sun?", "Lunar Eclipse", "Solar Eclipse", "Comet", "Asteroid", "Solar Eclipse"))}
        if (title == "Galaxy Riddles") {list.add(QuizModel("What is the name of the galaxy closest to the Milky Way?", "Andromeda", "Triangulum", "Whirlpool", "Sombrero", "Andromeda"))
            list.add(QuizModel("Which type of galaxy has a shape resembling a flattened disk with spiral arms?", "Elliptical", "Irregular", "Lenticular", "Spiral", "Spiral"))
            list.add(QuizModel("What is the name of the supermassive black hole located at the center of the Milky Way galaxy?", "VY Canis Majoris", "Sagittarius A*", "Betelgeuse", "Cygnus X-1", "Sagittarius A*"))
            list.add(QuizModel("Which galaxy is the largest in size in the observable universe?", "IC 1101", "Messier 87", "UDFy-38135539", "GN-z11", "IC 1101"))
            list.add(QuizModel("What is the name of the galaxy group that includes the Milky Way and the Andromeda Galaxy?", "Local Group", "Virgo Cluster", "Hydra-Centaurus", "Coma Cluster", "Local Group"))}
        if (title == "Alien Riddles") {list.add(QuizModel("What is the term for the hypothetical barrier separating Earth from the rest of the cosmos?", "Event Horizon", "Oort Cloud", "Kuiper Belt", "Fermi Paradox", "Fermi Paradox"))
            list.add(QuizModel("Which scientific study focuses on the search for extraterrestrial intelligence (ETI)?", "Astrobiology", "Exobiology", "SETI", "Xenobiology", "SETI"))
            list.add(QuizModel("What is the name of the famous equation used to estimate the number of extraterrestrial civilizations in the Milky Way galaxy?", "Drake Equation", "Fermi Paradox", "Hubble's Law", "Kepler's Law", "Drake Equation"))
            list.add(QuizModel("Which moon of Jupiter is considered one of the most likely places to find extraterrestrial life in our solar system?", "Europa", "Titan", "Io", "Enceladus", "Europa"))
            list.add(QuizModel("What is the name of the nearest known potentially habitable exoplanet to Earth?", "Kepler-186f", "Proxima Centauri b", "TRAPPIST-1d", "Gliese 581g", "Proxima Centauri b"))}
        if (title == "Football Riddles") {list.add(QuizModel("What is the name of the tournament where national teams compete for the FIFA World Cup?", "UEFA Euro", "Copa America", "UEFA Champions League", "FIFA World Cup", "FIFA World Cup"))
            list.add(QuizModel("Who is the all-time leading goal scorer in the history of FIFA World Cup tournaments?", "Lionel Messi", "Cristiano Ronaldo", "Pel", "Miroslav Klose", "Miroslav Klose"))
            list.add(QuizModel("Which country has won the most FIFA World Cup titles?", "Germany", "Brazil", "Italy", "Argentina", "Brazil"))
            list.add(QuizModel("What is the term for a situation where a player scores three goals in a single football match?", "Triple Double", "Triple Crown", "Hat-trick", "Touchdown", "Hat-trick"))
            list.add(QuizModel("Which football club has won the most UEFA Champions League titles?", "Barcelona", "Manchester United", "Real Madrid", "Bayern Munich", "Real Madrid"))}
        if (title == "Basketball Riddles") {list.add(QuizModel("What is the term for scoring three points on a single shot in basketball?", "Home Run", "Touchdown", "Slam Dunk", "Field Goal", "Slam Dunk"))
            list.add(QuizModel("Who is widely regarded as the greatest basketball player of all time?", "Michael Jordan", "LeBron James", "Kobe Bryant", "Magic Johnson", "Michael Jordan"))
            list.add(QuizModel("What basketball team has won the most NBA championships?", "Los Angeles Lakers", "Boston Celtics", "Chicago Bulls", "Golden State Warriors", "Boston Celtics"))
            list.add(QuizModel("What is the term for an offensive play in basketball where a player jumps and shoots the ball near the basket?", "Layup", "Slam Dunk", "Free Throw", "Three-pointer", "Layup"))
            list.add(QuizModel("What is the term for a violation in basketball where a player takes too many steps without dribbling the ball?", "Traveling", "Double Dribble", "Foul", "Blocking", "Traveling"))}
        if (title == "Cricket Riddles") {list.add(QuizModel("What is the highest individual score ever made by a batsman in a Test cricket match?", "300", "375", "400", "501", "400"))
            list.add(QuizModel("What is the term for a situation in cricket where a bowler takes three wickets on three consecutive deliveries?", "Hat-trick", "Full toss", "Yorker", "Googly", "Hat-trick"))
            list.add(QuizModel("Which country won the inaugural ICC Cricket World Cup held in 1975?", "West Indies", "Australia", "England", "India", "West Indies"))
            list.add(QuizModel("What is the name of the cricket tournament contested between England and Australia?", "Ashes", "Cup of Nations", "Super Series", "Border-Gavaskar Trophy", "Ashes"))
            list.add(QuizModel("What is the term for the situation in cricket where a bowler bowls a ball that reaches the batsman without bouncing?", "Full Toss", "Yorker", "No Ball", "Bouncer", "Full Toss"))}
        if (title == "Tennis Riddles") {list.add(QuizModel("Who holds the record for the most Grand Slam singles titles in tennis history?", "Rafael Nadal", "Novak Djokovic", "Roger Federer", "Pete Sampras", "Roger Federer"))
            list.add(QuizModel("What is the term for a situation in tennis where a player wins a set without losing a single point?", "Love Set", "Perfect Set", "Zero Set", "Golden Set", "Love Set"))
            list.add(QuizModel("Which tennis tournament is played on a grass court and is the oldest major championship in tennis?", "Wimbledon", "French Open", "US Open", "Australian Open", "Wimbledon"))
            list.add(QuizModel("What is the term for a serve in tennis that is not touched by the receiver and results in a point for the server?", "Ace", "Fault", "Let", "Double Fault", "Ace"))
            list.add(QuizModel("Who was the first male player to win all four Grand Slam singles titles in a single calendar year?", "Rod Laver", "Bjorn Borg", "Jimmy Connors", "Andre Agassi", "Rod Laver"))}
        if (title == "Golf Riddles") {list.add(QuizModel("What is the term for a hole in one shot in golf, where the ball is hit directly into the hole from the tee?", "Eagle", "Albatross", "Birdie", "Hole-in-one", "Hole-in-one"))
            list.add(QuizModel("Who holds the record for the most career major championships won in men's golf?", "Jack Nicklaus", "Tiger Woods", "Arnold Palmer", "Gary Player", "Jack Nicklaus"))
            list.add(QuizModel("What is the name of the prestigious golf tournament held annually at Augusta National Golf Club?", "US Open", "The Open Championship", "PGA Championship", "The Masters Tournament", "The Masters Tournament"))
            list.add(QuizModel("What is the term for the total number of strokes a player takes to complete a hole or a round of golf?", "Par", "Stroke", "Birdie", "Bogey", "Stroke"))
            list.add(QuizModel("Which golfer is known as the Golden Bear?", "Jack Nicklaus", "Tiger Woods", "Arnold Palmer", "Gary Player", "Jack Nicklaus"))}



        else {
            list.add(
                QuizModel(
                    "What is the capital of France?",
                    "Paris",
                    "London",
                    "Berlin",
                    "Rome",
                    "Paris"
                )
            )
            list.add(
                QuizModel(
                    "Who wrote the Harry Potter series?",
                    "J.K. Rowling",
                    "Stephen King",
                    "George R.R. Martin",
                    "J.R.R. Tolkien",
                    "J.K. Rowling"
                )
            )
            list.add(
                QuizModel(
                    "What is the chemical symbol for water?",
                    "H2O",
                    "CO2",
                    "NaCl",
                    "O2",
                    "H2O"
                )
            )
            list.add(
                QuizModel(
                    "Which planet is known as the Red Planet?",
                    "Mars",
                    "Venus",
                    "Jupiter",
                    "Saturn",
                    "Mars"
                )
            )
            list.add(
                QuizModel(
                    "Who painted the Mona Lisa?",
                    "Leonardo da Vinci",
                    "Pablo Picasso",
                    "Vincent van Gogh",
                    "Michelangelo",
                    "Leonardo da Vinci"
                )
            )
        }
        allQuestion = 5
        val listSize = allQuestion.toString()
        binding.totalQ.text = "/$listSize"

        val positionNo = if (position != allQuestion) (position + 1).toString() else position.toString()
        quizModel=list[position]

        answer = quizModel.correctAns

        binding.questionCon.text = quizModel.question
        binding.option1Con.text = quizModel.op1
        binding.option2Con.text = quizModel.op2
        binding.option3Con.text = quizModel.op3
        binding.option4Con.text = quizModel.op4

        optionCheckUp()
    }
    fun getRandomCorrectAnswer(vararg options: String): String {
        val randomIndex = Random.nextInt(options.size)
        return options[randomIndex]
    }

    private fun optionCheckUp() {
        binding.option1Con.setOnClickListener {

            if (quizModel.op1 == quizModel.correctAns) {
                right++
                val correctCount = sharedPreferences1.getInt(correct, 0)
                val newCorrectCount = correctCount + 1
                saveCorrect(newCorrectCount)

                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)

                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        fstream.write(newCorrectCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

                binding.option1Con.setBackgroundResource(R.drawable.rightbg)

            } else {
                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                showRightAns()
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        val correctCount = sharedPreferences1.getInt(correct, 0)
                        fstream.write(correctCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

                binding.option1Con.setBackgroundResource(R.drawable.wrongbg)
            }
            disableOption()
        }

        binding.option2Con.setOnClickListener {
            if (quizModel.op2 == quizModel.correctAns) {
                right++
                val correctCount = sharedPreferences1.getInt(correct, 0)
                val newCorrectCount = correctCount + 1
                saveCorrect(newCorrectCount)

                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        fstream.write(newCorrectCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                binding.option2Con.setBackgroundResource(R.drawable.rightbg)
            } else {
                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                showRightAns()
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        val correctCount = sharedPreferences1.getInt(correct, 0)
                        fstream.write(correctCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                binding.option2Con.setBackgroundResource(R.drawable.wrongbg)
            }
            disableOption()
        }

        binding.option3Con.setOnClickListener {
            if (quizModel.op3 == quizModel.correctAns) {
                right++
                val correctCount = sharedPreferences1.getInt(correct, 0)
                val newCorrectCount = correctCount + 1
                saveCorrect(newCorrectCount)
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        val correctCount = sharedPreferences1.getInt(correct, 0)
                        fstream.write(correctCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        fstream.write(newCorrectCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                binding.option3Con.setBackgroundResource(R.drawable.rightbg)
            } else {
                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                showRightAns()
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        val correctCount = sharedPreferences1.getInt(correct, 0)
                        fstream.write(correctCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

                binding.option3Con.setBackgroundResource(R.drawable.wrongbg)
            }
            disableOption()
        }

        binding.option4Con.setOnClickListener {
            if (quizModel.op4 == quizModel.correctAns) {
                right++
                val correctCount = sharedPreferences1.getInt(correct, 0)
                val newCorrectCount = correctCount + 1
                saveCorrect(newCorrectCount)

                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        fstream.write(newCorrectCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                binding.option4Con.setBackgroundResource(R.drawable.rightbg)
            } else {
                val totalCount = sharedPreferences2.getInt(correct, 0)
                val newTotalCount = totalCount + 1
                saveTotal(newTotalCount)
                showRightAns()
                try {

                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[0]
                        ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            mPermission[1]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(requireActivity(), mPermission, 23)
                    }
                    else{
                        myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(newTotalCount.toString().toByteArray())
                        fstream.write("\n".toByteArray())
                        val correctCount = sharedPreferences1.getInt(correct, 0)
                        fstream.write(correctCount.toString().toByteArray())
                        fstream.close()
                    }




                } catch (e: FileNotFoundException) {
                    Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                } catch (e: IOException) {
                    Toast.makeText(requireContext(),"Hey2",Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                binding.option4Con.setBackgroundResource(R.drawable.wrongbg)
            }
            disableOption()
        }

    }


    private fun showRightAns() {
        when (quizModel.correctAns) {
            quizModel.op1 -> binding.option1Con.setBackgroundResource(R.drawable.rightbg)
            quizModel.op2 -> binding.option2Con.setBackgroundResource(R.drawable.rightbg)
            quizModel.op3 -> binding.option3Con.setBackgroundResource(R.drawable.rightbg)
            quizModel.op4 -> binding.option4Con.setBackgroundResource(R.drawable.rightbg)
        }
    }



    private fun clearOption() {
        binding.option1Con.setBackgroundResource(R.drawable.sub_item_bg)
        binding.option2Con.setBackgroundResource(R.drawable.sub_item_bg)
        binding.option3Con.setBackgroundResource(R.drawable.sub_item_bg)
        binding.option4Con.setBackgroundResource(R.drawable.sub_item_bg)
        binding.nextBtn.setBackgroundResource(R.drawable.disable_btn)
    }

    private fun enableOption() {
        binding.option1Con.isEnabled = true
        binding.option2Con.isEnabled = true
        binding.option3Con.isEnabled = true
        binding.option4Con.isEnabled = true
        binding.nextBtn.isEnabled = false
    }
    private fun disableOption() {
        binding.option1Con.isEnabled = false
        binding.option2Con.isEnabled = false
        binding.option3Con.isEnabled = false
        binding.option4Con.isEnabled = false
        binding.nextBtn.isEnabled = true
        binding.nextBtn.setBackgroundResource(R.drawable.enable_btn)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun saveCorrect(counter: Int) {
        val editor = sharedPreferences1.edit()
        editor.putInt(correct, counter)
        editor.apply()
    }
    private fun saveTotal(counter: Int) {
        val editor = sharedPreferences2.edit()
        editor.putInt(total, counter)
        editor.apply()
    }
}