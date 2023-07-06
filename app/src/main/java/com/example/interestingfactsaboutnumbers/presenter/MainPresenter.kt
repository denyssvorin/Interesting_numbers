package com.example.interestingfactsaboutnumbers.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.interestingfactsaboutnumbers.model.data.NumberData
import com.example.interestingfactsaboutnumbers.model.data.RepositoryInterface
import com.example.interestingfactsaboutnumbers.view.MainContract

class MainPresenter(
    private var action: MainContract.View,
    private val repository: RepositoryInterface
) : MainContract.Presenter {

    private val searchFlow = repository.getSavedData()
    override val storedData: LiveData<List<NumberData>>
        get() = searchFlow.asLiveData()

    override fun getRandomNumberData() {
        action.showProgress()
        repository.requestRandomNumberData(this)
    }

    override fun getNumberData(number: Int) {
        action.showProgress()
        repository.requestNumberData(this, number)
    }

    override fun onResultSuccess(data: NumberData) {
        action.hideProgress()
        action.setRandomNumberData(data)
    }

    override fun onNumberResultSuccess(data: NumberData) {
        action.hideProgress()
        action.setNumberData(data.text)
    }

    override fun onResultError(error: String) {
        action.hideProgress()
        action.dataFetchFailed(error)
    }

}
