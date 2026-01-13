import SwiftUI
import ComposeApp

struct RootView: View {

    @StateObject private var viewModel = AuthViewModel()

    var body: some View {
        switch viewModel.authState {
        case is AuthState.LoggedOut:
            AuthContainerView(viewModel: viewModel)

        case let loggedIn as AuthState.LoggedIn:
            HomeScreen(
                email: loggedIn.user.email ?? "Anonymous",
                onLogout: {
                    Task {
                        await viewModel.logout()
                    }
                }, viewModel: viewModel
            )

        default:
            EmptyView()
        }
    }
}
