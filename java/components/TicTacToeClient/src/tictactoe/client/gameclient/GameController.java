// ##########################################################################
// # File Name:	GameController.java
// ##########################################################################

package tictactoe.client.gameclient;

import tictactoe.client.mainclient.IMainController;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.controller.AbstractController;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class GameController 
    extends    AbstractController 
    implements IGameController
{
    private final IMainController itsMainController;
    private final IGameView       itsView;
    private final IGameModel      itsModel;
    private final ILogger         itsLogger;

    /************************************************************************
     * Creates a new GameController. 
     *
     */
    @Inject
    public 
    GameController(
        IMainController mainController,
        IGameView       view,
        IGameModel      model,
        ILogger         logger)
    {
        itsMainController = mainController;
        itsView           = view;
        itsModel          = model;
        itsLogger         = logger;
                
        setCommands();
        setHandlers();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
        itsView.show();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startGame(Long newGameId)
    {
        itsModel.startGame( newGameId );
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    setCommands()
    {
        setCommand( 
            "???",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                }
            } );
    }

    /************************************************************************
     *  
     *
     */
    protected void
    setHandlers()
    {
        /*
        setHandler(
            NextMoveEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doNextMove((NextMoveEvent)event);
                }

            } );
            */
     }

}

// ##########################################################################
