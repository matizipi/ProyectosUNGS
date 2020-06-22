package com.app.ui.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ConfigurationView extends NFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _contentPanel;
	
	private int _realframeW = 550;
	private int _realframeH = 280;
	
	private int _frameW = this._realframeW + this._xOsDifference;
	private int _frameH = this._realframeH + this._yOsDifference;
	
	private JButton _btnReturn;
	
	private JLabel _lblCommonTablePerRows;
	private JComboBox< Integer > _cmbRCommonTablePerRows;
	private JComboBox< Integer > _cmbGCommonTablePerRows;
	private JComboBox< Integer > _cmbBCommonTablePerRows;
	private JTextField _txtCommonTablePerRowsSample;
	
	private JLabel _lblCommonTableInperRows;
	private JComboBox< Integer > _cmbRCommonTableInperRows;
	private JComboBox< Integer > _cmbGCommonTableInperRows;
	private JComboBox< Integer > _cmbBCommonTableInperRows;
	private JTextField _txtCommonTableInperRowsSample;
	
	private JLabel _lblCommonTableSelected;
	private JComboBox< Integer > _cmbRCommonTableSelected;
	private JComboBox< Integer > _cmbGCommonTableSelected;
	private JComboBox< Integer > _cmbBCommonTableSelected;
	private JTextField _txtCommonTableSelectedSample;
	
	private JLabel _lblLogTablePerRows;
	private JComboBox< Integer > _cmbRLogTablePerRows;
	private JComboBox< Integer > _cmbGLogTablePerRows;
	private JComboBox< Integer > _cmbBLogTablePerRows;
	private JTextField _txtLogTablePerRowsSample;
	
	private JLabel _lblLogTableInperRows;
	private JComboBox< Integer > _cmbRLogTableInperRows;
	private JComboBox< Integer > _cmbGLogTableInperRows;
	private JComboBox< Integer > _cmbBLogTableInperRows;
	private JTextField _txtLogTableInperRowsSample;
	
	private JLabel _lblLogTableSelected;
	private JComboBox< Integer > _cmbRLogTableSelected;
	private JComboBox< Integer > _cmbGLogTableSelected;
	private JComboBox< Integer > _cmbBLogTableSelected;
	private JTextField _txtLogTableSelectedSample;
	
	private JButton _btnSave;
	
	public ConfigurationView() {
		
		Integer[] colorValues = new Integer[256];
		for( int i = 0; i < colorValues.length; i++ ) {
			colorValues[i] = i;
		}
		
		this.setTitle( ".: Configuraciones :." );
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
		
		
		this._btnReturn = new JButton( "Volver" );
		this._btnReturn.setBounds( this._realframeW - 100
				, this.spY
				, 100
				, this.fldH );
		this._contentPanel.add( this._btnReturn );
		
		int lblW = 200;
		int cmbW = 70;
		
		this._lblCommonTablePerRows = new JLabel( "Filas pares tablas standar:" );
		this._lblCommonTablePerRows.setBounds( this.spX
				, this._btnReturn.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( this._lblCommonTablePerRows);
		
		this._cmbRCommonTablePerRows = new JComboBox<>( colorValues );
		this._cmbRCommonTablePerRows.setBounds( this.rigthTo( this._lblCommonTablePerRows )
				, this._btnReturn.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH);
		this._contentPanel.add( this._cmbRCommonTablePerRows );
		
		this._cmbGCommonTablePerRows = new JComboBox<>( colorValues );
		this._cmbGCommonTablePerRows.setBounds( this.rigthTo( this._cmbRCommonTablePerRows )
				, this._btnReturn.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGCommonTablePerRows );
		
		this._cmbBCommonTablePerRows = new JComboBox<>( colorValues );
		this._cmbBCommonTablePerRows.setBounds( this.rigthTo( this._cmbGCommonTablePerRows )
				, this._btnReturn.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBCommonTablePerRows );
		
		this._txtCommonTablePerRowsSample = new JTextField();
		this._txtCommonTablePerRowsSample.setBounds( this.rigthTo( this._cmbBCommonTablePerRows ) 
				, this._btnReturn.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtCommonTablePerRowsSample.setEnabled( false );
		this._contentPanel.add( this._txtCommonTablePerRowsSample );
		
		
		this._lblCommonTableInperRows = new JLabel( "Filas impares tablas standar:" );
		this._lblCommonTableInperRows.setBounds( this.spX
				, this._lblCommonTablePerRows.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( this._lblCommonTableInperRows );
		
		this._cmbRCommonTableInperRows = new JComboBox<>( colorValues );
		this._cmbRCommonTableInperRows.setBounds( this.rigthTo( this._lblCommonTableInperRows )
				, this._lblCommonTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbRCommonTableInperRows );
		
		this._cmbGCommonTableInperRows = new JComboBox<>( colorValues );
		this._cmbGCommonTableInperRows.setBounds( this.rigthTo( this._cmbRCommonTableInperRows )
				, this._lblCommonTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGCommonTableInperRows );
		
		this._cmbBCommonTableInperRows = new JComboBox<>( colorValues );
		this._cmbBCommonTableInperRows.setBounds( this.rigthTo( this._cmbGCommonTableInperRows )
				, this._lblCommonTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBCommonTableInperRows );
		
		this._txtCommonTableInperRowsSample = new JTextField();
		this._txtCommonTableInperRowsSample.setBounds( this.rigthTo( this._cmbBCommonTableInperRows )
				, this._lblCommonTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtCommonTableInperRowsSample.setEnabled( false );
		this._contentPanel.add( this._txtCommonTableInperRowsSample );
		
		
		this._lblCommonTableSelected = new JLabel( "Filas seleccionadas tablas standar:" );
		this._lblCommonTableSelected.setBounds( this.spX
				, this._lblCommonTableInperRows.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( this._lblCommonTableSelected );
		
		this._cmbRCommonTableSelected = new JComboBox<>( colorValues );
		this._cmbRCommonTableSelected.setBounds( this.rigthTo( this._lblCommonTableSelected )
				, this._lblCommonTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbRCommonTableSelected );
		
		this._cmbGCommonTableSelected = new JComboBox<>( colorValues );
		this._cmbGCommonTableSelected.setBounds( this.rigthTo( this._cmbRCommonTableSelected )
				, this._lblCommonTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGCommonTableSelected );
		
		this._cmbBCommonTableSelected = new JComboBox<>( colorValues );
		this._cmbBCommonTableSelected.setBounds( this.rigthTo( this._cmbGCommonTableSelected )
				, this._lblCommonTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBCommonTableSelected );
		
		this._txtCommonTableSelectedSample = new JTextField();
		this._txtCommonTableSelectedSample.setBounds( this.rigthTo( this._cmbBCommonTableSelected )
				, this._lblCommonTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtCommonTableSelectedSample.setEnabled( false );
		this._contentPanel.add( _txtCommonTableSelectedSample );
		
		
		this._lblLogTablePerRows = new JLabel( "Filas pares tablas log:" );
		this._lblLogTablePerRows.setBounds( this.spX
				, this._lblCommonTableSelected.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( _lblLogTablePerRows );
		
		this._cmbRLogTablePerRows = new JComboBox<>( colorValues );
		this._cmbRLogTablePerRows.setBounds( this.rigthTo( this._lblLogTablePerRows )
				, this._lblCommonTableSelected.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbRLogTablePerRows );
		
		this._cmbGLogTablePerRows = new JComboBox<>( colorValues );
		this._cmbGLogTablePerRows.setBounds( this.rigthTo( this._cmbRLogTablePerRows )
				, this._lblCommonTableSelected.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGLogTablePerRows );
		
		this._cmbBLogTablePerRows = new JComboBox<>( colorValues );
		this._cmbBLogTablePerRows.setBounds( this.rigthTo( this._cmbGLogTablePerRows )
				, this._lblCommonTableSelected.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBLogTablePerRows );
		
		this._txtLogTablePerRowsSample = new JTextField();
		this._txtLogTablePerRowsSample.setBounds( this.rigthTo( this._cmbBLogTablePerRows )
				, this._lblCommonTableSelected.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtLogTablePerRowsSample.setEnabled( false );
		this._contentPanel.add( this._txtLogTablePerRowsSample );
		
		
		this._lblLogTableInperRows = new JLabel( "Filas impares tablas log:" );
		this._lblLogTableInperRows.setBounds( this.spX
				, this._lblLogTablePerRows.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( this._lblLogTableInperRows );
		
		this._cmbRLogTableInperRows = new JComboBox<>( colorValues );
		this._cmbRLogTableInperRows.setBounds( this.rigthTo( this._lblLogTableInperRows )
				, this._lblLogTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbRLogTableInperRows );
		
		this._cmbGLogTableInperRows = new JComboBox<>( colorValues );
		this._cmbGLogTableInperRows.setBounds( this.rigthTo( this._cmbRLogTableInperRows )
				, this._lblLogTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGLogTableInperRows );
		
		this._cmbBLogTableInperRows = new JComboBox<>( colorValues );
		this._cmbBLogTableInperRows.setBounds( this.rigthTo( this._cmbGLogTableInperRows )
				, this._lblLogTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBLogTableInperRows );
		
		this._txtLogTableInperRowsSample = new JTextField();
		this._txtLogTableInperRowsSample.setBounds( this.rigthTo( this._cmbBLogTableInperRows )
				, this._lblLogTablePerRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtLogTableInperRowsSample.setEnabled( false );
		this._contentPanel.add( this._txtLogTableInperRowsSample );
		
		
		this._lblLogTableSelected = new JLabel( "Filas seleccionadas tablas log:" );
		this._lblLogTableSelected.setBounds( this.spX
				, this._lblLogTableInperRows.getY() + this.fldH + this.spY
				, lblW
				, this.fldH );
		this._contentPanel.add( this._lblLogTableSelected );
		
		this._cmbRLogTableSelected = new JComboBox<>( colorValues );
		this._cmbRLogTableSelected.setBounds( this.rigthTo( this._lblLogTableSelected )
				, this._lblLogTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbRLogTableSelected );
		
		this._cmbGLogTableSelected = new JComboBox<>( colorValues );
		this._cmbGLogTableSelected.setBounds( this.rigthTo( this._cmbRLogTableSelected )
				, this._lblLogTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbGLogTableSelected );
		
		this._cmbBLogTableSelected = new JComboBox<>( colorValues );
		this._cmbBLogTableSelected.setBounds( this.rigthTo( this._cmbGLogTableSelected )
				, this._lblLogTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._contentPanel.add( this._cmbBLogTableSelected );
		
		this._txtLogTableSelectedSample = new JTextField();
		this._txtLogTableSelectedSample.setBounds( this.rigthTo( this._cmbBLogTableSelected )
				, this._lblLogTableInperRows.getY() + this.fldH + this.spY
				, cmbW
				, this.fldH );
		this._txtLogTableSelectedSample.setEnabled( false );
		this._contentPanel.add( this._txtLogTableSelectedSample );
		
		
		this._btnSave = new JButton( "Guardar" );
		this._btnSave.setBounds( this._realframeW - this.spX - 100
				, this._realframeH - this.spY - this.fldH
				, 100
				, this.fldH );
		this._contentPanel.add( this._btnSave );
	}
	
	private int rigthTo( Component component ) {
		return component.getX() + component.getWidth() + this.spX;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel get_contentPanel() {
		return _contentPanel;
	}

	public int get_realframeW() {
		return _realframeW;
	}

	public int get_realframeH() {
		return _realframeH;
	}

	public int get_frameW() {
		return _frameW;
	}

	public int get_frameH() {
		return _frameH;
	}

	public JButton get_btnReturn() {
		return _btnReturn;
	}

	public JLabel get_lblCommonTablePerRows() {
		return _lblCommonTablePerRows;
	}

	public JComboBox<Integer> get_cmbBCommonTablePerRows() {
		return _cmbRCommonTablePerRows;
	}

	public JComboBox<Integer> get_cmbGCommonTablePerRows() {
		return _cmbGCommonTablePerRows;
	}

	public JComboBox<Integer> get_cmbRCommonTablePerRows() {
		return _cmbBCommonTablePerRows;
	}

	public JTextField get_txtCommonTablePerRowsSample() {
		return _txtCommonTablePerRowsSample;
	}

	public JLabel get_lblCommonTableInperRows() {
		return _lblCommonTableInperRows;
	}

	public JComboBox<Integer> get_cmbBCommonTableInperRows() {
		return _cmbRCommonTableInperRows;
	}

	public JComboBox<Integer> get_cmbGCommonTableInperRows() {
		return _cmbGCommonTableInperRows;
	}

	public JComboBox<Integer> get_cmbRCommonTableInperRows() {
		return _cmbBCommonTableInperRows;
	}

	public JTextField get_txtCommonTableInperRowsSample() {
		return _txtCommonTableInperRowsSample;
	}

	public JLabel get_lblCommonTableSelected() {
		return _lblCommonTableSelected;
	}

	public JComboBox<Integer> get_cmbBCommonTableSelected() {
		return _cmbRCommonTableSelected;
	}

	public JComboBox<Integer> get_cmbGCommonTableSelected() {
		return _cmbGCommonTableSelected;
	}

	public JComboBox<Integer> get_cmbRCommonTableSelected() {
		return _cmbBCommonTableSelected;
	}

	public JTextField get_txtCommonTableSelectedSample() {
		return _txtCommonTableSelectedSample;
	}

	public JLabel get_lblLogTablePerRows() {
		return _lblLogTablePerRows;
	}

	public JComboBox<Integer> get_cmbBLogTablePerRows() {
		return _cmbRLogTablePerRows;
	}

	public JComboBox<Integer> get_cmbGLogTablePerRows() {
		return _cmbGLogTablePerRows;
	}

	public JComboBox<Integer> get_cmbRLogTablePerRows() {
		return _cmbBLogTablePerRows;
	}

	public JTextField get_txtLogTablePerRowsSample() {
		return _txtLogTablePerRowsSample;
	}

	public JLabel get_lblLogTableInperRows() {
		return _lblLogTableInperRows;
	}

	public JComboBox<Integer> get_cmbBLogTableInperRows() {
		return _cmbRLogTableInperRows;
	}

	public JComboBox<Integer> get_cmbGLogTableInperRows() {
		return _cmbGLogTableInperRows;
	}

	public JComboBox<Integer> get_cmbRLogTableInperRows() {
		return _cmbBLogTableInperRows;
	}

	public JTextField get_txtLogTableInperRowsSample() {
		return _txtLogTableInperRowsSample;
	}

	public JLabel get_lblLogTableSelected() {
		return _lblLogTableSelected;
	}

	public JComboBox<Integer> get_cmbBLogTableSelected() {
		return _cmbRLogTableSelected;
	}

	public JComboBox<Integer> get_cmbGLogTableSelected() {
		return _cmbGLogTableSelected;
	}

	public JComboBox<Integer> get_cmbRLogTableSelected() {
		return _cmbBLogTableSelected;
	}

	public JTextField get_txtLogTableSelectedSample() {
		return _txtLogTableSelectedSample;
	}

	public JButton get_btnSave() {
		return _btnSave;
	}
}