package android.tuto.covid_progress.presenter

import android.tuto.covid_progress.model.CountryStat

interface ContractMain {
     interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToAdapter(listCountryStat : List<CountryStat>)

    }

     interface MainPresenter {
        fun displayResult()
    }
}