package com.hcato.assistantvirtual.core.di

import android.content.Context
import com.hcato.assistantvirtual.BuildConfig
import com.hcato.assistantvirtual.core.http.interceptors.AuthInterceptor
import com.hcato.assistantvirtual.core.network.AdviceApi
import com.hcato.assistantvirtual.core.network.DeleteUserApi
import com.hcato.assistantvirtual.core.network.LoginApi
import com.hcato.assistantvirtual.core.network.RegisterApi
import com.hcato.assistantvirtual.core.network.UpdateStatusApi
import com.hcato.assistantvirtual.features.assistent.data.datasource.repositories.AdviceRepositoryImpl
import com.hcato.assistantvirtual.features.assistent.domain.repositories.AdviceRepository
import com.hcato.assistantvirtual.features.deleteuser.data.datasource.DeleteUserRepositoryImpl
import com.hcato.assistantvirtual.features.deleteuser.domain.repository.DeleteUserRepository
import com.hcato.assistantvirtual.features.login.data.datasource.repositories.LoginRepositoryImpl
import com.hcato.assistantvirtual.features.login.domain.repositories.LoginRepository
import com.hcato.assistantvirtual.features.login.presentation.components.TokenDataStore
import com.hcato.assistantvirtual.features.login.presentation.components.UserSessionDataStore
import com.hcato.assistantvirtual.features.register.data.datasource.repositories.RegisterRepositoryImpl
import com.hcato.assistantvirtual.features.register.domain.repositories.RegisterRepository
import com.hcato.assistantvirtual.features.updatestatus.data.datasource.repositories.UpdateStatusRepositoryImpl
import com.hcato.assistantvirtual.features.updatestatus.domain.repositories.UpdateStatusRepository
import okhttp3.OkHttpClient
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

    private val retrofitByLocal: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_LOCAL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginApi: LoginApi by lazy {
        retrofitByLocal.create(LoginApi::class.java)
    }

    val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl(
            api = loginApi
        )
    }

    val registerApi: RegisterApi by lazy {
        retrofitByLocal.create(RegisterApi::class.java)
    }

    val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl(registerApi)
    }


    val tokenDataStore: TokenDataStore by lazy {
        TokenDataStore(context)
    }

    val userSessionDataStore: UserSessionDataStore by lazy {
        UserSessionDataStore(context)
    }

    private val authOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenDataStore))
        .build()

    private val retrofitAuth: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_LOCAL)
        .client(authOkHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val updateStatusApi: UpdateStatusApi by lazy {
        retrofitAuth.create(UpdateStatusApi::class.java)
    }

    val updateStatusRepository: UpdateStatusRepository by lazy {
        UpdateStatusRepositoryImpl(updateStatusApi)
    }
    val deleteUserApi: DeleteUserApi by lazy {
        retrofitAuth.create(DeleteUserApi::class.java)
    }

    val deleteUserRepository: DeleteUserRepository by lazy {
        DeleteUserRepositoryImpl(deleteUserApi,userSessionDataStore,tokenDataStore)
    }
}