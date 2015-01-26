// ##########################################################################
// # File Name:	BlockingPlayerReplyReceiver.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.PlayerReply;
import tictactoe.service.playerservice.PlayerRequest;
import tictactoe.service.playerservice.StartListeningReply;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

/****************************************************************************
 * 
 */
public 
class BlockingPlayerReplyReceiver 
    implements IPlayerReplyReceiver
{
    private final PlayerRequest itsRequest;
    private final long          itsTimeoutMilliseconds;
    private Object              itsReply;
    
    /************************************************************************
     * Creates a new BlockingPlayerReplyReceiver. 
     *
     */
    public 
    BlockingPlayerReplyReceiver(PlayerRequest request,long timeoutMs)
    {
        itsRequest             = request;
        itsTimeoutMilliseconds = timeoutMs;
        itsReply               = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onGetPlayers(GetPlayersReply reply)
    {
        itsReply = reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onIssueChallenge(IssueChallengeReply reply)
    {
        itsReply = reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onAcceptChallenge(AcceptChallengeReply reply)
    {
        itsReply = reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onDeclineChallenge(DeclineChallengeReply reply)
    {
        itsReply = reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onStartListening(StartListeningReply reply)
    {
        itsReply = reply;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onPlayerException(PlayerException exception)
    {
        itsReply = exception;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public synchronized void 
    onThrowable(Throwable throwable)
    {
        itsReply = throwable;
    }

    /************************************************************************
     *  
     *
     * @return
     */
    public PlayerReply
    getReply()
        throws PlayerException, TimeoutException
    {
        long remaining = itsTimeoutMilliseconds;
        
        while ( !isReceived() )
        {
            LockSupport.parkNanos( 10*1000000 );
            remaining -= 10;
            
            if ( remaining <= 0 )
                throw new TimeoutException();
        }
        
        if ( itsReply instanceof PlayerReply )
            return (PlayerReply)itsReply;
        
        if ( itsReply instanceof PlayerException)
            throw (PlayerException)itsReply;
        
        throw new PlayerException( itsRequest,(Throwable)itsReply );
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    private synchronized boolean
    isReceived()
    {
        return itsReply != null;
    }
}

// ##########################################################################
