/*
 * Copyright (c) 2015, Itworks10 <Alexandre.V.Leger@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package tk.itworks10.bookshelf.utils;

/**
 * Class Date's purpose is to convert 3 int into a new Date type object.
 * 
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class Date {

    /**
     * Constant for error messages.
     */
    public static final String DATE_ERR_1000_DAY_1_TO_31 = "A day should be contained between 1 and 31.";
    public static final String DATE_ERR_1000_DAY_1_TO_28 = "A February day should be contained between 1 and 28 in a non-bissextile year.";
    public static final String DATE_ERR_1000_DAY_1_TO_29 = "A February day should be contained between 1 and 29 in a bissextile year.";
    public static final String DATE_ERR_1000_DAY_1_TO_30 = "A day should be contained between 1 and 30, for this month.";
    public static final String DATE_ERR_2000_MONTH = "A month should be contained between 1 and 12.";
    public static final String DATE_ERR_3000_YEAR = "A year can't be negative or equal to 0.";

    /**
     * Ensure that the day is valid.
     *
     * Month are shifted from 1 to correspond java.util.Date constructor.
     *
     * @param day The day to validate.
     * @param month The month of this day minus 1.
     * @param year The year of this day.
     * @return True if the day is valid, false otherwise.
     */
    public static boolean validateDay(int day, int month, int year) {
        if (day > 31 || day < 1) {
            return false;
        }
        if (month == 1) {
            return (day < 29 || (day == 29 && leapYear(year)));
        }
        if (day == 31) {
           return (month <= 6 && ((month %2) == 0)) || (month >= 7 && ((month %2) != 0));
        }
        return true;
    }

    /**
     * Ensure that the month is valid.
     *
     * A month is comprised between 0 and 11 to correspond java.util.Date constructor.
     * 
     * @param month The month to validate minus 1.
     * @return True if the month is valid, false otherwise.
     */
    public static boolean validateMonth(int month) {
        return month >= 0 && month < 12;
    }

    /**
     * Ensure that the year is valid.
     *
     * The first year start at -1900 to correspond java.util.Date constructor.
     *
     * @param year The year to validate minus 1900.
     * @return True if the year is valid, false otherwise.
     */
    public static boolean validateYear(int year) {
        return year > -1900 && year < Integer.MAX_VALUE;
    }

    /**
     * Check if the year is a leap year or not.
     *
     * @param year The year we want to know is leap year or non leap year.
     * @return True if the year is leap year, false otherwise.
     */
    public static boolean leapYear(int year) {
        return ((year %4) == 0 && (year %100) != 0) || (year %400) == 0;
    }

    /**
     * Return a new object of type java.util.Date composed of the 3 int.
     *
     * Throws different exceptions when one of the methods "validate" return false.
     * @param day The day of that date.
     * @param month The month of that date minus one.
     * @param year The year of that date minus 1900.
     * @return A new object of type Date.
     * @throws InvalidDayException When validateDay return a false, with a different defined message according to the situation.
     * @throws InvalidMonthException When validateMonth return a false, with a defined message.
     * @throws InvalidYearException When validateYear return a false, with a defined message.
     */
    public static java.util.Date parseDate(int day, int month, int year) throws InvalidDayException, InvalidMonthException, InvalidYearException {
        if (!validateDay(day, month, year)) {
            if (day < 1) {
                throw new InvalidDayException(DATE_ERR_1000_DAY_1_TO_31);
            }
            if (day > 31) {
                throw new InvalidDayException(DATE_ERR_1000_DAY_1_TO_31);
            }
            if (month == 1 && leapYear(year) && day > 29) {
                throw new InvalidDayException(DATE_ERR_1000_DAY_1_TO_29);
            }
            if (month == 1 && day > 28) {
                throw new InvalidDayException(DATE_ERR_1000_DAY_1_TO_28);
            }
            if (day > 30 && (month <= 6 && ((month %2) != 0)) || (month >= 7 && ((month %2) == 0))) {
                throw new InvalidDayException(DATE_ERR_1000_DAY_1_TO_30);
            }
        }
        if (!validateMonth(month)) {
            throw new InvalidMonthException(DATE_ERR_2000_MONTH);
        }
        if (!validateYear(year)) {
            throw new InvalidYearException(DATE_ERR_3000_YEAR);
        }
        return new java.util.Date(year, month, day);
    }
}
