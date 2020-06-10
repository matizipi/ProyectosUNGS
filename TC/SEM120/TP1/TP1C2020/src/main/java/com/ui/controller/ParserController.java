package com.ui.controller;

import java.awt.event.ActionEvent;

import com.ui.view.ParserView;

public class ParserController implements ControllerImpl {
	
	private ParserView _frame;
	
	public ParserController() {
		this._frame = new ParserView();
		
		this.addListeners();
	}
	
	private void addListeners() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	@Override
	public void start() {
		this._frame.setVisible( true );
	}

	@Override
	public void finish() {
		this._frame.setVisible( false );
	}
}