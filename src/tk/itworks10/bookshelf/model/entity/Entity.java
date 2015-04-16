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

import java.util.Objects;

/**
 * 
 * 
 * Entity is an abstract class. Classes Book, Genre, Keyword, Person, Publisher and Role extends from her.
 * <p>
 * Entity set up the methods equals and compareTo used by the classes that extends from her.
 * @author Itworks10
 */
public abstract class Entity implements Comparable<Entity>{

    /**
     * Long type attribute id.
     */
    protected Long id;

    /**
     * Default constructor.
     * <p>
     * set id to null.
     */
    public Entity(){
        setId(null);
    }

    /**
     *Complete constructor.
     * @param id Long
     */
    public Entity(Long id) {
       setId(id);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Compare this object to the specified object. If the result is false, compare this object to a Long.
     * If the result is false, compare this object id to an Entity id.
     * @param obj
     * @return true if matches, false otherwise.
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
            return this.id != null && this.id.equals(((Entity)obj).id);
        }
        return false;
    }

    /**
     * Compare this Long id to argument Long id.
     * @param o
     * @return 0 if this id equals the argument id. Less than 0 if this id is numerically less than the
     * argument id. More than 0 if this id is numerically more than the argument id.
     */
    @Override
    public int compareTo(Entity o) {
        return this.id.compareTo(o.id);
    }

    /**
     *
     * @return id.
     */
    @Override
    public String toString() {
        return  "ID: " + this.id;
    }

    /**
     *
     * @return hash.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
