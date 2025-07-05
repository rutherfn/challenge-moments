package com.nicholas.rutherford.moments.home

import com.nicholas.rutherford.moments.room.MomentEntity

/**
 * Represents the state of the Home screen, which includes a list of moments.
 *
 * @param notes A list of [MomentEntity] that holds the details of moments to be displayed on the Home screen.
 *              Defaults to an empty list if no moments are available.
 */
data class HomeState(
    val notes: List<MomentEntity> = emptyList()
)
