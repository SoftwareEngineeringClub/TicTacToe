// ##########################################################################
// # File Name:	RegisterReply.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * Represents a reply to a request to register a new user with the system.
 * If the request is successful, {@code RegisterReply.isRegistered()}
 * returns true. If the request failed, {@code RegisterReply.isRegistered()}
 * returns false and {@code RegisterReply.getMessage()} returns a 
 * message explaining the failure.
 */
public 
class RegisterReply 
    extends SessionReply
{
    private static final long serialVersionUID = 7730376731600477898L;
    
    private Boolean itsRegisteredFlag;

    /************************************************************************
     * Creates a new RegisterReply. 
     *
     * @param originatingRequest
     */
    public 
    RegisterReply(RegisterRequest originatingRequest)
    {
        super( originatingRequest.getRequestId() );
        itsRegisteredFlag = false;
    }

    /************************************************************************
     * Creates a new RegisterReply. 
     *
     * @param replyId
     * @param origRequestId
     */
    public 
    RegisterReply(long replyId,long origRequestId)
    {
        super( replyId,origRequestId );
    }

    /************************************************************************
     * Sets the registered indicator to the specified input. 
     *
     * @param   registered    indicates success (true) or failure (false)
     * @return  reference to this {@code RegisterReply} for method chaining
     */
    public RegisterReply
    setRegistered(Boolean registered)
    {
        itsRegisteredFlag = registered;
        return this;
    }
    
    /************************************************************************
     * Sets the message attribute to the specified message. 
     *
     * @param   message     message explaining the result
     * @return  reference to this {@code RegisterReply} for method chaining
     */
    @Override
    public RegisterReply
    setMessage(String message)
    {
        super.setMessage( message );
        return this;
    }
    
    /************************************************************************
     * Queries the registered indicator. 
     *
     * @return true if registered and false otherwise
     */
    public Boolean
    isRegistered()
    {
        return itsRegisteredFlag;
    }
    
}

// ##########################################################################
