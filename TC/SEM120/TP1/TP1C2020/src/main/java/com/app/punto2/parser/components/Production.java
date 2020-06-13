package com.app.punto2.parser.components;

public class Production {
	
	private Variable _var;
	private ProductionComponent[] _producction;

	public Production( Variable var, ProductionComponent[] right ) {
		this._var = var;
		this._producction = right;
	}

	public ProductionComponent[] getRigth() {
		return this._producction;
	}

	public Variable getLeft() {
		return this._var;
	}
	
	@Override
	public String toString() {
		
		String s = this._var.toString() + " -> ";
		
		for( int i = 0; i < this._producction.length; i++ ) {
			s += this._producction[i].toString() + " ";
		}
		
		return s;
	}
}