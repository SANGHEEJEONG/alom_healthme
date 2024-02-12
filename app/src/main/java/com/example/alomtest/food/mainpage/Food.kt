package com.example.alomtest.food.mainpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentFoodBinding
import com.example.alomtest.food.foodcustom01.AddActivity
import com.example.alomtest.food.foodcustom01.AddActivity.Companion.RESULT_ADD_TASK
import com.example.alomtest.food.foodcustom02.FoodEditActivity


class Food : Fragment() {
    private val SharedViewModelFoodcal: SharedViewModelFoodcal by activityViewModels()
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList:ArrayList<DataClass>
    lateinit var titleList:Array<String>
    private lateinit var adapter: AdapterClass

//    private val editActivityResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == EditActivity.RESULT_EDIT_TASK) {
//                val editedDataTitle = result.data?.getStringExtra("editedDataTitle")
//                handleEditResult(editedDataTitle)
//            }
//        }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFoodBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleList = arrayOf(

        )

        recyclerView = view.findViewById(R.id.rv_food)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()

        adapter = AdapterClass(dataList)

        adapter.setOnReviseClickListener(object: AdapterClass.OnReviseClickListener{
            override fun ReviseClick(position: Int) {
                if (position >= 0 && position < dataList.size){
                    val intent = Intent(requireContext(), FoodEditActivity::class.java)
                    intent.putExtra("dataTitle", dataList[position].dataTitle)
                    intent.putExtra("position", position)
                    requestEditLauncher.launch(intent)
                }}

        })





        adapter.setOnFooterClickListener(object : AdapterClass.OnFooterClickListener {
            override fun FooterClick(position:Int){

                if(position == adapter.itemCount-1){
                    val intent = Intent(requireContext(), AddActivity::class.java)
                    requestLauncher.launch(intent)
                }
            }
        }

        )
        adapter.setOnDeleteClickListener(object:AdapterClass.OnDeleteClickListener{
            override fun onDeleteClick(position: Int) {
                // 이 곳에 삭제 이벤트를 처리하는 코드를 추가하세요
                dataList.removeAt(position)
                adapter.notifyItemRemoved(position)
                updateTotalCalories()
            }
        })

        recyclerView.adapter = adapter

        updateTotalCalories()



    }







    override fun onResume() {
        super.onResume()


    }



    private val requestEditLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == FoodEditActivity.RESULT_EDIT_TASK) {
            handleEditResult(result.data)
        }
    }
    private fun handleEditResult(result: Intent?) {
        val position = result?.getIntExtra("position", -1) ?: -1
        val editedDataTitle = result?.getStringExtra("editedDataTitle")
        val editfoodname = result?.getStringExtra("editfoodname")
        val edittimeFormat = result?.getStringExtra("edittimeFormat")
        val editcalories = result?.getIntExtra("editcalories", 0) ?: 0


        if (position != -1 && editedDataTitle != null) {

            // 데이터 갱신
            dataList[position].dataTitle = editedDataTitle
            dataList[position].foodSelect = editfoodname.orEmpty()
            dataList[position].foodTime = edittimeFormat.orEmpty()
            dataList[position].calories = editcalories

            adapter.notifyItemChanged(position)
            updateTotalCalories()
        }
    }
    //before
    private val requestLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode== AddActivity.RESULT_ADD_TASK){
                val newTask = result.data?.getStringExtra("newTask")
                val foodName = result.data?.getStringExtra("foodname")
                val timeFormat = result.data?.getStringExtra("timeFormat")
                val calories = result.data?.getIntExtra("calories", 0) ?: 0
                newTask?.let{
                    dataList.add(DataClass(it, foodName.orEmpty(), timeFormat.orEmpty(),calories))
                    adapter.notifyItemInserted(dataList.size-1)
                    updateTotalCalories()
                }
            }
        }







    private fun getData(){
        for(i in titleList.indices){
            val dataClass = DataClass(titleList[i])
            dataList.add(dataClass)
        }
    }
    private fun calculateTotalCalories(): Int {
        var totalCalories = 0

        for (data in dataList) {
            val calculatedCalories = if (data.dataTitle != "") {
                (data.calories * 0.01 * data.dataTitle.toInt()).toInt()
            } else {
                data.calories
            }
            totalCalories += calculatedCalories
        }
        return totalCalories
    }
    private fun updateTotalCalories() {
        val totalCalories = calculateTotalCalories()
        SharedViewModelFoodcal.totalCalories = totalCalories
        binding.todaycalories.text = calculateTotalCalories().toString()
        binding.leftcalories.text=(2500 - calculateTotalCalories()).toString()
        binding.extracalories.text=(-2500 + calculateTotalCalories()).toString()
        if(calculateTotalCalories()>2500){
            binding.leftcalories.visibility=View.GONE
            binding.extracalories.visibility=View.VISIBLE
            binding.beforefoodcal.visibility=View.GONE
            binding.afterfoodcal.visibility=View.VISIBLE
        }else{
            binding.leftcalories.visibility=View.VISIBLE
            binding.extracalories.visibility=View.GONE
            binding.beforefoodcal.visibility=View.VISIBLE
            binding.afterfoodcal.visibility=View.GONE}
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null

    }

//    private fun findPositionByDataTitle(dataTitle: String?): Int {
//        for (i in dataList.indices) {
//            if (dataList[i].dataTitle == dataTitle) {
//                return i
//            }
//        }
//        return -1
//    }
}
class SharedViewModelFoodcal : ViewModel() {
    var totalCalories: Int = 0
}

