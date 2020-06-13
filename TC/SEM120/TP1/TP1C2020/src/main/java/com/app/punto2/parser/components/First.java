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
		this._lstFirst.add( PrdC );
	}

	public void addTerminals( List< ProductionComponent > terminals) {
		this._lstFirst.addAll( terminals );
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

	public Object getFirstOf() {
		return this._from;
	}
}