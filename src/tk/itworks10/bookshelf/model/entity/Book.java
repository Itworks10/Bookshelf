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
import java.util.Map;
import java.util.Objects;

/**
 * Class Book extends abstract class Entity.
 * <p>
 * Class Book is related to every other classes that expends from class Entity except class Role.
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class Book extends Entity {

    /**
     * String type attribute title.
     */
    protected String title;
    
    /**
     * Date type attribute releaseDate.
     */
    protected Date releaseDate;
    
    /**
     * Long type attribute numero.
     */
    protected Long numero;
    
    /**
     * Long type attribute pageCount.
     */
    protected Long pageCount;

    /**
     * Collection of Person type attribute persons.
     */
    protected Collection<Person> persons;
    
    /**
     * Collection of Keyword type attribute keywords.
     */
    protected Collection<Keyword> keywords;
    
    /**
     * Collection of Genre type attribute genres.
     */
    protected Collection<Genre> genres;
    
    /**
     * Collection of Publisher type attribute publishers.
     */
    protected Collection<Publisher> publishers;

    /**
     * Default constructor.
     * <p>
     * Set id from Entity to null, Set title to empty character string, set releaseDate to null,
     * set numero to null, set pageCount to null, create an empty ArrayList persons, create an empty
     * ArrayList keywords, create an empty ArrayList genres, create an empty ArrayList publishers.
     */
    public Book() {
        super();
        setTitle("");
        setReleaseDate(null);
        setNumero(null);
        setPageCount(null);

        persons = new ArrayList();
        keywords = new ArrayList();
        genres = new ArrayList();
        publishers = new ArrayList();
    }

    /**
     * Complete constructor.
     * create an empty ArrayList persons, create an empty ArrayList keywords, create an empty
     * ArrayList genres, create an empty ArrayList publishers.
     * @param id Long
     * @param title String
     * @param releaseDate Date
     * @param numero Long
     * @param pageCount Long
     */
    public Book(Long id, String title, Date releaseDate, Long numero, Long pageCount) {
        super(id);
        setTitle(title);
        setReleaseDate(releaseDate);
        setNumero(numero);
        setPageCount(pageCount);

        persons = new ArrayList();
        keywords = new ArrayList();
        genres = new ArrayList();
        publishers = new ArrayList();
    }

    /**
     * Add a Person to the ArrayList persons.
     * @param person The person to add.
     * @return false if person is already added to ArrayList persons, true otherwise.
     */
    public Boolean addRelatedPerson(Person person) {
        if (isRelatedTo(person)) {
            return false;
        }
        persons.add(person);
        return true;
    }

    /**
     * Remove a Person from the ArrayList persons.
     * @param person The person to remove.
     */
    public void removeRelatedPerson(Person person) {
        persons.remove(person);
    }

    /**
     * Add a Keyword to the ArrayList keywords.
     * @param keyword The keyword to add.
     * @return false if keyword is already added to ArrayList keywords, true otherwise.
     */
    public Boolean addRelatedKeyword(Keyword keyword) {
        if (isRelatedTo(keyword)) {
            return false;
        }
        keywords.add(keyword);
        return true;
    }

    /**
     * Remove a Keyword from the ArrayList keywords.
     * @param keyword The keyword to remove.
     */
    public void removeRelatedKeyword(Keyword keyword) {
        keywords.remove(keyword);
    }

    /**
     * Add a Genre to the ArrayList genres.
     * @param genre The genre to add.
     * @return false if genre is already added to ArrayList genres, true otherwise.
     */
    public Boolean addRelatedGenre(Genre genre) {
        if (isRelatedTo(genre)) {
            return false;
        }
        genres.add(genre);
        return true;
    }

    /**
     * Remove a Genre from the ArrayList genres.
     * @param genre The genre to remove.
     */
    public void removeRelatedGenre(Genre genre) {
        genres.remove(genre);
    }

    /**
     * Add a Publisher to the ArrayList publishers.
     * @param publisher The publisher to add.
     * @return false if publisher is already added to ArrayList publishers, true otherwise.
     */
    public Boolean addRelatedPublisher(Publisher publisher) {
        if (isRelatedTo(publisher)) {
            return false;
        }
        publishers.add(publisher);
        return true;
    }

    /**
     * Remove a Publisher from the ArrayList publishers.
     * @param publisher The publisher to remove.
     */
    public void removeRelatedPublisher(Publisher publisher) {
        publishers.remove(publisher);
    }

    /**
     * Check if ArrayList persons contains a person.
     * @param person The person you want to know is inside the ArrayList or not.
     * @return True if persons contains person, false otherwise.
     */
    public Boolean isRelatedTo(Person person) {
        return persons.contains(person);
    }

    /**
     * Check if ArrayList publishers contains a publisher.
     * @param publisher The publisher you want to know is inside the ArrayList or not.
     * @return True if publishers contains publisher, false otherwise.
     */
    public Boolean isRelatedTo(Publisher publisher) {
        return publishers.contains(publisher);
    }

    /**
     * Check if ArrayList genres contains a genre.
     * @param genre The genre you want to know is inside the ArrayList or not.
     * @return True if genres contains genre, false otherwise.
     */
    public Boolean isRelatedTo(Genre genre) {
        return genres.contains(genre);
    }

    /**
     * Check if ArrayList keywordss contains a keyword.
     * @param keyword The keyword you want to know is inside the ArrayList or not.
     * @return True if keywords contains keyword, false otherwise.
     */
    public Boolean isRelatedTo(Keyword keyword) {
        return keywords.contains(keyword);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the pageCount
     */
    public Long getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the persons
     */
    public Collection<Person> getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    /**
     * @return the keywords
     */
    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the genres
     */
    public Collection<Genre> getGenres() {
        return genres;
    }

    /**
     * @param genres the genres to set
     */
    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    /**
     * @return the publishers
     */
    public Collection<Publisher> getPublishers() {
        return publishers;
    }

    /**
     * @param publishers the publishers to set
     */
    public void setPublishers(Collection<Publisher> publishers) {
        this.publishers = publishers;
    }

    /**
     * Overloaded method equals from class Entity.
     * <p>
     * Compare a book to another object. Comparison is done by first checking if the two objects are the same, 
     * then by checking if the compared object is a Long, then an Entity, and lastly a Map whose key can be: 
     * </p>
     * <ul>
     * <li>title</li>
     * <li>releaseDate</li>
     * <li>numero</li>
     * <li>pageCount</li>
     * <li>persons</li>
     * <li>keywords</li>
     * <li>genres</li>
     * <li>publishers</li>
     * </ul>
     * @param obj The object to compare with.
     * @return true if the objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Long) {
            return this.id.equals(obj);
        }
        if (obj instanceof Entity) {
            if (this.id != null) {
                return this.id.equals(((Entity)obj).id);
            }
        }
        if (obj instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>)obj;
            if (map.isEmpty()) {
                return false;
            }
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                if (entry.getKey().equals("title")) {
                    if (this.title == null || !this.title.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("releaseDate")) {
                    if (this.releaseDate == null || !this.releaseDate.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("numero")) {
                    if (this.numero == null || !this.numero.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("pageCount")) {
                    if (this.pageCount == null || !this.pageCount.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("persons")) {
                    if (entry.getValue() instanceof Collection) {
                        Collection<Object> collection = (Collection<Object>)entry.getValue();
                        for (Object o : collection) {
                            if (!this.persons.contains(o)) {
                                return false;
                            }
                        }
                    }
                    else {
                        return false;
                    }
                }
                else if (entry.getKey().equals("keywords")) {
                    if (entry.getValue() instanceof Collection) {
                        Collection<Object> collection = (Collection<Object>)entry.getValue();
                        for (Object o : collection) {
                            if (!this.keywords.contains(o)) {
                                return false;
                            }
                        }
                    }
                    else {
                        return false;
                    }
                }
                else if (entry.getKey().equals("genres")) {
                    if (entry.getValue() instanceof Collection) {
                        Collection<Object> collection = (Collection<Object>)entry.getValue();
                        for (Object o : collection) {
                            if (!this.genres.contains(o)) {
                                return false;
                            }
                        }
                    }
                    else {
                        return false;
                    }
                }
                else if (entry.getKey().equals("publishers")) {
                    if (entry.getValue() instanceof Collection) {
                        Collection<Object> collection = (Collection<Object>)entry.getValue();
                        for (Object o : collection) {
                            if (!this.publishers.contains(o)) {
                                return false;
                            }
                        }
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return id from Entity, title, releaseDate, numero, pageCount, collection persons, collection
     * keywords, collection genres, collection publishers.
     */
    @Override
    public String toString() {
        return  "ID: " + this.id +
                "; Title: " + this.title +
                "; Release date: " + this.releaseDate +
                "; Numero: " + this.numero +
                "; Page count: " + this.pageCount + "\n" +
                "; Persons: " + persons + "\n" +
                "; Keywords: " + keywords + "\n" +
                "; Genres: " + genres + "\n" +
                "; Publishers: " + publishers;
    }
    
    /**
     *
     * @return hash.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.releaseDate);
        hash = 53 * hash + Objects.hashCode(this.numero);
        hash = 53 * hash + Objects.hashCode(this.pageCount);
        hash = 53 * hash + Objects.hashCode(this.persons);
        hash = 53 * hash + Objects.hashCode(this.keywords);
        hash = 53 * hash + Objects.hashCode(this.genres);
        hash = 53 * hash + Objects.hashCode(this.publishers);
        return hash;
    }
}
