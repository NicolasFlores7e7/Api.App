package com.example.apilistapp.viewmodel

import android.util.Log
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
    val page = MutableLiveData(1)
    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText
    private val _searchBarBoolean = MutableLiveData(false)
    val searchBarBoolean = _searchBarBoolean
    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = page.value?.let { repository.getCharacters(it) }
            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {
                        _characters.value = response.body()
                        _loading.value = false
                    } else {
                        Log.e("Error :", response.message())
                    }
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

    fun saveFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(character)
        }
    }

    fun deleteFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(character)
        }
    }

    fun favController(character: Character?, isFavorite: Boolean?) {
        if (isFavorite == true) {
            character?.let { deleteFavorite(it) }
            _isFavorite.value = false
        } else {
            character?.let { saveFavorite(it) }
            _isFavorite.value = true
        }
    }

    fun onSearchTextChange(keyWord: String) {
        _searchText.value = keyWord
        if (keyWord.isNotEmpty()) {
            val charFiltered = Characters(_characters.value!!.characters.filter { it.name.lowercase().contains(keyWord.lowercase()) })
            _characters.value = charFiltered
        } else {
            getCharacters()
        }
    }


    fun searchActivator(searchBarBoolean: Boolean) {
        _searchBarBoolean.value = !searchBarBoolean
    }
}