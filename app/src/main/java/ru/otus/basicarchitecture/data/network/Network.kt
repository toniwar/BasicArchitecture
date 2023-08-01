package ru.otus.basicarchitecture.data.network

import kotlinx.coroutines.delay
import ru.otus.basicarchitecture.domain.models.NetworkError
import ru.otus.basicarchitecture.domain.models.NetworkException
import ru.otus.basicarchitecture.domain.models.NetworkRequest
import ru.otus.basicarchitecture.domain.models.NetworkResult
import ru.otus.basicarchitecture.domain.models.NetworkSuccess
import java.io.IOException

object Network {

    lateinit var request: NetworkRequest

    private val service = NetworkBuilder(NetworkBuilder.BASE_URL).service

    suspend fun sendRequest(): NetworkResult {


        val result = service.sendQuery(request)
        delay(1000)
        val body = result.body()

        return try {
            if(result.isSuccessful && body != null) NetworkSuccess(body)
            else NetworkError(result.code(), result.message())
        }
        catch (e: IOException){
            NetworkException(e)
        }

    }
}