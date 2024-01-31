package com.example.alomtest.food.mainpage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentFoodBinding
import com.example.alomtest.food.foodcustom01.AddActivity


class food : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList:ArrayList<DataClass>
    lateinit var titleList:Array<String>
    private lateinit var adapter: AdapterClass



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
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : AdapterClass.OnItemClickListener {
            override fun onItemClick(position:Int){

                if(position == adapter.itemCount-1){
                    val intent = Intent(requireContext(), AddActivity::class.java)
                    requestLauncher.launch(intent)
                }
            }
        }

        )


    }

    override fun onResume() {
        super.onResume()


    }
//before
    private val requestLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode== AddActivity.RESULT_ADD_TASK){
                val newTask = result.data?.getStringExtra("newTask")
                newTask?.let{
                    dataList.add(DataClass(it))
                    adapter.notifyItemInserted(dataList.size-1)
                }
            }
        }


//

    private fun getData(){
        for(i in titleList.indices){
            val dataClass = DataClass(titleList[i])
            dataList.add(dataClass)
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null

    }    }