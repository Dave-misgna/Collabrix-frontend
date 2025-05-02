package android.example.collabrix.ViewModel


import andorid.example.collabrix.Model.ActiveProjects.ActiveProjects
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BrowseResearchesViewModel : ViewModel(){
    //browse researches
    private val _browse = MutableStateFlow<List<ActiveProjects>>(emptyList())
    val browse: StateFlow<List<ActiveProjects>> = _browse


    //pending Projects
    private val _pending = MutableStateFlow<List<ActiveProjects>>(emptyList())
    val pending: StateFlow<List<ActiveProjects>> = _pending

    init {
        browseNewResearches()
        pendingResearchProjects()
    }
    private fun browseNewResearches(){
        _browse.value = listOf(
            ActiveProjects("1", "Mobile Application development", "John Doe", "Open", "This is the the best project you could imagine"),
            ActiveProjects("2", "IoT Tracker", "Jane Smith", "Open", "this is one of the best research collabe with me")
        )
    }

    private fun pendingResearchProjects(){
        _pending.value = listOf(
            ActiveProjects("1", "Mobile Application development", "John Doe", "Pending", "This is the the best project you could imagine"),
            ActiveProjects("2", "IoT Tracker", "Jane Smith", "Pending", "this is one of the best research collabe with me")
        )
    }

}