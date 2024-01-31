package com.example.alomtest.food.foodcustom01

import SharedPreferenceUtils
import android.widget.SearchView
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.ActivityAddBinding
import com.example.alomtest.food.mainpage.food
import com.example.alomtest.retrofit.Api

import com.google.android.material.card.MaterialCardView
import org.json.JSONObject
import java.util.Locale

class AddActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var cardView : MaterialCardView
    private lateinit var imageView1 : ImageView
    private lateinit var expandableLayout: View
    private lateinit var expandableLayout2 : View
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    private lateinit var binding: ActivityAddBinding
    private lateinit var expandBtn: Button
    private var isImage1Visible = true

    companion object {
        const val RESULT_ADD_TASK = 123 // Any unique value
    }
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_add)

        cardView = findViewById(R.id.materialCardView)
        recyclerView = findViewById(R.id.recyclerViewfood2)
        searchView = findViewById(R.id.searchViewfood)
        expandableLayout = binding.expandableLayout
        expandableLayout2 = binding.expandableLayout2
        expandBtn = binding.expandBtn
        imageView1 = findViewById(R.id.foodimageView)
        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(this)
        val linearLayoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerViewfood2).layoutManager = linearLayoutManager
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter

        var usertoken = SharedPreferenceUtils.loadData(this@AddActivity, "accessToken", "")





//        var current_idx:Int = 0
//
//        //retrofit 넣을 곳
//
//        val jsonObject= JSONObject()
//        jsonObject.put("idx",current_idx)
//
//
//
//
//        val api = Api.create()
//        api.load_food(accessToken = "Bearer $usertoken"),


















        addDataToList()
        if (searchView!=null){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })}

   findViewById<Button>(R.id.add_next).setOnClickListener{

            val newTask = findViewById<EditText>(R.id.add_edit).text.toString()

            val resultintent = Intent(this, food::class.java)
            resultintent.putExtra("newTask", newTask)
            setResult(RESULT_ADD_TASK, resultintent)
            finish()
        }
        expandableLayout.visibility = View.GONE // 초기에 화면에 보이지 않도록 설정
        cardView.visibility = View.GONE


   findViewById<Button>(R.id.expandBtn).setOnClickListener {
       runOnUiThread {
            toggleImage()

            if (expandableLayout.visibility == View.GONE) {
                expandableLayout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE

            } else {
                expandableLayout.visibility = View.GONE
                cardView.visibility = View.GONE

            }
        }}

        findViewById<Button>(R.id.expandBtn2).setOnClickListener {
            if (expandableLayout2.visibility == View.GONE) {
                expandableLayout2.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                expandableLayout2.visibility = View.GONE
            }
        }

        deleteData()
        fun toggleExpandableLayout(clickedItemTitle: String) {

            //text를 foodname으로 바꾸는 함수

        }

        // 리스트 아이템 클릭 이벤트 처리
        adapter.setOnItemClickListener { foodname ->
            // name: 클릭된 이름
            toggleExpandableLayout(foodname)

            if (expandableLayout.visibility == View.GONE) {
                print("$foodname")
                expandableLayout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                print("$foodname")
                expandableLayout.visibility = View.GONE
                cardView.visibility = View.GONE
            }


    }}



    private fun addDataToList() {
        val nextPosition = mList.size // 다음 위치는 현재 리스트의 크기와 같습니다.
        mList.add(FoodData("피자"))
        mList.add(FoodData("치킨"))
        mList.add(FoodData("떡볶이"))
        mList.add(FoodData("김치볶음밥"))
        mList.add(FoodData("짜장면"))
        mList.add(FoodData("짬뽕"))
        mList.add(FoodData("탕수육"))
        mList.add(FoodData("초코우유"))
        mList.add(FoodData("딸기우유"))

        // 새로운 음식을 현재 리스트의 다음 위치에 추가
        mList.add(nextPosition, FoodData("새로운음식"))
        adapter.notifyDataSetChanged()


    }
private fun filterList(query: String?) {
    if (query != null) {
        val filteredList = ArrayList<FoodData>()

        for (i in mList) {
            if (i.title.lowercase(Locale.ROOT).contains(query)) {
                filteredList.add(i)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "검색 결과 없음", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setFilteredList(filteredList)
        }
    }
}


    @RequiresApi(Build.VERSION_CODES.M)

    private fun toggleImage() {
        if (isImage1Visible) {
            imageView1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.group_124))

        } else {
            imageView1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.group_126))
        }

        isImage1Visible = !isImage1Visible
        imageView1.requestLayout()
        imageView1.invalidate()
    }

    private fun deleteData(){

        val swipegesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction:Int){
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        adapter.deleteItem(viewHolder.absoluteAdapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView((recyclerView))
    }


//    override fun onPause() {
//        super.onPause()
//        adapter.saveData(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        adapter.loadData(this)
//    }




       // fun getgramFromEditText(): Int {
        //    val gramText = gramEditText.text.toString()
         //   return if (gramText.isNotEmpty()) {
         //       gramText.toInt()
          //  } else {
                // 기본값 또는 에러 처리를 원하는 대로 설정
         //       0
         //   }
      //  }

       // fun getTimeFromTimePicker(): String {
       //     val hour = if (Build.VERSION.SDK_INT >= 23) {
       //         timePicker.hour
       //     } else {
       //         timePicker.currentHour
       //     }

        //    val minute = if (Build.VERSION.SDK_INT >= 23) {
        //        timePicker.minute
        //    } else {
        //        timePicker.currentMinute
        //    }

       //     // 시간을 원하는 형식으로 포맷팅
       //     return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
      //  }
}