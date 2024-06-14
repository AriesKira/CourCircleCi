package fr.gil.exampleAppKotlin.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import fr.gil.exampleAppKotlin.TodoModel
import fr.gil.exampleAppKotlin.model.TodoDTO
import fr.gil.exampleAppKotlin.repository.TodosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val todoRepository: TodosRepository, val context: Context) {
    val todos: MutableLiveData<ArrayList<TodoModel>> = MutableLiveData()
    fun todoFromRepo() {
        val todosApiResponse = this.todoRepository.fetchTodos()

        todosApiResponse.enqueue(object : Callback<List<TodoDTO>> {
            override fun onResponse(call: Call<List<TodoDTO>>, response: Response<List<TodoDTO>>) {
                if (response.isSuccessful) {
                    val responseBody: List<TodoDTO> = response.body() ?: listOf()
                    val mappedResponse = responseBody.map {
                        TodoModel(
                            it.title,
                            it.due_date,
                            it.completed,
                            it.description,
                            it.id
                        )
                    }
                    todos.value = ArrayList(mappedResponse)
                }
            }
            override fun onFailure(call: Call<List<TodoDTO>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        });
    }


}