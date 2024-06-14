package fr.gil.exampleAppKotlin.services

import fr.gil.exampleAppKotlin.model.TodoDTO
import retrofit2.Call
import retrofit2.http.GET

interface TodoServices {
    @GET("RamzyK/demo/todos")
    fun getTodos(): Call<List<TodoDTO>>


}