package com.example.interestingfactsaboutnumbers.model.di
//
//import android.app.Activity
//import android.app.Application
//import androidx.room.Room
//import com.example.interestingfactsaboutnumbers.model.api.NumberApi
//import com.example.interestingfactsaboutnumbers.model.data.NumberDatabase
//import com.example.interestingfactsaboutnumbers.model.data.NumberRepository
//import com.example.interestingfactsaboutnumbers.model.data.RepositoryInterface
//import com.example.interestingfactsaboutnumbers.presenter.MainPresenter
//import com.example.interestingfactsaboutnumbers.view.MainActivity
//import com.example.interestingfactsaboutnumbers.view.MainContract
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@InstallIn(ActivityComponent::class)
//@Module
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(NumberApi.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideNumberApi(retrofit: Retrofit): NumberApi =
//        retrofit.create(NumberApi::class.java)
//
//    @Provides
//    @Singleton
//    fun provideDatabase(application: Application): NumberDatabase =
//        Room.databaseBuilder(application, NumberDatabase::class.java, "number database")
//            .build()
//
//
//    @Provides
//    fun bindActivity(activity: Activity): MainActivity {
//        return activity as MainActivity
//    }
//}
//
//@InstallIn(ActivityComponent::class)
//@Module
//abstract class MainModule {
//
//    @Binds
//    abstract fun bindActivity(activity: MainActivity): MainContract.View
//
//    @Binds
//    abstract fun bindPresenter(impl: MainPresenter): MainContract.Presenter
//}
//
//@InstallIn(ActivityComponent::class)
//@Module
//abstract class NumberRepositoryModule {
//
//    @Binds
//    abstract fun bindNumberRepository(impl: NumberRepository): RepositoryInterface
//}