import tester.Tester;

import javalib.worldimages.*; // images, like RectangleImage or OverlayImages
import javalib.funworld.*; // the abstract World class and the big-bang library
import javalib.worldcanvas.WorldCanvas;
import java.awt.Color;

// to represent a mobile
interface IMobile {

  // computes the total weight of this mobile
  int totalWeight();

  // computes the height of this mobile
  int totalHeight();

  // performs necessary calculations to compute height of this mobile
  double totalHeightHelp();

  // determines if this mobile is balanced (there is no net torque)
  boolean isBalanced();

  //creates a new balanced mobile from this balanced mobile on the left,
  // and the given balanced mobile on the right
  IMobile buildMobile(IMobile givenmobile, int string, int strut);

  // calculates the ratio at which the strut should be split to create a
  // mobile with no net torque
  double helperRatio(IMobile givenmobile);

  // computes the current width of this mobile
  int curWidth();

  // computes the current width of the right side of this mobile
  int curWidthRight();

  // computes the current width of the left side of this mobile
  int curWidthLeft();

  // produces the image of this mobile if it were hanging from a nail at some Posn
  WorldImage drawMobile();

  // scales up the values of this mobile so that it is visible on a canvas
  int scaleFactor();

  // acts as a constant value for the width of strings and struts 
  int widthConstant();

  // returns the total weight as a double
  double totalWeightDouble();
  
  //returns the total height as a double
  double totalHeightDouble();

}

// to represent a mobile with a simple design
class Simple implements IMobile {
  int length;
  int weight;
  Color color;

  Simple(int length, int weight, Color color) {
    this.length = length;
    this.weight = weight;
    this.color = color;
  }

  /* TEMPLATE:
   * 
   * FIELDS: 
   * this.length ... int 
   * this.weight ... int 
   * this.color ... Color
   * 
   * METHODS: 
   * this.totalWeightDouble ... double
   * this.totalWeight ... int 
   * this.totalWeightDouble ... double
   * this.totalHeight ... int 
   * this.totalHeightHelp ... double
   * this.helperRatio(IMobile givenmobile) ... double
   * this.isBalanced ... boolean 
   * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
   * this.curWidth ... int
   * this.curWidthLeft ... int
   * this.curWidthRight ... int
   * this.drawMobile ... WorldImage
   * this.drawMobileHelper ... WorldImage
   * this.drawWeight ... WorldImage
   * this.drawString ... WorldImage
   * this.scaleFactor ... int
   * this.widthConstant ... int
   * 
   * METHODS ON FIELDS:
   * 
   */

  // returns the total weight as a double
  public double totalWeightDouble() {
    return this.weight;
  }

  // computes the weight of this mobile
  public int totalWeight() {
    return (int) this.totalWeightDouble();
  }

  // returns the total height as a double
  public double totalHeightDouble() {
    return this.totalHeightHelp();
  }

  // computes the height of this mobile
  public int totalHeight() {
    return (int) this.totalHeightDouble();
  }

  // performs necessary calculations to compute height of this mobile
  public double totalHeightHelp() {
    return this.length + Math.floor(this.weight / 10);
  }

  // determines if this mobile is balanced (there is no net torque)
  public boolean isBalanced() {
    return true;
  }

  //calculates the ratio at which the strut should be split to create a
  // mobile with no net torque
  public double helperRatio(IMobile givenmobile) {
    /* TEMPLATE
     * refer to class template
     * 
     * PARAMETERS: 
     * givenmobile ... IMobile
     * 
     * METHODS ON PARAMETERS:
     * this.totalWeightDouble ... double
     * this.totalWeight ... int 
     * this.totalWeightDouble ... double
     * this.totalHeight ... int 
     * this.totalHeightHelp ... double
     * this.helperRatio(IMobile givenmobile) ... double
     * this.isBalanced ... boolean 
     * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
     * this.curWidth ... int
     * this.curWidthLeft ... int
     * this.curWidthRight ... int
     * this.curWidthMaxRight ... int
     * this.curWidthMaxLeft ... int
     * this.drawMobile ... WorldImage
     * this.drawMobileHelper ... WorldImage
     * this.drawWeight ... WorldImage
     * this.drawString ... WorldImage
     * this.drawStrut ... WorldImage
     * this.scaleFactor ... int
     * this.widthConstant ... int
    */ 
    return this.totalWeightDouble() / (this.totalWeightDouble() + givenmobile.totalWeight());
  }

