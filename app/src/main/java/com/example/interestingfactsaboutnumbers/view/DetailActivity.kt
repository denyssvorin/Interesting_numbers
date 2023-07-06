package com.example.interestingfactsaboutnumbers.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.interestingfactsaboutnumbers.R
import com.example.interestingfactsaboutnumbers.databinding.ActivityDetailBinding
import com.example.interestingfactsaboutnumbers.model.data.NumberData

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val item = intent.getParcelableExtra(DETAIL_ACTIVITY_KEY) as? NumberData
        binding.textViewNumber.text = item?.number.toString()
        binding.textViewFact.text = item?.text


    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
    }


    companion object {
        @JvmStatic
        val DETAIL_ACTIVITY_KEY = "DETAIL_ACTIVITY_KEY"
    }
}