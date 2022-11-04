package com.example.docusalud.presentation.patients.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.docusalud.data.PtesInfo
import com.example.docusalud.databinding.ItemPatientsBinding

class PtesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPatientsBinding.bind(view)

    fun bind(pteInfo: PtesInfo, position: Int, listener: OnPteClickListener){
        with (binding){
            tvPatientName.text = "${pteInfo.name}, ${pteInfo.lastName}"
            tvPatientOS.text = pteInfo.os
            tvPatientFecNac.text = pteInfo.fecNac
            cvPte.setOnClickListener { listener.onClick(pteInfo) }
        }
    }
}