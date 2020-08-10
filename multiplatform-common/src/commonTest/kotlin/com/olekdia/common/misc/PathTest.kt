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
    fun pathWithName() {
        assertEquals(
            "/home/user/some",
            Path("/home/user/some.txt").pathWithName
        )
        assertEquals(
            "http://pranabreath.info/main",
            Path("http://pranabreath.info/main.html").pathWithName
        )
        assertEquals(
            "file://home/lib/",
            Path("file://home/lib/").pathWithName
        )
        assertEquals(
            "file://home/lib//",
            Path("file://home/lib//").pathWithName
        )
    }

    @Test
    fun nameAndExtension() {
        assertEquals(
            "some.txt",
            Path("/home/user/some.txt").nameWithExtension
        )
        assertEquals(
            "main.html",
            Path("http://pranabreath.info/main.html").nameWithExtension
        )
        assertEquals(
            "",
            Path("file://home/lib/").nameWithExtension
        )
        assertEquals(
            ".txt",
            Path("file://home/lib/.txt").nameWithExtension
        )
        assertEquals(
            "no.tar.gz",
            Path("file://home/lib/no.tar.gz").nameWithExtension
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
}