// ##########################################################################
// # File Name:	HibernateGameRepositoryTest.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;

/****************************************************************************
 * 
 */
public 
class HibernateGameRepositoryTest 
    extends GameRepositoryTest
{

    /************************************************************************
     * Creates a new HibernateGameRepositoryTest. 
     *
     */
    public 
    HibernateGameRepositoryTest() {}

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
