// ##########################################################################
// # File Name:	GetPlayersReply.java
// ##########################################################################

package tictactoe.service.gameservice;


/****************************************************************************
 * 
 */
public 
class StartListeningReply 
	extends GameReply
{

	private static final long serialVersionUID	= -4779075358154470856L;
	
	private boolean itsListeningFlag;

	/************************************************************************
	 * Creates a new GetPlayersReply. 
	 *
	 */
	public 
	StartListeningReply(StartListeningRequest request)
	{
	    super( request.getRequestId(),request.getReturnAddress());
	    itsListeningFlag = false;
	}

	/************************************************************************
	 *  
	 *
	 * @param playerData
	 * @return
	 */
	public StartListeningReply
	setListening(boolean listeningFlag)
	{
	    itsListeningFlag = listeningFlag;
	    return this;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	isListening()
	{
	    return itsListeningFlag;
	}
		
}

// ##########################################################################
