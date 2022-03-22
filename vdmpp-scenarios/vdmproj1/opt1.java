
class Expression{

	class AndExp{
	
		void AndExp(Expression E1, Expression E2){
			leftExpr = E1;
			rightExpr = E2;
		}

		Expression leftExpr;
		Expression rightExpr;
	
	
	}

	class True{

	}


	class False{}

	class Evaluator{
		public Expression evaluate(AndExp E){
			Expression E1 = evaluate(E.leftExpr);
			Expression E2 = evaluate(E.rightExpr);
			return (E1 == true & E2 ==true) ? true :false;
		}
	}


	class test{

		public void t1(){

			Expression E1 = new AndExp(
				new True(), 
				new True()
			);



			Expression res = eval.evaluate(E1);
			System.out.println(res);

		}

	}


	
	}