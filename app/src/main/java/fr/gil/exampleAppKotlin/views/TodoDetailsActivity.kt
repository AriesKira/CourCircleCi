package fr.gil.exampleAppKotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import fr.gil.exampleAppKotlin.R
import fr.gil.exampleAppKotlin.TodoModel
import fr.gil.exampleAppKotlin.views.MainActivity.Companion.TODO_MODEL

class TodoDetailsActivity : AppCompatActivity() {
    lateinit var todoTitleTv: TextView
    lateinit var todoDescriptionTv: TextView
    lateinit var todoDateTv: TextView

    lateinit var totoDeleteIBtn: ImageButton
    lateinit var todoEditIBtn: ImageButton
    lateinit var todoMarkAsDoneBtn: Button

    lateinit var todoTitle: String
    lateinit var todoDescription: String
    lateinit var todoDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)
        getIntentExtraData()
        setupViews()
        handleButtonObservation()
    }

    private fun getIntentExtraData() {
        if (intent.hasExtra(TODO_MODEL)) {
            val todoModel = intent.getParcelableExtra<TodoModel>(TODO_MODEL)
            this.todoTitle = todoModel?.title ?: ""
            this.todoDescription = todoModel?.description ?: ""
            this.todoDate = todoModel?.date ?: ""
        }
    }
    private fun setupViews() {
        this.todoTitleTv = findViewById(R.id.todoTitleTv)
        this.todoTitleTv.text = this.todoTitle
        this.todoDescriptionTv = findViewById(R.id.todoDescriptionTv)
        this.todoDescriptionTv.text = this.todoDescription
        this.todoDateTv = findViewById(R.id.todoDateTv)
        this.todoDateTv.text = this.todoDate
        this.totoDeleteIBtn = findViewById(R.id.totoDeleteIBtn)
        this.todoEditIBtn = findViewById(R.id.todoEditIBtn)
        this.todoMarkAsDoneBtn = findViewById(R.id.todoMarkAsDoneBtn)
    }

    private fun handleButtonObservation() {
        this.handleTodoDeleting()
        this.handleTodoEditing()
        this.handleTodoMarkingAsDone()
    }

    private fun handleTodoDeleting() {
        this.totoDeleteIBtn.setOnClickListener {
            Log.d("Deleting", "handleTodoDeleting")
        }
    }

    private fun handleTodoEditing() {
        this.todoEditIBtn.setOnClickListener {
            Log.d("Editing", "handleTodoEditing ")
        }
    }

    private fun handleTodoMarkingAsDone() {
        this.todoMarkAsDoneBtn.setOnClickListener {
            Log.d("Marking", "handleTodoMarkingAsDone ")
        }
    }

}