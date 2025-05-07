//package andorid.example.collabrix.ViewModel
//
//import andorid.example.collabrix.Data.Model.Authentication.SignInDto
//import andorid.example.collabrix.Data.Model.Authentication.Tokens
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class AuthViewModel : ViewModel() {
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
//    val authState: StateFlow<AuthState> = _authState
//
//    sealed class AuthState {
//        object Idle : AuthState()
//        object Loading : AuthState()
//        data class Success(val tokens: Tokens, val role: String?) : AuthState()
//        data class Error(val message: String) : AuthState()
//    }
//
//    fun signIn(email: String, password: String) {
//        _authState.value = AuthState.Loading
//        viewModelScope.launch {
//            try {
//                val response = RetrofitClient.instance.signin(SignInDto(email, password))
//                if (response.isSuccessful) {
//                    // Extract role from token (JWT) or API response
//                    val role = extractRoleFromToken(response.body()?.access_token)
//                    _authState.value = AuthState.Success(response.body()!!, role)
//                } else {
//                    _authState.value = AuthState.Error("Login failed: ${response.message()}")
//                }
//            } catch (e: Exception) {
//                _authState.value = AuthState.Error("Network error: ${e.message}")
//            }
//        }
//    }
//
//    private fun extractRoleFromToken(token: String?): String? {
//        // Decode JWT to get role (if stored in the token)
//        // Example: Use a JWT library or manual parsing
//        return "USER" // Replace with actual logic
//    }
//
//    // Similarly implement signUp(), logout(), refreshTokens()
//}