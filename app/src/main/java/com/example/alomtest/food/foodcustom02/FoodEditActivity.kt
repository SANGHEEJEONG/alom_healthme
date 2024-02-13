package com.example.alomtest.food.foodcustom02

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import java.util.Locale
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.alomtest.R
import com.example.alomtest.databinding.ActivityFoodEditBinding
import com.example.alomtest.food.foodcustom01.FoodAdapter
import com.example.alomtest.food.foodcustom01.FoodData
import com.example.alomtest.food.foodcustom01.SwipeGesture
import com.example.alomtest.food.mainpage.Food
import com.google.android.material.snackbar.Snackbar

class FoodEditActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var cardView : MaterialCardView
    private lateinit var cardview_time : MaterialCardView
    private lateinit var foodimageView : ImageView
    private lateinit var timeimageView : ImageView
    private lateinit var expandableLayout: View
    private lateinit var expandableLayout_time: View
    private val deletedItems: MutableList<String> = mutableListOf()
    private var mList: MutableList<FoodData> = mutableListOf()
    private lateinit var adapter: FoodAdapter
    private lateinit var binding: ActivityFoodEditBinding
    private lateinit var expandBtn: Button
    private lateinit var expandBtn_time: Button
    private lateinit var foodaddBtn: Button
    private var isImage1Visible = true
    private var isImage2Visible = true
    private lateinit var gramEditText: EditText
    private lateinit var foodAddEditText: EditText
    private lateinit var kcalAddEditText: EditText
    private lateinit var timePicker: TimePicker
    companion object {
        const val RESULT_EDIT_TASK = 456 // Any unique value
        const val RESULT_EDIT_BACK_TASK = 654
    }



    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodEditBinding.inflate(layoutInflater)

        setContentView(binding.root)



        cardView = binding.materialCardView
        cardview_time = binding.materialCardViewTime
        recyclerView = binding.recyclerViewfood2
        searchView = binding.searchViewfood
        expandableLayout = binding.expandableLayout
        expandableLayout_time = binding.expandableLayoutTime
        expandBtn = binding.expandBtnEdit
        expandBtn_time = binding.expandBtnTimeEdit
        foodaddBtn = binding.expandBtn2
        foodimageView = binding.foodimageView
        timeimageView = binding.timeimageView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter
        gramEditText = findViewById(R.id.food_edit_edit)
        foodAddEditText = findViewById(R.id.foodAddEditText)
        kcalAddEditText = findViewById(R.id.kcalAddEditText)
        timePicker = findViewById(R.id.timePicker)


        loadSavedData()

        // loadData()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        binding.foodEditBack.setOnClickListener(){
            val backintent = Intent()
            setResult(RESULT_EDIT_BACK_TASK,backintent)
            finish()
        }

        binding.foodEditNext.setOnClickListener() {
            val editedDataTitle = findViewById<EditText>(R.id.food_edit_edit).text.toString()
            val calories = mList.find { it.title == expandBtn.text.toString() }?.calories ?: 0
            if(expandBtn.text.toString()=="선택하기" || expandBtn_time.text.toString()=="선택하기"){
                Snackbar.make(it, "정보를 모두 입력해주세요!", Snackbar.LENGTH_SHORT).show()
            }else{
            val resultIntent = Intent()
            resultIntent.putExtra("editedDataTitle", editedDataTitle)
            resultIntent.putExtra("editfoodname",expandBtn.text.toString())
            resultIntent.putExtra("edittimeFormat",expandBtn_time.text.toString())
            resultIntent.putExtra("editcalories",calories)
            resultIntent.putExtra("position", intent.getIntExtra("position", -1))
            setResult(RESULT_EDIT_TASK, resultIntent)
            finish()}
        }

        binding.expandBtnEdit.setOnClickListener {
            toggleImage()

            if (expandableLayout.visibility == View.GONE) {
                expandableLayout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
                cardView.visibility = View.GONE
            }

        }

        binding.expandBtnTimeEdit.setOnClickListener {
            toggleImageTime()

            if (expandableLayout_time.visibility == View.GONE) {
                expandableLayout_time.visibility = View.VISIBLE
                cardview_time.visibility = View.VISIBLE
            } else {
                expandableLayout_time.visibility = View.GONE
                cardview_time.visibility = View.GONE
            }
        }

        binding.expandBtn2.setOnClickListener {
            addFoodToList()
        }

        deleteData()

        // 리스트 아이템 클릭 이벤트 처리
        adapter.setOnItemClickListener { foodName ->
            toggleExpandableLayout(foodName)

            expandableLayout.visibility = View.GONE
            cardView.visibility = View.GONE
        }

        binding.timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            val timeFormat = if (hour > 12) {
                val adjustedHour = hour - 12
                "오후 $adjustedHour 시 $minute 분"
            } else {
                "오전 $hour 시 $minute 분"
            }

            binding.expandBtnTimeEdit.text = timeFormat
            expandBtn_time.setTextColor(Color.parseColor("#000000"))
        }

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
                //Toast.makeText(this, "검색 결과 없음", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    private fun addFoodToList(nextPosition: Int? = null) {
        val userInput = foodAddEditText.text.toString().trim()
        val userInput2 = kcalAddEditText.text.toString().trim()

        if (userInput.isNotEmpty()&&userInput2.isNotEmpty()) {
            try {
                val newFood = FoodData(userInput, userInput2.toInt())
                mList.add(newFood)
                adapter.notifyItemInserted(mList.size - 1)
                saveFoodListToSharedPreferences(mList, mutableListOf())
            } catch (e: NumberFormatException) {
                // 변환 실패 처리
                // 사용자가 정수로 변환할 수 없는 값을 입력한 경우 예외가 발생합니다.
            }
        }
        foodAddEditText.text.clear()
        kcalAddEditText.text.clear()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleImage() {
        if (isImage1Visible) {
            foodimageView.setImageDrawable(getDrawable(R.drawable.group_124))
        } else {
            foodimageView.setImageDrawable(getDrawable(R.drawable.group_126))
        }

        isImage1Visible = !isImage1Visible
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun toggleImageTime() {
        if (isImage2Visible) {
            timeimageView.setImageDrawable(getDrawable(R.drawable.group_124))

        } else {
            timeimageView.setImageDrawable(getDrawable(R.drawable.group_126))
        }

        isImage2Visible = !isImage2Visible
    }

    private fun deleteData(){

        val swipegesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: ViewHolder,direction:Int){
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        adapter.deleteItem(viewHolder.absoluteAdapterPosition)
                        // 삭제된 음식을 SharedPreferences에 저장
                        saveFoodListToSharedPreferences(mList, deletedItems)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView((recyclerView))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleExpandableLayout(clickedItemTitle: String) {
        expandBtn.text = clickedItemTitle
        expandBtn.setTextColor(Color.parseColor("#000000"))
        toggleImage()
    }
    private fun saveFoodListToSharedPreferences(
        foodList: MutableList<FoodData>,
        deletedItems: MutableList<String>,
    ) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val foodSet = foodList.map { "${it.title}|${it.calories}" }.toMutableSet()
        sharedPreferences.edit().putStringSet("foods", foodSet).apply()

        // 삭제된 음식 리스트 저장
        sharedPreferences.edit().putStringSet("deletedItems", deletedItems.toSet()).apply()
    }

    private fun loadSavedData() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val foodSet = sharedPreferences.getStringSet("foods", setOf()) ?: setOf()
        mList.clear() // 기존 데이터를 지우고 새로 불러옴
        foodSet.forEach { food ->
            val (title, calories) = food.split("|")
            mList.add(FoodData(title, calories.toInt()))
        }
        adapter.notifyDataSetChanged()

        // 삭제된 음식 리스트 불러오기
        val deletedItemsSet = sharedPreferences.getStringSet("deletedItems", setOf()) ?: setOf()
        val deletedItems = deletedItemsSet.toMutableList()

        // 수정된 음식 리스트 불러오기
//        val modifiedItemsSet = sharedPreferences.getStringSet("modifiedItems", setOf()) ?: setOf()
//        val modifiedItems = modifiedItemsSet.map {
//            val (title, calories) = it.split("|")
//            FoodData(title, calories.toInt())
//        }.toMutableList()

        // 삭제된 음식 및 수정된 음식 적용
        mList.removeAll { deletedItems.contains(it.title) }
        adapter.notifyDataSetChanged()
    }


//    fun getgramFromEditText(): Int {
//        val gramText = gramEditText.text.toString()
//        return if (gramText.isNotEmpty()) {
//            gramText.toInt()
//        } else {
//            // 기본값 또는 에러 처리를 원하는 대로 설정
//            0
//        }
//    }

//    @Suppress("DEPRECATION")
//    fun getTimeFromTimePicker(): String {
//        val hour: Int
//        val minute: Int
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            hour = timePicker.hour
//            minute = timePicker.minute
//        } else {
//            @Suppress("DEPRECATION")
//            hour = timePicker.currentHour
//            @Suppress("DEPRECATION")
//            minute = timePicker.currentMinute
//        }
//
//        // 시간을 원하는 형식으로 포맷팅
//        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
}