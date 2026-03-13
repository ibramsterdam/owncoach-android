package app.owncoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

/**
 * Custom web fragment that suppresses the native toolbar.
 * The web app renders its own navigation chrome (back arrows, titles),
 * so we use a full-screen layout with no AppBarLayout.
 *
 * The @HotwireDestinationDeepLink URI must match the built-in default so
 * that the NavigatorGraphBuilder can register this fragment as a destination.
 */
@HotwireDestinationDeepLink(uri = "hotwire://fragment/web")
class OwnCoachWebFragment : HotwireWebFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_web_no_toolbar, container, false)
    }

    // Returning null tells HotwireFragmentDelegate to skip all toolbar setup.
    override fun toolbarForNavigation(): Toolbar? = null
}
