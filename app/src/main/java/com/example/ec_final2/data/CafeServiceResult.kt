package com.example.ec_final2.data
import java.lang.Exception

sealed class CafeServiceResult<T> (data: T? =null, error: Exception? =null){
    data class  Success<T>(val data:T): CafeServiceResult<T>(data,null)
    data class Error<T>(val error: Exception): CafeServiceResult<T>(null,error)
}
