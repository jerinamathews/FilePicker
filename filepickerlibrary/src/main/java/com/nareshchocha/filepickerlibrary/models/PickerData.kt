package com.nareshchocha.filepickerlibrary.models

import android.os.Build
import android.os.Parcelable
import android.provider.MediaStore
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.nareshchocha.filepickerlibrary.R
import com.nareshchocha.filepickerlibrary.utilities.appConst.Const
import kotlinx.parcelize.Parcelize
import java.io.File

@Keep
@Parcelize
data class PickerData(
    val mPopUpConfig: PopUpConfig? = null,
    val listIntents: List<BaseConfig> = emptyList(),
) : Parcelable

@Keep
@Parcelize
data class PopUpConfig(
    val chooserTitle: String? = null,
    @LayoutRes val layoutId: Int = R.layout.item_pop_up,
    val mPopUpType: PopUpType = PopUpType.BOTTOM_SHEET,
    @RecyclerView.Orientation val mOrientation: Int = RecyclerView.VERTICAL,
) : Parcelable

@Parcelize
data class ImageCaptureConfig(
    @DrawableRes override val popUpIcon: Int = R.drawable.ic_camera,
    override val popUpText: String = "Camera",
    val mFolder: File? = null,
    val fileName: String = Const.DefaultPaths.defaultImageFile,
    override val askPermissionTitle: String? = null,
    override val askPermissionMessage: String? = null,
    override val settingPermissionTitle: String? = null,
    override val settingPermissionMessage: String? = null,
) : Parcelable, BaseConfig(
    popUpIcon,
    popUpText,
    askPermissionTitle,
    askPermissionMessage,
    settingPermissionTitle,
    settingPermissionMessage,
)

@Parcelize
data class VideoCaptureConfig(
    @DrawableRes override val popUpIcon: Int = R.drawable.ic_video,
    override val popUpText: String = "Video",
    val mFolder: File? = null,
    val fileName: String = Const.DefaultPaths.defaultVideoFile,
    val maxSeconds: Int? = null,
    val maxSizeLimit: Long? = null,
    val isHighQuality: Boolean? = null,
    override val askPermissionTitle: String? = null,
    override val askPermissionMessage: String? = null,
    override val settingPermissionTitle: String? = null,
    override val settingPermissionMessage: String? = null,
) : Parcelable, BaseConfig(
    popUpIcon,
    popUpText,
    askPermissionTitle,
    askPermissionMessage,
    settingPermissionTitle,
    settingPermissionMessage,
)

@Parcelize
data class PickMediaConfig(
    @DrawableRes override val popUpIcon: Int = R.drawable.ic_media,
    override val popUpText: String = "Pick Media",
    val allowMultiple: Boolean = false,
    val maxFiles: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        MediaStore.getPickImagesMaxLimit()
    } else {
        Int.MAX_VALUE
    },
    val mPickMediaType: PickMediaType = ImageAndVideo,
    override val askPermissionTitle: String? = null,
    override val askPermissionMessage: String? = null,
    override val settingPermissionTitle: String? = null,
    override val settingPermissionMessage: String? = null,
) : Parcelable, BaseConfig(
    popUpIcon,
    popUpText,
    askPermissionTitle,
    askPermissionMessage,
    settingPermissionTitle,
    settingPermissionMessage,
) {
    fun getPickMediaType(input: PickMediaType): String? {
        return when (input) {
            is ImageOnly -> "image/*"
            is VideoOnly -> "video/*"
            is ImageAndVideo -> null
        }
    }
}

@Parcelize
data class DocumentFilePickerConfig(
    @DrawableRes override val popUpIcon: Int = R.drawable.ic_file,
    override val popUpText: String = "File Media",
    val allowMultiple: Boolean = false,
    val maxFiles: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        MediaStore.getPickImagesMaxLimit()
    } else {
        Int.MAX_VALUE
    },
    val mMimeTypes: List<String> = listOf("*/*"),
    override val askPermissionTitle: String? = null,
    override val askPermissionMessage: String? = null,
    override val settingPermissionTitle: String? = null,
    override val settingPermissionMessage: String? = null,
) : Parcelable, BaseConfig(
    popUpIcon,
    popUpText,
    askPermissionTitle,
    askPermissionMessage,
    settingPermissionTitle,
    settingPermissionMessage,
)

@Keep
@Parcelize
enum class PopUpType : Parcelable {
    BOTTOM_SHEET, DIALOG;

    fun isDialog() = this == DIALOG
}

@Parcelize
sealed interface PickMediaType : Parcelable

@Parcelize
object ImageOnly : PickMediaType

@Parcelize
object VideoOnly : PickMediaType

@Parcelize
object ImageAndVideo : PickMediaType
