/*
 * Copyright 2009 Ralf Joachim, Ahmad Hassan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castor.cpa.persistence.sql.query.expression;

import org.castor.cpa.persistence.sql.query.Qualifier;

/**
 * Class representing a column of the database table.
 *  
 * TODO find a way to to get database specific quotation
 *  
 * @author <a href="mailto:ahmad DOT hassan AT gmail DOT com">Ahmad Hassan</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date: 2006-04-25 15:08:23 -0600 (Tue, 25 Apr 2006) $
 */
public class Column extends Expression {    
    //-----------------------------------------------------------------------------------    

    /** Character separating qualifier and column in a SQL query. */
    public static final char SEPARATOR = '.';

    /** Qualifier the column belongs to. May be <code>null</code> if there is no risk
     *  for name clashes for a SQL query. */
    private final Qualifier _qualifier;
    
    /** Name of the column. */
    private final String _name;
    
    /** Character for quoting qualifier and column names. */
    private final Character _quote = null;

    //-----------------------------------------------------------------------------------    

    /**
     * Construct a column that has no qualifier with given name.
     * 
     * @param name Name of the column.
     */
    public Column(final String name) {
        this(null, name);
 
    }
    
    /**
     * Construct a column with given qualifier and name.
     * 
     * @param qualifier Qualifier the column belongs to.
     * @param name Name of the column.
     */
    public Column(final Qualifier qualifier, final String name) {
        if (name == null) { throw new NullPointerException(); }
        
        _qualifier = qualifier;
        _name = name;
    }

    //-----------------------------------------------------------------------------------    

    /**
     * Returns qualifier the column belongs to.
     * 
     * @return Qualifier the column belongs to.
     */
    public final Qualifier qualifier() {
        return _qualifier;
    }

    /**
     * Returns name of the column.
     * 
     * @return Name of the column.
     */
    public final String name() {
        return _name;
    }

    //-----------------------------------------------------------------------------------    

    /**
     * {@inheritDoc}
     */
    public void toString(final StringBuilder sb) {
        if (_qualifier != null) {
            _qualifier.toString(sb);
            sb.append(SEPARATOR);
        }
        if (_quote == null) {
            sb.append(_quote);
            sb.append(_name);
            sb.append(_quote);
        } else {
            sb.append(_name);
        }
    }

    //-----------------------------------------------------------------------------------    
}
