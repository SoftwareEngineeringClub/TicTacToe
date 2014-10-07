// ##########################################################################
// # File Name:	HibernateSessionRepositoryTest.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;

/****************************************************************************
 * 
 */
public 
class HibernateSessionRepositoryTest 
    extends SessionRepositoryTest
{

    /************************************************************************
     * Creates a new HibernateSessionRepositoryTest. 
     *
     */
    public 
    HibernateSessionRepositoryTest() {}

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
