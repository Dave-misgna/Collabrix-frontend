package andorid.example.collabrix.ViewModel

import andorid.example.collabrix.Model.StudentModel.AboutMe
import andorid.example.collabrix.Model.StudentModel.Education
import andorid.example.collabrix.Model.StudentModel.Skills
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
        image = Icons.Default.AccountCircle
    ))
    val studentcard: StateFlow<StudentProfile> = _studentcard

    // for the aboutMe Card
    private val _about = MutableStateFlow(AboutMe(
        id = "1",
        description = "I am a passionate mobile developer focused on Android and backend systems."
    ))
    val about: StateFlow<AboutMe> = _about

    //for the SKills List
    private val _skill = MutableStateFlow<List<Skills>>(emptyList())
    val skill: StateFlow<List<Skills>> = _skill

    //for the education section Card
    private val _educationHistory = MutableStateFlow(Education(
        id = "1",
        college = "Addis Ababa University",
        department = "Software Enginnering",
        year = "2023-"
    ))
    val educationHistory:StateFlow<Education> = _educationHistory

    init {

    }
    private fun LoadSkills(){
        _skill.value = listOf(
            Skills(
                id = "1",
                skill = "Java"
            ),
            Skills(
                id = "2",
                skill = "Python"
            ),
            Skills(
                id = "3",
                skill = "Web development"
            )
        )
    }


}