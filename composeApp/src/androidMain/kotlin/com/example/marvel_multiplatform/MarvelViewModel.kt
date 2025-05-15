package com.example.marvel_multiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MarvelApiImpl
import com.example.marvel_multiplatform.data.MarvelRepository
import com.example.marvel_multiplatform.data.Personaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelViewModel : ViewModel() {

    private val repository = MarvelRepository(MarvelApiImpl())

    private val _personajes = MutableStateFlow<List<Personaje>>(emptyList())
    val personajes: StateFlow<List<Personaje>> get() = _personajes

    init {
        fetchPersonajes()
    }

    private fun fetchPersonajes() {
        viewModelScope.launch {
            try {
                val resultado = repository.fetchPersonajes()
                _personajes.value = resultado
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
