// ##########################################################################
// # File Name:	StartGameRequest.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class StartGameRequest 
    extends GameRequest
{

    private static final long serialVersionUID = 8464192798124115740L;
    
    private final long itsNewGameId;

    /************************************************************************
     * Creates a new StartGameRequest. 
     *
     */
    public 
    StartGameRequest(final long sessionId,final long userId,final long newGameId)
    {
        super( sessionId,userId );
        itsNewGameId = newGameId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public StartGameRequest 
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
    public Long
    getNewGameId()
    {
        return itsNewGameId;
    }
}

// ##########################################################################
