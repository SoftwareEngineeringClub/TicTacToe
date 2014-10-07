// ##########################################################################
// # File Name:	HibernateUserRepositoryTest.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.serverplatform.hibernatepersistence.HibernatePersistenceModule;

/****************************************************************************
 * 
 */
public 
class HibernateUserRepositoryTest 
    extends UserRepositoryTest
{

    /************************************************************************
     * Creates a new HibernateUserRepositoryTest. 
     *
     */
    public 
    HibernateUserRepositoryTest() {}

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
