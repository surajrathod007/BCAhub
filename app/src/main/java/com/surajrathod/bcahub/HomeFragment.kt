package com.surajrathod.bcahub

import android.app.Fragment
import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class HomeFragment : Fragment() {

    lateinit var dbref: DatabaseReference
    lateinit var recyclerDashboard: RecyclerView //creating variable of recycler view

    lateinit var layoutManager: RecyclerView.LayoutManager // creating variable for layout manager


    lateinit var recyclerAdapter: RecyclerAdapter //declaring adapter

    lateinit var progressLayout : RelativeLayout
    lateinit var progressBar : ProgressBar

     val notesList = arrayListOf<Notes>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        ) //this is false because we dont need to open only one fragment, we need multiple


        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)


        progressLayout.visibility = View.VISIBLE


        //initialise or connect  the recycler view

        recyclerDashboard =
            view.findViewById(R.id.recyclerView)  // we are using view. becuase , in fragments we have to do this , otherwise it will be error

        layoutManager =
            LinearLayoutManager(activity) //we are passing activity because its fragment , but in activiyu.kt we will pass "this" keyword in parameters


        //initialize adapter
        recyclerAdapter = RecyclerAdapter( activity as Context,
            notesList
        ) //the "as" keyword is used for typecastig

        //if we type only activity then it will throw an error, so we typcasted it to Context. because its fragment activity


        //now atach adapter & layout manager


        recyclerDashboard.layoutManager = layoutManager




        getNotesDate()


        return view


    }



    private fun getNotesDate() {
        dbref = FirebaseDatabase.getInstance().getReference("Notes")

        dbref.addValueEventListener(object : ValueEventListener{


            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   for(notesSnapShot in snapshot.children){

                       val note = notesSnapShot.getValue(Notes::class.java)
                       notesList.add(note!!)
                   }

                   progressLayout.visibility = View.GONE
                   recyclerDashboard.adapter = recyclerAdapter

               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }




}