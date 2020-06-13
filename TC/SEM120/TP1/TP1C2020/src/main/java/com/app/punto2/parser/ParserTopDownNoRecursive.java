package com.app.punto2.parser;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto2.parser.components.First;
import com.app.punto2.parser.components.Follow;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Terminal;
import com.app.punto2.parser.components.Variable;

public class ParserTopDownNoRecursive extends Parser {

	private List< Production > _lstProduction;
	private Terminal _empty;
	private List< First > _lstFirst;
	private List< Follow > _lstFollow;
	
	public ParserTopDownNoRecursive( List<Production> productions, String charEmpty) {
		this._lstProduction = productions;
		
		this._empty = new Terminal( charEmpty );
		
		/* Create firsts. */
		this.firsts();
		
		/* Create follows. */
		this.follows();
	}
	
	private void firsts() {
		
		First first;
		List< ProductionComponent > terminals;
		
		/* Create list of first to return. */
		this._lstFirst = new ArrayList< First >();		
		
		/* First. */
		for( Production prd: this._lstProduction ) {
			Log.WriteFileLog( new Msg( Msg.INFO, this, prd.toString() ) );
			/* Terminals First. */			
			ProductionComponent[] arrayPrdC = prd.getRigth();
			
			/* Get all production component from right. */
			for( int i = 0; i < arrayPrdC.length; i++ ) {
				/* if production component is a terminal and this is not a empty. */
				if( arrayPrdC[i].isTerminal() && !arrayPrdC[i].equals( this._empty ) ){
					/* New first */
					first = new First( arrayPrdC[i] );
					first.addTerminal( arrayPrdC[i] );
					/* Add first to first list. */
					this._lstFirst.add( first );
				}
			}
			
			/* No terminal first. */
			Variable var = prd.getLeft();
			
			if( this.existsFirstTo( var ) == false ) {
				/* New first */
				first = new First( var );
			
				terminals = new ArrayList< ProductionComponent >();
				/* Put in list all result of first. */
				this.getFirstTo( var, terminals );
				first.addTerminals( terminals );
				
				this._lstFirst.add( first );
			}
		}
	}

	private boolean existsFirstTo( Variable var ) {
		
		for( First first: this._lstFirst ) {
			if( var.equals( first.getFirstOf() ) == true )  {
				return true;
			}
		}
		
		return false;
	}
	
	private void getFirstTo( ProductionComponent var, List< ProductionComponent > lstFirstProduction ) {		
		
		for( Production prdcomp: this._lstProduction ) {
			if( prdcomp.getLeft().equals( var ) ) {
				if( prdcomp.getRigth()[0].isTerminal() ) {
					lstFirstProduction.add( prdcomp.getRigth()[0] );
				} else {
					this.getFirstTo( prdcomp.getRigth()[0], lstFirstProduction );
				}
			}
		}
	}
	
	private void follows() {
		
		Follow follow;
		List< ProductionComponent > terminales;
		
		/* Buscar X_{1} para empezar y agregarle $. */
		
		for( Production prd: this._lstProduction ) {
			if( prd.getLeft() ) {
				
			}
		}
		
		/* recorro las producciones. */
	}
}