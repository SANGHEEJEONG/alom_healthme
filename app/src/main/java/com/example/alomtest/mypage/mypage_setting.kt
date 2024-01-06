package com.example.alomtest.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentMypageSettingBinding


class mypage_setting : Fragment() {
    private lateinit var binding: FragmentMypageSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageSettingBinding.inflate((layoutInflater))

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_main())
        }
        binding.editModeIcon.setOnClickListener {
            replaceFragment(mypage_setting_editmode())
        }
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_main())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)




        val name_edittext=binding.nameOutput2
        name_edittext.keyListener = null
        val email_edittext=binding.emailOutput
        email_edittext.keyListener = null

    }
    private var _binding: FragmentMypageSettingBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMypageSettingBinding.inflate(inflater,container,false)
        return binding.root
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
        val name:String= SharedPreferenceUtils.loadData(requireContext(), "name", "")
        val email:String= SharedPreferenceUtils.loadData(requireContext(), "email", "")



        binding.nameOutput.setText(name)
        binding.nameOutput2.setText(name)
        binding.emailOutput.setText(email)


    }
    companion object {

    }
}