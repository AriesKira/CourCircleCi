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

fun verifyPassword(motDePasse: String): List<String> {
    val erreurs = mutableListOf<String>()

    // Vérifier la longueur minimale
    if (motDePasse.length > 6) {
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