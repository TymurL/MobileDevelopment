package ua.kpi.comsys.iv7108

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class TimeTLTest {

    @Test
    @Parameters(value = [
        " 0, 0, 0, 12:00:00 AM",
        " 1, 0, 0, 01:00:00 AM",
        "12, 0, 0, 12:00:00 PM",
        "13, 0, 0, 01:00:00 PM"
    ])
    fun testToString(hours: Int, minutes: Int, seconds: Int, expected: String) {
        assertEquals(expected, TimeTL(hours, minutes, seconds).toString())
    }

    @Test
    @Parameters(value = [
        " 1, 30,  0,  2, 20,  0, 03:50:00 AM",
        "23, 59, 59,  0,  0,  1, 12:00:00 AM",
        "23, 59, 59, 12,  0,  1, 12:00:00 PM",
        "23, 59, 59,  1,  0,  1, 01:00:00 AM",
        "23, 59, 59, 23, 59, 59, 11:59:58 PM"
    ])
    fun testAdd(hours1: Int, minutes1: Int, seconds1: Int, hours2: Int, minutes2: Int, seconds2: Int, expected: String) {
        val timeTL = TimeTL(hours1, minutes1, seconds1)
            .add(TimeTL(hours2, minutes2, seconds2))
        assertEquals(expected, timeTL.toString())
    }

    @Test
    @Parameters(value = [
        " 4, 50,  0,  2, 24,  0, 02:26:00 AM",
        " 4, 50,  0,  2, 24, 50, 02:25:10 AM",
        " 0,  0,  0,  0,  0,  1, 11:59:59 PM",
        " 0,  0,  0, 23, 59, 59, 12:00:01 AM",
        " 5,  0,  1, 10,  0,  0, 07:00:01 PM"
    ])
    fun testDiff(hours1: Int, minutes1: Int, seconds1: Int, hours2: Int, minutes2: Int, seconds2: Int, expected: String) {
        val timeTL = TimeTL(hours1, minutes1, seconds1)
            .diff(TimeTL(hours2, minutes2, seconds2))
        assertEquals(expected, timeTL.toString())
    }
}