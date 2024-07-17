package com.example.aqtan.di

import android.app.Application
import com.example.aqtan.data.Constants
import com.example.aqtan.data.remote.ApiServices
import com.example.aqtan.data.repository.ApiServicesRepositoryImpl
import com.example.aqtan.domain.SharedPreferences
import com.example.aqtan.domain.repository.ApiServicesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // general Api services
    @Provides
    @Singleton
    fun providesRetrofit (): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.API_SERVICES_URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun providesApiServices () : ApiServices = providesRetrofit().create(ApiServices::class.java)



    @Provides
    @Singleton
    fun providesGeneralApiRepository(): ApiServicesRepository = ApiServicesRepositoryImpl(providesApiServices())



    @Singleton
    @Provides
    fun provideSharedPreferencesInstance(application: Application): SharedPreferences {
        return SharedPreferences(application)
    }

}