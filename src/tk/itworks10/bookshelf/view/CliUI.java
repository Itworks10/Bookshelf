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
package tk.itworks10.bookshelf.view;

import java.util.ArrayList;
import tk.itworks10.bookshelf.controller.Application;
import tk.itworks10.bookshelf.model.entity.Book;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import tk.itworks10.bookshelf.model.entity.Genre;
import tk.itworks10.bookshelf.model.entity.Keyword;
import tk.itworks10.bookshelf.model.entity.Person;
import tk.itworks10.bookshelf.model.entity.Publisher;
import tk.itworks10.bookshelf.model.entity.Role;
import tk.itworks10.bookshelf.utils.InvalidDayException;
import tk.itworks10.bookshelf.utils.InvalidMonthException;
import tk.itworks10.bookshelf.utils.InvalidYearException;

/**
 * Class CliUI implements interface UI.
 *
 * Class CliUI's purpose is to be the command line interface of the program. It is in charge of 
 * printing menus and letting the user manages entities. It interact only with class Application.
 * @author Itworks10
 */
public class CliUI implements UI {

    /**
     * Scanner used by methods readLong(), readInt(), readString().
     */
    protected static Scanner in = new Scanner(System.in);

    /**
     * Constant to gain time in typing, reading and modifying CliUI.
     */
    public static final String CLI_MENU_ENTRY_TITLE = "(#########Bibliotheque#########)";
    public static final String CLI_MENU_ENTRY_EXIT = "(Exit to main Menu)";
    public static final String CLI_NOTICE_NO_BOOK_FOUND = "(<NOTICE> No book found)";
    public static final String CLI_NOTICE_NO_PERSON_FOUND = "(<NOTICE> No person found)";
    public static final String CLI_NOTICE_NO_ROLE_FOUND = "(<NOTICE> No role found)";
    public static final String CLI_NOTICE_NO_PUBLISHER_FOUND = "(<NOTICE> No publisher found)";
    public static final String CLI_NOTICE_NO_GENRE_FOUND = "(<NOTICE> No genre found)";
    public static final String CLI_NOTICE_NO_KEYWORD_FOUND = "(<NOTICE> No keyword found)";
    public static final String CLI_ASK_LIST_ID = "Do you want to see a list of used IDs ?";
    public static final String CLI_ASK_LIST_ID_BOOK = "Do you want to see a list of used books ID ?";
    public static final String CLI_ASK_LIST_ID_PERSON = "Do you want to see a list of used persons ID ?";
    public static final String CLI_ASK_LIST_ID_PUBLISHER = "Do you want to see a list of used publishers ID ?";
    public static final String CLI_ASK_LIST_ID_GENRE = "Do you want to see a list of used genres ID ?";
    public static final String CLI_ASK_LIST_ID_KEYWORD = "Do you want to see a list of used keywords ID ?";
    public static final String CLI_PROMPT_DELETE = "Enter any ID to delete.";
    public static final String CLI_PROMPT_UPDATE = "Enter any ID to update.";
    public static final String CLI_PROMPT_LINK = "Enter any ID to start.";
    public static final String CLI_PROMPT_FIRSTNAME_FIELD = "First name:";
    public static final String CLI_PROMPT_LASTNAME_FIELD = "Last name:";
    public static final String CLI_PROMPT_ROLE_FIELD = "Enter any role ID:";
    public static final String CLI_PROMPT_NAME_FIELD = "Name:";
    public static final String CLI_PROMPT_TITLE_FIELD = "Title:";
    public static final String CLI_PROMPT_RELEASEDATE_FIELD = "Release date:";
    public static final String CLI_PROMPT_NUMERO_FIELD = "Numero:";
    public static final String CLI_PROMPT_PAGECOUNT_FIELD = "Page count:";
    public static final String CLI_PROMPT_PERSON_FIELD_LINK = "Enter any person ID:";
    public static final String CLI_PROMPT_PUBLISHER_FIELD_LINK = "Enter any publisher ID:";
    public static final String CLI_PROMPT_GENRE_FIELD_LINK = "Enter any genre ID:";
    public static final String CLI_PROMPT_KEYWORD_FIELD_LINK = "Enter any keyword ID:";
    public static final String CLI_ASK_UPDATE_AGAIN = "Do you want to update something else ?";
    public static final String CLI_ASK_ADD_AGAIN = "Do you want to add something else ?";
    public static final String CLI_ASK_LINK_PERSONS = "Do you want to link another person ?";
    public static final String CLI_ASK_LINK_PUBLISHERS = "Do you want to link another publisher ?";
    public static final String CLI_ASK_LINK_GENRES = "Do you want to link another genre ?";
    public static final String CLI_ASK_LINK_KEYWORDS = "Do you want to link another keyword ?";


