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

/**
 * Abstract base class for all qualifiers. 
 *  
 * @author <a href="mailto:ahmad DOT hassan AT gmail DOT com">Ahmad Hassan</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date: 2006-04-25 15:08:23 -0600 (Tue, 25 Apr 2006) $
 */
public abstract class Qualifier extends QueryObject {
    //-----------------------------------------------------------------------------------    

    /** Name of the qualifier. */
    private final String _name;
    
    //-----------------------------------------------------------------------------------    

    /**
     * Construct a qualifier with given name.
     * 
     * @param name Name of the qualifier.
     */
    protected Qualifier(final String name) {
        _name = name;
    }
    
    //-----------------------------------------------------------------------------------    

    /**
     * Returns name of the qualifier.
     * 
     * @return Name of the qualifier.
     */
    public final String name() {
        return _name;
    }

    //-----------------------------------------------------------------------------------    
}

