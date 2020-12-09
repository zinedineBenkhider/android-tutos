package android.tuto.covid_progress.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.tuto.covid_progress.R
import android.tuto.covid_progress.model.CountryStat
import android.tuto.covid_progress.model.CovidClient
import android.tuto.covid_progress.presenter.ContractMain
import android.tuto.covid_progress.presenter.MainPresenterImpl
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),
    ContractMain.MainView {
    lateinit var mainPresenterImpl: MainPresenterImpl
    lateinit var progressBar: ProgressBar
    lateinit var countryStatAdapter: CountryStatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar=findViewById(R.id.progress_bar)
        val recyclerView:RecyclerView =findViewById(R.id.countries_stats_recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        countryStatAdapter =CountryStatAdapter()
        recyclerView.adapter=countryStatAdapter
        val covidClient= CovidClient.getInstance()
        mainPresenterImpl=
            MainPresenterImpl(
                covidClient,
                this
            )
        mainPresenterImpl.displayResult()
    }

    override fun showProgress() {
        progressBar.visibility=VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility=GONE
    }

    override fun setDataToAdapter(listCountryStat: List<CountryStat>) {
        listCountryStat.forEach { countryStat->countryStatAdapter.bindViewModel(countryStat) }
    }
}