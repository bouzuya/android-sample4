package net.bouzuya.sample4

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HomeViewModel(private val _bookmarkRepository: BookmarkRepository) : ViewModel() {
    val name = "Home"

    private val _count = MutableLiveData<Int>()
    val count: LiveData<String> = Transformations.map(_count) { it.toString() }

    init {
        viewModelScope.launch {
            _count.value = _bookmarkRepository.findAll().size
        }
    }
}
