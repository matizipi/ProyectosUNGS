package com.app.ui.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import com.app.helper.TpConfiguration;
import com.app.ui.controller.Controller;
import com.app.ui.view.ConfigurationView;

public class ConfigurationController extends Controller {

	private ConfigurationView _frame;
	private TpConfiguration _conf;
	
	public ConfigurationController() {
		this._frame = new ConfigurationView();
		this._conf = TpConfiguration.getInstance();
		
		this.addActionListener();
	}
	
	private void addActionListener() {
		
		this._frame.get_btnReturn().addActionListener( this );
		
		this._frame.get_cmbBCommonTablePerRows().addActionListener( this );
		this._frame.get_cmbGCommonTablePerRows().addActionListener( this );
		this._frame.get_cmbRCommonTablePerRows().addActionListener( this );

		this._frame.get_cmbBCommonTableInperRows().addActionListener( this );
		this._frame.get_cmbGCommonTableInperRows().addActionListener( this );
		this._frame.get_cmbRCommonTableInperRows().addActionListener( this );
		
		this._frame.get_cmbBCommonTableSelected().addActionListener( this );
		this._frame.get_cmbGCommonTableSelected().addActionListener( this );
		this._frame.get_cmbRCommonTableSelected().addActionListener( this );
		
		this._frame.get_cmbBLogTablePerRows().addActionListener( this );
		this._frame.get_cmbGLogTablePerRows().addActionListener( this );
		this._frame.get_cmbRLogTablePerRows().addActionListener( this );
		
		this._frame.get_cmbBLogTableInperRows().addActionListener( this );
		this._frame.get_cmbGLogTableInperRows().addActionListener( this );
		this._frame.get_cmbRLogTableInperRows().addActionListener( this );

		this._frame.get_cmbBLogTableSelected().addActionListener( this );
		this._frame.get_cmbGLogTableSelected().addActionListener( this );
		this._frame.get_cmbRLogTableSelected().addActionListener( this );
		
		this._frame.get_btnSave().addActionListener( this );
		
	}
	
