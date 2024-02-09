package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.Cards
import com.example.apilistapp.models.CardsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel:ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _cards = MutableLiveData<Cards>()
    val cards = _cards

    fun getCards(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllVanillaCards()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val filteredCards = response.body()?.filter { it.img.equals() }
                    _cards.value = filteredCards as Cards?
                    _loading.value = false
                }
                else{
                    Log.e("Error:", response.message())
                }
            }
        }
    }
}