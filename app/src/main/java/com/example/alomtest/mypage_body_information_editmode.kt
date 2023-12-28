package com.example.alomtest

import SharedPreferenceUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.databinding.FragmentMypageBodyInformationEditmodeBinding


class mypage_body_information_editmode : Fragment() {

    private lateinit var binding:FragmentMypageBodyInformationEditmodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






        binding=FragmentMypageBodyInformationEditmodeBinding.inflate(layoutInflater)

        //성별 체크박스 코드

        val current_gender=(SharedPreferenceUtils.loadData(requireContext(),"gender","")).toString()



        val man=binding.manCheckbox
        val woman=binding.womanCheckbox
        if(current_gender=="남성"){
            man.isChecked=true
        }
        else if(current_gender=="여성"){
            woman.isChecked=true
        }



        man.setOnCheckedChangeListener { _, isChecked -> if(isChecked){
            woman.isChecked = false
        }
        }
        woman.setOnCheckedChangeListener { _, isChecked -> if(isChecked){
            man.isChecked = false
        }
        }

//


        val current_w=(SharedPreferenceUtils.loadData(requireContext(),"weight","")).toDouble()
        val current_h=(SharedPreferenceUtils.loadData(requireContext(),"height","")).toDouble()

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_body_information())
        }
                binding.savemode.setOnClickListener {
            //변수에 입력한 값들을 저장하고 fragment 변경

            //if문으로 예외처리
            //공백이 입력된 경우
            if (binding.heightOutput.text.toString()
                    .isEmpty() || binding.weightOutput.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "신장 또는 몸무게를 입력 후 저장버튼을 누르세요.", Toast.LENGTH_SHORT)
                    .show()

            }

            else if(!(binding.manCheckbox.isChecked) && !(binding.womanCheckbox.isChecked)){
                Toast.makeText(requireContext(), "성별을 선택해 주세요.", Toast.LENGTH_SHORT)
                    .show()
            }

            //신장 혹은 몸무게가 0으로 입력된 경우
            else if (binding.heightOutput.text.toString()
                    .toDouble() == 0.0 || binding.weightOutput.text.toString().toDouble() == 0.0
            ) {
                Toast.makeText(requireContext(), "신장 또는 몸무게가 0이 될 수 없습니다.", Toast.LENGTH_SHORT)
                    .show()

            } else {

                if(binding.heightOutput.text.isNotEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "height", binding.heightOutput.text.toString())
                }
                else{
                    SharedPreferenceUtils.saveData(requireContext(), "height", current_h.toString())

                }
                if(binding.weightOutput.text.isNotEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "weight", binding.weightOutput.text.toString())
                }
                else{
                    SharedPreferenceUtils.saveData(requireContext(), "weight", binding.weightOutput.text.toString())

                }

//                if(binding.genderOutput.text.isNotEmpty()){
//                    SharedPreferenceUtils.saveData(requireContext(), "gender", binding.genderOutput.text.toString())
//                }
                if(binding.manCheckbox.isChecked){
                    SharedPreferenceUtils.saveData(requireContext(), "gender", "남성")
                }
                else{
                    SharedPreferenceUtils.saveData(requireContext(), "gender", "여성")
                }


                if(binding.birthdayInput.text.isNotEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "birthday", binding.birthdayInput.text.toString())
                }
//                if(binding.nameOutput.text.isNotEmpty()){
//                    SharedPreferenceUtils.saveData(requireContext(), "name", binding.nameOutput.text.toString())
//                }

//                SharedPreferenceUtils.saveData(requireContext(), "name", binding.nameOutput.text.toString())
//                SharedPreferenceUtils.saveData(requireContext(), "gender", binding.genderOutput.text.toString())
//                SharedPreferenceUtils.saveData(requireContext(), "birthday", binding.birthdayInput.text.toString())
                //bmi 계산

                var weight:Double = (SharedPreferenceUtils.loadData(requireContext(),"weight","")).toDouble()
                var height:Double = (SharedPreferenceUtils.loadData(requireContext(),"height","")).toDouble()


                var calculate_bmi: Double = (weight) / ((height / 100.0) * (height / 100.0)) * 10



                    if(calculate_bmi>300){
                        calculate_bmi=300.0
                    }


                    SharedPreferenceUtils.saveData(requireContext(), "bmi", (calculate_bmi.toString()))


                    //home_height=binding.heightOutput.text.toString().toDouble()
                    //home_weight=binding.weightOutput.text.toString().toDouble()
                    //home_bmi=(home_weight)/((home_height/100.0)*(home_height/100.0))*10
//

                    //println("변경된이후 키:" + home_height)
                    //println("변경된이후 몸무게:" + home_weight)

                    replaceFragment(mypage_body_information())


            }

        }





        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_body_information())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


    }




    private var _binding: FragmentMypageBodyInformationEditmodeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMypageBodyInformationEditmodeBinding.inflate(inflater,container,false)
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





        binding.birthdayInput.hint=birthday
        //binding.genderOutput.hint=gender
        binding.heightOutput.hint=h
        binding.weightOutput.hint=w

        binding.nameOutput2.setText(name)

        println("변경된 w"+w)
        println("변경된 h"+h)
    }

    companion object {

    }
}