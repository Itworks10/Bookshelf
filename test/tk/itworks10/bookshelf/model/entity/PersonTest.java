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
import tk.itworks10.bookshelf.model.entity.Person;
import tk.itworks10.bookshelf.model.entity.Role;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class PersonTest {

    /**
     * Test the default constructor of classs Person.
     */
    @Test
    public void testDefaultConstructor() {
        Person instance = new Person();
        assertNull(instance.getId());
        assertEquals("", instance.getFirstName());
        assertEquals("", instance.getLastName());
        assertNull(instance.getRole());
    }

    /**
     * Test method isRelatedTo if this object id matches argument id. Method unused as of right now.
     */
    @Test
    public void testIsRelatedToValid() {
        Role o = new Role(new Long(0), "");
        Person instance = new Person(new Long(0), "", "", new Role(new Long(0), "role"));
        assertTrue(instance.isRelatedTo(o));
    }

    /**
     * Test method isRelatedTo if this object id doesn't match argument id. Method unused as of right now.
     */
    @Test
    public void testIsRelatedToInvalid() {
        Role o = new Role(new Long(0), "");
        Person instance = new Person(new Long(0), "", "", new Role(new Long(1), "role"));
        assertFalse(instance.isRelatedTo(o));
    }

    /**
     * Test method CompareTo from class Entity if this object id equals argument id.
     */
    @Test
    public void testCompareToEqual() {
        System.out.println("compareTo");
        Person o = new Person(new Long(0), "firstname1", "lastname1", new Role(new Long(1), "scenarist"));
        Person instance = new Person(new Long(0), "firstname2", "lastname2", new Role(new Long(1), "scenarist"));
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically less than argument id.
     */
    @Test
    public void testCompareToInf() {
        System.out.println("compareTo");
        Person o = new Person(new Long(0), null, null, null);
        Person instance = new Person(new Long(1), null, null, null);
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically more than argument id.
     */
    @Test
    public void testCompareToSup() {
        System.out.println("compareTo");
        Person o = new Person(new Long(1), null, null, null);
        Person instance = new Person(new Long(0), null, null, null);
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method equals from class Entity if this object id points to the same object.
     */
    @Test
    public void testEqualsValid() {
        Person instance = new Person(new Long(0), "", "", new Role());
        Person o = instance;
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't point to the same object.
     */
    @Test
    public void testEqualsInvalid() {
        Person instance = new Person(new Long(0), "", "", new Role());
        Person o = new Person(new Long(1), "", "", new Role());
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id.
     */
    @Test
    public void testEqualsIdEntityValid() {
        Person o = new Person(new Long(0), "", "", new Role());
        Person instance = new Person(new Long(0), "", "", new Role());
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id.
     */
    @Test
    public void testEqualsIdEntityInvalid() {
        Person o = new Person(new Long(0), "", "", new Role());
        Person instance = new Person(new Long(1), "", "", new Role());
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleValid() {
        Person o = new Person(new Long(0), "title", "", new Role());
        Person instance = new Person(new Long(0), "", "", new Role());
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleInvalid() {
        Person o = new Person(new Long(0), "title", "", new Role());
        Person instance = new Person(new Long(1), "", "", new Role());
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object type doesn't match argument type.
     */
    @Test
    public void testEqualsDifferentEntity() {
        Role o = new Role(new Long(0), "title");
        Person instance = new Person(new Long(1), "", "", new Role());
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument Long.
     */
    @Test
    public void testEqualsIdLongValid() {
        Long o = new Long(0);
        Person instance = new Person(new Long(0), "", "", new Role());
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument Long.
     */
    @Test
    public void testEqualsIdLongInvalid() {
        Long o = new Long(0);
        Person instance = new Person(new Long(1), "", "", new Role());
        assertFalse(instance.equals(o));
    }

    /**
     * Test the map from method equals.
     */
    @Test
    public void testMapEmpty() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), "firstName1", "lastName1", role);
        Map<String, Object> params = new HashMap();
        assertFalse(person.equals(params));
    }

    @Test
    public void testMapWrongKey() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), "firstName1", "lastName1", role);
        Map<String, Object> params = new HashMap();
        params.put("test1", "asez");
        params.put("test2", null);
        params.put("test3", "");
        assertFalse(person.equals(params));
    }

    @Test
    public void testMapWrongPlusGoodKey() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), "firstName1", "lastName1", role);
        Map<String, Object> params = new HashMap();
        params.put("firstName", "firstName1");
        params.put("test2", null);
        params.put("test3", "");
        assertFalse(person.equals(params));
    }

    @Test
    public void testMapEmptyThisNull() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), null, null, null);
        Map<String, Object> params = new HashMap();
        assertFalse(person.equals(params));

    }

    @Test
    public void testMapNotEgalThis() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), "firstName1", null, null);
        Map<String, Object> params = new HashMap();
        params.put("firtName", "test1");
        assertFalse(person.equals(params));
    }

    @Test
    public void testMapThisNull() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), null, null, null);
        Map<String, Object> params = new HashMap();
        params.put("name", "test1");
        assertFalse(person.equals(params));
    }

    @Test
    public void testMapEqualThis() {
        Role role = new Role(new Long(1), "");
        Person person = new Person(new Long(1), "firstName1", "lastName1", role);
        Map<String, Object> params = new HashMap();
        params.put("firstName", "firstName1");
        params.put("lastName", "lastName1");
        params.put("role", role);
        assertTrue(person.equals(params));
    }
}
