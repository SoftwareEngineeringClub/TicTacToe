// ##########################################################################
// # File Name:	Challenge.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import strata1.common.datetime.DateTime;

/****************************************************************************
 * 
 */
public 
class Challenge
{
    private Long            itsChallengeId;
    private Long            itsOriginatingRequestId;
    private Long            itsChallengerUserId;
    private Long            itsChallengedUserId;
    private String          itsSessionKey;
    private String          itsReplyChannelId;
    private String          itsReturnAddress;
    private String          itsCorrelationId;
    private ChallengeStatus itsStatus;
    private DateTime        itsTimestamp;
    private Integer         itsVersion;
    
    /************************************************************************
     * Creates a new Challenge. 
     *
     */
    public 
    Challenge()
    {
        itsChallengeId          = 0L;
        itsOriginatingRequestId = 0L;
        itsChallengerUserId     = 0L;
        itsChallengedUserId     = 0L;
        itsSessionKey           = "";
        itsReplyChannelId       = "";
        itsReturnAddress        = "";
        itsCorrelationId        = "";
        itsStatus               = ChallengeStatus.REPLY_PENDING;
        itsTimestamp            = new DateTime();
        itsVersion              = 0;
    }

    /************************************************************************
     * Creates a new Challenge. 
     *
     * @param original
     */
    public 
    Challenge(Challenge other)
    {
        itsChallengeId          = other.itsChallengeId;
        itsOriginatingRequestId = other.itsOriginatingRequestId;
        itsChallengerUserId     = other.itsChallengerUserId;
        itsChallengedUserId     = other.itsChallengedUserId;
        itsSessionKey           = other.itsSessionKey;
        itsReplyChannelId       = other.itsReplyChannelId;
        itsReturnAddress        = other.itsReturnAddress;
        itsCorrelationId        = other.itsCorrelationId;
        itsStatus               = other.itsStatus;
        itsTimestamp            = other.itsTimestamp;
        itsVersion              = 0;
    }

    public void
    setChallengeId(Long challengeId)
    {
        itsChallengeId = challengeId;
    }
    
    public void
    setOriginatingRequestId(Long originatingRequestId)
    {
        itsOriginatingRequestId = originatingRequestId;
    }
    
    public void
    setChallengerUserId(Long challengerUserId)
    {
        itsChallengerUserId = challengerUserId;
    }
    
    public void
    setChallengedUserId(Long challengedUserId)
    {
        itsChallengedUserId = challengedUserId;
    }
    
    public void
    setSessionKey(String sessionKey)
    {
        itsSessionKey = sessionKey;
    }
    
    public void
    setReplyChannelId(String replyChannelId)
    {
        itsReplyChannelId = replyChannelId;
    }
    
    public void
    setReturnAddress(String returnAddress)
    {
        itsReturnAddress = returnAddress;
    }
    
    public void
    setCorrelationId(String correlationId)
    {
        itsCorrelationId = correlationId;
    }
    
    public void
    setStatus(ChallengeStatus status)
    {
        itsStatus = status;
    }
    
    public void
    setTimestamp(DateTime timestamp)
    {
        itsTimestamp = timestamp;
    }
    
    /************************************************************************
     *  
     *
     * @param version
     */
    public void
    setVersion(Integer version)
    {
        itsVersion = version;
    }
    
    public Long
    getChallengeId()
    {
        return itsChallengeId;
    }
    
    public Long
    getOriginatingRequestId()
    {
        return itsOriginatingRequestId;
    }
    
    public Long
    getChallengerUserId()
    {
        return itsChallengerUserId;
    }
    
    public Long
    getChallengedUserId()
    {
        return itsChallengedUserId;
    }
    
    public String
    getSessionKey()
    {
        return itsSessionKey;
    }
    
    public String
    getReplyChannelId()
    {
        return itsReplyChannelId;
    }
    
    public String
    getReturnAddress()
    {
        return itsReturnAddress;
    }
    
    public String
    getCorrelationId()
    {
        return itsCorrelationId;
    }
    
    public ChallengeStatus
    getStatus()
    {
        return itsStatus;
    }
    
    public DateTime
    getTimestamp()
    {
        return itsTimestamp;
    }
    
    public Integer
    getVersion()
    {
        return itsVersion;
    }
}

// ##########################################################################
