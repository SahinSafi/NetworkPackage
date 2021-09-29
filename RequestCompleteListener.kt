package com.saifurs.user.network

interface RequestCompleteListener<T> {
    fun onSuccess(data: T)
    fun onFailed(message: String)
}