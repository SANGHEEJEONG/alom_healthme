package com.example.alomtest.exercise.custompage01

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.CustomExerciseSetListBinding
import com.example.alomtest.databinding.RoutineItemExampleBinding
import com.example.alomtest.databinding.RoutineItemFooterBinding
import com.example.alomtest.databinding.SetItemFooterBinding
import com.example.alomtest.exercise.mainpage.exercise_routine_adapter
import com.example.alomtest.exercise.mainpage.exercise_routine_profile

class set_list_adapter(val context: Context,val setlist:ArrayList<set_list_item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val ITEM_VIEW_TYPE_NORMAL = 0
    private val ITEM_VIEW_TYPE_FOOTER = 1
    var onFooterClickListener: (() -> Unit)? = null
    var onMinusClickListener: (() -> Unit)?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val binding = CustomExerciseSetListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                set_list_adapter.set_list_viewholder(binding)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val binding = SetItemFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                set_list_adapter.set_list_footer_viewholder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ITEM_VIEW_TYPE_FOOTER
        } else {
            ITEM_VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return setlist.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val setlistHolder = holder as set_list_adapter.set_list_viewholder
                setlistHolder.bind(setlist[position],position)

                setlistHolder.itemView.setOnClickListener {
                    onMinusClickListener?.invoke()
                    Log.d("삭제직전", setlist.toString())
                    Log.d("삭제할 인덱스",position.toString())
                    setlist.removeAt(position)
                    Log.d("삭제이후", setlist.toString())

                    notifyDataSetChanged()
                }






                //setlist[position]
                //setlistHolder.bind(setlist[position].)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val footerHolder = holder as set_list_adapter.set_list_footer_viewholder

                footerHolder.itemView.setOnClickListener {
                    onFooterClickListener?.invoke()
                    setlist.add(set_list_item(1,1))

                    notifyDataSetChanged()
                }
            }
        }
    }
    class set_list_viewholder(private val binding: CustomExerciseSetListBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
        fun bind(setList: set_list_item, idx:Int){
            binding.setNo.text="* ${idx+1}세트 | "


            binding.weight.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    //binding.weight.setText(s.toString())
                    setList.weight=s.toString().toInt()

                }

            })


            binding.cnt.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    //binding.weight.setText(s.toString())
                    setList.cnt=s.toString().toInt()

                }

            })




        }
    }



    class set_list_footer_viewholder(private val binding: SetItemFooterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {

            Log.d("footer click", "footer클릭")

            }
        }

        // Footer에 필요한 뷰 바인딩 등을 처리
    }


}