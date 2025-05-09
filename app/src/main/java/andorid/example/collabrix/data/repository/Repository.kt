package andorid.example.collabrix.data.repository

interface Repository<T> {
    suspend fun get(id: String): T?
    suspend fun getAll(): List<T>
    suspend fun create(item: T): T
    suspend fun update(item: T): T
    suspend fun delete(id: String): Boolean
} 