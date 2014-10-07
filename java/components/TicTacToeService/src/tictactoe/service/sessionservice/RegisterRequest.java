// ##########################################################################
// # File Name:	ReqisterRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * Represents a request to register a new user with the system. The request
 * contains the new user's <b>user name</b> and <b>password</b>.
 */
public 
class RegisterRequest 
    extends SessionRequest
{
    private static final long serialVersionUID = 8013604500635518591L;
    
    private String itsUserName;
    private String itsPassword;

    /************************************************************************
     * Creates a new ReqisterRequest. 
     *
     */
    public 
    RegisterRequest()
    {
        itsUserName = "";
        itsPassword = "";
    }

    /************************************************************************
     * Creates a new ReqisterRequest. 
     *
     * @param requestId
     */
    public 
    RegisterRequest(long requestId)
    {
        super( requestId );
    }

    /************************************************************************
     * Sets the request's user name. 
     *
     * @param   userName    the new user's user name.
     * @return  reference to this {@code RegisterRequest} 
     *          for method chaining
     */
    public RegisterRequest
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
    public RegisterRequest
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
