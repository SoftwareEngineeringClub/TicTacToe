// ##########################################################################
// # File Name:	SessionRepository.java
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
import java.util.List;

/****************************************************************************
 * 
 */
public 
class SessionRepository 
	implements ISessionRepository
{
	private final ISessionRepositoryProvider itsProvider;
	
	/************************************************************************
	 * Creates a new SessionRepository. 
	 *
	 */
	@Inject
	public 
	SessionRepository(ISessionRepositoryProvider provider)
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
	public Session 
	insertSession(Session session) 
		throws InsertFailedException
	{		
		return itsProvider.insertNew( session );
	}

	/************************************************************************
	 * {@inheritDoc} 
	 */
	@Override
	public Session 
	updateSession(Session session)
		throws UpdateFailedException
	{
		return itsProvider.updateExisting( session );
	}

	/************************************************************************
	 * {@inheritDoc} 
	 */
	@Override
	public void 
	removeSession(Session session)
	    throws RemoveFailedException
	{
	    itsProvider.removeExisting( session );
	}

	/************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public List<Session> 
    getSessions()
    {
        IFinder<Session> finder = getFinder("getSessions");
        
        return new ArrayList<Session>(finder.getAll());
    }

    /************************************************************************
	 * {@inheritDoc} 
	 */
	@Override
	public Session 
	getSession(Long sessionId)
	{
		return itsProvider.getExisting( sessionId );
	}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Session 
    getSessionFor(User user) 
        throws InvalidInputException, NotUniqueException
    {
        IFinder<Session> finder = getFinder("getSessionFor");
        
        finder.setInput( "userId",user.getUserId() );
        return finder.getUnique();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasSession(Long sessionId)
    {
        return itsProvider.hasExisting( sessionId );
    }
    /************************************************************************
     *  
     *
     * @param finderName
     * @return
     */
    private IFinder<Session> 
    getFinder(String finderName)
    {
        return 
            itsProvider.getFinder( 
                "tictactoe.domain.sessiondomain.Session." + finderName );
    }

}

// ##########################################################################
