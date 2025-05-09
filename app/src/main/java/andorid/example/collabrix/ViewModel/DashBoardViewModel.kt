package andorid.example.collabrix.ViewModel

import andorid.example.collabrix.data.model.ActiveProjects
import andorid.example.collabrix.data.repository.StudentRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DashboardState {
    object Initial : DashboardState()
    object Loading : DashboardState()
    data class Success(
        val activeProjects: List<ActiveProjects>,
        val totalAppliedProjects: List<ActiveProjects>,
        val activeProjectsPage: Int = 1,
        val appliedProjectsPage: Int = 1,
        val hasMoreActive: Boolean = false,
        val hasMoreApplied: Boolean = false
    ) : DashboardState()
    data class Error(val message: String) : DashboardState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {
    private val _state = MutableStateFlow<DashboardState>(DashboardState.Initial)
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _state.value = DashboardState.Loading
            try {
                val activeProjectsResponse = repository.getActiveProjects()
                val appliedProjectsResponse = repository.getAppliedProjects()

                _state.value = DashboardState.Success(
                    activeProjects = activeProjectsResponse.data,
                    totalAppliedProjects = appliedProjectsResponse.data,
                    activeProjectsPage = activeProjectsResponse.pagination.currentPage,
                    appliedProjectsPage = appliedProjectsResponse.pagination.currentPage,
                    hasMoreActive = activeProjectsResponse.pagination.hasNextPage,
                    hasMoreApplied = appliedProjectsResponse.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = DashboardState.Error(
                    e.message ?: "Failed to load dashboard data"
                )
            }
        }
    }

    fun loadMoreActiveProjects(page: Int) {
        viewModelScope.launch {
            try {
                val currentState = _state.value as? DashboardState.Success ?: return@launch
                val response = repository.getActiveProjects(page = page)
                _state.value = currentState.copy(
                    activeProjects = currentState.activeProjects + response.data,
                    activeProjectsPage = response.pagination.currentPage,
                    hasMoreActive = response.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = DashboardState.Error(e.message ?: "Failed to load more active projects")
            }
        }
    }

    fun loadMoreAppliedProjects(page: Int) {
        viewModelScope.launch {
            try {
                val currentState = _state.value as? DashboardState.Success ?: return@launch
                val response = repository.getAppliedProjects(page = page)
                _state.value = currentState.copy(
                    totalAppliedProjects = currentState.totalAppliedProjects + response.data,
                    appliedProjectsPage = response.pagination.currentPage,
                    hasMoreApplied = response.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = DashboardState.Error(e.message ?: "Failed to load more applied projects")
            }
        }
    }

    fun refreshDashboard() {
        loadDashboardData()
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up any resources if needed
    }
}
