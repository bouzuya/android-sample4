package net.bouzuya.sample4

class BookmarkRepository(private val _bookmarkDao: BookmarkDao) {
    suspend fun findAll(): List<Bookmark> = _bookmarkDao.findAll()

    suspend fun findById(id: Long): Bookmark? = _bookmarkDao.findById(id).firstOrNull()

    suspend fun insert(user: Bookmark): Unit = _bookmarkDao.insert(user)

    suspend fun update(bookmark: Bookmark): Unit = _bookmarkDao.update(bookmark)
}
