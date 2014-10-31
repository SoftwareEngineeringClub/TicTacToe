// ##########################################################################
// # File Name:	JmsMessagingModule.java
// ##########################################################################

package tictactoe.serverplatform.jmsmessaging;

import tictactoe.integration.messaging.MessagingModule;

import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsQueueMessagingSession;
import strata1.jmsintegrator.jmsmessaging.JmsTopicMessagingSession;

import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.fusesource.stomp.jms.StompJmsConnectionFactory;

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
    private ActiveMQSslConnectionFactory itsFactory1;
    private StompJmsConnectionFactory    itsFactory2;
    
    /************************************************************************
     * Creates a new JmsMessagingModule. 
     *
     */
    public 
    JmsMessagingModule()
    {
        super( "JmsMessagingModule" );
        itsFactory1 = null;
        itsFactory2 = null;
    }
    
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        itsFactory1 = createFactory1( container );
        itsFactory2 = createFactory2( container );
        super.initialize( container );
    }


    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createCommandSession1()
    {
        return new JmsQueueMessagingSession(itsFactory1);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createEventSession1()
    {
        return new JmsTopicMessagingSession(itsFactory1);
    }


    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createCommandSession2()
    {
        return new JmsQueueMessagingSession(itsFactory2);
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected IMessagingSession 
    createEventSession2()
    {
        return new JmsTopicMessagingSession(itsFactory2);
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected ActiveMQSslConnectionFactory
    createFactory1(IContainer container)
    {
        String host = 
            container.getInstance(String.class,"MessagingHostUri1");
        String userName = 
            container.getInstance(String.class,"MessagingUserName");
        String password = 
            container.getInstance(String.class,"MessagingPassword");
            
        ActiveMQSslConnectionFactory factory = null;
        TrustManager[]               manager = null;
        
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

    /************************************************************************
     *  
     *
     * @return
     */
    protected StompJmsConnectionFactory
    createFactory2(IContainer container)
    {
        String host = 
            container.getInstance(String.class,"MessagingHostUri2");
        String userName = 
            container.getInstance(String.class,"MessagingUserName");
        String password = 
            container.getInstance(String.class,"MessagingPassword");
        
        StompJmsConnectionFactory factory = null;
        TrustManager[]            manager = null;
        
        factory = new StompJmsConnectionFactory();
        factory.setBrokerURI( host );

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
                
        try
        {
            SSLContext context;

            context = SSLContext.getInstance("SSL");
            context.init( null,manager,new SecureRandom() );
            factory.setSslContext( context );
        }
        catch(Exception e)
        {
            throw new IllegalStateException( e );
        }        
        
        factory.setUsername( userName );
        factory.setPassword( password );
        
        return factory;
        
    }
}

// ##########################################################################
