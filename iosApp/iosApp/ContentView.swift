import SwiftUI
import ComposeApp

struct ContentView: View {
    // This defers initialization and works around the timing issue
    @State private var authWrapper: FirebaseAuthWrapper? = nil

    var body: some View {
        TestView()
//        Button("Test KMP Anonymous Login") {
//            print("Swift: Triggering Login...")
//            
//            // Create wrapper RIGHT when needed, not in onAppear
//           if authWrapper == nil {
//               print("Swift: Creating wrapper NOW...")
//               authWrapper = FirebaseAuthWrapper()
//           }
//           
//            
//            // Safely call the function on the initialized wrapper
//            authWrapper?.signInAnonymously()
//        }
//        .onAppear {
//            // This runs reliably AFTER FirebaseApp.configure()
////            if authWrapper == nil {
////                authWrapper = FirebaseAuthWrapper()
////                print("Swift: Wrapper initialized in .onAppear")
////            }
//        }
    }
}
