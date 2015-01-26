// ##########################################################################
// # File Name:	HomeController.java
// ##########################################################################

package tictactoe.client.homeclient;

import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.IMainSubController;
import tictactoe.service.playerservice.PlayerData;

import strata1.client.controller.AbstractController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HomeController 
    extends    AbstractController 
    implements IHomeController
{
    private final IMainController itsMainController;
    private final IHomeView       itsView;
    private final IHomeModel      itsModel;
    private final ILogger         itsLogger;
    
    /************************************************************************
     * Creates a new HomeController. 
     *
     */
    @Inject
    public 
    HomeController(
        IMainController mainController,
        IHomeView view,
        IHomeModel model,
        ILogger logger)
    {
        itsMainController = mainController;
        itsView           = view;
        itsModel          = model;
        itsLogger         = logger;
        
        itsMainController.setHomeController( this );
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
    public void 
    startListening()
    {
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
            RefreshPlayerEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doRefreshPlayerData((RefreshPlayerEvent)event);
                }

            } );
     }
    
    /************************************************************************
     *  
     *
     * @param event
     */
    private void 
    doRefreshPlayerData(RefreshPlayerEvent event)
    {
        IHomeModel model      = event.getSender( IHomeModel.class );
        PlayerData playerData = model.getPlayerData();
        
        itsView.setPlayerData( playerData );
        itsView.show();    
    }

}

// ##########################################################################