  //creates a new balanced mobile from this balanced mobile on the left,
  // and the given balanced mobile on the right
  public IMobile buildMobile(IMobile givenmobile, int string, int strut) {
    /* TEMPLATE
     * refer to class template
     * 
     * PARAMETERS:
     * givenmobile ... IMobile
     * string ... int 
     * strut ... int
     * 
     * METHODS ON PARAMETERS:
     * this.totalWeightDouble ... double
     * this.totalWeight ... int 
     * this.totalWeightDouble ... double
     * this.totalHeight ... int 
     * this.totalHeightHelp ... double
     * this.helperRatio(IMobile givenmobile) ... double
     * this.isBalanced ... boolean 
     * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
     * this.curWidth ... int
     * this.curWidthLeft ... int
     * this.curWidthRight ... int
     * this.curWidthMaxRight ... int
     * this.curWidthMaxLeft ... int
     * this.drawMobile ... WorldImage
     * this.drawMobileHelper ... WorldImage
     * this.drawWeight ... WorldImage
     * this.drawString ... WorldImage
     * this.drawStrut ... WorldImage
     * this.scaleFactor ... int
     * this.widthConstant ... int
     */
    return new Complex(string, (int) Math.floor(strut * this.helperRatio(givenmobile)),
        (int) Math.floor(strut * (1 - this.helperRatio(givenmobile))), this, givenmobile);
  }

  // computes the current width of the right side of this mobile
  public int curWidthRight() {
    return (int) (Math.ceil((Math.ceil(this.weight / 10)) / 2));
  }

  // computes the current width of the left side of this mobile
  public int curWidthLeft() {
    return (int) (Math.ceil((Math.ceil(this.weight / 10)) / 2));
  }

  // computes the current width of this mobile
  public int curWidth() {
    return (int) (Math.ceil(this.weight / 10));
  }

  // produces the image of this mobile if it were hanging from a nail at some Posn
  public WorldImage drawMobile() {
    return this.drawMobileHelper();
  }

  // places the image of this mobile's string above the image of this mobile's weight
  public WorldImage drawMobileHelper() {
    return new AboveImage(this.drawString(), this.drawWeight());
  }

  // produces the image of this mobile's weight 
  public WorldImage drawWeight() {
    return new RectangleImage((int) (scaleFactor() * Math.ceil(this.weight / 10)),
        (int) (scaleFactor() * Math.floor(this.weight / 10)), OutlineMode.SOLID, this.color);
  }

  // produces the image of the vertical string of this mobile
  public WorldImage drawString() {
    return new RectangleImage(widthConstant(), scaleFactor() * this.length, OutlineMode.SOLID,
        Color.BLACK);
  }

  // scales up the values of this mobile so that it is visible on a canvas
  public int scaleFactor() {
    return 10;
  }

  // acts as a constant value for the width of strings and struts 
  public int widthConstant() {
    return 2;
  }

}

// to represent a mobile with a complex design
class Complex implements IMobile {
  int length;
  int leftside;
  int rightside;
  IMobile left;
  IMobile right;

  Complex(int length, int d, int e, IMobile left, IMobile right) {
    this.length = length;
    this.leftside = d;
    this.rightside = e;
    this.left = left;
    this.right = right;
  }

