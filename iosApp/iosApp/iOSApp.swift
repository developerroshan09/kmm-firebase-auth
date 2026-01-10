import SwiftUI
import FirebaseCore

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        // Ensure this is called once and ONLY here
        FirebaseApp.configure()
        // check immediately after configuration
                if (FirebaseApp.app() != nil) {
                    print("✅ FirebaseApp successfully configured.")
                } else {
                    print("❌ FirebaseApp configuration FAILED.")
                }

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
