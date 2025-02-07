/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2015 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jkiss.dbeaver.ext.postgresql.model;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCConstants;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCUtils;
import org.jkiss.dbeaver.model.impl.jdbc.cache.JDBCBasicDataTypeCache;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.struct.DBSDataSourceContainer;
import org.jkiss.utils.CommonUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PostgreMetaModel
 */
public class PostgreDataTypeCache extends JDBCBasicDataTypeCache
{
    public PostgreDataTypeCache(DBSDataSourceContainer owner) {
        super(owner);
    }

    @Override
    protected JDBCStatement prepareObjectsStatement(@NotNull JDBCSession session, @NotNull JDBCDataSource owner) throws SQLException
    {
        return session.prepareStatement("SELECT t.oid,t.* FROM pg_catalog.pg_type ORDER by t.oid");
    }

    @Override
    protected JDBCDataType fetchObject(@NotNull JDBCSession session, @NotNull JDBCDataSource owner, @NotNull ResultSet dbResult) throws SQLException, DBException
    {
        int typeId = JDBCUtils.safeGetInt(dbResult, "oid");
        String name = JDBCUtils.safeGetString(dbResult, "typname");
        if (CommonUtils.isEmpty(name)) {
            return null;
        }
        PostgreTypeType typeType = PostgreTypeType.valueOf(JDBCUtils.safeGetString(dbResult, "typtype"));
        return new JDBCDataType(
            owner,
            JDBCUtils.safeGetInt(dbResult, JDBCConstants.DATA_TYPE),
            name,
            JDBCUtils.safeGetString(dbResult, JDBCConstants.LOCAL_TYPE_NAME),
            JDBCUtils.safeGetBoolean(dbResult, JDBCConstants.UNSIGNED_ATTRIBUTE),
            JDBCUtils.safeGetInt(dbResult, JDBCConstants.SEARCHABLE) != 0,
            JDBCUtils.safeGetInt(dbResult, JDBCConstants.PRECISION),
            JDBCUtils.safeGetInt(dbResult, JDBCConstants.MINIMUM_SCALE),
            JDBCUtils.safeGetInt(dbResult, JDBCConstants.MAXIMUM_SCALE));
    }


}
