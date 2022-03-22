
abstract class Expression{
}

	class AndExp extends Expression{
	
		void AndExp(Expression E1, Expression E2){
			leftExpr = E1;
			rightExpr = E2;
		}

		Expression leftExpr;
		Expression rightExpr;
	
	
	}

	class True extends Expression{

	}


	class False extends Expression{}

	class Evaluator{
		public boolean evaluate(AndExp E){
			// Expression E1 = evaluate(E.leftExpr);
			// Expression E2 = evaluate(E.rightExpr);

            return evaluate(E.leftExpr) && evaluate(E.rightExpr);
		}

        public boolean evaluate(True E){
            return true;
        }

        public boolean evaluate(False E){
            return false;
        }

        // public boolean evaluate(Expression E){
        //     return true;
        // }
	}


	class test{

		public void t1(){

			Expression E1 = new AndExp(
				new True(), 
				new True()
			);


            Evaluator eval = new Evaluator();
			boolean res = eval.evaluate(E1);
			System.out.println(res);

		}

	}


	
	// }