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

import org.junit.Test;
import static org.junit.Assert.*;
import tk.itworks10.bookshelf.utils.Date;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class DateTest {

    public DateTest() {
    }

    /**
     * Return true because the day is comprised between 1 and 31.
     */
    @Test
    public void testDayBetween1And31() {
        assertTrue(Date.validateDay(15, 11, 2015));
    }

    /**
     * Return false because the day isn't comprised between 1 and 31.
     */
    @Test
    public void testDayUnder1() {
        assertFalse(Date.validateDay(0, 11, 2015));
    }

    /**
     * Return false because the day isn't comprised between 1 and 31.
     */
    @Test
    public void testDayOver31() {
        assertFalse(Date.validateDay(32, 11, 2015));
    }

    /**
     * Return false because the day isn't comprised between 1 and 28.
     */
    @Test
    public void testDayFebruaryNonLeapYearInvalid() {
        assertFalse(Date.validateDay(29, 1, 2015));
    }

    /**
     * Return true because the day is comprised between 1 and 28.
     */
    @Test
    public void testDayFebruaryNonLeapYearValid() {
        assertTrue(Date.validateDay(28, 1, 2015));
    }

    /**
     * Return false because the day isn't comprised between 1 and 29.
     */
    @Test
    public void testDayFebruaryLeapYearInvalid() {
        assertFalse(Date.validateDay(30, 1, 2012));
    }

    /**
     * Return true because the day is comprised between 1 and 29.
     */
    @Test
    public void testDayFebruaryLeapYearValid() {
        assertTrue(Date.validateDay(29, 1, 2012));
    }

    /**
     * Return true because the day is comprised between 1 and 31.
     */
    @Test
    public void testDayMonth31Days() {
        assertTrue(Date.validateDay(31, 4, 2015));
    }

    /**
     * Return false because the day isn't comprised between 1 and 30.
     */
    @Test
    public void testDayMonth30Days() {
        assertFalse(Date.validateDay(31, 3, 2015));
    }

    /**
     * Return true because the month is comprised between 0 and 11.
     */
    @Test
    public void testMonthValid() {
        assertTrue(Date.validateMonth(0));
    }

    /**
     * Return false because the month isn't comprised between 0 and 11.
     */
    @Test
    public void testMonthInvalid() {
        assertFalse(Date.validateMonth(12));
    }

    /**
     * Return true because the year is comprised between -1899 and the max integer valid.
     */
    @Test
    public void testYearValid() {
        assertTrue(Date.validateYear(-1899));
    }

    /**
     * Return true because the year is comprised between -1899 and the max integer valid.
     */
    @Test
    public void testYearValidPositiveValue() {
        assertTrue(Date.validateYear(115));
    }

    /**
     * Return false because the year isn't comprised between -1899 and the max integer valid.
     */
    @Test
    public void testYearInvalid() {
        assertFalse(Date.validateYear(-1900));
    }

    /**
     * Return true because the year is a leap year.
     */
    @Test
    public void testLeapYearValid() {
        assertTrue(Date.leapYear(2012));
    }

    /**
     * Return false because the year isn't a leap year.
     */
    @Test
    public void testLeapYearInvalid() {
        assertFalse(Date.leapYear(2015));
    }

    /**
     * Ensure that the date created with method parseDate is equals to a date created with java.util.
     * Date constructor.
     * 
     * @throws tk.itworks10.bookshelf.utils.InvalidDayException
     * @throws tk.itworks10.bookshelf.utils.InvalidMonthException
     * @throws tk.itworks10.bookshelf.utils.InvalidYearException
     */
    @Test
    public void testParseDate15December2015() throws InvalidDayException, InvalidMonthException, InvalidYearException {
        java.util.Date date = Date.parseDate(15, 11, 115);
        assertEquals(date, new java.util.Date(115, 11, 15));
    }

}
