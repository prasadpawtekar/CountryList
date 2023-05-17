package com.thomas.walmarttest.model

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Message(val msg: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            Loading -> "Loading"
            is Message -> "Message = $msg"
        }
    }
    val Resource<*>.succeeded
        get() = this is Success && data != null

    val Resource<*>.isLoading
        get() = this is Loading
}