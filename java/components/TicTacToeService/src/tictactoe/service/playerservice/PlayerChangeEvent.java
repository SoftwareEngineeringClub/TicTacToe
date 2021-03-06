// ##########################################################################
// # File Name:	PlayerChangeEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class PlayerChangeEvent 
    extends PlayerEvent
{

    private static final long serialVersionUID = -6076282165878781632L;
    private PlayerData        itsPlayerData;
    private ChangeKind        itsChange;

    /************************************************************************
     * Creates a new PlayerChangeEvent. 
     *
     */
    public 
    PlayerChangeEvent()
    {
        super();
        itsPlayerData = null;
        itsChange     = null;
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
     *  
     *
     * @param change
     * @return
     */
    public PlayerChangeEvent
    setChange(ChangeKind change)
    {
        itsChange = change;
        return this;
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
     *  
     *
     * @return
     */
    public ChangeKind
    getChange()
    {
        return itsChange;
    }
}

// ##########################################################################