	@Override
	public void start() {
		this.loadConfiguration();
		this._frame.setVisible( true );
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == this._frame.get_btnReturn() ) {
			MainController ctr = new MainController();
			this.finish();
			ctr.start();
		} else if( arg0.getSource() == this._frame.get_cmbBCommonTablePerRows() ) {
			this.sampleComTablePerRow();
		} else if( arg0.getSource() == this._frame.get_cmbGCommonTablePerRows() ) {
			this.sampleComTablePerRow();
		} else if( arg0.getSource() == this._frame.get_cmbRCommonTablePerRows() ) {
			this.sampleComTablePerRow();
	
			
		} else if( arg0.getSource() == this._frame.get_cmbBCommonTableInperRows() ) {
			this.sampleComTblImpRow();
		} else if( arg0.getSource() == this._frame.get_cmbGCommonTableInperRows() ) {
			this.sampleComTblImpRow();
		} else if( arg0.getSource() == this._frame.get_cmbRCommonTableInperRows() ) {
			this.sampleComTblImpRow();
		
		
		} else if( arg0.getSource() == this._frame.get_cmbBCommonTableSelected() ) {
			this.sampleComTblSel();
		} else if( arg0.getSource() == this._frame.get_cmbGCommonTableSelected() ) {
			this.sampleComTblSel();
		} else if( arg0.getSource() == this._frame.get_cmbRCommonTableSelected() ) {
			this.sampleComTblSel();
			
		
		} else if( arg0.getSource() == this._frame.get_cmbBLogTablePerRows() ) {
			this.sampleLogTblPerRow();
		} else if( arg0.getSource() == this._frame.get_cmbGLogTablePerRows() ) {
			this.sampleLogTblPerRow();
		} else if( arg0.getSource() == this._frame.get_cmbRLogTablePerRows() ) {
			this.sampleLogTblPerRow();
		
			
		} else if( arg0.getSource() == this._frame.get_cmbBLogTableInperRows() ) {
			this.sampleLogImRow();
		} else if( arg0.getSource() == this._frame.get_cmbGLogTableInperRows() ) {
			this.sampleLogImRow();
		} else if( arg0.getSource() == this._frame.get_cmbRLogTableInperRows() ) {
			this.sampleLogImRow();
			

		} else if( arg0.getSource() == this._frame.get_cmbBLogTableSelected() ) {
			this.sampleLogSel();
		} else if( arg0.getSource() == this._frame.get_cmbGLogTableSelected() ) {
			this.sampleLogSel();
		} else if( arg0.getSource() == this._frame.get_cmbRLogTableSelected() ) {
			this.sampleLogSel();
		
			
		} else if( arg0.getSource() == this._frame.get_btnSave() ) {
			this.save();
		}
	}
	
	private void loadConfiguration() {
		
		Color color;
		
		color = this._conf.getColorTo( TpConfiguration.COMMON_PAR_ROW );
		
		this._frame.get_cmbRCommonTablePerRows().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGCommonTablePerRows().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBCommonTablePerRows().setSelectedIndex( color.getBlue() );
		
		color = this._conf.getColorTo( TpConfiguration.COMMON_INPAR_ROW );
		
		this._frame.get_cmbRCommonTableInperRows().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGCommonTableInperRows().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBCommonTableInperRows().setSelectedIndex( color.getBlue() );
		
		color = this._conf.getColorTo( TpConfiguration.COMMON_SELECTED );
	
		this._frame.get_cmbRCommonTableSelected().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGCommonTableSelected().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBCommonTableSelected().setSelectedIndex( color.getBlue() );
		
		color = this._conf.getColorTo( TpConfiguration.LOG_PAR_ROW );
	
		this._frame.get_cmbRLogTablePerRows().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGLogTablePerRows().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBLogTablePerRows().setSelectedIndex( color.getBlue() );
		
		color = this._conf.getColorTo( TpConfiguration.LOG_INPAR_ROW );
	
		this._frame.get_cmbRLogTableInperRows().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGLogTableInperRows().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBLogTableInperRows().setSelectedIndex( color.getBlue() );
		
		color = this._conf.getColorTo( TpConfiguration.LOG_SELECTED );
		
		this._frame.get_cmbRLogTableSelected().setSelectedIndex( color.getRed() );
		this._frame.get_cmbGLogTableSelected().setSelectedIndex( color.getGreen() );
		this._frame.get_cmbBLogTableSelected().setSelectedIndex( color.getBlue() );
	}
	
	private void sampleComTablePerRow() {
		int r = Integer.parseInt( this._frame.get_cmbRCommonTablePerRows().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGCommonTablePerRows().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBCommonTablePerRows().getSelectedItem().toString() );
		
		this._frame.get_txtCommonTablePerRowsSample().setBackground( new Color( r, g, b ) );
	}
	
	private void sampleComTblImpRow() {
		int r = Integer.parseInt( this._frame.get_cmbRCommonTableInperRows().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGCommonTableInperRows().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBCommonTableInperRows().getSelectedItem().toString() );
		
		this._frame.get_txtCommonTableInperRowsSample().setBackground( new Color( r, g, b ) );
	}
	
	private void sampleComTblSel() {
		int r = Integer.parseInt( this._frame.get_cmbRCommonTableSelected().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGCommonTableSelected().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBCommonTableSelected().getSelectedItem().toString() );
		
		this._frame.get_txtCommonTableSelectedSample().setBackground( new Color( r, g, b ) );
	}
	
	private void sampleLogTblPerRow() {
		int r = Integer.parseInt( this._frame.get_cmbRLogTablePerRows().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGLogTablePerRows().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBLogTablePerRows().getSelectedItem().toString() );
		
		this._frame.get_txtLogTablePerRowsSample().setBackground( new Color( r, g, b ) );
	}
	
	private void sampleLogImRow() {
		int r = Integer.parseInt( this._frame.get_cmbRLogTableInperRows().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGLogTableInperRows().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBLogTableInperRows().getSelectedItem().toString() );
		
		this._frame.get_txtLogTableInperRowsSample().setBackground( new Color( r, g, b ) );
	}
	
	private void sampleLogSel() {
		int r = Integer.parseInt( this._frame.get_cmbRLogTableSelected().getSelectedItem().toString() );
		int g = Integer.parseInt( this._frame.get_cmbGLogTableSelected().getSelectedItem().toString() );
		int b = Integer.parseInt( this._frame.get_cmbBLogTableSelected().getSelectedItem().toString() );
		
		this._frame.get_txtLogTableSelectedSample().setBackground( new Color( r, g, b ) );
	}
	
	private void save() {
		
		int r, g, b;
		
		r = Integer.parseInt( this._frame.get_cmbRCommonTablePerRows().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGCommonTablePerRows().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBCommonTablePerRows().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.COMMON_PAR_ROW , new Color( r, g, b ) );
		
		r = Integer.parseInt( this._frame.get_cmbRCommonTableInperRows().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGCommonTableInperRows().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBCommonTableInperRows().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.COMMON_INPAR_ROW , new Color( r, g, b ) );
	
		r = Integer.parseInt( this._frame.get_cmbRCommonTableSelected().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGCommonTableSelected().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBCommonTableSelected().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.COMMON_SELECTED , new Color( r, g, b ) );
	
		r = Integer.parseInt( this._frame.get_cmbRLogTablePerRows().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGLogTablePerRows().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBLogTablePerRows().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.LOG_PAR_ROW , new Color( r, g, b ) );
	
		r = Integer.parseInt( this._frame.get_cmbRLogTableInperRows().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGLogTableInperRows().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBLogTableInperRows().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.LOG_INPAR_ROW , new Color( r, g, b ) );
	
		r = Integer.parseInt( this._frame.get_cmbRLogTableSelected().getSelectedItem().toString() );
		g = Integer.parseInt( this._frame.get_cmbGLogTableSelected().getSelectedItem().toString() );
		b = Integer.parseInt( this._frame.get_cmbBLogTableSelected().getSelectedItem().toString() );
		
		this._conf.setColorTo( TpConfiguration.LOG_SELECTED , new Color( r, g, b ) );
	}
}