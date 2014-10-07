// ##########################################################################
// # File Name:	IUserRepository.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import java.util.Collection;
import java.util.List;

/****************************************************************************
 * 
 */
public 
interface IUserRepository 
	extends IRepository
{
	public User
	insertUser(User user)
	    throws InsertFailedException;
	
	public User
	updateUser(User user)
	    throws UpdateFailedException;
	
	public void
	removeUser(User user)
	    throws RemoveFailedException;
	
	public List<User>
	getUsers();
	
	public User
	getUser(Long userId);
	
	public User
	getUserWithName(String userName)
	    throws InvalidInputException,NotUniqueException;
	
	public User
	getUserFor(Session session);
	
	public boolean
	hasUser(Long userId);
	
	public boolean
	hasUserWithName(String userName)
	    throws InvalidInputException;
}

// ##########################################################################
