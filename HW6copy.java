import tester.Tester;
import java.util.Comparator;

//------------------ABST---------------------
abstract class ABST<T> {

  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

  /*
   * Fields: this.length ... int this.weight ... int this.color ... Color
   * 
   * Methods: this.totalWeight ... int this.totalHeight ... int this.isBalanced
   * ... boolean this.buildMobile(IMobile other) ... IMobile this.curWidth ... int
   * this.drawMobile(Posn p) ... WorldImage
   * 
   * Methods on Fields:
   * 
   */

  abstract ABST<T> insert(T t);

  abstract boolean present(T t);

  abstract T getLeftmost();

  abstract T getLeftMostHelper(T accum);

  abstract ABST<T> getRight();

  abstract ABST<T> removeLeft(T leftmost);

  abstract boolean sameTree(ABST<T> tree2);

  abstract boolean isSame(ABST<T> tree2);

  abstract boolean sameTreeHelper(Comparator<T> comp, T data, ABST<T> left, ABST<T> right);

  abstract boolean sameData(ABST<T> tree2);

  abstract boolean sameDataHelp(ABST<T> tree2);

  abstract IList<T> buildList();

}

//------------------LEAF---------------------

class Leaf<T> extends ABST<T> {
  Leaf(Comparator<T> order) {
    super(order);
  }

  /*
   * Fields: this.length ... int this.weight ... int this.color ... Color
   * 
   * Methods: this.totalWeight ... int this.totalHeight ... int this.isBalanced
   * ... boolean this.buildMobile(IMobile other) ... IMobile this.curWidth ... int
   * this.drawMobile(Posn p) ... WorldImage
   * 
   * Methods on Fields:
   * 
   */

  ABST<T> insert(T t) {
    return new Node<T>(this.order, t, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }

  boolean present(T t) {
    return false;
  }

  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  T getLeftMostHelper(T accum) {
    return accum;
  }

  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  ABST<T> removeLeft(T leftmost) {
    return this;
  }

  boolean sameTree(ABST<T> tree2) {
    return tree2.isSame(this);
  }

  boolean isSame(ABST<T> tree2) {
    return true;
  }

  boolean sameTreeHelper(Comparator<T> comp, T data, ABST<T> left, ABST<T> right) {
    return false;
  }

  boolean sameData(ABST<T> tree2) {
    return tree2.isSame(this);
  }

  boolean sameDataHelp(ABST<T> tree2) {
    return true;
  }

  IList<T> buildList() {
    return new MtList<T>();
  }
}

//------------------NODE---------------------

class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /*
   * Fields: this.length ... int this.weight ... int this.color ... Color
   * 
   * Methods: this.totalWeight ... int this.totalHeight ... int this.isBalanced
   * ... boolean this.buildMobile(IMobile other) ... IMobile this.curWidth ... int
   * this.drawMobile(Posn p) ... WorldImage
   * 
   * Methods on Fields:
   * 
   */

  ABST<T> insert(T t) {
    if (this.order.compare(this.data, t) > 0) {
      return new Node<T>(this.order, this.data, this.left.insert(t), this.right);
    }
    else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(t));
    }
  }

  boolean present(T t) {
    return this.order.compare(this.data, t) == 0 || this.left.present(t) || this.right.present(t);
  }

  T getLeftmost() {
    return this.left.getLeftMostHelper(this.data);
  }

  T getLeftMostHelper(T accum) {
    return this.left.getLeftMostHelper(this.data);
  }

  ABST<T> getRight() {
    T leftmost = this.getLeftmost();
    if (this.order.compare(this.data, leftmost) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.order, this.data, this.left.removeLeft(leftmost), this.right);
    }
  }

  ABST<T> removeLeft(T leftmost) {
    if (this.order.compare(this.data, leftmost) == 0) {
      return new Leaf<T>(this.order);
    }
    else {
      return new Node<T>(this.order, this.data, this.left.removeLeft(leftmost), this.right);
    }
  }

  /*
   * ABST<T> removeLeft(T leftmost) {
   * if (this.data == leftmost) {
   * return new Leaf<T>(this.order);
   * }
   * else {
   * return ((Node<T>) this.left).removeLeft(leftmost);
   * }
   * }
   */

  boolean sameTree(ABST<T> tree2) {
    return tree2.sameTreeHelper(this.order, this.data, this.left, this.right);
  }

  boolean sameTreeHelper(Comparator<T> comp, T data, ABST<T> left, ABST<T> right) {
    return this.order.compare(this.data, data) == 0 && this.left.sameTree(left)
        && this.right.sameTree(right);
  }

  boolean isSame(ABST<T> tree2) {
    return false;
  }

  boolean sameData(ABST<T> tree2) {
    return this.sameDataHelp(tree2) && tree2.sameDataHelp(this);
  }

  boolean sameDataHelp(ABST<T> tree2) {
    return tree2.present(this.data) && this.left.sameDataHelp(tree2)
        && this.right.sameDataHelp(tree2);
  }

  IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }

}

//---------------COMPARE---------------------

class BooksByTitle implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.title.compareTo(t2.title);
  }
}

