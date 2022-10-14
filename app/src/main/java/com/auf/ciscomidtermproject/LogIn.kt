package com.auf.ciscomidtermproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auf.ciscomidtermproject.databinding.ActivityLogInBinding


class LogIn : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val context = this
        val sharedPref = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        var db = HandlerDatabase(context)
        when(p0!!.id){
            (R.id.btnRegister)->{
                var intent = Intent(this,Register::class.java)
                startActivity(intent)
            }
            (R.id.btnLogin)->{
               var data = db.readData()
               for(i in 0 until data.size){
                   if(data[i].Email == binding.edtEmail.text.toString() && data[i].Password == binding.edtPassword.text.toString()){

                       var intent = Intent(this,MainActivity::class.java)
                       val editor = sharedPref.edit()
                       editor.putString(EMAIL,data[i].Email)
                       editor.putString(PASSWORD,data[i].Password)
                       editor.commit()
                       startActivity(intent)
                   }
                   else{
                     Toast.makeText(context,"Wrong Email or Password",Toast.LENGTH_SHORT)
                   }
               }
            }
        }
    }
}