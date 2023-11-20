package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.Home.Companion.home_height
import com.example.alomtest.Home.Companion.home_weight
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementBinding


class mypage_body_measurement : Fragment() {
    private lateinit var binding: FragmentMypageBodyMeasurementBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageBodyMeasurementBinding.inflate((layoutInflater))


        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_main())
        }
        binding.editModeIcon.setOnClickListener {
            replaceFragment(mypage_body_measurement_editmode())
        }

        val homeinstance = Home()

//        val w:String=homeinstance.weight.toString()
//        val h:String=homeinstance.height.toString()

        val w:String=home_weight.toString()
        val h:String= home_height.toString()




        binding.weightOutput.setText(w)
        binding.heightOutput.setText(h)

        val weight_edittext=binding.weightOutput
        weight_edittext.keyListener = null

        val height_edittext=binding.heightOutput
        height_edittext.keyListener = null


//        binding.backiconBtn2.setOnClickListener {
//            replaceFragment(mypage_main())
//        }


    }
    //binding을 위한 코드
    private var _binding: FragmentMypageBodyMeasurementBinding? = null

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//
//
//
//        return inflater.inflate(R.layout.fragment_mypage_body_measurement, container, false)
//    }
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentMypageBodyMeasurementBinding.inflate(inflater,container,false)
    return binding.root
}

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")
    }




    //saying.keyListener = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)







    }


    override fun onResume() {
        super.onResume()
        val homeinstance = Home()

//        val w:String=homeinstance.weight.toString()
//        val h:String=homeinstance.height.toString()

        val w:String= home_weight.toString()
        val h:String= home_height.toString()



        binding.weightOutput.setText(w)
        binding.heightOutput.setText(h)
        println("변경된 w"+w)
        println("변경된 h"+h)
    }

//    fun access_weight_height(){
//        val homeinstance = Home()
//        var w=homeinstance.weight
//        var h=homeinstance.height
//        //val w=homeinstance.weight
//    }

    companion object {
    }


}