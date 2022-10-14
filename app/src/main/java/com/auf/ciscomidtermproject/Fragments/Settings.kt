package com.auf.ciscomidtermproject.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.auf.ciscomidtermproject.*
import com.auf.ciscomidtermproject.databinding.FragmentSettingsBinding


class Settings : Fragment(), View.OnClickListener{
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val prefs = activity?.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        val loginEmail = prefs?.getString(EMAIL, null);


        val context = this.activity
        val db = context?.let { HandlerDatabase(it) }
        var data = db?.readData()
        for(i in 0 until (data?.size ?: -1)){
            if(data?.get(i)?.Email == loginEmail){
                binding.settingsName.text = "NAME: " +data?.get(i)?.Name
                binding.settingsEmail.text = "EMAIL: "+data?.get(i)?.Email
                binding.settingsAge.text = "AGE: "+data?.get(i)?.age
            }
        }


        binding.btnlogout.setOnClickListener(this)
        binding.btnchpass.setOnClickListener(this)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClick(p0: View?) {
        val prefs = activity?.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        val loginEmail = prefs?.getString(EMAIL, null);
        val context = this.activity
        val db = context?.let { HandlerDatabase(it) }
        when(p0!!.id){
            (R.id.btnlogout)->{
                activity?.let{
                    val intent = Intent (it, LogIn::class.java)
                    it.startActivity(intent)
                }
            }
            (R.id.btnchpass)->{
                var Passwords = binding.newPassword.text.toString()
                db?.updateData(Passwords)

                val context = this.activity
                val db = context?.let { HandlerDatabase(it) }
                var data = db?.readData()
                for(i in 0 until (data?.size ?: -1)){
                    if(data?.get(i)?.Email == loginEmail){
                        Toast.makeText(context, data?.get(i)?.Password, Toast.LENGTH_SHORT).show()

                    }
                }

            }
        }
    }
}






