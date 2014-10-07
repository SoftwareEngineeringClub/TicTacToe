// ##########################################################################
// # File Name:	InMemoryPlayerRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.inmemorypersistence.InMemoryPersistenceModule;
import tictactoe.domain.persistence.PersistenceModule;

/****************************************************************************
 * 
 */
public 
class InMemoryPlayerRepositoryTest 
    extends PlayerRepositoryTest
{

    /************************************************************************
     * Creates a new InMemoryPlayerRepositoryTest. 
     *
     */
    public 
    InMemoryPlayerRepositoryTest() {}

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
