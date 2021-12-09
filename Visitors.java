import tester.Tester;

import java.util.function.Function;

//--------------------------------- IArith ----------------------------------- //

// To represent an IArith
interface IArith {

  // Accepts a visitor and directs it to corresponding method
  <R> R accept(IArithVisitor<R> visitor);

}

//--------------------------------- CONST ------------------------------------- //

// To represent a Constant (Const)
class Const implements IArith {
  double num;

  // Main Constructor
  Const(double num) {
    this.num = num;
  }

  // To return the result of applying the given visitor to this Const
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitConst(this);
  }

}
//--------------------------------- FORMULA ------------------------------------ //

// To represent a Formula
class Formula implements IArith {
  IFunc2<Double, Double, Double> fun;
  String name;
  IArith left;
  IArith right;

  // Main Constructor
  Formula(IFunc2<Double, Double, Double> fun, String name, IArith left, IArith right) {
    this.fun = fun;
    this.name = name;
    this.left = left;
    this.right = right;
  }

  // To return the result of applying the given visitor to this Formula
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitFormula(this);
  }

}

//------------------------------- IFunc2<A1, A2, R>  ----------------------------------- //

// To represent an IFunc2
interface IFunc2<A1, A2, R> {
  R apply(A1 a1, A2 a2);
}

//------------------------------- IArithVisitor<R> ----------------------------------- //

// To represent an IArithVisitor<R>
interface IArithVisitor<R> extends Function<IArith, R> {

  // To represent a Const Visitor
  R visitConst(Const constant);

  // To represent a Formula Visitor
  R visitFormula(Formula f);

}
//------------------------------- EVAL VISITORS ----------------------------------- //

// To evaluate an IArith
class EvalVisitor implements IArithVisitor<Double> {

  // To compute the value of a Const
  public Double visitConst(Const constant) {
    return constant.num;
  }

  // To compute the value of a Formula
  public Double visitFormula(Formula form) {
    return form.fun.apply(form.left.accept(this), form.right.accept(this));
  }

  // Applies the given arithmetic to this visitor
  public Double apply(IArith a1) {
    return a1.accept(this);
  }

}
//------------------------------- PRINT VISITOR ----------------------------------- //

// To print an IArith as a String
class PrintVisitor implements IArithVisitor<String> {

  // Visits Const and computes its value as a String
  public String visitConst(Const constant) {
    return Double.toString(constant.num);
  }

  // Visits a Formula and computes its value as a String
  public String visitFormula(Formula form) {
    return "(" + form.name + " " + form.left.accept(this) + " " + form.right.accept(this) + ")";
  }

  // Applies the given arithmetic to this visitor
  public String apply(IArith a1) {
    return a1.accept(this);
  }

}

//------------------------------- DOUBLER VISITOR ----------------------------------- //

// To double all values
class DoublerVisitor implements IArithVisitor<IArith> {

  // To visit a Const and double its value
  public IArith visitConst(Const constant) {
    return new Const(constant.num * 2);
  }

  // To visit a formula and multiply
  public IArith visitFormula(Formula form) {
    return new Formula(form.fun, form.name, form.left.accept(this), form.right.accept(this));
  }

  // Applies the given arithmetic to this visitor
  public IArith apply(IArith a1) {
    return a1.accept(this);
  }

}
// -------------------------------ALL SMALL VISITORS ----------------------------------- //

// To determine if an IArith is made of values < 10
class AllSmallVisitor implements IArithVisitor<Boolean> {

  // To visit a Const and determine if its value is < 10
  public Boolean visitConst(Const constant) {
    return constant.num < 10;
  }

  // To visit a Formula and determine if its values are < 10
  public Boolean visitFormula(Formula form) {
    return form.left.accept(this) && form.right.accept(this);
  }

  // Applies the given arithmetic to this visitor
  public Boolean apply(IArith a1) {
    return a1.accept(this);
  }

}

// ----------------------------- Mathematical Operators ------------------------------//

//To represent the division of two doubles
class Divide implements IFunc2<Double, Double, Double> {

  // Divides the first double by the second double
  public Double apply(Double a1, Double a2) {
    return a1 / a2;
  }
}

// To represent the subtraction of two doubles
class Subtract implements IFunc2<Double, Double, Double> {

  // Subtracts the second double from the first double
  public Double apply(Double a1, Double a2) {
    return a1 - a2;
  }
}

// To represent the addition of two doubles
class Add implements IFunc2<Double, Double, Double> {

  // Adds the first double and the second double
  public Double apply(Double a1, Double a2) {
    return a1 + a2;
  }
}

// To represent the multiplication of two doubles
class Multiply implements IFunc2<Double, Double, Double> {

  // Multiplies the first double and the second double
  public Double apply(Double a1, Double a2) {
    return a1 * a2;
  }
}

//------------------------------- EXAMPLES ----------------------------------- //

// Examples Class
class ExamplesVisitors {

  // Const Examples
  IArith c0 = new Const(0.0);
  IArith c1 = new Const(1.0);
  IArith c2 = new Const(2.0);
  IArith c3 = new Const(3.0);
  IArith c4 = new Const(4.0);
  IArith c5 = new Const(5.0);
  IArith c10 = new Const(10.0);
  IArith c20 = new Const(20.0);

