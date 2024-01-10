package com.example.alomtest.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentMypageBodyInformationBinding
import com.example.alomtest.databinding.FragmentMypageBodyInformationNewBinding


class mypage_body_information_new : Fragment() {
  private lateinit var binding:FragmentMypageBodyInformationNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=FragmentMypageBodyInformationNewBinding.inflate(layoutInflater)
        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_main())

        }
        binding.editModeIcon.setOnClickListener {
            replaceFragment(mypage_body_information_editmode())

        }



//editable을 막는 코드
//        val name_edittext=binding.nameOutput
//        name_edittext.keyListener = null
        val birthday_edittext=binding.birthdayOutput
        birthday_edittext.keyListener = null
        val gender_edittext=binding.genderOutput
        gender_edittext.keyListener = null
        val weight_edittext=binding.weightOutput
        weight_edittext.keyListener = null
        val height_edittext=binding.heightOutput
        height_edittext.keyListener = null



        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_main())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }





    private var _binding: FragmentMypageBodyInformationNewBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMypageBodyInformationNewBinding.inflate(inflater,container,false)
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



        override fun onResume() {
            super.onResume()
            //val homeinstance = Home()

//        val w:String=homeinstance.weight.toString()
//        val h:String=homeinstance.height.toString()

            val w: String = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
            //val w:String=home_weight.toString()
            //val h:String= home_height.toString()
            val h: String = SharedPreferenceUtils.loadData(requireContext(), "height", "")

            val bmi:String= SharedPreferenceUtils.loadData(requireContext(), "bmi", "")

            val name:String= SharedPreferenceUtils.loadData(requireContext(), "name", "")

            val birthday:String= SharedPreferenceUtils.loadData(requireContext(), "birthday", "")

            val gender:String= SharedPreferenceUtils.loadData(requireContext(), "gender", "")


            binding.genderOutput.setText(gender)
            binding.nameOutput.setText(name)
            binding.birthdayOutput.setText(birthday)

            binding.weightOutput.setText(w)
            binding.heightOutput.setText(h)
            println("변경된 w"+w)
            println("변경된 h"+h)
        }




    companion object {
    }
}