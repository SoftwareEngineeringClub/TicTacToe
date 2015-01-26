// ##########################################################################
// # File Name:	PlayerReply.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public abstract 
class PlayerReply 
	implements Serializable
{

	private static final long serialVersionUID	= 6911201790326339932L;
    private static final Random theirGenerator   = new SecureRandom();
    
    private final long itsReplyId;
    private final long itsOriginatingRequestId;
    private String     itsReturnAddress;
    private String     itsMessage;

    /************************************************************************
     * Creates a new PlayerReply. 
     *
     * @param origRequestId
     */
    public 
    PlayerReply(final long origRequestId,String returnAddress)
    {
        this( generateId(),origRequestId,returnAddress );
    }

    /************************************************************************
     * Creates a new PlayerReply. 
     *
     * @param replyId
     * @param origRequestId
     */
    public 
    PlayerReply(
        final long replyId,
        final long origRequestId,
        final String returnAddress)
    {
        itsReplyId              = replyId;
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
    public PlayerReply
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
    public PlayerReply
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
