package com.app.punto2.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.app.common.Msg;
import com.app.common.log.Log;
import com.app.punto2.parser.components.First;
import com.app.punto2.parser.components.Follow;
import com.app.punto2.parser.components.Production;
import com.app.punto2.parser.components.ProductionComponent;
import com.app.punto2.parser.components.Terminal;
import com.app.punto2.parser.components.Variable;

public class ParserTopDownNoRecursive extends Parser {

	private static String _parsingTableKey = "%s|%s";
	
	private Map< String, First > _mapFirst;
	private Map< String, Follow > _mapFollow;
	private Map< String, Production > _mapParsingTable;
	private List< String[] > _processTable;
	
	public ParserTopDownNoRecursive( List< Production > productions, String charEmpty ) {
		super( productions, charEmpty );
		/* Create map to firsts. */
		this._mapFirst = new HashMap< String, First >();
		/* Create map to follows. */
		this._mapFollow = new HashMap< String, Follow >();
		/* Create map to parsing table. */
		this._mapParsingTable = new HashMap< String, Production >();
		/* Initial process table */
		this._processTable = new ArrayList< String[] >();		
		
		/* Create firsts. */
//		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de firsts." ) );
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Empieza contrucción de firsts." ) );
		this.firsts();
//		this._mapFirst.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, V.toString() ) ) );
		this._mapFirst.forEach( ( K, V ) -> Log.WriteFileLog( new Msg( Msg.INFO, this, V.toString() ) ) );
		
		/* Create follows. */
//		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de follows." ) );
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Empieza contrucción de follows." ) );
		this.follows();
//		this._mapFollow.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, V.toString() ) ) );
		this._mapFollow.forEach( ( K, V ) -> Log.WriteFileLog( new Msg( Msg.INFO, this, V.toString() ) ) );
		
		/* Create table of parsing. */
//		this._lstMsgs.add( new Msg( Msg.INFO, this, "Empieza contrucción de tabla de parsing." ) );
		Log.WriteFileLog( new Msg( Msg.INFO, this, "Empieza contrucción de tabla de parsing." ) );
		this.parsingTable();
//		this._mapParsingTable.forEach( ( K, V ) -> this._lstMsgs.add( new Msg( Msg.INFO, this, K.toString() + ": " + V.toString() ) ) );
		this._mapParsingTable.forEach( ( K, V ) -> Log.WriteFileLog( new Msg( Msg.INFO, this, K.toString() + ": " + V.toString() ) ) );
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
						this.addValueToParsingTable( prd.getLeft(), firstPrdComp, prd );
//						this._mapParsingTable.put( prd.getLeft().toString() + "|" + firstPrdComp.toString() , prd);
					} else {
						Follow follow = this._mapFollow.get( prd.getLeft().toString() );
						List< ProductionComponent > followComponents = follow.getResults();
							
						for( ProductionComponent followProdComp: followComponents ) {
							if( followProdComp.isTerminal() ) {
								this.addValueToParsingTable( prd.getLeft(), followProdComp, prd );
//								this._mapParsingTable.put( prd.getLeft().toString() + "|" + followProdComp.toString(), prd );
							}
							
						}
					}
				}	
			}
		}
	}
	
	private void addValueToParsingTable( ProductionComponent var, ProductionComponent terminal, Production production ) {
		
		String key = String.format( _parsingTableKey, var.toString(), terminal.toString() );
		Production prd = this._mapParsingTable.get( key );
		
		if( prd == null ) {
			this._mapParsingTable.put( key, production );
		} else {
			this._error = true;
			this._msgError = "Tabla de parsing no soportada.";
			this._lstMsgs.add( new Msg( Msg.ERROR, this, "Para la celda M[" + var.toString() + "," + terminal.toString() + "] se esta intendo ingresar una nueva producción" ) );
		}
	}
	
	@Override
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
		
		String[] processRow = new String[3];
		Production production = new Production( new Variable("avanzar"), new ProductionComponent[0]);
		
		while( ( stckComp.equals( ProductionComponent.getFinalComponent() ) ) == false
				|| w[wIndex].equals( ProductionComponent.getFinalComponent() ) == false ) {
			
			String key = stckComp.toString() + "|" + w[ wIndex ].toString();
			production = this._mapParsingTable.get( key );
			
			if( w[ wIndex ].equals( stckComp ) ) {
				production = new Production( new Variable("avanzar"), new ProductionComponent[0]);
				processRow = new String[] { stack.toString().substring( 1, stack.toString().length() - 1 ), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() };
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
				processRow = new String[] { stack.toString().substring( 1, stack.toString().length() - 1 ), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() };
				stack.pop();
				
				ProductionComponent[] right = production.getRigth();
				for( int j = right.length - 1; j >= 0; j-- ) {
					if( right[j].equals( this._empty ) == false ) {
						stack.push( right[j] );
					}
				}
				stckComp = stack.lastElement();
				
			}
			
			this._processTable.add( processRow );
		}
		
		processRow = new String[] { stack.toString().substring( 1, stack.toString().length() - 1 ), withFinalSymbol.substring( wIndex, withFinalSymbol.length() ), production.toString() };
		this._processTable.add( processRow );
		
		return true;
	}
	
	public First[] getArrayFirsts() {
		return this._mapFirst.values().toArray( new First[0] );
	}
	
	public Follow[] getArrayFollows() {
		return this._mapFollow.values().toArray( new Follow[0] );
	}

	public String[][] getParsingTable() {
		
		String[][] prdTbl = new String[ this._lstVariable.size() + 1 ][ this._lstTerminal.size() + 1 ];
		
		prdTbl[0][0] = "";
		
		for( int i = 0; i < this._lstTerminal.size(); i++ ) {
			prdTbl[0][ i + 1 ] = this._lstTerminal.get( i ).toString();
		}
		
		int index;
		Production prd;
		String value;
		
		for( int i = 0; i < this._lstVariable.size(); i++ ) {
			index = i + 1;
			prdTbl[ index ][0] = this._lstVariable.get( i ).toString();
			
			for( int j = 0; j < this._lstTerminal.size(); j++ ) {
				prd = this._mapParsingTable.get( prdTbl[ index ][0] + "|" + prdTbl[0][ j + 1 ] );
				if( prd == null ) {
					value = "";
				} else {
					value = prd.toString();
				}
				
				prdTbl[ index ][ j + 1 ] = value;
			}
		}
		
		return prdTbl;
	}

	public String[][] getValidationProcessTable() {
		return this._processTable.toArray( new String[0][] );
	}
}