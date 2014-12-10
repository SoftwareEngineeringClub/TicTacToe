// ##########################################################################
// # File Name:	ChallengeAcceptedEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class ChallengeAcceptedEvent 
    extends PlayerEvent
{

    private static final long serialVersionUID = -6170743132060664231L;
    private final Long        itsOriginatingRequestId;
    private final Long        itsChallengerUserId;
    private final Long        itsChallengedUserId;

    /************************************************************************
     * Creates a new ChallengeAcceptedEvent. 
     *
     */
    public 
    ChallengeAcceptedEvent(
        Long origRequestId,
        Long challengerUserId,
        Long challengedUserId)
    {
        super();
        itsOriginatingRequestId = origRequestId;
        itsChallengerUserId     = challengerUserId;
        itsChallengedUserId     = challengedUserId;
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
