package com.app.punto2.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.app.common.Msg;
import com.app.punto2.parser.components.First;
import com.app.punto2.parser.components.Follow;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Terminal;
import com.app.punto2.parser.components.Variable;

public class ParserTopDownNoRecursive extends Parser {

	private List< Production > _lstProduction;
	private Terminal _empty;
	private List< ProductionComponent > _lstTerminal;
	private List< ProductionComponent > _lstVariable;
	private Map< String, First > _mapFirst;
	private Map< String, Follow > _mapFollow;
	private Map< String, Production > _mapParsingTable;
	
	private List< Msg > _lstMsgs = new ArrayList< Msg >();
	
	public ParserTopDownNoRecursive( List<Production> productions, String charEmpty) {
		this._lstProduction = productions;
		this._empty = new Terminal( charEmpty );
		this._lstTerminal = new ArrayList< ProductionComponent >();
		this._lstVariable = new ArrayList< ProductionComponent >();
		/* Create map to firsts. */
		this._mapFirst = new HashMap< String, First >();
		/* Create map to follows. */
		this._mapFollow = new HashMap< String, Follow >();
		/* Create map to parsing table. */
		this._mapParsingTable = new HashMap< String, Production >();
		
		/* Load terminals and variables. */
		this.loadTerminalsAndVariables();
		this._lstTerminal.forEach( O -> this._lstMsgs.add( new Msg( Msg.INFO, this, O.toString() ) ) );
		this._lstMsgs.add( new Msg( Msg.INFO, this, "----------" ) );
		this._lstVariable.forEach( O -> this._lstMsgs.add( new Msg( Msg.INFO, this, O.toString() ) ) );
		
		
		/* Create firsts. */
		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de firsts." ) );
		this.firsts();
		this._mapFirst.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, V.toString() ) ) );
		
		/* Create follows. */
		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de follows." ) );
		this.follows();
		this._mapFollow.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, V.toString() ) ) );
		
		/* Create table of parsing. */
		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de tabla de parsing." ) );
		this.parsingTable();
		this._mapParsingTable.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, K.toString() + ": " + V.toString() ) ) );
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
	
	private void firsts() {
		
		First first;
		List< ProductionComponent > terminals;
		
		/* First. */
		for( Production prd: this._lstProduction ) {
			this._lstMsgs.add( new Msg( Msg.INFO, this, "Buscando First en producción: " + prd.toString() ) );
			
			/* Terminals First. */			
			ProductionComponent[] arrayRight = prd.getRigth();
			
			/* Get all production component from right. */
			for( int i = 0; i < arrayRight.length; i++ ) {
				/* if production component is a terminal and this is not a empty. */
				if( arrayRight[i].isTerminal() == true && arrayRight[i].equals( this._empty ) == false ){
					/* New first */
					first = new First( arrayRight[i] );
					first.addTerminal( arrayRight[i] );
					/* Add first to first list. */
					this.addToFirstMap( first );
				}
			}
			
			/* No terminals first. */
			Variable left = prd.getLeft();
			
			/* Ask if the first exists in the list/map. */
			if( this.existsFirstTo( left ) == false ) {
				/* New first */
				first = new First( left );
			
				terminals = new ArrayList< ProductionComponent >();
				/* Put in list all result of first. */
				this.getFirstTo( left, terminals );
				first.addTerminals( terminals );
				
				this.addToFirstMap( first );
			}
		}
	}

	private boolean existsFirstTo( Variable var ) {
				
		First mapFirst = this._mapFirst.get( var.toString() );
		
		if( mapFirst == null ) {
			return false;
		} else {
			return true;
		}
		
	}
	
	private void getFirstTo( ProductionComponent var, List< ProductionComponent > lstFirst ) {		
		
		for( Production prdcomp: this._lstProduction ) {
			if( prdcomp.getLeft().equals( var ) ) {
				if( prdcomp.getRigth()[0].isTerminal() ) {
					lstFirst.add( prdcomp.getRigth()[0] );
				} else {
					this.getFirstTo( prdcomp.getRigth()[0], lstFirst );
				}
			}
		}
	}
	
	private void addToFirstMap( First first ) {
		
		String mapId = first.getleft().toString();
		
		First mapFirst = this._mapFirst.get( mapId );
		
		if( mapFirst == null ) {
			this._mapFirst.put( mapId, first );
		} else {
			mapFirst.addTerminals( first.getRight() );
		}
		
	}
	
	private void follows() {
		
		/* For each production look for the follows. */
		for( Production prd: this._lstProduction ) {
			this.followsOf( prd );
		}
	}
	
	private void followsOf( Production prd ) {
		
		ProductionComponent[] pc = prd.getRigth();
		
		/* For each component in right part. */
		for( int i = 0; i < pc.length; i++ ) {
			
			if( pc[i].isTerminal() == false ) {
				this.followOf( pc[i] );
			}
		}
	}
		
	private void followOf( ProductionComponent prdComp ) {
		
		Follow fllw = this._mapFollow.get( prdComp.toString() );
		
		if( fllw == null ) {
			
			int indexFollow;
			Follow newFollow = new Follow( prdComp );

			this._mapFollow.put( newFollow.getFrom().toString(), newFollow );
			
			if( prdComp.isInitSymbol() )  {
				newFollow.addTerminal( ProductionComponent.getFinalComponent() );
			}
			
			/* Recorro las producciones. */
			for( Production prd: this._lstProduction ) {
				ProductionComponent[] arrayProdComp = prd.getRigth();
				
				/* por cada componente de la parte derecha. */
				for( int i = 0; i < arrayProdComp.length; i++ ) {
					/* Si es el mismo simbolo pregunto si existe un siguiente. */
					if( arrayProdComp[i].equals( prdComp ) ) {
						indexFollow = i + 1;
						
						/* Si tiene siguiente y el siguiente es distinto del lado izquierdo. */
						if( indexFollow < arrayProdComp.length && ( arrayProdComp[ indexFollow ].equals( prd.getLeft() ) ) == false ) {
							String key = arrayProdComp[ indexFollow ].toString();
							First first = this._mapFirst.get( key );
							newFollow.addTerminals( first.getRightExcept( this._empty ) );
						} else {
							this.followOf( prd.getLeft() );
							ProductionComponent llll = prd.getLeft();
							String key = llll.toString();
							Follow kkkk = this._mapFollow.get( key );
							newFollow.addTerminals( kkkk.getResults() );
						}
					}
				}
			}
		}		
	}
	
	private void parsingTable() {
		
		for( Production prd: this._lstProduction ) {
			
			ProductionComponent[] right = prd.getRigth();
			First first;
			List< ProductionComponent > firstTerminals;
			
			if( right[0].equals( this._empty ) ) {
				first = new First( this._empty );
				first.addTerminal( this._empty );
			} else {
				first = this._mapFirst.get( right[0].toString() );
			}
			
			firstTerminals = first.getRight();

			for( ProductionComponent firstPrdComp: firstTerminals ) {
				if( firstPrdComp.isTerminal() ) {
					if( firstPrdComp.equals( this._empty ) == false ) {
						this._mapParsingTable.put( prd.getLeft().toString() + "|" + firstPrdComp.toString() , prd);
					} else {
						Follow follow = this._mapFollow.get( prd.getLeft().toString() );
						List< ProductionComponent > followComponents = follow.getResults();
							
						for( ProductionComponent followProdComp: followComponents ) {
							if( followProdComp.isTerminal() ) {
								this._mapParsingTable.put( prd.getLeft().toString() + "|" + followProdComp.toString(), prd );
							}
							
						}
					}
				}	
			}
		}
	}
	
	public boolean AcceptString( String string ) {
		
		this._lstMsgs.clear();
		
		Stack< ProductionComponent > stack = new Stack< ProductionComponent >();
		stack.push( ProductionComponent.getFinalComponent() );
		stack.push( Variable.getInitialComponent() );
		
		String withFinalSymbol = string + "$";
		ProductionComponent[] w = new ProductionComponent[ withFinalSymbol.length() ];
		
		for( int i = 0; i < withFinalSymbol.length(); i++) {
			w[i] = new Terminal( String.valueOf( withFinalSymbol.charAt(i) ) );
		}
		
		this._lstMsgs.add( new Msg( Msg.INFO, this, "w para analizar: " + withFinalSymbol ) );
		
		ProductionComponent stckComp = stack.lastElement();
		int wIndex = 0;
		
		System.out.println( "Pila\t|\tEntrada|\tProducción" );
		
		String format = "%s\t|\t%s|\t%s";
		Production production = new Production( new Variable("avanzar"), new ProductionComponent[0]);
		
		System.out.println( stckComp.toString() + " :: " + ProductionComponent.getFinalComponent().toString() );
		
		while( ( stckComp.equals( ProductionComponent.getFinalComponent() ) ) == false ) {
			
			String key = stckComp.toString() + "|" + w[ wIndex ].toString();
			production = this._mapParsingTable.get( key );
			
			if( w[ wIndex ].equals( stckComp ) ) {
				production = new Production( new Variable("avanzar"), new ProductionComponent[0]);
				System.out.println( String.format( format, stack.toString(), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() ) );
				stack.pop();
				stckComp = stack.lastElement();
				wIndex++;
			} else if( stckComp.isTerminal() ) {
				this._lstMsgs.add( new Msg( Msg.ERROR, this, "Terminal en la pila que no corresponde a la tabla de parsing: " + stckComp.toString() ) );
				return false;
			} else if( production == null ) {
				this._lstMsgs.add( new Msg( Msg.ERROR, this, "Producción no encontrada en la tabla de parsing: M[" + stack.lastElement().toString() + "," + w[ wIndex ] + "]" ) );
				return false;
			} else if( production != null ) {
				System.out.println( String.format( format, stack.toString(), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() ) );
				stack.pop();
				
				ProductionComponent[] right = production.getRigth();
				for( int j = right.length - 1; j >= 0; j-- ) {
					if( right[j].equals( this._empty ) == false ) {
						stack.push( right[j] );
					}
				}
				stckComp = stack.lastElement();
				
			}
		}
		
		System.out.println( String.format( format, stack.toString(), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() ) );
		return true;
	}
	
	public List<Msg> getMsgs() {
		return this._lstMsgs;
	}
}