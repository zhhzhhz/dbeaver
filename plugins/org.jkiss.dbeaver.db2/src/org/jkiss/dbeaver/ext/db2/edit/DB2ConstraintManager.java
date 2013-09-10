/*
 * Copyright (C) 2010-2013 Serge Rieder serge@jkiss.org
 * Copyright (C) 2011-2012 Eugene Fradkin eugene.fradkin@gmail.com
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
package org.jkiss.dbeaver.ext.db2.edit;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.jkiss.dbeaver.ext.db2.DB2Messages;
import org.jkiss.dbeaver.ext.db2.model.DB2Table;
import org.jkiss.dbeaver.ext.db2.model.DB2TableUniqueKey;
import org.jkiss.dbeaver.model.edit.DBECommandContext;
import org.jkiss.dbeaver.model.impl.DBSObjectCache;
import org.jkiss.dbeaver.model.impl.jdbc.edit.struct.JDBCConstraintManager;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.ui.dialogs.struct.EditConstraintDialog;

/**
 * DB2 constraint manager
 */
public class DB2ConstraintManager extends JDBCConstraintManager<DB2TableUniqueKey, DB2Table> {

   @Override
   public DBSObjectCache<? extends DBSObject, DB2TableUniqueKey> getObjectsCache(DB2TableUniqueKey object) {
      return object.getParentObject().getSchema().getConstraintCache();
   }

   @Override
   protected DB2TableUniqueKey createDatabaseObject(IWorkbenchWindow workbenchWindow,
                                                    DBECommandContext context,
                                                    DB2Table parent,
                                                    Object from) {
      EditConstraintDialog editDialog = new EditConstraintDialog(workbenchWindow.getShell(),
                                                                 DB2Messages.edit_db2_constraint_manager_dialog_title,
                                                                 parent,
                                                                 new DBSEntityConstraintType[] {
                                                                          DBSEntityConstraintType.PRIMARY_KEY,
                                                                          DBSEntityConstraintType.UNIQUE_KEY });
      if (editDialog.open() != IDialogConstants.OK_ID) {
         return null;
      }

      // final DB2TableConstraint constraint = new DB2TableConstraint(
      // parent,
      // null,
      // editDialog.getConstraintType(),
      // null,
      // DB2ObjectStatus.ENABLED);
      //        constraint.setName(DBObjectNameCaseTransformer.transformName(constraint, CommonUtils.escapeIdentifier(parent.getName()) + "_PK")); //$NON-NLS-1$
      // int colIndex = 1;
      // for (DBSEntityAttribute tableColumn : editDialog.getSelectedColumns())
      // {
      // constraint.addColumn(
      // new DB2TableConstraintColumn(
      // constraint,
      // (DB2TableColumn) tableColumn,
      // colIndex++));
      // }
      // return constraint;
      return null;
   }

   @Override
   protected String getDropConstraintPattern(DB2TableUniqueKey constraint) {
      String clause;
      if (constraint.getConstraintType() == DBSEntityConstraintType.PRIMARY_KEY) {
         clause = "PRIMARY KEY"; //$NON-NLS-1$
      } else {
         clause = "KEY"; //$NON-NLS-1$
      }
      return "ALTER TABLE " + PATTERN_ITEM_TABLE + " DROP " + clause + " " + PATTERN_ITEM_CONSTRAINT; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

}