package com.university.core.exception

import android.content.Context
import com.university.core.R
import com.university.core.datasource.remote.interceptor.NoConnectivityException
import retrofit2.HttpException

sealed class AppException : Throwable() {
    data object NoConnection : AppException()
    data object UnAuthorized : AppException()
    data object NotFound : AppException()
    data object Business : AppException()
    data object ServerDown : AppException()
}

fun Throwable.toAppException(): AppException {
    return try {
        when (this) {
            is NoConnectivityException -> AppException.NoConnection
            is HttpException -> {
                when (code()) {
                    400 -> AppException.Business
                    401 -> AppException.UnAuthorized
                    404 -> AppException.NotFound
                    else -> AppException.ServerDown
                }
            }

            else -> AppException.ServerDown
        }
    } catch (e: Exception) {
        AppException.ServerDown
    }
}

fun AppException.getMessageShouldDisplay(
    ctx: Context,
    generalErrorMsgResId: Int = R.string.lbl_general_error_msg,
    networkErrorMsgResId: Int = R.string.lbl_no_connection_error_msg
): String {
    return when (this) {
        is AppException.NoConnection -> ctx.getString(networkErrorMsgResId)
        else -> ctx.getString(generalErrorMsgResId)
    }
}