package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.HSCards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _cards = MutableLiveData<HSCards>()
    val cards = _cards
    private val _card = MutableLiveData<HSCards>()
    val card = _card
    var id = ""

    fun getCards() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCards()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cards.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error:", response.message())
                }
            }
        }
    }

//    fun getCard(id: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = repository.getDetailedCard(id)
//            withContext(Dispatchers.Main){
//                if (response.isSuccessful){
//                    _card.value = response.body()
//                    _loading.value = false
//                }else{
//                    Log.e("Error: ", response.message())
//                }
//            }
//        }
//    }

    fun set_Id(id:String){
        this.id = id
    }
}