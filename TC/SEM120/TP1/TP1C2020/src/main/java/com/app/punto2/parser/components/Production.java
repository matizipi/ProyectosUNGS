package com.app.punto2.parser.components;

public class Production {
	
	private Variable _var;
	private ProductionComponent[] _producction;

	public Production( Variable var, ProductionComponent[] right ) {
		this._var = var;
		this._producction = right;
	}
	
}