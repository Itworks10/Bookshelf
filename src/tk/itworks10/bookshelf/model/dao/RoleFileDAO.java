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
import tk.itworks10.bookshelf.model.entity.Role;

/**
 * Class RoleFileDAO extends abstract class FileDAO.
 *
 * <p>
 * Class RoleFileDAO's purpose is to give next role added with method insert a Long id equal to largest
 * Id already given plus 1.
 * </p>
 * @author Itworks10 <Alexandre.V.Leger@gmail.com>
 */
public class RoleFileDAO extends FileDAO<Long, Role>{

    /**
     * Long type attribute next key. Used by method setUniqueId.
     */
    protected Long nextKey = new Long(0);

    /**
     * Default constructor. Call FileDAO's default constructor.
     */
    public RoleFileDAO() {
        super();
    }

    /**
     * Complete constructor. Call FileDAO's complete constructor.
     * @param entities
     */
    public RoleFileDAO(Collection<Role> entities){
        super(entities);
    }

    /**
     * Set Id to next role added with method insert from class FileDAO.
     * 
     * <p>
     * If added role Id is equal to null, give it a Long Id equal to largest Id already given plus 1.
     * If added role Id is new largest Id in collection, nextKey become this Id plus 1.
     * </p>
     * @param role
     */
    @Override
    public void setUniqueId(Role role) {
        if (role.getId() == null) {
            role.setId(nextKey);
            nextKey++;
        }
        else if (nextKey < role.getId()) {
            nextKey = role.getId() + 1;
        }
    }
}