package kh.com.pheaktra.developer.basic.android.model

sealed class BaseUiState<out T> {
    data object None : BaseUiState<Nothing>()
    data object Loading : BaseUiState<Nothing>()
    data class Success<out T>(val data: T) : BaseUiState<T>()
    data class Error(val message: String) : BaseUiState<Nothing>()
    data class ErrorWithException(val message: String) : BaseUiState<Nothing>()
}


/**
 * Global Loading state
 * 1. base ui state (Done)
 * 2. loading util (singleTon) (Done)
 * 3. Use View Model
 * 4. Use Loading content in screen
 * 5. Loading Ui Content (done)
 */