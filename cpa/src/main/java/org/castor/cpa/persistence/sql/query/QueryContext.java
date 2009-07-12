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
package org.castor.cpa.persistence.sql.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Function of QueryContext is 3 fold. It offers information about small syntax differences
 * of database engines. For example about quoting of qualifier and column names. In addition
 * it is a builder for SQL query strings. Third function is to map parameter names to indices
 * and offer a method to bind values to these named parameters.    
 * 
 * @author <a href="mailto:ahmad DOT hassan AT gmail DOT com">Ahmad Hassan</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date: 2006-04-25 15:08:23 -0600 (Tue, 25 Apr 2006) $
 */
public class QueryContext {
    //-----------------------------------------------------------------------------------    

    /** The <a href="http://jakarta.apache.org/commons/logging/">Jakarta
     *  Commons Logging</a> instance used for all logging. */
    private static final Log LOG = LogFactory.getLog(QueryContext.class);

    /** String for quoting qualifier and column names. */
    private final String _quote;
    
    /** StringBuilder to build the SQL query string. */
    private final StringBuilder _builder = new StringBuilder();
    
    /** Map of parameter names to indices filled during build of SQL query string. */
    private final Map<String, Integer> _parameters = new HashMap<String, Integer>();

    //-----------------------------------------------------------------------------------    

    /**
     * Default constructor for a delete query that does not quote qualifier and column names.
     */
    public QueryContext() {
        this("");
    }

    /**
     * Constructor for a delete query that uses given string to quote qualifier and column names.
     * 
     * @param quote String for quoting qualifier and column names.
     */
    public QueryContext(final String quote) {
        if (quote == null) { throw new NullPointerException(); }
        _quote = quote;
    }
    
    //-----------------------------------------------------------------------------------    

    /**
     * Returns string for quoting qualifier and column names.
     * 
     * @return String for quoting qualifier and column names.
     */
    public String getQuote() {
        return _quote;
    }
    
    //-----------------------------------------------------------------------------------    

    /**
     * Append the given character to end of SQL query string.
     * 
     * @param chr Character to append.
     * @return This QueryContext instance.
     */
    public final QueryContext append(final char chr) {
        _builder.append(chr);
        return this;
    }
    
    /**
     * Append the given string to end of SQL query string.
     * 
     * @param str String to append.
     * @return This QueryContext instance.
     */
    public final QueryContext append(final String str) {
        _builder.append(str);
        return this;
    }
    
    //-----------------------------------------------------------------------------------    

    /**
     * Returns the SQL query string build by previous calls to one of the append methods.
     * 
     * @return SQL query string.
     */
    public final String toString() {
        return _builder.toString();
    }

    //-----------------------------------------------------------------------------------    

    /**
     * Add a named parameter to the query context. It is important to add the parameter
     * names in the sequence the parameters appear in the SQL query string for binding to
     * work properly.
     * 
     * @param name Name of the parameter.
     */
    public final void addParameter(final String name) {
        _parameters.put(name, Integer.valueOf(_parameters.size() + 1));
    }
    
    /**
     * Bind value of named parameter to prepared statement. If parameter name is unknown no
     * binding takes place and its name will only be logged.
     * 
     * @param stmt Prepared statement to bind value of named parameter to.
     * @param name Name of the parameter to bind.
     * @param value Value of the named parameter to bind.
     * @throws SQLException If a database access error occurs or the type of the given object
     *         is ambiguous.
     */
    public final void bindParameter(final PreparedStatement stmt,
            final String name, final Object value)
    throws SQLException {
        Integer index = _parameters.get(name);
        if (index != null) {
            stmt.setObject(index.intValue(), value);
        } else {
            LOG.debug("Unknown parameter: " + name);
        }
    }
    
    //-----------------------------------------------------------------------------------    
}
