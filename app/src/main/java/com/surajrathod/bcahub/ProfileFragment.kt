package com.surajrathod.bcahub

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import android.widget.Toast

import android.content.pm.PackageManager

import android.content.Intent
import android.net.Uri
import android.content.ActivityNotFoundException


class ProfileFragment : Fragment() {


    val number: String = "+91 9512865416"
    val uri: String = "https://api.whatsapp.com/send?phone=" + number

    lateinit var cardWhatsapp: CardView
    lateinit var cardEmail: CardView
    lateinit var cardgroup : CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        (activity as MainActivity).supportActionBar?.title = "About Us"

        cardWhatsapp = view.findViewById(R.id.cardViewWhatsapp)
        cardEmail = view.findViewById(R.id.cardViewEmail)
        cardgroup = view.findViewById(R.id.cardViewGroup)


        cardEmail.setOnClickListener {
            try {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "surajsinhrathod75@gmail.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "My Notes Title")
                intent.putExtra(Intent.EXTRA_TEXT, "About My Notes")
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(getActivity(), "Text!", Toast.LENGTH_SHORT).show();
            }
        }


        cardWhatsapp.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(uri)
            startActivity(i)

        }

        cardgroup.setOnClickListener {
            val intentWhatsapp = Intent(Intent.ACTION_VIEW)
            val url = "https://chat.whatsapp.com/LCwTAX2fPUmE69WchdEhje"
            intentWhatsapp.data = Uri.parse(url)
            startActivity(intentWhatsapp)
        }

        return view

    }

}