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
package tk.itworks10.bookshelf.controller;

import tk.itworks10.bookshelf.model.entity.Book;
import tk.itworks10.bookshelf.view.CliUI;
import tk.itworks10.bookshelf.view.UI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import tk.itworks10.bookshelf.model.dao.BookFileDAO;
import tk.itworks10.bookshelf.model.dao.DAO;
import tk.itworks10.bookshelf.model.dao.DAODuplicateEntityException;
import tk.itworks10.bookshelf.model.dao.DAOEntityNotFoundException;
import tk.itworks10.bookshelf.model.dao.GenreFileDAO;
import tk.itworks10.bookshelf.model.dao.KeywordFileDAO;
import tk.itworks10.bookshelf.model.dao.PersonFileDAO;
import tk.itworks10.bookshelf.model.dao.PublisherFileDAO;
import tk.itworks10.bookshelf.model.dao.RoleFileDAO;
import tk.itworks10.bookshelf.model.entity.Genre;
import tk.itworks10.bookshelf.model.entity.Keyword;
import tk.itworks10.bookshelf.model.entity.Person;
import tk.itworks10.bookshelf.model.entity.Publisher;
import tk.itworks10.bookshelf.model.entity.Role;
import tk.itworks10.bookshelf.view.CLiInvalidChoiceException;

/**
 * Class Application's purpose is to answer interface UI and child's requests.
 *
 * It is in charge of answering CliUI and GUI requests and to call class FileDAO and its childs, and
 * class Book.
 * @author Itworks10
 */
public class Application {

    /**
     *
     */
    public static final boolean APP_RUNNING = true;
   
    /**
     * 
     */
    public static final boolean APP_STOPPED = false;

    /**
     *
     */
    protected UI ui;
    
    /**
     * Constant run. Start as false.
     */
    protected boolean run = APP_STOPPED;

    /**
     * Entity's childs DAO.
     */
    protected DAO<Long, Book> bookDAO;
    protected DAO<Long, Person>personDAO;
    protected DAO<Long, Role>roleDAO;
    protected DAO<Long, Publisher>publisherDAO;
    protected DAO<Long, Genre>genreDAO;
    protected DAO<Long, Keyword>keywordDAO;

    /**
     * Constant for error messages. 
     */
    public static final String APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND = "Update failed: element not found.";
    public static final String APP_ERR_1001_UPDATE_DAO_ENTITY_NOT_FOUND = "Update failed: wrong role ID.";
    public static final String APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY = "Insertion failed: element already exists.";
    public static final String APP_ERR_2001_CREATE_DAO_ENTITY_NOT_FOUND = "Insertion failed: wrong role ID.";
    public static final String APP_ERR_3000_LINK_DAO_DUPLICATE_ENTITY = "Link failed: element already exists.";
    public static final String APP_ERR_3001_LINK_DAO_ENTITY_NOT_FOUND = "Link failed: element not found..";

    /**
     *  Constructor.
     */
    public Application() {
        setUi(new CliUI(this));
        init();
        bookDAO = new BookFileDAO();
        personDAO = new PersonFileDAO();
        roleDAO = new RoleFileDAO();
        publisherDAO = new PublisherFileDAO();
        genreDAO = new GenreFileDAO();
        keywordDAO = new KeywordFileDAO();
    }

    /**
     * Constructor.
     * @param ui
     */
    public Application(UI ui) {
        if (ui != null) {
            setUi(ui);
            ui.setApplication(this);
        }
        else {
            setUi(new CliUI(this));
        }
        init();
    } 

    /**
     * Initializes the application.
     */
    public void init() {
        getUi().init();
    }

    /**
     * Start the loop in main.
     */
    public void run() {
        setRun(APP_RUNNING);

        while (isRunning()) {
            try {
                ui.start();
            } catch (CLiInvalidChoiceException ex) {
            }
        }
    }

    /**
     * Stop the loop in CliUI.
     */
    public void stop() {
        setRun(APP_STOPPED);
    }

