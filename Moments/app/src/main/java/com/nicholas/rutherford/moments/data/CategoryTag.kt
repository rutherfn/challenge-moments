package com.nicholas.rutherford.moments.data

sealed class CategoryTag(val title: String, val typeIdentifier: Int) {
    data object Family : CategoryTag("Family", 0)
    data object Love : CategoryTag("Love", 1)
    data object Music : CategoryTag("Music", 2)
    data object Tech : CategoryTag("Tech", 3)
    data object JustMe : CategoryTag("Just Me", 4)
    data object Other : CategoryTag("Other", 5)

    companion object {
        fun fromTypeId(id: Int): CategoryTag = when (id) {
            0 -> Family
            1 -> Love
            2 -> Music
            3 -> Tech
            4 -> JustMe
            5 -> Other
            else -> Other
        }
    }
}