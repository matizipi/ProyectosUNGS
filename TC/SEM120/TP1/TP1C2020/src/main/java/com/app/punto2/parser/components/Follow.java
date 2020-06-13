package com.app.punto2.parser.components;

import java.util.ArrayList;
import java.util.List;

public class Follow {

	private ProductionComponent _from;
	private List< ProductionComponent > _lstFollow;
	
	public Follow( ProductionComponent pc ) {
		this._from = pc;
		this._lstFollow = new ArrayList< ProductionComponent >();
	}
	
	public void addTerminal( ProductionComponent prdComp ) {
		this._lstFollow.add( prdComp );
	}
}
