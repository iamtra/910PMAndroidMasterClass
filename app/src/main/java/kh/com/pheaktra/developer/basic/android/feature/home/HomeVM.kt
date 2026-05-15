package kh.com.pheaktra.developer.basic.android.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.android.model.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.ComponentModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeVM(
    private val homeRepository: HomeRepository = HomeRepository()
) :  ViewModel() {
    /**
     * List Component
     */
    private var _componentList: MutableStateFlow<BaseUiState<List<ComponentModel>>> =
        MutableStateFlow(BaseUiState.Loading)
    val componentList = _componentList.asStateFlow()

    fun getComponentList() {
        viewModelScope.launch {
            _componentList.emit(BaseUiState.Loading)
            homeRepository.getComponentList().collect {
                _componentList.emit(BaseUiState.Success(it))
            }
        }
    }

    init {
        getComponentList()
    }

    fun onDispose() {
        viewModelScope.cancel()
        _componentList.value = BaseUiState.None
    }
}