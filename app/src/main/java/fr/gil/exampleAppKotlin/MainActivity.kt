package fr.gil.exampleAppKotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TodoClickListener {
    private lateinit var todoListRv : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActivityViews()
    }

    private fun setupActivityViews() {
        this.todoListRv = findViewById(R.id.todo_list_rv)

        val todoAdapter = TodoListAdapter(arrayListOf(
            TodoModel("Faire les courses", "12/12/2021", false),
            TodoModel("Acheter du pain", "12/12/2021", false),
            TodoModel("Acheter du lait", "12/12/2021", false),
            TodoModel("Acheter du beurre", "12/12/2021", false),
            TodoModel("Acheter du fromage", "12/12/2021", false)
        ),this)
        this.todoListRv.layoutManager = LinearLayoutManager(this)
        this.todoListRv.adapter = todoAdapter

    }

    override fun displayTodoDetails(todoModel: TodoModel) {
        Intent(this, TodoDetailsActivity::class.java).also {
            startActivity(it)
        }
    }

}


interface TodoClickListener {
    fun displayTodoDetails(todoModel: TodoModel)
}