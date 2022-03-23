package com.example.quixote

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.example.quixote.models.note
import com.example.quixote.models.user
import com.example.quixote.room.QuixoteDatabase
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val passpattern=Pattern.compile("^[a-z]" +//first character must be lowercase
                "(?=(.*[0-9]){2})" +         //at least 2 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=(.*[A-Z]){2})" +         //at least 2 upper case letter
                //"(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,15}" +               //at least 4 characters
                "$");
        val dao=QuixoteDatabase.get(this).getDao()

        val usernameField:EditText=findViewById(R.id.SignupUsernameField)


        val emailField:EditText=findViewById(R.id.SignupEmailField)


        val phonenoField:EditText=findViewById(R.id.SignupPhonenoField)


        val passwordField:EditText=findViewById(R.id.SIgnupPasswordField)


        val signupButton:Button=findViewById(R.id.SignupButton)
        signupButton.setOnClickListener {
            if(usernameField.text.isEmpty()){
                usernameField.requestFocus()
                usernameField.setError("please enter username")
                return@setOnClickListener
            }
            if(emailField.text.isEmpty()||!(Patterns.EMAIL_ADDRESS.matcher(emailField.text.toString()).matches())){
                emailField.requestFocus()
                emailField.setError("please enter valid email")
                return@setOnClickListener
            }
            if(phonenoField.text.isEmpty()||(phonenoField.text.toString().length!=10)||
                !containsdigit(phonenoField.text.toString())){
                phonenoField.requestFocus()
                phonenoField.setError("please enter valid ten digit phone number")
                return@setOnClickListener
            }
           if (!passpattern.matcher(passwordField.text.toString()).matches()){
                passwordField.requestFocus()
               passwordField.setError("password must contain min 8 and max 15 characters with 1 special " +
                       "character,2 uppercase and 2 digits and should start with lowercase letter")
               return@setOnClickListener
           }




            val username:String=usernameField.text.toString()
            val email=emailField.text.toString()
            val phoneno:String=phonenoField.text.toString()
            val password:String=passwordField.text.toString()
            if(dao.is_taken(email,username,phoneno)){
                Toast.makeText(this,"user already exists with the entered username,email or phoneno",Toast.LENGTH_SHORT).show()
            }
            else{
                val pic:Bitmap= BitmapFactory.decodeResource(resources,R.drawable.tree)


                val note=note("have fun","don't forget to have fun",pic,username)
                dao.insertUser(user(username,email,phoneno,password))
                dao.insertNote(note)
                Toast.makeText(this,"registered new user $username",Toast.LENGTH_SHORT).show()
                usernameField.setText("")
                emailField.setText("")
                phonenoField.setText("")
                passwordField.setText("")
            }
        }

        val goToLogin:TextView=findViewById(R.id.goToLoginPage)
        goToLogin.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun containsdigit(num: String): Boolean {

        var count=0
        while(count<num.length){
            if(!num[count].isDigit()){
                return false
            }
            count=count+1
        }
        return true
    }
}