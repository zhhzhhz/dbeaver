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

import org.jkiss.dbeaver.model.struct.rdb.DBSProcedureParameterType;
import org.jkiss.utils.CommonUtils;

/**
 * Parameter/argument mode
 */
public enum DB2ParameterMode {
   IN(DBSProcedureParameterType.IN), OUT(DBSProcedureParameterType.OUT), INOUT(DBSProcedureParameterType.INOUT);
   private final DBSProcedureParameterType parameterType;

   DB2ParameterMode(DBSProcedureParameterType parameterType) {
      this.parameterType = parameterType;
   }

   public static DB2ParameterMode getMode(String modeName) {
      if (CommonUtils.isEmpty(modeName)) {
         return null;
      } else if ("IN".equals(modeName)) {
         return IN;
      } else if ("OUT".equals(modeName)) {
         return DB2ParameterMode.OUT;
      } else {
         return DB2ParameterMode.INOUT;
      }
   }

   public DBSProcedureParameterType getParameterType() {
      return parameterType;
   }
}