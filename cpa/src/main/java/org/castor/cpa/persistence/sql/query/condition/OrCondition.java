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
package org.castor.cpa.persistence.sql.query.condition;

import java.util.Iterator;

import org.castor.cpa.persistence.sql.query.QueryConstants;
import org.castor.cpa.persistence.sql.query.QueryContext;

/**
 * Represents a list of conditions that are concatenated by an OR operator.
 * 
 * @author <a href="mailto:ahmad DOT hassan AT gmail DOT com">Ahmad Hassan</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date: 2006-04-25 15:08:23 -0600 (Tue, 25 Apr 2006) $
 */
public final class OrCondition extends CompoundCondition {
    //-----------------------------------------------------------------------------------    
    
    /**
     * Default constructor for an empty OR condition. 
     */
    public OrCondition() {
        super();
    }
    
    /**
     * Constructor for a compound condition that concatenates the 2 given conditions with
     * an OR operator. 
     *  
     * @param condition1 First condition. 
     * @param condition2 Second condition. 
     */
    public OrCondition(final Condition condition1, final Condition condition2) {
        super();
        
        append(condition1);
        append(condition2);
    }
    
    /**
     * Copy constructor to create a copy of given OR condition.
     * 
     * @param condition OR condition to create a copy of.
     */
    public OrCondition(final OrCondition condition) {
        super(condition);
    }
    
    //-----------------------------------------------------------------------------------    

    @Override
    public Condition or(final Condition condition) {
        append(condition);
        return this;
    }

    @Override
    public Condition not() {
        Condition condition = new OrCondition();
        for (Iterator<Condition> iter = iterator(); iter.hasNext(); ) {
            condition.or(iter.next().not());
        }
        return condition;
    }

    //-----------------------------------------------------------------------------------    

    @Override
    public void toString(final QueryContext ctx) {
        for (Iterator<Condition> iter = iterator(); iter.hasNext(); ) {
            Condition condition = iter.next();
            if (condition instanceof CompoundCondition) {
                ctx.append(QueryConstants.LPAREN);
                condition.toString(ctx);
                ctx.append(QueryConstants.RPAREN);
            } else {
                condition.toString(ctx);
            }
            if (iter.hasNext()) {
                ctx.append(QueryConstants.SPACE);
                ctx.append(QueryConstants.OR);
                ctx.append(QueryConstants.SPACE);
            }
        }
    }
    
    //-----------------------------------------------------------------------------------    
}
