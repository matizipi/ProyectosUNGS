package com.app.punto2.parser;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Terminal;

public abstract class Parser {

	protected List< Production > _lstProduction;
	protected Terminal _empty;
	protected List< ProductionComponent > _lstTerminal;
	protected List< ProductionComponent > _lstVariable;
	
	protected String _msgError = "";
	protected boolean _error = false;
	
	protected Parser( List<Production> productions, String charEmpty ) {
		this._lstProduction = productions;
		this._empty = new Terminal( charEmpty );
		this._lstTerminal = new ArrayList< ProductionComponent >();
		this._lstVariable = new ArrayList< ProductionComponent >();
		
		/* Load terminals and variables. */
		this.loadTerminalsAndVariables();
		this._lstTerminal.forEach( O -> this._lstMsgs.add( new Msg( Msg.INFO, this, O.toString() ) ) );
		this._lstMsgs.add( new Msg( Msg.INFO, this, "----------" ) );
		this._lstVariable.forEach( O -> this._lstMsgs.add( new Msg( Msg.INFO, this, O.toString() ) ) );
	}
	
	private void loadTerminalsAndVariables() {
		for( Production prd: this._lstProduction ) {
			if( prd.getLeft().isTerminal() ) {
				this.addTerminal( prd.getLeft() );
			} else {
				this.addVariable( prd.getLeft() );
			}
			
			ProductionComponent[] prdComp = prd.getRigth();
			
			for( int i = 0; i < prdComp.length; i++ ) {
				if( prdComp[i].isTerminal() ) {
					this.addTerminal( prdComp[i] );
				} else {
					this.addVariable( prdComp[i] );
				}
			}
		}
		
		this.addTerminal( ProductionComponent.getFinalComponent() );
	}
	
	private void addTerminal( ProductionComponent prdComp ) {
		if ( this._lstTerminal.contains( prdComp ) == false 
				&& prdComp.equals( this._empty ) == false )
			this._lstTerminal.add( prdComp );
	}
	
	private void addVariable( ProductionComponent prdComp ) {
		if ( this._lstVariable.contains( prdComp ) == false )
			this._lstVariable.add( prdComp );
	}
	
	protected List< Msg > _lstMsgs = new ArrayList< Msg >();
	
	abstract public boolean acceptString( String string );
	
	public List< Production > getProductions(){
		return this._lstProduction;
	}
	
	public List< Msg > getMsgs() {
		return this._lstMsgs;
	}
	
	public boolean isCorrect() {
		return this._error?false:true;
	}

	public String getErrorMssg() {
		return this._msgError;
	}
}