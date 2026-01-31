package com.hcato.assistantvirtual.core.di

import android.content.Context
import com.hcato.assistantvirtual.BuildConfig
import com.hcato.assistantvirtual.core.network.AdviceApi
import com.hcato.assistantvirtual.features.assistent.data.datasource.repositories.AdviceRepositoryImpl
import com.hcato.assistantvirtual.features.assistent.domain.repositories.AdviceRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val adviceApi: AdviceApi by lazy {
        retrofit.create(AdviceApi::class.java)
    }

    val adviceRepository: AdviceRepository by lazy {
        AdviceRepositoryImpl(adviceApi)
    }
}