
interface Expr{
  void accept(ExprVisitor visitor);
}


////////////////////////////////
////  Boolean Expressions //////
////////////////////////////////

interface BoolExpr extends Expr{}

class AndExpr implements BoolExpr {
  
  AndExpr(BoolExpr e1, BoolExpr e2) {
    left = e1;
    right = e2;
  }

  public BoolExpr left;
  public BoolExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class OrExpr implements BoolExpr {
  
  OrExpr(BoolExpr e1, BoolExpr e2) {
    left = e1;
    right = e2;
  }

  public BoolExpr left;
  public BoolExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class ImpliesExpr implements BoolExpr {
  
  ImpliesExpr(BoolExpr e1, BoolExpr e2) {
    left = e1;
    right = e2;
  }

  public BoolExpr left;
  public BoolExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class NotExpr implements BoolExpr {
  
  NotExpr(BoolExpr e1) {
    left = e1;

  }

  public BoolExpr left;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class EqualBoolExpr implements BoolExpr {
  
  EqualBoolExpr(BoolExpr e1, BoolExpr e2) {
    left = e1;
    right = e2;
  }

  public BoolExpr left;
  public BoolExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}



class BooleanValue implements BoolExpr {
  BooleanValue(boolean v) {value = v;}
  public boolean value;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}


class LessThanExpr implements BoolExpr {

  LessThanExpr(ArithExpr e1, ArithExpr e2) {
    left = e1;
    right = e2;
  }

  public ArithExpr left;
  public ArithExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}

class GreaterThanExpr implements BoolExpr {

  GreaterThanExpr(ArithExpr e1, ArithExpr e2) {
    left = e1;
    right = e2;
  }

  public ArithExpr left;
  public ArithExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}

class EqualArithExpr implements BoolExpr {

  EqualArithExpr(ArithExpr e1, ArithExpr e2) {
    left = e1;
    right = e2;
  }

  public ArithExpr left;
  public ArithExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}

///////////////////////////////////
////  Arithmetic Expressions //////
///////////////////////////////////

interface ArithExpr extends Expr {};

class PlusExpr implements ArithExpr{
  PlusExpr(ArithExpr e1, ArithExpr e2) {
    left = e1;
    right = e2;
  }
  
  public ArithExpr left;
  public ArithExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}

class Number implements ArithExpr {
  Number(int v) {value = v;}
  public int value;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}


/////////////////////
////  Visitors //////
/////////////////////

interface ExprVisitor {
    void visit(AndExpr e);
    void visit(OrExpr e);
    void visit(ImpliesExpr e);
    void visit(NotExpr e);
    void visit(EqualBoolExpr e);
    void visit(BooleanValue e);
    void visit(EqualArithExpr e);
    void visit(LessThanExpr e);
    void visit(GreaterThanExpr e);
    void visit(PlusExpr e);
    void visit(Number e);
}


////////////////////////////
////  Printer Visitor //////
////////////////////////////


class ExprPrintVisitor implements ExprVisitor {
    @Override
    public void visit(AndExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" and ");
        e.right.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(OrExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" or ");
        e.right.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(ImpliesExpr e){
      System.out.print("(");
      e.left.accept(this);
      System.out.print(" implies ");
      e.right.accept(this);
      System.out.print(")");
    }

    
    @Override
    public void visit(NotExpr e){
      System.out.print("(");
      System.out.print(" not ");
      e.left.accept(this);
      System.out.print(")");
    }

    @Override
    public void visit(EqualBoolExpr e){
      System.out.print("(");
      e.left.accept(this);
      System.out.print(" == ");
      e.right.accept(this);
      System.out.print(")");
    }
    
    @Override
    public void visit(BooleanValue e) {
        System.out.print(e.value);
    }
    @Override
    public void visit(EqualArithExpr e){
      System.out.print("(");
      e.left.accept(this);
      System.out.print(" = ");
      e.right.accept(this);
      System.out.print(")");
    }
    
    @Override
    public void visit(LessThanExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" < ");
        e.right.accept(this);
        System.out.print(")");
    }
    
    @Override
    public void visit(GreaterThanExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" > ");
        e.right.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(PlusExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" + ");
        e.right.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Number e) {
        System.out.print(e.value);
    }

}

//////////////////////////////
////  Evaluator Visitor //////
//////////////////////////////

class ExprEvalVisitor implements ExprVisitor {

    public double  last_double_result;
    public boolean last_bool_result;

    @Override
    public void visit(AndExpr e) {
        e.left.accept(this);
        boolean r1 = last_bool_result;
        e.right.accept(this);
        boolean r2 = last_bool_result;

        last_bool_result = r1 && r2;
    }

    @Override
    public void visit(OrExpr e) {
        e.left.accept(this);
        boolean r1 = last_bool_result;
        e.right.accept(this);
        boolean r2 = last_bool_result;

        last_bool_result = r1 || r2;
    }
    @Override
    public void visit(ImpliesExpr e) {
        e.left.accept(this);
        boolean r1 = last_bool_result;
        e.right.accept(this);
        boolean r2 = last_bool_result;

        last_bool_result = (!r1) || r2;
    }
    @Override
    public void visit(NotExpr e) {
        e.left.accept(this);
        boolean r1 = last_bool_result;
        
        last_bool_result = (!r1);
    }    

    @Override
    public void visit(EqualBoolExpr e) {
        e.left.accept(this);
        boolean r1 = last_bool_result;
        e.right.accept(this);
        boolean r2 = last_bool_result;

        last_bool_result = r1 == r2;
    }

    @Override
    public void visit(BooleanValue e) {
        last_bool_result = e.value;
    }

    @Override
    public void visit(EqualArithExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_bool_result = (r1 == r2);
    }

    @Override
    public void visit(LessThanExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_bool_result = r1 < r2;
    }
    
    @Override
    public void visit(GreaterThanExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_bool_result = r1 > r2;
    }

    @Override
    public void visit(PlusExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_double_result = r1 + r2;
    }

    @Override
    public void visit(Number e) {
        last_double_result = e.value;
    }

}


//////////////////
////  Tests //////
//////////////////

public class ExpressionDemo {

    public static void testExpression(Expr e) {

       System.out.println("The expression is:");
       e.accept(new ExprPrintVisitor());
       System.out.println("\n");

       ExprEvalVisitor eval = new ExprEvalVisitor();
       e.accept(eval);
       System.out.println("The expression evaluates to: " + eval.last_bool_result);
    }

    public static void test1(){
      Number n = new Number(5);
      ExprPrintVisitor v = new ExprPrintVisitor();
      v.visit(n);
      // visitor.visit(this);
      n.accept(v);


    }

    public static void main(final String[] args) {
       Expr e1 = new AndExpr(
                  new LessThanExpr(
                    new PlusExpr(new Number(3), new Number(4)),
                    new Number(100)
                  ),
                  new BooleanValue(true)
                );

       Expr e2 = new AndExpr(
                  new LessThanExpr(
                    new PlusExpr(new Number(300), new Number(4)),
                    new Number(100)
                    ),
                  new BooleanValue(true)
                );

       Expr e3 = new OrExpr(
                    new LessThanExpr(
                      new PlusExpr(new Number(300), new Number(4)),
                      new Number(100)
                    ),
                    new BooleanValue(true)
                    );

       Expr e4 = new ImpliesExpr(
                  new BooleanValue(true),
                  new BooleanValue(true)
                  );


      Expr e5 = new NotExpr(
          new BooleanValue(true)
          );


      Expr e6 = new AndExpr(
        new LessThanExpr(
          new PlusExpr(new Number(3), new Number(4)),
          new Number(100)
        ),
        new NotExpr(
          new BooleanValue(true)
          )
      );

      Expr e7 = new AndExpr(
        new EqualBoolExpr(new BooleanValue(true), new BooleanValue(true)),
        new EqualArithExpr(new Number(5), new Number(5))
        );


      // testExpression(e1);
      // testExpression(e2);
      // testExpression(e3);
      // testExpression(e4);      
      testExpression(e7);

      // test1();


      /*
      - create a new variable type - maybe string
      -  x == x : new EqualArithExpr(new variable(5), new variable(5))
           - print it
      - extend quantifiers - forall, exists
          - print them
      - create a test func that only prints
      - substitution as a new kind of visitor -> maybe takes a map(variable -> expression)
          end up with a new expression 
          - define a binding
          - if it is a free variable -> first develop without thinking og quantifiers
          - p1.x == p2.x      
          
          and(variable(p1.x),variable(p2.x))
          start simple : map m.put(variable,number)


          ______
          m.put("p1.x",new Number(5))
          m.put("p2.x", new Number(7))

          this map encodes variable binding


        Inside visit:
        purpose: apply substitution
        when we get to a variable -> check if there is a key in our map that matches the variable
        
        if (key) -> get number

        visit(Number n){
          currentExpr = n;

        }
      
        visit(AndExpr e){
          e.left.accept(this);
          expr e1 = currentExpr;
          
          e.right.accept(this);
          expr e2 = currentExpr;

          currentExpr = new AndExpr(e1,e2);

        }


        ____
        visit(Variable v){
          if(m.hasKey(v.name)){
              currentExpr= m.get(v.name);
          }else{
            currentExpr = v;
          }

        }

        smt2 -> z3 tool
        https://compsys-tools.ens-lyon.fr/z3/index.php

      */
    }
}


