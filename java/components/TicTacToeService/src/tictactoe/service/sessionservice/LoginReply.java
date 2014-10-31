// ##########################################################################
// # File Name:	LoginReply.java
// ##########################################################################

package tictactoe.service.sessionservice;


/****************************************************************************
 * Represents a reply to a request to login to the system. If the request 
 * was successful, {@code LoginReply.itsLoggedIn()} returns true and 
 * the {@code LoginReply.getSessionId()} returns the id of the new 
 * session. If the request failed, {@code LoginReply.itsLoggedIn()}
 * returns false and {@code LoginReply.getMessage()} returns a 
 * message explaining the failure.
 */
public 
class LoginReply 
	extends SessionReply
{
	private static final long serialVersionUID	= 2045086519428517965L;

	private long itsSessionId;
	private long itsUserId;
	
	/************************************************************************
	 * Creates a new LoginReply. 
	 *
	 * @param origRequest
	 */
	public
	LoginReply(LoginRequest origRequest)
	{
	    super( origRequest.getRequestId() );
	    itsSessionId = 0L;
	}
	
	/************************************************************************
	 * Creates a new LoginReply. 
	 *
	 * @param origRequestId
	 * @param sessionId
	 */
	public 
	LoginReply(long origRequestId,long sessionId)
	{
		super( origRequestId );
		itsSessionId = sessionId;
	}
	
    /************************************************************************
     * Sets the reply's sessionId to the identifier of the session 
     * created by the original {@code LoginRequest}. 
     *
     * @param   sessionId identifier of the newly created session
     * @return  reference to this {@code LoginReply} for method chaining
     */
    public LoginReply
    setSessionId(long sessionId)
    {
        itsSessionId = sessionId;
        return this;
    }
    
    /************************************************************************
     * Sets the reply's userId to the identifier of the user 
     * logging in via the original {@code LoginRequest}. 
     *
     * @param   userId identifier of the user logging in
     * @return  reference to this {@code LoginReply} for method chaining
     */
    public LoginReply
    setUserId(long userId)
    {
        itsUserId = userId;
        return this;
    }

    /************************************************************************
     * Sets the reply's message attribute to the specified message. 
     *
     * @param   message     message explaining the result
     * @return  reference to this {@code LoginReply} for method chaining
     */
    @Override
    public LoginReply
    setMessage(String message)
    {
        super.setMessage( message );
        return this;
    }
    
    /************************************************************************
     * Gets the reply's sessionId attribute. 
     *
     * @return  {@code if (isLoggedIn() == true)}, 
     *          the identifier of the newly created session,
     *          {@code else} 0L;
     */
    public long
    getSessionId()
    {
    	return itsSessionId;
    }
    
    /************************************************************************
     * Gets the reply's sessionId attribute. 
     *
     * @return  {@code if (isLoggedIn() == true)}, 
     *          the identifier of the logged in user,
     *          {@code else} 0L;
     */
    public long
    getUserId()
    {
        return itsUserId;
    }
    
    /************************************************************************
     * Gets the reply's loggedIn indicator 
     *
     * @return  true if original request was successful, false otherwise
     */
    public boolean
    isLoggedIn()
    {
        return itsSessionId != 0;
    }
}

// ##########################################################################
