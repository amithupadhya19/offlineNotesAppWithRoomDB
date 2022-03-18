package com.example.quixote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quixote.room.QuixoteDatabase

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        QuixoteDatabase.get(this)
        val sharedPreferences:SharedPreferences=getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val user=sharedPreferences.getString("USER",null).toString()
        if(sharedPreferences.contains("USER")){
            val intent=Intent(this,Home::class.java)
            intent.putExtra("user",user)
            startActivity(intent)
        }
        val editor:SharedPreferences.Editor=sharedPreferences.edit()

        val dao=QuixoteDatabase.get(this).getDao()
        val goToSignup:TextView=findViewById(R.id.goToSignupPage)
        goToSignup.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }

        val inputfield:EditText=findViewById(R.id.LoginPhonenoemailField)


        val passwordField:EditText=findViewById(R.id.LoginPasswordField)


        val loginButton: Button =findViewById(R.id.LoginButton)
        loginButton.setOnClickListener {
            val input:String=inputfield.text.toString()
            val password:String=passwordField.text.toString()
            if(dao.is_valid(input, password)){

                editor.apply{
                    putString("USER",input)
                }.commit()
                val intent=Intent(this,Home::class.java)
                intent.putExtra("user",input)
                startActivity(intent)
                finish()
                //Toast.makeText(this,"valid",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"invalid credentials",Toast.LENGTH_SHORT).show()
            }
        }
    }
}