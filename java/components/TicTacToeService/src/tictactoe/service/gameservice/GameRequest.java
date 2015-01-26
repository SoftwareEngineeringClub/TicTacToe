// ##########################################################################
// # File Name:	GameRequest.java
// ##########################################################################

package tictactoe.service.gameservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public abstract 
class GameRequest 
    implements Serializable
{

    private static final long   serialVersionUID = 7188522778085897846L;
    private static final Random theirGenerator   = new SecureRandom();

    private final long itsRequestId;
    private String     itsReturnAddress;
    private final Long itsSessionId;
    private final Long itsUserId;

    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     */
    public
    GameRequest(Long sessionId,Long userId)
    {
        this( generateId(),sessionId,userId );
    }
    
    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     * @param requestId
     */
    public 
    GameRequest(
        final long requestId,
        Long       sessionId,
        Long       userId)
    {
        itsRequestId     = requestId;
        itsReturnAddress = null;
        itsSessionId     = sessionId;
        itsUserId        = userId;
    }

    /************************************************************************
     *  
     *
     * @param returnAddress
     * @return
     */
    public GameRequest
    setReturnAddress(String returnAddress)
    {
        itsReturnAddress = returnAddress;
        return this;
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
    public String
    getReturnAddress()
    {
        return itsReturnAddress;
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
