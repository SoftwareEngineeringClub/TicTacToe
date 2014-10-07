// ##########################################################################
// # File Name:	SessionDomainModule.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
public 
class SessionDomainModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new SessionDomainModule. 
     *
     */
    public 
    SessionDomainModule()
    {
        super( "SessionDomainModule" );
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
                bindType( ISessionRepository.class )
                    .toType( SessionRepository.class )
                    .withScope( new ThreadScope<ISessionRepository>() ) )
            .insertBinding( 
                bindType( IUserRepository.class )
                    .toType( UserRepository.class )
                    .withScope( new ThreadScope<IUserRepository>() ) );
    }

}

// ##########################################################################
