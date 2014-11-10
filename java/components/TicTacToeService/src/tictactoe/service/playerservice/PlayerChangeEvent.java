// ##########################################################################
// # File Name:	PlayerChangeEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public 
class PlayerChangeEvent 
	implements Serializable
{

	private static final long   serialVersionUID = 7137370749935844783L;
    private static final Random theirGenerator   = new SecureRandom();

    private final long itsEventId;
    private PlayerData itsPlayerData;

    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     */
    public
    PlayerChangeEvent()
    {
        this( generateId() );
        itsPlayerData = null;
    }
    
    /************************************************************************
     * Creates a new PlayerRequest. 
     *
     * @param eventId
     */
    public 
    PlayerChangeEvent(final long eventId)
    {
        itsEventId = eventId;
    }

    /************************************************************************
     *  
     *
     * @param playerData
     * @return
     */
    public PlayerChangeEvent
    setPlayerData(PlayerData playerData)
    {
        itsPlayerData = playerData;
        return this;
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
     *  
     *
     * @return
     */
    public PlayerData
    getPlayerData()
    {
        return itsPlayerData;
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
