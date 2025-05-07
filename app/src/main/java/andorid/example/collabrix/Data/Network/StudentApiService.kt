package andorid.example.collabrix.Data.Network

import andorid.example.collabrix.Data.Model.StudentModel.StudentProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface StudentApiService {

    @GET("profile")
    suspend fun getProfile():StudentProfile

    @PATCH("profile")
    suspend fun createStudentProfile(@Body profile: StudentProfile): Response<Unit>
}