// ##########################################################################
// # File Name:	GameModel.java
// ##########################################################################

package tictactoe.client.gameclient;

import tictactoe.service.gameservice.IGameService;

import strata1.client.model.AbstractModel;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class GameModel 
    extends    AbstractModel
    implements IGameModel
{
    private final IGameService itsService;
    private final ILogger      itsLogger;
    
    /************************************************************************
     * Creates a new GameModel. 
     *
     */
    @Inject
    public 
    GameModel(
        IGameService service,
        ILogger      logger)
    {
        itsService = service;
        itsLogger  = logger;
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startGame(Long newGameId)
    {
        
    }

}

// ##########################################################################
