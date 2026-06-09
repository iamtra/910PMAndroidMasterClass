package kh.com.pheaktra.developer.basic.android.domain.model

import kh.com.pheaktra.developer.basic.android.di.local.entity.Task
import kh.com.pheaktra.developer.basic.android.util.extension.isYes
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    val id: Long = 0,
    val taskName: String,
    val description: String,
    val completedYN: String, // Y: Yes, N: No
)

fun TaskModel.isCompleted(): Boolean {
    return this.completedYN.isYes()
}

fun TaskModel.toTask(): Task {
    return Task(
        id = this.id,
        taskName = this.taskName,
        description = this.description,
        completedYN = this.completedYN
    )
}

