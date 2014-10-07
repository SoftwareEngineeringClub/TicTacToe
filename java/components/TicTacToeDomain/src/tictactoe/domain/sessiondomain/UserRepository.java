// ##########################################################################
// # File Name:	UserRepository.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import strata1.entity.repository.IFinder;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class UserRepository 
    implements IUserRepository
{
    private final IUserRepositoryProvider itsProvider;
    
    /************************************************************************
     * Creates a new UserRepository. 
     *
     */
    @Inject
    public 
    UserRepository(IUserRepositoryProvider provider)
    {
        itsProvider = provider;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IRepositoryContext 
    getContext()
    {
        return itsProvider.getContext();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IUnitOfWork 
    getUnitOfWork()
    {
        return getContext().getUnitOfWork();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public User 
    insertUser(User user) 
        throws InsertFailedException
    {
        return itsProvider.insertNew( user );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public User 
    updateUser(User user) 
        throws UpdateFailedException
    {
        return itsProvider.updateExisting( user );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    removeUser(User user) 
        throws RemoveFailedException
    {
        itsProvider.removeExisting( user );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public List<User> 
    getUsers()
    {
        IFinder<User> finder = getFinder("getUsers");
        
        return new ArrayList<User>(finder.getAll());
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public User 
    getUser(Long userId)
    {
        return itsProvider.getExisting( userId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public User 
    getUserWithName(String userName) 
        throws InvalidInputException,NotUniqueException
    {
        IFinder<User> finder = getFinder( "getUserWithName" );
        
        finder.setInput( "userName",userName );
        return finder.getUnique();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public User 
    getUserFor(Session session)
    {
        return getUser( session.getUserId() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasUser(Long userId)
    {
        return itsProvider.hasExisting( userId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasUserWithName(String userName)
        throws InvalidInputException
    {
        try
        {
            return getUserWithName(userName) != null;
        }
        catch (NotUniqueException e)
        {
            return true;
        }
    }

    /************************************************************************
     *  
     *
     * @param finderName
     * @return
     */
    private IFinder<User> 
    getFinder(String finderName)
    {
        return 
            itsProvider.getFinder( 
                "tictactoe.domain.sessiondomain.User." + finderName );
    }

}

// ##########################################################################
