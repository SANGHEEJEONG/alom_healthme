package com.example.alomtest.mypage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R

import com.example.alomtest.databinding.FragmentMypageBodyMeasurementEditmodeBinding


class mypage_body_measurement_editmode : Fragment() {
    private lateinit var binding: FragmentMypageBodyMeasurementEditmodeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentMypageBodyMeasurementEditmodeBinding.inflate(layoutInflater)

        binding.backiconBtn.setOnClickListener {

            replaceFragment(mypage_body_measurement())
        }
//        binding.savemode.setOnClickListener {
//            //변수에 입력한 값들을 저장하고 fragment 변경
//
//            //if문으로 예외처리
//            //공백이 입력된 경우
//            if (binding.heightOutput.text.toString()
//                    .isEmpty() || binding.weightOutput.text.toString().isEmpty()
//            ) {
//                Toast.makeText(requireContext(), "신장 또는 몸무게를 입력 후 저장버튼을 누르세요.", Toast.LENGTH_SHORT)
//                    .show()
//
//            }
//            //신장 혹은 몸무게가 0으로 입력된 경우
//            else if (binding.heightOutput.text.toString()
//                    .toDouble() == 0.0 || binding.weightOutput.text.toString().toDouble() == 0.0
//            ) {
//                Toast.makeText(requireContext(), "신장 또는 몸무게가 0이 될 수 없습니다.", Toast.LENGTH_SHORT)
//                    .show()
//
//            } else {
//
//
//                SharedPreferenceUtils.saveData(requireContext(), "height", binding.heightOutput.text.toString())
//                SharedPreferenceUtils.saveData(requireContext(), "weight", binding.weightOutput.text.toString())
//                //bmi 계산
//                var calculate_bmi: Double = (binding.weightOutput.text.toString().toDouble()) / ((binding.heightOutput.text.toString().toDouble() / 100.0) * (binding.heightOutput.text.toString().toDouble() / 100.0)) * 10
//
//
//                    if(calculate_bmi>300){
//                        calculate_bmi=300.0
//                    }
//
//
//                    SharedPreferenceUtils.saveData(requireContext(), "bmi", (calculate_bmi.toString()))
//
//
//                    //home_height=binding.heightOutput.text.toString().toDouble()
//                    //home_weight=binding.weightOutput.text.toString().toDouble()
//                    //home_bmi=(home_weight)/((home_height/100.0)*(home_height/100.0))*10
////
//
//                    //println("변경된이후 키:" + home_height)
//                    //println("변경된이후 몸무게:" + home_weight)
//
//                    replaceFragment(mypage_body_measurement())
//
//
//            }
//
//        }

       //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_body_measurement())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

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