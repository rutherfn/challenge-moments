package com.nicholas.rutherford.moments.room

import androidx.room.*

/**
 * [MomentDao] is a Data Access Object (DAO) interface that defines methods to perform CRUD (Create, Read, Update, Delete)
 * operations on the `moments` table in the Room database.
 *
 * This interface uses Room annotations to map the methods to SQL queries for interacting with the database.
 */
@Dao
interface MomentDao {

    /**
     * Inserts a new moment into the database. If a moment with the same ID already exists, it is replaced.
     *
     * @param moment The [MomentEntity] to be inserted into the database.
     *
     * This method uses the [Insert] annotation to insert a new row into the `moments` table. If a conflict occurs (e.g.,
     * a moment with the same primary key), the `onConflict` strategy is set to `REPLACE`, meaning the existing moment will
     * be replaced by the new one.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moment: MomentEntity)

    /**
     * Updates an existing moment in the database.
     *
     * @param moment The [MomentEntity] with updated data to be saved in the database.
     *
     * This method uses the [Update] annotation to update the details of an existing moment in the `moments` table.
     * The entity must have a primary key (`id`) that corresponds to an existing row in the table.
     */
    @Update
    suspend fun update(moment: MomentEntity)

    /**
     * Retrieves all moments from the database.
     *
     * @return A list of all [MomentEntity] objects stored in the `moments` table.
     *
     * This method uses the [Query] annotation to execute a SQL `SELECT *` statement to fetch all rows from the `moments` table.
     */
    @Query("SELECT * FROM moments")
    suspend fun getAllMoments(): List<MomentEntity>

    /**
     * Deletes a moment from the database.
     *
     * @param moment The [MomentEntity] to be deleted from the database.
     *
     * This method uses the [Delete] annotation to remove the specified moment from the `moments` table.
     * The entity passed must have a valid primary key to identify the record to be deleted.
     */
    @Delete
    suspend fun delete(moment: MomentEntity)

    /**
     * Retrieves a moment by its title from the database.
     *
     * @param title The title of the moment to be retrieved.
     * @return The [MomentEntity] that matches the given title, or `null` if no such moment exists.
     *
     * This method uses the [Query] annotation to fetch a row from the `moments` table where the title matches
     * the provided value. The `LIMIT 1` ensures only one result is returned, or `null` if no match is found.
     */
    @Query("SELECT * FROM moments WHERE title = :title LIMIT 1")
    suspend fun getMomentByTitle(title: String): MomentEntity?

    /**
     * Retrieves moments from the database that belong to a specific category.
     *
     * @param categoryId The category identifier of the moments to be retrieved.
     * @return A list of [MomentEntity] objects that match the specified category.
     *
     * This method uses the [Query] annotation to fetch rows from the `moments` table where the `categoryTag` field
     * matches the provided `categoryId`.
     */
    @Query("SELECT * FROM moments WHERE categoryTag = :categoryId")
    suspend fun getMomentByCategory(categoryId: Int): List<MomentEntity>

    /**
     * Retrieves the ID of a moment by its title.
     *
     * @param title The title of the moment to find.
     * @return The ID of the moment if found, or `null` if no such moment exists.
     *
     * This method uses the [Query] annotation to fetch the `id` of a moment based on its title. The `LIMIT 1`
     * ensures only one result is returned, or `null` if no match is found.
     */
    @Query("SELECT id FROM moments WHERE title = :title LIMIT 1")
    suspend fun getIdByTitle(title: String): String?
}
