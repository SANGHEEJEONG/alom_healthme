package com.example.alomtest.mypage

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.home.Home
import com.example.alomtest.R

import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FragmentMypageMainBinding
import kotlin.system.exitProcess


class mypage_main : Fragment() {
    private lateinit var binding: FragmentMypageMainBinding
    private lateinit var binding2 : ActivityMainBinding //bottomnav 조종용
    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //적절한 페이지(Fragment)로 이동
        binding = FragmentMypageMainBinding.inflate(layoutInflater)
        binding2 = ActivityMainBinding.inflate(layoutInflater)
        //
        binding.bodyMeasurementBtn3.setOnClickListener {
            replaceFragment(mypage_body_information())
        }
        binding.bodyMeasurementBtn.setOnClickListener {
            replaceFragment(mypage_body_information())
        }
        binding.bodyMeasurementBtn2.setOnClickListener {
            replaceFragment(mypage_body_information())
        }

        //건강 세부사항
        binding.bodyMeasurementBtn4.setOnClickListener {
            replaceFragment(mypage_setting())
        }
        binding.bodyMeasurementBtn5.setOnClickListener {
            replaceFragment(mypage_setting())
        }
        binding.bodyMeasurementBtn6.setOnClickListener {
            replaceFragment(mypage_setting())
        }

        binding.bodyMeasurementBtn10.setOnClickListener {
            Toast.makeText(requireContext(), "HealthMe 앱버전 1.0 beta", Toast.LENGTH_SHORT).show()
        }
        binding.bodyMeasurementBtn11.setOnClickListener {
            Toast.makeText(requireContext(), "HealthMe 앱버전 1.0 beta", Toast.LENGTH_SHORT).show()
        }
        binding.bodyMeasurementBtn12.setOnClickListener {
            Toast.makeText(requireContext(), "HealthMe 앱버전 1.0 beta", Toast.LENGTH_SHORT).show()
        }

        binding.backiconBtn.setOnClickListener {
            binding2.bottomNavigationView.selectedItemId= R.id.home
            replaceFragment(Home())

        }

        //2번 뒤로가기 누르면 앱 완전 종료
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()

                if (currentTime - backPressedTime > 2000) {
                    // 첫 번째 뒤로가기
                    backPressedTime = currentTime
                    // 3초 안에 두 번 뒤로가기를 누르면 앱 종료

                    Toast.makeText(requireContext(), "버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        backPressedTime = 0
                    }, 3000)
                } else {
                    // 두 번째 뒤로가기
                    requireActivity().finish()
                    exitProcess(0)
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)





    }
    //binding을 위한 코드
    private var _binding: FragmentMypageMainBinding? = null
    private var _binding2:ActivityMainBinding?=null






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMypageMainBinding.inflate(inflater,container,false)
        _binding2=ActivityMainBinding.inflate(inflater,container,false)
        return binding.root
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_mypage_main, container, false)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeinstance = Home()

//        val w:String="${"%.1f".format(homeinstance.weight)}"
//        val h:String="${"%.1f".format(homeinstance.height)}"
//        val b: String = "${"%.2f".format(homeinstance.bmi/10)}"

        val weight: String = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
        //val w:String=home_weight.toString()
        //val h:String= home_height.toString()
        val height: String = SharedPreferenceUtils.loadData(requireContext(), "height", "")
        val bmi: String = SharedPreferenceUtils.loadData(requireContext(), "bmi", "")


        val w:String="${"%.1f".format(weight.toDouble())}"
        val h:String="${"%.1f".format(height.toDouble())}"
        val b: String = "${"%.2f".format(bmi.toDouble()/10)}"



        binding.height.text = h
        binding.weight.text = w
        binding.bmi.text=b






        fun getScreenHeight(context: mypage_main): Int {
            val displayMetrics = DisplayMetrics()

            var phoneheight=Resources.getSystem().displayMetrics.heightPixels

            return phoneheight
        }







        //픽셀을 dp로 변경
        fun pxToDp(context: mypage_main, px: Int): Float {
            val displayMetrics = context.resources.displayMetrics
            return px / displayMetrics.density
        }







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