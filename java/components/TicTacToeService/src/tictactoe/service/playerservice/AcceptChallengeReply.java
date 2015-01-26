// ##########################################################################
// # File Name:	AcceptChallengeReply.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class AcceptChallengeReply 
    extends PlayerReply
{

    private static final long serialVersionUID = -6170743132060664231L;
    private final Long        itsChallengerUserId;
    private final Long        itsChallengedUserId;
    private Long              itsGameId;

    /************************************************************************
     * Creates a new AcceptChallengeReply. 
     *
     */
    public 
    AcceptChallengeReply(AcceptChallengeRequest request)
    {
        super( request.getRequestId(),request.getReturnAddress() );
        itsChallengerUserId = request.getChallengerUserId();
        itsChallengedUserId = request.getChallengedUserId();
        itsGameId = 0L;
    }
    
    /************************************************************************
     *  
     *
     * @param gameId
     */
    public void 
    setGameId(Long gameId)
    {
        itsGameId = gameId;
    }

    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getChallengerUserId()
    {
        return itsChallengerUserId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getChallengedUserId()
    {
        return itsChallengedUserId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getGameId()
    {
        return itsGameId;
    }
}

// ##########################################################################
