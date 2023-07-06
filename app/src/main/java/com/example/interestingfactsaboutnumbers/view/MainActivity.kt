package com.example.interestingfactsaboutnumbers.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interestingfactsaboutnumbers.R
import com.example.interestingfactsaboutnumbers.databinding.ActivityMainBinding
import com.example.interestingfactsaboutnumbers.model.data.NumberData
import com.example.interestingfactsaboutnumbers.model.data.NumberRepository
import com.example.interestingfactsaboutnumbers.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainActivityAdapter.Listener, MainContract.View {
    private lateinit var binding: ActivityMainBinding

    lateinit var mainPresenter: MainContract.Presenter

    private val numAdapter = MainActivityAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainPresenter = MainPresenter(this, NumberRepository(this@MainActivity))

        binding.recycler.apply {
            adapter = numAdapter
            layoutManager = LinearLayoutManager(context)
        }

        mainPresenter.storedData.observe(this@MainActivity) {
            numAdapter.submitList(it)
        }

        binding.apply {
            editTextNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    editTextNumber.requestLayout()
                }

            })
            editTextNumber.setOnClickListener { editTextNumber.isCursorVisible = true }

            buttonGetFact.setOnClickListener {
                val editText = binding.editTextNumber.text.toString()
                if (!TextUtils.isEmpty(editText) || editText != "") {
                    mainPresenter?.getNumberData(editText.toInt())
                } else {
                    Toast.makeText(this@MainActivity, "Number field can not be empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            buttonGetRandomFact.setOnClickListener { mainPresenter?.getRandomNumberData() }
        }
    }


    override fun onClick(num: NumberData) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.DETAIL_ACTIVITY_KEY, num)
        })
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setRandomNumberData(data: NumberData) {
        binding.editTextNumber.setText(data.number.toString())
        binding.textViewFact.text = data.text
        binding.editTextNumber.isCursorVisible = false
    }

    override fun setNumberData(data: String) {
        binding.textViewFact.text = data
        binding.editTextNumber.isCursorVisible = false
    }

    override fun dataFetchFailed(error: String) {
        binding.textViewFact.text = error
    }

}
