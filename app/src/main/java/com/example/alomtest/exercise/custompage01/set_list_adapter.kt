package com.example.alomtest.exercise.custompage01

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.databinding.CustomExerciseSetListBinding
import com.example.alomtest.databinding.RoutineItemExampleBinding
import com.example.alomtest.databinding.RoutineItemFooterBinding
import com.example.alomtest.exercise.mainpage.exercise_routine_adapter
import com.example.alomtest.exercise.mainpage.exercise_routine_profile

class set_list_adapter(val context: Context,val setlist:ArrayList<set_list_item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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
                exercise_routine_adapter.exercise_routine_viewholder(binding)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val binding = RoutineItemFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                exercise_routine_adapter.exercise_routine_footer_viewholder(context, binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return setlist.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_NORMAL -> {
//                val setlistHolder = holder as set_list_adapter.set_list_viewholder
//                setlistHolder.bind(setlist[position])
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                // Footer 처리
            }
        }


        //holder.bind(routine[position])
    }
//    class set_list_viewholder(private val binding: CustomExerciseSetListBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
//        fun bind(setList: set_list_item){
//
//            binding.exerciseCnt.text="루틴 ${routine.cnt}개"
//            binding.exerciseTitle.text=routine.nane
//        }
//    }

}