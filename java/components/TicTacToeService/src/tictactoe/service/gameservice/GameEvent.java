// ##########################################################################
// # File Name:	GameReply.java
// ##########################################################################

package tictactoe.service.gameservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public abstract 
class GameEvent 
    implements Serializable
{

    private static final long   serialVersionUID = -6262157114640182835L;
    private static final Random theirGenerator   = new SecureRandom();
    
    private final long itsEventId;
    private final long itsOriginatingRequestId;
    private String     itsReturnAddress;
    private String     itsMessage;

    /************************************************************************
     * Creates a new PlayerReply. 
     *
     * @param origRequestId
     */
    public 
    GameEvent(final long origRequestId)
    {
        this( generateId(),origRequestId,null );
    }

    /************************************************************************
     * Creates a new PlayerReply. 
     *
     * @param replyId
     * @param origRequestId
     */
    public 
    GameEvent(
        final long replyId,
        final long origRequestId,
        final String returnAddress)
    {
        itsEventId              = replyId;
        itsOriginatingRequestId = origRequestId;
        itsReturnAddress        = returnAddress;
        itsMessage              = "";
    }
      
    /************************************************************************
     * Sets the reply's return address attribute to the specified address. 
     *
     * @param  returnAddress
     * @return reference to this {@code PlayerReply} for method chaining
     */
    public GameEvent
    setReturnAddress(String returnAddress)
    {
        itsReturnAddress = returnAddress;
        return this;
    }
    
    /************************************************************************
     * Sets the reply's message attribute to the specified message. 
     *
     * @param   message     message explaining the result
     * @return  reference to this {@code PlayerReply} for method chaining
     */
    public GameEvent
    setMessage(String message)
    {
        itsMessage = message;
        return this;
    }
    
    /************************************************************************
     * Gets the event's identifier. 
     *
     * @return reply identifier
     */
    public long
    getEventId()
    {
        return itsEventId;
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
     * Gets the reply's message attribute. 
     *
     * @return message explaining reply
     */
    public String
    getMessage()
    {
        return itsMessage;
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
