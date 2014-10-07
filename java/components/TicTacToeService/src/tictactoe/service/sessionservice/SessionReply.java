// ##########################################################################
// # File Name:	SessionReply.java
// ##########################################################################

package tictactoe.service.sessionservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * Base of all session replies.
 * 
 */
public abstract 
class SessionReply 
	implements Serializable
{

	private static final long   serialVersionUID = -5043750254398417368L;
	private static final Random theirGenerator   = new SecureRandom();
	
	private final long itsReplyId;
	private final long itsOriginatingRequestId;
	private String     itsMessage;

	/************************************************************************
	 * Creates a new SessionReply. 
	 *
	 * @param originatingRequestId
	 */
	public 
	SessionReply(long originatingRequestId)
	{
		this( theirGenerator.nextLong(),originatingRequestId );
		itsMessage = "";
	}

	/************************************************************************
	 * Creates a new SessionReply. 
	 *
	 * @param replyId
	 * @param origRequestId
	 */
	public 
	SessionReply(final long replyId,final long origRequestId)
	{
		itsReplyId              = replyId;
		itsOriginatingRequestId = origRequestId;
	}
	  
    /************************************************************************
     * Sets the reply's message attribute to the specified message. 
     *
     * @param   message     message explaining the result
     * @return  reference to this {@code SessionReply} for method chaining
     */
    public SessionReply
    setMessage(String message)
    {
        itsMessage = message;
        return this;
    }
	
	/************************************************************************
	 * Gets the reply's identifier. 
	 *
	 * @return reply identifier
	 */
	public long
	getReplyId()
	{
		return itsReplyId;
	}
	
	/************************************************************************
	 * Gets the originating request's identifier 
	 *
	 * @return originating request identifier
	 */
	public long
	getOriginatingRequestId()
	{
		return itsOriginatingRequestId;
	}
	
	/************************************************************************
	 * Gets the reply's message attribute. 
	 *
	 * @return message explaining reply
	 */
	public String
	getMessage()
	{
	    return itsMessage;
	}
}

// ##########################################################################
