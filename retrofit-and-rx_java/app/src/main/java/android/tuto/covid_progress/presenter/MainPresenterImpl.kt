package android.tuto.covid_progress.presenter

import android.tuto.covid_progress.model.CountryStatResponse
import android.tuto.covid_progress.model.CovidClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainPresenterImpl(covidClient: CovidClient, mainView: ContractMain.MainView):
    ContractMain.MainPresenter {
    private val covidClient=covidClient;
    private val mainView=mainView
    override fun displayResult(){
        mainView.showProgress()
        covidClient.getCountriesStats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CountryStatResponse> {
                override fun onNext(countriesStats: CountryStatResponse) {
                    mainView.setDataToAdapter(countriesStats.response)
                }
                override fun onError(e: Throwable) {
                    mainView.hideProgress()
                }
                override fun onComplete() {
                    mainView.hideProgress()
                }
                override fun onSubscribe(d: Disposable) {
                }
            })
    }

}