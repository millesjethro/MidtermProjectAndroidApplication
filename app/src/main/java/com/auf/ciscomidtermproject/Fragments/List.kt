package com.auf.ciscomidtermproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.auf.ciscomidtermproject.*
import com.auf.ciscomidtermproject.databinding.FragmentListBinding


class List : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.car1Order.setOnClickListener(this)
        binding.car2Order.setOnClickListener(this)
        binding.car3Order.setOnClickListener(this)

        return view

    }

    override fun onClick(p0: View?) {
        val context = this.activity
        when(p0!!.id){
            (R.id.car1Order)->{
                var UID = DataBaseHelpCars(binding.car1Name1.text.toString(),binding.car1Price1.text.toString(),binding.car1Model1.text.toString())
                var db = context?.let { HandlerDatabaseCar(it) }
                db?.instertsData(UID)

                Toast.makeText(context, binding.car1Name1.text.toString()+" "+ binding.car1Model1.text.toString()+" "+ binding.car1Price1.text.toString(), Toast.LENGTH_SHORT).show()

            }
            (R.id.car2Order)->{
                var UID = DataBaseHelpCars(binding.car2Name2.text.toString(),binding.car2Model2.text.toString(),binding.car2Price2.text.toString())
                var db = context?.let { HandlerDatabaseCar(it) }
                db?.instertsData(UID)

                Toast.makeText(context, binding.car2Name2.text.toString()+" "+ binding.car2Model2.text.toString()+" "+ binding.car2Price2.text.toString(), Toast.LENGTH_SHORT).show()
            }
            (R.id.car3Order)->{
                var UID = DataBaseHelpCars(binding.car3Name3.text.toString(),binding.car3Price3.text.toString(),binding.car3Model3.text.toString())
                var db = context?.let { HandlerDatabaseCar(it) }
                db?.instertsData(UID)

                Toast.makeText(context, binding.car3Name3.text.toString()+" "+ binding.car3Model3.text.toString()+" "+ binding.car3Price3.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}