package com.example.chithi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginAct : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtPass:EditText
    private lateinit var login:Button
    private lateinit var signup:Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.editEmail)
        edtPass=findViewById(R.id.editPass)
        login=findViewById(R.id.logInbutton)
        signup=findViewById(R.id.signUpbutton)

        signup.setOnClickListener{
            val intent= Intent(this,SignUpAct::class.java)
            startActivity(intent)
        }

        login.setOnClickListener{
            val email=edtEmail.text.toString()
            val pass=edtPass.text.toString()

            logIn(email,pass);
        }
    }

    private fun logIn(email: String, pass: String) {
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@LoginAct,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }
}