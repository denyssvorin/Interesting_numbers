package com.example.interestingfactsaboutnumbers.model.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.interestingfactsaboutnumbers.model.api.NumberApi
import com.example.interestingfactsaboutnumbers.view.MainContract
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NumberRepository(context: Context): RepositoryInterface {

    val db: NumberDatabase =
        Room.databaseBuilder(context, NumberDatabase::class.java, "number database")
            .build()

    override fun getSavedData(): Flow<List<NumberData>> = db.numberDao().getNumbers()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(NumberApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    private val api = retrofit.create(NumberApi::class.java)


    override fun requestNumberData(onFinishedListener: MainContract.Presenter, number: Int) {
        val call = api.getNumberData(number)
        Log.i(TAG, "requestNumberData: number: $call")
        call.enqueue(object : Callback<NumberData> {
            override fun onResponse(
                call: Call<NumberData>,
                response: Response<NumberData>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.i(TAG, "onResponse: data - $responseData")

                    if (responseData != null) {
                        CoroutineScope(Dispatchers.Main).launch {
                            onFinishedListener.onResultSuccess(responseData)

                            db.numberDao().insertNumberData(NumberData(number = number, text = responseData.text))
                        }
                    }
                } else {
                    Log.i(TAG, "onResponse: Server returned error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NumberData>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
                onFinishedListener.onResultError(t.message.toString())
            }
        })
    }


    override fun requestRandomNumberData(onFinishedListener: MainContract.Presenter) {
        val call = api.getRandomNumberData()
        call.enqueue(object : Callback<NumberData> {
            override fun onResponse(
                call: Call<NumberData>,
                response: Response<NumberData>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.i(TAG, "onResponse: data - $responseData")

                    if (responseData != null) {
                        CoroutineScope(Dispatchers.Main).launch {
                            onFinishedListener.onResultSuccess(responseData)

                            db.numberDao().insertNumberData(responseData)
                        }
                    }
                } else {
                    Log.i(TAG, "onResponse: Server returned error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NumberData>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
                onFinishedListener.onResultError(t.message.toString())
            }
        })
    }

    companion object {
        private val TAG: String = NumberRepository::class.java.simpleName
    }

}




