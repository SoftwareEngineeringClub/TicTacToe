// ##########################################################################
// # File Name:	AcceptChallengeRequest.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class AcceptChallengeRequest 
    extends PlayerRequest
{

    private static final long  serialVersionUID = -217263369640179985L;
    
    private final Long         itsOriginatingRequestId;
    private final Long         itsChallengeId;
    private final Long         itsChallengerUserId;
    private final Long         itsChallengedUserId;

    /************************************************************************
     * Creates a new AcceptChallengeRequest. 
     *
     */
    public 
    AcceptChallengeRequest(
        Long   sessionId,
        Long   userId,
        Long   origRequestId,
        Long   challengeId,
        Long   challengerUserId,
        Long   challengedUserId)
    {
        super(sessionId,userId);
        itsOriginatingRequestId = origRequestId;
        itsChallengeId          = challengeId;
        itsChallengerUserId     = challengerUserId;
        itsChallengedUserId     = challengedUserId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public AcceptChallengeRequest 
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
    getOriginatingRequestId()
    {
        return itsOriginatingRequestId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getChallengeId()
    {
        return itsChallengeId;
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
    
}

// ##########################################################################
