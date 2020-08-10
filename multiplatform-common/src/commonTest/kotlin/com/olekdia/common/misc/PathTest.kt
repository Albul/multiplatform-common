package com.olekdia.common.misc

import kotlin.test.*

class PathTest {

    @Test
    fun path() {
        assertEquals(
            "/home/user/",
            Path("/home/user/some.txt").path
        )
        assertEquals(
            "http://pranabreath.info/",
            Path("http://pranabreath.info/main.html").path
        )
        assertEquals(
            "file://home/lib/",
            Path("file://home/lib/").path
        )
        assertEquals(
            "file://home/lib//",
            Path("file://home/lib//").path
        )
    }

    @Test
    fun pathName() {
        assertEquals(
            "/home/user/some",
            Path("/home/user/some.txt").pathName
        )
        assertEquals(
            "http://pranabreath.info/main",
            Path("http://pranabreath.info/main.html").pathName
        )
        assertEquals(
            "file://home/lib/",
            Path("file://home/lib/").pathName
        )
        assertEquals(
            "file://home/lib//",
            Path("file://home/lib//").pathName
        )
    }

    @Test
    fun nameExtension() {
        assertEquals(
            "some.txt",
            Path("/home/user/some.txt").nameExtension
        )
        assertEquals(
            "main.html",
            Path("http://pranabreath.info/main.html").nameExtension
        )
        assertEquals(
            "",
            Path("file://home/lib/").nameExtension
        )
        assertEquals(
            ".txt",
            Path("file://home/lib/.txt").nameExtension
        )
        assertEquals(
            "no.tar.gz",
            Path("file://home/lib/no.tar.gz").nameExtension
        )
    }

    @Test
    fun name() {
        assertEquals(
            "some",
            Path("/home/user/some.txt").name
        )
        assertEquals(
            "main",
            Path("http://pranabreath.info/main.html").name
        )
        assertEquals(
            "",
            Path("file://home/lib/").name
        )
        assertEquals(
            "",
            Path("file://home/lib/.txt").name
        )
        assertEquals(
            "no.tar",
            Path("file://home/lib/no.tar.gz").name
        )
    }

    @Test
    fun extension() {
        assertEquals(
            "txt",
            Path("/home/user/some.txt").extension
        )
        assertEquals(
            "html",
            Path("http://pranabreath.info/main.html").extension
        )
        assertEquals(
            "",
            Path("file://home/lib/").extension
        )
        assertEquals(
            "txt",
            Path("file://home/lib/.txt").extension
        )
        assertEquals(
            "gz",
            Path("file://home/lib/no.tar.gz").extension
        )
    }

    @Test
    fun isFilePath() {
        assertTrue(
            Path("/home/user/some.txt").isFilePath
        )
        assertFalse(
            Path("http://pranabreath.info/main.html").isFilePath
        )
        assertFalse(
            Path("content://home/lib/").isFilePath
        )
        assertTrue(
            Path("file://home/lib/.txt").isFilePath
        )
        assertTrue(
            Path("file://home/lib/no.tar.gz").isFilePath
        )
    }

    @Test
    fun isHttpPath() {
        assertFalse(
            Path("/home/user/some.txt").isHttpPath
        )
        assertTrue(
            Path("http://pranabreath.info/main.html").isHttpPath
        )
        assertFalse(
            Path("content://home/lib/").isHttpPath
        )
        assertTrue(
            Path("https://blabla.com").isHttpPath
        )
        assertFalse(
            Path("file://home/lib/.txt").isHttpPath
        )
        assertFalse(
            Path("file://home/lib/no.tar.gz").isHttpPath
        )
    }

