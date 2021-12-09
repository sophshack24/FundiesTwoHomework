import tester.Tester;

// to represent a list of motifs
interface ILoMotif {

  // counts the number of motifs in this list
  int count();

  // adds together the total difficulties of motifs in this list
  double total();

  // returns information about this list of motifs
  String listInfo();

}

// to represent an empty list of motifs
class MtLoMotif implements ILoMotif {
  MtLoMotif() {
  }

  /*
   * Fields:
   * 
   * Methods: 
   * this.count() ... int 
   * this.add() ... double
   * this.listInfo() ... String
   * this.motifAverage() ... double
   * 
   * Methods on Fields:
   * 
   */

  // counts the number of motifs in this list
  public int count() {
    return 0;
  }

  // adds together the total difficulties of motifs in this list
  public double total() {
    return 0;
  }

  // returns information about this list of motifs
  public String listInfo() {
    return "";
  }

  // calculates the average of the total difficulties of motifs in this list
  public double motifAverage() {
    return 0.0;
  }

}

// to represent a non-empty list of motifs
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * Fields: 
   * this.first ... IMotif 
   * this.rest ... ILoMotif
   * 
   * Methods: 
   * this.count() ... int 
   * this.total() ... double
   * this.listInfo() ... String
   * 
   * Methods for Fields: 
   * this.first.motifCount() ... int
   * this.first.motifTotal() ... double
   * this.first.motifAverage() ... double
   * this.first.motifInfo() ... String
   * this.rest.count() ... int 
   * this.rest.total() ... double
   * this.rest.listInfo() ... String
   */

  // counts the number of motifs in this list
  public int count() {
    return this.first.motifCount() + this.rest.count();
  }

  // adds together the total difficulties of motifs in this list
  public double total() {
    return this.first.motifTotal() + this.rest.total();
  }

  // returns information about this list of motifs
  public String listInfo() {
    return this.first.motifInfo() + this.rest.listInfo();
  }

}

// to represent a motif
interface IMotif {

  // returns total difficulty of this motif
  double motifTotal();

  // returns count of this motif
  int motifCount();

  // returns average difficulty of this motif
  double motifAverage();

  // returns information about this motif
  String motifInfo();

}

// to represent a cross-stitched motif
class CrossStitchMotif implements IMotif {
  String description;
  double difficulty;

  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * Fields:
   * this.description ... String
   * this.difficulty ... double
   * 
   * Methods:
   * this.motifTotal ... double
   * this.motifCount ... int
   * this.motifAverage ... double
   * this.motifInfo ... String
   * 
   * Methods for Fields:
   * 
   */

  // returns total difficulty of this motif
  public double motifTotal() {
    return this.difficulty;
  }

  // returns count of this motif
  public int motifCount() {
    return 1;
  }

  // returns average difficulty of this motif
  public double motifAverage() {
    return this.difficulty;
  }

  // returns information about this motif
  public String motifInfo() {
    return this.description + " (cross stitch), ";
  }

}

// to represent a chain-stitched motif
class ChainStitchMotif implements IMotif {
  String description;
  double difficulty;

  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * Fields:
   * this.description ... String
   * this.difficulty ... double
   * 
   * Methods:
   * this.motifTotal ... double
   * this.motifCount ... int
   * this.motifAverage ... double
   * this.motifInfo ... String
   * 
   * Methods for Fields:
   * 
   */

  // returns total difficulty of this motif
  public double motifTotal() {
    return this.difficulty;
  }

  // returns count of this motif
  public int motifCount() {
    return 1;
  }

  // returns average difficulty of this motif
  public double motifAverage() {
    return this.difficulty;
  }

  // returns information about this motif
  public String motifInfo() {
    return this.description + " (chain stitch), ";
  }

}

// to represent a group of motifs
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  /*
   * Fields:
   * this.description ... String
   * this.motifs ... ILoMotif
   * 
   * Methods:
   * this.motifTotal ... double
   * this.motifCount ... int
   * this.motifAverage ... double
   * this.motifInfo ... String
   * 
   * Methods for Fields:
   * this.motifs.count() ... int 
   * this.motifs.total() ... double
   * this.motifs.listInfo() ... String
   */

  // returns total difficulty of this motif
  public double motifTotal() {
    return this.motifs.total();
  }

  // returns count of this motif
  public int motifCount() {
    return this.motifs.count();
  }

  // returns average difficulty of this motif
  public double motifAverage() {
    if (this.motifs.count() == 0) {
      return 0.0;
    }
    else {
      return this.motifs.total() / this.motifs.count();
    }
  }

  // returns information about this motif
  public String motifInfo() {
    return this.motifs.listInfo();
  }

}

// to represent an embroidery piece
class EmbroideryPiece {
  String name;
  IMotif motif;

  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }

  /*
   * Fields: 
   * this.name ... String 
   * this.motif ... IMotif
   * 
   * Methods: 
   * this.averageDifficulty ... double
   * this.embroideryInfoBeginning ... String
   * this.embroideryInfoFirst ... String
   * this.embroideryInfo ... String
   * 
   * Methods for Fields: 
   * this.motif.motifTotal ... double
   * this.motif.motifCount ... int
   * this.motif.motifAverage ... double
   * this.motif.motifInfo ... String
   */
  
  // returns the average difficulty of the motif(s) of this embroidery piece
  public double averageDifficulty() {
    return this.motif.motifAverage();
  }

  // returns the name of this embroidery piece 
  public String embroideryInfoBeginning() {
    return this.name + ": ";
  }


  // returns all of the information about the motifs in this embroidery
  public String embroideryInfoFirst() {
    return this.embroideryInfoBeginning() + motif.motifInfo();
  }
  
  // returns all of the information about the motifs in this embroidery in the required format
  public String embroideryInfo() {
    if (this.motif.motifInfo().length() < 2) {
      return embroideryInfoBeginning() + ".";
    }
    else {
      return embroideryInfoFirst().substring(0, (embroideryInfoFirst().length() - 2)) + ".";
    }
  }
}

