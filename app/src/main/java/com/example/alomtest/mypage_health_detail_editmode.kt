package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.databinding.FragmentMypageHealthDetailBinding
import com.example.alomtest.databinding.FragmentMypageHealthDetailEditmodeBinding


class mypage_health_detail_editmode : Fragment() {

    private lateinit var binding:FragmentMypageHealthDetailEditmodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=FragmentMypageHealthDetailEditmodeBinding.inflate(layoutInflater)

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_health_detail())
        }




        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_health_detail())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


    }




    private var _binding: FragmentMypageHealthDetailEditmodeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMypageHealthDetailEditmodeBinding.inflate(inflater,container,false)
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