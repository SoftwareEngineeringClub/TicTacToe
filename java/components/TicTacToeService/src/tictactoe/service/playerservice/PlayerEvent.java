// ##########################################################################
// # File Name:	PlayerEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public abstract
class PlayerEvent 
	implements Serializable
{

	private static final long   serialVersionUID = 7137370749935844783L;
    private static final Random theirGenerator   = new SecureRandom();

    private final long itsEventId;

    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     */
    protected
    PlayerEvent()
    {
        this( generateId() );
    }
    
    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     * @param eventId
     */
    protected 
    PlayerEvent(final long eventId)
    {
        itsEventId = eventId;
    }
    
    /************************************************************************
     * Gets the event's identifier 
     *
     * @return event identifier
     */
    public long
    getEventId()
    {
        return itsEventId;
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