    /**
     * Used to make calls to Application.
     */
    protected Application app;

    /**
     * Constructor.
     */
    private CliUI() {}

    /**
     * Constructor.
     * @param app
     */
    public CliUI(Application app) {
        setApplication(app);
    }

    /**
     *
     */
    @Override
    public void init() {

    }

    /**
     * The main menu.
     */
    public void printMainMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("Main Menu");
        System.out.println("1: Manage books");
        System.out.println("2: Manage persons");
        System.out.println("3: Manage roles");
        System.out.println("4: Manage publishers");
        System.out.println("5: Manage genres");
        System.out.println("6: manage keywords");
        System.out.println("7: Exit");
    }

    /**
     * The book menu.
     */
    public void printManageBookMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List book(s)");
        System.out.println("2: Add book");
        System.out.println("3: Edit book");
        System.out.println("4: Delete book");
        System.out.println("5: Link persons to book");
        System.out.println("6: Link publishers to book");
        System.out.println("7: Link genres to book");
        System.out.println("8: Link keywords to book");
        System.out.println("9:" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing book menu.
     */
    public void printListBookMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all books");
        System.out.println("2: List book(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The person menu.
     */
    public void printManagePersonMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List person(s)");
        System.out.println("2: Add person");
        System.out.println("3: Edit person");
        System.out.println("4: Delete person");
        System.out.println("5 :" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing person menu.
     */
    public void printListPersonMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all persons");
        System.out.println("2: List person(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The role menu.
     */
    public void printManageRoleMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List role(s)");
        System.out.println("2: Add role");
        System.out.println("3: Edit role");
        System.out.println("4: Delete role");
        System.out.println("5 :" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing role menu.
     */
    public void printListRoleMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all roles");
        System.out.println("2: List role(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The publisher menu.
     */
    public void printManagePublisherMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List publisher(s)");
        System.out.println("2: Add publisher");
        System.out.println("3: Edit publisher");
        System.out.println("4: Delete publisher");
        System.out.println("5 :" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing publisher menu.
     */
    public void printListPublisherMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all publishers");
        System.out.println("2: List publisher(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The genre menu.
     */
    public void printManageGenreMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List genre(s)");
        System.out.println("2: Add genre");
        System.out.println("3: Edit genre");
        System.out.println("4: Delete genre");
        System.out.println("5 :" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing genre menu.
     */
    public void printListGenreMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all genres");
        System.out.println("2: List genre(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The keyword menu.
     */
    public void printManageKeywordMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List keyword(s)");
        System.out.println("2: Add keyword");
        System.out.println("3: Edit keyword");
        System.out.println("4: Delete keyword");
        System.out.println("5 :" + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * The listing keyword menu.
     */
    public void printListKeywordMenu() {
        System.out.println(CLI_MENU_ENTRY_TITLE);
        System.out.println("1: List all keywords");
        System.out.println("2: List keyword(s) by criteria");
        System.out.println("3: " + CLI_MENU_ENTRY_EXIT);
    }

    /**
     * Switch for the main menu.
     *
     * case 1 to 6 call a switch related to each class Entity's child. case 7 end the program.
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    @Override
    public void start() throws CLiInvalidChoiceException {
        printMainMenu();
        int choice = readMenuChoice(1, 7);
        switch (choice) {
            case 1: {
                manageBooks();
                break;
            }
            case 2: {
                managePersons();
                break;
            }
            case 3: {
                manageRoles();
                break;
            }
            case 4: {
                managePublishers();
                break;
            }
            case 5: {
                manageGenres();
                break;
            }
            case 6: {
                manageKeywords();
                break;
            }
            case 7: {
                app.setRun(Application.APP_STOPPED);
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the book menu.
     *
     * <p>
     * Case 1 call a switch for the list book menu. Case 2 to 4 call methods to add, edit or delete
     * books. Case 5 to 8 call methods to link other etntities to books. Case 9 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void manageBooks() throws CLiInvalidChoiceException {
        printManageBookMenu();
        int choice = readMenuChoice(1, 9);
        switch (choice) {
            case 1: {
                listBooks();
                break;
            }
            case 2: {
                addBook();
                break;
            }
            case 3: {
                editBook();
                break;
            }
            case 4: {
                deleteBook();
                break;
            }
            case 5: {
                linkPersonsToBook();
                break;
            }
            case 6: {
                linkPublishersToBook();
                break;
            }
            case 7: {
                linkGenresToBook();
                break;
            }
            case 8: {
                linkKeywordsToBook();
                break;
            }
            case 9: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list book menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all books or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listBooks() throws CLiInvalidChoiceException {
        printListBookMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllBooks();
                break;
            }
            case 2: {
                printBooksByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing books, or a message if nothing is found.
     */
    public void printAllBooks() {
        Collection<Book> books = app.getAllBooks();
        if (books.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_BOOK_FOUND);
            return;
        }
        for (Book book: books) {
            System.out.println("");
            System.out.println(book);
            System.out.println("");
        }
    }

    /**
     * Print books according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method only take as criteria for the moment:
     * <ul>
     * <li>title</li>
     * <li>releaseDate</li>
     * <li>numero</li>
     * <li>pageCount</li>
     * </ul>
     * Criteria: person, publisher, genre and keyword are yet to be added.
     * </p>
     */
    public void printBooksByCriteria() {
        Map<String, Object> params = new HashMap();
        String title;
        Date releaseDate = null;
        Long numero = null;
        Long pageCount = null;
        Boolean b, c, d;
        System.out.println(CLI_PROMPT_TITLE_FIELD);
        title = readString();
        b = readBooleanChoice("Do you want to search by release date ?");
        if (b == true) {
        System.out.println(CLI_PROMPT_RELEASEDATE_FIELD);
        releaseDate = readDate();
        }
        c = readBooleanChoice("Do you want to search by numero ?");
        if (c == true) {
        System.out.println(CLI_PROMPT_NUMERO_FIELD);
        numero = readLong();
        }
        d = readBooleanChoice("Do you want to search by page count ?");
        if (d == true) {
        System.out.println(CLI_PROMPT_PAGECOUNT_FIELD);
        pageCount = readLong();
        }
        if (!title.equals("")) {
            params.put("title", title);
        }
        if (releaseDate != null) {
            params.put("releaseDate", releaseDate);
        }
        if (numero != null) {
            params.put("numero", numero);
        }
        if (pageCount != null) {
            params.put("pageCount", pageCount);
        }

        Collection<Book> books = app.getBooksByCriteria(params);
        if (books.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_BOOK_FOUND);
            return;
        }
        for (Book book: books) {
            System.out.println("");
            System.out.println(book);
            System.out.println("");
        }
    }

    /**
     * Add a book and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>title</li>
     * <li>releaseDate</li>
     * <li>numero</li>
     * <li>pageCount</li>
     * </ul>
     * </p>
     */
    public void addBook() {
        String title;
        Date releaseDate;
        Long numero, pageCount;
        Boolean b = false;
        do {
            System.out.println(CLI_PROMPT_TITLE_FIELD);
            title = readString();
            System.out.println(CLI_PROMPT_RELEASEDATE_FIELD);
            releaseDate = readDate();
            System.out.println(CLI_PROMPT_NUMERO_FIELD);
            numero = readLong();
            System.out.println(CLI_PROMPT_PAGECOUNT_FIELD);
            pageCount = readLong();
            app.createBook(title, releaseDate, numero, pageCount);
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a book and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything.
     * </p>
     */
    public void editBook() {
        String title;
        Date releaseDate = null;
        Long id;
        Long numero = null;
        Long pageCount = null;
	Map<String, Object> params = new HashMap();
        Boolean list;
        Boolean b = false;
        Boolean c, d, e;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllBooks();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_TITLE_FIELD);
            title = readString();
            c = readBooleanChoice("Do you want to edit the release date ?");
            if (c == true) {
            System.out.println(CLI_PROMPT_RELEASEDATE_FIELD);
            releaseDate = readDate();
            }
            d = readBooleanChoice("Do you want to edit the numero ?");
            if (d == true) {
            System.out.println(CLI_PROMPT_NUMERO_FIELD);
            numero = readLong();
            }
            e = readBooleanChoice("Do you want to edit the page count ?");
            if (e == true) {
            System.out.println(CLI_PROMPT_PAGECOUNT_FIELD);
            pageCount = readLong();
            }
            if (!title.equals("")) {
                params.put("title", title);
            }
            if (releaseDate != null) {
        	params.put("releaseDate", releaseDate);
            }
            if (numero != null) {
        	params.put("numero", numero);
            }
            if (pageCount != null) {
        	params.put("pageCount", pageCount);
            }
            app.updateBook(id, params);
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a book.
     */
    public void deleteBook() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllBooks();
        }
        System.out.println(CLI_PROMPT_DELETE);
        id = readLong();
        app.deleteBook(id);
    }

    /**
     * Make the link between persons and a book.
     *
     * Add 1 or more person to a book's Person ArrayList.
     */
    public void linkPersonsToBook() {
        Boolean b, again, list;
        Long idBook, idPerson;
        ArrayList <Long> personsId = new ArrayList();
        b = readBooleanChoice(CLI_ASK_LIST_ID_BOOK);
        if (b == true) {
            printAllBooks();
        }
        System.out.println(CLI_PROMPT_LINK);
        idBook = readLong();
        list = readBooleanChoice(CLI_ASK_LIST_ID_PERSON);
        if (list == true) {
            printAllPersons();
        }
        do {
            System.out.println(CLI_PROMPT_PERSON_FIELD_LINK);
            idPerson = readLong();
            personsId.add(idPerson);
            again = readBooleanChoice(CLI_ASK_LINK_PERSONS);
        } while(again);
        Book book = app.getBook(idBook);
        Collection <Person> persons = app.getPersons(personsId);
        app.addRelatedPersons(book, persons);
    }

    /**
     * Make the link between publishers and a book.
     *
     * Add 1 or more publisher to a book's Publisher ArrayList.
     */
    public void linkPublishersToBook() {
        Boolean b, again, list;
        Long idBook, idPublisher;
        ArrayList <Long> publishersId = new ArrayList();
        b = readBooleanChoice(CLI_ASK_LIST_ID_BOOK);
        if (b == true) {
            printAllBooks();
        }
        System.out.println(CLI_PROMPT_LINK);
        idBook = readLong();
        list = readBooleanChoice(CLI_ASK_LIST_ID_PUBLISHER);
        if (list == true) {
            printAllPublishers();
        }
        do {
            System.out.println(CLI_PROMPT_PUBLISHER_FIELD_LINK);
            idPublisher = readLong();
            publishersId.add(idPublisher);
            again = readBooleanChoice(CLI_ASK_LINK_PUBLISHERS);
        } while(again);
        Book book = app.getBook(idBook);
        Collection <Publisher> publishers = app.getPublishers(publishersId);
        app.addRelatedPublishers(book, publishers);
    }

    /**
     * Make the link between genres and a book.
     *
     * Add 1 or more genre to a book's Genre ArrayList.
     */
    public void linkGenresToBook() {
        Boolean b, again, list;
        Long idBook, idGenre;
        ArrayList <Long> genresId = new ArrayList();
        b = readBooleanChoice(CLI_ASK_LIST_ID_BOOK);
        if (b == true) {
            printAllBooks();
        }
        System.out.println(CLI_PROMPT_LINK);
        idBook = readLong();
        list = readBooleanChoice(CLI_ASK_LIST_ID_GENRE);
        if (list == true) {
            printAllGenres();
        }
        do {
            System.out.println(CLI_PROMPT_GENRE_FIELD_LINK);
            idGenre = readLong();
            genresId.add(idGenre);
            again = readBooleanChoice(CLI_ASK_LINK_GENRES);
        } while(again);
        Book book = app.getBook(idBook);
        Collection <Genre> genres = app.getGenres(genresId);
        app.addRelatedGenres(book, genres);
    }

    /**
     * Make the link between keywords and a book.
     *
     * Add 1 or more keyword to a book's Keyword ArrayList.
     */
    public void linkKeywordsToBook() {
        Boolean b, again, list;
        Long idBook, idKeyword;
        ArrayList <Long> keywordsId = new ArrayList();
        b = readBooleanChoice(CLI_ASK_LIST_ID_BOOK);
        if (b == true) {
            printAllBooks();
        }
        System.out.println(CLI_PROMPT_LINK);
        idBook = readLong();
        list = readBooleanChoice(CLI_ASK_LIST_ID_KEYWORD);
        if (list == true) {
            printAllKeywords();
        }
        do {
            System.out.println(CLI_PROMPT_KEYWORD_FIELD_LINK);
            idKeyword = readLong();
            keywordsId.add(idKeyword);
            again = readBooleanChoice(CLI_ASK_LINK_KEYWORDS);
        } while(again);
        Book book = app.getBook(idBook);
        Collection <Keyword> keywords = app.getKeywords(keywordsId);
        app.addRelatedKeywords(book, keywords);
    }

    /**
     * Switch for the person menu.
     *
     * <p>
     * Case 1 call a switch for the list person menu. Case 2 to 4 call methods to add, edit or delete
     * persons. Case 5 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void managePersons() throws CLiInvalidChoiceException {
        printManagePersonMenu();
        int choice = readMenuChoice(1, 5);
        switch (choice) {
            case 1: {
                listPersons();
                break;
            }
            case 2: {
                addPerson();
                break;
            }
            case 3: {
                editPerson();
                break;
            }
            case 4: {
                deletePerson();
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list person menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all persons or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listPersons() throws CLiInvalidChoiceException {
        printListPersonMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllPersons();
                break;
            }
            case 2: {
                printPersonsByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing persons, or a message if nothing is found.
     */
    public void printAllPersons() {
        Collection<Person> persons = app.getAllPersons();
        if (persons.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_PERSON_FOUND);
            return;
        }
        for (Person person: persons) {
            System.out.println("");
            System.out.println(person);
            System.out.println("");
        }
    }

    /**
     * Print persons according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method only take as criteria for the moment:
     * <ul>
     * <li>firstName</li>
     * <li>lastName</li>
     * </ul>
     * Criterion role is yet to be added.
     * </p>
     */
    public void printPersonsByCriteria() {
        Map<String, Object> params = new HashMap();
        String firstName, lastName;
        System.out.println(CLI_PROMPT_FIRSTNAME_FIELD);
        firstName = readString();
        System.out.println(CLI_PROMPT_LASTNAME_FIELD);
        lastName = readString();
        if (!firstName.equals("")) {
            params.put("firstName", firstName);
        }
        if (!lastName.equals("")) {
            params.put("lastName", lastName);
        }
        Collection<Person> persons = app.getPersonsByCriteria(params);
        if (persons.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_PERSON_FOUND);
            return;
        }
        for (Person person: persons) {
            System.out.println("");
            System.out.println(person);
            System.out.println("");
        }
    }

    /**
     * Add a person and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>firstName</li>
     * <li>lastName</li>
     * </ul>
     * The user is also let the option to choose from a role list to add a criterion:
     * <ul>
     * <li>role</li>
     * </ul>
     * </p>
     */
    public void addPerson() {
        String firstName, lastName;
        Boolean b = false;
        Boolean role;
        Boolean list;
        Long idRole;
        do {
            System.out.println(CLI_PROMPT_FIRSTNAME_FIELD);
            firstName = readString();
            System.out.println(CLI_PROMPT_LASTNAME_FIELD);
            lastName = readString();
            role = readBooleanChoice("Do you want to link a role to that person ?");
            if (role.equals(true)) {
                list = readBooleanChoice(CLI_ASK_LIST_ID);
                if (list == true) {
                    printAllRoles();
                }
                System.out.println(CLI_PROMPT_ROLE_FIELD);
                idRole = readLong();
                app.createPerson(firstName, lastName, idRole);
            }
            else {
                app.createPerson(firstName, lastName);
            }
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a person and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything. The user is also let
     * the option to choose from a role list to edit the criterion role.
     * </p>
     */
    public void editPerson() {
        String firstName, lastName;
        Long id;
        Long idRole = null;
	Map<String, Object> params = new HashMap();
        Boolean list;
        Boolean role;
        Boolean b = false;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllPersons();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_FIRSTNAME_FIELD);
            firstName = readString();
            System.out.println(CLI_PROMPT_LASTNAME_FIELD);
            lastName = readString();
            role = readBooleanChoice("Do you want to edit the role of that person ?");
            if (role.equals(true)) {
                list = readBooleanChoice(CLI_ASK_LIST_ID);
                if (list == true) {
                    printAllRoles();
                }
                System.out.println(CLI_PROMPT_ROLE_FIELD);
                idRole = readLong();
            }
            if (!firstName.equals("")) {
                params.put("firstName", firstName);
            }
            if (!lastName.equals("")) {
        	params.put("lastName", lastName);
            }
            if (idRole != null) {
        	app.updatePerson(id, params, idRole);
            }
            else {
                app.updatePerson(id, params);
            }
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a person.
     */
    public void deletePerson() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllPersons();
        }
            System.out.println(CLI_PROMPT_DELETE);
            id = readLong();
                app.deletePerson(id);
    }

    /**
     * Switch for the role menu.
     *
     * <p>
     * Case 1 call a switch for the list role menu. Case 2 to 4 call methods to add, edit or delete
     * roles. Case 5 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void manageRoles() throws CLiInvalidChoiceException {
        printManageRoleMenu();
        int choice = readMenuChoice(1, 5);
        switch (choice) {
            case 1: {
                listRoles();
                break;
            }
            case 2: {
                addRole();
                break;
            }
            case 3: {
                editRole();
                break;
            }
            case 4: {
                deleteRole();
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list role menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all roles or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listRoles() throws CLiInvalidChoiceException {
        printListRoleMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllRoles();
                break;
            }
            case 2: {
                printRolesByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing roles, or a message if nothing is found.
     */
    public void printAllRoles() {
        Collection<Role> roles = app.getAllRoles();
        if (roles.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_ROLE_FOUND);
            return;
        }
        for (Role role: roles) {
            System.out.println("");
            System.out.println(role);
            System.out.println("");
        }
    }

    /**
     * Print roles according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method take as criteria:
     * <ul>
     * <li>title</li>
     * </ul>
     * </p>
     */
    public void printRolesByCriteria() {
        Map<String, Object> params = new HashMap();
        String title;
        System.out.println(CLI_PROMPT_TITLE_FIELD);
        title = readString();
        if (!title.equals("")) {
            params.put("title", title);
        }
        Collection<Role> roles = app.getRolesByCriteria(params);
        if (roles.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_ROLE_FOUND);
            return;
        }
        for (Role role: roles) {
            System.out.println("");
            System.out.println(role);
            System.out.println("");
        }
    }

    /**
     * Add a role and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>title</li>
     * </ul>
     * </p>
     */
    public void addRole() {
        String title;
        Boolean b = false;
        do {
            System.out.println(CLI_PROMPT_TITLE_FIELD);
            title = readString();
                app.createRole(title);
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a role and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything.
     * </p>
     */
    public void editRole() {
        String title;
        Long id;
        Boolean list;
        Boolean b = false;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllRoles();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_NAME_FIELD);
            title = readString();
            if (!title.equals("")) {
                app.updateRole(id, title);
            }
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a role.
     */
    public void deleteRole() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllRoles();
        }
            System.out.println(CLI_PROMPT_DELETE);
            id = readLong();
                app.deleteRole(id);
    }

    /**
     * Switch for the publisher menu.
     *
     * <p>
     * Case 1 call a switch for the list publisher menu. Case 2 to 4 call methods to add, edit or delete
     * publishers. Case 5 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void managePublishers() throws CLiInvalidChoiceException {
        printManagePublisherMenu();
        int choice = readMenuChoice(1, 5);
        switch (choice) {
            case 1: {
                listPublishers();
                break;
            }
            case 2: {
                addPublisher();
                break;
            }
            case 3: {
                editPublisher();
                break;
            }
            case 4: {
                deletePublisher();
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list publisher menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all publishers or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listPublishers() throws CLiInvalidChoiceException {
        printListPublisherMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllPublishers();
                break;
            }
            case 2: {
                printPublishersByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing publishers, or a message if nothing is found.
     */
    public void printAllPublishers() {
        Collection<Publisher> publishers = app.getAllPublishers();
        if (publishers.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_PUBLISHER_FOUND);
            return;
        }
        for (Publisher publisher: publishers) {
            System.out.println("");
            System.out.println(publisher);
            System.out.println("");
        }
    }

    /**
     * Print publishers according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method take as criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void printPublishersByCriteria() {
        Map<String, Object> params = new HashMap();
        String name;
        System.out.println(CLI_PROMPT_NAME_FIELD);
        name = readString();
        if (!name.equals("")) {
            params.put("name", name);
        }
        Collection<Publisher> publishers = app.getPublishersByCriteria(params);
        if (publishers.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_PUBLISHER_FOUND);
            return;
        }
        for (Publisher publisher: publishers) {
            System.out.println("");
            System.out.println(publisher);
            System.out.println("");
        }
    }

    /**
     * Add a publisher and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void addPublisher() {
        String name;
        Boolean b = false;
        do {
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            app.createPublisher(name);
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a publisher and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything.
     * </p>
     */
    public void editPublisher() {
        String name;
        Long id;
        Boolean list;
        Boolean b = false;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllPublishers();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            if (!name.equals("")) {
                app.updatePublisher(id, name);
            }
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a publisher.
     */
    public void deletePublisher() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllPublishers();
        }
            System.out.println(CLI_PROMPT_DELETE);
            id = readLong();
                app.deletePublisher(id);
    }

    /**
     * Switch for the genre menu.
     *
     * <p>
     * Case 1 call a switch for the list genre menu. Case 2 to 4 call methods to add, edit or delete
     * genres. Case 5 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void manageGenres() throws CLiInvalidChoiceException {
        printManageGenreMenu();
        int choice = readMenuChoice(1, 5);
        switch (choice) {
            case 1: {
                listGenres();
                break;
            }
            case 2: {
                addGenre();
                break;
            }
            case 3: {
                editGenre();
                break;
            }
            case 4: {
                deleteGenre();
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list genre menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all genres or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listGenres() throws CLiInvalidChoiceException {
        printListGenreMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllGenres();
                break;
            }
            case 2: {
                printGenresByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing genres, or a message if nothing is found.
     */
    public void printAllGenres() {
        Collection<Genre> genres = app.getAllGenres();
        if (genres.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_GENRE_FOUND);
            return;
        }
        for (Genre genre: genres) {
            System.out.println("");
            System.out.println(genre);
            System.out.println("");
        }
    }

    /**
     * Print genres according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method take as criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void printGenresByCriteria() {
        Map<String, Object> params = new HashMap();
        String name;
        System.out.println(CLI_PROMPT_NAME_FIELD);
        name = readString();
        if (!name.equals("")) {
            params.put("name", name);
        }
        Collection<Genre> genres = app.getGenresByCriteria(params);
        if (genres.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_GENRE_FOUND);
            return;
        }
        for (Genre genre: genres) {
            System.out.println("");
            System.out.println(genre);
            System.out.println("");
        }
    }

    /**
     * Add a genre and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void addGenre() {
        String name;
        Boolean b = false;
        do {
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            app.createGenre(name);
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a genre and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything.
     * </p>
     */
    public void editGenre() {
        String name;
        Long id;
        Boolean list;
        Boolean b = false;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllGenres();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            if (!name.equals("")) {
                app.updateGenre(id, name);
            }
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a genre.
     */
    public void deleteGenre() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllGenres();
        }
            System.out.println(CLI_PROMPT_DELETE);
            id = readLong();
                app.deleteGenre(id);
    }

    /**
     * Switch for the keyword menu.
     *
     * <p>
     * Case 1 call a switch for the list keyword menu. Case 2 to 4 call methods to add, edit or delete
     * keywords. Case 5 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void manageKeywords() throws CLiInvalidChoiceException {
        printManageKeywordMenu();
        int choice = readMenuChoice(1, 5);
        switch (choice) {
            case 1: {
                listKeywords();
                break;
            }
            case 2: {
                addKeyword();
                break;
            }
            case 3: {
                editKeyword();
                break;
            }
            case 4: {
                deleteKeyword();
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Switch for the list keyword menu.
     *
     * <p>
     * Case 1 to 2 call methods to print either all keywords or by criteria. Case 3 send back to main menu.
     * </p>
     * @throws CLiInvalidChoiceException In a very unlikely case where the user's choice couldn't be
     * handled by readMenuChoice().
     */
    public void listKeywords() throws CLiInvalidChoiceException {
        printListKeywordMenu();
        int choice = readMenuChoice(1, 3);
        switch (choice) {
            case 1: {
                printAllKeywords();
                break;
            }
            case 2: {
                printKeywordsByCriteria();
                break;
            }
            case 3: {
                break;
            }
            default: {
                throw new CLiInvalidChoiceException();
            }
        }
    }

    /**
     * Print every existing keywords, or a message if nothing is found.
     */
    public void printAllKeywords() {
        Collection<Keyword> keywords = app.getAllKeywords();
        if (keywords.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_KEYWORD_FOUND);
            return;
        }
        for (Keyword keyword: keywords) {
            System.out.println("");
            System.out.println(keyword);
            System.out.println("");
        }
    }

    /**
     * Print keywords according to criteria, or a message if nothing is found.
     *
     * <p>
     * This method take as criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void printKeywordsByCriteria() {
        Map<String, Object> params = new HashMap();
        String name;
        System.out.println(CLI_PROMPT_NAME_FIELD);
        name = readString();
        if (!name.equals("")) {
            params.put("name", name);
        }
        Collection<Keyword> keywords = app.getKeywordsByCriteria(params);
        if (keywords.isEmpty()) {
            System.out.println(CLI_NOTICE_NO_KEYWORD_FOUND);
            return;
        }
        for (Keyword keyword: keywords) {
            System.out.println("");
            System.out.println(keyword);
            System.out.println("");
        }
    }

    /**
     * Add a keyword and ask the user if he wants to add one more.
     *
     * <p>
     * Ask for the user to fill criteria:
     * <ul>
     * <li>name</li>
     * </ul>
     * </p>
     */
    public void addKeyword() {
        String name;
        Boolean b = false;
        do {
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            app.createKeyword(name);
            b = readBooleanChoice(CLI_ASK_ADD_AGAIN);
        } while (b);
    }

    /**
     * Edit a keyword and ask the user if he wants to edit one more.
     *
     * <p>
     * Leave the choice of not editing a criterion by not entering anything.
     * </p>
     */
    public void editKeyword() {
        String name;
        Long id;
        Boolean list;
        Boolean b = false;
        list = readBooleanChoice(CLI_ASK_LIST_ID);
        if (list == true) {
            printAllKeywords();
        }
        do {
            System.out.println(CLI_PROMPT_UPDATE);
            id = readLong();
            System.out.println(CLI_PROMPT_NAME_FIELD);
            name = readString();
            if (!name.equals("")) {
                app.updateKeyword(id, name);
            }
            b = readBooleanChoice(CLI_ASK_UPDATE_AGAIN);
        } while (b);
    }

    /**
     * Delete a keyword.
     */
    public void deleteKeyword() {
        Long id;
        Boolean b;
        b = readBooleanChoice(CLI_ASK_LIST_ID);
        if (b == true) {
            printAllKeywords();
        }
            System.out.println(CLI_PROMPT_DELETE);
            id = readLong();
                app.deleteKeyword(id);
    }

    /**
     * Overrided method printerror from interface UI.
     * @param error The error code.
     * @param message The error message.
     */
    @Override
    public void printError(long error, String message) {
        System.out.println("Error " + error + ": " + message);
    }

    /**
     * Read the user's choice.
     *
     * @param start The smallest int the user can type.
     * @param end The largest int the user can type.
     * @param message The message to print, if any.
     * @return The user's choice.
     */
    public int readMenuChoice(int start, int end, String message) {
	int choice = start - 1;
        System.out.println(message + " ");
        while (true) {
            try {
                choice = in.nextInt();
            }
            catch (Exception e) {
                choice = start -1;
            }
            in.nextLine();
            if ((choice >= start) && (choice <= end)) {
                break;
            }
            else {
                System.out.println (
                    "Valeur incorrecte. Entrez une valeur comprise entre " +
                    start + " et " + end + ".");
            }
        }
        return choice;
	}

    /**
     * Overrided method. Allow to not set any String message.
     *
     * @param start The smallest int the user can type.
     * @param end The largest int the user can type.
     * @return The user's choice.
     */
    protected  int readMenuChoice(int start, int end) {
        return readMenuChoice(start, end, "");
    }

    /**
     * Read the user's String.
     * 
     * @return The user's value.
     */
    public String readString() {
        return in.nextLine();
    }

    /**
     * Read the user's Long.
     *
     * @return The user's value.
     */
    public Long readLong() {
        Long l = in.nextLong();
        in.nextLine();
        return l;
    }

    /**
     * Read the user's int.
     *
     * @return The user's value.
     */
    public int readInt() {
        int l = in.nextInt();
        in.nextLine();
        return l;
    }

    /**
     * Read the user's Date.
     *
     * Substract 1 to month and 1900 to year, to correspond java.util.date constructor.
     * 
     * @return A new java.util.Date made from the user's values.
     */
    public Date readDate() {
        //DateFormat sdf = new SimpleDateFormat("MM dd yyyy");
        Date date = null;
        Boolean b = false;
        do {
            b = false;
            System.out.println("Day: ");
            int day = readInt();
            System.out.println("Month: ");
            int month = (readInt() - 1);
            System.out.println("Year: ");
            int year = (readInt() - 1900);
            try {
                date = tk.itworks10.bookshelf.utils.Date.parseDate(day, month, year);
            } catch (InvalidDayException ex1){
                printError(1000, ex1.getMessage());
                b = true;
            }
            catch (InvalidMonthException ex2){
                printError(2000, ex2.getMessage());
                b = true;
            }
            catch (InvalidYearException ex3){
                printError(3000, ex3.getMessage());
                b = true;
            }
        } while(b);
        return date;
    }

    /**
     * Read the user's choice.
     *
     * @param message
     * @return True if the user type "Y" or "YES"(upper or lower case), false otherwise.
     */
    public Boolean readBooleanChoice(String message) {
        System.out.println(message + " - (Y)es/(N)o");
        String s = in.nextLine();
        return (s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("yes"));
    }

    /**
     *
     * @return
     */
    @Override
    public Application getApplication() {
        return app;
    }

    /**
     *
     * @param app
     */
    @Override
    public void setApplication(Application app) {
        this.app = app;
    }
}