package kh.com.pheaktra.developer.basic.android.domain.model.base

abstract class BaseUseCase<in Params, out Result> {

    suspend operator fun invoke(params: Params): Result {
        return execute(params)
    }

    protected abstract suspend fun execute(params: Params): Result
}