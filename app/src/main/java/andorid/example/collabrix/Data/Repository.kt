package andorid.example.collabrix.Data

import andorid.example.collabrix.Data.Model.StudentModel.StudentProfile
import andorid.example.collabrix.Data.Network.ApiClient
import andorid.example.collabrix.Data.Network.StudentApiService

class StudentRepository(private val apiService: StudentApiService) {

    suspend fun getProfile(): Result<StudentProfile> {
        return try {
            val profile = apiService.getProfile()
            Result.success(profile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createProfile(profile: StudentProfile): Result<Unit> {
        return try {
            val response = apiService.createStudentProfile(profile)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
