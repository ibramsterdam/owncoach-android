package app.owncoach

import android.app.Application
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerFragmentDestinations

class OwnCoachApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Replace ALL registered fragment destinations with our custom fragment,
        // which hides the native toolbar. This covers both start and push navigations.
        Hotwire.defaultFragmentDestination = OwnCoachWebFragment::class
        Hotwire.registerFragmentDestinations(OwnCoachWebFragment::class)

        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                assetFilePath = "json/configuration.json",
                remoteFileUrl = "https://owncoach.app/configurations/android_v1.json"
            )
        )
    }
}
