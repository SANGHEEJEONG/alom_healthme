package com.example.alomtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {
    // MutableLiveData that contains an ArrayList
    val _myList = MutableLiveData<ArrayList<exerciseData>>().apply { value = ArrayList<exerciseData>() }


    // Function to add an item to the ArrayList
    fun addItem(item: exerciseData) {
        val updatedList = _myList.value
        updatedList?.add(item)
        _myList.value = updatedList
    }

    fun deleteItem(idx:Int){
        val updatedList = _myList.value
        updatedList?.removeAt(idx)
        _myList.value=updatedList


    }
    fun clearData() {
        _myList.value?.clear()
    }



}
