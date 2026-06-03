package kh.com.pheaktra.developer.basic.android.domain.model

import kh.com.pheaktra.developer.basic.android.util.extension.isYes

data class TaskModel(
    val id: String,
    val taskName: String,
    val description: String,
    val completedYN: String, // Y: Yes, N: No
)

fun TaskModel.isCompleted(): Boolean {
    return this.completedYN.isYes()
}

