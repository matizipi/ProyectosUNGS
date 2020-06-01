package automaton.dfn.components;

import java.util.ArrayList;
import java.util.List;

import automaton.components.StateA;

public class StateDFAFromNFA extends StateA {
	
	private List<StateA> _lstStates;

	public StateDFAFromNFA(String name) {
		super(name);
		this._lstStates = new ArrayList<StateA>();
	}
	
	public void addState( StateA state ) {
		this.name += state.getName();
		
		if ( state.isFinalState() ) {
			this.finalState = true;
		}
		this._lstStates.add(state);
	}
	
	@Override
	public boolean equals( StateA sa ) {
		
		boolean exist = false;
		String[] sIds = sa.getName().split( "Q_" );
		
		if ( sIds.length > 0 ) {
			for ( StateA _sa: this._lstStates ) {
				exist = false;
				for ( int i = 0; i < sIds.length; i++ ) {
					if ( _sa.equals(sIds[i]) ) {
						exist = true;
						break;
					}
				}
				if ( !exist )
					return exist;
			}
		}
		
		return exist;
	}

	public List<StateA> getStatesA() {
		return this._lstStates;
	}

	public StateA getStateDFA() {
		
		for ( StateA sa: this._lstStates ) {
			this.name += sa.getName();
		}
		
		return null;
	}
}