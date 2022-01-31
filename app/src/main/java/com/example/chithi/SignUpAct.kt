package com.example.chithi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpAct : AppCompatActivity() {

    private lateinit var edtUser: EditText
    private lateinit var edtEmal: EditText
    private lateinit var edtPas: EditText
    private lateinit var signup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth= FirebaseAuth.getInstance()

        edtEmal=findViewById(R.id.editEmail2)
        edtPas=findViewById(R.id.editPass2)
        edtUser=findViewById(R.id.editUser)
        signup=findViewById(R.id.signUpbutton2)

        signup.setOnClickListener{
            val name=edtUser.text.toString()
            val email=edtEmal.text.toString()
            val password=edtPas.text.toString()

            createAccount(name,email,password);
        }
    }
    private fun createAccount(name:String,email: String, password: String) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                        addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent= Intent(this@SignUpAct,MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
        // [END create_user_with_email]
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))


    }

}