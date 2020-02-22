package br.ufsc.ine;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoMoreSolutionException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import br.ufsc.ine.utils.PrologEnvironment;

public class MainProlog {
	public static void main(String[] args) throws MalformedGoalException, NoMoreSolutionException, NoSolutionException {

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("\\+a2.\n");
			//builder.append("a1 :- a2.");
			// builder.append("bc(teste :- aux).");
			Theory contextTheory;
			/*
			 * bc(not have(beta,screw)). bc(not have(beta,screwDriver)). bc(not
			 * have(alpha,nail)). bc(have(mi,screwDriver)). bc(have(alpha,picture)).
			 * bc(have(alpha,screw)). bc(have(alpha,hammer)). bc(have(beta,nail)).
			 */
			contextTheory = new Theory(builder.toString());
			PrologEnvironment prolog = new PrologEnvironment();
			
			prolog.appendFact("a2.");
			
			SolveInfo solveGoal = prolog.solveGoal("\\+a2.");
			
			System.out.println(solveGoal.isSuccess());
			
			
			
			
			
			
			/*SolveInfo solve = prolog.solve("bc(X).");
			System.out.println(solve.hasOpenAlternatives());
			

			SolveInfo s1 = prolog.solveNext();
			solve.getBindingVars();
			System.out.println(solve);
			System.out.println(s1);
			System.out.println(s1.hasOpenAlternatives());*/


			

		} catch (InvalidTheoryException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
