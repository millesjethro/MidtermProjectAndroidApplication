package com.auf.ciscomidtermproject.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.auf.ciscomidtermproject.*
import com.auf.ciscomidtermproject.databinding.FragmentBookingBinding
import com.auf.ciscomidtermproject.databinding.FragmentSettingsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Booking.newInstance] factory method to
 * create an instance of this fragment.
 */
class Booking : Fragment(), View.OnClickListener{
    var i = 0
    var max = 0

    private lateinit var binding: FragmentBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBookingBinding.inflate(layoutInflater)
        val prefs = activity?.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        val loginEmail = prefs?.getString(EMAIL, null);



    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookingBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnNext.setOnClickListener(this)
        binding.btnPrev.setOnClickListener(this)
        binding.Forder.setOnClickListener(this)
        binding.Corder.setOnClickListener(this)

        val context = this.activity
        val db = context?.let { HandlerDatabaseCar(it) }
        var data = db?.readsData()
        max = (data?.size ?: -1)

        if(max-1 < 0){
            binding.carPrice.text = "No Car Ordered Yet"
            binding.carName.text = "No Car Ordered Yet"
            binding.carModel.text = "No Car Ordered Yet"
        }
        else{
            val context = this.activity
            val db = context?.let { HandlerDatabaseCar(it) }
            var data = db?.readsData()
            binding.carPrice.text = data?.get(i)?.carPrice.toString()
            binding.carName.text = data?.get(i)?.carName.toString()
            binding.carModel.text = data?.get(i)?.carModel.toString()
        }
        return view
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btnNext)->{
                i++
                val context = this.activity
                val db = context?.let { HandlerDatabaseCar(it) }
                var data = db?.readsData()
                if((data?.size ?: -1)-1 < 0){
                    i = 0
                    binding.carPrice.text = "No Car Ordered Yet"
                    binding.carName.text = "No Car Ordered Yet"
                    binding.carModel.text = "No Car Ordered Yet"
                }
                else if(i > (data?.size ?: -1)-1){
                    i = 0

                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()
                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
                else {
                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()
                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
            }

            (R.id.btnPrev)->{
                i--
                val context = this.activity
                val db = context?.let { HandlerDatabaseCar(it) }
                var data = db?.readsData()
                max = (data?.size ?: -1)
                if((data?.size ?: -1)-1 < 0){
                    i = 0
                    binding.carPrice.text = "No Car Ordered Yet"
                    binding.carName.text = "No Car Ordered Yet"
                    binding.carModel.text = "No Car Ordered Yet"
                }
                else if(i < 0){
                    i = max-1
                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()

                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
                else {
                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()

                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
            }
            (R.id.Corder)->{
                i--
                val context = this.activity
                val db = context?.let { HandlerDatabaseCar(it) }
                db?.deleteData(binding.carName.text.toString())
                Toast.makeText(context, "Deleted Successfully",Toast.LENGTH_SHORT).show()

                var data = db?.readsData()
                if((data?.size ?: -1)-1 < 0){
                    i = 0
                    binding.carPrice.text = "No Car Ordered Yet"
                    binding.carName.text = "No Car Ordered Yet"
                    binding.carModel.text = "No Car Ordered Yet"

                }
                else{
                    i = 0
                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()
                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
            }
            (R.id.Forder)->{
                i--
                val context = this.activity
                val db = context?.let { HandlerDatabaseCar(it) }
                db?.deleteData(binding.carName.text.toString())

                Toast.makeText(context, "Ordered Successfully",Toast.LENGTH_SHORT).show()

                var data = db?.readsData()
                if((data?.size ?: -1)-1 < 0){
                    i = 0
                    binding.carPrice.text = "No Car Ordered Yet"
                    binding.carName.text = "No Car Ordered Yet"
                    binding.carModel.text = "No Car Ordered Yet"
                }
                else{
                    i = 0
                    val context = this.activity
                    val db = context?.let { HandlerDatabaseCar(it) }
                    var data = db?.readsData()
                    binding.carPrice.text = data?.get(i)?.carPrice.toString()
                    binding.carName.text = data?.get(i)?.carName.toString()
                    binding.carModel.text = data?.get(i)?.carModel.toString()
                }
            }
        }
    }


}