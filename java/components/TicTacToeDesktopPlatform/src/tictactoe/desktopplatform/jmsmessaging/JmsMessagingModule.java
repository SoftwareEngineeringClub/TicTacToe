// ##########################################################################
// # File Name:	JmsMessagingModule.java
// ##########################################################################

package tictactoe.desktopplatform.jmsmessaging;

import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsQueueMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsTopicMessagingSession;

import org.apache.activemq.ActiveMQSslConnectionFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/****************************************************************************
 * 
 */
public 
class JmsMessagingModule 
    extends MessagingModule
{
    private ActiveMQSslConnectionFactory itsFactory;
    
    /************************************************************************
     * Creates a new JmsMessagingModule. 
     *
     */
    public 
    JmsMessagingModule()
    {
        super( "(Desktop)JmsMessagingModule" );
        itsFactory = null;
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        itsFactory = createFactory( container );
        
        if ( itsFactory == null )
            throw 
                new NullPointerException( 
                    "ActiveMq connection factory is null.");
        
        super.initialize( container );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createCommandSession()
    {
        return new JmsQueueMessagingSession(itsFactory);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createEventSession()
    {
        return new JmsTopicMessagingSession(itsFactory);
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected ActiveMQSslConnectionFactory
    createFactory(IContainer container)
    {
        String host     = null;
        String userName = null;
        String password = null;
        
        ActiveMQSslConnectionFactory factory = null;
        TrustManager[]               manager = null;
        
        host = container.getInstance(String.class,"MessagingHostUri");
        userName = container.getInstance(String.class,"MessagingUserName");
        password = container.getInstance(String.class,"MessagingPassword");
        
        factory = new ActiveMQSslConnectionFactory( host );

        manager = 
            new TrustManager[] 
            { 
                new X509TrustManager()
                {
                    public X509Certificate[] 
                    getAcceptedIssuers()
                    {
                        return null;
                    }
        
                    public void 
                    checkClientTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
        
                    public void 
                    checkServerTrusted(
                        X509Certificate[] certificates,
                        String authType) {}
                } 
            };
        
        
        factory.setKeyAndTrustManagers(
            null, 
            manager, 
            new SecureRandom()); 
        
        factory.setUserName( userName );
        factory.setPassword( password );
        
        return factory;

    }

}

// ##########################################################################
