package com.stac.eatitdog.features.home.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.stac.domain.model.search.CategoryType
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun onClickSearch() {
        event(Event.onClickSearch)
    }

    fun onClickMilkProduct() {
        event(Event.onClickMilkProduct)
    }

    fun onClickSnack() {
        event(Event.onClickSnack)
    }

    fun onClickMeat() {
        event(Event.onClickMeat)
    }

    fun onClickVegetable() {
        event(Event.onClickVegetable)
    }

    fun onClickJunkFood() {
        event(Event.onClickJunkFood)
    }

    fun onClickSeaFood() {
        event(Event.onClickSeaFood)
    }

    fun onClickDrink() {
        event(Event.onClickDrink)
    }

    fun onClickSeaSoning() {
        event(Event.onClickSeaSoning)
    }

    fun onClickFruit() {
        event(Event.onClickFruit)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object onClickSearch : Event()
        object onClickMilkProduct : Event()
        object onClickSnack : Event()
        object onClickMeat : Event()
        object onClickVegetable : Event()
        object onClickJunkFood : Event()
        object onClickSeaFood : Event()
        object onClickDrink : Event()
        object onClickSeaSoning : Event()
        object onClickFruit : Event()
    }
}