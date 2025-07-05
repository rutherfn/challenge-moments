package com.nicholas.rutherford.moments.data.converter

import androidx.room.TypeConverter
import com.nicholas.rutherford.moments.data.CategoryTag

/**
 * A TypeConverter class that provides conversion methods between [CategoryTag] and [Int].
 * This is used for persisting [CategoryTag] values in a Room database as [Int] values.
 */
class CategoryTagConverter {

    /**
     * Converts a [CategoryTag] to its corresponding integer representation (type identifier).
     *
     * @param tag The [CategoryTag] to be converted.
     * @return The integer representation (type identifier) of the [CategoryTag].
     */
    @TypeConverter
    fun fromCategoryTag(tag: CategoryTag): Int = tag.typeIdentifier

    /**
     * Converts an integer (type identifier) to its corresponding [CategoryTag].
     *
     * @param id The integer value representing the type identifier of a [CategoryTag].
     * @return The [CategoryTag] that corresponds to the given type identifier.
     */
    @TypeConverter
    fun toCategoryTag(id: Int): CategoryTag = CategoryTag.fromTypeId(id)
}
