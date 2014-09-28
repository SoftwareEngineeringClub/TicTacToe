// ##########################################################################
// # File Name:	LogoutReply.java
// ##########################################################################

package tictactoe.service.sessionservice;


/****************************************************************************
 * 
 */
public 
class LogoutReply 
	extends SessionReply
{

	private static final long serialVersionUID	= 3242615336600649611L;
	
	private final boolean itsLoggedInFlag;

	/************************************************************************
	 * Creates a new LogoutReply. 
	 *
	 * @param origRequestId
	 * @param loggedIn
	 */
	public 
	LogoutReply(
		final long origRequestId,
		final boolean loggedIn)
	{
		super( origRequestId );
		itsLoggedInFlag = loggedIn;
	}

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	isLoggedIn()
	{
		return itsLoggedInFlag;
	}
}

// ##########################################################################
