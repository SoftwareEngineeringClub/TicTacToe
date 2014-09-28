// ##########################################################################
// # File Name:	SessionReply.java
// ##########################################################################

package tictactoe.service.sessionservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
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

	/************************************************************************
	 * Creates a new SessionReply. 
	 *
	 * @param originatingRequestId
	 */
	public 
	SessionReply(long originatingRequestId)
	{
		this( theirGenerator.nextLong(),originatingRequestId );
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
	 *  
	 *
	 * @return
	 */
	public long
	getReplyId()
	{
		return itsReplyId;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public long
	getOriginatingRequestId()
	{
		return itsOriginatingRequestId;
	}
}

// ##########################################################################
