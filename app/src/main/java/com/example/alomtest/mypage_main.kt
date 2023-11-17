package com.example.alomtest

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.databinding.FragmentMypageMainBinding
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mypage_main.newInstance] factory method to
 * create an instance of this fragment.
 */
class mypage_main : Fragment() {
    private lateinit var binding: FragmentMypageMainBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //적절한 페이지(Fragment)로 이동
        binding = FragmentMypageMainBinding.inflate((layoutInflater))
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



    }
    //binding을 위한 코드
    private var _binding: FragmentMypageMainBinding? = null





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMypageMainBinding.inflate(inflater,container,false)
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
    fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")
    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mypage_main.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mypage_main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}