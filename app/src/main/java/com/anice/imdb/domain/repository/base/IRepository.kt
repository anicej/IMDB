package com.anice.imdb.domain.repository.base

import com.anice.imdb.domain.remote.response.ApiResponse
import com.anice.imdb.domain.remote.response.ErrorCode
import com.anice.imdb.domain.remote.response.ErrorResponse
import com.google.gson.stream.MalformedJsonException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException

private const val TAG = "IRepository"

/**
 * Base interface to be implemented by all repositories
 */
interface IRepository {

    suspend fun <T> handleRequest(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.isSuccessful) {
                ApiResponse.Success(apiResponse.body())
            } else {
                handleFailureResponse(apiResponse.errorBody())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            handleException(ex)
        }
    }

    fun <T> handleFailureResponse(response: ResponseBody?): ApiResponse<T> {
        response?.let {
            val jsonObject = JSONObject(it.string())
            try {
                val code = jsonObject.getInt("status")
                val error = jsonObject.getString("error")
                val errorMessage = jsonObject.getString("message")

                val errorCode: ErrorCode = when (code) {
                    401 -> ErrorCode.UNAUTHORIZED
                    404 -> ErrorCode.NOT_FOUND
                    else -> ErrorCode.UNKNOWN
                }

                return ApiResponse.Failure(ErrorResponse(errorCode, error, errorMessage))
            } catch (ex: Exception) {

            }
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN))
    }

    private fun <T> handleException(exception: Exception?): ApiResponse<T> {
        exception?.let {
            val errorResponse = if (exception is ConnectException) {
                ErrorResponse(ErrorCode.NO_NETWORK)
            } else if (exception is MalformedJsonException) {
                ErrorResponse(ErrorCode.BAD_RESPONSE)
            } else {
                ErrorResponse(ErrorCode.UNKNOWN)
            }
            return ApiResponse.Failure(errorResponse)
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN))
    }
}