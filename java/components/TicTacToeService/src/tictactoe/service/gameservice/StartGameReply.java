// ##########################################################################
// # File Name:	StartGameReply.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class StartGameReply 
    extends GameReply
{

    private static final long serialVersionUID = -5744703920231491831L;

    /************************************************************************
     * Creates a new StartGameReply. 
     *
     * @param request
     */
    public 
    StartGameReply(final StartGameRequest request)
    {
        super( request.getRequestId(),request.getReturnAddress() );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public StartGameReply 
    setReturnAddress(String returnAddress)
    {
        super.setReturnAddress( returnAddress );
        return this;
    }

}

// ##########################################################################
