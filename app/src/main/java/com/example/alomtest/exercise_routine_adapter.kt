package com.example.alomtest

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.databinding.RoutineItemExampleBinding
import com.example.alomtest.databinding.RoutineItemFooterBinding

class exercise_routine_adapter(private val routine:List<exercise_routine_profile>):RecyclerView.Adapter<RecyclerView.ViewHolder>() { //어댑터는 데이터를 그려주는 역할
    private val ITEM_VIEW_TYPE_NORMAL = 0
    private val ITEM_VIEW_TYPE_FOOTER = 1




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val binding = RoutineItemExampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                exercise_routine_viewholder(binding)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val binding = RoutineItemFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                exercise_routine_footer_viewholder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }




    override fun getItemCount(): Int {
        return routine.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val routineHolder = holder as exercise_routine_viewholder
                routineHolder.bind(routine[position])
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                // Footer 처리
            }
        }


    //holder.bind(routine[position])
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ITEM_VIEW_TYPE_FOOTER
        } else {
            ITEM_VIEW_TYPE_NORMAL
        }
    }

    // Footer를 위한 ViewHolder 클래스 추가
    class exercise_routine_footer_viewholder(private val binding: RoutineItemFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {



            }
        }

        // Footer에 필요한 뷰 바인딩 등을 처리
    }
    class exercise_routine_viewholder(private val binding: RoutineItemExampleBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
        fun bind(routine:exercise_routine_profile){

            binding.exerciseCnt.text="루틴 ${routine.cnt}개"
            binding.exerciseTitle.text=routine.nane
        }
    }


}
