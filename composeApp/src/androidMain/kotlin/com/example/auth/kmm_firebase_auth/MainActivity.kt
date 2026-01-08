package com.example.auth.kmm_firebase_auth

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        Log.i("FirebaseTest", "signInAnonymously() called")

        FirebaseAuth.getInstance()
            .signInAnonymously()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.i("FirebaseTest", "✅ Firebase anonymous login SUCCESS")
//                } else {
//                    Log.i("FirebaseTest", "❌ Firebase login FAILED", task.exception)
//                }
//            }

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}