import tester.Tester;
import java.util.Comparator;

//------------------ABST---------------------
// To represent binary search trees
abstract class ABST<T> {

  Comparator<T> order;

  // Main Constructor
  ABST(Comparator<T> order) {
    this.order = order;
  }

  // Produces a new binary search tree with the given item t inserted in the
  // correct place
  abstract ABST<T> insert(T t);

  // Determines whether item t is present in this binary search tree
  abstract boolean present(T t);

  // Returns the leftmost item contained in this binary search tree
  abstract T getLeftmost();

  // Uses an accumulator to return the most leftmost node in this binary search
  // tree
  // when a bottom leaf is reached
  abstract T getLeftMostHelper(T accum);

  // Returns this tree containing all but the leftmost item of this tree
  abstract ABST<T> getRight();

  // Uses the leftmost item of this tree to return the rest of this tree
  abstract ABST<T> removeLeft(T leftmost);

  // Determines whether this binary search tree is the same as the given one:
  // They must have matching structure and matching data in all nodes.
  abstract boolean sameTree(ABST<T> tree2);

  // Uses double dispatch to determine if there are two leafs being compared
  abstract boolean isSame(ABST<T> tree2);

  // Uses two left and right accumulators to determine if two BSTs contain
  // the same data in the same order
  abstract boolean sameTreeHelper(T data, ABST<T> left, ABST<T> right);

  // Determines whether this binary search tree contains the same data
  // in the same order as the given tree.
  abstract boolean sameData(ABST<T> tree2);

  // Determines if the given binary search tree's data is all present in the
  // this binary search tree
  abstract boolean sameDataHelp(ABST<T> tree2);

  // Produces a list of items in this binary search tree in the sorted order.
  abstract IList<T> buildList();

}

//------------------LEAF---------------------

// To represent a leaf (no data) of a binary search tree
class Leaf<T> extends ABST<T> {
  Leaf(Comparator<T> order) {
    super(order);
  }

  // Main Constructor
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

