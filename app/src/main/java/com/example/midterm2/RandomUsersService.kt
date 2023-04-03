package com.example.midterm2
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface RandomUsersService {
    @GET("/api/")
    suspend fun getUsers(
        @Query("inc") inc: String,
        @Query("results") results: Int
    ): Response<UserResponse>
}

data class UserResponse(val results: List<Result>)

data class Result(val name: Name, val email: String)

data class Name(val first: String, val last: String)
