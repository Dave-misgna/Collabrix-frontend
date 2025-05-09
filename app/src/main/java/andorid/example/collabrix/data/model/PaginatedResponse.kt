package android.example.collabrix.data.model

data class PaginatedResponse<T>(
    val data: T,
    val pagination: Pagination
)

data class Pagination(
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val itemsPerPage: Int,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
) 