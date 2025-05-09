package android.example.collabrix.ViewModel


import andorid.example.collabrix.data.model.ActiveProjects
import andorid.example.collabrix.data.model.StudentProfile
import andorid.example.collabrix.data.repository.StudentRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StudentState {
    object Initial : StudentState()
    object Loading : StudentState()
    data class Success(
        val profile: StudentProfile? = null,
        val projects: List<ActiveProjects> = emptyList()
    ) : StudentState()
    data class Error(val message: String) : StudentState()
}

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _state = MutableStateFlow<StudentState>(StudentState.Initial)
    val state: StateFlow<StudentState> = _state.asStateFlow()

    init {
        loadStudentData()
    }

    private fun loadStudentData() {
        viewModelScope.launch {
            _state.value = StudentState.Loading
            try {
                val profile = repository.getStudentProfile()
                val projectsResponse = repository.getAvailableProjects()
                _state.value = StudentState.Success(
                    profile = profile,
                    projects = projectsResponse.data
                )
            } catch (e: Exception) {
                _state.value = StudentState.Error(e.message ?: "Failed to load student data")
            }
        }
    }

    fun updateProfile(profile: StudentProfile) {
        viewModelScope.launch {
            _state.value = StudentState.Loading
            try {
                val updatedProfile = repository.updateStudentProfile(profile)
                _state.value = (_state.value as? StudentState.Success)?.copy(
                    profile = updatedProfile
                ) ?: StudentState.Success(profile = updatedProfile)
            } catch (e: Exception) {
                _state.value = StudentState.Error(e.message ?: "Failed to update profile")
            }
        }
    }

    fun getProjectById(projectId: String) {
        viewModelScope.launch {
            _state.value = StudentState.Loading
            try {
                val project = repository.getProjectById(projectId)
                _state.value = StudentState.Success(
                    projects = listOf(project)
                )
            } catch (e: Exception) {
                _state.value = StudentState.Error(e.message ?: "Failed to load project")
            }
        }
    }

    fun refreshData() {
        loadStudentData()
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up any resources if needed
    }
} 
