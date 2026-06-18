package kh.com.pheaktra.developer.kmp.basic.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    val id: Long = 0,
    val taskName: String,
    val description: String,
    val completedYN: String, // Y: Yes, N: No
)

fun TaskModel.isCompleted(): Boolean {
    return this.completedYN == "Y"
}

