// ##########################################################################
// # File Name:	HibernatePlayerRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;

/****************************************************************************
 * 
 */
public 
class HibernatePlayerRepositoryTest 
    extends PlayerRepositoryTest
{

    /************************************************************************
     * Creates a new HibernatePlayerRepositoryTest. 
     *
     */
    public 
    HibernatePlayerRepositoryTest() {}

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
