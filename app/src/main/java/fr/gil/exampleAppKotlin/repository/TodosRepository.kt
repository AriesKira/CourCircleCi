package fr.gil.exampleAppKotlin.repository

import fr.gil.exampleAppKotlin.model.TodoDTO
import fr.gil.exampleAppKotlin.services.TodoServices
import retrofit2.Call

class TodosRepository(private val todoService: TodoServices) {
    fun fetchTodos(): Call<List<TodoDTO>> {
        return todoService.getTodos();
    }

}