package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.databinding.FragmentMypageMainBinding
import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FragmentMypageBodyMeasurementBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mypage_body_measurement.newInstance] factory method to
 * create an instance of this fragment.
 */
class mypage_body_measurement : Fragment() {
    private lateinit var binding: FragmentMypageBodyMeasurementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageBodyMeasurementBinding.inflate((layoutInflater))


        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_main())
        }

//        binding.backiconBtn2.setOnClickListener {
//            replaceFragment(mypage_main())
//        }


    }
    //binding을 위한 코드
    private var _binding: FragmentMypageBodyMeasurementBinding? = null

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//
//
//
//        return inflater.inflate(R.layout.fragment_mypage_body_measurement, container, false)
//    }
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = FragmentMypageBodyMeasurementBinding.inflate(inflater,container,false)
    return binding.root
}

    private fun replaceFragment(fragment: Fragment){
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
         * @return A new instance of fragment mypage_body_measurement.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mypage_body_measurement().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}