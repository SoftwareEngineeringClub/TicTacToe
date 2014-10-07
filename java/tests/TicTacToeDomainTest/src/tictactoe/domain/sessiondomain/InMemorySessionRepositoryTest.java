// ##########################################################################
// # File Name:	InMemorySessionRepositoryTest.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import tictactoe.domain.inmemorypersistence.InMemoryPersistenceModule;
import tictactoe.domain.persistence.PersistenceModule;

/****************************************************************************
 * 
 */
public 
class InMemorySessionRepositoryTest 
    extends SessionRepositoryTest
{

    /************************************************************************
     * Creates a new InMemorySessionRepositoryTest. 
     *
     */
    public 
    InMemorySessionRepositoryTest() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected PersistenceModule 
    getPersistenceModule()
    {
        return new InMemoryPersistenceModule();
    }

}

// ##########################################################################
