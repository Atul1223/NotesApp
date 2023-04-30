package com.example.notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R

class NotesAdapter(private val listener: OnItemClickListener?): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_tile,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val deleteButton = itemView.findViewById(R.id.iv_note_tile_delete) as ImageView
        private val itemLayout = itemView.findViewById(R.id.notes_item_tile) as ConstraintLayout

        init {
           itemLayout.setOnClickListener(this)

            deleteButton.setOnClickListener {
                val position = adapterPosition
                listener?.onDeleteClick(position)
            }

        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener?.onItemClick(position)
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

}