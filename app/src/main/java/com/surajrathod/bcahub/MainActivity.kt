package com.surajrathod.bcahub

//import android.app.Activity
//import android.content.Context
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
//import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class MainActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    //var versionCode = 0
    lateinit var database : DatabaseReference

    //lateinit var remoteConfig : FirebaseRemoteConfig

    //var updateLink : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(this,"Welcome to BCA Hub",Toast.LENGTH_LONG).show()
        //initialize variables

       /* database = FirebaseDatabase.getInstance().getReference("Update")

        database.get().addOnSuccessListener {



            val data = it.child("link").value

            updateLink = data.toString()



        }.addOnFailureListener {

            Toast.makeText(this,"Data not read",Toast.LENGTH_SHORT).show()
        }*/

        //remote configue

       /* remoteConfig = FirebaseRemoteConfig.getInstance()
        var configSettings : FirebaseRemoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(5)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)


        remoteConfig.fetchAndActivate().addOnCompleteListener(this){

            if(it.isSuccessful){

                val new_version_code = remoteConfig.getString("new_version_code")

                if(Integer.parseInt(new_version_code)>versionCode)
                {
                    //create a dilogue
                   // showUpdateDialog()
                    showUpdateDialog()

                }
            }
        }*/




        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)

        //versionCode = GetAppCode(this)

        //Toast.makeText(this,"The code is $versionCode",Toast.LENGTH_SHORT).show()



        var previousMenuItem : MenuItem? = null

        setUpToolBar() //add the toolbar ;)

        //open home fragment here

        /* ---------------Below Is The Code, But we are using function here ;)______________________
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, homeFragment()).addToBackStack("Home")
            .commit() //apply the transcation
        supportActionBar?.title ="Home"


         */

        openHome()

        //now enable hamburger



        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        //now add the click listenr to ham burger icon
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState() //change ham burgor to arrow when drawer is open , visa versa

        //set click listenr is navigation view

        navigationView.setNavigationItemSelectedListener {

            //code for check and uncheck drawer items

            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }


            it.isCheckable = true //make item checkable
            it.isChecked = true //chech the clicked item
            previousMenuItem = it // assin this item to previous item

            when (it.itemId) { //where it means currently clicked item
                R.id.nav_home_gragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
                        //      .addToBackStack("Home") -----------> Old code to do backstack
                        .commit() //apply the transcation
                    //supportActionBar?.title ="Home"
                    drawerLayout.closeDrawers() //then close the drawers
                }

                R.id.nav_profile_gragment -> { //for notes
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, HomeFragment())
                        //      .addToBackStack("Home") -----------> Old code to do backstack
                        .commit()
                    //supportActionBar?.title = "Notes"
                    drawerLayout.closeDrawers()
                }

                R.id.nav_syllabus_gragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, SaylabussFragment())
                        //      .addToBackStack("Home") -----------> Old code to do backstack
                        .commit()
                    //supportActionBar?.title = "About"
                    drawerLayout.closeDrawers()
                }

                R.id.nav_result_gragment->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ResultFragment())
                        //      .addToBackStack("Home") -----------> Old code to do backstack
                        .commit()
                    //supportActionBar?.title = "Result"
                    drawerLayout.closeDrawers()
                }

                R.id.nav_about_gragment->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        //      .addToBackStack("Home") -----------> Old code to do backstack
                        .commit()
                    //supportActionBar?.title = "About Us"
                    drawerLayout.closeDrawers()
                }

                R.id.nav_upload->{
                   /* val intent = Intent(this@MainActivity,
                        upload_pdf::class.java
                    )



                    startActivity(intent) //start the activity, in adapter we have to use context

                    */
                    Toast.makeText(this,"Nothnig Happen dude",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawers()
                    it.isChecked = false
                }
            }
            return@setNavigationItemSelectedListener true
        }




    }
/*

    private fun showUpdateDialog() {
        val alertDialog : AlertDialog? = AlertDialog.Builder(this)
            .setTitle("New Update Availible")
            .setMessage("Update Now")
            .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->

                try{

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(updateLink)
                    startActivity(intent)
                }catch (e : Exception){
                    Toast.makeText(this,"SomeThing went wrong",Toast.LENGTH_SHORT).show()
                }
            }).show()

        alertDialog?.setCancelable(false)

    }

*/

    //setup toolbar
    fun setUpToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true) //ham burger icon enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //display hamburger

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
//the home id is predifined for hamburger icon
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START) //here start means from starting of screen means drawer open from left side
        }
        return super.onOptionsItemSelected(item)
    }

    fun openHome() {

        navigationView.setCheckedItem(R.id.nav_home_gragment)
        val fragment = AboutFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        supportActionBar?.title = "Home"
    }

    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.frame)


        when (frag) {
            !is AboutFragment -> openHome()

            else -> super.onBackPressed()

        }
    }

    /*fun GetAppCode(context: Context): Int{

        var code = 0

        try{

            val pInfo = context.packageManager.getPackageInfo(context.packageName,0)
            code = pInfo.versionCode
        }catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        return code
    }*/







}