/*
 * Copyright (C) 2010-2013 Serge Rieder
 * serge@jkiss.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.jkiss.dbeaver.ext.db2.model;

import org.jkiss.dbeaver.model.DBPQualifiedObject;
import org.jkiss.dbeaver.model.DBUtils;

/**
 * Abstract DB2 schema object
 */
public abstract class DB2SchemaObject extends DB2Object<DB2Schema> implements DBPQualifiedObject {
   protected DB2SchemaObject(DB2Schema schema, String name, boolean persisted) {
      super(schema, name, persisted);
   }

   public DB2Schema getSchema() {
      return getParentObject();
   }

   @Override
   public String getFullQualifiedName() {
      return DBUtils.getFullQualifiedName(getDataSource(), getParentObject(), this);
   }

}