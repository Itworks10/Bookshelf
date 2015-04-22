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
 * Class Role extends from abstract class Entity.
 * <p>
 * Class Person is linked to class Role. A person possess one role.
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class Person extends Entity {

    /**
     * String type attribute firstName.
     */
    protected String firstName;

    /**
     * String type attribute lastName.
     */
    protected String lastName;

    /**
     * Role type attribute role.
     */
    protected Role role;

    /**
     * Default constructor.
     * <p>
     * Set id from Entity to null, Set firstName to empty character string, set lastName to empty
     * character string, set role to null.
     */
    public Person() {
        super();
        setFirstName("");
        setLastName("");
        setRole(null);
    }

    /**
     * Complete constructor.
     * @param id Long
     * @param firstName String
     * @param lastName String
     * @param role Role
     */
    public Person(Long id, String firstName, String lastName, Role role) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(role);
    }

    /**
     * Compare this object role to a role. Unused as of right now.
     * @param role Role
     * @return true if matches, false otherwise.
     */
    public boolean isRelatedTo(Role role) {
        return this.role.equals(role);
    }

    /**
     * Compare this object role to the id of a role. Unused as of right now.
     * @param role Role
     * @return true if matches, false otherwise.
     */
    public boolean isRelatedTo(Long role) {
        return this.role.equals(role);
    }

    /**
     * Compare this object role to an object. Unused as of right now.
     * @param o Object
     * @return true if matches, false otherwise.
     */
    public boolean isRelatedTo(Object o) {
        return role.equals(o);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public final void setRole(Role role) {
        this.role = role;
    }

    /**
     * Overloaded method equals from class Entity.
     * <p>
     * Compare a person to another object. Comparison is done by first checking if the two objects are the same,
     * then by checking if the compared object is a Long, then an Entity, and lastly a Map whose key can be:
     * </p>
     * <ul>
     * <li>firstName</li>
     * <li>lastName</li>
     * <li>role</li>
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
                if (entry.getKey().equals("firstName")) {
                    if (this.firstName == null || !this.firstName.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("lastName")) {
                    if (this.lastName == null || !this.lastName.equals(entry.getValue())) {
                        return false;
                    }
                }
                else if (entry.getKey().equals("role")) {
                    if (this.role == null || !this.role.equals(entry.getValue())) {
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
     * @return id from Entity, firstName, lastName, role.
     */
    @Override
    public String toString() {
        return  "ID: " + this.id +
                "; First name: " + this.firstName +
                "; Last name: " + this.lastName +
                "; Role: " + this.role;
    }

    /**
     * 
     * @return hash.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + Objects.hashCode(this.role);
        return hash;
    }
}
