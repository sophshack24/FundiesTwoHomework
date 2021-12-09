import tester.Tester;

import java.awt.Color;

// to represent paint on a painter's palette
interface IPaint {

  // computes the final color of this paint
  Color getFinalColor();

  // darkens the color of this paint
  Color darkenColor();

  // brightens the color of this paint
  Color brightenColor();
  
  // retrieves the red rgb value
  int getRedValue();

  // retrieves the green rgb value 
  int getGreenValue();

  // retrieves the blue rgb value
  int getBlueValue();

  // computes the number of solid paints involved in producing the final color of this paint
  int countPaints();

  //  computes the number of times this paint was mixed in some way
  int countMixes();

  // computes how deeply mixtures are nested in the formula for this paint
  int formulaDepth();
  
  // produces the paint as is, except all Darken mixtures should 
  // become Brighten mixtures and vice versa
  IPaint invert();
  
  // produces a String representing the contents of this paint, 
  // where the formula for the paint has been expanded only depth times
  String mixingFormula(int depth);
  
  // retrieves the name of this paint
  String paintName();

}

// to represent a solid paint 
class Solid implements IPaint {
  String name;
  Color color;

  Solid(String name, Color color) {
    this.name = name;
    this.color = color;
  }
  
  /*
   * Fields:
   * this.name ... String
   * this.color ... Color
   * 
   * Methods:
   * this.getFinalColor ... Color
   * this.darkenColor ... Color
   * this.brightenColor ... Color
   * this.getRedValue ... int
   * this.getGreenValue ... int
   * this.getBlueValue ... int
   * this.countPaints ... int
   * this.countMixes ... int
   * this.formulaDepth ... int
   * this.invert ... IPaint
   * this.mixingFormula ... String
   * this.paintName ... String
   * 
   * Methods for Fields:
   * this.color.darker ... Color
   * this.color.brighter ... Color
   * this.color.getRed ... int
   * this.color.getGreen ... int
   * this.color.getBlue ... int
   * 
   */

  // computes the final color of this paint
  public Color getFinalColor() {
    return this.color;
  }

  // darkens the color of this paint
  public Color darkenColor() {
    return this.color.darker();
  }

  // brightens the color of this paint
  public Color brightenColor() {
    return this.color.brighter();
  }

  // retrieves the red rgb value
  public int getRedValue() {
    return this.color.getRed();
  }

  // retrieves the green rgb value
  public int getGreenValue() {
    return this.color.getGreen();
  }
  
  // retrieves the blue rgb value
  public int getBlueValue() {
    return this.color.getBlue();
  }

  // computes the number of solid paints involved in producing the final color of this Paint
  public int countPaints() {
    return 1;
  }

  //  computes the number of times this paint was mixed in some way
  public int countMixes() {
    return 0;
  }

  // computes how deeply mixtures are nested in the formula for this Paint
  public int formulaDepth() {
    return 0;
  }

  // produces the paint as is, except all Darken mixtures should 
  // become Brighten mixtures and vice versa
  public IPaint invert() {
    return this;
  }
  
  // produces a String representing the contents of this paint, 
  // where the formula for the paint has been expanded only depth times
  public String mixingFormula(int depth) {
    return this.name;
  }
  
  // retrieves the name of this paint
  public String paintName() {
    return this.name;
  }
  
}

// to represent a paint that has been combined in some way
class Combo implements IPaint {
  String name;
  IMixture operation;

  Combo(String name, IMixture operation) {
    this.name = name;
    this.operation = operation;
  }
  
  /*
   * Fields:
   * this.name ... String
   * this.operation ... IMixture
   * 
   * Methods:
   * this.getFinalColor ... Color
   * this.darkenColor ... Color
   * this.brightenColor ... Color
   * this.getRedValue ... int
   * this.getGreenValue ... int
   * this.getBlueValue ... int
   * this.countPaints ... int
   * this.countMixes ... int
   * this.formulaDepth ... int
   * this.invert ... IPaint
   * this.mixingFormula ... String
   * this.paintName ... String
   * 
   * Methods for Fields:
   * this.color.darker ... Color
   * this.color.brighter ... Color
   * this.color.getRed ... int
   * this.color.getGreen ... int
   * this.color.getBlue ... int
   * 
   */
  
  // computes the final color of this paint
  public Color getFinalColor() {
    return this.operation.iMixtureFinalColor();
  }

  // darkens the color of this paint
  public Color darkenColor() {
    return this.darkenColor();
  }
  
  // brightens the color of this paint
  public Color brightenColor() {
    return this.brightenColor();
  }
  
  // retrieves the red rgb value
  public int getRedValue() {
    return this.getRedValue();
  }

