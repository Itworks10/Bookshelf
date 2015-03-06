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
package tk.itworks10.bookshelf.model.entity;

import tk.itworks10.bookshelf.model.entity.Keyword;
import tk.itworks10.bookshelf.model.entity.Role;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class KeywordTest {

    /**
     * Test the default constructor of classs Keyword.
     */
    @Test
    public void testDefaultConstructor() {
        Keyword instance = new Keyword();
        assertNull(instance.getId());
        assertEquals("", instance.getName());
    }

    /**
     * Test method CompareTo from class Entity if this object id equals argument id.
     */
    @Test
    public void testCompareToEqual() {
        Keyword o = new Keyword(new Long(0), "");
        Keyword instance = new Keyword(new Long(0), "");
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically less than argument id.
     */
    @Test
    public void testCompareToInf() {
        Keyword o = new Keyword(new Long(1), "");
        Keyword instance = new Keyword(new Long(0), "");
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically more than argument id.
     */
    @Test
    public void testCompareToSup() {
        Keyword o = new Keyword(new Long(0), "");
        Keyword instance = new Keyword(new Long(1), "");
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method equals from class Entity if this object id points to the same object.
     */
    @Test
    public void testEqualsValid() {
        Keyword instance = new Keyword(new Long(0), "");
        Keyword o = instance;
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't point to the same object.
     */
    @Test
    public void testEqualsInvalid() {
        Keyword instance = new Keyword(new Long(0), "");
        Keyword o = new Keyword(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id.
     */
    @Test
    public void testEqualsIdEntityValid() {
        Keyword o = new Keyword(new Long(0), "");
        Keyword instance = new Keyword(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id.
     */
    @Test
    public void testEqualsIdEntityInvalid() {
        Keyword o = new Keyword(new Long(0), "");
        Keyword instance = new Keyword(new Long(1), "");
        assertFalse(instance.equals(o));
    }


     /**
     * Test method equals from class Entity if this object id matches to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleValid() {
        Keyword o = new Keyword(new Long(0), "title");
        Keyword instance = new Keyword(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleInvalid() {
        Keyword o = new Keyword(new Long(0), "title");
        Keyword instance = new Keyword(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object type doesn't match argument type.
     */
    @Test
    public void testEqualsDifferentEntity() {
        Role o = new Role(new Long(0), "title");
        Keyword instance = new Keyword(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument Long.
     */
    @Test
    public void testEqualsIdLongValid() {
        Long o = new Long(0);
        Keyword instance = new Keyword(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument Long.
     */
    @Test
    public void testEqualsIdLongInvalid() {
        Long o = new Long(0);
        Keyword instance = new Keyword(new Long(1), "");
        assertFalse(instance.equals(o));
    }
}
