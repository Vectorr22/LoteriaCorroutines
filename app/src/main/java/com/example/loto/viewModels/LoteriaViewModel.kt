package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoteriaViewModel: ViewModel() {
    private val _lotoNumbers = mutableStateOf( emptyList<Int>() )
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    var _isLoading = mutableStateOf(false)
    var finished = mutableStateOf(false)
    fun autoGenerateNumbers(){
        try {
            viewModelScope.launch {
                generateLotoNumbers()
            }
        }catch (e: Exception){
            println(e.toString())
        }finally {
            finished.value = true
        }
    }

    private suspend fun generateLotoNumbers()
    {
        _isLoading.value = true
        for(i in 0 until 7){
            delay(1000)
            val number = Random.nextInt(60)+1
            val newList = _lotoNumbers.value.toMutableList()
            newList.add(number)
            _lotoNumbers.value = newList
        }
        _isLoading.value = false
    }


}
