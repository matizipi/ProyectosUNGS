package com.app.punto2.parser.components;

import java.util.ArrayList;
import java.util.List;

public class Follow {

	private ProductionComponent _from;
	private List< ProductionComponent > _lstFollow;
	
	public Follow( ProductionComponent from ) {
		this._from = from;
		this._lstFollow = new ArrayList< ProductionComponent >();
	}
	
	public void addTerminal( ProductionComponent prdComp ) {
		
		if( this._lstFollow.contains( prdComp ) == false ) {
			this._lstFollow.add( prdComp );
		}
	}

	public void addTerminals(List<ProductionComponent> terminals) {
		
		for( ProductionComponent prdComp: terminals ) {
			this.addTerminal( prdComp );
		}
	}
	
	public ProductionComponent getFrom() {
		return this._from;
	}

	public List<ProductionComponent> getResults() {
		return this._lstFollow;
	}
	
	@Override
	public String toString() {
		return "Follow(" + this._from.toString() + ") = " + this._lstFollow.toString();
	}
}