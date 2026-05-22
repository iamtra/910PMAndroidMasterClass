package kh.com.pheaktra.developer.basic.android.util

import android.content.Context
import android.net.Uri

enum class FileType(val type: String) {
    IMAGE("image"),
    VIDEO("video"),
    UNKNOWN("unknown")
}

object FileUtils {

    /**
     * Get the file type based on the URI
     */
    fun getFileType(context: Context, uri: Uri): String {
        val mimeType = context.contentResolver.getType(uri)

        return when {
            mimeType?.startsWith("image/") == true -> FileType.IMAGE.type
            mimeType?.startsWith("video/") == true -> FileType.VIDEO.type
            else -> FileType.UNKNOWN.type
        }
    }
}