package net.bouzuya.sample4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

class HomeViewModel(private val _bookmarkRepository: BookmarkRepository) : ViewModel() {
    val name = "Home"

    private val _count = MutableLiveData<Int>()
    val count: LiveData<String> = Transformations.map(_count) { it.toString() }

    private val _urlText = MutableLiveData<String>()
    val urlText: LiveData<String> = _urlText

    init {
        viewModelScope.launch {
            _count.value = _bookmarkRepository.findAll().size
        }
    }

    fun insert() = viewModelScope.launch {
        val name = _urlText.value ?: return@launch
        val createdAt = Instant.now().atZone(ZoneOffset.UTC).toOffsetDateTime()
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val bookmark = Bookmark(0, name, createdAt)
        _bookmarkRepository.insert(bookmark)

        _urlText.value = ""
        _count.value = _bookmarkRepository.findAll().size
    }

    fun updateUrlText(s: String) {
        _urlText.value = s
    }
}
