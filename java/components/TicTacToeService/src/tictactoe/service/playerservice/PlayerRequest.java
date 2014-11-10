// ##########################################################################
// # File Name:	PlayerRequest.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public abstract 
class PlayerRequest 
	implements Serializable
{

	private static final long   serialVersionUID = 2416174975117785049L;
    private static final Random theirGenerator   = new SecureRandom();

    private final long itsRequestId;
    private final Long itsSessionId;
    private final Long itsUserId;

    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     */
    public
    PlayerRequest(Long sessionId,Long userId)
    {
        this( generateId(),sessionId,userId );
    }
    
    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     * @param requestId
     */
    public 
    PlayerRequest(final long requestId,Long sessionId,Long userId)
    {
        itsRequestId = requestId;
        itsSessionId = sessionId;
        itsUserId    = userId;
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
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getSessionId()
    {
        return itsSessionId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getUserId()
    {
        return itsUserId;
    }
    
    /************************************************************************
     * Generates a random identifier. 
     *
     * @return
     */
    private static long
    generateId()
    {
        long id = theirGenerator.nextLong();
        
        return id < 0 ? -id : id;
    }

}

// ##########################################################################
