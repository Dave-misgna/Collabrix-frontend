package andorid.example.collabrix.ViewModel

import andorid.example.collabrix.Model.StudentModel.ActiveProjects
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    private val _activeProjects = MutableStateFlow<List<ActiveProjects>>(emptyList())
    val projects: StateFlow<List<ActiveProjects>> = _activeProjects

    //total applied
    private val _totalAppliedProjects = MutableStateFlow<List<ActiveProjects>>(emptyList())
    val totalAppliedProjects: StateFlow<List<ActiveProjects>> = _totalAppliedProjects

    init {
        loadActiveProjects()
    }

    private fun loadActiveProjects() {
        // Simulate API call
        _activeProjects.value = listOf(
            ActiveProjects("1", "AI Chatbot", "John Doe", "In Progress", "This is the the best project you could imagine"),
            ActiveProjects("2", "IoT Tracker", "Jane Smith", "In Progress", "this is one of the best research collabe with me")
        )


    }

    private fun totalProjects(projects: ActiveProjects){
        _totalAppliedProjects.value += projects
    }
}