// to represent examples and tests of embroidery
class ExamplesEmbroidery {
  ExamplesEmbroidery() {}
  
  // examples
  IMotif rose = new CrossStitchMotif("rose", 5.0);
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif daisy = new CrossStitchMotif("daisy", 3.2);
  IMotif bird = new CrossStitchMotif("bird", 4.5);
  IMotif tree = new ChainStitchMotif("tree", 3.0);

  ILoMotif empty = new MtLoMotif();
  
  ILoMotif testexamplemt = new ConsLoMotif(this.daisy, this.empty);
  ILoMotif testexample = new ConsLoMotif(this.tree, this.testexamplemt);
  ILoMotif daisylist = new ConsLoMotif(this.daisy, this.empty);
  ILoMotif daisypoppylist = new ConsLoMotif(this.poppy, this.daisylist);
  ILoMotif flowerlist = new ConsLoMotif(this.rose, this.daisypoppylist);
  IMotif flowers = new GroupMotif("flowers", this.flowerlist);

  ILoMotif flowernaturelist = new ConsLoMotif(this.flowers, this.empty);
  ILoMotif treeflowerlist = new ConsLoMotif(this.tree, this.flowernaturelist);
  ILoMotif naturelist = new ConsLoMotif(this.bird, this.treeflowerlist);

  IMotif nature = new GroupMotif("nature", this.naturelist);
  IMotif emptymotif = new GroupMotif("empty", this.empty);
  IMotif testmotif = new GroupMotif("test motif", this.testexample);

  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", this.nature);
  EmbroideryPiece MT = new EmbroideryPiece("empty", this.emptymotif);
  EmbroideryPiece test = new EmbroideryPiece("test", this.testmotif);
  
  // tests
  boolean testCount(Tester t) {
    return t.checkExpect(this.flowerlist.count(), 3) 
        && t.checkExpect(this.naturelist.count(), 5)
        && t.checkExpect(this.empty.count(), 0);
  } 

  boolean testTotal(Tester t) {
    return t.checkInexact(this.flowerlist.total(), 12.95, 0.001)
        && t.checkInexact(this.naturelist.total(), 20.45, 0.001)
        && t.checkExpect(this.empty.total(), 0.0);
  }
  
  boolean testListInfo(Tester t) {
    return t.checkExpect(this.flowerlist.listInfo(), "rose (cross stitch), poppy (chain stitch),"
        + " daisy (cross stitch), ")
        && t.checkExpect(this.naturelist.listInfo(), "bird (cross stitch), tree (chain stitch), "
            + "rose (cross stitch), poppy (chain stitch), daisy (cross stitch), ")
        && t.checkExpect(this.empty.listInfo(), "");
  }

  boolean testMotifCount(Tester t) {
    return t.checkExpect(this.bird.motifCount(), 1) 
        && t.checkExpect(this.tree.motifCount(), 1)
        && t.checkExpect(this.nature.motifCount(), 5);
  }

  boolean testMotifTotal(Tester t) {
    return t.checkInexact(this.bird.motifTotal(), 4.5, 0.001)
        && t.checkInexact(this.tree.motifTotal(), 3.0, 0.001)
        && t.checkInexact(this.nature.motifTotal(), 20.45, 0.001);
  }

  boolean testMotifAverage(Tester t) {
    return t.checkInexact(this.bird.motifAverage(), 4.5, 0.001)
        && t.checkInexact(this.tree.motifAverage(), 3.0, 0.001)
        && t.checkInexact(this.nature.motifAverage(), 4.09, 0.001);
  }
  
  boolean testMotifInfo(Tester t) {
    return t.checkExpect(this.bird.motifInfo(), "bird (cross stitch), ")
        && t.checkExpect(this.tree.motifInfo(), "tree (chain stitch), ")
        && t.checkExpect(this.flowers.motifInfo(), "rose (cross stitch), poppy (chain stitch), "
            + "daisy (cross stitch), ");
  }

  boolean testAverageDifficulty(Tester t) {
    return t.checkInexact(this.pillowCover.averageDifficulty(), 4.09, 0.001)
        && t.checkInexact(this.MT.averageDifficulty(), 0.0, 0.001);
  }

  boolean testEmbroideryInfoBeginning(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfoBeginning(), "Pillow Cover: ")
        && t.checkExpect(this.MT.embroideryInfoBeginning(), "empty: ");
  }
  
  boolean testEmbroideryInfoFirst(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfoFirst(), "Pillow Cover: bird "
        + "(cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch),"
        + " daisy (cross stitch), ")
        && t.checkExpect(this.MT.embroideryInfoFirst(), "empty: ");
  }
  
  boolean testEmbroideryInfo(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfo(),
        "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch),"
        + " poppy (chain stitch), daisy (cross stitch).")
        && t.checkExpect(this.test.embroideryInfo(),
            "test: tree (chain stitch), daisy (cross stitch).")
        && t.checkExpect(this.MT.embroideryInfo(), "empty: .");
  }
}