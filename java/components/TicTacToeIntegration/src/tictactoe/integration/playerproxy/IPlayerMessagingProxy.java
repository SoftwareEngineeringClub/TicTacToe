// ##########################################################################
// # File Name:	IPlayerMessagingProxy.java
// ##########################################################################

package tictactoe.integration.playerproxy;

import tictactoe.service.playerservice.IPlayerService;

import strata1.integrator.messagingproxy.IMessagingProxy;

/****************************************************************************
 * 
 */
public 
interface IPlayerMessagingProxy 
    extends IPlayerService,IMessagingProxy 
{
    public IPlayerMessagingProxy
    setUserId(Long userId);
    
    public Long
    getUserId();
}

// ##########################################################################
