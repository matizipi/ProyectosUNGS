package com.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ParserView extends NFrame {

	private JPanel _contentPanel;
	
	private static String IMGPATH = "";
	
	private ImageIcon _imgBackground;
	
	private JLabel _lblInput;
	private JTextField _txtInput;
	private JButton _btnInput;
	
	public ParserView() {
		
		this._imgBackground = new ImageIcon( IMGPATH );
		
		this.setTitle( ".: Trabajo Practico - Parser :." );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
/*
		this.setBounds( 0
				, 0
				,  this._imgBackground.getIconWidth() + this._xOsDifference - spX
				,  this._imgBackground.getIconHeight() + this._yOsDifference - spY);
*/
		this.setBounds( 0
				,0
				,600
				,427);
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		/* Input to process. */
		this._lblInput = new JLabel( "Input: " );
		this._lblInput.setBounds( this.spX
				, this.spY
				, 50
				, this.fldH );
		this._contentPanel.add( this._lblInput );
		
		this._txtInput = new JTextField();
		this._txtInput.setColumns( 10 );
		this._txtInput.setBounds( this._lblInput.getX() + this._lblInput.getWidth()  + this.spX
				, this.spY
				, 200
				, this.fldH );
		this._contentPanel.add( this._txtInput );
		
		
		this._btnInput = new JButton( "Procesar" );
		this._btnInput.setBounds( this._txtInput.getX() + this._txtInput.getWidth() + this.spX
				, this.spY
				, 80
				, this.fldH );
		this._contentPanel.add( this._btnInput );
	}
}