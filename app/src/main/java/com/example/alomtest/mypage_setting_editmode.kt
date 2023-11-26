package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.databinding.FragmentMypageSettingBinding
import com.example.alomtest.databinding.FragmentMypageSettingEditmodeBinding


class mypage_setting_editmode : Fragment() {
    private lateinit var binding: FragmentMypageSettingEditmodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageSettingEditmodeBinding.inflate((layoutInflater))

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_setting())
        }
        binding.savemode.setOnClickListener {
            replaceFragment(mypage_setting())
        }
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_setting())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private var _binding: FragmentMypageSettingEditmodeBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMypageSettingEditmodeBinding.inflate(inflater,container,false)
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




        binding.nameOutput.setText(name)


    }
    companion object {


    }
}