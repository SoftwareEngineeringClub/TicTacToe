// ##########################################################################
// # File Name:	GetPlayersRequest.java
// ##########################################################################

package tictactoe.service.gameservice;



/****************************************************************************
 * 
 */
public 
class StartListeningRequest 
	extends GameRequest
{

	private static final long serialVersionUID	= -1966789538643597240L;
	
	private final transient IGameEventListener itsListener;
	
	/************************************************************************
	 * Creates a new GetPlayersRequest. 
	 *
	 */
	public 
	StartListeningRequest(
	    final Long               sessionId,
	    final Long               userId,
	    final IGameEventListener listener)
	{
	    super( sessionId,userId );
	    itsListener = listener;
	}

	/************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public StartListeningRequest 
    setReturnAddress(String returnAddress)
    {
        super.setReturnAddress( returnAddress );
        return this;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public IGameEventListener
    getListener()
    {
        return itsListener;
    }
}

// ##########################################################################