  boolean sameTreeHelper(T data, ABST<T> left, ABST<T> right) {
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

// To represent a node of a binary search tree
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  // Main Constructor
  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

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

  boolean sameTree(ABST<T> tree2) {
    return tree2.sameTreeHelper(this.data, this.left, this.right);
  }

  boolean sameTreeHelper(T data, ABST<T> left, ABST<T> right) {
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

// To compare Books by their titles
class BooksByTitle implements Comparator<Book> {

  // Compares two book titles alphabetically:
  // --> Negative integer if t1 comes before t2
  // --> Zero if t1 is the same as t2
  // --> Positive integer if t1 comes after t2
  public int compare(Book t1, Book t2) {
    return t1.title.compareTo(t2.title);
  }
}

// To compare Books by their authors
class BooksByAuthor implements Comparator<Book> {

  // Compares two book authors alphabetically:
  // --> Negative integer if t1 comes before t2
  // --> Zero if t1 is the same as t2
  // --> Positive integer if t1 comes after t2
  public int compare(Book t1, Book t2) {
    return t1.author.compareTo(t2.author);
  }
}

// To compare Books by their prices
class BooksByPrice implements Comparator<Book> {

  // Compares two book prices numerically:
  // --> Negative integer if t1 < t2
  // --> Zero if t1 = t2
  // --> Positive integer if t1 > t2
  public int compare(Book t1, Book t2) {
    return t1.price - t2.price;
  }

}

//------------------BOOK---------------------

// To represent books
class Book {
  String title;
  String author;
  int price;

  // Main Constructor
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

}

//------------------LIST---------------------

// To represent a generic list
interface IList<T> {
}

// To represent an empty generic list
class MtList<T> implements IList<T> {

  // Main Constructor
  MtList() {
  }

}

// To represent a non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  // Main Constructor
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

}

//------------------EX---------------------

// To represent examples and tests of binary search trees
class ExamplesABST {

  // Book Examples
  Book abcs = new Book("Abc's", "Aaron Rodgers", 8);
  Book allTheLightWeCannotSee = new Book("All The Light We Cannot See", "Anthony Doerr", 9);
  Book aManCalledOve = new Book("A Man Called Ove", "Backman Fredrik", 10);
  Book meBeforeYou = new Book("Me Before You", "Chad Moyes", 11);
  Book rogueLawyer = new Book("Rogue Lawyer", "Erisham Jake", 12);
  Book theAlchemist = new Book("The Alchemist", "Frank Paulo", 13);
  Book theGirlOnTheTrain = new Book("The Girl on The Train", "Gemma Hawkins", 14);
  Book theNightingale = new Book("The Nightingale", "Hannah Kristen", 15);
  Book theWhistler = new Book("The Whistler", "John Grisham", 16);
  Book toKillAMockingbird = new Book("To Kill a Mockingbird", "Lee Harper", 17);
  Book twoByTwo = new Book("Two By Two", "Nicholas Sparks", 20);
  Book youBeforeMe = new Book("You Before Me", "Rachel Moyes", 22);

  // Leaf Examples
  ABST<Book> leaf1 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leaf2 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leaf3 = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> leaf4 = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> leaf5 = new Leaf<Book>(new BooksByPrice());
  ABST<Book> leaf6 = new Leaf<Book>(new BooksByPrice());

  // Node Examples BooksByTitle()
  ABST<Book> node1 = new Node<Book>(new BooksByTitle(),
      this.aManCalledOve, this.leaf1, this.leaf2);
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

  // Nodes sorted by Book title
  ABST<Book> node123 = new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee, this.node1,
      this.node3);
  ABST<Book> node123GetRight = new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee,
      this.leaf1, this.node3);
  ABST<Book> node456 = new Node<Book>(new BooksByTitle(), this.theGirlOnTheTrain, this.node4,
      this.node6);
  ABST<Book> node789 = new Node<Book>(new BooksByTitle(),
      this.theWhistler, this.node7, this.node9);
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
  ABST<Book> nodeLeft = new Node<Book>(new BooksByTitle(),
      this.twoByTwo, this.node123, this.leaf1);
  ABST<Book> nodeLeftSame = new Node<Book>(new BooksByTitle(), this.twoByTwo, this.node123,
      this.leaf1);
  ABST<Book> node123sameData = new Node<Book>(new BooksByTitle(), this.meBeforeYou,
      new Node<Book>(new BooksByTitle(), this.allTheLightWeCannotSee, this.node1, this.leaf1),
      this.leaf2);

  // Node Examples BooksByPrice()
  ABST<Book> node1p = new Node<Book>(new BooksByPrice(), this.aManCalledOve, this.leaf5,
      this.leaf6);
  ABST<Book> node2p = new Node<Book>(new BooksByPrice(), this.allTheLightWeCannotSee, this.leaf5,
      this.leaf6);
  ABST<Book> node3p = new Node<Book>(new BooksByPrice(), this.meBeforeYou, this.leaf5, this.leaf6);
  ABST<Book> node4p = new Node<Book>(new BooksByPrice(),
      this.theAlchemist, this.leaf5, this.leaf6);
  ABST<Book> node5p = new Node<Book>(new BooksByPrice(), this.theGirlOnTheTrain, this.leaf5,
      this.leaf6);
  ABST<Book> node6p = new Node<Book>(new BooksByPrice(), this.theNightingale, this.leaf5,
      this.leaf6);
  ABST<Book> node7p = new Node<Book>(new BooksByPrice(), this.toKillAMockingbird, this.leaf5,
      this.leaf6);
  ABST<Book> node8p = new Node<Book>(new BooksByPrice(),
      this.theWhistler, this.leaf5, this.leaf6);
  ABST<Book> node9p = new Node<Book>(new BooksByPrice(), this.twoByTwo, this.leaf5, this.leaf6);
  ABST<Book> node10p = new Node<Book>(new BooksByPrice(),
      this.rogueLawyer, this.leaf5, this.leaf6);

