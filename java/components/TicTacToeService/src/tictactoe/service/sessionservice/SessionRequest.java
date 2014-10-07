// ##########################################################################
// # File Name:	SessionRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * Base of all session requests.
 */
public abstract 
class SessionRequest 
	implements Serializable
{

	private static final long   serialVersionUID = 1827825312708837522L;
	private static final Random theirGenerator   = new SecureRandom();
	
	private final long itsRequestId;

	/************************************************************************
	 * Creates a new SessionRequest. 
	 *
	 */
	public
	SessionRequest()
	{
		this( theirGenerator.nextLong() );
	}
	
	/************************************************************************
	 * Creates a new SessionRequest. 
	 *
	 * @param requestId
	 */
	public 
	SessionRequest(final long requestId)
	{
		itsRequestId = requestId;
	}

	/************************************************************************
	 * Gets the request's identifier 
	 *
	 * @return request identifier
	 */
	public long
	getRequestId()
	{
		return itsRequestId;
	}
}

// ##########################################################################
