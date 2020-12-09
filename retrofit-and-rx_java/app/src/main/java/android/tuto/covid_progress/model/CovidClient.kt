package android.tuto.covid_progress.model

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class CovidClient {
    private val covidService   =  Retrofit.Builder()
        .baseUrl("https://covid-193.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getHttpClient())
        .build().create(CovidService::class.java)

    companion object{
         lateinit var INSTANCE: CovidClient
        fun getInstance(): CovidClient {
            INSTANCE = CovidClient()
            return INSTANCE
        }
    }


    fun getCountriesStats():Observable<CountryStatResponse>{
        return covidService.getCountriesStats()
    }
    private fun getHttpClient(): OkHttpClient? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                    val newRequest: Request = request.newBuilder()
                        .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
                        .addHeader("x-rapidapi-key","ff63e42a9emshd0c9b93c8a3378bp1b89c9jsnf8b65c48fa5e")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .build()
    }
}