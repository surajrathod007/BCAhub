package com.surajrathod.bcahub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast


class ResultFragment : Fragment() {

    lateinit var webViewResult: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        (activity as MainActivity).supportActionBar?.title = "Result"

        progressBar = view.findViewById(R.id.progressResult)
        progressBar.visibility = View.GONE

        progressBar.visibility = View.VISIBLE
        webViewResult = view.findViewById(R.id.resultWebView)

        webViewResult.loadUrl("http://result.gujaratuniversity.ac.in/")



        webViewResult.settings.loadWithOverviewMode = true
        webViewResult.settings.javaScriptEnabled = true

        webViewResult.webViewClient = object : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
                webViewResult.visibility = View.VISIBLE
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