package com.example.alomtest.food.foodcustom01

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
import android.widget.TextView
import android.widget.TimePicker
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.alomtest.R
import com.example.alomtest.databinding.ActivityAddBinding
import com.example.alomtest.food.foodcustom01.FoodAdapter
import com.example.alomtest.food.foodcustom01.FoodData
import com.example.alomtest.food.foodcustom01.SwipeGesture
import com.example.alomtest.food.mainpage.Food
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class AddActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var cardView : MaterialCardView
    private lateinit var cardview_time : MaterialCardView
    private lateinit var foodimageView : ImageView
    private lateinit var timeimageView : ImageView
    private lateinit var expandableLayout: View
    private lateinit var expandableLayout_time: View
    private var mList: MutableList<FoodData> = mutableListOf()
    private val deletedItems: MutableList<String> = mutableListOf()
    private lateinit var adapter: FoodAdapter
    private lateinit var binding: ActivityAddBinding
    private lateinit var expandBtn: Button
    private lateinit var expandBtn_time: Button
    private lateinit var foodaddBtn: Button
    private var isImage1Visible = true
    private var isImage2Visible = true
    private lateinit var gramEditText: EditText
    private lateinit var foodAddEditText: EditText
    private lateinit var kcalAddEditText: EditText
    private lateinit var timePicker: TimePicker
    private lateinit var foodaddback: Button
    companion object {
        const val RESULT_ADD_TASK = 123 // Any unique value
        const val RESULT_BACK_TASK = 321

    }



    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(binding.root)



        cardView = binding.materialCardView
        cardview_time = binding.materialCardViewTime
        recyclerView = binding.recyclerViewfood2
        searchView = binding.searchViewfood
        expandableLayout = binding.expandableLayout
        expandableLayout_time = binding.expandableLayoutTime
        expandBtn = binding.expandBtn
        expandBtn_time = binding.expandBtnTime
        foodaddBtn = binding.expandBtn2
        foodimageView = binding.foodimageView
        timeimageView = binding.timeimageView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter
        gramEditText = findViewById(R.id.add_edit)
        foodAddEditText = findViewById(R.id.foodAddEditText)
        kcalAddEditText = findViewById(R.id.kcalAddEditText)
        timePicker = findViewById(R.id.timePicker)
        foodaddback=findViewById(R.id.food_add_back)


        addDefaultFoodToList()
        loadSavedData()



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        binding.addNext.setOnClickListener() {
            val newTask = findViewById<EditText>(R.id.add_edit).text.toString()
            val calories = mList.find { it.title == expandBtn.text.toString() }?.calories ?: 0
            if(expandBtn.text.toString()=="선택하기" || binding.expandBtnTime.text.toString()=="선택하기"){
                Snackbar.make(it, "정보를 모두 입력해주세요!", Snackbar.LENGTH_SHORT).show()
            }else{

                val resultintent = Intent(this, Food::class.java)
                resultintent.putExtra("newTask", newTask)
                resultintent.putExtra("foodname",expandBtn.text.toString())
                resultintent.putExtra("timeFormat",binding.expandBtnTime.text.toString())
                resultintent.putExtra("calories",calories)

                setResult(RESULT_ADD_TASK, resultintent)
                finish()}
            // saveData()
        }
        binding.foodAddBack.setOnClickListener{
            val backintent = Intent(this,Food::class.java)
            setResult(RESULT_BACK_TASK,backintent)
            finish()
        }

        binding.expandBtn.setOnClickListener {
            toggleImage()

            if (expandableLayout.visibility == View.GONE) {
                expandableLayout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
                cardView.visibility = View.GONE
            }

        }

        binding.expandBtnTime.setOnClickListener {
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

        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY) // 시
        val currentMinute = currentTime.get(Calendar.MINUTE) // 분

        binding.timePicker.hour = currentHour
        binding.timePicker.minute = currentMinute


        binding.timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            val timeFormat = if (hour > 12) {
                val adjustedHour = hour - 12
                "오후 $adjustedHour 시 $minute 분"
            } else {
                "오전 $hour 시 $minute 분"
            }

            binding.expandBtnTime.text = timeFormat
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

    private fun addDefaultFoodToList() {
        val nextPosition = mList.size // 다음 위치는 현재 리스트의 크기와 같습니다.
        mList.add(FoodData("닭갈비", 596))
        mList.add(FoodData("닭꼬치", 177))
        mList.add(FoodData("돼지갈비", 240))
        mList.add(FoodData("불고기", 395))
        mList.add(FoodData("훈제오리", 797))
        mList.add(FoodData("굴국밥", 683))
        mList.add(FoodData("김치국", 89))
        mList.add(FoodData("떡만둣국", 625))
        mList.add(FoodData("미소된장국", 38))
        mList.add(FoodData("바지락조개국", 157))
        mList.add(FoodData("뼈다귀해장국", 714))
        mList.add(FoodData("소고기무국", 123))
        mList.add(FoodData("소고기미역국", 151))
        mList.add(FoodData("고추잡채", 256))
        mList.add(FoodData("소고기샤브샤브", 264))
        mList.add(FoodData("오징어순대", 467))
        mList.add(FoodData("메밀전병", 168))
        mList.add(FoodData("송편(깨)", 224))
        mList.add(FoodData("송편(콩)", 194))
        mList.add(FoodData("찹쌀떡", 277))
        mList.add(FoodData("간짜장", 825))
        mList.add(FoodData("김치라면", 552))
        mList.add(FoodData("김치우동", 500))
        mList.add(FoodData("닭칼국수", 663))
        mList.add(FoodData("막국수", 572))
        mList.add(FoodData("삼선짜장면", 804))
        mList.add(FoodData("삼선짬뽕", 662))
        mList.add(FoodData("쌀국수", 320))
        mList.add(FoodData("짜장면", 797))
        mList.add(FoodData("치즈라면", 595))
        mList.add(FoodData("크림소스스파게티", 838))
        mList.add(FoodData("토마토소스스파게티", 643))
        mList.add(FoodData("해물칼국수", 628))
        mList.add(FoodData("해물크림소스스파게티", 918))
        mList.add(FoodData("해물토마토소스스파게티", 584))
        mList.add(FoodData("골뱅이무침", 109))
        mList.add(FoodData("곤드레나물밥", 522))
        mList.add(FoodData("국밥", 418))
        mList.add(FoodData("돼지국밥", 911))
        mList.add(FoodData("비빔밥", 692))
        mList.add(FoodData("새우튀김롤", 607))
        mList.add(FoodData("소고기국밥", 331))
        mList.add(FoodData("숯불갈비 삼각김밥", 161))
        mList.add(FoodData("연어롤", 510))
        mList.add(FoodData("연어초밥", 447))
        mList.add(FoodData("오곡밥", 388))
        mList.add(FoodData("짜장밥", 741))
        mList.add(FoodData("잡탕밥", 777))
        mList.add(FoodData("장어덮밥", 716))
        mList.add(FoodData("참치덮밥", 679))
        mList.add(FoodData("장어초밥", 486))
        mList.add(FoodData("충무김밥", 583))
        mList.add(FoodData("캘리포니아롤", 487))
        mList.add(FoodData("해물덮밥", 771))
        mList.add(FoodData("해물볶음밥", 705))
        mList.add(FoodData("회덮밥", 683))
        mList.add(FoodData("깻잎나물볶음", 206))
        mList.add(FoodData("돼지고기볶음", 350))
        mList.add(FoodData("두부김치", 288))
        mList.add(FoodData("멸치풋고추볶음", 52))
        mList.add(FoodData("소시지볶음", 471))
        mList.add(FoodData("순대볶음", 582))
        mList.add(FoodData("오삼불고기", 363))
        mList.add(FoodData("주꾸미볶음", 211))
        mList.add(FoodData("해물볶음", 419))
        mList.add(FoodData("곰보빵", 277))
        mList.add(FoodData("만주", 222))
        mList.add(FoodData("버터크림빵", 229))
        mList.add(FoodData("불고기피자", 505))
        mList.add(FoodData("참치샌드위치", 555))
        mList.add(FoodData("찹쌀도넛", 207))
        mList.add(FoodData("채소고로케", 300))
        mList.add(FoodData("초콜릿케이크", 420))
        mList.add(FoodData("치즈케이크", 329))
        mList.add(FoodData("치즈피자", 553))
        mList.add(FoodData("페이스트리빵", 319))
        mList.add(FoodData("페퍼로니피자", 532))
        mList.add(FoodData("포테이토피자", 628))
        mList.add(FoodData("수정과", 133))
        mList.add(FoodData("식혜", 130))
        mList.add(FoodData("매실장아찌", 72))
        mList.add(FoodData("무장아찌", 23))
        mList.add(FoodData("소고기산적", 453))
        mList.add(FoodData("오징어산적", 101))
        mList.add(FoodData("홍합산적", 156))
        mList.add(FoodData("가자미전", 230))
        mList.add(FoodData("고추전", 274))
        mList.add(FoodData("굴전", 195))
        mList.add(FoodData("깻잎전", 361))
        mList.add(FoodData("녹두빈대떡", 207))
        mList.add(FoodData("동그랑땡(육원전)", 309))


        adapter.notifyDataSetChanged()

//        addFoodToList(mList.size - 1)
    }

    private fun addFoodToList() {
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
                    ItemTouchHelper.RIGHT -> {
                        // 오른쪽으로 스와이프한 경우
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

        // 삭제된 음식 및 수정된 음식 적용
        mList.removeAll { deletedItems.contains(it.title) }
        adapter.notifyDataSetChanged()
    }


}