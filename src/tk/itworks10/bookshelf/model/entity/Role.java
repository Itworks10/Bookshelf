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

import java.util.Map;
import java.util.Objects;

/**
 * Class Role extends abstract class Entity.
 * <p>
 * Class Role is linked to class Person. A person possess one role.
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class Role extends Entity {

    /**
     * String type attribute title.
     */
    protected String title;

    /**
     * Default constructor.
     * <p>
     * Set id from Entity to null, Set title to empty character string.
     */
    public Role() {
        super();
        setTitle("");
    }

    /**
     * Complete constructor.
     * @param id Long
     * @param title String
     */
    public Role(Long id, String title) {
        super(id);
        setTitle(title);
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
     * Overloaded method equals from class Entity.
     * <p>
     * Compare a role to another object. Comparison is done by first checking if the two objects are the same,
     * then by checking if the compared object is a Long, then an Entity, and lastly a Map whose key can be:
     * </p>
     * <ul>
     * <li>title</li>
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
     * @return id from Entity, title.
     */
    @Override
    public String toString() {
        return  "ID: " + this.id +
                "; Title: " + this.title;
    }

    /**
     * 
     * @return hash.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.title);
        return hash;
    }
}
