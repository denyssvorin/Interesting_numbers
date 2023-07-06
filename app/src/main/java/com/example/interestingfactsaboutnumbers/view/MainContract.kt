package com.example.interestingfactsaboutnumbers.view

import androidx.lifecycle.LiveData
import com.example.interestingfactsaboutnumbers.model.data.NumberData

interface MainContract {

    interface Presenter {
        fun getNumberData(number: Int)
        fun getRandomNumberData()
        val storedData: LiveData<List<NumberData>>

        fun onResultSuccess(data: NumberData)
        fun onNumberResultSuccess(data: NumberData)
        fun onResultError(error: String)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setRandomNumberData(data: NumberData)
        fun setNumberData(data: String)
        fun dataFetchFailed(error: String)
    }

}