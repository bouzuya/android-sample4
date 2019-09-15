package net.bouzuya.sample4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

class HomeViewModel(private val _bookmarkRepository: BookmarkRepository) : ViewModel() {
    private val _bookmarkCount = MutableLiveData<Int>()
    val bookmarkCount: LiveData<String> = Transformations.map(_bookmarkCount) { it.toString() }

    private val _urlText = MutableLiveData<String>()
    val urlText: LiveData<String> = _urlText

    private val _bookmarkList = MutableLiveData<List<Bookmark>>()
    val bookmarkList: LiveData<List<Bookmark>> = _bookmarkList

    init {
        viewModelScope.launch {
            refreshList()
        }
    }

    fun deleteAll() = viewModelScope.launch {
        _bookmarkRepository.deleteAll()

        refreshList()
    }

    fun edit(bookmark: Bookmark) {
        TODO()
    }

    fun insert() = viewModelScope.launch {
        val name = _urlText.value ?: return@launch
        val createdAt = Instant.now().atZone(ZoneOffset.UTC).toOffsetDateTime()
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val bookmark = Bookmark(0, name, createdAt)
        _bookmarkRepository.insert(bookmark)

        _urlText.value = ""

        refreshList()
    }

    fun updateUrlText(s: String) {
        _urlText.value = s
    }

    private suspend fun refreshList() {
        _bookmarkList.value = _bookmarkRepository.findAll()
        _bookmarkCount.value = _bookmarkList.value?.size ?: 0
    }
}