  /* TEMPLATE:
   * 
   * FIELDS: 
   * this.length ... int 
   * this.leftside ... int 
   * this.rightside ... int
   * this.left ... IMobile 
   * this.right ... IMobile
   * 
   * METHODS: 
   * this.totalWeightDouble ... double
   * this.totalWeight ... int 
   * this.totalWeightDouble ... double
   * this.totalHeight ... int 
   * this.totalHeightHelp ... double
   * this.helperRatio(IMobile givenmobile) ... double
   * this.isBalanced ... boolean 
   * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
   * this.curWidth ... int
   * this.curWidthLeft ... int
   * this.curWidthRight ... int
   * this.curWidthMaxRight ... int
   * this.curWidthMaxLeft ... int
   * this.drawMobile ... WorldImage
   * this.drawMobileHelper ... WorldImage
   * this.drawWeight ... WorldImage
   * this.drawString ... WorldImage
   * this.drawStrut ... WorldImage
   * this.scaleFactor ... int
   * this.widthConstant ... int
   * 
   * METHODS ON FIELDS: 
   * this.left.totalWeightDouble ... double
   * this.left.totalWeight ... int 
   * this.left.totalWeightDouble ... double
   * this.left.totalHeight ... int 
   * this.left.totalHeightHelp ... double
   * this.left.helperRatio(IMobile givenmobile) ... double
   * this.left.isBalanced ... boolean 
   * this.left.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
   * this.left.curWidth ... int
   * this.left.curWidthLeft ... int
   * this.left.curWidthRight ... int
   * this.left.curWidthMaxRight ... int
   * this.left.curWidthMaxLeft ... int
   * this.left.drawMobile ... WorldImage
   * this.left.drawMobileHelper ... WorldImage
   * this.left.drawWeight ... WorldImage
   * this.left.drawString ... WorldImage
   * this.left.drawStrut ... WorldImage
   * this.left.scaleFactor ... int
   * this.left.widthConstant ... int
   * this.right.totalWeightDouble ... double
   * this.right.totalWeight ... int 
   * this.right.totalWeightDouble ... double
   * this.right.totalHeight ... int 
   * this.right.totalHeightHelp ... double
   * this.right.helperRatio(IMobile givenmobile) ... double
   * this.right.isBalanced ... boolean 
   * this.right.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
   * this.right.curWidth ... int
   * this.right.curWidthLeft ... int
   * this.right.curWidthRight ... int
   * this.right.curWidthMaxRight ... int
   * this.right.curWidthMaxLeft ... int
   * this.right.drawMobile ... WorldImage
   * this.right.drawMobileHelper ... WorldImage
   * this.right.drawWeight ... WorldImage
   * this.right.drawString ... WorldImage
   * this.right.drawStrut ... WorldImage
   * this.right.scaleFactor ... int
   * this.right.widthConstant ... int
   */

  // returns the total weight as a double
  public double totalWeightDouble() {
    return this.left.totalWeight() + this.right.totalWeight();
  }

  // computes the total weight of this mobile
  public int totalWeight() {
    return (int) this.totalWeightDouble();
  }

  // returns the total height as a double
  public double totalHeightDouble() {
    return (this.length + Math.max(this.left.totalHeightHelp(), this.right.totalHeightHelp()));
  }

  // computes the total height of this mobile
  public int totalHeight() {
    return (int) this.totalHeightDouble();
  }

  // performs necessary calculations to compute height of this mobile
  public double totalHeightHelp() {
    return this.length + Math.max(this.left.totalHeightHelp(), this.right.totalHeightHelp());
  }

  // determines if this mobile is balanced (there is no net torque)
  public boolean isBalanced() {
    return (this.leftside * this.left.totalWeight()) == (this.rightside * this.right.totalWeight());
  }

  // calculates the ratio at which the strut should be split to create a
  // mobile with no net torque
  public double helperRatio(IMobile givenmobile) {
    /* TEMPLATE
     * refer to class template
     * 
     * PARAMETERS: 
     * givenmobile ... IMobile
     * 
     * METHODS ON PARAMETERS:
     * this.totalWeightDouble ... double
     * this.totalWeight ... int 
     * this.totalWeightDouble ... double
     * this.totalHeight ... int 
     * this.totalHeightHelp ... double
     * this.helperRatio(IMobile givenmobile) ... double
     * this.isBalanced ... boolean 
     * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
     * this.curWidth ... int
     * this.curWidthLeft ... int
     * this.curWidthRight ... int
     * this.curWidthMaxRight ... int
     * this.curWidthMaxLeft ... int
     * this.drawMobile ... WorldImage
     * this.drawMobileHelper ... WorldImage
     * this.drawWeight ... WorldImage
     * this.drawString ... WorldImage
     * this.drawStrut ... WorldImage
     * this.scaleFactor ... int
     * this.widthConstant ... int
    */ 
    return this.totalWeight() / (this.totalWeightDouble() + givenmobile.totalWeightDouble());
  }

  // creates a new balanced mobile from this balanced mobile on the left,
  // and the given balanced mobile on the right
  public IMobile buildMobile(IMobile givenmobile, int string, int strut) {
    /* TEMPLATE
     * refer to class template
     * 
     * PARAMETERS:
     * givenmobile ... IMobile
     * string ... int 
     * strut ... int
     * 
     * METHODS ON PARAMETERS:
     * this.totalWeightDouble ... double
     * this.totalWeight ... int 
     * this.totalWeightDouble ... double
     * this.totalHeight ... int 
     * this.totalHeightHelp ... double
     * this.helperRatio(IMobile givenmobile) ... double
     * this.isBalanced ... boolean 
     * this.buildMobile(IMobile givenmobile, int string, int strut) ... IMobile 
     * this.curWidth ... int
     * this.curWidthLeft ... int
     * this.curWidthRight ... int
     * this.curWidthMaxRight ... int
     * this.curWidthMaxLeft ... int
     * this.drawMobile ... WorldImage
     * this.drawMobileHelper ... WorldImage
     * this.drawWeight ... WorldImage
     * this.drawString ... WorldImage
     * this.drawStrut ... WorldImage
     * this.scaleFactor ... int
     * this.widthConstant ... int
     */
    return new Complex(string, (int) Math.floor(strut * this.helperRatio(givenmobile)),
        (int) Math.floor(strut * (1 - this.helperRatio(givenmobile))), this, givenmobile);
  }

