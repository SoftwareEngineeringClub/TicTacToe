// ##########################################################################
// # File Name:	SessionException.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
class SessionException 
    extends Exception
{

    private static final long serialVersionUID = 207124232407433896L;

    /************************************************************************
     * Creates a new SessionException. 
     *
     */
    public 
    SessionException()
    {
    }

    /************************************************************************
     * Creates a new SessionException. 
     *
     * @param message
     */
    public 
    SessionException(String message)
    {
        super( message );
    }

    /************************************************************************
     * Creates a new SessionException. 
     *
     * @param cause
     */
    public 
    SessionException(Throwable cause)
    {
        super( cause );
    }
    
}

// ##########################################################################
