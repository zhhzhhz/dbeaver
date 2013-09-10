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
package org.jkiss.dbeaver.ext.db2.model.dict;

/**
 * DB2 Index Column Order
 * 
 * @author Denis Forveille
 * 
 */
public enum DB2IndexColOrder {
   A("A (Ascending)", true),

   D("D (Descending)", false),

   I("I (Include)", true);

   private String  description;
   private Boolean ascending;

   // -----------------
   // Constructor
   // -----------------
   private DB2IndexColOrder(String description, Boolean ascending) {
      this.description = description;
      this.ascending = ascending;
   }

   // ----------------
   // Standard Getters
   // ----------------
   public String getDescription() {
      return description;
   }

   public Boolean isAscending() {
      return ascending;
   }
}