//
//  TestView.swift
//  iosApp
//
//  Created by Roshan Bade on 10/01/2026.
//

import SwiftUI
import ComposeApp

struct TestView: View {
    
    @State private var authWrapper: FirebaseAuthWrapper? = nil
    
    var body: some View {
        Button("Test KMP Anonymous Login") {
            print("Swift: Triggering Login...")
            
            // Create wrapper RIGHT when needed, not in onAppear
           if authWrapper == nil {
               print("Swift: Creating wrapper NOW...")
               authWrapper = FirebaseAuthWrapper()
           }
           
            
            // Safely call the function on the initialized wrapper
            authWrapper?.signInAnonymously()
        }
        .onAppear {
            // This runs reliably AFTER FirebaseApp.configure()
//            if authWrapper == nil {
//                authWrapper = FirebaseAuthWrapper()
//                print("Swift: Wrapper initialized in .onAppear")
//            }
        }
    }
}

#Preview {
    TestView()
}
