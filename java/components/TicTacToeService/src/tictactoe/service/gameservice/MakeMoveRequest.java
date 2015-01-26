// ##########################################################################
// # File Name:	MakeMoveRequest.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class MakeMoveRequest 
	extends GameRequest
{

	private static final long serialVersionUID	= -3081316198512751347L;
	
    private final long    itsPlayerId;
    private GameStateData itsNewState;

	/************************************************************************
	 * Creates a new MakeMoveRequest. 
	 *
	 */
	public 
	MakeMoveRequest(
	    final long sessionId,
	    final long userId,
	    final long playerId)
	{
	    super( sessionId,userId );
	    itsPlayerId = playerId;
	    itsNewState = null;
	}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public MakeMoveRequest 
    setReturnAddress(String returnAddress)
    {
        super.setReturnAddress( returnAddress );
        return this;
    }

    /************************************************************************
     *  
     *
     * @param state
     * @return
     */
    public MakeMoveRequest
    setNewState(GameStateData state)
    {
        itsNewState = state;
        return this;
    }

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public long
	getPlayerId()
	{
	    return itsPlayerId;
	}

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public GameStateData
	getNewState()
	{
	    return itsNewState;
	}
}

// ##########################################################################
