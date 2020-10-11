package ua.kpi.comsys.iv7108

import java.util.Date

class TimeTL {
    private var hours: Int
    private var minutes: Int
    private var seconds: Int

    constructor() {
        this.hours = 0
        this.minutes = 0
        this.seconds = 0
    }

    constructor(hours: Int, minutes: Int, seconds: Int) {
        this.hours = if (hours in 0 until HOURS_PER_DAY) hours else throw IllegalArgumentException("Hours out of range")
        this.minutes = if (minutes in 0 until MINUTES_PER_HOUR) minutes else throw IllegalArgumentException("Minutes out of range")
        this.seconds = if (seconds in 0 until SECONDS_PER_MINUTE) seconds else throw IllegalArgumentException("Seconds out of range")
    }

    constructor(date: Date) {
        this.hours = date.hours
        this.minutes = date.minutes
        this.seconds = date.seconds
    }

    /**
     * return a String value in `hh:mm:ss ZZ` format,
     * where hh is hours, mm is minutes, ss is seconds, ZZ is `AM` or `PM` (12-hour format)
     */
    override fun toString(): String {
        return "%02d:%02d:%02d %s".format(if (hours == 0 || hours == 12)
                                              12
                                          else
                                              hours % 12,
                                          minutes,
                                          seconds,
                                          if (hours < 12)
                                              "AM"
                                          else
                                              "PM")
    }

    /**
     * return a TimeXY object that represents the sum (by the corresponding properties)
     * of current and input objects
     */
    fun add(time: TimeTL): TimeTL {
        val (hours, minutes, seconds) = calculate(convertInSeconds(this) + convertInSeconds(time))
        return TimeTL(hours, minutes, seconds)
    }

    /**
     * return a TimeXY object that represents the difference (by the corresponding properties)
     * of current and input objects.
     */
    fun diff(time: TimeTL): TimeTL {
        val (hours, minutes, seconds) = calculate(convertInSeconds(this) - convertInSeconds(time))
        return TimeTL(hours, minutes, seconds)
    }

    /**
     * return a TimeXY object that represents the sum (by the corresponding properties)
     * of two input objects
     */
    fun add(time1: TimeTL, time2: TimeTL): TimeTL {
        val (hours, minutes, seconds) = calculate(convertInSeconds(time1) + convertInSeconds(time2))
        return TimeTL(hours, minutes, seconds)
    }

    /**
     * return a TimeXY object that represents the difference (by the corresponding properties)
     * of two input objects
     */
    fun diff(time1: TimeTL, time2: TimeTL): TimeTL {
        val (hours, minutes, seconds) = calculate(convertInSeconds(time1) - convertInSeconds(time2))
        return TimeTL(hours, minutes, seconds)
    }

    private fun convertInSeconds(time: TimeTL): Int {
        return time.hours * SECONDS_PER_HOUR + time.minutes * SECONDS_PER_MINUTE + time.seconds
    }

    @Suppress("NAME_SHADOWING")
    private fun calculate(inSeconds: Int): Triple<Int, Int, Int> {
        var inSeconds = inSeconds
        if (inSeconds < 0)
            inSeconds += HOURS_PER_DAY * SECONDS_PER_HOUR
        var hours = inSeconds / SECONDS_PER_HOUR
        if (hours > 23)
            hours %= HOURS_PER_DAY
        val minutes = inSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE
        val seconds = inSeconds % SECONDS_PER_HOUR % SECONDS_PER_MINUTE
        return Triple(hours, minutes, seconds)
    }

    companion object {
        private const val SECONDS_PER_MINUTE = 60
        private const val MINUTES_PER_HOUR = 60
        private const val HOURS_PER_DAY = 24
        private const val SECONDS_PER_HOUR = MINUTES_PER_HOUR * SECONDS_PER_MINUTE
    }
}