// ##########################################################################
// # File Name:	MakeMoveReply.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class MakeMoveReply 
	extends GameReply
{

	private static final long serialVersionUID	= 4580970159784013468L;

	private boolean           itsMoveAcceptedFlag;
	
	/************************************************************************
	 * Creates a new MakeMoveReply. 
	 *
	 */
	public 
	MakeMoveReply(final MakeMoveRequest request)
	{
	    super( request.getRequestId(),request.getReturnAddress() );
	    itsMoveAcceptedFlag = false;
	}
	
    /************************************************************************
     *  
     *
     * @param state
     * @return
     */
    public MakeMoveReply
    setMoveAccepted(boolean accepted)
    {
        itsMoveAcceptedFlag = accepted;
        return this;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public boolean
    isMoveAccepted()
    {
        return itsMoveAcceptedFlag;
    }

}

// ##########################################################################
