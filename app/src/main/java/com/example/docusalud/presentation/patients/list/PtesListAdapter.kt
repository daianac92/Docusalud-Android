package com.example.docusalud.presentation.patients.list

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.docusalud.R
import com.example.docusalud.data.PtesInfo

class PtesListAdapter(
    private var ptesList: List<PtesInfo>,
    private var listener: OnPteClickListener
): RecyclerView.Adapter<PtesViewHolder>(){

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PtesViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_patients, parent,false)
        return PtesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PtesViewHolder, position: Int) {
       val item = ptesList[position]
        holder.bind(item, position, listener)
    }

    override fun getItemCount() = ptesList.size

    fun updateList(newPtesList: List<PtesInfo>){
        ptesList = newPtesList
        notifyDataSetChanged()
    }


}