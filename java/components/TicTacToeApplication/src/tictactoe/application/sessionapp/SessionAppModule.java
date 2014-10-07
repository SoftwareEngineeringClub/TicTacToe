// ##########################################################################
// # File Name:	SessionAppModule.java
// ##########################################################################

package tictactoe.application.sessionapp;

import tictactoe.service.sessionservice.ISessionService;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
/****************************************************************************
 * 
 */
public 
class SessionAppModule 
    extends AbstractModule
{

    public 
    SessionAppModule()
    {
        super( "SessionAppModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        container
            .insertBinding( 
                bindType(ISessionProcessor.class)
                    .toType(SessionProcessor.class)
                    .withScope( new ThreadScope<ISessionProcessor>() ) )
            .insertBinding( 
                bindType(ISessionService.class)
                    .toType(SessionServiceImp.class)
                    .withScope( new ThreadScope<ISessionService>() ) );
    }

}

// ##########################################################################
