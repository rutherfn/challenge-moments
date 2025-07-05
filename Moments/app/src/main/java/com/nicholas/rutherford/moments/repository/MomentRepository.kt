package com.nicholas.rutherford.moments.repository

import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.room.MomentEntity

/**
 * [MomentRepository] is an interface that provides methods for interacting with moments
 * in the data layer. It defines operations to create, read, update, and delete (CRUD) moments
 * from the underlying data source (e.g., database).
 *
 * Implementations of this interface are responsible for providing the actual data access logic,
 * while the interface itself is used to abstract the data source interactions for the rest of the application.
 */
interface MomentRepository {

    /**
     * Inserts a new moment into the data source.
     *
     * @param moment The [MomentEntity] to be inserted.
     *
     * This method is used to add a new moment to the database or other persistent storage.
     *
     * @throws Exception If the insertion fails (e.g., due to a database error).
     */
    suspend fun insertMoment(moment: MomentEntity)

    /**
     * Edits an existing moment in the data source.
     *
     * @param currentMomentTitle The title of the moment to be updated.
     * @param newMoment The new [MomentEntity] that will replace the old one.
     *
     * This method is used to modify the details of an existing moment.
     * The current moment is identified by its title, and the new moment contains updated data.
     *
     * @throws Exception If the update fails (e.g., if the moment does not exist).
     */
    suspend fun editMoment(currentMomentTitle: String, newMoment: MomentEntity)

    /**
     * Retrieves all moments from the data source.
     *
     * @return A list of [MomentEntity] representing all moments.
     *
     * This method fetches all moments available in the data source, typically used for displaying
     * a list of moments in the user interface.
     *
     * @throws Exception If fetching the moments fails.
     */
    suspend fun getAllMoments(): List<MomentEntity>

    /**
     * Retrieves moments filtered by category from the data source.
     *
     * @param tag The [CategoryTag] used to filter the moments by category.
     * @return A list of [MomentEntity] that belong to the specified category.
     *
     * This method allows fetching moments that are categorized under a specific tag, such as "Family"
     * or "Music".
     *
     * @throws Exception If fetching the moments fails.
     */
    suspend fun getMomentsByCategory(tag: CategoryTag): List<MomentEntity>

    /**
     * Deletes a moment from the data source based on its title.
     *
     * @param title The title of the moment to be deleted.
     *
     * This method removes the specified moment from the database or other persistent storage.
     *
     * @throws Exception If the deletion fails (e.g., if the moment does not exist).
     */
    suspend fun deleteMoment(title: String)
}
