package com.surajrathod.bcahub

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class upload_pdf : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var editTitle: EditText
    lateinit var editDesc: EditText
    lateinit var editSem: EditText
    lateinit var editSub: EditText
    lateinit var editPrev: EditText
    lateinit var imgPrev: ImageView
    lateinit var noSelect: TextView
    lateinit var btnImg: Button
    lateinit var btnPdf: Button
    lateinit var btnUpload: Button
    lateinit var imageUri: Uri
    lateinit var imgLink: String
    lateinit var pdfLink: String
    lateinit var pdfUri : Uri
    var flag : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pdf)


        //toolbar

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Upload Notes"

        //assign variables

        editTitle = findViewById(R.id.edittitle)
        editDesc = findViewById(R.id.editdesc)
        editSem = findViewById(R.id.editsem)
        editPrev = findViewById(R.id.editulink)
        editSub = findViewById(R.id.editsub)
        imgPrev = findViewById(R.id.imgPrev)
        noSelect = findViewById(R.id.noFile)
        btnImg = findViewById(R.id.btnSelectImg)
        btnPdf = findViewById(R.id.btnSelectPdf)
        btnUpload = findViewById(R.id.btnUpload)


        //select image

        btnImg.setOnClickListener {
            selectImage()
            //flag = true
            //uploadImg()
        }

        //select pdf

        btnPdf.setOnClickListener {
            selectPdf()
            //flag = true
        }

        btnUpload.setOnClickListener {

            uploadFile()

        }
    }

    private fun selectPdf() {

        val intent = Intent()

        intent.type = "*/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 101)

    }

    private fun uploadFile() {


        //lateinit var imageLinking : String
        //uploadPdf()

        if(flag){

            val formatter1 = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
            val now1 = Date()
            val fileName1 = formatter1.format(now1)

            var pdfRef: StorageReference =
                FirebaseStorage.getInstance().reference.child("pdf/$fileName1")
            pdfRef.putFile(pdfUri).addOnSuccessListener {

                val result1 = it.metadata!!.reference!!.downloadUrl

                result1.addOnSuccessListener {
                    var pdfLink1 = it.toString()

                    pdfLink = pdfLink1
                    noSelect.text = "File is uploaded"


                }


                Toast.makeText(applicationContext,"Pdf is uploaded",Toast.LENGTH_SHORT).show()

            }

                .addOnFailureListener{
                    Toast.makeText(applicationContext,"Pdf is not uploaded",Toast.LENGTH_SHORT).show()
                }


            //uploadImg()

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Uploading Note...")
            progressDialog.show()

            val formatter = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)

            var imageRef: StorageReference =
                FirebaseStorage.getInstance().reference.child("images/$fileName+.jpg")
            imageRef.putFile(imageUri).addOnSuccessListener {


                val result = it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {

                    var imageLink = it.toString()

                    //noSelect.text = imageLink

                    //imageLinking = imageLink
                    imgLink = imageLink

                    //when image is uploaded do this




                    val ref = FirebaseDatabase.getInstance().getReference("Notes")
                    val noteid = ref.push().key

                    val note = Notes(

                        editDesc.text.toString(),
                        //imgLink,



                        noteid,
                        //noSelect.text.toString(),
                        imageLink,
                        pdfLink,
                        //editPrev.text.toString(),
                        editSem.text.toString(),
                        editSub.text.toString(),
                        editTitle.text.toString(),
                        // imgLink.toString()
                        editPrev.text.toString()



                    )

                    if (noteid != null) {
                        ref.child(noteid).setValue(note).addOnCompleteListener {

                            Toast.makeText(applicationContext, "String Uploaded", Toast.LENGTH_SHORT).show()
                        }
                    }






                }
                Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

                .addOnFailureListener {

                    Toast.makeText(applicationContext, "Image Not uploaded", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()

                }


            //uploading string values here

        }else{


            Toast.makeText(applicationContext,"Please choose files",Toast.LENGTH_SHORT).show()

        }


/*
        val formatter1 = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
        val now1 = Date()
        val fileName1 = formatter1.format(now1)

        var pdfRef: StorageReference =
            FirebaseStorage.getInstance().reference.child("pdf/$fileName1")
        pdfRef.putFile(pdfUri).addOnSuccessListener {

            val result1 = it.metadata!!.reference!!.downloadUrl

            result1.addOnSuccessListener {
                var pdfLink1 = it.toString()

                pdfLink = pdfLink1
                noSelect.text = "File is uploaded"


            }


            Toast.makeText(applicationContext,"Pdf is uploaded",Toast.LENGTH_SHORT).show()

        }

            .addOnFailureListener{
                Toast.makeText(applicationContext,"Pdf is not uploaded",Toast.LENGTH_SHORT).show()
            }


        //uploadImg()

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading Note...")
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)

        var imageRef: StorageReference =
            FirebaseStorage.getInstance().reference.child("images/$fileName+.jpg")
        imageRef.putFile(imageUri).addOnSuccessListener {


            val result = it.metadata!!.reference!!.downloadUrl
            result.addOnSuccessListener {

                var imageLink = it.toString()

                //noSelect.text = imageLink

                //imageLinking = imageLink
                imgLink = imageLink

                //when image is uploaded do this




                val ref = FirebaseDatabase.getInstance().getReference("Notes")
                val noteid = ref.push().key

                val note = Notes(

                    editDesc.text.toString(),
                    //imgLink,



                    noteid,
                    //noSelect.text.toString(),
                    imageLink,
                    pdfLink,
                    //editPrev.text.toString(),
                    editSem.text.toString(),
                    editSub.text.toString(),
                    editTitle.text.toString(),
                    // imgLink.toString()
                    editPrev.text.toString()



                )

                if (noteid != null) {
                    ref.child(noteid).setValue(note).addOnCompleteListener {

                        Toast.makeText(applicationContext, "String Uploaded", Toast.LENGTH_SHORT).show()
                    }
                }






            }
            Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        }

            .addOnFailureListener {

                Toast.makeText(applicationContext, "Image Not uploaded", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }


        //uploading string values here




        */



    }

    private fun uploadImg() {


        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading Note...")
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)

        var imageRef: StorageReference =
            FirebaseStorage.getInstance().reference.child("images/$fileName+.jpg")
        imageRef.putFile(imageUri).addOnSuccessListener {


            val result = it.metadata!!.reference!!.downloadUrl
            result.addOnSuccessListener {

                var imageLink = it.toString()

                //noSelect.text = imageLink




            }
            Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        }

            .addOnFailureListener {

                Toast.makeText(applicationContext, "Image Not uploaded", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }


    }

    private fun uploadPdf() {

        val formatter1 = SimpleDateFormat("yyyy__MM__dd__HH__mm__ss", Locale.getDefault())
        val now1 = Date()
        val fileName1 = formatter1.format(now1)

        var pdfRef: StorageReference =
            FirebaseStorage.getInstance().reference.child("pdf/$fileName1")
        pdfRef.putFile(pdfUri).addOnSuccessListener {

            Toast.makeText(applicationContext,"Pdf is uploaded",Toast.LENGTH_SHORT).show()
        }

            .addOnFailureListener{
                Toast.makeText(applicationContext,"Pdf is not uploaded",Toast.LENGTH_SHORT).show()
            }
    }

    private fun selectImage() {

        val intent = Intent()

        intent.type = "*/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {


            imageUri = data?.data!!
            imgPrev.setImageURI(imageUri)
            flag = true


        }

        if (requestCode == 101 && resultCode == RESULT_OK) {


            pdfUri = data?.data!!
            noSelect.text = "File is choosen!"
            flag = true


        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}