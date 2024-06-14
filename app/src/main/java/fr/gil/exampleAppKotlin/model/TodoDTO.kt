package fr.gil.exampleAppKotlin.model

data class TodoDTO(
    val completed: Boolean,
    val description: String,
    val due_date: String,
    val id: Int,
    val title: String
)