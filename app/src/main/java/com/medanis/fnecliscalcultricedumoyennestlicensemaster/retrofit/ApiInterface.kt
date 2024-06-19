package com.medanis.fnecliscalcultricedumoyennestlicensemaster.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface OpenAIInterface {
    @Headers("Content-Type: application/json",
        "Authorization: Bearer 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")
    @POST("completions")
    suspend fun getCompletion(@Body requestBody: RequestBody): Response<CompletionResponse>
}

data class RequestBody(val prompt: String, val max_tokens: Int)
data class CompletionResponse(val choices: List<Choice>)
data class Choice(val text: String, val finish_reason: String)
