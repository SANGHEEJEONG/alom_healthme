package com.example.alomtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter


class exercise_list_adpater(val customList:ArrayList<exerciseData>): Adapter<exercise_list_adpater.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exercise_list_adpater.CustomViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.custom_exercise_list,parent,false)//context는 activity에서 담고 있는 모든 정보
        return CustomViewHolder(View).apply {

            itemView.setOnClickListener {
                val curPos = adapterPosition
                val exerciseList = customList.get(curPos)



            }
        }
    }

    override fun onBindViewHolder(holder: exercise_list_adpater.CustomViewHolder, position: Int) {//실질적으로 생성된 뷰를 연결해주는 것
        //holder.image.setImageResource(customList.get(position))
        holder.name.text = customList.get(position).exerciseName
        holder.detail.text=customList.get(position).exerciseDetail
    }

    override fun getItemCount(): Int { //list들에 대한 총 개수
        return customList.size //footer를 위한 사이즈 추가
    }

    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) { //뭔가를 잡아주는
        val name = itemView.findViewById<TextView>(R.id.exercise_name)
        val detail=itemView.findViewById<TextView>(R.id.exercise_detail)
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