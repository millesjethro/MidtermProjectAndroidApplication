package com.auf.ciscomidtermproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.auf.ciscomidtermproject.databinding.ActivityRegisterBinding

class Register : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.btncancel.setOnClickListener(this)


    }


    override fun onClick(p0: View?) {
        val context = this


        when(p0!!.id){
            (R.id.btnRegister)-> {

                val FullNames = binding.edttxtFullName.text.toString()
                val Emails = binding.edttxtEmail.text.toString()
                val Passwords = binding.edttxtPassword.text.toString()
                val Ages = binding.edttxtAge.text.toString()

                if(FullNames != "" && Emails != "" && Passwords != "" && Ages != ""){
                    var UID = DataBaseHelp(binding.edttxtFullName.text.toString(),binding.edttxtEmail.text.toString(),binding.edttxtPassword.text.toString(),Integer.parseInt(binding.edttxtAge.text.toString()))
                    var db = HandlerDatabase(context)
                    db.instertData(UID)
                }
                else{
                    Toast.makeText(context,"Please Fill in Every Details",Toast.LENGTH_SHORT).show()
                }
            }
            (R.id.btncancel)->{
                var intent = Intent(this,LogIn::class.java)
                startActivity(intent)
            }
        }


    }
}