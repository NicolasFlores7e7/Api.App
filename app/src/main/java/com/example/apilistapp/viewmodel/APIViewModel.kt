package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.Cards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _cards = MutableLiveData<Cards>()
    val cards = _cards
    private val _card = MutableLiveData<Cards>()
    val card = _card
    var name = ""

    fun getCards() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllVanillaCards()
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

    fun getCard(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getDetailedCard(name)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    _card.value = response.body()
                    _loading.value = false
                }else{
                    Log.e("Error: ", response.message())
                }
            }
        }
    }

    fun set_Name(name:String){
        this.name = name
    }
}