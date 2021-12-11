package com.surajrathod.bcahub

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters


    lateinit var frameLayout: FrameLayout
    lateinit var notesCardView: CardView
    lateinit var salyCardView : CardView
    lateinit var resultCardView : CardView
    lateinit var aboutCardView: CardView
    lateinit var buildText : TextView
    lateinit var database : DatabaseReference
    lateinit var updateTextView: TextView
    //var dataBaseCode = ""
    var versionName = ""
    var versionCode = 0

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
        buildText = view.findViewById(R.id.buildversion)
        updateTextView = view.findViewById(R.id.updateTitle)


        //database things

        /*database = FirebaseDatabase.getInstance().getReference("Update")

        database.get().addOnSuccessListener {



            val data = it.child("code").value

            var databasecode1 = data.toString()

            if(Integer.parseInt(databasecode1)>versionCode)
            {
                //create a dilogue
                // showUpdateDialog()
                updateTextView.text = "Update Is Available"


            }




        }.addOnFailureListener {

            Toast.makeText(activity as Context,"Data not read",Toast.LENGTH_SHORT).show()
        }



*/



        //initialize variables
        versionCode = GetAppCode(activity as Context)
        versionName = GetAppVerssion(activity as Context)
        buildText.text = "Build version is " + versionName











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

    fun GetAppVerssion(context : Context): String {
        var version = ""
        try{

            val pInfo = context.packageManager.getPackageInfo(context.packageName,0)
            version = pInfo.versionName
        }catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        return version
    }

    fun GetAppCode(context: Context): Int{

        var code = 0

        try{

            val pInfo = context.packageManager.getPackageInfo(context.packageName,0)
            code = pInfo.versionCode
        }catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        return code
    }


    //setup toolbar


}


