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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class BookTest {

    /**
     * Test the default constructor of classs Book.
     */
    @Test
    public void testDefaultConstructor() {
        Book instance = new Book();
        assertNull(instance.getId());
        assertEquals("", instance.getTitle());
        assertNull(instance.getReleaseDate());
        assertNull(instance.getNumero());
        assertNull(instance.getPageCount());
        assertTrue(instance.persons.isEmpty());
        assertTrue(instance.keywords.isEmpty());
        assertTrue(instance.genres.isEmpty());
        assertTrue(instance.publishers.isEmpty());
    }

    /**
     * Test method addRelatedPerson on a Person with default constructor.
     */
    @Test
    public void testAddRelatedPerson() {
        System.out.println("addRelatedPerson");
        Person person = new Person();
        Book instance = new Book();
        instance.addRelatedPerson(person);
        assertTrue(instance.persons.contains(person));
    }

    /**
     * Test method addRelatedPerson on a Person null. No exception throwed because of the null person.
     */
    @Test
    public void testAddRelatedPersonNullPointerException() {
        System.out.println("addRelatedPerson");
        Person person = null;
        Book instance = new Book();
        boolean b = true;
        try {
        instance.addRelatedPerson(person);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method removeRelatedPerson on a Person with default constructor.
     */
    @Test
    public void testRemoveRelatedPerson() {
        System.out.println("removeRelatedPerson");
        Person person = new Person();
        Book instance = new Book();
        instance.addRelatedPerson(person);
        instance.removeRelatedPerson(person);
        assertFalse(instance.persons.contains(person));
    }

    /**
     * Test method removeRelatedPerson on a Person null. No exception throwed because of the null person.
     */
    @Test
    public void testRemoveRelatedPersonNullPointerException() {
        System.out.println("removeRelatedPerson");
        Person person = null;
        Book instance = new Book();
        boolean b = true;
        try {
            instance.removeRelatedPerson(person);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method addRelatedKeyword on a Keyword with default constructor.
     */
    @Test
    public void testAddRelatedKeyword() {
        System.out.println("addRelatedKeyword");
        Keyword keyword = new Keyword();
        Book instance = new Book();
        instance.addRelatedKeyword(keyword);
        assertTrue(instance.keywords.contains(keyword));
    }

    /**
     * Test method addRelatedKeyword on a Keyword null. No exception throwed because of the null keyword.
     */
    @Test
    public void testAddRelatedKeywordNullPointerException() {
        System.out.println("addRelatedKeyword");
        Keyword keyword = null;
        Book instance = new Book();
        boolean b = true;
        try {
        instance.addRelatedKeyword(keyword);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method removeRelatedKeyword on a Keyword with default constructor.
     */
    @Test
    public void testRemoveRelatedKeyword() {
        System.out.println("removeRelatedKeyword");
        Keyword keyword = new Keyword();
        Book instance = new Book();
        instance.addRelatedKeyword(keyword);
        instance.removeRelatedKeyword(keyword);
        assertFalse(instance.keywords.contains(keyword));
    }

    /**
     * Test method removeRelatedKeyword on a Keyword null. No exception throwed because of the null keyword.
     */
    @Test
    public void testRemoveRelatedKeywordNullPointerExecption() {
        System.out.println("removeRelatedKeyword");
        Keyword keyword = null;
        Book instance = new Book();
        boolean b = true;
        try {
        instance.removeRelatedKeyword(keyword);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method addRelatedGenre on a Genre with default constructor.
     */
    @Test
    public void testAddRelatedGenre() {
        System.out.println("addRelatedGenre");
        Genre genre = new Genre();
        Book instance = new Book();
        instance.addRelatedGenre(genre);
        assertTrue(instance.genres.contains(genre));
    }

    /**
     * Test method addRelatedGenre on a Genre null. No exception throwed because of the null Genre.
     */
    @Test
    public void testAddRelatedGenreNullPointerException() {
        System.out.println("addRelatedGenre");
        Genre genre = null;
        Book instance = new Book();
        boolean b = true;
        try {
        instance.addRelatedGenre(genre);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);

    }

    /**
     * Test method removeRelatedGenre on a Genre with default constructor.
     */
    @Test
    public void testRemoveRelatedGenre() {
        System.out.println("removeRelatedGenre");
        Genre genre = new Genre();
        Book instance = new Book();
        instance.addRelatedGenre(genre);
        instance.removeRelatedGenre(genre);
        assertFalse(instance.genres.contains(genre));
    }

    /**
     * Test method removeRelatedGenre on a Genre null. No exception throwed because of the null Genre.
     */
    @Test
    public void testRemoveRelatedGenreNullPointerException() {
        System.out.println("removeRelatedGenre");
        Genre genre = null;
        Book instance = new Book();
        instance.addRelatedGenre(genre);
        boolean b = true;
        try {
        instance.removeRelatedGenre(genre);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method addRelatedPublisher on a Publisher with default constructor.
     */
    @Test
    public void testAddRelatedPublisher() {
        System.out.println("addRelatedPublisher");
        Publisher publisher = new Publisher();
        Book instance = new Book();
        instance.addRelatedPublisher(publisher);
        assertTrue(instance.publishers.contains(publisher));
    }

    /**
     * Test method addRelatedPublisher on a Publisher null. No exception throwed because of the null Publisher.
     */
    @Test
    public void testAddRelatedPublisherNullPointerException() {
        System.out.println("addRelatedPublisher");
        Publisher publisher = null;
        Book instance = new Book();
        boolean b = true;
        try {
        instance.addRelatedPublisher(publisher);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method removeRelatedPublisher on a Publisher with default constructor.
     */
    @Test
    public void testRemoveRelatedPublisher() {
        System.out.println("removeRelatedPublisher");
        Publisher publisher = new Publisher();
        Book instance = new Book();
        instance.addRelatedPublisher(publisher);
        instance.removeRelatedPublisher(publisher);
        assertFalse(instance.publishers.contains(publisher));
    }

    /**
     * Test method removeRelatedPublisher on a Publisher null. No exception throwed because of the null Publisher.
     */
    @Test
    public void testRemoveRelatedPublisherNullPointerException() {
        System.out.println("removeRelatedPublisher");
        Publisher publisher = null;
        Book instance = new Book();
        instance.addRelatedPublisher(publisher);
        boolean b = true;
        try {
        instance.removeRelatedPublisher(publisher);
        } catch (NullPointerException e) {
            b = false;
        }
        assertTrue(b);
    }

    /**
     * Test method CompareTo from class Entity if this object id equals argument id.
     */
    @Test
    public void testCompareToEqual() {
        Book o = new Book(new Long(0), "", new Date(), new Long(0), new Long(0));
        Book instance = new Book(new Long(0), "", new Date(), new Long(0), new Long(0));
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically less than argument id.
     */
    @Test
    public void testCompareToInf() {
        Book o = new Book(new Long(1), "", new Date(), new Long(0), new Long(0));
        Book instance = new Book(new Long(0), "", new Date(), new Long(0), new Long(0));
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method CompareTo from class Entity if this object id is numerically more than argument id.
     */
    @Test
    public void testCompareToSup() {
        Book o = new Book(new Long(0), "", new Date(), new Long(0), new Long(0));
        Book instance = new Book(new Long(1), "", new Date(), new Long(0), new Long(0));
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test method equals from class Entity if this object id points to the same object.
     */
    @Test
    public void testEqualsValid() {
        Book instance = new Book(new Long(1), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book o = instance;
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't point to the same object.
     */
    @Test
    public void testEqualsInvalid() {
        Book instance = new Book(new Long(0), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book o = new Book(new Long(1), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id.
     */
    @Test
    public void testEqualsIdEntityValid() {
        Book o = new Book(new Long(0), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book instance = new Book(new Long(0), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id.
     */
    @Test
    public void testEqualsIdEntityInvalid() {
        Book o = new Book(new Long(0), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book instance = new Book(new Long(1), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleValid() {
        Book o = new Book(new Long(1), "title", null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book instance = new Book(new Long(1), "title", null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument id, with different
     * attribute.
     */
    @Test
    public void testEqualsIdEntityDifferentTitleInvalid() {
        Book o = new Book(new Long(0), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        Book instance = new Book(new Long(1), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object type doesn't match argument type.
     */
    @Test
    public void testEqualsDifferentEntity() {
        Role o = new Role(new Long(0), "title");
        Book instance = new Book(new Long(1), null, null, Long.MIN_VALUE, Long.MIN_VALUE);
        assertFalse(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id matches to argument Long.
     */
    @Test
    public void testEqualsIdLongValid() {
        Long o = new Long(0);
        Book instance = new Book(new Long(0), null, null, null, null);
        assertTrue(instance.equals(o));
    }

    /**
     * Test method equals from class Entity if this object id doesn't match to argument Long.
     */
    @Test
    public void testEqualsIdLongInvalid() {
        Long o = new Long(0);
        Book instance = new Book(new Long(1), null, null, null, null);
        assertFalse(instance.equals(o));
    }

    /**
     * Test overloaded method equals if Map is empty.
     */
    @Test
    public void testEqualsMapEmpty() {
        Map<String, Object> params = new HashMap();
        Book o = new Book(new Long(1), "title", new Date(), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is empty and Book object isn't defined(default constructor.
     */
    @Test
    public void testEqualsMapEmptyBookEmptyConstructor() {
        Map<String, Object> params = new HashMap();
        Book o = new Book();
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "title" and value matches to Book object.
     */
    @Test
    public void testEqualsMapTitleValid() {
        Map<String, Object> params = new HashMap();
        params.put("title", "titre1");
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "title" and value does not match to Book object.
     */
    @Test
    public void testEqualsMapTitleInvalid() {
        Map<String, Object> params = new HashMap();
        params.put("title", "titre2");
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "title" and Book isn't defined(default constructor).
     */
    @Test
    public void testEqualsMapTitleEmptyBookConstructor() {
        Map<String, Object> params = new HashMap();
        params.put("title", "titre2");
        Book o = new Book();
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "releaseDate" and value matches to Book object.
     */
    @Test
    public void testEqualsMapReleaseDateValid() {
        Map<String, Object> params = new HashMap();
        params.put("releaseDate", new Date(1));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "releaseDate" and value does not match to Book object.
     */
    @Test
    public void testEqualsMapReleaseDateInvalid() {
        Map<String, Object> params = new HashMap();
        params.put("releaseDate", new Date(2));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "numero" and value matches to Book object.
     */
    @Test
    public void testEqualsMapNumeroValid() {
        Map<String, Object> params = new HashMap();
        params.put("numero", new Long(1));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "numero" and value does not match to Book object.
     */
    @Test
    public void testEqualsMapNumeroInvalid() {
        Map<String, Object> params = new HashMap();
        params.put("numero", new Date(2));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "pageCount" and value matches to Book object.
     */
    @Test
    public void testEqualsMapPageCountValid() {
        Map<String, Object> params = new HashMap();
        params.put("pageCount", new Long(1));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "pageCount" and value does not match to Book object.
     */
    @Test
    public void testEqualsMapPageCountInvalid() {
        Map<String, Object> params = new HashMap();
        params.put("pageCount", new Date(2));
        Book o = new Book(new Long(1), "titre1", new Date(1), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "persons" and method addRelatedPerson
     * has been called on the right person.
     */
    @Test
    public void testEqualsMapPersonValid() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), "p1", "p1", null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("persons", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedPerson(p1);
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "persons" and method addRelatedPerson
     * has been called on a wrong person.
     */
    @Test
    public void testEqualsMapPersonInvalid() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), "p1", "p1", null);
        Person p2 = new Person(new Long(2), "p2", "p2", null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("persons", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedPerson(p2);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "persons" and method addRelatedPerson
     * has not been called.
     */
    @Test
    public void testEqualsMapNoPersonAssociated() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), "p1", "p1", null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("persons", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "keyworlds" and method addRelatedKeyword
     * has been called on the right keyword.
     */
    @Test
    public void testEqualsMapKeywordValid() {
        Map<String, Object> params = new HashMap();
        Keyword k1 = new Keyword(new Long(1), null);
        Collection<Keyword> col = new ArrayList();
        col.add(k1);
        params.put("keywords", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedKeyword(k1);
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "keyworlds" and method addRelatedKeyword
     * has been called on a wrong keyword.
     */
    @Test
    public void testEqualsMapKeywordInvalid() {
        Map<String, Object> params = new HashMap();
        Keyword k1 = new Keyword(new Long(1), null);
        Keyword k2 = new Keyword(new Long(2), null);
        Collection<Keyword> col = new ArrayList();
        col.add(k1);
        params.put("keywords", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedKeyword(k2);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "genres" and method addRelatedGenre
     * has been called on the right genre.
     */
    @Test
    public void testEqualsMapGenreValid() {
        Map<String, Object> params = new HashMap();
        Genre g1 = new Genre(new Long(1), null);
        Collection<Genre> col = new ArrayList();
        col.add(g1);
        params.put("genres", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedGenre(g1);
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "genres" and method addRelatedGenre
     * has been called on a wrong genre.
     */
    @Test
    public void testEqualsMapGenreInvalid() {
        Map<String, Object> params = new HashMap();
        Genre g1 = new Genre(new Long(1), null);
        Genre g2 = new Genre(new Long(2), null);
        Collection<Genre> col = new ArrayList();
        col.add(g1);
        params.put("keywords", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedGenre(g2);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "publishers" and method addRelatedPublisher
     * has been called on the right publisher.
     */
    @Test
    public void testEqualsMapPublisherValid() {
        Map<String, Object> params = new HashMap();
        Publisher p1 = new Publisher(new Long(1), null);
        Collection<Publisher> col = new ArrayList();
        col.add(p1);
        params.put("publishers", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedPublisher(p1);
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to key "publishers" and method addRelatedPublisher
     * has been called on a wrong publisher.
     */
    @Test
    public void testEqualsMapPublishersInvalid() {
        Map<String, Object> params = new HashMap();
        Publisher p1 = new Publisher(new Long(1), null);
        Publisher p2 = new Publisher(new Long(2), null);
        Collection<Publisher> col = new ArrayList();
        col.add(p1);
        params.put("publishers", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedPublisher(p2);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to wrong key "keyworlds", value is set to a
     * collection of Genre containing "g1" and method addRelatedGenre has been called on Genre "g1".
     */
    @Test
    public void testEqualsMapWrongKeyAndValue() {
        Map<String, Object> params = new HashMap();
        Genre g1 = new Genre(new Long(1), null);
        Genre g2 = new Genre(new Long(2), null);
        Collection<Genre> col = new ArrayList();
        col.add(g1);
        params.put("keywords", col);
        Book o = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        o.addRelatedGenre(g1);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria and none matches with Book object.
     */
    @Test
    public void testMultipleCriteriaNoMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("title", "titre2");
        params.put("numero", new Long(2));
        params.put("persons", col);
        Book o = new Book();
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria and some matches with Book object.
     */
    @Test
    public void testMultipleCriteriaSomeMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("title", "titre2");
        params.put("numero", new Long(1));
        params.put("persons", col);
        Book o = new Book(new Long(1), "titre2", null, new Long(2), null);
        o.addRelatedPerson(p1);
        assertFalse(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria and everything matches with Book object.
     */
    @Test
    public void testMultipleCriteriaAllMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p1);
        params.put("title", "titre2");
        params.put("numero", new Long(2));
        params.put("persons", col);
        Book o = new Book(new Long(1), "titre2", null, new Long(2), null);
        o.addRelatedPerson(p1);
        assertTrue(o.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria, multiple Book objects are
     * inspected and nothing matches with Book objects.
     */
    @Test
    public void testMultipleCriteriaNoBooksMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Person p2 = new Person(new Long(2), null, null, null);
        Person p3 = new Person(new Long(3), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p3);
        params.put("title", "titre3");
        params.put("numero", new Long(3));
        params.put("persons", col);
        Book b1 = new Book(new Long(0), "titre0", new Date(), new Long(0), new Long(0));
        Book b2 = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        Book b3 = new Book(new Long(2), "titre2", new Date(), new Long(2), new Long(2));
        b1.addRelatedPerson(p1);
        b2.addRelatedPerson(p2);
        b3.addRelatedPerson(p1);
        b3.addRelatedPerson(p2);
        assertFalse(b1.equals(params));
        assertFalse(b2.equals(params));
        assertFalse(b3.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria, multiple Book objects are
     * inspected and some Book objects matches with all Map criteria, while others don't.
     */
    @Test
    public void testMultipleCriteriaSomeBooksMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Person p2 = new Person(new Long(2), null, null, null);
        Person p3 = new Person(new Long(3), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p2);
        params.put("title", "titre2");
        params.put("numero", new Long(2));
        params.put("persons", col);
        Book b1 = new Book(new Long(0), "titre2", new Date(), new Long(2), new Long(0));
        Book b2 = new Book(new Long(1), "titre1", new Date(), new Long(1), new Long(1));
        Book b3 = new Book(new Long(2), "titre2", new Date(), new Long(2), new Long(2));
        b1.addRelatedPerson(p2);
        b2.addRelatedPerson(p2);
        b3.addRelatedPerson(p1);
        b3.addRelatedPerson(p2);
        assertTrue(b1.equals(params));
        assertFalse(b2.equals(params));
        assertTrue(b3.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria, multiple Book objects are
     * inspected and all Book objects matches with all Map criteria.
     */
    @Test
    public void testMultipleCriteriaAllBooksMatches() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), null, null, null);
        Person p2 = new Person(new Long(2), null, null, null);
        Person p3 = new Person(new Long(3), null, null, null);
        Collection<Person> col = new ArrayList();
        col.add(p2);
        params.put("title", "titre2");
        params.put("numero", new Long(2));
        params.put("persons", col);
        Book b1 = new Book(new Long(0), "titre2", new Date(), new Long(2), new Long(0));
        Book b2 = new Book(new Long(1), "titre2", new Date(), new Long(2), new Long(1));
        Book b3 = new Book(new Long(2), "titre2", new Date(), new Long(2), new Long(2));
        b1.addRelatedPerson(p2);
        b2.addRelatedPerson(p2);
        b3.addRelatedPerson(p1);
        b3.addRelatedPerson(p2);
        assertTrue(b1.equals(params));
        assertTrue(b2.equals(params));
        assertTrue(b3.equals(params));
    }

    /**
     * Test overloaded method equals if Map is set to multiple criteria, multiple Book objects are
     * inspected and each Book object contains only one different criteria.
     */
    @Test
    public void testSharedCriteriaBetweenBooks() {
        Map<String, Object> params = new HashMap();
        Person p1 = new Person(new Long(1), "firstname1", "lastname1", new Role(new Long(1), "role1"));
        Person p2 = new Person(new Long(2), "firstname2", "lastname2", new Role(new Long(2), "role2"));
        Genre g1 = new Genre(new Long(1), "genre1");
        Genre g2 = new Genre(new Long(2), "genre2");
        Book b1 = new Book();
        Book b2 = new Book();
        b1.addRelatedPerson(p1);
        b1.addRelatedGenre(g1);
        b2.addRelatedPerson(p2);
        b2.addRelatedGenre(g2);
        Collection<Person> colPerson = new ArrayList();
        colPerson.add(p1);
        Collection<Genre> colGenre = new ArrayList();
        colGenre.add(g2);
        params.put("persons", colPerson);
        params.put("genres", colGenre);
        assertFalse(b1.equals(params));
        assertFalse(b2.equals(params));
    }
}
