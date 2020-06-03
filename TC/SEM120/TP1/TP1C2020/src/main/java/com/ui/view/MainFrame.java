package com.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends NFrame {
	
	private static String IMGPATH = "src/main/resources/MainBackground01.png";
	
	private JPanel _contentPanel;
	
	private ImageIcon _imgBackground;
	private JLabel _background;
	
	private JButton _btnAutomatonSolution;
	
	private JLabel _lblSo;
	
	public MainFrame() {
//		this.setUndecorated( true );
		this.setTitle( ".: Trabajo Practico - Teoria de la computación :." );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setBounds( 0, 0, 600 + this._xOsDifference - spX, 427 + this._yOsDifference - spY);
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		this._btnAutomatonSolution = new JButton( "Automaton\nSolution" );
		this._btnAutomatonSolution.setBounds(5, 5, 150, 27);
		this.add( this._btnAutomatonSolution );
		
		this._imgBackground = new ImageIcon( IMGPATH );
		
		System.out.println( this.getHeight() - this.fldH - spY );
		
		this._lblSo = new JLabel();
		this._lblSo.setText( System.getProperty("os.name").toString() );
		this._lblSo.setBounds( this.getWidth() - this._xOsDifference - spX - 70
				, this.getHeight() - this._yOsDifference - spY - this.fldH
				, 70
				, this.fldH );
		this._contentPanel.add( this._lblSo );
		
		this._background = new JLabel();
		this._background.setBounds( 0, 0, this.getWidth() - this._xOsDifference + spX, this.getHeight() - this._yOsDifference + spY );
		this._background.setIcon( this._imgBackground );
		this._contentPanel.add( this._background );
	}

	public JButton getBtnAutomatonSolution() {
		return this._btnAutomatonSolution;
	}
}