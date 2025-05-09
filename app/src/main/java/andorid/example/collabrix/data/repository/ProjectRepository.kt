package andorid.example.collabrix.data.repository

import andorid.example.collabrix.data.model.ActiveProjects
import android.example.collabrix.data.api.StudentApiService
import android.example.collabrix.data.model.PaginatedResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val api: StudentApiService
) {
    suspend fun getAvailableProjects(
        page: Int = 1,
        limit: Int = 10,
        department: String? = null,
        searchQuery: String? = null,
        sortBy: String? = null
    ): PaginatedResponse<List<ActiveProjects>> {
        val response = api.getAvailableProjects(page, limit, department, searchQuery, sortBy)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        throw Exception("Failed to get available projects: ${response.code()}")
    }

    suspend fun getActiveProjects(
        page: Int = 1,
        limit: Int = 10
    ): PaginatedResponse<List<ActiveProjects>> {
        val response = api.getActiveProjects(page, limit)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        throw Exception("Failed to get active projects: ${response.code()}")
    }

    suspend fun getPendingProjects(
        page: Int = 1,
        limit: Int = 10
    ): PaginatedResponse<List<ActiveProjects>> {
        val response = api.getPendingProjects(page, limit)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        throw Exception("Failed to get pending projects: ${response.code()}")
    }

    suspend fun getAppliedProjects(
        page: Int = 1,
        limit: Int = 10
    ): PaginatedResponse<List<ActiveProjects>> {
        val response = api.getAppliedProjects(page, limit)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        throw Exception("Failed to get applied projects: ${response.code()}")
    }

    suspend fun getProjectById(projectId: String): ActiveProjects {
        val response = api.getProjectById(projectId)
        if (response.isSuccessful) {
            val apiResponse = response.body()
            if (apiResponse?.success == true && apiResponse.data != null) {
                return apiResponse.data
            }
            throw Exception(apiResponse?.error ?: "Failed to get project")
        }
        throw Exception("Failed to get project: ${response.code()}")
    }

    suspend fun applyToProject(projectId: String) {
        val response = api.applyToProject(projectId)
        if (response.isSuccessful) {
            val apiResponse = response.body()
            if (apiResponse?.success == true) {
                return
            }
            throw Exception(apiResponse?.error ?: "Failed to apply to project")
        }
        throw Exception("Failed to apply to project: ${response.code()}")
    }

    suspend fun withdrawApplication(projectId: String) {
        val response = api.withdrawApplication(projectId)
        if (response.isSuccessful) {
            val apiResponse = response.body()
            if (apiResponse?.success == true) {
                return
            }
            throw Exception(apiResponse?.error ?: "Failed to withdraw application")
        }
        throw Exception("Failed to withdraw application: ${response.code()}")
    }
} 