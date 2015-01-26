// ##########################################################################
// # File Name:	ChallengeEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class ChallengeEvent 
    extends PlayerEvent
{

    private static final long serialVersionUID = -6170743132060664231L;
    private final Long        itsOriginatingRequestId;
    private final Long        itsChallengerUserId;
    private final Long        itsChallengedUserId;
    private Long              itsChallengeId;
    private String            itsReturnAddress;

    /************************************************************************
     * Creates a new ChallengeEvent. 
     *
     */
    public 
    ChallengeEvent(
        Long origRequestId,
        Long challengerUserId,
        Long challengedUserId)
    {
        super();
        itsOriginatingRequestId = origRequestId;
        itsChallengerUserId     = challengerUserId;
        itsChallengedUserId     = challengedUserId;
        itsChallengeId          = 0L;
        itsReturnAddress        = "";
    }

    /************************************************************************
     * Creates a new ChallengeEvent. 
     *
     * @param request
     */
    public 
    ChallengeEvent(IssueChallengeRequest request)
    {
        super();
        itsOriginatingRequestId = request.getRequestId();
        itsChallengerUserId     = request.getUserId();
        itsChallengedUserId     = request.getChallengedUserId();
        itsChallengeId          = 0L;
    }

    /************************************************************************
     *  
     *
     * @param challengeId
     */
    public void
    setChallengeId(Long challengeId)
    {
        itsChallengeId = challengeId;
    }
    
    /************************************************************************
     *  
     *
     * @param returnAddress
     */
    public void
    setReturnAddress(String returnAddress)
    {
        itsReturnAddress = returnAddress;
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
    
    /************************************************************************
     *  
     *
     * @return
     */
    public String 
    getReturnAddress()
    {
        return itsReturnAddress;
    }
}

// ##########################################################################
