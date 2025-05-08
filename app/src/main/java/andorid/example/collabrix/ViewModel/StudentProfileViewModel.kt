package andorid.example.collabrix.ViewModel

import andorid.example.collabrix.Model.StudentModel.StudentProfile
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel: ViewModel(){

    // for the student profile card
    private val _studentcard = MutableStateFlow(StudentProfile(
        id = "1",
        name = "Dawit Misgna",
        department = "Software Engineering",
        email = "dawit@gmail.com",
        college = "Addis Ababa Institute Of Technology",
        year = "3rd year",
        skill = listOf("Java","Python","Web development"),
        description = "I'm a Software Engineering student with a focus on machine learning and data science. " +
                "I'm passionate about using technology to solve real-world problems and am looking for research opportunities to apply my skills."
        ,
    ))
    val studentcard: StateFlow<StudentProfile> = _studentcard





    }



