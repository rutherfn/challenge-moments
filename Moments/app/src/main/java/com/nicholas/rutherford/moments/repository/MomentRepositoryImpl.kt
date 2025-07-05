package com.nicholas.rutherford.moments.repository

import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.room.MomentDao
import com.nicholas.rutherford.moments.room.MomentEntity
import timber.log.Timber

/**
 * [MomentRepositoryImpl] is an implementation of the [MomentRepository] interface. It interacts
 * with the [MomentDao] (Data Access Object) to perform CRUD (Create, Read, Update, Delete) operations
 * on moment data in the database.
 *
 * This class serves as the concrete data layer for the application, utilizing Room's [MomentDao]
 * for database operations.
 *
 * @param momentDao The [MomentDao] instance used to access the moments data in the database.
 */
class MomentRepositoryImpl(private val momentDao: MomentDao) : MomentRepository {

    /**
     * Inserts a new moment into the database.
     *
     * @param moment The [MomentEntity] to be inserted.
     *
     * This method uses [MomentDao]'s [insert] function to insert the provided moment into the database.
     */
    override suspend fun insertMoment(moment: MomentEntity) {
        Timber.d("Inserting moment: ${moment.title}")
        try {
            momentDao.insert(moment = moment)
            Timber.d("Moment inserted successfully: ${moment.title}")
        } catch (e: Exception) {
            Timber.e(e, "Failed to insert moment: ${moment.title}")
        }
    }

    /**
     * Edits an existing moment in the database.
     *
     * @param currentMomentTitle The title of the moment that will be updated.
     * @param newMoment The new [MomentEntity] that will replace the old one.
     *
     * This method updates a moment identified by its title. It retrieves the ID of the moment by
     * title and then updates the moment with the new data provided in [newMoment].
     * If the moment with the provided title doesn't exist, no changes are made.
     */
    override suspend fun editMoment(currentMomentTitle: String, newMoment: MomentEntity) {
        Timber.d("Editing moment with title: $currentMomentTitle")
        try {
            momentDao.getIdByTitle(title = currentMomentTitle)?.let { id ->
                momentDao.update(moment = newMoment.copy(id = id))
                Timber.d("Moment edited successfully: $currentMomentTitle")
            } ?: Timber.w("No moment found with title: $currentMomentTitle")
        } catch (e: Exception) {
            Timber.e(e, "Failed to edit moment with title: $currentMomentTitle")
        }
    }

    /**
     * Retrieves all moments from the database.
     *
     * @return A list of all [MomentEntity] objects from the database.
     *
     * This method calls [MomentDao]'s [getAllMoments] function to fetch all stored moments from
     * the database.
     */
    override suspend fun getAllMoments(): List<MomentEntity> {
        Timber.d("Fetching all moments from the database")
        return try {
            val moments = momentDao.getAllMoments()
            Timber.d("Fetched ${moments.size} moments")
            moments
        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch all moments")
            emptyList() // Return empty list in case of error
        }
    }

    /**
     * Retrieves all moments that belong to a specific category from the database.
     *
     * @param tag The [CategoryTag] to filter moments by category.
     * @return A list of [MomentEntity] that belong to the given category.
     *
     * This method calls [MomentDao]'s [getMomentByCategory] function, passing the [CategoryTag]'s
     * `typeIdentifier` to fetch moments of that specific category.
     */
    override suspend fun getMomentsByCategory(tag: CategoryTag): List<MomentEntity> {
        Timber.d("Fetching moments for category: ${tag.typeIdentifier}")
        return try {
            val moments = momentDao.getMomentByCategory(categoryId = tag.typeIdentifier)
            Timber.d("Fetched ${moments.size} moments for category: ${tag.typeIdentifier}")
            moments
        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch moments for category: ${tag.typeIdentifier}")
            emptyList() // Return empty list in case of error
        }
    }

    /**
     * Deletes a moment from the database based on its title.
     *
     * @param title The title of the moment to be deleted.
     *
     * This method first fetches the moment with the provided title using [getMomentByTitle]. If the
     * moment exists, it is deleted from the database using the [delete] function in [MomentDao].
     */
    override suspend fun deleteMoment(title: String) {
        Timber.d("Deleting moment with title: $title")
        try {
            momentDao.getMomentByTitle(title = title)?.let { moment ->
                momentDao.delete(moment = moment)
                Timber.d("Moment deleted successfully: $title")
            } ?: Timber.w("No moment found with title: $title")
        } catch (e: Exception) {
            Timber.e(e, "Failed to delete moment with title: $title")
        }
    }
}



