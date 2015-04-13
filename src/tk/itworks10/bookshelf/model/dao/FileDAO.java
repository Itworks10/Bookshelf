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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Class FileDAO implements interface DAO.
 *
 * <p>
 * Class FileDAO's pupose is to provide a DAO implementation which does not use any external storage engine.
 * </p>
 * Each entity is stored in a standard Collection and serialized on disk.
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 * @param <K>
 * @param <E>
 */
public abstract class FileDAO<K, E> implements DAO<K, E> {

    /**
     *E type attribute Collection entities. Used as a collection for entity book, genre, keyword, person,
     * publisher ands role.
     */
    protected Collection<E> entities;

    /**
     * Default constructor. Set a new ArrayList.
     */
    public FileDAO() {
        setEntities(new ArrayList());
    }

    /**
     * Complete constructor. Use an already defined Collection.
     * @param entities
     */
    public FileDAO(Collection<E> entities){
        setEntities(entities);
    }

    /**
     * Generate and check entities unique ids.
     * <p>
     * This method is called on each entity insertion by method insert.
     * This is the child responsability to ensure that every entity as a unique,
     * valid id and to keep track of the used ids.
     * </p>
     * @param entity to insert.
     */
    protected abstract void setUniqueId(E entity);

    /**
     * This entity can have an id preset but it needs to be unique. 
     * 
     * Otherwise the setUniqueId() method will generate a new one.
     * @param entity To insert.
     * @throws DAODuplicateEntityException thrown when an entity with the same key already exists.
     */
    @Override
    public void insert(E entity) throws DAODuplicateEntityException {
        if(entities.contains(entity)) {
            throw new DAODuplicateEntityException();
        }
        this.setUniqueId(entity);
        entities.add(entity);
    }

    /**
     * Return an entity matching the passed in key.
     *
     * @param key The searched entity's id.
     * @return An entity instance matching the passed in key.
     * @throws DAOEntityNotFoundException If no entity with this key is found.
     */
    @Override
    public E select(K key) throws DAOEntityNotFoundException {
        for (E entity : entities) {
            if (entity.equals(key)) {
                return entity;
            }
        }
        throw new DAOEntityNotFoundException();
    }

    /**
     * Return an entity's collection matching the parameters map.
     *
     * Each pair in the map must have aString key matching the name of the attribute attribute and a
     * value matching the attribute actual type and value.
     * @param params The searched entity's parameters.
     * @return A collection's entity matching parameters.
     */
    @Override
    public Collection<E> select(Map<String, Object> params) {
        Collection<E> collection = new ArrayList();
        for (E entity : entities) {
            if (entity.equals(params)) {
                collection.add(entity);
            }
        }
        return collection;
    }

    /**
     * Return every entity.
     *
     * @return A collection's entity.
     */
    @Override
    public Collection<E> selectAll() {
        Collection<E> collection = new ArrayList();
        collection.addAll(entities);
        return collection;
    }

    /**
     * Update an already existing entity.
     *
     * @param entity To update.
     * @return The actual entity instance after modification.
     * @throws DAOEntityNotFoundException if no matching entity is found using method equals.
     */
    @Override
    public E update(E entity) throws DAOEntityNotFoundException {
        if (entities.contains(entity)) {
            entities.remove(entity);
            entities.add(entity);
        }
        else {
            throw new DAOEntityNotFoundException();
        }
        return entity;
    }

    /**
     * Delete an existing entity. Nothing happens if no entity exists.
     *
     * @param key The searched entity's id.
     */
    @Override
    public void delete(K key) {
        for (Iterator<E> it = entities.iterator(); it.hasNext(); ) {
            if (it.next().equals(key)) {
                it.remove();
            }
        }
    }

    /**
     * @return entities
     */
    public Collection<E> getEntities() {
        return entities;
    }

    /**
     * @param entities
     */
    public void setEntities(Collection<E> entities) {
        this.entities = entities;
    }
}
