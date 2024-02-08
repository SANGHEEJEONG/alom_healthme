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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.MyViewModel
import com.example.alomtest.R

import com.example.alomtest.databinding.CustomExerciseSetListBinding
import com.example.alomtest.databinding.SetItemFooterBinding

import com.example.alomtest.exercise.mainpage.exercise_routine_adapter
import com.example.alomtest.exercise.mainpage.exercise_routine_profile

class set_list_adapter( val context: Context, val setlist:ArrayList<set_list_item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //private lateinit var viewmodel : MyViewModel
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
                set_list_adapter.set_list_viewholder(setlist,binding)
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
        //viewmodel = ViewModelProvider(this).get(MyViewModel::class.java)

        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val setlistHolder = holder as set_list_adapter.set_list_viewholder
                setlistHolder.bind(setlist[position],position)

//                setlistHolder.itemView.setOnClickListener {
//                    onMinusClickListener?.invoke()
//                    Log.d("삭제직전", setlist.toString())
//                    Log.d("삭제할 인덱스",position.toString())
//                    setlist.removeAt(position)
//                    Log.d("삭제이후", setlist.toString())
//
//                    notifyDataSetChanged()
//                }
                setlistHolder.binding.minusBtn.setOnClickListener {
                    onMinusClickListener?.invoke()
                    Log.d("삭제직전", setlist.toString())
                    Log.d("삭제할 인덱스",position.toString())

                    //setlist.removeAt(position)

                    for (i:Int in position until setlist.size-1){
                        setlist[i]=setlist[i+1]



                    }

                    setlist.removeAt(setlist.size-1)

                    notifyItemRemoved(1)



                    Log.d("삭제이후", setlist.toString())

//                    if(setlistHolder.setlist.size==0){
//
//                        viewmodel._myList.value?.removeAt(position)
//
//                    }



                    Log.d("세트리스트 사이즈", setlist.size.toString())
                    for(i:Int in 0 until setlist.size){
                        //setlistHolder.binding.setNo.text="* ${i+1}세트 | "
                        setlistHolder.binding.setNo.setText("* ${i+1}세트 | ")
                        setlistHolder.binding.weight.setText("${setlist[i].weight}")
                        setlistHolder.binding.cnt.setText("${setlist[i].cnt}")



                    }




                    notifyDataSetChanged()
                    //notifyItemRemoved(position)






                }


                //setlist[position]
                //setlistHolder.bind(setlist[position].)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val footerHolder = holder as set_list_adapter.set_list_footer_viewholder

//                footerHolder.itemView.setOnClickListener {
//                    onFooterClickListener?.invoke()
//                    setlist.add(set_list_item(1,1))
//                    notifyDataSetChanged()
//                }
                footerHolder.binding.footerIcon.setOnClickListener {
                    onFooterClickListener?.invoke()
                    setlist.add(set_list_item(0,0))

                    //notifyItemInserted(setlist.size)
                    notifyDataSetChanged()
                }





            }
        }
    }
    class set_list_viewholder(val setlist:ArrayList<set_list_item>, val binding: CustomExerciseSetListBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
        fun bind(setList: set_list_item, idx:Int){
            binding.setNo.text="* ${idx+1}세트 | "
            //setlist[idx].set=idx+1




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

    class set_list_footer_viewholder(val binding: SetItemFooterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {

            Log.d("footer click", "footer클릭")

            }
        }

        // Footer에 필요한 뷰 바인딩 등을 처리
    }


}