  // Nodes sorted by Book price
  ABST<Book> node123p = new Node<Book>(new BooksByPrice(),
      this.allTheLightWeCannotSee, this.node1p, this.node3p);
  ABST<Book> node123GetRightp = new Node<Book>(new BooksByPrice(), this.allTheLightWeCannotSee,
      this.leaf5, this.node3p);
  ABST<Book> node456p = new Node<Book>(new BooksByPrice(), this.theGirlOnTheTrain, this.node4p,
      this.node6p);
  ABST<Book> node789p = new Node<Book>(new BooksByPrice(), this.theWhistler, this.node7p,
      this.node9p);
  ABST<Book> node7to10p = new Node<Book>(new BooksByPrice(), this.rogueLawyer, this.node789p,
      this.leaf5);
  ABST<Book> nodeBigp = new Node<Book>(new BooksByPrice(), this.theNightingale, this.node123p,
      this.node789p);
  ABST<Book> nodeBigGetRightp = new Node<Book>(new BooksByPrice(), this.theNightingale,
      this.node123GetRightp, this.node789p);
  ABST<Book> nodeRightp = new Node<Book>(new BooksByPrice(), this.theAlchemist, this.leaf5,
      this.node789p);
  ABST<Book> nodeRightSamep = new Node<Book>(new BooksByPrice(), this.theAlchemist, this.leaf5,
      this.node789p);
  ABST<Book> nodeLeftp = new Node<Book>(new BooksByPrice(), this.twoByTwo, this.node123p,
      this.leaf5);
  ABST<Book> nodeLeftSamep = new Node<Book>(new BooksByPrice(), this.twoByTwo, this.node123p,
      this.leaf5);

