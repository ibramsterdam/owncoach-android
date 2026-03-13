# Hotwire Native
-keep class dev.hotwire.** { *; }

# Keep WebView JavaScript interfaces
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Suppress missing annotation classes from error-prone / guava that are
# compile-time only and not present at runtime
-dontwarn javax.lang.model.element.Modifier
-dontwarn com.google.errorprone.annotations.**
-dontwarn com.google.j2objc.annotations.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
