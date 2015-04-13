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
package tk.itworks10.bookshelf.model.dao;

import tk.itworks10.bookshelf.model.entity.Book;
import tk.itworks10.bookshelf.model.entity.Genre;
import tk.itworks10.bookshelf.model.entity.Person;
import tk.itworks10.bookshelf.model.entity.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tk.itworks10.bookshelf.Bookshelf;

/**
 *
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class FileDAOTest {

    FileDAO<Long, Book> bookDAO;
    
    public FileDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ArrayList<Book> books = new ArrayList();
        books.add(new Book(new Long(1), "title1", new Date(), new Long(3), new Long(50)));
        books.add(new Book(new Long(2), "title2", new Date(), new Long(8070), new Long(10)));
        books.add(new Book(new Long(3), "title3", new Date(), new Long(9), new Long(200)));
        books.add(new Book(new Long(4), "title4", new Date(), new Long(70), new Long(4000)));
        books.add(new Book(new Long(5), "title5", new Date(), new Long(45), new Long(1)));
        books.add(new Book(new Long(10), "title6", new Date(), new Long(70), new Long(50)));
        books.add(new Book(new Long(100), null, null, null, null));

        Role r1 = new Role(new Long(1), "role1");
        Role r2 = new Role(new Long(8), "role2");
        Role r3 = new Role(new Long(47), "role3");
        Role r4 = new Role(new Long(6), "role4");

        Person p1 = new Person(new Long(1), "firstname1", "lastname1", r1);
        Person p2 = new Person(new Long(2), "firstname2", "lastname2", r2);
        Person p3 = new Person(new Long(3), "firstname3", "lastname3", r3);
        //Person p4 = new Person(new Long(10), "firstname10", "lastname10", null);

        Genre g1 = new Genre(new Long(1), "genre1");
        
        books.get(1).addRelatedPerson(p1);
        books.get(1).addRelatedPerson(p2);
        //books.get(1).addRelatedPerson(p4);
        books.get(2).addRelatedPerson(p1);
        //books.get(2).addRelatedPerson(null);
        books.get(2).addRelatedPerson(p3);

        books.get(0).addRelatedGenre(g1);

        bookDAO = new BookFileDAO(books);
    }

    @After
    public void tearDown() {
    }


    /**
     * Test the default constructor of classs FileDAO.
     */
    @ Test
    public void testDefaultConstructor() {
        ArrayList<Book> entities = new ArrayList();
        assertTrue(entities.isEmpty());
    }

    /**
     * Test method insert of class FileDAO, to see if book is added to bookDAO.
     */
    @Test
    public void testInsertValid() throws DAODuplicateEntityException {
        Book book11 = new Book(new Long(11), null, null, null, null);
        bookDAO.insert(book11);
        assertTrue(bookDAO.getEntities().contains(book11));
    }

    /**
     * Test method insert of class FileDAO, to see if wrong book is added to bookDAO.
     */
    @Test
    public void testInsertInvalid() throws DAODuplicateEntityException{
        Book book12 = new Book(new Long(12), null, null, null, null);
        Book book11 = new Book(new Long(11), null, null, null, null);
        bookDAO.insert(book12);
        assertFalse(bookDAO.getEntities().contains(book11));
    }

    /**
     * Test method insert of class FileDAO, to see if method insert give an id Long to b and b1.
     * @throws tk.itworks10.bookshelf.model.dao.DAODuplicateEntityException
     */
    @Test
    public void testInsertNewObjectNoId()throws DAODuplicateEntityException {
        BookFileDAO dao = new BookFileDAO();
        Book b = new Book(), b1 = new Book();
        assertNull(b.getId());
        assertNull(b1.getId());
        dao.insert(b);
        dao.insert(b1);
        assertEquals(new Long(0), b.getId());
        assertEquals(new Long(1), b1.getId());
    }

    /**
     * Test method insert of class FileDAO, to see if method insert give right id Long to b2.
     * @throws tk.itworks10.bookshelf.model.dao.DAODuplicateEntityException
     */
    @Test
    public void testInsertNewObjecJumpId()throws DAODuplicateEntityException {
        BookFileDAO dao = new BookFileDAO();
        Book b1 = new Book(new Long(30), null, null, null, null), b2 = new Book();
        dao.insert(b1);
        dao.insert(b2);
        assertEquals(new Long(30), b1.getId());
        assertEquals(new Long(31), b2.getId());
    }

    /**
     * Test method insert of class FileDAO, to see if method insert give right id Long to b3, which
     * should be highest next available, new Long(31), instead of new Long(21).
     * @throws DAODuplicateEntityException
     */
    @Test
    public void testInsertNewObjectIdOrder() throws DAODuplicateEntityException{
        BookFileDAO dao = new BookFileDAO();
        Book b1 = new Book(new Long(30), null, null, null, null);
        Book b2 = new Book(new Long(20), null, null, null, null);
        Book b3 = new Book();
        dao.insert(b1);
        dao.insert(b2);
        dao.insert(b3);
        assertEquals(new Long(30), b1.getId());
        assertEquals(new Long(20), b2.getId());
        assertEquals(new Long(31), b3.getId());
    }

    /**
     * Test method select of class FileDAO, to see if book is selected.
     * @throws tk.itworks10.bookshelf.model.dao.DAOEntityNotFoundException
     */

    @Test
    public void testSelect() throws DAOEntityNotFoundException {
        System.out.println("select");
        Book book = bookDAO.select(new Long(1));
        assertEquals(new Long(1), book.getId());
        assertEquals("title1", book.getTitle());
    }

    /**
     * Test method select of class FileDAO, to see if selected book id is detected.
     * @throws tk.itworks10.bookshelf.model.dao.DAOEntityNotFoundException
     */
    @Test
    public void testSelectIdObject()throws DAOEntityNotFoundException {
        Book book = bookDAO.select(new Long(1));
        assertTrue(book.getId() != null);
    }

    /**
     * Test method select of class FileDAO, to see if newly created and added selected book id
     * is detected.
     * @throws tk.itworks10.bookshelf.model.dao.DAOEntityNotFoundException
     */
    @Test
    public void testSelectIdNewObject()throws DAOEntityNotFoundException, DAODuplicateEntityException {
        Book book20 = new Book(new Long(20), null, null, null, null);
        bookDAO.insert(book20);
        Book book = bookDAO.select(new Long(20));
        assertTrue(book.getId() != null);
    }

    /**
     * Test method select of class FileDAO, to see if "entity not found" exception is throwed when
     * inexisting book is selected.
     */
    @Test
    public void testSelectException() {
        System.out.println("select");
        boolean b = false;
        try {
            Book book = bookDAO.select(new Long(1000));
        } catch (DAOEntityNotFoundException e) {
            b = true;
        }
        assertTrue(b);
    }

    /**
     * Test method select by map of class FileDAO, to see if collection is empty when params is
     * invalid.
     */
    @Test
    public void testSelect_Map() {
        System.out.println("select");
        Map<String, Object> params = new HashMap();
        params.put("title", "title58");
        Collection<Book> col = bookDAO.select(params);
        assertTrue(col.isEmpty());
    }

    /**
     * Test method select by map of class FileDAO, to see if collection is empty when params is
     * empty.
     */
    @Test
    public void testSelect_EmptyMap() {
        System.out.println("select");
        Map<String, Object> params = new HashMap();
        Collection<Book> col = bookDAO.select(params);
        assertTrue(col.isEmpty());
    }

     /**
     * Test method select by map of class FileDAO, to see if collection is empty when multiple
     * params are defined and partially invalid.
     */
    @Test
    public void testSelect_MapMultipleParamsInvalid() {
        System.out.println("select");
        Map<String, Object> params = new HashMap();
        params.put("title", "title2");
        params.put("numero", new Long(3));
        Collection<Book> col = bookDAO.select(params);
        assertTrue(col.isEmpty());
    }

    /**
     * Test method select by map of class FileDAO, to see if collection isn't empty and if correct
     * book is added, when multiple params are defined and all valid.
     */
    @Test
    public void testSelect_MapMultipleParamsValid() {
        System.out.println("select");
        Map<String, Object> params = new HashMap();
        params.put("title", "title1");
        params.put("numero", new Long(3));
        Collection<Book> col = bookDAO.select(params);
        assertFalse(col.isEmpty());
        for (Book book : col) {
            assertEquals("title1", book.getTitle());
            assertEquals(new Long(3), book.getNumero());
        }
    }

    /**
     * Test method selectAll of class FileDAO, to see if all books are selected.
     */
    @Test
    public void testSelect_All() {
        Collection<Book> col = bookDAO.selectAll();
        for (Book b : col) {
            assertTrue(bookDAO.entities.contains(b));
        }
        assertTrue(col.size() == bookDAO.entities.size());
    }

    /**
     * Test method selectAll of class FileDAO on a new FileDAO, to see if all books are selected.
     */
    @Test
    public void testSelect_AllNewDao() {
        FileDAO<Long, Book> dao = new BookFileDAO();
        Collection<Book> col = dao.selectAll();
        for (Book b : col) {
            assertTrue(bookDAO.entities.contains(b));
        }
        assertEquals(0, col.size());
    }

    /**
     * Test method update of class FileDAO, to see if book b1 is found when using method equals
     * and if his title is updated.
     */
    @Test
    public void testUpdate() throws DAOEntityNotFoundException, DAODuplicateEntityException {
        Book b1 = new Book(new Long(43), "title1", null, null, null);
        bookDAO.insert(b1);
        b1.setTitle("title2");
        bookDAO.update(b1);
        boolean found = false;
        for (Book b : bookDAO.entities) {
            if (b.equals(b1.getId())) {
                found = true;
                assertEquals("title2", b.getTitle());
            }
        }
        assertTrue(found);
    }

    /**
     * Test method update of class FileDAO, to see if "entity not found" exception is throwed when
     * not inserted book is updated and if he is found by using method equals.
     */
    @Test
    public void testUpdateException() throws DAOEntityNotFoundException {
        Book b1 = new Book(new Long(43), "title1", null, null, null);
        assertFalse(bookDAO.entities.contains(b1));
        boolean found = false;
        boolean b = false;
        try {
            bookDAO.update(b1);
        } catch (DAOEntityNotFoundException e) {
            b = true;
        }
        for (Book book : bookDAO.entities) {
            if (book.equals(b1.getId())) {
                found = true;
            }
        }
        assertTrue(b);
        assertFalse(found);
    }

    /**
     * Test method delete of class FileDAO, to see if added book is deleted by method delete.
     */
    @Test
    public void testDeleteObjectExisting() {
        Book b1 = new Book(new Long(1), null, null, null, null);
        assertTrue(bookDAO.entities.contains(b1));
        bookDAO.delete(new Long(1));
        assertFalse(bookDAO.getEntities().contains(b1));
    }

    /**
     * Test method delete of class FileDAO, to see if exception is throwed when not added book is
     * deleted.
     */
    @Test
    public void testDeleteObjectInexisting() {
        Book b1 = new Book(new Long(57), null, null, null, null);
        assertFalse(bookDAO.entities.contains(b1));
        boolean b = true;
        try {
            bookDAO.delete(new Long(57));
        } catch (Exception e) {
            b = false;
        }
        assertTrue(b);
    }
}
