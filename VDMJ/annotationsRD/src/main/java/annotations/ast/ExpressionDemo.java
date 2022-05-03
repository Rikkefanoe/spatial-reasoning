package annotations.ast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

import com.microsoft.z3.*;


interface Expr{
  void accept(ExprVisitor visitor);
}


////////////////////////////////
////  Boolean Expressions //////
////////////////////////////////

interface BooleanExpr extends Expr{}

class AndExpr implements BooleanExpr {
  
  AndExpr(BooleanExpr e1, BooleanExpr e2) {
    left = e1;
    right = e2;
  }

  public BooleanExpr left;
  public BooleanExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class OrExpr implements BooleanExpr {
  
  OrExpr(BooleanExpr e1, BooleanExpr e2) {
    left = e1;
    right = e2;
  }

  public BooleanExpr left;
  public BooleanExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class ImpliesExpr implements BooleanExpr {
  
  ImpliesExpr(BooleanExpr e1, BooleanExpr e2) {
    left = e1;
    right = e2;
  }

  public BooleanExpr left;
  public BooleanExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class NotExpr implements BooleanExpr {
  
  NotExpr(BooleanExpr e1) {
    left = e1;
  }

  public BooleanExpr left;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}

class EqualBooleanExpr implements BooleanExpr {
  
  EqualBooleanExpr(BooleanExpr e1, BooleanExpr e2) {
    left = e1;
    right = e2;
  }

  public BooleanExpr left;
  public BooleanExpr right;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}



class BooleanValue implements BooleanExpr {
  BooleanValue(boolean v) {value = v;}
  public boolean value;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }

}


class LessThanExpr implements BooleanExpr {

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

class GreaterThanExpr implements BooleanExpr {

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

class EqualArithExpr implements BooleanExpr {

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

class ExistsExpr implements BooleanExpr{
  ExistsExpr(Variable e1, Expr e2){
    variable=e1;
    expr=e2;
  }
  public Variable variable;
  public Expr expr;

  @Override
  public void accept(ExprVisitor visitor) {
      visitor.visit(this);
  }
}


// class ForAllExpr implements Expr{
//   ForAllExpr(Variable e1, Expr e2){
//     variable=e1;
//     expr=e2;
//   }
//   public Variable variable;
//   public Expr expr;

//   @Override
//   public void accept(ExprVisitor visitor) {
//       visitor.visit(this);
//   }
// }


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

class Variable implements VariableExpr, ArithExpr, BooleanExpr {
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
      BooleanExpr r1 = (BooleanExpr) last_subst_expr;
      e.right.accept(this);
      BooleanExpr r2 = (BooleanExpr) last_subst_expr;
      last_subst_expr = new AndExpr(r1, r2);
  }
  @Override
  public void visit(OrExpr e){
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_subst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_subst_expr;
    last_subst_expr = new OrExpr(r1, r2);
  }
  @Override
  public void visit(ImpliesExpr e){
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_subst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_subst_expr;
    last_subst_expr = new ImpliesExpr(r1, r2);
  }
  @Override
  public void visit(NotExpr e){
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_subst_expr;
    last_subst_expr = new NotExpr(r1);
  }
  
  @Override
  public void visit(EqualBooleanExpr e){
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_subst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_subst_expr;
    last_subst_expr = new EqualBooleanExpr(r1, r2);
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
  public void visit(PredicateExpr e) {
    last_subst_expr = new PredicateExpr(e.name, e.arguments);    
  }
  @Override
  public void visit(ExistsExpr e) {
    e.expr.accept(this);
    Expr e1 = last_subst_expr;
  // bound.add(e.variable)
    last_subst_expr = new ExistsExpr(new Variable(e.variable.name), e1);    
  //bound.remove(e.variable)
}

  
}
///////////////////////////////////
////   Predicate definition  //////
///////////////////////////////////

class PredicateDefinition{
  String name;
  List<String> variable_names;
  Expr expr_def;
  
  PredicateDefinition(String n, List<String> v, Expr d){
    name = n;
    variable_names = v;
    expr_def = d;
  }

  int arity() {return variable_names.size();}

  String getName() {return name;}

  String getArgName(int i) {return variable_names.get(i);}

  Expr getExprDefinition() {return expr_def;}
}

class PredicateExpr implements Expr{
  String name;
  List<Expr> arguments;
  PredicateExpr(String n, List<Expr> args){
    name=n;
    arguments=args;
  }

  int arity() {return arguments.size();}
  String getName() {return name;}
  Expr getArg(int i) {return arguments.get(i);}

 

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

  private boolean hasPred(String n, int a) {
    for (PredicateDefinition pDef : defs) {
      if(pDef.getName() == n && pDef.arity() == a)
        return true;
    }
    return false;
  }

  //- precondition: assumes pred definition exists in defs
  private PredicateDefinition getPredDef(String n, int a) {
    for (PredicateDefinition pDef : defs) {
      if(pDef.getName() == n && pDef.arity() == a)
        return pDef;
    }
    return null;
  }

  private Expr doSubst(PredicateDefinition d, PredicateExpr p) {
    SubstVisitor v = new SubstVisitor();
    //- set up the bindings
    for(int i = 0; i < d.arity(); i++) {
      v.addBinding(d.getArgName(i), p.getArg(i));
    }
    //- do the sub on the definition
    d.getExprDefinition().accept(v);
    return v.last_subst_expr;
  }



  @Override
  public void visit(PredicateExpr p) {
    if(hasPred(p.getName(), p.arity())) {
      PredicateDefinition d = getPredDef(p.getName(), p.arity());
      last_predSubst_expr = doSubst(d,p);
    } else {
      last_predSubst_expr = p;
    }
  }


  @Override
  public void visit(AndExpr e) {
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_predSubst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_predSubst_expr;
    last_predSubst_expr = new AndExpr(r1, r2);    
  }

  @Override
  public void visit(OrExpr e) {
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_predSubst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_predSubst_expr;
    last_predSubst_expr = new OrExpr(r1, r2);    
  }

  @Override
  public void visit(ImpliesExpr e) {
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_predSubst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_predSubst_expr;
    last_predSubst_expr = new ImpliesExpr(r1, r2);    
  }

  @Override
  public void visit(NotExpr e) {
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_predSubst_expr;
    last_predSubst_expr = new NotExpr(r1);    
  }

  @Override
  public void visit(EqualBooleanExpr e) {
    e.left.accept(this);
    BooleanExpr r1 = (BooleanExpr) last_predSubst_expr;
    e.right.accept(this);
    BooleanExpr r2 = (BooleanExpr) last_predSubst_expr;
    last_predSubst_expr = new EqualBooleanExpr(r1, r2);    
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
  @Override
  public void visit(ExistsExpr e) {
    e.expr.accept(this);
    Expr e1 = last_predSubst_expr;
    last_predSubst_expr = new ExistsExpr(new Variable(e.variable.name), e1);    
}

  // @Override
  // public void visit(ForAllExpr e) {
  //   // TODO Auto-generated method stub
    
  // }
}

class z3Visitor implements ExprVisitor{

  Context ctx = new Context();

  Map<String, RatNum> z3vars = new HashMap<>();


  BoolExpr last_z3_boolExpr;
  RatNum last_z3_RatNumExpr;



  @Override
  public void visit(AndExpr e) {
    e.left.accept(this);
    BoolExpr e1 = last_z3_boolExpr;
    e.right.accept(this);
    BoolExpr e2 = last_z3_boolExpr;
    last_z3_boolExpr = ctx.mkAnd(e1,e2);
  }

  @Override
  public void visit(OrExpr e) {
    e.left.accept(this);
    BoolExpr e1 = last_z3_boolExpr;
    e.right.accept(this);
    BoolExpr e2 = last_z3_boolExpr;
    last_z3_boolExpr = ctx.mkOr(e1,e2);    
  }

  @Override
  public void visit(ImpliesExpr e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NotExpr e) {
    // TODO Auto-generated method stub
    e.left.accept(this);
    BoolExpr e1 = last_z3_boolExpr;
    last_z3_boolExpr = ctx.mkNot(e1);
    
  }

  @Override
  public void visit(EqualBooleanExpr e) {
    e.left.accept(this);
    BoolExpr e1 = last_z3_boolExpr;
    e.right.accept(this);
    BoolExpr e2 = last_z3_boolExpr;
    last_z3_boolExpr = ctx.mkEq(e1,e2);    
  }

  @Override
  public void visit(BooleanValue e) {
    // last_z3_boolExpr = ctx.mkBoolConst(e.value);
    
  }

  @Override
  public void visit(EqualArithExpr e) {
    // TODO Auto-generated method stub
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    last_z3_boolExpr = ctx.mkEq(e1,e2);
  }

  @Override
  public void visit(LessThanExpr e) {
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    last_z3_boolExpr = ctx.mkLt(e1,e2);
  }

  @Override
  public void visit(GreaterThanExpr e) {
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    last_z3_boolExpr = ctx.mkGt(e1,e2);    
  }

  @Override
  public void visit(PlusExpr e) {
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    // last_z3_RatNumExpr = ctx.mkAdd(e1,e2);    
  }

  @Override
  public void visit(SubtractionExpr e) {
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    // last_z3_RatNumExpr = ctx.mkSub(e1,e2);       
  }

  @Override
  public void visit(MultiplicationExpr e) {
    e.left.accept(this);
    RatNum e1 = last_z3_RatNumExpr;
    e.right.accept(this);
    RatNum e2 = last_z3_RatNumExpr;
    // last_z3_RatNumExpr = ctx.mkMul(e1,e2);       
  }

  @Override
  public void visit(DivisionExpr e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(Number e) {
    last_z3_RatNumExpr = ctx.mkReal(e.value);
    
  }

  @Override
  public void visit(Variable e) {
      if(!z3vars.containsKey(e.name)){
        System.out.println("Error: trying to evaluate a free variable");
      }else{
        last_z3_RatNumExpr = z3vars.get(e.name);
      }

  }

  @Override
  public void visit(EqualVariableExpr e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void visit(PredicateExpr e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ExistsExpr e) {
    String vn  = e.variable.name;
    z3vars.put(vn,ctx.mkReal(vn.charAt(0)));
    e.expr.accept(this);


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
    void visit(EqualBooleanExpr e);
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

    void visit(PredicateExpr e);

    void visit(ExistsExpr e);
    // void visit(ForAllExpr e);
    
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
    public void visit(EqualBooleanExpr e){
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
    public void visit(PredicateExpr e) {
      System.out.print(e.name);
    }

    @Override
    public void visit(ExistsExpr e){
      System.out.print("(");
      System.out.print("exists ");
      e.variable.accept(this);
      System.out.print(" . ");
      e.expr.accept(this);
      System.out.print(")");
    }
    // @Override
    // public void visit(ForAllExpr e){
    //   System.out.print("(");
    //   System.out.print("forall ");
    //   e.variable.accept(this);
    //   e.expr.accept(this);
    //   System.out.print(")");
    // }
    
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
    public void visit(EqualBooleanExpr e) {
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
    public void visit(PredicateExpr e) {
      // last_string_result = e.name;
      System.out.println("Error PredicateExpr not supported for basic expression evaluation");

    }

    @Override 
    public void visit(ExistsExpr e){
      System.out.println("Error ExistsExpr not supported for basic expression evaluation");
    
    }

    // @Override 
    // public void visit(ForAllExpr e){
    
    // }


    
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

  // Variable x = new Variable("X");
  // Variable y = new Variable("Y");
  // Variable b = new Variable("B");

  // SubstVisitor substv = new SubstVisitor();
  // substv.addBinding(x.name, new Number(1));
  // substv.addBinding(y.name, new Number(2));
  // substv.addBinding(b.name, new BooleanValue(false));
  
  
  // List<String> list = new ArrayList<String>();
  // list.add(x.name);
  // list.add(y.name);
  // list.add(b.name);

  // PredicateDefinition p = new PredicateDefinition("L", 3, list, new AndExpr(new LessThanExpr(x, y),b), substv.bindings);
  
  // PredicateSubstVisitor subst = new PredicateSubstVisitor();
  // subst.addPredicate(p);
  // p.accept(subst);

  // Expr esub = subst.last_predSubst_expr;

  // System.out.println("The expression is:");
  // esub.accept(new ExprPrintVisitor());
  // System.out.println("\n");

  // ExprEvalVisitor eval = new ExprEvalVisitor();
  // esub.accept(eval);
  // System.out.println("The expression evaluates to: " + eval.last_bool_result);


  /////////////////////////
  // PredicateDefinition p = new PredicateDefinition("Smaller", Arrays.asList("X","Y"), new LessThanExpr(new Variable("X"), new Variable("Y")) );


  // Expr e = new PredicateExpr("Smaller",Arrays.asList(
  //  new Number(5),
  //  new Number(6) ));


  // Expr e = new PredicateExpr("Coincident",Arrays.asList(
  //   new Number(1),
  //   new Number(2),
  //   new Number(5),
  //   new Number(6)));

  // PredicateDefinition p = new PredicateDefinition(
  //   "Coincident",  Arrays.asList("X1", "Y1", "X2", "Y2"),
  //   new AndExpr(
  //     new EqualArithExpr(new Variable("X1"), new Variable("X2")),  
  //     new EqualArithExpr(new Variable("Y1"), new Variable("Y2"))
  //   )
  // );

  // PredicateSubstVisitor v = new PredicateSubstVisitor();
  // v.addPredicate(p); 
  // e.accept(v);

  // Expr esub = v.last_predSubst_expr;



  // System.out.println("The expression is:");
  // esub.accept(new ExprPrintVisitor());
  // System.out.println("\n");

  // ExprEvalVisitor eval = new ExprEvalVisitor();
  // esub.accept(eval);
  // System.out.println("The expression evaluates to: " + eval.last_bool_result);
}


// public static void testVis(){
//   Expr e = new PredicateExpr("smaller", Arrays.asList(
//       new Number(5),
//       new Number(6)));

//   PredicateDefinition def = new PredicateDefinition("smaller", Arrays.asList("X","Y"), new LessThanExpr(new Variable("X"), new Variable("Y")));

//   PredicateSubstVisitor v = new PredicateSubstVisitor();
//   v.addPredicate(def);
//   e.accept(v);
//   Expr e2 = v.last_predSubst_expr;

//   e.accept(new ExprPrintVisitor());

//   ExprEvalVisitor eval = new ExprEvalVisitor();
//   e2.accept(eval);

// }



public static void testz3(){
  Expr e = new ExistsExpr(new Variable("X"),new NotExpr(new EqualArithExpr(new Variable("X"), new Variable("X"))));
  // exist x not(x==x)
    
  z3Visitor v = new z3Visitor();
  
  e.accept(v);
  
  Solver s = v.ctx.mkSolver();
  
  s.add(v.last_z3_boolExpr);
  
  Status q = s.check();
  System.out.println("Solver says: "+q);

}

public static String createSolver(z3Visitor v){
  Solver s = v.ctx.mkSolver();
  
  s.add(v.last_z3_boolExpr);
  
  Status q = s.check();
  if(q == Status.SATISFIABLE){
    System.out.println(s.getModel());
  }

  // System.out.println("Solver says: "+q);
  return q.toString();
}
    

    // public static void main(final String[] args) {

    //    Expr e1 = new AndExpr(
    //               new LessThanExpr(
    //                 new PlusExpr(new Number(3), new Number(4)),
    //                 new Number(100)
    //               ),
    //               new BooleanValue(true)
    //             );

    //    Expr e2 = new AndExpr(
    //               new LessThanExpr(
    //                 new PlusExpr(new Number(300), new Number(4)),
    //                 new Number(100)
    //                 ),
    //               new BooleanValue(true)
    //             );

    //    Expr e3 = new OrExpr(
    //                 new LessThanExpr(
    //                   new PlusExpr(new Number(300), new Number(4)),
    //                   new Number(100)
    //                 ),
    //                 new BooleanValue(true)
    //                 );

    //    Expr e4 = new ImpliesExpr(
    //               new BooleanValue(true),
    //               new BooleanValue(true)
    //               );

    //   Expr e6 = new AndExpr(
    //     new LessThanExpr(
    //       new PlusExpr(new Number(3), new Number(4)),
    //       new Number(100)
    //     ),
    //     new NotExpr(
    //       new BooleanValue(true)
    //       )
    //   );

    //   Expr e7 = new AndExpr(
    //     new EqualBooleanExpr(new BooleanValue(true), new BooleanValue(true)),
    //     new EqualArithExpr(new Number(5), new Number(5))
    //     );


    //   Expr e8 = new EqualVariableExpr(new Variable("x1"), new Variable("x2"));

    //   // testExpression(e1);
 
    //   // substTest();
    //   // substTest2();

    //   // predTest1();
    //   // testz3();


    
    // }
}


