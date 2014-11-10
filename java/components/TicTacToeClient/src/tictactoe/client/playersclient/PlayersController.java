// ##########################################################################
// # File Name:	PlayersController.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.IMainSubController;
import tictactoe.service.playerservice.PlayerData;

import strata1.client.controller.AbstractController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

import java.util.List;

/****************************************************************************
 * 
 */
public 
class PlayersController 
    extends    AbstractController 
    implements IPlayersController
{
    private final IMainController itsMainController;
    private final IPlayersView    itsView;
    private final IPlayersModel   itsModel;
    private final ILogger         itsLogger;
    
    /************************************************************************
     * Creates a new PlayersController. 
     *
     */
    @Inject
    public 
    PlayersController(
        IMainController mainController,
        IPlayersView    view,
        IPlayersModel   model,
        ILogger         logger)
    {
        itsMainController = mainController;
        itsView           = view;
        itsModel          = model;
        itsLogger         = logger;
        
        itsMainController.setPlayersController( this );
        itsView.setProvider( this );
        itsModel.setProcessor( this );
        
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
    public IMainSubController 
    setSessionId(Long sessionId)
    {
        itsModel.setSessionId( sessionId );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    setUserId(Long userId)
    {
        itsModel.setUserId( userId );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    getMainController()
    {
        return itsMainController;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    showView()
    {
        itsModel.refreshPlayerData();
        return this;
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    setHandlers()
    {
        setHandler(
            RefreshPlayersEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doRefreshPlayerData((RefreshPlayersEvent)event);
                }

            } );
     }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    private void 
    doRefreshPlayerData(RefreshPlayersEvent event)
    {
        IPlayersModel model = event.getSender( IPlayersModel.class );
        
        itsView.removePlayers();
        
        for(PlayerData playerData : model.getPlayerData())      
            itsView.insertPlayer( playerData );
        
        itsView.show();    
    }

}

// ##########################################################################
