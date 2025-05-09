package android.example.collabrix.ViewModel


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

sealed class BrowseResearchesState {
    object Initial : BrowseResearchesState()
    object Loading : BrowseResearchesState()
    data class Success(
        val availableProjects: List<ActiveProjects>,
        val pendingProjects: List<ActiveProjects>,
        val availablePage: Int = 1,
        val pendingPage: Int = 1,
        val hasMoreAvailable: Boolean = false,
        val hasMorePending: Boolean = false
    ) : BrowseResearchesState()
    data class Error(val message: String) : BrowseResearchesState()
}

@HiltViewModel
class BrowseResearchesViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BrowseResearchesState>(BrowseResearchesState.Initial)
    val state: StateFlow<BrowseResearchesState> = _state.asStateFlow()

    init {
        loadAllProjects()
    }

    fun loadAllProjects() {
        viewModelScope.launch {
            _state.value = BrowseResearchesState.Loading
            try {
                val availableResponse = repository.getAvailableProjects()
                val pendingResponse = repository.getPendingProjects()
                _state.value = BrowseResearchesState.Success(
                    availableProjects = availableResponse.data,
                    pendingProjects = pendingResponse.data,
                    availablePage = availableResponse.pagination.currentPage,
                    pendingPage = pendingResponse.pagination.currentPage,
                    hasMoreAvailable = availableResponse.pagination.hasNextPage,
                    hasMorePending = pendingResponse.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(
                    e.message ?: "Failed to load projects"
                )
            }
        }
    }

    fun searchProjects(query: String) {
        viewModelScope.launch {
            _state.value = BrowseResearchesState.Loading
            try {
                val availableResponse = repository.getAvailableProjects(searchQuery = query)
                val pendingResponse = repository.getPendingProjects()
                _state.value = BrowseResearchesState.Success(
                    availableProjects = availableResponse.data,
                    pendingProjects = pendingResponse.data,
                    availablePage = availableResponse.pagination.currentPage,
                    pendingPage = pendingResponse.pagination.currentPage,
                    hasMoreAvailable = availableResponse.pagination.hasNextPage,
                    hasMorePending = pendingResponse.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(
                    e.message ?: "Failed to search projects"
                )
            }
        }
    }

    fun loadMoreAvailableProjects(page: Int) {
        viewModelScope.launch {
            try {
                val currentState = _state.value as? BrowseResearchesState.Success ?: return@launch
                val response = repository.getAvailableProjects(page = page)
                _state.value = currentState.copy(
                    availableProjects = currentState.availableProjects + response.data,
                    availablePage = response.pagination.currentPage,
                    hasMoreAvailable = response.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(e.message ?: "Failed to load more available projects")
            }
        }
    }

    fun loadMorePendingProjects(page: Int) {
        viewModelScope.launch {
            try {
                val currentState = _state.value as? BrowseResearchesState.Success ?: return@launch
                val response = repository.getPendingProjects(page = page)
                _state.value = currentState.copy(
                    pendingProjects = currentState.pendingProjects + response.data,
                    pendingPage = response.pagination.currentPage,
                    hasMorePending = response.pagination.hasNextPage
                )
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(e.message ?: "Failed to load more pending projects")
            }
        }
    }

    fun applyToProject(projectId: String) {
        viewModelScope.launch {
            try {
                repository.applyToProject(projectId)
                // Reload projects after applying
                loadAllProjects()
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(e.message ?: "Failed to apply to project")
            }
        }
    }

    fun withdrawApplication(projectId: String) {
        viewModelScope.launch {
            try {
                repository.withdrawApplication(projectId)
                // Reload projects after withdrawing
                loadAllProjects()
            } catch (e: Exception) {
                _state.value = BrowseResearchesState.Error(e.message ?: "Failed to withdraw application")
            }
        }
    }

    fun refreshProjects() {
        loadAllProjects()
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up any resources if needed
    }
} 