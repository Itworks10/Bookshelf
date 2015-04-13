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

import java.util.Collection;
import java.util.Map;

/**
 * Interface DAO.
 *
 * <p>
 * Standard DAO interface. Used by each DAO classes.
 * </p>
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 * @param <K>
 * @param <E>
 */
public interface DAO<K, E> {

    /**
     * This entity can have an id preset but it needs to be unique. 
     * 
     * Otherwise the setUniqueId() method will generate a new one.
     * @param entity To insert.
     * @throws DAODuplicateEntityException thrown when an entity with the same key already exists.
     */
    public void insert(E entity) throws DAODuplicateEntityException;

    /**
     * Return an entity matching the passed in key.
     *
     * @param key The searched entity's id.
     * @return An entity instance matching the passed in key.
     * @throws DAOEntityNotFoundException If no entity with this key is found.
     */
    public E select(K key) throws DAOEntityNotFoundException;

    /**
     * Return an entity's collection matching the parameters map. 
     * 
     * Each pair in the map must have a String key matching the name of the attribute attribute and
     * a value matching the attribute actual type and value.
     * @param params The searched entity's parameters.
     * @return A collection's entity matching parameters.
     */
    public Collection<E> select(Map<String, Object> params);

    /**
     * Return every entity.
     * 
     * @return A collection's entity.
     */
    public Collection<E> selectAll();

    /**
     * Update an already existing entity.
     *
     * @param entity To update.
     * @return The actual entity instance after modification.
     * @throws DAOEntityNotFoundException if no matching entity is found using method equals.
     */
    public E update(E entity) throws DAOEntityNotFoundException;

    /**
     * Delete an existing entity. Nothing happens if no entity exists.
     *
     * @param key The searched entity's id.
     */
    public void delete(K key);
}
