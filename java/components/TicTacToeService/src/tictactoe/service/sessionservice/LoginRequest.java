// ##########################################################################
// # File Name:	LoginRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
class LoginRequest 
	extends SessionRequest
{
	private static final long   serialVersionUID = 8518679406286996581L;

	private String itsUserName;
	private String itsPassword;
	
	/************************************************************************
	 * Creates a new LoginRequest. 
	 *
	 */
	public
	LoginRequest()
	{
	    itsUserName = "";
	    itsPassword = "";
	}
	
	/************************************************************************
	 * Creates a new LoginRequest. 
	 *
	 * @param userName
	 * @param password
	 */
	public 
	LoginRequest(String userName,String password)
	{
		itsUserName = userName;
		itsPassword = password;
	}

    /************************************************************************
     * Sets the request's user name. 
     *
     * @param   userName    the new user's user name.
     * @return  reference to this {@code RegisterRequest} 
     *          for method chaining
     */
    public LoginRequest
    setUserName(String userName)
    {
        itsUserName = userName;
        return this;
    }
    
    /************************************************************************
     * Sets the request's password. 
     *
     * @param   password    the new user's password
     * @return  reference to this {@code RegisterRequest} 
     *          for method chaining
     */
    public LoginRequest
    setPassword(String password)
    {
        itsPassword = password;
        return this;
    }
    
    /************************************************************************
     * Gets the request's username. 
     *
     * @return  request's userName
     */
    public String
    getUserName()
    {
        return itsUserName;
    }
    
    /************************************************************************
     * Gets the request's password 
     *
     * @return  request's password
     */
    public String
    getPassword()
    {
        return itsPassword;
    }
}

// ##########################################################################
