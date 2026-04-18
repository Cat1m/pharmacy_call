-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
-keepnames class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
-keep class com.hungduy.pharmacycall.data.remote.model.PrescriptionModelJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-if class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
-keepclassmembers class com.hungduy.pharmacycall.data.remote.model.PrescriptionModel {
    public synthetic <init>(java.lang.String,java.lang.String,com.hungduy.pharmacycall.data.remote.model.ToaThuoc,java.util.List,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