  // computes the current width of the right side of this mobile
  public int curWidthMaxRight() {
    return (int) Math.max(this.curWidthRight(), this.left.curWidthRight() - this.leftside);
  }

  // computes the current width of the left side of this mobile
  public int curWidthMaxLeft() {
    return (int) Math.max(this.curWidthLeft(), this.right.curWidthLeft() - this.rightside);
  }

  // computes the current width of this mobile
  public int curWidth() {
    return this.curWidthMaxLeft() + this.curWidthMaxRight();
  }

  // computes the current width of the right side of this mobile
  public int curWidthRight() {
    return (int) (this.rightside + (int) (this.right.curWidthRight()));
  }

  // computes the current width of the left side of this mobile
  public int curWidthLeft() {
    return (int) (this.leftside + (int) (this.left.curWidthLeft()));
  }

  // produces the image of this mobile if it were hanging from a nail at some Posn
  public WorldImage drawMobile() {
    return new VisiblePinholeImage(
        new AboveImage(this.drawString(), this.drawStrut(),
            this.left.drawMobile()
                .movePinhole(scaleFactor() * ((this.leftside + this.rightside) / 2), 0),
            this.right.drawMobile()));
  }

  // produces the image of this horizontal strut within this node of this mobile
  public WorldImage drawStrut() {
    return new RectangleImage(
        (int) (scaleFactor() * this.leftside) + (int) (scaleFactor() * this.rightside),
        (int) widthConstant(), OutlineMode.SOLID, Color.BLACK);
  }

  // produces the image of this vertical string within this node of this mobile
  public WorldImage drawString() {
    return new RectangleImage(widthConstant(), scaleFactor() * this.length, OutlineMode.SOLID,
        Color.BLACK);
  }

  // acts as a constant value for the width of strings and struts 
  public int widthConstant() {
    return 2;
  }

  // scales up the values of this mobile so that it is visible on a canvas
  public int scaleFactor() {
    return 10;
  }

}

//to represent examples and tests of mobiles
class ExamplesMobiles {
  ExamplesMobiles() {}

  // EXAMPLES
  Color blue = new Color(0, 0, 255);
  Color red = new Color(255, 0, 0);
  Color green = new Color(0, 255, 0);

  IMobile exampleSimple = new Simple(2, 20, this.blue);
  IMobile exampleComplex = new Complex(1, 9, 3, new Simple(1, 36, this.blue),
      new Complex(2, 8, 1, new Simple(1, 12, this.red),
          new Complex(2, 5, 3, new Simple(2, 36, this.red), new Simple(1, 60, this.green))));
  IMobile lightSimple = new Simple(1, 2, this.blue);
  IMobile example3 = new Complex(1, 72, 1, lightSimple, exampleComplex);
  IMobile example4 = new Complex(1, 20, 20, exampleSimple, exampleComplex);
  IMobile example5 = new Complex(1, 10, 10, exampleSimple, 
      new Complex(1, 50, 10, exampleSimple, exampleSimple));

  // TESTS
  boolean testTotalHeight(Tester t) {
    return t.checkExpect(this.exampleSimple.totalHeight(), 4)
        && t.checkExpect(this.exampleComplex.totalHeight(), 12)
        && t.checkExpect(this.lightSimple.totalHeight(), 1);
  }

  boolean testTotalHeightHelp(Tester t) {
    return t.checkExpect(this.exampleSimple.totalHeightHelp(), 4.0)
        && t.checkExpect(this.exampleComplex.totalHeightHelp(), 12.0)
        && t.checkExpect(this.lightSimple.totalHeightHelp(), 1.0);
  }

  boolean testTotalHeightDouble(Tester t) {
    return t.checkExpect(((Simple) this.exampleSimple).totalHeightDouble(), 4.0)
        && t.checkExpect(((Complex) this.exampleComplex).totalHeightDouble(), 12.0)
        && t.checkExpect(((Simple) this.lightSimple).totalHeightDouble(), 1.0);
  }

