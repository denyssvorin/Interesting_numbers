package com.example.interestingfactsaboutnumbers.model.data

import com.example.interestingfactsaboutnumbers.view.MainContract
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    fun getSavedData(): Flow<List<NumberData>>
    fun requestNumberData(onFinishedListener: MainContract.Presenter, number: Int)
    fun requestRandomNumberData(onFinishedListener: MainContract.Presenter)
}