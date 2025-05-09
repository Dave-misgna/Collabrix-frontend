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

sealed class MyApplicationsState {
    object Initial : MyApplicationsState()
    object Loading : MyApplicationsState()
    data class Success(
        val pendingProjects: List<ActiveProjects>,
        val approvedProjects: List<ActiveProjects>,
        val rejectedProjects: List<ActiveProjects>
    ) : MyApplicationsState()
    data class Error(val message: String) : MyApplicationsState()
}

@HiltViewModel
class MyApplicationsViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {
    private val _state = MutableStateFlow<MyApplicationsState>(MyApplicationsState.Initial)
    val state: StateFlow<MyApplicationsState> = _state.asStateFlow()

    init {
        loadApplications()
    }

    fun loadApplications() {
        viewModelScope.launch {
            _state.value = MyApplicationsState.Loading
            try {
                val pendingResponse = repository.getPendingProjects()
                val approvedResponse = repository.getApprovedProjects()
                val rejectedResponse = repository.getRejectedProjects()

                _state.value = MyApplicationsState.Success(
                    pendingProjects = pendingResponse.data,
                    approvedProjects = approvedResponse.data,
                    rejectedProjects = rejectedResponse.data
                )
            } catch (e: Exception) {
                _state.value = MyApplicationsState.Error(
                    e.message ?: "Failed to load applications"
                )
            }
        }
    }

    fun withdrawApplication(projectId: String) {
        viewModelScope.launch {
            try {
                repository.withdrawApplication(projectId)
                loadApplications() // Reload after withdrawal
            } catch (e: Exception) {
                _state.value = MyApplicationsState.Error(
                    e.message ?: "Failed to withdraw application"
                )
            }
        }
    }

    fun refreshApplications() {
        loadApplications()
    }
} 