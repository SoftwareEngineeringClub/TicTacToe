// ##########################################################################
// # File Name:	InMemoryChallengeRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.inmemorypersistence.InMemoryPersistenceModule;
import tictactoe.domain.persistence.PersistenceModule;

/****************************************************************************
 * 
 */
public 
class InMemoryChallengeRepositoryTest 
    extends ChallengeRepositoryTest
{

    /************************************************************************
     * Creates a new InMemoryPlayerRepositoryTest. 
     *
     */
    public 
    InMemoryChallengeRepositoryTest() {}

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
