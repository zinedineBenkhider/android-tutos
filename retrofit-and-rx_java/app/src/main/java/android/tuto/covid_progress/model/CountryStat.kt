package android.tuto.covid_progress.model

class CountryStat (country:String,population:Int,cases:Stat,deaths:Stat,tests:Stat){
    val country=country
    val population=population
    val cases=cases
    val deaths=deaths
    val tests=tests

     class Stat(total:Int){
        val total=total
    }
}
