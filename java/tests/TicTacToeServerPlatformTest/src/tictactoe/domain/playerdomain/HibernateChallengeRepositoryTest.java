// ##########################################################################
// # File Name:	HibernateChallengeRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;

/****************************************************************************
 * 
 */
public 
class HibernateChallengeRepositoryTest 
    extends ChallengeRepositoryTest
{

    /************************************************************************
     * Creates a new HibernateChallengeRepositoryTest. 
     *
     */
    public 
    HibernateChallengeRepositoryTest() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected PersistenceModule 
    getPersistenceModule()
    {
        return new HibernatePersistenceModule();
    }

}

// ##########################################################################
