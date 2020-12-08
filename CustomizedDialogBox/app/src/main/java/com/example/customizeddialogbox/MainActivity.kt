package com.example.customizeddialogbox

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MainOnActionInterface{

    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var dialogBox: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnShowDialogBox: Button = findViewById(R.id.btn_show_dialog_box)
        btnShowDialogBox.setOnClickListener(View.OnClickListener {
            this.dialogBox = Dialog(this)
            this.dialogBox.setContentView(R.layout.dialog_box)
            this.animalAdapter = AnimalAdapter(this)
            val animalsRecyclerView: RecyclerView = this.dialogBox.findViewById(R.id.dialog_recycler_view)//recycler view is defined in dialog box View
            animalsRecyclerView.layoutManager = LinearLayoutManager(this)
            animalsRecyclerView.adapter = this.animalAdapter
            addDataToAdapter()
            dialogBox.show()
        })
    }

    private fun addDataToAdapter() {
        val animals: List<AnimalViewModel> =
            listOf(
                AnimalViewModel("Kangaroo", "Australia"),
                AnimalViewModel("Fourniers", "Argentine"),
                AnimalViewModel("Royal Eagle","Albania"),
                AnimalViewModel("Mouflon","Cyprus"),
                AnimalViewModel("Fennec","Algeria")
            )
        animals.forEach { animal -> animalAdapter.bindViewModel(animal) }
    }

    override fun onAnimalClick(animal: AnimalViewModel) {
        dialogBox.cancel()
        val snackBar=Snackbar.make(window.decorView.rootView,getString(R.string.msg_animal_selected)+animal.name,LENGTH_SHORT)
        snackBar.show()
    }
}