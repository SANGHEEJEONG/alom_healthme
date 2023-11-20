package com.example.alomtest

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.Home.Companion.home_bmi
import com.example.alomtest.Home.Companion.home_height
import com.example.alomtest.Home.Companion.home_weight
import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FragmentMypageMainBinding
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementBinding


class mypage_main : Fragment() {
    private lateinit var binding: FragmentMypageMainBinding
    private lateinit var binding2 : ActivityMainBinding //bottomnav 조종용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //적절한 페이지(Fragment)로 이동
        binding = FragmentMypageMainBinding.inflate(layoutInflater)
        binding2 = ActivityMainBinding.inflate(layoutInflater)
        //
        binding.bodyMeasurementBtn3.setOnClickListener {
            replaceFragment(mypage_body_measurement())
        }
        binding.bodyMeasurementBtn.setOnClickListener {
            replaceFragment(mypage_body_measurement())
        }
        binding.bodyMeasurementBtn2.setOnClickListener {
            replaceFragment(mypage_body_measurement())
        }

        //건강 세부사항
        binding.bodyMeasurementBtn4.setOnClickListener {
            replaceFragment(mypage_health_detail())
        }
        binding.bodyMeasurementBtn5.setOnClickListener {
            replaceFragment(mypage_health_detail())
        }
        binding.bodyMeasurementBtn6.setOnClickListener {
            replaceFragment(mypage_health_detail())
        }
        binding.backiconBtn.setOnClickListener {
            binding2.bottomNavigationView.selectedItemId=R.id.home
            replaceFragment(Home())

        }


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
        val w:String="${"%.1f".format(home_weight)}"
        val h:String="${"%.1f".format(home_height)}"
        val b: String = "${"%.2f".format(home_bmi/10)}"



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


        val circle_background = pxToDp(this,getScreenHeight(this))

        val background=binding.circleBackground

        var background_param=background.layoutParams
        background_param.height=circle_background.toInt()
        background.layoutParams=background_param



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