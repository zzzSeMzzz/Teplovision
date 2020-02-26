package ru.sem.teplovision.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.sem.teplovision.model.ServerImage
import ru.sem.teplovision.ui.base.BaseViewModel
import ru.sem.teplovision.ui.base.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(private val context: Context): BaseViewModel() {

    val imgMainData: SingleLiveEvent<ServerImage> = SingleLiveEvent()

    fun setMainImage(mainFileName: String){
        val serverImage =  ServerImage(mainFileName)
        imgMainData.value = serverImage
    }
}