package com.example.doan_3tuan.ViewModel.SNViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SaveNews : ViewModel(){
    private lateinit var database : DatabaseReference
    fun setValueSaveNews(value:String , Id : String)
    {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(Id).push().setValue(value)
    }

    private val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
    private val _dataList = MutableLiveData<List<String>>()
    val dataList: LiveData<List<String>> get() = _dataList

    init {
        fetchData("Id")
    }

    fun fetchData(id : String) {
        databaseReference.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children.mapNotNull { it.value as? String }
                _dataList.value = data
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
}
}