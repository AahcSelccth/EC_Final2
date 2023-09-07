package com.example.ec_final2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.ec_final2.R
import com.example.ec_final2.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    authenticateWithFirebase(account.idToken!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.etEmail.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateEmailPassword(text.toString(), binding.etPassword.editText?.text.toString())
        }

        binding.etPassword.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateEmailPassword(binding.etEmail.editText?.text.toString(), text.toString())
        }

        binding.btnLogin.setOnClickListener {
            val password = binding.etPassword.editText?.text.toString()
            val email = binding.etEmail.editText?.text.toString()
            loginWithEmailAndPassword(email, password)
        }

        binding.btnGoogle.setOnClickListener {
            // Inicio de sesión con Google
            loginWithGoogle()
        }

        binding.btnSignUp.setOnClickListener {
            // Registro con email y contraseña
            val password = binding.etPassword.editText?.text.toString()
            val email = binding.etEmail.editText?.text.toString()
            signUpWithEmailAndPassword(email, password)
        }
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "El usuario no está registrado", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signUpWithEmailAndPassword(email: String, password: String) {
        if (validateEmailPassword(email, password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        Toast.makeText(this, "El usuario fue registrado", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "No son credenciales válidas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun authenticateWithFirebase(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }

    private fun loginWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, googleSignInOptions)
        val intent = googleClient.signInIntent
        googleLauncher.launch(intent)
    }

    private fun validateEmailPassword(email: String, password: String): Boolean {
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        return isEmailValid && isPasswordValid
    }
}

