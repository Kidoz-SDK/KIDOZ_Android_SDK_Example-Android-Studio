# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\User\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:
#-keepattribute InnerClasses

-verbose
-optimizationpasses 5
-dontusemixedcaseclassnames
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontwarn android.support.**
-keepattributes *Annotation*
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault


-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep class com.kidoz.sdk.api.**{
   *;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
 }

-keepclassmembers class * {
     @android.webkit.JavascriptInterface <methods>;
 }
-keepattributes JavascriptInterface

-dontwarn okio.**
-keep class com.squareup.okhttp3.** {*;}
-dontwarn com.squareup.okhttp3.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-keep public class pl.droidsonroids.gif.GifIOException{<init>(int);}
-keep class pl.droidsonroids.gif.GifInfoHandle{<init>(long,int,int,int);}

-dontwarn android.webkit.**
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient
-keep public class android.webkit.WebView