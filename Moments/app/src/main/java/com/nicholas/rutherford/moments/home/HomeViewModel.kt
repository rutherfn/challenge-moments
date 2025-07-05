
package com.nicholas.rutherford.moments.home

import androidx.lifecycle.LifecycleOwner
import com.nicholas.rutherford.moments.BaseViewModel
import com.nicholas.rutherford.moments.navigation.NavigationActions
import com.nicholas.rutherford.moments.navigation.Navigator
import com.nicholas.rutherford.moments.repository.MomentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel that's responsible for retrieving and exposing a list of all user moments, handling item click events,
 * and navigating to the Create/Edit Moment screen.
 *
 * @param scope The coroutine scope used for launching data-fetching operations.
 * @param momentRepository Provides access to moment data stored / exposed in the database.
 * @param navigator Handles navigation actions within the app.
 */
class HomeViewModel(
    private val scope: CoroutineScope,
    private val momentRepository: MomentRepository,
    private val navigator: Navigator
) : BaseViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        updateNotesState()
    }

    /**
     * Fetches all saved moments and updates the screen state.
     */
    fun updateNotesState() {
        scope.launch {
            val notes = momentRepository.getAllMoments()

            _state.update { state ->
                state.copy(
                    notes = notes
                )
            }
        }
    }

    /**
     * Navigates to the Create/Edit Moment screen for the given moment.
     *
     * @param title The title of the selected moment.
     * @param categoryTag The integer representing the category of the selected moment.
     * This is built using CategoryTag.valueOf extension function
     */
    fun onItemClicked(title: String, categoryTag: Int) {
        navigator.navigate(navigationAction = NavigationActions.createEditMoment(title, categoryTag))

    }
}