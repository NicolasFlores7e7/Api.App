package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.R
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.Character
import com.example.apilistapp.models.Ninja
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val font = FontFamily(Font(R.font.njnaruto))
    val loading = _loading
    private val _characters = MutableLiveData<Ninja>()
    val characters = _characters
    private val _character = MutableLiveData<Character>()
    val character = _character
    var id = 0


    fun getNinjas() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllNinjas()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error:", response.message())
                }
            }
        }
    }

    fun getNinja() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getDetailedNinja(get_Id())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error: ", response.message())
                }
            }
        }
    }

    fun set_Id(id: Int) {
        this.id = id
    }
    fun get_Id():Int {
        return id
    }
}