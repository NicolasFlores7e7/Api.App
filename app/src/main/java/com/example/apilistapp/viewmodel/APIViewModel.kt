package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.R
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.Characters
import com.example.apilistapp.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val font = FontFamily(Font(R.font.randm))
    val loading = _loading
    private val _characters = MutableLiveData<Characters>()
    val characters = _characters
    private val _character = MutableLiveData<Result>()
    val character = _character
    var id = 0


    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacters()
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

    fun getCharacter() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacter(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _character.value = response.body()
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
}