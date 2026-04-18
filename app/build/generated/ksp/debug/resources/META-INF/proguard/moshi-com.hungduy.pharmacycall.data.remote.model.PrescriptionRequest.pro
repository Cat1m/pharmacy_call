-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionRequest
-keepnames class com.hungduy.pharmacycall.data.remote.model.PrescriptionRequest
-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionRequest
-keep class com.hungduy.pharmacycall.data.remote.model.PrescriptionRequestJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
