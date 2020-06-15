package com.app.ui.view;

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
	
	/* Components to import parser from file. */
	private JLabel _lblFile;
	private JTextField _txtFile;
	private JButton _btnFile;

	/* Return */
	private JButton _btnReturn;
	
	/* Input to accept. */
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
		
		/* Load File. */
		this._lblFile = new JLabel( "File:" );
		this._lblFile.setBounds( this.spX
				, this.spY
				, this._lblFile.getText().length() * 5
				, this.fldH );
		this._contentPanel.add( this._lblFile );
		
		this._txtFile = new JTextField();
		this._txtFile.setColumns( 10 );
		this._txtFile.setBounds( this._lblFile.getX() + this._lblFile.getWidth() + this.spX
				, this.spY
				, 250
				, this.fldH );
		this._contentPanel.add( this._txtFile );
		
		this._btnFile = new JButton( " Importar " );
		this._btnFile.setBounds( this._txtFile.getX() + this._txtFile.getWidth() + this.spX
				, this.spY
				, 90
				, this.fldH );
		this._contentPanel.add( this._btnFile );
		
		/* Return button. */
		this._btnReturn = new JButton( "Volver" );
		this._btnReturn.setBounds( 
				, y
				, width
				, height);
		
		/* Input to process. */
		this._lblInput = new JLabel( "Input: " );
		this._lblInput.setBounds( this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, this._lblInput.getText().length() * 5
				, this.fldH );
		this._contentPanel.add( this._lblInput );
		
		this._txtInput = new JTextField();
		this._txtInput.setColumns( 10 );
		this._txtInput.setBounds( this._lblInput.getX() + this._lblInput.getWidth()  + this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, 200
				, this.fldH );
		this._contentPanel.add( this._txtInput );
		
		
		this._btnInput = new JButton( "Procesar" );
		this._btnInput.setBounds( this._txtInput.getX() + this._txtInput.getWidth() + this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, 80
				, this.fldH );
		this._contentPanel.add( this._btnInput );
	}

	public JPanel get_contentPanel() {
		return _contentPanel;
	}

	public ImageIcon getImgBackground() {
		return _imgBackground;
	}

	public JLabel getLblFile() {
		return _lblFile;
	}

	public JTextField getTxtFile() {
		return _txtFile;
	}

	public JButton getBtnFile() {
		return _btnFile;
	}

	public JLabel getLblInput() {
		return _lblInput;
	}

	public JTextField getTxtInput() {
		return _txtInput;
	}

	public JButton getBtnInput() {
		return _btnInput;
	}
}