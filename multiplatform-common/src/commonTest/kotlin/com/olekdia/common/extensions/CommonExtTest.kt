package com.olekdia.common.extensions

import kotlin.test.*

class CommonExtTest {

    @Test
    fun ifNotNull_withNotNullReceiver() {
        val someObj = ""

        var isCalled = false
        someObj.ifNotNull { isCalled = true }

        assertTrue(isCalled)
    }

    @Test
    fun ifNotNull_withNullReceiver() {
        val someObj = null

        var isCalled = false
        someObj.ifNotNull { isCalled = true }

        assertFalse(isCalled)
    }

    @Test
    fun ifNull_withNotNullReceiver() {
        val someObj = ""

        var isCalled = false
        someObj.ifNull { isCalled = true }

        assertFalse(isCalled)
    }

    @Test
    fun ifNull_withNullReceiver() {
        val someObj = null

        var isCalled = false
        someObj.ifNull { isCalled = true }

        assertTrue(isCalled)
    }

//--------------------------------------------------------------------------------------------------
//  ifNotNull
//--------------------------------------------------------------------------------------------------

    @Test
    fun ifNotNull_withNotNull1Arg() {
        val arg1: String? = "1"

        var isCalled = false
        val result = ifNotNull(
            arg1
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertEquals("1 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNull_withNotNull2Args() {
        val arg1: String? = "1"
        val arg2: String? = "2"

        var isCalled = false
        val result = ifNotNull(
            arg1, arg2
        ) { one, two ->
            isCalled = true
            "${one} ${two} called"
        }

        assertEquals("1 2 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNull_withNotNull3Args() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val arg3: String? = "3"

        var isCalled = false
        val result = ifNotNull(
            arg1, arg2, arg3
        ) { one, two, three ->
            isCalled = true
            "${one} ${two} ${three} called"
        }

        assertEquals("1 2 3 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNull_withNotNull4Args() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val arg3: String? = "3"
        val arg4: String? = "4"

        var isCalled = false
        val result = ifNotNull(
            arg1, arg2, arg3, arg4
        ) { one, two, three, four ->
            isCalled = true
            "${one} ${two} ${three} ${four} called"
        }

        assertEquals("1 2 3 4 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNull_withNull1Arg() {
        val arg1: String? = null

        var isCalled = false
        val result = ifNotNull(
            arg1
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }


    @Test
    fun ifNotNull_withNull2Args() {
        val arg1: String? = "1"
        val arg2: String? = null

        var isCalled = false
        val result = ifNotNull(
            arg1, arg2
        ) { one, two ->
            isCalled = true
            "${one} ${two} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNull_withNull3Args() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val arg3: String? = null

        var isCalled = false
        val result = ifNotNull(
            arg1, arg2, arg3
        ) { one, two, three ->
            isCalled = true
            "${one} ${two} ${three} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

//--------------------------------------------------------------------------------------------------
//  ifNotNullAnd
//--------------------------------------------------------------------------------------------------

    @Test
    fun ifNotNullAnd_withNotNull1Arg_withTrueArg_called() {
        val arg1: String? = "1"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, num > 2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertEquals("1 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNull1Arg_withTrueArg_notCalled() {
        val arg1: String? = null
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, num > 2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull1Arg_withFalseArg_notCalled() {
        val arg1: String? = "1"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, num < 2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull2Args_withTrueArg_called() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, num > 2
        ) { one, two ->
            isCalled = true
            "${one} ${two} called"
        }

        assertEquals("1 2 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNull2Args_withTrueArg_notCalled() {
        val arg1: String? = "1"
        val arg2: String? = null
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, num > 2
        ) { one, two ->
            isCalled = true
            "${one} ${two} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull2Args_withFalseArg_notCalled() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, num < 2
        ) { one, two ->
            isCalled = true
            "${one} ${two} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull3Args_withTrueArg_called() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val arg3: String? = "3"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, arg3, num > 2
        ) { one, two, three ->
            isCalled = true
            "${one} ${two} ${three} called"
        }

        assertEquals("1 2 3 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNull3Args_withTrueArg_notCalled() {
        val arg1: String? = "1"
        val arg2: String? = null
        val arg3: String? = "3"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, arg3, num > 2
        ) { one, two, three ->
            isCalled = true
            "${one} ${two} ${three} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull3Args_withFalseArg_notCalled() {
        val arg1: String? = "1"
        val arg2: String? = "2"
        val arg3: String? = "3"
        val num = 3

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, arg2, arg3, num < 2
        ) { one, two, three ->
            isCalled = true
            "${one} ${two} ${three} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNotNull1Arg_withTrue2Args_called() {
        val arg1: String? = "1"
        val bool1 = 3 > 2
        val bool2 = 3 > 1

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, bool1, bool2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertEquals("1 called", result)
        assertTrue(isCalled)
    }

    @Test
    fun ifNotNullAnd_withNull1Arg_withTrue2Args_notCalled() {
        val arg1: String? = null
        val bool1 = 3 > 2
        val bool2 = 3 > 1

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, bool1, bool2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

    @Test
    fun ifNotNullAnd_1arg_2boolExp_withNotNull1Arg_withFalse2Args_notCalled() {
        val arg1: String? = "1"
        val bool1 = 3 > 2
        val bool2 = 3 > 5

        var isCalled = false
        val result = ifNotNullAnd(
            arg1, bool1, bool2
        ) { one ->
            isCalled = true
            "${one} called"
        }

        assertNull(result)
        assertFalse(isCalled)
    }

//--------------------------------------------------------------------------------------------------
//  ifNotNullElse
//--------------------------------------------------------------------------------------------------

    @Test
    fun ifNotNullElse_withNotNull3Args() {
        val arg: String? = "1"

        var ifCalled = false
        var elseCalled = false
        val result = ifNotNullElse(
            arg,
            { one ->
                ifCalled = true
                "${one} if called"
            },
            {
                elseCalled = true
                "else called"
            }
        )

        assertTrue(ifCalled)
        assertFalse(elseCalled)
        assertEquals("1 if called", result)
    }

    @Test
    fun ifNotNullElse_withNull1Arg() {
        val arg: String? = null

        var ifCalled = false
        var elseCalled = false
        val result = ifNotNullElse(
            arg,
            { one ->
                ifCalled = true
                "${one} if called"
            },
            {
                elseCalled = true
                "else called"
            }
        )

        assertFalse(ifCalled)
        assertTrue(elseCalled)
        assertEquals("else called", result)
    }


    @Test
    fun ifNotNullElse_withNotNull2Args() {
        val arg1: String? = "1"
        val arg2: String? = "2"

        var ifCalled = false
        var elseCalled = false
        val result = ifNotNullElse(
            arg1, arg2,
            { one, two ->
                ifCalled = true
                "${one} ${two} if called"
            },
            {
                elseCalled = true
                "else called"
            }
        )

        assertTrue(ifCalled)
        assertFalse(elseCalled)
        assertEquals("1 2 if called", result)
    }

    @Test
    fun ifNotNullElse_withNull2Args() {
        val arg1: String? = "1"
        val arg2: String? = null

        var ifCalled = false
        var elseCalled = false
        val result = ifNotNullElse(
            arg1, arg2,
            { one, two ->
                ifCalled = true
                "${one} ${two} if called"
            },
            {
                elseCalled = true
                "else called"
            }
        )

        assertFalse(ifCalled)
        assertTrue(elseCalled)
        assertEquals("else called", result)
    }

//--------------------------------------------------------------------------------------------------

    @Test
    fun ifNotNullOrFalse_withNotNullReceiver() {
        val receiver: String? = "1"

        var isCalled = false
        val result = receiver.ifNotNullOrFalse {
            isCalled = true
            isCalled
        }

        assertTrue(isCalled)
        assertTrue(result)
    }
    @Test
    fun ifNotNullOrFalse_withNullReceiver() {
        val receiver: String? = null

        var isCalled = false
        val result = receiver.ifNotNullOrFalse {
            isCalled = true
            true
        }

        assertFalse(isCalled)
        assertFalse(result)
    }

    @Test
    fun ifFalse_withFalseReceiver() {
        val value: Boolean? = false

        var isCalled = false
        value.ifFalse { isCalled = true }

        assertTrue(isCalled)
    }

    @Test
    fun ifFalse_withTrueReceiver() {
        val value: Boolean? = true

        var isCalled = false
        value.ifFalse { isCalled = true }

        assertFalse(isCalled)
    }

    @Test
    fun ifFalse_withNullReceiver() {
        val value: Boolean? = null

        var isCalled = false
        value.ifFalse { isCalled = true }

        assertFalse(isCalled)
    }

    @Test
    fun ifTrue_withFalseReceiver() {
        val value: Boolean? = false

        var isCalled = false
        value.ifTrue { isCalled = true }

        assertFalse(isCalled)
    }

    @Test
    fun ifTrue_withTrueReceiver() {
        val value: Boolean? = true

        var isCalled = false
        value.ifTrue { isCalled = true }

        assertTrue(isCalled)
    }

    @Test
    fun ifTrue_withNullReceiver() {
        val value: Boolean? = null

        var isCalled = false
        value.ifTrue { isCalled = true }

        assertFalse(isCalled)
    }

//--------------------------------------------------------------------------------------------------

    @Test
    fun let_with2NullParams() {
        val arg1: String? = null
        val arg2: String? = null

        val result = let(
            arg1, arg2
        ) { a1, a2 ->
            (a1 ?: "null") + (a2 ?: "null")
        }
        assertEquals("nullnull", result)
    }

    @Test
    fun let_with2NotNullParams() {
        val arg1: String? = null
        val arg2: String? = "arg"

        val result = let(
            arg1, arg2
        ) { a1, a2 ->
            (a1 ?: "null") + (a2 ?: "null")
        }
        assertEquals("nullarg", result)
    }

    @Test
    fun let_with3NullParams() {
        val arg1: String? = null
        val arg2: String? = null
        val arg3: String? = null

        val result = let(
            arg1, arg2, arg3
        ) { a1, a2, a3 ->
            (a1 ?: "null") + (a2 ?: "null") + (a3 ?: "null")
        }
        assertEquals("nullnullnull", result)
    }

    @Test
    fun let_with3NotNullParams() {
        val arg1: String? = null
        val arg2: String? = "arg2"
        val arg3: String? = "arg3"

        val result = let(
            arg1, arg2, arg3
        ) { a1, a2, a3 ->
            (a1 ?: "null") + (a2 ?: "null") + (a3 ?: "null")
        }
        assertEquals("nullarg2arg3", result)
    }

    @Test
    fun let_with4NotNullParams() {
        val arg1: String? = null
        val arg2: String? = "arg2"
        val arg3: String? = "arg3"
        val arg4: String? = "arg4"

        val result = let(
            arg1, arg2, arg3, arg4
        ) { a1, a2, a3, a4 ->
            (a1 ?: "null") + (a2 ?: "null") + (a3 ?: "null")  + (a4 ?: "null")
        }
        assertEquals("nullarg2arg3arg4", result)
    }

    @Test
    fun let_with4NullParams() {
        val arg1: String? = null
        val arg2: String? = null
        val arg3: String? = null
        val arg4: String? = null

        val result = let(
            arg1, arg2, arg3, arg4
        ) { a1, a2, a3, a4 ->
            (a1 ?: "null") + (a2 ?: "null") + (a3 ?: "null")  + (a4 ?: "null")
        }
        assertEquals("nullnullnullnull", result)
    }

    @Test
    fun let_withMixParams() {
        val arg1: String? = null
        val arg2: String = "Text"
        val arg3: String? = null
        val arg4: String = "Txt2"

        val result = let(
            arg1, arg2, arg3, arg4
        ) { a1, a2, a3, a4 ->
            (a1 ?: "null") + a2.length + (a3 ?: "null")  + a4
        }
        assertEquals("null4nullTxt2", result)
    }
}