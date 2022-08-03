package com.example.retrofitpractice

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code: Int,
    @SerializedName(value = "message") val message: String,
    // null  처리를 해야 회원가입 api 도 같이 쓸 수 있음
    @SerializedName(value = "result") val result: Result?
)

data class Result(
    @SerializedName(value = "userIdx") val userIdx: Int,
    @SerializedName(value = "jwt") val jwt: String
)

