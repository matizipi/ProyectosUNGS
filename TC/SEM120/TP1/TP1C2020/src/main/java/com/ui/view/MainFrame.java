package com.ui.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	
	private static String IMGPATH = "src/main/resources/MainBackground01.png";
	
	private JPanel _contentPanel;
	
	private ImageIcon _imgBackground;
	private JLabel _background;
	
	private JButton _btnAutomatonSolution;
	
	public MainFrame() {
		
//		this.setUndecorated( true );
		this.setTitle( ".: Trabajo Practico - Teoria de la computación :." );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setBounds( 100, 100, 600, 427);
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		this._btnAutomatonSolution = new JButton( "Automaton\nSolution" );
		this._btnAutomatonSolution.setBounds(5, 5, 150, 27);
		this.add( this._btnAutomatonSolution );
		
		this._imgBackground = new ImageIcon( IMGPATH );
		
		this._background = new JLabel();
		this._background.setBounds( 0, 0, this.getWidth(), this.getHeight() );
		this._background.setIcon( this._imgBackground );
		this._contentPanel.add( this._background );
	}

	public JButton getBtnAutomatonSolution() {
		return this._btnAutomatonSolution;
	}
}