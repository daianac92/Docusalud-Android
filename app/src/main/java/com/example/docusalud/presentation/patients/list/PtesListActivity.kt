package com.example.docusalud.presentation.patients.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docusalud.data.PtesInfo
import com.example.docusalud.databinding.ActivityPtesListBinding
import com.example.docusalud.mvvm.viewmodel.PatientsViewModel
import com.example.docusalud.mvvm.factory.PatientsViewModelFactory
import com.example.docusalud.presentation.patients.detail.PteDetailActivity
import com.example.docusalud.presentation.patients.info.PteInfoActivity

class PtesListActivity : AppCompatActivity(), OnPteClickListener {
    private lateinit var binding: ActivityPtesListBinding
    private val viewModel: PatientsViewModel by viewModels(factoryProducer = {
        PatientsViewModelFactory(this)
    })
    private lateinit var ptesListAdapter: PtesListAdapter
    private val ptes = mutableListOf<PtesInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPtesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPatients()
        setObservers()
        setUpViews()
    }

    private fun setUpViews() {
        initRecyclerView()
        binding.btnAddPatient.setOnClickListener {
            navigateToPteInfoActivity()
        }
    }

    private fun navigateToPteInfoActivity() {
        val pteInfoActivity = Intent(this, PteInfoActivity::class.java)
        startActivity(pteInfoActivity)
    }

    private fun setObservers() {
        viewModel.ptesList.observe(this) {
            Log.d("DATO", it.toString())
            ptesListAdapter.updateList(it)
        }
    }

    private fun initRecyclerView() {
        ptesListAdapter = PtesListAdapter(ptes, this)
        binding.rvPatients.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ptesListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@PtesListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

        }
    }

    override fun onClick(pteInfo: PtesInfo) {
        val pteDetailActivity = Intent(this, PteDetailActivity::class.java).apply {
            putExtra(PATIENTS_INFO, pteInfo)
        }
        startActivity(pteDetailActivity)
    }
}

const val PATIENTS_INFO = "Patients info"