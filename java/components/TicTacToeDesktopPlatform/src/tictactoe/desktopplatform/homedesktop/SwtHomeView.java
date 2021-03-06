// ##########################################################################
// # File Name:	SwtHomeView.java
// ##########################################################################

package tictactoe.desktopplatform.homedesktop;

import tictactoe.client.homeclient.IHomeView;
import tictactoe.client.mainclient.IMainView;
import tictactoe.desktopplatform.maindesktop.SwtMainView;
import tictactoe.service.playerservice.PlayerData;

import strata1.client.shell.IDispatcher;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import javax.inject.Inject;

import java.math.BigDecimal;

/****************************************************************************
 * 
 */
public 
class SwtHomeView 
    extends    AbstractView 
    implements IHomeView,ISwtView
{
    private IDispatcher itsDispatcher;
    private CTabFolder  itsTabFolder;
    private CTabItem    itsHomeTab;
    private Composite   itsHomeComposite;
    private Label       itsPlayerNameLabel;
    private Label       itsPlayerNameValueLabel;
    private Label       itsPlayerRankLabel;
    private Label       itsPlayerRankValueLabel;
    private Label       itsGamesPlayedLabel;
    private Label       itsGamesPlayedValueLabel;
    private Label       itsWinsLabel;
    private Label       itsWinsValueLabel;
    private Label       itsLossesLabel;
    private Label       itsLossesValueLabel;
    private Label       itsTiesLabel;
    private Label       itsTiesValueLabel;

    /************************************************************************
     * Creates a new SwtHomeView. 
     *
     * @param mainView
     */
    @Inject
    public 
    SwtHomeView(IDispatcher dispatcher,IMainView mainView)
    {
        this(
            dispatcher,
            ((SwtMainView)mainView).getTabFolder(),
            ((SwtMainView)mainView).getHomeTab() );
    }
    
    /************************************************************************
     * @wbp.parser.constructor 
     * Creates a new SwtHomeView. 
     *
     * @param folder
     * @param item
     */
    public 
    SwtHomeView(IDispatcher dispatcher,CTabFolder folder,CTabItem item)
    {
        itsDispatcher = dispatcher;
        itsTabFolder = folder;
        itsHomeTab   = item;
        
        itsHomeComposite = new Composite(itsTabFolder, SWT.NONE);
        itsHomeTab.setControl(itsHomeComposite);
        itsHomeComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsHomeComposite.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        GridLayout gl_itsHomeComposite = new GridLayout(4, false);
        gl_itsHomeComposite.horizontalSpacing = 10;
        itsHomeComposite.setLayout(gl_itsHomeComposite);
        
        itsPlayerNameLabel = new Label(itsHomeComposite, SWT.NONE);
        itsPlayerNameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerNameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayerNameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsPlayerNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsPlayerNameLabel.setText("Player Name:");
        
        itsPlayerNameValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsPlayerNameValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerNameValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayerNameValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsPlayerNameValueLabel.setText("<name>");
        
        itsWinsLabel = new Label(itsHomeComposite, SWT.NONE);
        itsWinsLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsWinsLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsWinsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsWinsLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsWinsLabel.setText("Wins:");
        
        itsWinsValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsWinsValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsWinsValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsWinsValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsWinsValueLabel.setText("<wins>");
        
        itsPlayerRankLabel = new Label(itsHomeComposite, SWT.NONE);
        itsPlayerRankLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerRankLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayerRankLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsPlayerRankLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsPlayerRankLabel.setText("Player Rank:");
        
        itsPlayerRankValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsPlayerRankValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerRankValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayerRankValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsPlayerRankValueLabel.setText("<rank>");
        
        itsLossesLabel = new Label(itsHomeComposite, SWT.NONE);
        itsLossesLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsLossesLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLossesLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsLossesLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsLossesLabel.setText("Losses:");
        
        itsLossesValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsLossesValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsLossesValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsLossesValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsLossesValueLabel.setText("<losses>");
        
        itsGamesPlayedLabel = new Label(itsHomeComposite, SWT.NONE);
        itsGamesPlayedLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsGamesPlayedLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsGamesPlayedLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsGamesPlayedLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsGamesPlayedLabel.setText("Games Played:");
        
        itsGamesPlayedValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsGamesPlayedValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsGamesPlayedValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsGamesPlayedValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsGamesPlayedValueLabel.setText("<gamesPlayed>");
        
        itsTiesLabel = new Label(itsHomeComposite, SWT.NONE);
        itsTiesLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsTiesLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsTiesLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsTiesLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsTiesLabel.setText("Ties:");
        
        itsTiesValueLabel = new Label(itsHomeComposite, SWT.NONE);
        itsTiesValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsTiesValueLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsTiesValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsTiesValueLabel.setText("<ties>");
        
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    itsHomeComposite.setVisible( false );
              }
            } );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    itsHomeComposite.setVisible( true );
              }
            } );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stop()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Composite 
    getComposite()
    {
        return itsHomeComposite;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Widget 
    getWidget()
    {
        return getComposite();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IHomeView 
    setPlayerData(final PlayerData playerData)
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    itsPlayerNameValueLabel.setText( playerData.getUserName() );
                    itsPlayerRankValueLabel.setText( playerData.getCurrentRank().toPlainString() );
                    itsGamesPlayedValueLabel.setText( playerData.getGamesPlayed().toString() );
                    itsWinsValueLabel.setText(  playerData.getWins().toString() );
                    itsLossesValueLabel.setText( playerData.getLosses().toString() );
                    itsTiesValueLabel.setText( playerData.getTies().toString() );
              }
            } );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    displayError(final String message)
    {
        Runnable action = 
            new Runnable()
            {
                @Override
                public void 
                run()
                {
                    MessageBox dialog = 
                        new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                    
                      dialog.setText("Error");
                      dialog.setMessage(message);
                      dialog.open();                     
                }               
            };
            
        itsDispatcher.invokeAsynchronous( action );
        
    }

 }

// ##########################################################################
