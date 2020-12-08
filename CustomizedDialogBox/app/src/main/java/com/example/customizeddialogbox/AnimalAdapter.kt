package com.example.customizeddialogbox
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(onActionInterface: MainOnActionInterface) : RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() {
    private var animals: List<AnimalViewModel> = emptyList()
    private val onActionInterface:MainOnActionInterface=onActionInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val inflatedView = parent.inflate(R.layout.recycler_view_dialog_item, false)
        return AnimalHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.updateViewHolder(animals[position],onActionInterface)
    }

    fun bindViewModel(animal: AnimalViewModel){
        animals+=animal
        notifyDataSetChanged()
    }

    class AnimalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         private val view : View=itemView
         private val nameTextView:TextView=itemView.findViewById(R.id.name_animal_dialog_item);
         private val countryTextView:TextView=itemView.findViewById(R.id.country_animal_dialog_item)
         fun updateViewHolder(animal : AnimalViewModel, mainOnActionInterface: MainOnActionInterface){
             nameTextView.text = animal.name
             countryTextView.text=animal.country
             view.setOnClickListener(View.OnClickListener {
                 mainOnActionInterface.onAnimalClick(animal)
             })
         }
    }
}