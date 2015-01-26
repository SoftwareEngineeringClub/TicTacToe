// ##########################################################################
// # File Name:	GameUpdatedEvent.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class GameUpdatedEvent 
	extends GameEvent
{

	private static final long serialVersionUID	= 4580970159784013468L;
	
	private GameStateData itsState;
	
	/************************************************************************
	 * Creates a new GameUpdatedEvent. 
	 *
	 */
	public 
	GameUpdatedEvent(final MakeMoveRequest request)
	{
	    super( request.getRequestId() );
	    itsState = new GameStateData();
	}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public GameUpdatedEvent 
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
    public GameUpdatedEvent
    setState(GameStateData state)
    {
        itsState = state;
        return this;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public GameStateData
    getState()
    {
        return itsState;
    }
}

// ##########################################################################