  //retrieves the green rgb value
  public int getGreenValue() {
    return this.getGreenValue();
  }

  //retrieves the blue rgb value
  public int getBlueValue() {
    return this.getBlueValue();
  }

  // computes the number of solid paints involved in producing the final color of this Paint
  public int countPaints() {
    return this.operation.countPaints();
  }

  //  computes the number of times this paint was mixed in some way
  public int countMixes() {
    return this.operation.countMixes();
  }

  // computes how deeply mixtures are nested in the formula for this Paint
  public int formulaDepth() {
    return this.operation.formulaDepth();
  }
  
  //produces the paint as is, except all Darken mixtures should 
  // become Brighten mixtures and vice versa
  public IPaint invert() {
    return new Combo(this.name, this.operation.mixtureInvert());
  }
  
  // produces a String representing the contents of this paint, 
  // where the formula for the paint has been expanded only depth times
  public String mixingFormula(int depth) {
    if (depth < 1) {
      return this.name;
    }
    else {
      return this.operation.mixingHelp(depth);
    }
  }
  
  // retrieves the name of this paint
  public String paintName() {
    return this.paintName();
  }

}

// to represent the type of paint mixture
interface IMixture {

  // computes the final color of this mixture
  Color iMixtureFinalColor();

  // computes the number of paints within this mixture
  int countPaints();

  // computes the number of times this mixture was mixed in some way
  int countMixes();

  // computes how deeply mixtures are nested in the formula for this mixture
  int formulaDepth();
  
  // swaps whether a color is darkened or brightened within this mixture
  IMixture mixtureInvert();
  
  String mixingHelp(int depth); 

}

// to represent a darkened paint mixture
class Darken implements IMixture {
  IPaint paint;

  Darken(IPaint color) {
    this.paint = color;
  }

  /*
   * Fields:
   * this.color ... IPaint
   * 
   * Methods:
   * this.iMixtureFinalColor ... Color
   * this.countPaints ... int
   * this.countMixes ... int
   * this.formulaDepth ... int
   * this.mixtureInvert ... IPaint
   * this.iMixtureMixingFormula ... String
   * 
   * Methods for Fields:
   * this.color.getFinalColor ... Color
   * this.color.darkenColor ... Color
   * this.color.brightenColor ... Color
   * this.color.getRedValue ... int
   * this.color.getGreenValue ... int
   * this.color.getBlueValue ... int
   * this.color.countPaints ... int
   * this.color.countMixes ... int
   * this.color.formulaDepth ... int
   * this.color.invert ... IPaint
   * this.color.mixingFormula ... String
   * this.color.paintName ... String
   */

  // computes the final color of this mixture
  public Color iMixtureFinalColor() {
    return new Color(this.paint.darkenColor().getRed(), this.paint.darkenColor().getGreen(),
        this.paint.darkenColor().getBlue());
  }

  // computes the number of paints within this mixture
  public int countPaints() {
    return 1 + paint.countPaints();
  }

  // computes the number of times this mixture was mixed in some way
  public int countMixes() {
    return 1 + paint.countMixes();
  }

  // computes how deeply mixtures are nested in the formula for this mixture
  public int formulaDepth() {
    return 1 + paint.countMixes();
  }
  
  // swaps whether a color is darkened or brightened within this mixture
  public IMixture mixtureInvert() {
    return new Brighten(this.paint);
  }
  
  public String mixingHelp(int depth) {
    return "darken(" + this.paint.mixingFormula(depth - 1) + ")";
  }
  
  public String mixingFormula(int depth) {
    if (depth < this.formulaDepth()) {
      return this.mixingHelp(depth);
    }
    else {
      return this.mixingHelp(formulaDepth());
    }
  }
  

  
}

// to represent a brightened paint mixture
class Brighten implements IMixture {
  IPaint paint;

  Brighten(IPaint color) {
    this.paint = color;
  }

  /*
   * Fields:
   * this.color ... IPaint
   * 
   * Methods:
   * this.iMixtureFinalColor ... Color
   * this.countPaints ... int
   * this.countMixes ... int
   * this.formulaDepth ... int
   * this.mixtureInvert ... IPaint
   * this.iMixtureMixingFormula ... String
   * 
   * Methods for Fields:
   * this.color.getFinalColor ... Color
   * this.color.darkenColor ... Color
   * this.color.brightenColor ... Color
   * this.color.getRedValue ... int
   * this.color.getGreenValue ... int
   * this.color.getBlueValue ... int
   * this.color.countPaints ... int
   * this.color.countMixes ... int
   * this.color.formulaDepth ... int
   * this.color.invert ... IPaint
   * this.color.mixingFormula ... String
   * this.color.paintName ... String
   */

