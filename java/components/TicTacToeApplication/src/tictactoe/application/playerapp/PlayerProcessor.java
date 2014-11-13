// ##########################################################################
// # File Name:	PlayerProcessor.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.domain.playerdomain.IPlayerRepository;
import tictactoe.domain.playerdomain.Player;
import tictactoe.domain.sessiondomain.IUserRepository;
import tictactoe.domain.sessiondomain.User;
import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.PlayerData;

import strata1.common.logger.ILogger;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;

import javax.inject.Inject;

import java.util.List;

/****************************************************************************
 * 
 */
public 
class PlayerProcessor 
    implements IPlayerProcessor
{
    private final IUserRepository    itsUserRepository;
    private final IPlayerRepository  itsPlayerRepository;
    private final ILogger            itsLogger;

    /************************************************************************
     * Creates a new PlayerProcessor. 
     *
     */
    @Inject
    public 
    PlayerProcessor(
        IUserRepository   userRepository,
        IPlayerRepository playerRepository,
        ILogger           logger)
    {
        itsUserRepository   = userRepository;
        itsPlayerRepository = playerRepository;
        itsLogger           = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public GetPlayersReply 
    getPlayers(GetPlayersRequest request) 
        throws NotUniqueException,InvalidInputException
    {
        GetPlayersReply reply = new GetPlayersReply( request );
        
        itsLogger.logInfo( 
            "Processing get players request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );
        
        if ( request.isSinglePlayer() )
            return getPlayer( reply,request );
        else
            return getPlayers( reply,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ChallengePlayerReply 
    challengePlayer(ChallengePlayerRequest request)
    {
        ChallengePlayerReply reply = new ChallengePlayerReply( request );
        
        itsLogger.logInfo( 
            "Processing challenge player request:" + request.getRequestId() + 
            " on thread: " + Thread.currentThread().getName() );

        
        return reply;
    }

    /************************************************************************
     *  
     *
     * @param reply
     * @param request
     * @return
     * @throws NotUniqueException
     * @throws InvalidInputException
     */
    private GetPlayersReply
    getPlayer(GetPlayersReply reply,GetPlayersRequest request)
        throws NotUniqueException,InvalidInputException
    {
        User   user   = itsUserRepository.getUser(request.getUserId());
        Player player = itsPlayerRepository.getPlayerFor( user );
        
        itsLogger.logDebug( "Returning single player." );
        reply.insertPlayerData( 
            new PlayerData(
                user.getUserId(),
                user.getUserName(),
                player.getStatus().name(),
                player.getWins(),
                player.getLosses(),
                player.getTies(),
                player.getCurrentRank(),
                player.getAverageRank() ) );           
        
        return reply;
    }
    
    /************************************************************************
     *  
     *
     * @param reply
     * @param request
     * @return
     * @throws NotUniqueException
     * @throws InvalidInputException
     */
    private GetPlayersReply
    getPlayers(GetPlayersReply reply,GetPlayersRequest request)
        throws NotUniqueException,InvalidInputException
    {
        for (User user : itsUserRepository.getUsers())
        {
            Player player = itsPlayerRepository.getPlayerFor( user );
            
            reply.insertPlayerData( 
                new PlayerData(
                    user.getUserId(),
                    user.getUserName(),
                    player.getStatus().name(),
                    player.getWins(),
                    player.getLosses(),
                    player.getTies(),
                    player.getCurrentRank(),
                    player.getAverageRank() ) );                           
        }            

        itsLogger.logDebug( "Returning " +  reply.getSize() +" players." );
        return reply;
    }
}

// ##########################################################################
