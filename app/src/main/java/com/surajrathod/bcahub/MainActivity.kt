package com.surajrathod.bcahub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(this,"Welcome to BCA Hub",Toast.LENGTH_LONG).show()
        //initialize variables

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)

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
                    val intent = Intent(this@MainActivity,
                        upload_pdf::class.java
                    )



                    startActivity(intent) //start the activity, in adapter we have to use context
                    drawerLayout.closeDrawers()
                    it.isChecked = false
                }
            }
            return@setNavigationItemSelectedListener true
        }




    }




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



}