    @Test
    fun isFtpPath() {
        assertFalse(
            Path("/home/user/some.txt").isFtpPath
        )
        assertFalse(
            Path("http://pranabreath.info/main.html").isFtpPath
        )
        assertTrue(
            Path("ftp://pranabreath.info/main.html").isFtpPath
        )
        assertFalse(
            Path("content://home/lib/").isFtpPath
        )
        assertTrue(
            Path("ftps://blabla.com").isFtpPath
        )
        assertFalse(
            Path("file://home/lib/.txt").isFtpPath
        )
        assertFalse(
            Path("file://home/lib/no.tar.gz").isFtpPath
        )
    }

    @Test
    fun isContentPath() {
        assertFalse(
            Path("/home/user/some.txt").isContentPath
        )
        assertFalse(
            Path("http://pranabreath.info/main.html").isContentPath
        )
        assertTrue(
            Path("content://home/lib/").isContentPath
        )
        assertFalse(
            Path("https://blabla.com").isContentPath
        )
        assertFalse(
            Path("file://home/lib/.txt").isContentPath
        )
        assertFalse(
            Path("file://home/lib/no.tar.gz").isContentPath
        )
    }

    @Test
    fun withPath() {
        assertEquals(
            "/root/etc/some.txt",
            Path("/home/user/some.txt").withPath("/root/etc/").fullPath
        )
        assertEquals(
            "/home/user/main.html",
            Path("http://pranabreath.info/main.html").withPath("/home/user/").fullPath
        )
        assertEquals(
            "/home/lib/apache/",
            Path("file://home/lib/").withPath("/home/lib/apache/").fullPath
        )
        assertEquals(
            "/home/lib/apache/.txt",
            Path("file://home/lib/.txt").withPath("/home/lib/apache/").fullPath
        )
        assertEquals(
            "/usr/lib/apache/no.tar.gz",
            Path("file://home/lib/no.tar.gz").withPath("/usr/lib/apache/").fullPath
        )
    }

    @Test
    fun withNameExtension() {
        assertEquals(
            "/home/user/pupkin.tar.gz",
            Path("/home/user/some.txt").withNameExtension("pupkin.tar.gz").fullPath
        )
        assertEquals(
            "http://pranabreath.info/root.txt",
            Path("http://pranabreath.info/main.html").withNameExtension("root.txt").fullPath
        )
        assertEquals(
            "file://home/lib/image.jpg",
            Path("file://home/lib/").withNameExtension("image.jpg").fullPath
        )
        assertEquals(
            "file://home/lib/.html",
            Path("file://home/lib/.txt").withNameExtension(".html").fullPath
        )
        assertEquals(
            "file://home/lib/yes.tar.bz2",
            Path("file://home/lib/no.tar.gz").withNameExtension("yes.tar.bz2").fullPath
        )
    }

    @Test
    fun withName() {
        assertEquals(
            "/home/user/other.txt",
            Path("/home/user/some.txt").withName("other").fullPath
        )
        assertEquals(
            "http://pranabreath.info/root.txt.html",
            Path("http://pranabreath.info/main.html").withName("root.txt").fullPath
        )
        assertEquals(
            "file://home/lib/image",
            Path("file://home/lib/").withName("image").fullPath
        )
        assertEquals(
            "file://home/lib/car.txt",
            Path("file://home/lib/.txt").withName("car").fullPath
        )
        assertEquals(
            "file://home/lib/yes.gz",
            Path("file://home/lib/no.tar.gz").withName("yes").fullPath
        )
    }

    @Test
    fun withExtension() {
        assertEquals(
            "/home/user/some.php",
            Path("/home/user/some.txt").withExtension("php").fullPath
        )
        assertEquals(
            "http://pranabreath.info/main.xml",
            Path("http://pranabreath.info/main.html").withExtension("xml").fullPath
        )
        assertEquals(
            "file://home/lib/.png",
            Path("file://home/lib/").withExtension("png").fullPath
        )
        assertEquals(
            "file://home/lib/.c",
            Path("file://home/lib/.txt").withExtension("c").fullPath
        )
        assertEquals(
            "file://home/lib/no.tar.bz2",
            Path("file://home/lib/no.tar.gz").withExtension("bz2").fullPath
        )
    }
}