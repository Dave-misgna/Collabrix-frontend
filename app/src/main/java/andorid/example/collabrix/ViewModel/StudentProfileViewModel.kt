package andorid.example.collabrix.ViewModel

import andorid.example.collabrix.Data.Model.StudentModel.AboutMe
import andorid.example.collabrix.Data.Model.StudentModel.Education
import andorid.example.collabrix.Data.Model.StudentModel.Skills
import andorid.example.collabrix.Data.Model.StudentModel.StudentProfile
import andorid.example.collabrix.Data.Network.ApiClient
import andorid.example.collabrix.Data.StudentRepository
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _profile = mutableStateOf<StudentProfile?>(null)
    val profile: State<StudentProfile?> = _profile

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {

            val result = studentRepository.getProfile()
            result.onSuccess { profileData ->
                _profile.value = profileData
            }
        }
    }

    fun refreshProfile() = loadProfile()
}
