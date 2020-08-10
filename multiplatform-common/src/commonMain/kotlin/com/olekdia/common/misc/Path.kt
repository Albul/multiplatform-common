package com.olekdia.common.misc

class Path(val fullPath: String) {

    val path: String
        get() = fullPath.lastIndexOf('/').let { index ->
            if (index > 0) fullPath.substring(0, index + 1) else fullPath
        }

    val pathWithName: String
        get() = fullPath.lastIndexOf('.').let { index ->
            if (index > 0) fullPath.substring(0, index) else fullPath
        }

    val pathWithNameWithExtension: String
        get() = fullPath

    val name: String
        get() = nameWithExtension.let {
            it.lastIndexOf('.').let { index ->
                if (index >= 0) it.substring(0, index) else it
            }
        }

    val nameWithExtension: String
        get() = fullPath.lastIndexOf('/').let { index ->
            if (index > 0) fullPath.substring(index + 1) else fullPath
        }

    val extension: String
        get() = fullPath.lastIndexOf('.').let { index ->
            if (index >= 0) fullPath.substring(index + 1) else ""
        }

    val isFilePath: Boolean
        get() = fullPath.startsWith("/")
            || fullPath.startsWith("file://")

    val isHttpPath: Boolean
        get() = fullPath.startsWith("https://")
            || fullPath.startsWith("http://")

    val isFtpPath: Boolean
        get() =  fullPath.startsWith("ftp://")
                || fullPath.startsWith("ftps://")

    val isContentPath: Boolean
        get() = fullPath.startsWith("content://")
}