package android.example.collabrix.data.api

import andorid.example.collabrix.data.model.ActiveProjects
import andorid.example.collabrix.data.model.StudentProfile
import android.example.collabrix.data.model.ApiResponse
import android.example.collabrix.data.model.PaginatedResponse
import retrofit2.Response
import retrofit2.http.*

interface StudentApiService {
    // Profile endpoints
    @GET("students/profile")
    suspend fun getStudentProfile(): Response<ApiResponse<StudentProfile>>

    @PATCH("students/profile")
    suspend fun updateStudentProfile(@Body profile: StudentProfile): Response<ApiResponse<StudentProfile>>

    // Project endpoints with pagination and filtering
    @GET("projects/available")
    suspend fun getAvailableProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("department") department: String? = null,
        @Query("search") searchQuery: String? = null,
        @Query("sort") sortBy: String? = null
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/active")
    suspend fun getActiveProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/pending")
    suspend fun getPendingProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/applied")
    suspend fun getAppliedProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/approved")
    suspend fun getApprovedProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/rejected")
    suspend fun getRejectedProjects(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaginatedResponse<List<ActiveProjects>>>

    @GET("projects/{id}")
    suspend fun getProjectById(@Path("id") projectId: String): Response<ApiResponse<ActiveProjects>>

    @POST("projects/{id}/apply")
    suspend fun applyToProject(@Path("id") projectId: String): Response<ApiResponse<Unit>>

    @DELETE("projects/{id}/withdraw")
    suspend fun withdrawApplication(@Path("id") projectId: String): Response<ApiResponse<Unit>>
} 