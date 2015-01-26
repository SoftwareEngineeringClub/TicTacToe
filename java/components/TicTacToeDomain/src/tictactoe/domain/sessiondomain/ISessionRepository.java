// ##########################################################################
// # File Name:	ISessionRepository.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import java.util.List;

/****************************************************************************
 * 
 */
public 
interface ISessionRepository 
	extends IRepository
{
	public Session
	insertSession(Session session) 
		throws InsertFailedException;
	
	public Session
	updateSession(Session session)
		throws UpdateFailedException;
	
	public void
	removeSession(Session session)
		throws RemoveFailedException;
	
    public List<Session>
    getSessions();
    
	public Session
	getSession(Long sessionId);

	public Session
	getSessionFor(User user) 
	    throws InvalidInputException, NotUniqueException;
	
	public boolean
	hasSession(Long sessionId);
}

// ##########################################################################
