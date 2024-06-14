package fr.gil.exampleAppKotlin.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import fr.gil.exampleAppKotlin.R
import fr.gil.exampleAppKotlin.TodoModel
import fr.gil.exampleAppKotlin.repository.TodosRepository
import fr.gil.exampleAppKotlin.services.TodoServices
import fr.gil.exampleAppKotlin.viewModels.TodoViewModel
import fr.gil.exampleAppKotlin.views.components.TodoListAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),
    TodoClickListener {
    private lateinit var todoListRv : RecyclerView
    //Data
    lateinit var retrofitClient: Retrofit
    lateinit var todoService: TodoServices
    lateinit var todoViewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createretrofitClient()
        createTodoService()
        initViewModel()
        observeTodoListData()
        fetchTodoList()
    }

    private fun setupActivityViews(data: List<TodoModel>) {
        this.todoListRv = findViewById(R.id.todo_list_rv)

        val todoAdapter = TodoListAdapter(
            data, this
        )
        this.todoListRv.layoutManager = LinearLayoutManager(this)
        this.todoListRv.adapter = todoAdapter

    }

    companion object {
         val TODO_MODEL = "TODO_MODEL"
    }

    override fun displayTodoDetails(todoModel: TodoModel) {
        Intent(this, TodoDetailsActivity::class.java).also {
            it.putExtra(TODO_MODEL, todoModel)
            startActivity(it)
        }
    }

    private fun createretrofitClient() {
        val gsonConverterFactory = GsonConverterFactory.create(
            GsonBuilder().create()
        )
        this.retrofitClient = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(gsonConverterFactory)
            .build()

    }
    private fun createTodoService() {
        this.todoService = this.retrofitClient.create(TodoServices::class.java)
    }
    private fun initViewModel() {
        this.todoViewModel = TodoViewModel(TodosRepository(this.todoService), this)
    }
    private fun fetchTodoList() {
        this.todoViewModel.todoFromRepo()
    }
    private fun observeTodoListData() {
        this.todoViewModel.todos.observe(this) { todoListModel ->
            this.setupActivityViews(todoListModel);
        }
    }
}


interface TodoClickListener {
    fun displayTodoDetails(todoModel: TodoModel)
}

fun verifyPassword(motDePasse: String): List<String> {
    val erreurs = mutableListOf<String>()

    // Vérifier la longueur minimale
    if (motDePasse.length < 6) {
        erreurs.add("Le mot de passe doit contenir au moins 6 caractères.")
    }

    // Vérifier au moins une lettre majuscule
    if (!Regex("[A-Z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre majuscule.")
    }

    // Vérifier au moins une lettre minuscule
    if (!Regex("[a-z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre minuscule.")
    }

    // Vérifier au moins un chiffre
    if (!Regex("\\d").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins un chiffre.")
    }

    // Vérifier au moins un caractère spécial
    val caracteresSpeciaux = "~`!@#\\$%\\^&*\\(\\)-_+=<>?/\\[]\\{}|"
    if (!Regex(caracteresSpeciaux).containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins un caractère spécial parmi $caracteresSpeciaux.")
    }

    return erreurs
}