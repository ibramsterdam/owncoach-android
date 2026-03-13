package app.owncoach

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration

class MainActivity : HotwireActivity() {

    private val tabConfigs = listOf(
        TabConfig(R.id.tab_food,      R.id.nav_host_food,      "https://owncoach.app/foods"),
        TabConfig(R.id.tab_dashboard, R.id.nav_host_dashboard, "https://owncoach.app/dashboard"),
        TabConfig(R.id.tab_me,        R.id.nav_host_me,        "https://owncoach.app/user")
    )

    private var currentTabId: Int = R.id.tab_food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showTab(R.id.tab_food)
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setOnItemSelectedListener { item ->
                if (item.itemId != currentTabId) {
                    showTab(item.itemId)
                }
                true
            }
    }

    override fun navigatorConfigurations() = tabConfigs.map { tab ->
        NavigatorConfiguration(
            name = hostIdToName(tab.hostId),
            startLocation = tab.startUrl,
            navigatorHostId = tab.hostId
        )
    }

    private fun showTab(tabId: Int) {
        currentTabId = tabId
        tabConfigs.forEach { tab ->
            val host = findViewById<View>(tab.hostId)
            host.visibility = if (tab.tabId == tabId) View.VISIBLE else View.GONE
        }
    }

    private fun hostIdToName(hostId: Int) = when (hostId) {
        R.id.nav_host_food      -> "food"
        R.id.nav_host_dashboard -> "dashboard"
        R.id.nav_host_me        -> "me"
        else                    -> "main"
    }

    private data class TabConfig(
        val tabId: Int,
        val hostId: Int,
        val startUrl: String
    )
}
