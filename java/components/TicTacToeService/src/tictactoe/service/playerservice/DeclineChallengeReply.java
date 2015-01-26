// ##########################################################################
// # File Name:	DeclineChallengeReply.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class DeclineChallengeReply 
    extends PlayerReply
{

    private static final long serialVersionUID = -6170743132060664231L;
    private final Long        itsChallengerUserId;
    private final Long        itsChallengedUserId;

    /************************************************************************
     * Creates a new DeclineChallengeReply. 
     *
     */
    public 
    DeclineChallengeReply(DeclineChallengeRequest request)
    {
        super( request.getRequestId(),request.getReturnAddress() );
        
        itsChallengerUserId = request.getChallengerUserId();
        itsChallengedUserId = request.getChallengerUserId();
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
