package com.surajrathod.bcahub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters


    lateinit var frameLayout: FrameLayout
    lateinit var notesCardView: CardView
    lateinit var salyCardView : CardView
    lateinit var resultCardView : CardView
    lateinit var aboutCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        notesCardView = view.findViewById(R.id.notesCardView)
        salyCardView = view.findViewById(R.id.sallaybusCardView)
        resultCardView = view.findViewById(R.id.resultCardView)
        aboutCardView = view.findViewById(R.id.aboutMeCardView)

        //initialize variables




        notesCardView.setOnClickListener {

            val fragment = HomeFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("home")
            fragmentTransaction.commit()

        }

        salyCardView.setOnClickListener {
            val fragment = SaylabussFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("home")
            fragmentTransaction.commit()
        }

        resultCardView.setOnClickListener {
            val fragment = ResultFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("home")
            fragmentTransaction.commit()
        }

        aboutCardView.setOnClickListener {
            val fragment = ProfileFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("home")
            fragmentTransaction.commit()

        }



        return view
    }

    //setup toolbar


}