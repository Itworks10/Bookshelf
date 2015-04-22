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

import java.util.HashMap;
import java.util.Map;
import tk.itworks10.bookshelf.model.entity.Publisher;
import tk.itworks10.bookshelf.model.entity.Role;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class PublisherTest {

    /**
     * Test the default constructor of classs Publisher.
     */
    @Test
    public void testDefaultConstructor() {
        System.out.println("getName");
        Publisher instance = new Publisher();
        assertNull(instance.getId());
        assertEquals("", instance.getName());
    }

    /**
     * Test method CompareTo from class Entity if this object id equals argument id.
     */
    @Test
    public void testCompareToEqual() {
        Publisher o = new Publisher(new Long(0), "");
        Publisher instance = new Publisher(new Long(0), "");
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically less than argument id.
     */
    @Test
    public void testCompareToInf() {
        Publisher o = new Publisher(new Long(1), "");
        Publisher instance = new Publisher(new Long(0), "");
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically more than argument id.
     */
    @Test
    public void testCompareToSup() {
        Publisher o = new Publisher(new Long(0), "");
        Publisher instance = new Publisher(new Long(1), "");
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method equals from class Entity if this object id points to the same object.
     */
    @Test
    public void testEqualsValid() {
        Publisher instance = new Publisher(new Long(0), "");
        Publisher o = instance;
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't point to the same object.
     */
    @Test
    public void testEqualsInvalid() {
        Publisher instance = new Publisher(new Long(0), "");
        Publisher o = new Publisher(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id.
     */
    @Test
    public void testEqualsIdEntityValid() {
        Publisher o = new Publisher(new Long(0), "");
        Publisher instance = new Publisher(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id.
     */
    @Test
    public void testEqualsIdEntityInvalid() {
        Publisher o = new Publisher(new Long(0), "");
        Publisher instance = new Publisher(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleValid() {
        Publisher o = new Publisher(new Long(0), "title");
        Publisher instance = new Publisher(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleInvalid() {
        Publisher o = new Publisher(new Long(0), "title");
        Publisher instance = new Publisher(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object type doesn't match argument type.
     */
    @Test
    public void testEqualsDifferentEntity() {
        Role o = new Role(new Long(0), "title");
        Publisher instance = new Publisher(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument Long.
     */
    @Test
    public void testEqualsIdLongValid() {
        Long o = new Long(0);
        Publisher instance = new Publisher(new Long(0), "");
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument Long.
     */
    @Test
    public void testEqualsIdLongInvalid() {
        Long o = new Long(0);
        Publisher instance = new Publisher(new Long(1), "");
        assertFalse(instance.equals(o));
    }

    /**
     * Test the map from method equals.
     */
    @Test
    public void testMapEmpty() {
        Publisher publisher = new Publisher(new Long(1), "publisher");
        Map<String, Object> params = new HashMap();
        assertFalse(publisher.equals(params));
    }

    @Test
    public void testMapWrongKey() {
        Publisher publisher = new Publisher(new Long(1), "publisher");
        Map<String, Object> params = new HashMap();
        params.put("test1", "asez");
        params.put("test2", null);
        params.put("test3", "");
        assertFalse(publisher.equals(params));
    }

    @Test
    public void testMapEmptyThisNull() {
        Publisher publisher = new Publisher(new Long(1), null);
        Map<String, Object> params = new HashMap();
        assertFalse(publisher.equals(params));

    }

    @Test
    public void testMapNotEgalThis() {
        Publisher publisher = new Publisher(new Long(1), "publisher");
        Map<String, Object> params = new HashMap();
        params.put("name", "test1");
        assertFalse(publisher.equals(params));
    }

    @Test
    public void testMapThisNull() {
        Publisher publisher = new Publisher(new Long(1), null);
        Map<String, Object> params = new HashMap();
        params.put("name", "test1");
        assertFalse(publisher.equals(params));
    }

    @Test
    public void testMapEqualThis() {
        Publisher publisher = new Publisher(new Long(1), "test1");
        Map<String, Object> params = new HashMap();
        params.put("name", "test1");
        assertTrue(publisher.equals(params));
    }
}