  // computes the final color of this mixture
  public Color iMixtureFinalColor() {
    return new Color(this.paint.brightenColor().getRed(), this.paint.brightenColor().getGreen(),
        this.paint.brightenColor().getBlue());
  }

  // computes the number of paints within this mixture
  public int countPaints() {
    return 1 + paint.countPaints();
  }

  // computes the number of times this mixture was mixed in some way
  public int countMixes() {
    return 1 + paint.countMixes();
  }

  // computes how deeply mixtures are nested in the formula for this mixture
  public int formulaDepth() {
    return 1 + this.paint.countMixes();
  }
  
  // swaps whether a color is darkened or brightened within this mixture
  public IMixture mixtureInvert() {
    return new Darken(this.paint);
  }
  
 
  public String mixingHelp(int depth) {
    return "brighten(" + this.paint.mixingFormula(depth - 1) + ")";
  }
  
  public String mixingFormula(int depth) {
    if (depth < this.formulaDepth()) {
      return this.mixingHelp(depth);
    }
    else {
      return this.mixingHelp(formulaDepth());
    }
  }

}

// to represent a blended paint mixture
class Blend implements IMixture {
  IPaint paint1;
  IPaint paint2;

  Blend(IPaint paint1, IPaint paint2) {
    this.paint1 = paint1;
    this.paint2 = paint2;
  }
  
  /*
   * Fields:
   * this.color ... IPaint
   * 
   * Methods:
   * this.iMixtureFinalColor ... Color
   * this.countPaints ... int
   * this.countMixes ... int
   * this.formulaDepth ... int
   * this.mixtureInvert ... IPaint
   * this.iMixtureMixingFormula ... String
   * 
   * Methods for Fields:
   * this.paint1.getFinalColor ... Color
   * this.paint1.darkenColor ... Color
   * this.paint1.brightenColor ... Color
   * this.paint1.getRedValue ... int
   * this.paint1.getGreenValue ... int
   * this.paint1.getBlueValue ... int
   * this.paint1.ountPaints ... int
   * this.paint1.countMixes ... int
   * this.paint1.formulaDepth ... int
   * this.paint1.invert ... IPaint
   * this.paint1.mixingFormula ... String
   * this.paint1.paintName ... String
   * 
   * this.paint2.getFinalColor ... Color
   * this.paint2.darkenColor ... Color
   * this.paint2.brightenColor ... Color
   * this.paint2.getRedValue ... int
   * this.paint2.getGreenValue ... int
   * this.paint2.getBlueValue ... int
   * this.paint2.ountPaints ... int
   * this.paint2.countMixes ... int
   * this.paint2.formulaDepth ... int
   * this.paint2.invert ... IPaint
   * this.paint2.mixingFormula ... String
   * this.paint2.paintName ... String
   * 
   */

  // computes the final color of this mixture
  public Color iMixtureFinalColor() {
    return new Color(((this.paint1.getRedValue() / 2) + (this.paint2.getRedValue() / 2)),
        ((this.paint1.getGreenValue() / 2) + (this.paint2.getGreenValue() / 2)),
        ((this.paint1.getBlueValue() / 2) + (this.paint2.getBlueValue() / 2)));
  }

  // computes the number of paints within this mixture
  public int countPaints() {
    return paint1.countPaints() + paint2.countPaints();
  }

  // computes the number of times this mixture was mixed in some way
  public int countMixes() {
    return 1 + paint1.countMixes() + paint2.countMixes();
  }

  // computes how deeply mixtures are nested in the formula for this mixture
  public int formulaDepth() {
    return 1 + Math.max(paint1.countMixes(), paint2.countMixes());
  }
  
  // swaps whether a color is darkened or brightened within this mixture
  public IMixture mixtureInvert() {
    return new Blend(this.paint1.invert(), this.paint2.invert());
  }

  public String mixingHelp(int depth) {
    return "blend(" + this.paint1.mixingFormula(depth -1) + ", " + this.paint2.mixingFormula(depth -1) + ")";
  }
  
  public String mixingFormula(int depth) {
    if (depth < this.formulaDepth()) {
      return this.mixingHelp(depth);
    }
    else {
      return this.mixingHelp(formulaDepth());
    }
  }
}



class ExamplesPaint {
  ExamplesPaint() {
  }

