import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


class LessThanExpr implements BoolExpr, ArithExpr {

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
class SubtractionExpr implements ArithExpr{
  SubtractionExpr(ArithExpr e1, ArithExpr e2) {
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
class MultiplicationExpr implements ArithExpr{
  MultiplicationExpr(ArithExpr e1, ArithExpr e2) {
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
class DivisionExpr implements ArithExpr{
  DivisionExpr(ArithExpr e1, ArithExpr e2) {
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


///////////////////////////////////
////  Variable Expressions   //////
///////////////////////////////////
interface VariableExpr extends Expr {};


class EqualVariableExpr implements VariableExpr{
  EqualVariableExpr(VariableExpr e1, VariableExpr e2) {
    left = e1;
    right = e2;
  }
  
  public VariableExpr left;
  public VariableExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}

class Variable implements VariableExpr, ArithExpr, BoolExpr {
  Variable(String v) {name = v;}
  public String name;

  @Override
  public void accept(ExprVisitor visitor){
    visitor.visit(this);
  }

}
///////////////////////////////////
////       Substitution      //////
///////////////////////////////////

class SubstVisitor implements ExprVisitor {
  // Map<Variable, Expr> bindings = new HashMap<Variable, Expr>();
  // public void addBinding(Variable v, Expr e) {
  Map<String, Expr> bindings = new HashMap<String, Expr>();
  public void addBinding(String v, Expr e) {
    bindings.put(v,e);
  }
  public Expr last_subst_expr;

  @Override
  public void visit(AndExpr e) {
      e.left.accept(this);
      BoolExpr r1 = (BoolExpr) last_subst_expr;
      e.right.accept(this);
      BoolExpr r2 = (BoolExpr) last_subst_expr;
      last_subst_expr = new AndExpr(r1, r2);
  }
  @Override
  public void visit(OrExpr e){
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_subst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_subst_expr;
    last_subst_expr = new OrExpr(r1, r2);
  }
  @Override
  public void visit(ImpliesExpr e){
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_subst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_subst_expr;
    last_subst_expr = new ImpliesExpr(r1, r2);
  }
  @Override
  public void visit(NotExpr e){
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_subst_expr;
    last_subst_expr = new NotExpr(r1);
  }
  
  @Override
  public void visit(EqualBoolExpr e){
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_subst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_subst_expr;
    last_subst_expr = new EqualBoolExpr(r1, r2);
  }

  /////////


  @Override
  public void visit(LessThanExpr e) {
      e.left.accept(this);
      ArithExpr r1 = (ArithExpr) last_subst_expr;
      e.right.accept(this);
      ArithExpr r2 = (ArithExpr) last_subst_expr;
      last_subst_expr = new LessThanExpr(r1, r2);
  }

  @Override
  public void visit(PlusExpr e) {
      e.left.accept(this);
      ArithExpr r1 = (ArithExpr) last_subst_expr;
      e.right.accept(this);
      ArithExpr r2 = (ArithExpr) last_subst_expr;
      last_subst_expr = new PlusExpr(r1, r2);
  }

  @Override
  public void visit(SubtractionExpr e) {
      e.left.accept(this);
      ArithExpr r1 = (ArithExpr) last_subst_expr;
      e.right.accept(this);
      ArithExpr r2 = (ArithExpr) last_subst_expr;
      last_subst_expr = new SubtractionExpr(r1, r2);
  }
  @Override
  public void visit(MultiplicationExpr e) {
      e.left.accept(this);
      ArithExpr r1 = (ArithExpr) last_subst_expr;
      e.right.accept(this);
      ArithExpr r2 = (ArithExpr) last_subst_expr;
      last_subst_expr = new MultiplicationExpr(r1, r2);
  }
  @Override
  public void visit(DivisionExpr e) {
      e.left.accept(this);
      ArithExpr r1 = (ArithExpr) last_subst_expr;
      e.right.accept(this);
      ArithExpr r2 = (ArithExpr) last_subst_expr;
      last_subst_expr = new DivisionExpr(r1, r2);
  }


  
  @Override
  public void visit(EqualArithExpr e){
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_subst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_subst_expr;
    last_subst_expr = new EqualArithExpr(r1, r2);
  }
  @Override
  public void visit(GreaterThanExpr e){
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_subst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_subst_expr;
    last_subst_expr = new GreaterThanExpr(r1, r2);
  }
  
  /////////

  @Override
  public void visit(BooleanValue e) {
      last_subst_expr = new BooleanValue(e.value);
  }

  @Override
  public void visit(Number e) {
      last_subst_expr = new Number(e.value);
  }

  @Override
  public void visit(Variable v) {
    if(bindings.containsKey(v.name)) {
        last_subst_expr = bindings.get(v.name);
      } else {
        last_subst_expr = new Variable(v.name);
      }
  }

  @Override
  public void visit(EqualVariableExpr e){
    e.left.accept(this);
    VariableExpr r1 = (VariableExpr) last_subst_expr;
    e.right.accept(this);
    VariableExpr r2 = (VariableExpr) last_subst_expr;
    last_subst_expr = new EqualVariableExpr(r1, r2);
  }

  @Override
  public void visit(PredicateDefinition e) {
    last_subst_expr = new PredicateDefinition(e.name, e.arity, e.variable_names, e.expr_def, e.vMap);
    
  }
}
///////////////////////////////////
////   Predicate definition  //////
///////////////////////////////////

interface PredicateExpr extends Expr{};

class PredicateDefinition implements PredicateExpr{
  String name;
  int arity;
  List<String> variable_names;
  Map<String, Expr> vMap = new HashMap<String, Expr>();
  Expr expr_def;
  
  

  PredicateDefinition(String n, int a, List<String> v, Expr d, Map<String, Expr> sv){
    name = n;
    arity=a;
    variable_names = v;
    expr_def = d;

    for(int i =0; i<arity; i++){
      vMap.put(variable_names.get(i), sv.get(variable_names.get(i)));
    }

  }

  @Override
  public void accept(ExprVisitor visitor){
    visitor.visit(this);
  }
}


class PredicateSubstVisitor implements ExprVisitor{
  List<PredicateDefinition> defs = new ArrayList<>();
  public void addPredicate(PredicateDefinition pred) {
    defs.add(pred);
  }
  public Expr last_predSubst_expr;
  

  @Override
  public void visit(BooleanValue e) {
    last_predSubst_expr = new BooleanValue(e.value);
  }

  @Override
  public void visit(Number e) {
    last_predSubst_expr = new Number(e.value);
  }

  @Override
  public void visit(Variable v) {
        last_predSubst_expr = new Variable(v.name);
  }

  @Override
  public void visit(PredicateDefinition p){
    for (PredicateDefinition pDef : defs) {
      if(pDef.name == p.name && pDef.arity == p.arity){
        SubstVisitor v = new SubstVisitor();
        Expr e;
        for(int i =0; i<pDef.arity; i++){
          Variable variable = new Variable(pDef.variable_names.get(i));
          if(p.vMap.get(variable.name)!=null){
            e = p.vMap.get(variable.name);
          }else{
            e = variable;
          }
          v.addBinding(variable.name,e);

        }

        pDef.expr_def.accept(v);
        last_predSubst_expr = v.last_subst_expr;
      }
    }

  }

  @Override
  public void visit(AndExpr e) {
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_predSubst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_predSubst_expr;
    last_predSubst_expr = new AndExpr(r1, r2);    
  }

  @Override
  public void visit(OrExpr e) {
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_predSubst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_predSubst_expr;
    last_predSubst_expr = new OrExpr(r1, r2);    
  }

  @Override
  public void visit(ImpliesExpr e) {
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_predSubst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_predSubst_expr;
    last_predSubst_expr = new ImpliesExpr(r1, r2);    
  }

  @Override
  public void visit(NotExpr e) {
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_predSubst_expr;
    last_predSubst_expr = new NotExpr(r1);    
  }

  @Override
  public void visit(EqualBoolExpr e) {
    e.left.accept(this);
    BoolExpr r1 = (BoolExpr) last_predSubst_expr;
    e.right.accept(this);
    BoolExpr r2 = (BoolExpr) last_predSubst_expr;
    last_predSubst_expr = new EqualBoolExpr(r1, r2);    
  }

  @Override
  public void visit(EqualArithExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new EqualArithExpr(r1, r2);    
  }

  @Override
  public void visit(LessThanExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new LessThanExpr(r1, r2);     
  }

  @Override
  public void visit(GreaterThanExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new GreaterThanExpr(r1, r2);    
  }

  @Override
  public void visit(PlusExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new PlusExpr(r1, r2);    
  }

  @Override
  public void visit(SubtractionExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new SubtractionExpr(r1, r2);    
  }

  @Override
  public void visit(MultiplicationExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new MultiplicationExpr(r1, r2);    
  }

  @Override
  public void visit(DivisionExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new DivisionExpr(r1, r2);    
  }

  @Override
  public void visit(EqualVariableExpr e) {
    e.left.accept(this);
    ArithExpr r1 = (ArithExpr) last_predSubst_expr;
    e.right.accept(this);
    ArithExpr r2 = (ArithExpr) last_predSubst_expr;
    last_predSubst_expr = new EqualArithExpr(r1, r2);    
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
    void visit(SubtractionExpr e);
    void visit(MultiplicationExpr e);
    void visit(DivisionExpr e);
    void visit(Number e);

    void visit(Variable e);
    void visit(EqualVariableExpr e);

    void visit(PredicateDefinition e);
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
    public void visit(SubtractionExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" - ");
        e.right.accept(this);
        System.out.print(")");
    }
    @Override
    public void visit(MultiplicationExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" * ");
        e.right.accept(this);
        System.out.print(")");
    }
    @Override
    public void visit(DivisionExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" / ");
        e.right.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Number e) {
        System.out.print(e.value);
    }


    @Override
    public void visit(Variable e) {
        System.out.print(e.name);
    }

    @Override
    public void visit(EqualVariableExpr e) {
        System.out.print("(");
        e.left.accept(this);
        System.out.print(" == ");
        e.right.accept(this);
        System.out.print(")");
    }
  
    @Override
    public void visit(PredicateDefinition e) {
      System.out.print(e.name);
      
    }
}

//////////////////////////////
////  Evaluator Visitor //////
//////////////////////////////

class ExprEvalVisitor implements ExprVisitor {

    public double  last_double_result;
    public boolean last_bool_result;
    public String last_string_result;

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
    public void visit(SubtractionExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_double_result = r1 - r2;
    }
    @Override
    public void visit(MultiplicationExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_double_result = r1 * r2;
    }
    @Override
    public void visit(DivisionExpr e) {
        e.left.accept(this);
        double r1 = last_double_result;
        e.right.accept(this);
        double r2 = last_double_result;

        last_double_result = r1 / r2;
    }

    @Override
    public void visit(Number e) {
        last_double_result = e.value;
    }

    @Override
    public void visit(Variable e) {
      last_string_result = e.name;
      
    }

    @Override
    public void visit(EqualVariableExpr e) {
        e.left.accept(this);
        String r1 = last_string_result;
        e.right.accept(this);
        String r2 = last_string_result;
        last_bool_result = (r1 == r2);
    }

    @Override
    public void visit(PredicateDefinition e) {
      last_string_result = e.name;
      
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


    public static void testVariable(){
      Variable v = new Variable("p1.x");
      ExprPrintVisitor p = new ExprPrintVisitor();
      p.visit(v);
    }

    public static void substTest() {  
      Variable x = new Variable("X");

      Expr e = 
        new EqualArithExpr(
          new Variable("X"), new Number(4)
      );

      SubstVisitor subst = new SubstVisitor();
      subst.addBinding(x.name, new Number(4));
      e.accept(subst);
 
      Expr esub = subst.last_subst_expr;

      System.out.println("The expression is:");
      esub.accept(new ExprPrintVisitor());
      System.out.println("\n");

      ExprEvalVisitor eval = new ExprEvalVisitor();
      esub.accept(eval);
      System.out.println("The expression evaluates to: " + eval.last_bool_result);

   }

   public static void substTest2() {
    Expr e = 
      new LessThanExpr(
        new Variable("X"), new Number(4)
    );

    SubstVisitor subst = new SubstVisitor();
    // subst.addBinding(new Variable("X"), new Number(5));
    e.accept(subst);
   
    Expr esub = subst.last_subst_expr;

    System.out.println("The expression is:");
    esub.accept(new ExprPrintVisitor());
    System.out.println("\n");

    ExprEvalVisitor eval = new ExprEvalVisitor();
    esub.accept(eval);
    System.out.println("The expression evaluates to: " + eval.last_bool_result);
 }


 public static void predTest1() {

  Variable x = new Variable("X");
  Variable y = new Variable("Y");
  Variable b = new Variable("B");

  SubstVisitor substv = new SubstVisitor();
  substv.addBinding(x.name, new Number(1));
  substv.addBinding(y.name, new Number(2));
  substv.addBinding(b.name, new BooleanValue(false));
  
  
  List<String> list = new ArrayList<String>();
  list.add(x.name);
  list.add(y.name);
  list.add(b.name);

  PredicateDefinition p = new PredicateDefinition("L", 3, list, new AndExpr(new LessThanExpr(x, y),b), substv.bindings);
  
  PredicateSubstVisitor subst = new PredicateSubstVisitor();
  subst.addPredicate(p);
  p.accept(subst);

  Expr esub = subst.last_predSubst_expr;

  System.out.println("The expression is:");
  esub.accept(new ExprPrintVisitor());
  System.out.println("\n");

  ExprEvalVisitor eval = new ExprEvalVisitor();
  esub.accept(eval);
  System.out.println("The expression evaluates to: " + eval.last_bool_result);
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


      Expr e8 = new EqualVariableExpr(new Variable("x1"), new Variable("x2"));

      // testExpression(e1);
 
      // substTest();
      // substTest2();

      predTest1();

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
          - if it is a free variable -> first develop without thinking of quantifiers
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


