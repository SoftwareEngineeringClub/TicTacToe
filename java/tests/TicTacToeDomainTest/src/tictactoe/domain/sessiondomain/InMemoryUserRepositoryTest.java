// ##########################################################################
// # File Name:	InMemoryUserRepositoryTest.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import tictactoe.domain.inmemorypersistence.InMemoryPersistenceModule;
import tictactoe.domain.persistence.PersistenceModule;

/****************************************************************************
 * 
 */
public 
class InMemoryUserRepositoryTest 
    extends UserRepositoryTest
{

    /************************************************************************
     * Creates a new InMemoryUserRepositoryTest. 
     *
     */
    public 
    InMemoryUserRepositoryTest() {}

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