class BooksByAuthor implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.author.compareTo(t2.author);
  }
}

class BooksByPrice implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.price - t2.price;
  }

}

//------------------BOOK---------------------

class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  /*
   * Fields: this.length ... int this.weight ... int this.color ... Color
   * 
   * Methods: this.totalWeight ... int this.totalHeight ... int this.isBalanced
   * ... boolean this.buildMobile(IMobile other) ... IMobile this.curWidth ... int
   * this.drawMobile(Posn p) ... WorldImage
   * 
   * Methods on Fields:
   * 
   */

}

//------------------LIST---------------------
interface IList<T> {

}

class MtList<T> implements IList<T> {

  MtList() {
  }

}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

}

//------------------EX---------------------

class ExamplesABST {

  // Book Examples
  Book aManCalledOve = new Book("A Man Called Ove", "Fredrik Backman", 15);
  Book theAlchemist = new Book("The Alchemist", "Paulo Coelho", 30);
  Book allTheLightWeCannotSee = new Book("All The Light We Cannot See", "Anthony Doerr", 25);
  Book rogueLawyer = new Book("Rogue Lawyer", "Jake Grisham", 20);
  Book theWhistler = new Book("The Whistler", "John Grisham", 10);
  Book toKillAMockingbird = new Book("To Kill a Mockingbird", "Harper Lee", 20);
  Book meBeforeYou = new Book("Me Before You", "Jojo Moyes", 20);
  Book theNightingale = new Book("The Nightingale", "Kristin Hannah", 15);
  Book twoByTwo = new Book("Two By Two", "Nicholas Sparks", 20);
  Book theGirlOnTheTrain = new Book("The Girl on The Train", "Paula Hawkins", 20);

  // Leaf Examples
  ABST<Book> leaf1 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leaf2 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leaf3 = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> leaf4 = new Leaf<Book>(new BooksByAuthor());

