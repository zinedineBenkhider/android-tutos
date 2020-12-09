package android.tuto.covid_progress.view

import android.tuto.covid_progress.R
import android.tuto.covid_progress.model.CountryStat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView


class CountryStatAdapter :RecyclerView.Adapter<CountryStatAdapter.AssetViewHolder>() {
        private var data: List<CountryStat> = emptyList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
            val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_country_stat_item, parent, false)
            return AssetViewHolder(v)
        }

        override fun onBindViewHolder(@NonNull holder: AssetViewHolder, position: Int) {
            holder.updateAsset(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }

        fun bindViewModel(countryStat: CountryStat) {
            data+=countryStat
            notifyDataSetChanged()
        }

        class AssetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private val nameTextView: TextView
            private val populationTextView: TextView
            private val deathsTextView: TextView
            private val testTextView: TextView
            private val casesTextView: TextView
            private val view: View
            init {
                view = v
                nameTextView = v.findViewById(R.id.country_name)
                populationTextView=v.findViewById(R.id.country_population)
                deathsTextView = v.findViewById(R.id.nb_deaths)
                testTextView = v.findViewById(R.id.nb_tests)
                casesTextView=v.findViewById(R.id.nb_cases)
            }

            fun updateAsset(countryStat: CountryStat) {
                nameTextView.text = countryStat.country
                populationTextView.text=countryStat.population.toString()
                deathsTextView.text=countryStat.deaths.total.toString()
                testTextView.text=countryStat.tests.total.toString()
                casesTextView.text=countryStat.cases.total.toString()
            }

        }
}