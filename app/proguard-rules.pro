# ProGuard rules for PharmacyCallApp
# Add project specific ProGuard rules here.

# --- Moshi ---
-keep @com.squareup.moshi.JsonClass class * { *; }
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class * {
    @com.squareup.moshi.FromJson *;
    @com.squareup.moshi.ToJson *;
}

# --- Retrofit ---
-keep class retrofit2.** { *; }
-keepattributes Signature, Exceptions
-keepattributes RuntimeVisibleAnnotations
-dontwarn retrofit2.**

# --- OkHttp ---
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# --- SignalR ---
-keep class com.microsoft.signalr.** { *; }
-dontwarn com.microsoft.signalr.**

# --- Hilt ---
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.HiltAndroidApp class * { *; }
-keep @dagger.hilt.android.AndroidEntryPoint class * { *; }
-dontwarn dagger.hilt.**

# --- Kotlin ---
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

# --- Application models ---
-keep class com.hungduy.pharmacycall.data.remote.model.** { *; }
