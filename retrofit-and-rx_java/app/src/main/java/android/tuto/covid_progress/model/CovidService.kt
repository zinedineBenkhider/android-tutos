package android.tuto.covid_progress.model
import io.reactivex.Observable
import retrofit2.http.GET

interface CovidService {
    @GET("/statistics")
    fun getCountriesStats() : Observable<CountryStatResponse>
}