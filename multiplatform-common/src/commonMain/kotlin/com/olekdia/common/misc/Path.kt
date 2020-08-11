package com.olekdia.common.misc

import com.olekdia.common.extensions.toPath

class Path(val fullPath: String) {

    val path: String
        get() = fullPath.lastIndexOf('/').let { index ->
            if (index >= 0) fullPath.substring(0, index + 1) else ""
        }

    val pathName: String
        get() = fullPath.lastIndexOf('.').let { index ->
            if (index >= 0) fullPath.substring(0, index) else fullPath
        }

    val pathNameExtension: String
        get() = fullPath

    val name: String
        get() = nameExtension.let {
            it.lastIndexOf('.').let { index ->
                if (index >= 0) it.substring(0, index) else it
            }
        }

    val nameExtension: String
        get() = fullPath.lastIndexOf('/').let { index ->
            if (index >= 0) fullPath.substring(index + 1) else fullPath
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

    fun withPath(path: String): Path =
        (path + this.nameExtension).toPath()

    fun withNameExtension(nameExtension: String): Path =
        (this.path + nameExtension).toPath()

    fun withName(name: String): Path =
        this.extension.let { ext ->
            (this.path + name + (if (ext.isEmpty()) ext else ".$ext")).toPath()
        }

    fun withExtension(ext: String): Path =
        (this.pathName + if (ext.isEmpty()) "" else ".${ext}").toPath()
}