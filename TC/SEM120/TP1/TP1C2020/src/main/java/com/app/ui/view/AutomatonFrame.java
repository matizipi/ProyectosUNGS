package com.app.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class AutomatonFrame extends NFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JPanel _contentPanel;
	
	protected int _realframeW = 1000;
	protected int _realframeH = 600;
	
	protected int _frameW = this._realframeW + this._xOsDifference;
	protected int _frameH = this._realframeH + this._yOsDifference;
	
	/* Background. */
	private static String BACKGROUND = "src/main/resources/img/Tp_AutomatonBackground.png";
	private ImageIcon _imgBackground;
	private JLabel _background;
	
	public AutomatonFrame() {
		this._imgBackground = new ImageIcon(BACKGROUND);
		
		this.setBounds( 0
				, 0
				, this._frameW
				, this._frameH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		
		this._background = new JLabel();
		this._background.setBounds( 0
				, 0
				, this._realframeW
				, this._realframeH );
		this._background.setIcon( this._imgBackground );
		
//		this.backgroundToBack();
	}
	
	@Override
	public void setVisible( boolean b ) {
		super.setVisible( b );
		
		this.backgroundToBack();
	}
	
	protected void backgroundToBack() {
		this._contentPanel.remove( this._background );
		this._contentPanel.add( this._background );
	}
}