package com.app.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends NFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _contentPanel;
	
	/* Dimensions. */
	int _realframeW = 600;
	int _realframeH = 427;

	private int _frameW = this._realframeW + this._xOsDifference;
	private int _frameH = this._realframeH + this._yOsDifference;
	
	/* Background */
	private static String IMGPATH = "src/main/resources/img/MainBackground01.png";	
	private ImageIcon _imgBackground;
	private JLabel _background;
	
	/* Automaton */
	private JButton _btnAutomatonSolution;
	
	private JButton _btnParser;
	
	/* Configuration. */
	private static String CONFIGURATION_ICON_PATH = "src/main/resources/img/btn_configuration.png";
	private ImageIcon _imgiConfigurationIcon;
	private JButton _btnConfiguration;
	
	private JLabel _lblSo;
	
	public MainFrame() {
		this._imgBackground = new ImageIcon( IMGPATH );
		
		this.setTitle( ".: Trabajo Practico - Teoria de la computación :." );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setBounds( 0
				, 0
				, /*600*/ this._frameW//this._imgBackground.getIconWidth() + this._xOsDifference - spX
				, /*427*/ this._frameH );//this._imgBackground.getIconHeight() + this._yOsDifference - spY);
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		
		/* Set automaton button. */
		this._btnAutomatonSolution = new JButton( "<html>Verificador<br/>  automatas</html>" );
		this._btnAutomatonSolution.setBounds( ( this._realframeW - ( this.fldH * 3 ) ) * 3 / 4 
				, ( this._realframeH - ( this.fldH * 3 * 3 ) ) * 1 / 4
				, this.fldH * 6
				, this.fldH * 3);
		this._contentPanel.add( this._btnAutomatonSolution );
		
		
		/* Set parser button. */
		this._btnParser = new JButton( "Parser" );
		this._btnParser.setBounds( ( this._realframeW - ( this.fldH * 3 ) ) * 3 / 4 
				, ( this._realframeH - ( this.fldH * 3 * 3 ) ) * 2 / 4 + this.fldH * 3 
				, this.fldH * 6
				, this.fldH * 3);
		this._contentPanel.add( this._btnParser );
		
		

		/* Set configuration Button. */
		this._imgiConfigurationIcon = new ImageIcon( CONFIGURATION_ICON_PATH );
		this._btnConfiguration = new JButton( this._imgiConfigurationIcon );
		this._btnConfiguration.setBounds( ( this._realframeW - ( this.fldH * 3 ) ) * 3 / 4 
				, ( this._realframeH - ( this.fldH * 3 * 3 ) ) * 3 / 4 + this.fldH * 3 + this.fldH * 3
				, this.fldH * 6
				, this.fldH * 3 );
		this._contentPanel.add( this._btnConfiguration );
		
		
		/* Set operating system information. */
		this._lblSo = new JLabel();
		this._lblSo.setText( System.getProperty("os.name").toString() );
		this._lblSo.setBounds( this.getWidth() - this._xOsDifference - spX - 70
				, this.getHeight() - this._yOsDifference - spY - this.fldH
				, 70
				, this.fldH );
		this._contentPanel.add( this._lblSo );
		
		
		/* Set background. */		
		this._background = new JLabel();
		this._background.setBounds( 0
				, 0
				, this._realframeW//this.getWidth() - this._xOsDifference + spX
				, this._realframeH );//this.getHeight() - this._yOsDifference + spY );
		this._background.setIcon( this._imgBackground );
		this._contentPanel.add( this._background );
	}

	public JButton getBtnAutomatonSolution() {
		return this._btnAutomatonSolution;
	}
	
	public JButton getBtnParser() {
		return this._btnParser;
	}
}