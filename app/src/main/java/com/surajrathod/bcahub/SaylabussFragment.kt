package com.surajrathod.bcahub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import android.content.Intent
import android.net.Uri


class SaylabussFragment : Fragment() {


    lateinit var webSy: WebView
    lateinit var progress : ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saylabuss, container, false)

        progress = view.findViewById(R.id.SyllyProgress)

        progress.visibility = View.VISIBLE
        (activity as MainActivity).supportActionBar?.title = "Syllabus"




        webSy = view.findViewById(R.id.webSaylabus)

        webSy.loadUrl("https://www1.gujaratuniversity.ac.in/custom/student/syllabus")

        webSy.settings.loadWithOverviewMode = true
        webSy.settings.javaScriptEnabled = true

        webSy.webViewClient = object : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
                webSy.visibility = View.VISIBLE
            }



            override  fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)

                return true
            }
        }



        return view
    }


}