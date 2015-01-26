// ##########################################################################
// # File Name:	KeepAliveRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
class KeepAliveRequest 
    extends SessionRequest
{

    private static final long serialVersionUID = 4298540505971887424L;

    private long itsSessionId;
    
    /************************************************************************
     * Creates a new KeepAliveRequest. 
     *
     */
    public 
    KeepAliveRequest()
    {
        itsSessionId = 0L;
    }

    /************************************************************************
     * Creates a new KeepAliveRequest. 
     *
     * @param requestId
     */
    public 
    KeepAliveRequest(long requestId)
    {
        super( requestId );
        itsSessionId = 0L;
    }
    
    /************************************************************************
     * Sets the request's sessionId to the identifier 
     * of the session to be updated. 
     *
     * @param   sessionId identifier of session to end
     * @return  reference to this {@code KeepAliveRequest} 
     *          for method chaining
     */
    public KeepAliveRequest
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
