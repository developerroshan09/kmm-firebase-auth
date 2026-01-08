import com.kmm_firebase_auth.domain.repository.FirebaseAuthWrapper

class AuthTest {

    fun testAnonymousLogin() {
        FirebaseAuthWrapper().signInAnonymously()
    }
}