  // Formula Examples
  IArith f1 = new Formula(new Divide(), "divide", this.c1, this.c2);
  IArith f2 = new Formula(new Multiply(), "multiply", this.c2, this.c4);
  IArith f3 = new Formula(new Add(), "add", this.c0, this.c5);
  IArith f4 = new Formula(new Subtract(), "subtract", this.c20, this.c10);
  IArith fComplex = new Formula(new Add(), "complex", this.f1, this.f2);
  IArith fComplex2 = new Formula(new Multiply(), "complexmult", this.f3, this.f4);
  IArith fComplex3 = new Formula(new Add(), "add", this.c10, this.fComplex2);
  IArith fComplex4 = new Formula(new Add(), "add", this.fComplex3, this.c5);

  // -------------------------- TESTS --------------------------------//

  // Test method apply
  boolean testMathematicalOperators(Tester t) {
    return t.checkExpect(new Divide().apply(5.0, 8.0), 0.625)
        && t.checkExpect(new Divide().apply(20.0, 5.0), 4.0)
        && t.checkExpect(new Subtract().apply(5.0, 1.0), 4.0)
        && t.checkExpect(new Subtract().apply(0.0, 1.0), -1.0)
        && t.checkExpect(new Add().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Add().apply(0.0, 0.0), 0.0)
        && t.checkExpect(new Add().apply(16.0, 4.0), 20.0)
        && t.checkExpect(new Multiply().apply(4.0, 6.0), 24.0)
        && t.checkExpect(new Multiply().apply(3.0, 9.0), 27.0);
  }

  boolean testEvalVisitor(Tester t) {
    return t.checkInexact(new EvalVisitor().apply(c0), 0.0, 0.001)
        && t.checkInexact(new EvalVisitor().apply(f1), 0.5, 0.001)
        && t.checkInexact(new EvalVisitor().apply(c0.accept(new DoublerVisitor())), 0.0, 0.001)
        && t.checkInexact(new EvalVisitor().apply(fComplex), 8.5, 0.001)
        && t.checkInexact(new EvalVisitor().apply(fComplex4), 65.0, 0.001);
  }

  boolean testPrintVisitor(Tester t) {
    return t.checkExpect(new PrintVisitor().apply(c2), "2.0")
        && t.checkExpect(new PrintVisitor().apply(f2), "(multiply 2.0 4.0)")
        && t.checkExpect(new PrintVisitor().apply(fComplex),
            "(complex (divide 1.0 2.0) (multiply 2.0 4.0))")
        && t.checkExpect(new PrintVisitor().apply(fComplex4),
            "(add (add 10.0 (complexmult (add 0.0 5.0) (subtract 20.0 10.0))) 5.0)");
  }

  boolean testDoublerVisitor(Tester t) {
    return t.checkExpect(new DoublerVisitor().apply(c5), new Const(10.0))
        && t.checkExpect(new DoublerVisitor().apply(f3),
            new Formula(new Add(), "add", this.c0, new Const(10.0)))
        && t.checkExpect(new DoublerVisitor().apply(fComplex),
            new Formula(new Add(), "complex",
                new Formula(new Divide(), "divide", new Const(2.0), new Const(4.0)),
                new Formula(new Multiply(), "multiply", new Const(4.0), new Const(8.0))))
        && t.checkExpect(new DoublerVisitor().apply(fComplex4),
            new Formula(new Add(), "add",
                new Formula(new Add(), "add", new Const(20.0),
                    new Formula(new Multiply(), "complexmult",
                        new Formula(new Add(), "add", new Const(0.0), new Const(10.0)),
                        new Formula(new Subtract(), "subtract", new Const(40.0), new Const(20.0)))),
                new Const(10.0)));

  }

  boolean testAllSmallVisitor(Tester t) {
    return t.checkExpect(new AllSmallVisitor().apply(c10), false)
        && t.checkExpect(new AllSmallVisitor().apply(c5), true)
        && t.checkExpect(new AllSmallVisitor().apply(f1), true)
        && t.checkExpect(new AllSmallVisitor().apply(fComplex2), false)
        && t.checkExpect(new AllSmallVisitor().apply(fComplex4), false);
  }

  // Test method accept
  boolean testAccept(Tester t) {
    return t.checkExpect(this.c0.accept(new EvalVisitor()), 0.0)
        && t.checkExpect(this.f1.accept(new EvalVisitor()), 0.5)
        && t.checkExpect(this.c2.accept(new PrintVisitor()), "2.0")
        && t.checkExpect(this.f2.accept(new PrintVisitor()), "(multiply 2.0 4.0)")
        && t.checkExpect(this.c5.accept(new DoublerVisitor()), new Const(10.0))
        && t.checkExpect(this.f3.accept(new DoublerVisitor()),
            new Formula(new Add(), "add", this.c0, new Const(10.0)))
        && t.checkExpect(this.c10.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.c5.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.f1.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.fComplex2.accept(new AllSmallVisitor()), false);
  }
}
