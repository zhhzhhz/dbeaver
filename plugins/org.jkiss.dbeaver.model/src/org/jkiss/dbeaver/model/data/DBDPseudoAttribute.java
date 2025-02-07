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

package org.jkiss.dbeaver.model.data;

import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;

/**
 * Pseudo attribute
 */
public class DBDPseudoAttribute implements DBPNamedObject {

    private final DBDPseudoAttributeType type;
    private final String name;
    private final String queryExpression;
    private final String alias;
    private final String description;

    public DBDPseudoAttribute(DBDPseudoAttributeType type, String name, String queryExpression, @Nullable String alias, String description)
    {
        this.type = type;
        this.name = name;
        this.queryExpression = queryExpression;
        this.alias = alias;
        this.description = description;
    }

    @NotNull
    @Override
    public String getName()
    {
        return name;
    }

    public DBDPseudoAttributeType getType()
    {
        return type;
    }

    public String getQueryExpression()
    {
        return queryExpression;
    }

    @Nullable
    public String getAlias()
    {
        return alias;
    }

    public String getDescription()
    {
        return description;
    }

    public String translateExpression(String tableAlias) {
        return queryExpression.replace("$alias", tableAlias);
    }

    @Override
    public String toString()
    {
        return name + " (" + type + ")";
    }

    public DBSEntityAttribute createFakeAttribute(DBSEntity owner, DBCAttributeMetaData attribute)
    {
        return new FakeEntityAttribute(owner, attribute);
    }

    @Nullable
    public static DBDPseudoAttribute getAttribute(DBDPseudoAttribute[] attributes, DBDPseudoAttributeType type)
    {
        if (attributes == null || attributes.length == 0) {
            return null;
        }
        for (DBDPseudoAttribute attribute : attributes) {
            if (attribute.getType() == type) {
                return attribute;
            }
        }
        return null;
    }

    private class FakeEntityAttribute implements DBSEntityAttribute {
        private DBSEntity owner;
        private DBCAttributeMetaData attribute;

        public FakeEntityAttribute(DBSEntity owner, DBCAttributeMetaData attribute)
        {
            this.owner = owner;
            this.attribute = attribute;
        }

        @Override
        public boolean isAutoGenerated()
        {
            return false;
        }

        @Override
        public boolean isPseudoAttribute() {
            return true;
        }

        @Override
        public int getOrdinalPosition()
        {
            return attribute.getOrdinalPosition();
        }

        @Nullable
        @Override
        public String getDefaultValue()
        {
            return null;
        }

        @NotNull
        @Override
        public DBSEntity getParentObject()
        {
            return owner;
        }

        @Nullable
        @Override
        public String getDescription()
        {
            return description;
        }

        @NotNull
        @Override
        public DBPDataSource getDataSource()
        {
            return owner.getDataSource();
        }

        @NotNull
        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public boolean isPersisted()
        {
            return true;
        }

        @Override
        public boolean isRequired()
        {
            return attribute.isRequired();
        }

        @Override
        public String getTypeName()
        {
            return attribute.getTypeName();
        }

        @Override
        public int getTypeID()
        {
            return attribute.getTypeID();
        }

        @Override
        public DBPDataKind getDataKind()
        {
            return attribute.getDataKind();
        }

        @Override
        public int getScale()
        {
            return attribute.getScale();
        }

        @Override
        public int getPrecision()
        {
            return attribute.getPrecision();
        }

        @Override
        public long getMaxLength()
        {
            return attribute.getMaxLength();
        }
    }
}
