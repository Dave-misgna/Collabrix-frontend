package andorid.example.collabrix.Data.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // API base URL
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Retrofit instances for each role
    val api: StudentApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentApiService::class.java)
    }
}