    /**
     * Return the book corresponding the id inside bookDAO.
     *
     * @param id The id corresponding to the book to return.
     * @return A book matching the id.
     */
    public Book getBook(Long id) {
        try {
            return bookDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {}
        return null;
    }

    /**
     * Return the book corresponding the id inside bookDAO.
     *
     * @param id The id corresponding to the book to return.
     * @param error The error code.
     * @param message The error message.
     * @return A book matching the id.
     */
    public Book getBook(Long id, long error, String message) {
        try {
            return bookDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(error, message);
        }
        return null;
    }

    /**
     * Return every books in bookDAO.
     *
     * @return Every books in bookDAO.
     */
    public Collection<Book> getAllBooks() {
        return bookDAO.selectAll();
    }

    /**
     * Return a selection of book depending of criteria in bookDAO.
     *
     * @param params The searched books parameters.
     * @return A selection of books depending of the map.
     */
    public Collection<Book> getBooksByCriteria(Map<String, Object> params) {
       return bookDAO.select(params);
    }

    /**
     * Create a book and insert it in collection bookDAO.
     *
     * @param id The id of the added book.
     * @param title The title of the added book.
     * @param releaseDate The release date of the added book.
     * @param numero The numero of the added book.
     * @param pageCount The page count of the added book.
     */
    public void createBook(Long id, String title, Date releaseDate, Long numero, Long pageCount) {
        Book book = new Book(id, title, releaseDate, numero, pageCount);
        try {
            bookDAO.insert(book);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createBook().
     *
     * Create a book and insert it in collection bookDAO.
     * @param title The title of the added book.
     * @param releaseDate The release date of the added book.
     * @param numero The numero of the added book.
     * @param pageCount The page count of the added book.
     */
    public void createBook(String title, Date releaseDate, Long numero, Long pageCount) {
        createBook(null, title, releaseDate, numero, pageCount);
    }

    /**
     * Modify a book in bookDAO and then update it with method update().
     *
     * @param id The id of the book to update.
     * @param params The modified parameters.
     */
    public void updateBook(Long id, Map<String, Object> params) {
        try {
            Book book = bookDAO.select(id);
            if (params.containsKey("title") == true) {
                book.setTitle((String)params.get("title"));
            }
            if (params.containsKey("releaseDate") == true) {
                book.setReleaseDate((Date)params.get("releaseDate"));
            }
            if (params.containsKey("numero") == true) {
                book.setNumero((Long)params.get("numero"));
            }
            if (params.containsKey("pageCount") == true) {
                book.setPageCount((Long)params.get("pageCount"));
            }
            bookDAO.update(book);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a book in bookDAO.
     *
     * @param id The id of the book to delete.
     */
    public void deleteBook(Long id) {
        bookDAO.delete(id);
    }

    /**
     * Link a collection of person to a book.
     *
     * @param book The book which is linked to the collection of person.
     * @param persons The collection of person linked to the book.
     */
    public void addRelatedPersons(Book book, Collection<Person> persons) {
        for (Person p : persons) {
            book.addRelatedPerson(p);
        }
    }

    /**
     * Link a collection of publisher to a book.
     *
     * @param book The book which is linked to the collection of publisher.
     * @param publishers The collection of publisher linked to the book.
     */
    public void addRelatedPublishers(Book book, Collection<Publisher> publishers) {
        for (Publisher p : publishers) {
            book.addRelatedPublisher(p);
        }
    }

    /**
     * Link a collection of genre to a book.
     *
     * @param book The book which is linked to the collection of genre.
     * @param genres The collection of genre linked to the book.
     */
    public void addRelatedGenres(Book book, Collection<Genre> genres) {
        for (Genre g : genres) {
            book.addRelatedGenre(g);
        }
    }

    /**
     * Link a collection of keyword to a book.
     *
     * @param book The book which is linked to the collection of keyword.
     * @param keywords The collection of keyword linked to the book.
     */
    public void addRelatedKeywords(Book book, Collection<Keyword> keywords) {
        for (Keyword k : keywords) {
            book.addRelatedKeyword(k);
        }
    }

    /**
     * Return the person corresponding the id inside personDAO.
     *
     * @param id The id corresponding to the person to return.
     * @return A person matching the id.
     */
    public Person getPerson(Long id) {
        try {
            return personDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {}
        return null;
    }

    /**
     * Return the person corresponding the id inside personDAO.
     *
     * @param id The id corresponding to the person to return.
     * @param error The error code.
     * @param message The error message.
     * @return A person matching the id.
     */
    public Person getPerson(Long id, long error, String message) {
        try {
            return personDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(error, message);
        }
        return null;
    }

    /**
     * Return a collection of person.
     *
     * @param personsId A collection of Long corresponding person's ids.
     * @return A collection of person.
     */
    public Collection getPersons(Collection<Long> personsId) {
        Collection <Person> persons = new ArrayList();
        Person person;
        for (Long id: personsId) {
            person = getPerson(id);
            if (person != null) {
                persons.add(person);
            }
        }
        return persons;
    }

    /**
     * Return a collection of person.
     *
     * @param personsId A collection of Long corresponding person's ids.
     * @param error The error code.
     * @param message The error message.
     * @return A collection of person.
     */
    public Collection getPersons(Collection<Long> personsId, long error, String message) {
        Collection <Person> persons = new ArrayList();
        Person person;
        for (Long id: personsId) {
            person = getPerson(id);
            if (person != null) {
                persons.add(person);
            }
            else {
                ui.printError(error, message);
            }
        }
        return persons;
    }

    /**
     * Return every person in personDAO.
     *
     * @return Every person in personDAO.
     */
    public Collection<Person> getAllPersons() {
        return personDAO.selectAll();
    }

    /**
     * Return a selection of person depending of criteria in personDAO.
     *
     * @param params The searched persons parameters.
     * @return A selection of persons depending of the map.
     */
    public Collection<Person> getPersonsByCriteria(Map<String, Object> params) {
        return personDAO.select(params);
    }

    /**
     * Create a person and insert it in collection personDAO.
     *
     * @param id The id of the added person.
     * @param firstName The first name of the added person.
     * @param lastName The last name of the added person.
     * @param role The role of the added person.
     */
    public void createPerson(Long id, String firstName, String lastName, Role role) {
        Person person = new Person(id, firstName, lastName, role);
        try {
            personDAO.insert(person);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createPerson().
     *
     * @param firstName The first name of the added person.
     * @param lastName  The last name of the added person.
     */
    public void createPerson(String firstName, String lastName) {
        createPerson(null, firstName, lastName, null);
    }

    /**
     * Overrided method createPerson().
     *
     * @param firstName The first name of the added person.
     * @param lastName The last name of the added person.
     * @param role  The role of the added person.
     */
    public void createPerson(String firstName, String lastName, Role role) {
        createPerson(null, firstName, lastName, role);
    }

    /**
     * Overrided method createPerson().
     *
     * @param firstName The first name of the added person.
     * @param lastName The last name of the added person.
     * @param idRole The role's id of the added person.
     */
    public void createPerson(String firstName, String lastName, Long idRole) {
        try {
            createPerson(null, firstName, lastName, roleDAO.select(idRole));
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(2001, APP_ERR_2001_CREATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Modify a person in personDAO and then update it with method update().
     *
     * @param id The id of the person to update.
     * @param params The modified parameters.
     */
    public void updatePerson(Long id, Map<String, Object> params) {
        try {
            Person person = personDAO.select(id);
            if (params.containsKey("firstName") == true) {
                person.setFirstName((String)params.get("firstName"));
            }
            if (params.containsKey("lastName") == true) {
            person.setLastName((String)params.get("lastName"));
            }
            personDAO.update(person);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Modify a person in personDAO and then update it with method update().
     *
     * @param id The id of the person to update.
     * @param params The modified parameters.
     * @param idRole The id of the role to set or modify.
     */
    public void updatePerson(Long id, Map<String, Object> params, Long idRole) {
        try {
            Person person = personDAO.select(id);
            if (params.containsKey("firstName") == true) {
                person.setFirstName((String)params.get("firstName"));
            }
            if (params.containsKey("lastName") == true) {
            person.setLastName((String)params.get("lastName"));
            }
            try {
                person.setRole(roleDAO.select(idRole));
            } catch (DAOEntityNotFoundException ex) {
                ui.printError(1001, APP_ERR_1001_UPDATE_DAO_ENTITY_NOT_FOUND);
            }
            personDAO.update(person);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1001, APP_ERR_1001_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a person in personDAO.
     *
     * @param id The id of the person to delete.
     */
    public void deletePerson(Long id) {
        personDAO.delete(id);
    }

    /**
     * Return every role in roleDAO.
     *
     * @return Every role in roleDAO.
     */
    public Collection<Role> getAllRoles() {
        return roleDAO.selectAll();
    }

    /**
     * Return a selection of role depending of criteria in roleDAO.
     *
     * @param params The searched roles parameters.
     * @return A selection of roles depending of the map.
     */
    public Collection<Role> getRolesByCriteria(Map<String, Object> params) {
        return roleDAO.select(params);
    }

    /**
     * Create a role and insert it in collection roleDAO.
     *
     * @param id The id of the added role.
     * @param title The title of the added role.
     */
    public void createRole(Long id, String title) {
        Role role = new Role(id, title);
        try {
            roleDAO.insert(role);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createRole().
     *
     * @param title The title of the added role.
     */
    public void createRole(String title) {
        createRole(null, title);
    }

    /**
     * Modify a role in roleDAO and then update it with method update().
     *
     * @param id The id of the role to update.
     * @param title The modified title.
     */
    public void updateRole(Long id, String title) {
        try {
            Role role = roleDAO.select(id);
            role.setTitle(title);
            roleDAO.update(role);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a role in roleDAO.
     *
     * @param id The id of the role to delete.
     */
    public void deleteRole(Long id) {
        roleDAO.delete(id);
    }

    /**
     * Return the publisher corresponding the id inside publisherDAO.
     *
     * @param id The id corresponding to the publisher to return.
     * @return A publisher matching the id.
     */
    public Publisher getPublisher(Long id) {
        try {
            return publisherDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {}
        return null;
    }

    /**
     * Return the publisher corresponding the id inside publisherDAO.
     *
     * @param id The id corresponding to the publisher to return.
     * @param error The error code.
     * @param message The error message.
     * @return A publisher matching the id.
     */
    public Publisher getPublisher(Long id, long error, String message) {
        try {
            return publisherDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(error, message);
        }
        return null;
    }

    /**
     * Return a collection of publisher.
     *
     * @param publishersId A collection of Long corresponding publisher's ids.
     * @return A collection of publisher.
     */
    public Collection getPublishers(Collection<Long> publishersId) {
        Collection <Publisher> publishers = new ArrayList();
        Publisher publisher;
        for (Long id: publishersId) {
            publisher = getPublisher(id);
            if (publisher != null) {
                publishers.add(publisher);
            }
        }
        return publishers;
    }

    /**
     * Return a collection of publisher.
     *
     * @param publishersId A collection of Long corresponding publisher's ids.
     * @param error The error code.
     * @param message The error message.
     * @return A collection of publisher.
     */
    public Collection getPublishers(Collection<Long> publishersId, long error, String message) {
        Collection <Publisher> publishers = new ArrayList();
        Publisher publisher;
        for (Long id: publishersId) {
            publisher = getPublisher(id);
            if (publisher != null) {
                publishers.add(publisher);
            }
            else {
                ui.printError(error, message);
            }
        }
        return publishers;
    }

    /**
     * Return every publisher in publisherDAO.
     * 
     * @return Every publisher in publisherDAO.
     */
    public Collection<Publisher> getAllPublishers() {
        return publisherDAO.selectAll();
    }

    /**
     * Return a selection of publisher depending of criteria in publisherDAO.
     *
     * @param params The searched publishers parameters.
     * @return A selection of publishers depending of the map.
     */
    public Collection<Publisher> getPublishersByCriteria(Map<String, Object> params) {
        return publisherDAO.select(params);
    }

    /**
     * Create a publisher and insert it in collection publisherDAO.
     *
     * @param id The id of the added publisher.
     * @param name The name of the added publisher.
     */
    public void createPublisher(Long id, String name) {
        Publisher publisher = new Publisher(id, name);
        try {
            publisherDAO.insert(publisher);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createPublisher().
     *
     * @param name The name of the added publisher.
     */
    public void createPublisher(String name) {
        createPublisher(null, name);
    }

    /**
     * Modify a publisher in publisherDAO and then update it with method update().
     *
     * @param id The id of the publisher to update.
     * @param name The modified name.
     */
    public void updatePublisher(Long id, String name) {
        try {
            Publisher publisher = publisherDAO.select(id);
            publisher.setName(name);
            publisherDAO.update(publisher);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a publisher in publisherDAO.
     *
     * @param id The id of the publisher to delete.
     */
    public void deletePublisher(Long id) {
        publisherDAO.delete(id);
    }

    /**
     * Return the genre corresponding the id inside genreDAO.
     *
     * @param id The id corresponding to the genre to return.
     * @return A genre matching the id.
     */
    public Genre getGenre(Long id) {
        try {
            return genreDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {}
        return null;
    }

    /**
     * Return the genre corresponding the id inside genreDAO.
     *
     * @param id The id corresponding to the genre to return.
     * @param error The error code.
     * @param message Th error message.
     * @return A genre matching the id.
     */
    public Genre getGenre(Long id, long error, String message) {
        try {
            return genreDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(error, message);
        }
        return null;
    }

    /**
     * Return a collection of genre.
     *
     * @param genresId A collection of Long corresponding genre's ids.
     * @return A collection of genre.
     */
    public Collection getGenres(Collection<Long> genresId) {
        Collection <Genre> genres = new ArrayList();
        Genre genre;
        for (Long id: genresId) {
            genre = getGenre(id);
            if (genre != null) {
                genres.add(genre);
            }
        }
        return genres;
    }

    /**
     * Return a collection of genre.
     *
     * @param genresId A collection of Long corresponding genre's ids.
     * @param error The error code.
     * @param message The error message.
     * @return A collection of genre.
     */
    public Collection getGenres(Collection<Long> genresId, long error, String message) {
        Collection <Genre> genres = new ArrayList();
        Genre genre;
        for (Long id: genresId) {
            genre = getGenre(id);
            if (genre != null) {
                genres.add(genre);
            }
            else {
                ui.printError(error, message);
            }
        }
        return genres;
    }

    /**
     * Return every genre in genreDAO.
     *
     * @return Every genre in genreDAO.
     */
    public Collection<Genre> getAllGenres() {
        return genreDAO.selectAll();
    }

    /**
     * Return a selection of genre depending of criteria in genreDAO.
     *
     * @param params The searched genres parameters.
     * @return A selection of genres depending of the map.
     */
    public Collection<Genre> getGenresByCriteria(Map<String, Object> params) {
        return genreDAO.select(params);
    }

    /**
     * Create a genre and insert it in collection genreDAO.
     *
     * @param id The id of the added genre.
     * @param name The name of the added genre.
     */
    public void createGenre(Long id, String name) {
        Genre genre = new Genre(id, name);
        try {
            genreDAO.insert(genre);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createGenre().
     *
     * @param name The name of the added genre.
     */
    public void createGenre(String name) {
        createGenre(null, name);
    }

    /**
     * Modify a genre in genreDAO and then update it with method update().
     *
     * @param id The id of the genre to update.
     * @param name The modified name.
     */
    public void updateGenre(Long id, String name) {
        try {
            Genre genre = genreDAO.select(id);
            genre.setName(name);
            genreDAO.update(genre);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a genre in genreDAO.
     *
     * @param id The id of the genre to delete.
     */
    public void deleteGenre(Long id) {
        genreDAO.delete(id);
    }

    /**
     * Return the keyword corresponding the id inside keywordDAO.
     *
     * @param id The id corresponding to the keyword to return.
     * @return A keyword matching the id.
     */
    public Keyword getKeyword(Long id) {
        try {
            return keywordDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {}
        return null;
    }

    /**
     * Return the keyword corresponding the id inside keywordDAO.
     *
     * @param id The id corresponding to the keyword to return.
     * @param error The error code.
     * @param message Th error message.
     * @return A keyword matching the id.
     */
    public Keyword getKeyword(Long id, long error, String message) {
        try {
            return keywordDAO.select(id);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(error, message);
        }
        return null;
    }

    /**
     * Return a collection of keyword.
     *
     * @param keywordsId A collection of Long corresponding keyword's ids.
     * @return A collection of keyword.
     */
    public Collection getKeywords(Collection<Long> keywordsId) {
        Collection <Keyword> keywords = new ArrayList();
        Keyword keyword;
        for (Long id: keywordsId) {
            keyword = getKeyword(id);
            if (keyword != null) {
                keywords.add(keyword);
            }
        }
        return keywords;
    }

    /**
     * Return a collection of keyword.
     *
     * @param keywordsId A collection of Long corresponding keyword's ids.
     * @param error The error code.
     * @param message The error message.
     * @return A collection of keyword.
     */
    public Collection getKeywords(Collection<Long> keywordsId, long error, String message) {
        Collection <Keyword> keywords = new ArrayList();
        Keyword keyword;
        for (Long id: keywordsId) {
            keyword = getKeyword(id);
            if (keyword != null) {
                keywords.add(keyword);
            }
            else {
                ui.printError(error, message);
            }
        }
        return keywords;
    }

    /**
     * Return every keyword in keywordDAO.
     *
     * @return Every keyword in keywordDAO.
     */
    public Collection<Keyword> getAllKeywords() {
        return keywordDAO.selectAll();
    }

    /**
     * Return a selection of keyword depending of criteria in keywordDAO.
     *
     * @param params The searched keywords parameters.
     * @return A selection of keywords depending of the map.
     */
    public Collection<Keyword> getKeywordsByCriteria(Map<String, Object> params) {
        return keywordDAO.select(params);
    }

    /**
     * Create a keyword and insert it in collection keywordDAO.
     *
     * @param id The id of the added keyword.
     * @param name The name of the added keyword.
     */
    public void createKeyword(Long id, String name) {
        Keyword keyword = new Keyword(id, name);
        try {
            keywordDAO.insert(keyword);
        } catch (DAODuplicateEntityException ex) {
            ui.printError(2000, APP_ERR_2000_CREATE_DAO_DUPLICATE_ENTITY);
        }
    }

    /**
     * Overrided method createKeyword().
     *
     * @param name The name of the added keyword.
     */
    public void createKeyword(String name) {
        createKeyword(null, name);
    }

    /**
     * Modify a keyword in keywordDAO and then update it with method update().
     *
     * @param id The id of the keyword to update.
     * @param name The modified name.
     */
    public void updateKeyword(Long id, String name) {
        try {
            Keyword keyword = keywordDAO.select(id);
            keyword.setName(name);
            keywordDAO.update(keyword);
        } catch (DAOEntityNotFoundException ex) {
            ui.printError(1000, APP_ERR_1000_UPDATE_DAO_ENTITY_NOT_FOUND);
        }
    }

    /**
     * Delete a keyword in keywordDAO.
     *
     * @param id The id of the keyword to delete.
     */
    public void deleteKeyword(Long id) {
        keywordDAO.delete(id);
    }

    /**
     * @return the ui
     */
    public UI getUi() {
        return ui;
    }

    /**
     * @param ui the ui to set
     */
    public void setUi(UI ui) {
        this.ui = ui;
    }

    /**
     * Return run state.
     * @return Run state.
     */
    public boolean isRunning() {
        return run;
    }

    /**
     * @param run the run to set
     */
    public void setRun(boolean run) {
        this.run = run;
    }
}
