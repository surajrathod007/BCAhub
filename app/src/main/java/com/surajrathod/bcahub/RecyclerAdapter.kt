package com.surajrathod.bcahub

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso


class RecyclerAdapter(val context: Context,val notesList : ArrayList<Notes>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notes_container,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = notesList[position]
        holder.txtTitle.text = currentItem.title
        Picasso.get().load(currentItem.img).error(R.drawable.ic_notes_icon).into(holder.imgNotes);
        holder.txtSem.text = currentItem.sem
        holder.txtSubject.text = currentItem.sub


        holder.cardView.setOnClickListener {
            //open new activity

            val intent = Intent(context,
                Notes_info::class.java
            )

            intent.putExtra("title", currentItem.title)
            intent.putExtra("img", currentItem.img)
            intent.putExtra("sem", currentItem.sem)
            intent.putExtra("sub", currentItem.sub)
            intent.putExtra("desc", currentItem.desc)
            intent.putExtra("link", currentItem.link)
            intent.putExtra("ulink",currentItem.ulink)

            context.startActivity(intent) //start the activity, in adapter we have to use context
        }


    }

    override fun getItemCount(): Int {
        return notesList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

/*
        val imgNotes : ImageView = itemView.findViewById(R.id.imgNotes)
        val txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        val txtSem : TextView = itemView.findViewById(R.id.txtSem)
        val txtSubject : TextView = itemView.findViewById(R.id.txtSubject)
        val cardView : CardView = itemView.findViewById(R.id.cardViewDashboard)

 */






        val imgNotes : RoundedImageView = itemView.findViewById(R.id.imageRound)
        val txtTitle : TextView = itemView.findViewById(R.id.notesTitle)
        val txtSem : TextView = itemView.findViewById(R.id.notesSem)
        val txtSubject : TextView = itemView.findViewById(R.id.notesSub)
        val cardView : ConstraintLayout = itemView.findViewById(R.id.notesContainer)






    }
}