  // examples
  IPaint red = new Solid("red", new Color(255, 0, 0));
  IPaint green = new Solid("green", new Color(0, 255, 0));
  IPaint blue = new Solid("blue", new Color(0, 0, 255));
  IPaint purple = new Combo("purple", new Blend(this.red, this.blue));
  IPaint darkPurple = new Combo("dark purple", new Darken(this.purple));
  IPaint khaki = new Combo("khaki", new Blend(this.red, this.green));
  IPaint yellow = new Combo("yellow", new Brighten(this.khaki));
  IPaint coral = new Combo("coral", new Blend(this.pink, this.khaki));
  IPaint mauve = new Combo("mauve", new Blend(this.purple, this.khaki));
  IPaint pink = new Combo("pink", new Brighten(this.mauve));
  IPaint brightYellow = new Combo("bright yellow", new Brighten(this.yellow));
  IPaint darkKhaki = new Combo("dark khaki", new Darken(this.khaki));
  IPaint blueishKhaki = new Combo("blue-ish khaki", new Blend(this.blue, this.khaki));
  IPaint indigo = new Solid("indigo", new Color(200, 0, 255));
  IPaint darkDarkPurple = new Combo("dark dark purple", 
      new Blend(this.darkPurple, this.darkPurple));
  IPaint brightPurple = new Combo("bright purple", new Brighten(this.purple));
  IPaint darkBlue = new Combo("dark blue", new Darken(this.blue));
  
  // tests
  boolean test1(Tester t) {
    return t.checkExpect(this.red.getRedValue(), 255) 
        && t.checkExpect(this.red.getGreenValue(), 0)
        && t.checkExpect(this.red.getBlueValue(), 0);
  }

  boolean paintName(Tester t) {
    return t.checkExpect(this.yellow, "yellow")
        && t.checkExpect(this.blueishKhaki, "blue-ish khaki");
  }
    
  boolean testDarkenColor(Tester t) {
    return t.checkExpect(this.blue.darkenColor(), new Color(0, 0, 255).darker());
  }
  
  boolean testIMixture(Tester t) {
    return t.checkInexact(this.purple.getFinalColor(), new Color(127, 0, 127), 0.001);
  }

  boolean testIMixture1(Tester t) {
    return t.checkInexact(this.khaki.getFinalColor(), new Color(127, 127, 0), 0.001);
  }

  boolean testCountPaints1(Tester t) {
    return t.checkExpect(this.blue.countPaints(), 1);
  }

  boolean testCountPaints2(Tester t) {
    return t.checkExpect(this.purple.countPaints(), 2);
  }

  boolean testCountPaints3(Tester t) {
    return t.checkExpect(this.blueishKhaki.countPaints(), 3);
  }

  boolean testCountPaints4(Tester t) {
    return t.checkExpect(this.darkPurple.countPaints(), 3);
  }

  boolean testFormulaDepth(Tester t) {
    return t.checkExpect(this.darkPurple.formulaDepth(), 2)
        && t.checkExpect(this.darkDarkPurple.formulaDepth(), 3);  
  }

  boolean testCountFormulaDepth1(Tester t) {
    return t.checkExpect(this.darkPurple.countMixes(), 2)
        && t.checkExpect(this.brightYellow.countMixes(), 3);
  }

  boolean testPaintName(Tester t) {
    return t.checkExpect(this.purple.mixingFormula(1), "blend(red, blue)")
        && t.checkExpect(this.purple.mixingFormula(0), "purple")
        && t.checkExpect(this.pink.mixingFormula(3), "brighten(blend(blend(red, blue), blend(red, green)))");
        }
  
  boolean testInvert(Tester t) {
    return 
        t.checkExpect(this.darkPurple.invert(),
            new Combo("dark purple", new Brighten(this.purple)))
        && t.checkExpect(this.darkDarkPurple.invert(), 
            new Combo("dark dark purple", 
                new Blend(new Combo("dark purple", new Brighten(this.purple)), 
                (new Combo("dark purple", new Brighten(this.purple))))));
  }
  
  IMixture darkpurplemix = new Darken(this.purple);
  IMixture brightbluemix = new Brighten(this.blue);
  IMixture indigomix = new Blend(this.darkPurple, this.darkBlue);
  IMixture darkkhakimix = new Darken(this.khaki);
  
  
  /*boolean testIMixtureFinalColor(Tester t) {
    return t.checkExpect(this.darkpurplemix.iMixtureFinalColor(), this.purple.darker()
        )
  }
  */
  
  boolean testCountPaints(Tester t) {
    return t.checkExpect(this.darkpurplemix.countPaints(), 3)
        && t.checkExpect(this.brightbluemix.countPaints(), 2)
        && t.checkExpect(this.indigomix.countPaints(), 5);
  }
  
  boolean testCountMixes(Tester t) {
    return t.checkExpect(this.darkpurplemix.countMixes(), 2)
        && t.checkExpect(this.brightbluemix.countMixes(), 1)
        && t.checkExpect(this.indigomix.countMixes(), 4);
  }
  
}