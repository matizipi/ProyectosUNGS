package com.app.ui.dialog;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ExportFileDialog extends CustomDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel _contentPanel;
	
	private int _realframeW = 400;
	private int _realframeH = 350;
	
	private int _frameW = this._realframeW + this._xOsDifference;
	private int _frameH = this._realframeH + this._yOsDifference;
	
	
	private JLabel _lblFile;
	private JTextField _txtFile;
	private JCheckBox _chbFromFile;
	private JCheckBox _chbTable;
	private JCheckBox _chbSteps;
	private JProgressBar _prgb;
	private JButton _btnExport;
	
	public ExportFileDialog( JFrame parent ) {
		super( parent );
		this.setTitle( ".: Expotación de archivos :." );
		this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		this.setBounds( 0
				, 0
				, _frameW
				, _frameH );
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		
		this.setModalityType( DEFAULT_MODALITY_TYPE );

		
		/* File of inputs */
		this._lblFile = new JLabel( "Ruta archivo con inputs:" );
		this._lblFile.setBounds( this.spX
				, this.spY
				, 200
				, this.fldH);
		this._contentPanel.add( this._lblFile );
		
		
		this._txtFile = new JTextField();
		this._txtFile.setColumns( 10 );
		this._txtFile.setBounds( this.spX
				, this._lblFile.getY() + this._lblFile.getHeight() + 2
				, this._realframeW - this.spX
				, this.fldH );
		this._contentPanel.add( this._txtFile );
		
		
		/* Checkboxs */
		this._chbFromFile = new JCheckBox( "Incluir archivo desde." );
		this._chbFromFile.setBounds( this.spX
				, this._txtFile.getY() + this.fldH + this.spY
				, 200
				, this.fldH);
		this._contentPanel.add( this._chbFromFile );
		
		
		this._chbTable = new JCheckBox( "Incluir información de tablas." );
		this._chbTable.setBounds( this.spX
				, this._chbFromFile.getY() + this.fldH + this.spY
				, 200
				, this.fldH);
		this._contentPanel.add( this._chbTable );
		
		
		this._chbSteps = new JCheckBox( "Incluir pasos para resolver." );
		this._chbSteps.setBounds( this.spX
				, this._chbTable.getY() + this.fldH + this.spY
				, 200
				, this.fldH);
		this._contentPanel.add( this._chbSteps );
		
		
		/* ProgressBar */
		this._prgb = new JProgressBar(0, 100);
		this._prgb.setBounds( this.spX
				, this._chbSteps.getY() + this.fldH + this.spY
				, this._realframeW - ( 2 * this.spX )
				, this.fldH);
		this._prgb.setStringPainted( true );
		this._contentPanel.add( this._prgb );
		
		
		/* Export button. */
		this._btnExport = new JButton( "Exportar" );
		this._btnExport.setBounds( this._realframeW - 100
				, this._realframeH - this.fldH - this.spY
				, 100
				, this.fldH);
		this._contentPanel.add( this._btnExport );
	}
	
	public JLabel getLblFiles() {
		return this._lblFile;
	}
	
	public JTextField getTxtFile() {
		return this._txtFile;
	}

	/**
	 * @return the _chbFromFile
	 */
	public JCheckBox getChbFromFile() {
		return _chbFromFile;
	}

	/**
	 * @return the _chbTable
	 */
	public JCheckBox getChbTable() {
		return _chbTable;
	}

	/**
	 * @return the _chbSteps
	 */
	public JCheckBox getChbSteps() {
		return _chbSteps;
	}

	/**
	 * @return the _prgb
	 */
	public JProgressBar getPrgb() {
		return _prgb;
	}

	/**
	 * @return the _btnExport
	 */
	public JButton getBtnExport() {
		return _btnExport;
	}
	
	
	
}