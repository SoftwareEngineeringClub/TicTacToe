// ##########################################################################
// # File Name:	LoginReply.java
// ##########################################################################

package tictactoe.service.sessionservice;


/****************************************************************************
 * 
 */
public 
class LoginReply 
	extends SessionReply
{
	private static final long serialVersionUID	= 2045086519428517965L;

	private final long itsSessionId;
	
	/************************************************************************
	 * Creates a new LoginReply. 
	 *
	 * @param origRequestId
	 * @param sessionId
	 */
	public 
	LoginReply(final long origRequestId,final long sessionId)
	{
		super( origRequestId );
		itsSessionId = sessionId;
	}

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public long
	getSessionId()
	{
		return itsSessionId;
	}
}

// ##########################################################################
