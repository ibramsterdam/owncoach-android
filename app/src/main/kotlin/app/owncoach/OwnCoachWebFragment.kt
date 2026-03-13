package app.owncoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

/**
 * Custom web fragment that suppresses the native toolbar.
 * The web app renders its own navigation chrome (back arrows, titles),
 * so we use a full-screen layout with no AppBarLayout.
 *
 * Also hides the bottom tab bar on unauthenticated pages (login, etc.)
 * so the tab bar only appears when the user is signed in.
 */
@HotwireDestinationDeepLink(uri = "hotwire://fragment/web")
class OwnCoachWebFragment : HotwireWebFragment() {

    // URLs where the bottom nav should be hidden (auth flow)
    private val unauthenticatedPaths = setOf("/session/new", "/session", "/")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_web_no_toolbar, container, false)
    }

    // Returning null tells HotwireFragmentDelegate to skip all toolbar setup.
    override fun toolbarForNavigation(): Toolbar? = null

    override fun onVisitCompleted(location: String, completedOffline: Boolean) {
        super.onVisitCompleted(location, completedOffline)
        updateBottomNavVisibility(location)
    }

    override fun onVisitStarted(location: String) {
        super.onVisitStarted(location)
        updateBottomNavVisibility(location)
    }

    private fun updateBottomNavVisibility(location: String) {
        val path = android.net.Uri.parse(location).path ?: "/"
        val isAuthenticated = unauthenticatedPaths.none { path.startsWith(it) && path.length <= it.length + 1 }
        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)?.visibility =
            if (isAuthenticated) View.VISIBLE else View.GONE
    }
}