  // Node Examples BooksByAuthor()
  ABST<Book> node1a = new Node<Book>(new BooksByAuthor(), this.aManCalledOve, this.leaf3,
      this.leaf4);
  ABST<Book> node2a = new Node<Book>(new BooksByAuthor(), this.allTheLightWeCannotSee, this.leaf3,
      this.leaf4);
  ABST<Book> node3a = new Node<Book>(new BooksByAuthor(), this.meBeforeYou, this.leaf3, this.leaf4);
  ABST<Book> node4a = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf3,
      this.leaf4);
  ABST<Book> node5a = new Node<Book>(new BooksByAuthor(), this.theGirlOnTheTrain, this.leaf3,
      this.leaf4);
  ABST<Book> node6a = new Node<Book>(new BooksByAuthor(), this.theNightingale, this.leaf3,
      this.leaf4);
  ABST<Book> node7a = new Node<Book>(new BooksByAuthor(), this.toKillAMockingbird, this.leaf3,
      this.leaf4);
  ABST<Book> node8a = new Node<Book>(new BooksByAuthor(),
      this.theWhistler, this.leaf3, this.leaf4);
  ABST<Book> node9a = new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.leaf3, this.leaf4);
  ABST<Book> node10a = new Node<Book>(new BooksByAuthor(), this.rogueLawyer, this.leaf3,
      this.leaf4);

  // Nodes sorted by Book Author
  ABST<Book> node123a = new Node<Book>(new BooksByAuthor(), this.allTheLightWeCannotSee,
      this.node1a, this.node3a);
  ABST<Book> node123GetRighta = new Node<Book>(new BooksByAuthor(), this.allTheLightWeCannotSee,
      this.leaf3, this.node3a);
  ABST<Book> node456a = new Node<Book>(new BooksByAuthor(), this.theGirlOnTheTrain, this.node4a,
      this.node6a);
  ABST<Book> node789a = new Node<Book>(new BooksByAuthor(), this.theWhistler, this.node7a,
      this.node9a);
  ABST<Book> node7to10a = new Node<Book>(new BooksByAuthor(), this.rogueLawyer, this.node789a,
      this.leaf3);
  ABST<Book> nodeBiga = new Node<Book>(new BooksByAuthor(), this.theNightingale, this.node123a,
      this.node789a);
  ABST<Book> nodeBigGetRighta = new Node<Book>(new BooksByAuthor(), this.theNightingale,
      this.node123GetRighta, this.node789a);
  ABST<Book> nodeRighta = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf3,
      this.node789a);
  ABST<Book> nodeRightSamea = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf3,
      this.node789a);
  ABST<Book> nodeLefta = new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.node123a,
      this.leaf3);
  ABST<Book> nodeLeftSamea = new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.node123,
      this.leaf3);

  ABST<Book> node11 = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.node1,
      this.leaf1);
  ABST<Book> node11getRight = new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf1,
      this.leaf1);
  ABST<Book> node12 = new Node<Book>(new BooksByAuthor(),
      this.rogueLawyer, this.leaf2, this.node8);
  ABST<Book> node13 = new Node<Book>(new BooksByAuthor(),
      this.allTheLightWeCannotSee, this.node11,
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

  IList<Book> listNode1 = new ConsList<Book>(this.aManCalledOve, this.listLeaf1);

  IList<Book> listNodeRight = new ConsList<Book>(this.theAlchemist,
      new ConsList<Book>(this.toKillAMockingbird,
          new ConsList<Book>(this.theWhistler, new ConsList<Book>(this.twoByTwo, 
              this.listLeaf1))));

  IList<Book> listNodeLeft = new ConsList<Book>(this.aManCalledOve, new ConsList<Book>(
      this.allTheLightWeCannotSee, new ConsList<Book>(this.twoByTwo, new MtList<Book>())));

  // Tests

  // Test BuildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.node123.buildList(), this.listNode123)
        // Two nodes on each side
        && t.checkExpect(this.nodeRight.buildList(), this.listNodeRight)
        // Leaf on left and node on right
        && t.checkExpect(this.nodeLeft.buildList(), this.listNodeLeft)
        // Leaf on right and node on left
        && t.checkExpect(this.leaf1.buildList(), this.listLeaf1)
        // Leaf
        && t.checkExpect(this.node1.buildList(), this.listNode1);
    // Node with leaf on each side
  }

  // Test insert method
  boolean testInsertTitle(Tester t) {
    return t.checkExpect(this.node1.insert(this.theGirlOnTheTrain), 
        // Node with leaf on each side
        new Node<Book>(new BooksByTitle(), this.aManCalledOve, this.leaf1,
            new Node<Book>(new BooksByTitle(), this.theGirlOnTheTrain, this.leaf1, this.leaf1)))
        && t.checkExpect(this.leaf1.insert(this.theAlchemist), 
            // Leaf
            new Node<Book>(new BooksByTitle(), this.theAlchemist, this.leaf1, this.leaf2))
        && t.checkExpect(this.nodeRight.insert(this.aManCalledOve), 
            new Node<Book>(new BooksByTitle(), this.theAlchemist, this.node1,
                this.node789))
        // Leaf on left and node on right, insert on left         
        && t.checkExpect(this.nodeRight.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByTitle(),
            this.theAlchemist, this.leaf1,
            new Node<Book>(new BooksByTitle(), this.theWhistler, this.node7,
                new Node<Book>(new BooksByTitle(), this.twoByTwo, this.leaf1,
                    new Node<Book>(new BooksByTitle(), 
                        this.youBeforeMe, this.leaf1, this.leaf2)))))
        && t.checkExpect(this.nodeLeft.insert(this.youBeforeMe),
            new Node<Book>(new BooksByTitle(), this.twoByTwo, this.node123,
                new Node<Book>(new BooksByTitle(), this.youBeforeMe, this.leaf1,
                this.leaf1))) 
        // Leaf on right and node on left
        && t.checkExpect(this.node123.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByTitle(),
                this.allTheLightWeCannotSee, 
                this.node1, new Node<Book>(new BooksByTitle(), this.meBeforeYou, this.leaf1, 
                    new Node<Book>(new BooksByTitle(), 
                        this.youBeforeMe, this.leaf1, this.leaf1)))); 
    // Two nodes on each side
  }
    
  // Test insert method
  boolean testInsertAuthor(Tester t) {
    return t.checkExpect(this.node1a.insert(this.theGirlOnTheTrain), 
        // Node with leaf on each side
        new Node<Book>(new BooksByAuthor(), this.aManCalledOve, this.leaf3,
            new Node<Book>(new BooksByAuthor(), this.theGirlOnTheTrain, this.leaf3, this.leaf3)))
        && t.checkExpect(this.leaf3.insert(this.theAlchemist), 
            // Leaf 
            new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf3, this.leaf3))
        && t.checkExpect(this.nodeRighta.insert(this.aManCalledOve), 
            new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.node1a,
                this.node789a))// Leaf on left and node on right, insert on left
        && t.checkExpect(this.nodeRighta.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByAuthor(), this.theAlchemist, this.leaf3,
            new Node<Book>(new BooksByAuthor(), this.theWhistler, this.node7a,
                new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.leaf3,
                    new Node<Book>(new BooksByAuthor(),
                        this.youBeforeMe, this.leaf3, this.leaf3)))))
        && t.checkExpect(this.nodeLefta.insert(this.youBeforeMe),
            new Node<Book>(new BooksByAuthor(), this.twoByTwo, this.node123a,
                new Node<Book>(new BooksByAuthor(), this.youBeforeMe, this.leaf3,
                this.leaf3))) // Leaf on right and node on left
        && t.checkExpect(this.node123a.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByAuthor(),
                this.allTheLightWeCannotSee, 
                this.node1a, new Node<Book>(new BooksByAuthor(), this.meBeforeYou, this.leaf3, 
                    new Node<Book>(new BooksByAuthor(), 
                        this.youBeforeMe, this.leaf3, this.leaf4)))); // Two nodes on each side
  }
  
  // Test insert method
  boolean testInsertPrice(Tester t) {
    return t.checkExpect(this.node1p.insert(this.theGirlOnTheTrain), 
        // Node with leaf on each side
        new Node<Book>(new BooksByPrice(), this.aManCalledOve, this.leaf5,
            new Node<Book>(new BooksByPrice(),
                this.theGirlOnTheTrain, this.leaf5, this.leaf5)))
        && t.checkExpect(this.leaf5.insert(this.theAlchemist), // Leaf 
            new Node<Book>(new BooksByPrice(), 
                this.theAlchemist, this.leaf5, this.leaf5))
        && t.checkExpect(this.nodeRightp.insert(this.aManCalledOve), 
            new Node<Book>(new BooksByPrice(), this.theAlchemist, this.node1p,
                this.node789p)) // Leaf on left and node on right, insert on left
        && t.checkExpect(this.nodeRightp.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByPrice(), this.theAlchemist, this.leaf5,
            new Node<Book>(new BooksByPrice(), this.theWhistler, this.node7p,
                new Node<Book>(new BooksByPrice(), this.twoByTwo, this.leaf5,
                    new Node<Book>(new BooksByPrice(),
                        this.youBeforeMe, this.leaf5, this.leaf5)))))
        && t.checkExpect(this.nodeLeftp.insert(this.youBeforeMe),
            new Node<Book>(new BooksByPrice(), this.twoByTwo, this.node123p,
                new Node<Book>(new BooksByPrice(), this.youBeforeMe, this.leaf5,
                this.leaf5))) // Leaf on right and node on left
        && t.checkExpect(this.node123p.insert(this.youBeforeMe), 
            new Node<Book>(new BooksByPrice(),
                this.allTheLightWeCannotSee, 
                this.node1p, new Node<Book>(new BooksByPrice(), this.meBeforeYou, this.leaf5, 
                    new Node<Book>(new BooksByPrice(), 
                        this.youBeforeMe, this.leaf5, this.leaf5)))); // Two nodes on each side
  }
  

  // Test present
  boolean testPresent(Tester t) {
    return t.checkExpect(this.node1.present(this.meBeforeYou), false)
        && t.checkExpect(this.nodeBig.present(this.aManCalledOve), true)
        && t.checkExpect(this.nodeBig.present(this.toKillAMockingbird), true)
        && t.checkExpect(this.node123.present(this.rogueLawyer), false)
        && t.checkExpect(this.node16.present(allTheLightWeCannotSee), true)
        && t.checkExpect(this.leaf1.present(allTheLightWeCannotSee), false)
        && t.checkExpect(this.node1a.present(this.meBeforeYou), false)
        && t.checkExpect(this.nodeBiga.present(this.aManCalledOve), true)
        && t.checkExpect(this.nodeBiga.present(this.toKillAMockingbird), true)
        && t.checkExpect(this.node123a.present(this.rogueLawyer), false)
        && t.checkExpect(this.leaf3.present(allTheLightWeCannotSee), false)
        && t.checkExpect(this.node1p.present(this.meBeforeYou), false)
        && t.checkExpect(this.nodeBigp.present(this.aManCalledOve), true)
        && t.checkExpect(this.nodeBigp.present(this.toKillAMockingbird), true)
        && t.checkExpect(this.node123p.present(this.rogueLawyer), false)
        && t.checkExpect(this.leaf5.present(allTheLightWeCannotSee), false);
  }


  // Test get Leftmost
  boolean testGetLeftMost(Tester t) {
    return t.checkExpect(this.node1.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node123.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node456.getLeftmost(), (this.theAlchemist))
        && t.checkExpect(this.node789.getLeftmost(), (this.toKillAMockingbird))
        && t.checkExpect(this.node16.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node1a.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node123a.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node456a.getLeftmost(), (this.theAlchemist))
        && t.checkExpect(this.node789a.getLeftmost(), (this.toKillAMockingbird))
        && t.checkExpect(this.nodeBiga.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node1p.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node123p.getLeftmost(), (this.aManCalledOve))
        && t.checkExpect(this.node456p.getLeftmost(), (this.theAlchemist))
        && t.checkExpect(this.node789p.getLeftmost(), (this.toKillAMockingbird))
        && t.checkExpect(this.nodeBigp.getLeftmost(), (this.aManCalledOve));
  }


  // checking runtime exception for getLeftmost on leaf
  boolean testRuntimeException(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf1,
        "getLeftmost")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf1,
            "getRight");
  }

  boolean testGetRight(Tester t) {
    return t.checkExpect(this.nodeRight.getRight(), this.node789)
        && t.checkExpect(this.node123.getRight(),
            new Node<Book>(new BooksByTitle(),
                this.allTheLightWeCannotSee, this.leaf1, this.node3))
        && t.checkExpect(this.node16.getRight(), this.node16GetRight)
        && t.checkExpect(this.node789.getRight(),
            new Node<Book>(new BooksByTitle(), this.theWhistler, this.leaf1, this.node9))
        && t.checkExpect(this.node1.getRight(), this.leaf1)
        && t.checkExpect(this.nodeRighta.getRight(), this.node789a)
        && t.checkExpect(this.node123a.getRight(),
            new Node<Book>(new BooksByAuthor(),
                this.allTheLightWeCannotSee, this.leaf3, this.node3a))
        && t.checkExpect(this.nodeBiga.getRight(), this.nodeBigGetRighta)
        && t.checkExpect(this.node789a.getRight(),
            new Node<Book>(new BooksByAuthor(), this.theWhistler, this.leaf3, this.node9a))
        && t.checkExpect(this.node1p.getRight(), this.leaf5)
        && t.checkExpect(this.nodeRightp.getRight(), this.node789p)
        && t.checkExpect(this.node123p.getRight(), 
            new Node<Book>(new BooksByPrice(),
                this.allTheLightWeCannotSee, this.leaf5, this.node3p))
        && t.checkExpect(this.nodeBigp.getRight(), this.nodeBigGetRightp)
        && t.checkExpect(this.node789p.getRight(),
            new Node<Book>(new BooksByPrice(), this.theWhistler, this.leaf5, this.node9p))
        && t.checkExpect(this.node1p.getRight(), this.leaf5);

  }

  boolean testSameTree(Tester t) { 
    return t.checkExpect(this.nodeRight.sameTree(this.nodeRightSame), true) 
        //testing two equal right trees
        && t.checkExpect(this.nodeRight.sameTree(this.nodeBig), false) 
        //two inequal trees
        && t.checkExpect(this.nodeRight.sameTree(this.leaf1), false) 
        // testing complex node with leaf
        && t.checkExpect(this.leaf1.sameTree(this.leaf2), true) 
        // testing leaf with leaf
        && t.checkExpect(this.leaf1.sameTree(this.nodeRight), false) 
        // testing leaf with complex node
        && t.checkExpect(this.nodeRighta.sameTree(this.nodeRightp), true) 
        // testing same complex nodes with different comparators
        && t.checkExpect(this.nodeLeft.sameTree(this.nodeLeftSame), true) 
        //testing two equal left trees
        && t.checkExpect(this.nodeLeft.sameTree(this.leaf1), false) 
        // testing a left complex node with a leaf
        && t.checkExpect(this.node123.sameTree(this.node123sameData), false);
    // testing trees with different structures but same data
  }
  
  boolean testSameData(Tester t) {
    return t.checkExpect(this.leaf1.sameData(this.leaf2), true) 
        // test leaf with leaf
        && t.checkExpect(this.nodeRight.sameData(this.nodeRightSame), true) 
        // testing two structurally equal trees
        && t.checkExpect(this.leaf3.sameData(this.leaf5), true) 
        // testing two leafs with different comparators
        && t.checkExpect(this.node123.sameData(this.node123a), true) 
        //testing two complex nodes with different comparators
        && t.checkExpect((this.node123.sameData(this.node123sameData)), true);
    // testing trees with different structures but same data
  }
  
  boolean testSameDataHelp(Tester t) { 
    return t.checkExpect(this.leaf1.sameDataHelp(this.leaf2), true) 
        // test leaf with leaf
        && t.checkExpect(this.nodeRight.sameDataHelp(this.nodeRightSame), true) 
        // testing two structurally equal trees
        && t.checkExpect(this.leaf3.sameDataHelp(this.leaf5), true) 
        // testing two leafs with different comparators
        && t.checkExpect(this.node123.sameDataHelp(this.node123a), true)
        //testing two complex nodes with different comparators
        && t.checkExpect((this.node123.sameDataHelp(this.node123sameData)), true);
    // testing trees with different structures but same data
  }
  
  boolean getLeftMostHelper(Tester t) {
    return t.checkExpect(this.node1.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node123.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node456.getLeftMostHelper(this.theAlchemist), (this.theAlchemist))
        && t.checkExpect(this.node789.getLeftMostHelper(this.toKillAMockingbird),
            (this.toKillAMockingbird))
        && t.checkExpect(this.node16.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.nodeBig.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node1a.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node123a.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node456a.getLeftMostHelper(this.theAlchemist), (this.theAlchemist))
        && t.checkExpect(this.node789a.getLeftMostHelper(this.toKillAMockingbird),
            (this.toKillAMockingbird))
        && t.checkExpect(this.nodeBiga.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node1p.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node123p.getLeftMostHelper(this.aManCalledOve), (this.aManCalledOve))
        && t.checkExpect(this.node456p.getLeftMostHelper(this.theAlchemist), (this.theAlchemist))
        && t.checkExpect(this.node789p.getLeftMostHelper(this.toKillAMockingbird),
            (this.toKillAMockingbird))
        && t.checkExpect(this.nodeBigp.getLeftMostHelper(this.aManCalledOve),
            (this.aManCalledOve));
  }

  boolean testRemoveLeft(Tester t) {
    return t.checkExpect(this.node1.removeLeft(this.node1.getLeftmost()), this.leaf1)
        && t.checkExpect(this.node123.removeLeft(this.node123.getLeftmost()),
            this.node123.getRight())
        && t.checkExpect(this.nodeBig.removeLeft(this.nodeBig.getLeftmost()),
            this.nodeBig.getRight());
  }
  
  boolean testisSame(Tester t) {
    return t.checkExpect(this.nodeRight.isSame(this.nodeRightSame), false)
        //testing two equal right trees
        && t.checkExpect(this.nodeRight.isSame(this.nodeBig), false)
        //two inequal trees
        && t.checkExpect(this.nodeRight.isSame(this.leaf1), false)
        // testing complex node with leaf
        && t.checkExpect(this.leaf1.isSame(this.leaf2), true);
    // testing leaf with leaf 
  }
  
  boolean testSameTreeHelper(Tester t) {
    return t.checkExpect(this.leaf1.sameTreeHelper(
        this.aManCalledOve, this.leaf1, this.leaf2), false)
        && t.checkExpect(
            this.node123.sameTreeHelper(
                this.allTheLightWeCannotSee, this.node1, this.node2), false)
        && t.checkExpect(this.node123.sameTreeHelper(
            this.aManCalledOve, this.node1, this.node2), false);
  }
  
}

