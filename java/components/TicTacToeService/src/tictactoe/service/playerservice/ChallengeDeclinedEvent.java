// ##########################################################################
// # File Name:	ChallengeDeclinedEvent.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class ChallengeDeclinedEvent extends PlayerEvent
{

    private static final long serialVersionUID = -6170743132060664231L;
    private final Long        itsOriginatingRequestId;
    private final Long        itsChallengerUserId;
    private final Long        itsChallengedUserId;

    /************************************************************************
     * Creates a new ChallengeDeclinedEvent. 
     *
     */
    public 
    ChallengeDeclinedEvent(
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
