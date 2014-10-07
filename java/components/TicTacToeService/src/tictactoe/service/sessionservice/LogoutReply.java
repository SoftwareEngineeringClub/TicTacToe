// ##########################################################################
// # File Name:	LogoutReply.java
// ##########################################################################

package tictactoe.service.sessionservice;


/****************************************************************************
 * Represents a reply to a request to logout of the system. If the request 
 * was successful, {@code LogoutReply.itsLoggedOut()} returns true and 
 * the {@code LogoutReply.getSessionId()} returns the id of the session
 * that has ended. If the request failed, {@code LogoutReply.itsLoggedOut()}
 * returns false and {@code LogoutReply.getMessage()} returns a 
 * message explaining the failure.
 */
public 
class LogoutReply 
	extends SessionReply
{

	private static final long serialVersionUID	= 3242615336600649611L;
	
	private long    itsSessionId;
	private boolean itsLoggedOutFlag;

	/************************************************************************
	 * Creates a new LogoutReply. 
	 *
	 * @param origRequestId
	 */
	public
	LogoutReply(LogoutRequest origRequest)
	{
	    super( origRequest.getRequestId() );
	    itsSessionId     = 0L;
	    itsLoggedOutFlag = false;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param sessionId
	 * @return
	 */
	public LogoutReply
	setSessionId(long sessionId)
	{
	    itsSessionId = sessionId;
	    return this;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param loggedOut
	 * @return
	 */
	public LogoutReply
	setLoggedOut(boolean loggedOut)
	{
	    itsLoggedOutFlag = loggedOut;
	    return this;
	}
	
    /************************************************************************
     * Sets the reply's message attribute to the specified message. 
     *
     * @param   message     message explaining the result
     * @return  reference to this {@code LogoutReply} for method chaining
     */
	@Override
	public LogoutReply
	setMessage(String message)
	{
	    super.setMessage( message );
	    return this;
	}
	
	/************************************************************************
	 * Gets the reply's sessionId. 
	 *
	 * @return the identifier of session being ended
	 */
	public long
	getSessionId()
	{
	    return itsSessionId;
	}
	
    /************************************************************************
     * Gets the reply's loggedOut indicator 
     *
     * @return  true if original request was successful, false otherwise
     */
	public boolean
	isLoggedOut()
	{
	    return itsLoggedOutFlag;
	}
}

// ##########################################################################
