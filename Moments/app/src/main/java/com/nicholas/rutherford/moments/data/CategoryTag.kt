package com.nicholas.rutherford.moments.data

/**
 * A sealed class representing different categories or tags that a moment can belong to.
 * Each category is associated with a title and a unique type identifier.
 *
 * Example categories include: Family, Love, Music, Tech, JustMe, and Other.
 */
sealed class CategoryTag(val title: String, val typeIdentifier: Int) {

    /**
     * Family category with a title of "Family" and type identifier 0.
     */
    data object Family : CategoryTag("Family", 0)

    /**
     * Love category with a title of "Love" and type identifier 1.
     */
    data object Love : CategoryTag("Love", 1)

    /**
     * Music category with a title of "Music" and type identifier 2.
     */
    data object Music : CategoryTag("Music", 2)

    /**
     * Tech category with a title of "Tech" and type identifier 3.
     */
    data object Tech : CategoryTag("Tech", 3)

    /**
     * Just Me category with a title of "Just Me" and type identifier 4.
     */
    data object JustMe : CategoryTag("Just Me", 4)

    /**
     * Other category with a title of "Other" and type identifier 5.
     */
    data object Other : CategoryTag("Other", 5)

    companion object {

        /**
         * Converts an integer type identifier to the corresponding [CategoryTag].
         *
         * @param id The type identifier of the category.
         * @return The [CategoryTag] that corresponds to the given type identifier.
         *         Returns [Other] if the id is invalid.
         */
        fun fromTypeId(id: Int): CategoryTag = when (id) {
            0 -> Family
            1 -> Love
            2 -> Music
            3 -> Tech
            4 -> JustMe
            5 -> Other
            else -> Other // Default to Other if no valid match
        }
    }

    /**
     * A list of all available [CategoryTag]s.
     */
    val Companion.all: List<CategoryTag>
        get() = listOf(
            Family,
            Love,
            Music,
            Tech,
            JustMe,
            Other
        )
}