  // Node Examples
  ABST<Book> node1 = new Node<Book>(new BooksByTitle(), this.aManCalledOve, this.leaf1, this.leaf2);
  ABST<Book> node2 = new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee, this.leaf1,
      this.leaf2);
  ABST<Book> node3 = new Node<Book>(new BooksByTitle(), this.meBeforeYou, this.leaf1, this.leaf2);
  ABST<Book> node4 = new Node<Book>(new BooksByTitle(), this.theAlchemist, this.leaf1, this.leaf2);
  ABST<Book> node5 = new Node<Book>(new BooksByTitle(), this.theGirlOnTheTrain, this.leaf1,
      this.leaf2);
  ABST<Book> node6 = new Node<Book>(new BooksByTitle(), this.theNightingale, this.leaf1,
      this.leaf2);
  ABST<Book> node7 = new Node<Book>(new BooksByTitle(), this.toKillAMockingbird, this.leaf1,
      this.leaf2);
  ABST<Book> node8 = new Node<Book>(new BooksByTitle(), this.theWhistler, this.leaf1, this.leaf2);
  ABST<Book> node9 = new Node<Book>(new BooksByTitle(), this.twoByTwo, this.leaf1, this.leaf2);
  ABST<Book> node10 = new Node<Book>(new BooksByTitle(), this.rogueLawyer, this.leaf1, this.leaf2);

  ABST<Book> node11 = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.node1,
      this.leaf1);
  ABST<Book> node11getRight = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf1,
      this.leaf1);
  ABST<Book> node12 = new Node<Book>(new BooksByAuthor(), this.rogueLawyer, this.leaf2, this.node8);
  ABST<Book> node13 = new Node<Book>(new BooksByAuthor(), this.allTheLightWeCannotSee, this.node11,
      this.node12);
  ABST<Book> node13getRight = new Node<Book>(new BooksByAuthor(), this.allTheLightWeCannotSee,
      this.node11getRight, this.node12);
  ABST<Book> node14 = new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.leaf1, this.node5);
  ABST<Book> node15 = new Node<Book>(new BooksByAuthor(), this.theNightingale, this.node3,
      this.node14);
  ABST<Book> node16 = new Node<Book>(new BooksByAuthor(), this.toKillAMockingbird, this.node13,
      this.node15);
  ABST<Book> node16GetRight = new Node<Book>(new BooksByAuthor(), this.toKillAMockingbird,
      this.node13getRight, this.node15);

  //Nodes sorted by Book title
  ABST<Book> node123 = new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee, this.node1,
      this.node3);
  ABST<Book> node123GetRight = new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee,
      this.leaf1, this.node3);
  ABST<Book> node456 = new Node<Book>(new BooksByTitle(), this.theGirlOnTheTrain, this.node4,
      this.node6);
  ABST<Book> node789 = new Node<Book>(new BooksByTitle(), this.theWhistler, this.node7, this.node9);
  ABST<Book> node7to10 = new Node<Book>(new BooksByTitle(), this.rogueLawyer, this.node789,
      this.leaf1);
  ABST<Book> nodeBig = new Node<Book>(new BooksByTitle(), this.theNightingale, this.node123,
      this.node789);
  ABST<Book> nodeBigGetRight = new Node<Book>(new BooksByTitle(), this.theNightingale,
      this.node123GetRight, this.node789);
  ABST<Book> nodeRight = new Node<Book>(new BooksByTitle(), this.theAlchemist, this.leaf1,
      this.node789);
  ABST<Book> nodeRightSame = new Node<Book>(new BooksByTitle(), this.theAlchemist, this.leaf1,
      this.node789);

  // List Examples
  IList<Book> listNode123 = new ConsList<Book>(this.aManCalledOve, new ConsList<Book>(
      this.allTheLightWeCannotSee, new ConsList<Book>(this.meBeforeYou, new MtList<Book>())));

  IList<Book> listNode456 = new ConsList<Book>(this.theAlchemist, new ConsList<Book>(
      this.theGirlOnTheTrain, new ConsList<Book>(this.theNightingale, new MtList<Book>())));

  IList<Book> listNode789 = new ConsList<Book>(this.toKillAMockingbird,
      new ConsList<Book>(this.theWhistler, new ConsList<Book>(this.twoByTwo, new MtList<Book>())));

  IList<Book> listNodeBig = new ConsList<Book>(this.aManCalledOve,
      new ConsList<Book>(this.allTheLightWeCannotSee,
          new ConsList<Book>(this.meBeforeYou,
              new ConsList<Book>(this.theNightingale,
                  new ConsList<Book>(this.toKillAMockingbird, new ConsList<Book>(this.theWhistler,
                      new ConsList<Book>(this.twoByTwo, new MtList<Book>())))))));

  IList<Book> listLeaf1 = new MtList<Book>();

  IList<Book> listNode16 = new ConsList<Book>(this.aManCalledOve,
      new ConsList<Book>(this.theAlchemist,
          new ConsList<Book>(this.allTheLightWeCannotSee,
              new ConsList<Book>(this.rogueLawyer,
                  new ConsList<Book>(this.theWhistler, new ConsList<Book>(this.toKillAMockingbird,
                      new ConsList<Book>(this.meBeforeYou,
                          new ConsList<Book>(this.theNightingale, new ConsList<Book>(this.twoByTwo,
                              new ConsList<Book>(this.theGirlOnTheTrain, this.listLeaf1))))))))));

  // Tests

  // Test BuildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.node123.buildList(), this.listNode123)
        && t.checkExpect(this.node456.buildList(), this.listNode456)
        && t.checkExpect(this.node789.buildList(), this.listNode789)
        && t.checkExpect(this.nodeBig.buildList(), this.listNodeBig)
        && t.checkExpect(this.leaf1.buildList(), this.listLeaf1);
  }

  // Test insert method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.node1.insert(this.theGirlOnTheTrain),
        new Node<Book>(new BooksByTitle(), this.aManCalledOve, this.leaf1,
            new Node<Book>(new BooksByTitle(), this.theGirlOnTheTrain, this.leaf1, this.leaf1)));
  }

  // Test present
  boolean testPresent(Tester t) {
    return t.checkExpect(this.node1.present(this.meBeforeYou), false)
        && t.checkExpect(this.nodeBig.present(this.aManCalledOve), true)
        && t.checkExpect(this.nodeBig.present(this.toKillAMockingbird), true)
        && t.checkExpect(this.nodeBig.present(this.rogueLawyer), false)
        && t.checkExpect(this.node16.present(allTheLightWeCannotSee), true);
  }

  // Test get Leftmost
  boolean testGetLeftMost(Tester t) {
    return t.checkExpect(this.node1.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node123.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node456.getLeftmost(), (this.theAlchemist))
        && t.checkExpect(this.node789.getLeftmost(), (this.toKillAMockingbird))
        && t.checkExpect(this.node16.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.nodeBig.getLeftmost(), (this.aManCalledOve));

  }

  // checking runtime expection for getLeftmost on leaf
  boolean testRuntimeException(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf1,
        "getLeftmost")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf1,
            "getRight");
  }

  boolean testGetRight(Tester t) {
    return t.checkExpect(this.nodeRight.getRight(), this.node789)
        && t.checkExpect(this.node123.getRight(),
            new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee, this.leaf1, this.node3))
        && t.checkExpect(this.node16.getRight(), this.node16GetRight)
        && t.checkExpect(this.nodeBig.getRight(), this.nodeBigGetRight)
        && t.checkExpect(this.node789.getRight(),
            new Node<Book>(new BooksByTitle(), this.theWhistler, this.leaf1, this.node9))
        && t.checkExpect(this.node1.getRight(), this.leaf1);

  }

  boolean testSameTree(Tester t) {
    return t.checkExpect(this.nodeRight.sameTree(this.nodeRightSame), true)
        && t.checkExpect(this.nodeRight.sameTree(this.nodeBig), false)
        && t.checkExpect(this.nodeRight.sameTree(this.leaf1), false)
        && t.checkExpect(this.leaf1.sameTree(this.leaf2), true)
        && t.checkExpect(this.leaf1.sameTree(this.nodeRight), false);
  }

  boolean testSameData(Tester t) {
    return t.checkExpect(this.leaf1.sameData(this.leaf2), true)
        && t.checkExpect(this.nodeRight.sameData(this.nodeRightSame), true);
  }
}
