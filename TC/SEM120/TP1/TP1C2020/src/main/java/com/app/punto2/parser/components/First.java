package com.app.punto2.parser.components;

import java.util.ArrayList;
import java.util.List;

public class First {

	private ProductionComponent _from;
	private List< ProductionComponent > _lstFirst;
	
	public First( ProductionComponent pc ) {
		this._from = pc;
		this._lstFirst = new ArrayList< ProductionComponent >();
	}
	
	public void addTerminal( ProductionComponent PrdC ) {
		if( this._lstFirst.contains( PrdC ) == false ) {
			this._lstFirst.add( PrdC );
		}
	}

	public void addTerminals( List< ProductionComponent > terminals) {
		
		for( ProductionComponent terminal: terminals ) {
			this.addTerminal( terminal );
		}
	}
	
	@Override
	public String toString() {
		
		String s = "First(" + this._from.toString() + ") = {";
		
		for( ProductionComponent prdComp: this._lstFirst ) {
			s += prdComp.toString() + ",";
		}
		
		s = s.substring( 0, s.length() - 1) + "}";
		
		return s;
	}

	public Object getleft() {
		return this._from;
	}

	public List< ProductionComponent > getRight() {
		return this._lstFirst;
	}

	public List< ProductionComponent > getRightExcept( Terminal terminal ) {
		
		List< ProductionComponent > lstProduction = new ArrayList< ProductionComponent >();
		
		for( ProductionComponent prdComp: this._lstFirst ) {
			if( prdComp.equals( terminal ) == false ) {
				lstProduction.add( prdComp );
			}
		}
		
		return lstProduction;
	}
}