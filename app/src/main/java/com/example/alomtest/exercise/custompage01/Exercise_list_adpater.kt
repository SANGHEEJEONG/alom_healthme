package com.example.alomtest.exercise.custompage01

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.alomtest.R
import com.example.alomtest.databinding.CustomExerciseListCopyBinding
import com.example.alomtest.exerciseData


class exercise_list_adpater(val customList:ArrayList<exerciseData>): Adapter<exercise_list_adpater.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //val View = LayoutInflater.from(parent.context).inflate(R.layout.custom_exercise_list_copy,parent,false)//context는 activity에서 담고 있는 모든 정보
        val view = CustomExerciseListCopyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomViewHolder(view).apply {

            itemView.setOnClickListener {
                val curPos = adapterPosition
                val exerciseList = customList.get(curPos)

            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {//실질적으로 생성된 뷰를 연결해주는 것
        //holder.image.setImageResource(customLisgt.get(position))
        holder.name.text = customList.get(position).exerciseName
        holder.detail.text=customList.get(position).exerciseDetail








        holder.bind(position)


        //val child =



    }

    override fun getItemCount(): Int { //list들에 대한 총 개수
        return customList.size //footer를 위한 사이즈 추가
    }

    inner class CustomViewHolder(private val binding : CustomExerciseListCopyBinding):RecyclerView.ViewHolder(binding.root) { //뭔가를 잡아주는


        fun bind(pos:Int){
            binding.setRecyclerview.apply {
                adapter = set_list_adapter(context,/*ArrayList<set_list_item>()*/customList[pos].set_list )
                Log.d("커스텀리스트출력", customList.toString())

                layoutManager = LinearLayoutManager(binding.setRecyclerview.context,
                    LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }

        }
        val name = itemView.findViewById<TextView>(R.id.exercise_name)
        val detail=itemView.findViewById<TextView>(R.id.exercise_detail)
        //val set=itemView.findViewById<ArrayList>(R.id.)

        //val image=itemView.findViewById<ImageView>(R.id.exercise_img)
    }
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = parentFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout,fragment)
//        fragmentTransaction.commit()
//        println("success")
//
//
//    }
}