  boolean testTotalWeight(Tester t) {
    return t.checkExpect(this.exampleSimple.totalWeight(), 20)
        && t.checkExpect(this.exampleComplex.totalWeight(), 144)
        && t.checkExpect(this.lightSimple.totalWeight(), 2)
        && t.checkExpect(this.example3.totalWeight(), 146);
  }

  boolean testTotalWeightDouble(Tester t) {
    return t.checkExpect(this.exampleSimple.totalWeightDouble(), 20.0)
        && t.checkExpect(this.exampleComplex.totalWeightDouble(), 144.0)
        && t.checkExpect(this.lightSimple.totalWeightDouble(), 2.0)
        && t.checkExpect(this.example3.totalWeightDouble(), 146.0);
  }

  boolean testIsBalanced(Tester t) {
    return t.checkExpect(this.example3.isBalanced(), true)
        && t.checkExpect(this.exampleSimple.isBalanced(), true)
        && t.checkExpect(this.lightSimple.isBalanced(), true)
        && t.checkExpect(this.exampleComplex.isBalanced(), true);
  }

  boolean testHelperRatio(Tester t) {
    return t.checkInexact(this.exampleSimple.helperRatio(this.exampleComplex), 0.122, 0.001)
        && t.checkInexact(this.example3.helperRatio(this.exampleComplex), 0.5034, 0.001)
        && t.checkInexact(this.exampleComplex.helperRatio(this.lightSimple), 0.9863, 0.001);
  }

  boolean testBuildMobile(Tester t) {
    return t.checkExpect(this.exampleSimple.buildMobile(this.exampleComplex, 20, 40),
        new Complex(20, 4, 35, this.exampleSimple, this.exampleComplex))
        && t.checkExpect(this.example3.buildMobile(this.exampleComplex, 13, 250),
            new Complex(13, 125, 124, this.example3, this.exampleComplex))
        && t.checkExpect(this.exampleComplex.buildMobile(this.lightSimple, 23, 456),
            new Complex(23, 449, 6, this.exampleComplex, this.lightSimple));
  }

  boolean testCurWidth(Tester t) {
    return t.checkExpect(this.exampleSimple.curWidth(), 2)
        && t.checkExpect(this.exampleComplex.curWidth(), 21)
        && t.checkExpect(this.example4.curWidth(), 40 + 10 + 1)
        && t.checkExpect(this.example5.curWidth(), 50 + 1 + 10 + 1);
  }

  boolean testCurWidthRight(Tester t) {
    return t.checkExpect(this.exampleComplex.curWidthRight(), 10)
        && t.checkExpect(this.example4.curWidthRight(), 30);
  }

  boolean testCurWidthMaxRight(Tester t) {
    return t.checkExpect(((Complex) this.exampleComplex).curWidthMaxRight(), 10)
        && t.checkExpect(((Complex) this.example4).curWidthMaxRight(), 30);
  }

  boolean testCurWidthMaxLeft(Tester t) {
    return t.checkExpect(this.exampleComplex.curWidthLeft(), 11)
        && t.checkExpect(this.example4.curWidthLeft(), 21);
  }

  boolean testCurWidthLeft(Tester t) {
    return t.checkExpect(this.exampleComplex.curWidthLeft(), 11)
        && t.checkExpect(this.example4.curWidthLeft(), 21);
  }

  boolean testImages(Tester t) {
    return t.checkExpect(new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY),
        new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY))
        && t.checkExpect(((Simple) this.exampleSimple).drawString(),
            new RectangleImage(2, 20, OutlineMode.SOLID, Color.black))
        && t.checkExpect(((Complex) this.exampleComplex).drawString(),
            new RectangleImage(2, 10, OutlineMode.SOLID, Color.black))
        && t.checkExpect(((Complex) this.exampleComplex).drawStrut(),
            new RectangleImage(120, 2, OutlineMode.SOLID, Color.black))
        && t.checkExpect(((Complex) this.example3).drawStrut(),
            new RectangleImage(730, 2, OutlineMode.SOLID, Color.black));
  }

  boolean testDrawMobileS(Tester t) {
    WorldCanvas c = new WorldCanvas(1000, 1000);
    WorldScene s = new WorldScene(1000, 1000);
    return c.drawScene(s.placeImageXY(this.exampleComplex.drawMobile(), 500, 250)) && c.show();
  }
  
}
