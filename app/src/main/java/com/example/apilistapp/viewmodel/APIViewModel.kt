package com.example.apilistapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilistapp.R
import com.example.apilistapp.api.Repository
import com.example.apilistapp.models.Characters
import com.example.apilistapp.models.Character
import com.example.apilistapp.navigation.BottomNavScreens
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
    private val _character = MutableLiveData<Character>()
    val character = _character
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite = _isFavorite
    private val _favorites = MutableLiveData<MutableList<Character>>()
    val favorites = _favorites
    var id = 0
    val bottomNavItems = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Favorite
    )
    var page = 1
    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacters(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
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

    fun getFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _loading.value = false
            }
        }
    }

    fun isFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(character)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }
    fun saveFavorite(character: Character){
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(character)
        }
    }
    fun deleteFavorite(character: Character){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(character)
        }
    }
    fun favController(){
        if(_isFavorite.value==true) _character.value?.let { deleteFavorite(it)}
        else _character.value?.let { saveFavorite(it) }
    }
}