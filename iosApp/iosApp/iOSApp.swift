import SwiftUI
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        // Ensure this is called once and ONLY here
        FirebaseAuthWrapper().configure()
        return true
    }
}

@main
struct iOSApp: App {
    // This adaptor is the modern standard for 2026 KMP projects
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
