package fr.gil.exampleAppKotlin.views.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.gil.exampleAppKotlin.R
import fr.gil.exampleAppKotlin.TodoModel
import fr.gil.exampleAppKotlin.views.TodoClickListener

class TodoListAdapter(private val todoList: List<TodoModel>, private val todoClickHandler: TodoClickListener): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {



    //class representing the object linked to the XML of one cell of the RV
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var todoTitle: TextView
        private var todoDate: TextView
        private var todoCheckBox: CheckBox

        init {
            this.todoTitle = itemView.findViewById(R.id.todo_cell_title_tv)
            this.todoDate = itemView.findViewById(R.id.todo_cell_date_tv)
            this.todoCheckBox = itemView.findViewById(R.id.todo_cell_cb)
        }

        fun bind(todoModel: TodoModel) {
            this.todoTitle.text = todoModel.title
            this.todoDate.text = todoModel.date
            this.todoCheckBox.isChecked = todoModel.isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoView= LayoutInflater.from(parent.context).inflate(R.layout.todo_cell_layout,parent,false)
        return TodoViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return this.todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodoData = todoList[position]
        holder.bind(currentTodoData)
        holder.itemView.setOnClickListener(View.OnClickListener {
            todoClickHandler.displayTodoDetails(currentTodoData)
        })
    }

}
