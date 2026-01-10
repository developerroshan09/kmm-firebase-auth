import Foundation
import ComposeApp

@MainActor
final class AuthViewModel: ObservableObject {

    @Published private(set) var authState: AuthState = AuthState.LoggedOut()

    private let authWrapper: FirebaseAuthWrapper
    private var authStateTask: Task<Void, Never>?

    init(authWrapper: FirebaseAuthWrapper = FirebaseAuthWrapper()) {
        self.authWrapper = authWrapper
//        self.authWrapper.configure()
        
        self.authWrapper.collectAuthState { state in
            print("New AuthState: \(state)")
            self.authState = state
        }
    }

    // UI intent forwarding only
    func login(email: String, password: String) {
        Task {
            do {
                let user = try await authWrapper.login(email: email, password: password)
                print("Login success: \(user.id)")
            } catch {
                print("Login failed: \(error)")
            }
        }
    }

    func signUp(email: String, password: String) {
        Task {
            do {
                let user = try await authWrapper.signUp(email: email, password: password)
                print("Signup success: \(user.id)")
            } catch {
                print("Signup failed: \(error)")
            }
        }
    }

    func loginWithGoogle(idToken: String) async {
            do {
                // Call KMM suspend function
                let user = try await authWrapper.loginWithGoogle(idToken: idToken)

                print("✅ Google login success: \(user.id), email: \(user.email ?? "N/A")")
                // authState will automatically update if your KMM _authState Flow is updated

            } catch {
                print("❌ Google login failed: \(error)")
            }
        }
    
    func loginAnonymously() {
        authWrapper.signInAnonymously()
    }

    func logout() async {
        do {
            try await authWrapper.logout()
            print("Logout success: ")
        } catch {
            print("Logout failed: \(error)")
        }
    }

    deinit {
        authStateTask?.cancel()
    }
}
