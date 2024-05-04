package com.example.final_project_term_six



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter( fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if (position == 0)
            fragment = Fragment1()
        else if (position == 1)
            fragment = Fragment3()
        else if (position == 2)
            fragment = Fragment4()

        return fragment!!
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0)
            title = "About Us"
        else if (position == 1)
            title = "Reminders"
        else if (position == 2)
            title = "Results"

        return title
    }
}