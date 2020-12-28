package com.example.newsapp.repository

import com.example.newsapp.local_db.NewsPost
import com.example.newsapp.util.Result
import retrofit2.Response
import java.io.IOException


abstract class BaseRepository {

    protected suspend fun <S : Any, D : Any> safeApiCall(
            errorMessage: String = "",
            converter: (S) -> D,
            call: suspend () -> Response<S>
    ): Result<D> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return Result.Success(converter.invoke(response.body()!!))
            }

            return Result.Fail(
                    errorMessage,
                    IOException("Error (code: ${response.code()}) occurred during safe Api call: ${response.errorBody()?.string()}")
            )
        } catch (e: IOException) {
            return Result.Fail(errorMessage, e)
        }
    }

   abstract suspend fun getData() : List<NewsPost>

   abstract suspend fun getData(title:String) : NewsPost
}
