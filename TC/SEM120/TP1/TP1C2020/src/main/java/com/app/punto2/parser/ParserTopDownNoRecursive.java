package com.app.punto2.parser;

import java.util.List;

import com.app.punto2.parser.components.Production;

public class ParserTopDownNoRecursive extends Parser {

	private List< Production > _lstProduction;
	
	public ParserTopDownNoRecursive( List<Production> productions ) {
		this._lstProduction = productions;
	}
}