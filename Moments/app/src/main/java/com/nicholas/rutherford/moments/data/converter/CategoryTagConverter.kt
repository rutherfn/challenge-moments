package com.nicholas.rutherford.moments.data.converter

import androidx.room.TypeConverter
import com.nicholas.rutherford.moments.data.CategoryTag

class CategoryTagConverter {

    @TypeConverter
    fun fromCategoryTag(tag: CategoryTag): Int = tag.typeIdentifier

    @TypeConverter
    fun toCategoryTag(id: Int): CategoryTag = CategoryTag.fromTypeId(id)
}