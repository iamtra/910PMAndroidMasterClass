package kh.com.pheaktra.developer.basic.android.domain

abstract class BaseUseCase<in Params, out Result> {

    suspend operator fun invoke(params: Params): Result {
        return execute(params)
    }

    protected abstract suspend fun execute(params: Params): Result
}

/**
 * Task List
 *  - id: String -> Generate by room
 *  - task_name: String
 *  - description
 *  - completedYN: String -> Y: Yes, N: No
 */