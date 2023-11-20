package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.Home.Companion.home_bmi
import com.example.alomtest.Home.Companion.home_height
import com.example.alomtest.Home.Companion.home_weight
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementBinding
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementEditmodeBinding


class mypage_body_measurement_editmode : Fragment() {
    private lateinit var binding: FragmentMypageBodyMeasurementEditmodeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentMypageBodyMeasurementEditmodeBinding.inflate(layoutInflater)

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_body_measurement())
        }
        binding.savemode.setOnClickListener {
            //변수에 입력한 값들을 저장하고 fragment 변경
            val homeinstance = Home()

//            homeinstance.height=binding.heightOutput.text.toString().toDouble()
//            homeinstance.weight=binding.weightOutput.text.toString().toDouble()
            home_height=binding.heightOutput.text.toString().toDouble()
            home_weight=binding.weightOutput.text.toString().toDouble()
            home_bmi=(home_weight)/((home_height/100.0)*(home_height/100.0))*10
            if(home_bmi>300){
                home_bmi=300.0
            }

            println("변경된이후 키:"+ home_height)
            println("변경된이후 몸무게:"+ home_weight)

            replaceFragment(mypage_body_measurement())
        }




    }
    private var _binding: FragmentMypageBodyMeasurementEditmodeBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMypageBodyMeasurementEditmodeBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }










    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")
    }



    companion object {
    }

}