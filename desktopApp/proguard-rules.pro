-dontwarn org.apache.commons.logging.**
-dontwarn org.apache.logging.log4j.**
-dontwarn com.google.common.**
-dontwarn io.pebbletemplates.pebble.**
-dontwarn org.apache.pdfbox.**
-dontwarn org.codehaus.janino.**
-dontwarn org.slf4j.**
-dontwarn kotlinx.coroutines.**
-dontwarn androidx.lifecycle.**
-dontwarn org.jetbrains.compose.resources.**
-dontwarn org.jetbrains.skiko.**
-dontwarn androidx.compose.**
-dontwarn com.google.j2objc.annotations.**
-dontwarn javax.servlet.**
-dontwarn org.apache.tools.ant.**
-dontwarn org.osgi.framework.**
-dontwarn aQute.bnd.annotation.spi.**
-dontwarn com.github.benmanes.caffeine.cache.**
-dontwarn jakarta.servlet.**
-dontwarn org.apache.avalon.framework.logger.**
-dontwarn org.apache.log4j.**
-dontwarn org.apache.log.**
-dontwarn edu.umd.cs.findbugs.annotations.**
-dontwarn org.w3c.dom.**
-dontwarn org.xml.sax.**
-dontwarn javax.xml.**
-dontwarn org.jcp.xml.dsig.internal.dom.**
-dontwarn com.apple.eawt.**
-dontwarn com.apple.eio.**
-dontwarn java.awt.**
-dontwarn sun.misc.**
-dontwarn com.sun.jna.**

-keep public class edu.jas.** {
    public protected *;
}
-dontwarn edu.jas.**
-keepattributes *Annotation*
-keepnames class edu.jas.**$*

# Keep all Kotlin metadata
-keepclassmembers class ** {
    @kotlin.Metadata <methods>;
}
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep class kotlin.coroutines.Continuation
-keep class kotlin.coroutines.intrinsics.IntrinsicsKt

# Keep Compose Desktop specific rules
-keep class org.jetbrains.skia.** { *; }
-keep class org.jetbrains.skiko.** { *; }
-keep class org.jetbrains.compose.desktop.application.internal.** { *; }
-keep class org.jetbrains.compose.desktop.ui.tooling.preview.** { *; }

# Keep all classes that extend from androidx.compose.ui.tooling.preview.PreviewParameterProvider
-keep class * implements androidx.compose.ui.tooling.preview.PreviewParameterProvider { *; }

# Keep all annotations
-keepattributes Annotations

# Keep all public methods in classes that extend from androidx.compose.ui.tooling.preview.PreviewParameterProvider
-keepclassmembers class * implements androidx.compose.ui.tooling.preview.PreviewParameterProvider {
    public <methods>;
}

# Keep all public constructors and methods of your main application class
-keep class MainKt { # Replace with your main class
    public static void main(java.lang.String[]);
}
