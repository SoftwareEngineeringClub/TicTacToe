// ##########################################################################
// # File Name:	GetPlayersRequest.java
// ##########################################################################

package tictactoe.service.playerservice;



/****************************************************************************
 * 
 */
public 
class StartListeningRequest 
	extends PlayerRequest
{

	private static final long serialVersionUID	= -1966789538643597240L;
	
	private final transient IPlayerEventListener itsListener;
	
	/************************************************************************
	 * Creates a new GetPlayersRequest. 
	 *
	 */
	public 
	StartListeningRequest(
	    final Long sessionId,
	    final Long userId,
	    final IPlayerEventListener listener)
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
    public IPlayerEventListener
    getListener()
    {
        return itsListener;
    }
}

// ##########################################################################
