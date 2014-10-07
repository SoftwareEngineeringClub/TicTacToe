// ##########################################################################
// # File Name:	InMemoryGameRepositoryTest.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import tictactoe.domain.inmemorypersistence.InMemoryPersistenceModule;
import tictactoe.domain.persistence.PersistenceModule;

/****************************************************************************
 * 
 */
public 
class InMemoryGameRepositoryTest 
    extends GameRepositoryTest
{

    /************************************************************************
     * Creates a new InMemoryGameRepositoryTest. 
     *
     */
    public 
    InMemoryGameRepositoryTest() {}

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
