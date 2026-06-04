package kh.com.pheaktra.developer.basic.android.di.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel

@Entity("Task")
data class Task(
    @PrimaryKey(true) val id: Long = 0,
    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "completeYN") val completedYN: String,
)

fun List<Task>.toTaskModel(): List<TaskModel> {
    return this.map { task ->
        TaskModel(
            id = task.id,
            taskName = task.taskName,
            description = task.description,
            completedYN = task.completedYN
        )
    }
}

fun Task.toTaskModel(): TaskModel {
    return TaskModel(
        id = this.id,
        taskName = this.taskName,
        description = this.description,
        completedYN = this.completedYN
    )
}
