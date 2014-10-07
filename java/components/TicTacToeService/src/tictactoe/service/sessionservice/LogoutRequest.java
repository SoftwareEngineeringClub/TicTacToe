// ##########################################################################
// # File Name:	LogoutRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;


/****************************************************************************
 * Represents a request to logout of the system  
 * by ending an existing session. 
 */
public 
class LogoutRequest 
	extends SessionRequest
{

	private static final long serialVersionUID	= 3618895032462358497L;
	
	private long itsSessionId;

	/************************************************************************
	 * Creates a new LogoutRequest. 
	 *
	 */
	public 
	LogoutRequest()
	{
	    itsSessionId = 0L;
	}

    /************************************************************************
     * Sets the request's sessionId to the identifier 
     * of the session to be ended. 
     *
     * @param   sessionId identifier of session to end
     * @return  reference to this {@code LogoutRequest} for method chaining
     */
    public LogoutRequest
    setSessionId(long sessionId)
    {
        itsSessionId = sessionId;
        return this;
    }
	
	/************************************************************************
	 * Gets the request's sessionId attribute. 
	 *
	 * @return identifier of session to end
	 */
	public long
	getSessionId()
	{
	    return itsSessionId;
	}
}

// ##########################################################################
