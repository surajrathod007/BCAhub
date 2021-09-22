package com.surajrathod.bcahub

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class Notes_info : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var imgNotes: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtSem: TextView
    lateinit var txtSub: TextView
    lateinit var txtDesc: TextView
    lateinit var btnDown: Button
    lateinit var prevWeb: WebView
    lateinit var progress: ProgressBar


    var myDownloadId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_info)
        val intent = getIntent()
        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")
        val sem = intent.getStringExtra("sem")
        val sub = intent.getStringExtra("sub")
        val desc = intent.getStringExtra("desc")
        val link = intent.getStringExtra("link")
        val ulink = intent.getStringExtra("ulink")

        txtTitle = findViewById(R.id.txtTitle)
        txtSem = findViewById(R.id.txtSem)
        txtSub = findViewById(R.id.txtSubject)
        imgNotes = findViewById(R.id.imgNotes)
        txtDesc = findViewById(R.id.txtDesc)
        btnDown = findViewById(R.id.btnDownload)
        progress = findViewById(R.id.SyllyProgress)




        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Notes"


        progress.visibility = View.VISIBLE

        txtTitle.text = title
        txtSem.text = sem
        txtSub.text = sub
        Picasso.get().load(img).error(R.drawable.ic_notes_icon)
            .into(imgNotes);
        txtDesc.text = desc

        prevWeb = findViewById(R.id.webViewNotes)

        prevWeb.settings.javaScriptEnabled = true



        prevWeb.loadUrl(ulink!!)

        prevWeb.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }
        }

        btnDown.setOnClickListener {

            var request = DownloadManager.Request(Uri.parse(link))
                .setTitle(title)
                .setDescription("Downloading " + title)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title+".pdf")
                .setAllowedOverMetered(true)


            // .allowScanningByMediaScanner()


            var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            myDownloadId = dm.enqueue(request)

            Toast.makeText(applicationContext, "Download Started", Toast.LENGTH_SHORT).show()
        }

        var br = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {
                var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

                if (id == myDownloadId) {
                    Toast.makeText(applicationContext, "Note is downloaded", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}