import tester.Tester;
import java.util.function.Predicate;

//---------------------------DEQUE--------------------------------//

// To represent a Deque
class Deque<T> {
  Sentinel<T> header;

  // zero argument constructor
  Deque() {
    this.header = new Sentinel<T>();
  }

  // sentinel argument constructor
  Deque(Sentinel<T> header) {
    this.header = header;
  }

  // computes number of nodes in deque
  int size() {
    return this.header.size();
  }

  // EFFECT: adds T to the front of this list
  void addAtHead(T t) {
    this.header.addAtHead(t);
  }

  // EFFECT: adds T to the end of this list
  void addAtTail(T t) {
    this.header.addAtTail(t);
  }

  // EFFECT: Removes the first node of a Deque
  T removeFromHead() {
    return this.header.removeFromHead();
  }

  // EFFECT: Removes the last node of a Deque
  T removeFromTail() {
    return this.header.removeFromTail();
  }

  // produces the first node in this Deque that passes the inputted predicate
  ANode<T> find(Predicate<T> pred) {
    return this.header.find(pred);
  }

  // Removes the given node from this Deque
  void removeNode(ANode<T> n) {
    n.removeNodeHelp();
  }

}

//---------------------------ANODE--------------------------------//

// To represent ANode

abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  // counts the number of Nodes in an ANode
  abstract int sizeHelp();

  // helps in removing an ANode
  abstract T removeHelp();

  // produces the first node in this ANode for which the given predicate returns
  // true
  abstract ANode<T> findHelp(Predicate<T> pred);

  // removes the given node in ANode class
  abstract void removeNodeHelp();
}

//---------------------------SENTINEL--------------------------------//

// To represent a sentinel
class Sentinel<T> extends ANode<T> {

  // Zero Argument Constructor
  Sentinel() {
    this.next = this;
    this.prev = this;
  }

  // computes number of nodes in a Deque
  int size() {
    return this.next.sizeHelp();
  }

  // Counts the number of nodes in the sentinel
  int sizeHelp() {
    return 0;
  }

  // EFFECT: Adds node at the beginning of the list
  void addAtHead(T t) {
    new Node<T>(t, this.next, this);
  }

  // EFFECT: Adds node at the end of the list
  void addAtTail(T t) {
    new Node<T>(t, this, this.prev);
  }

  // Removes the first node from the sentinel
  T removeFromHead() {
    return this.next.removeHelp();
  }

  // Removes the last node (last) from the sentinel
  T removeFromTail() {
    return this.prev.removeHelp();
  }

  // Removes the node from a Sentinel
  T removeHelp() {
    throw new RuntimeException("You cannot remove the sentinel!!");
  }

  // Determines the first node in this Sentinel for which the given predicate
  // returns true
  ANode<T> find(Predicate<T> pred) {
    return this.next.findHelp(pred);
  }

  // produces the first node in this Sentinel for which the given predicate
  // returns true
  ANode<T> findHelp(Predicate<T> pred) {
    return this;
  }

  // remove the given node in Sentinel class
  void removeNodeHelp() {
    return;
  }
}

//---------------------------NODE--------------------------------//

// To represent a Node
class Node<T> extends ANode<T> {
  T data;

  // Single Argument Constructor
  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  // Three Argument Constructor
  Node(T data, ANode<T> next, ANode<T> prev) {
    this(data);
    this.next = next;
    this.prev = prev;
    // Updates the Given Nodes to Refer Back to This Node
    // and throws an exception if next or prev are null
    if (next == null || prev == null) {
      throw new IllegalArgumentException("You cannot add a null into the node");
    }
    else {
      next.prev = this;
      prev.next = this;
    }
  }

  // Computes the number of nodes in a Deque
  int sizeHelp() {
    return 1 + this.next.sizeHelp();
  }

  // removes a node
  T removeHelp() {
    prev.next = this.next;
    next.prev = this.prev;
    return this.data; /////////////////////////////// is necessary?
  }

  // produces the first node where the given predicate returns true
  ANode<T> findHelp(Predicate<T> pred) {
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return this.next.findHelp(pred);
    }
  }

  // removes the given node in Node class
  void removeNodeHelp() {
    this.removeHelp();
  }

}

//---------------------------PREDICATE--------------------------------//

// To determine if two strings are equal
class EqualStrings implements Predicate<String> {
  String s1;

  // Main Constructor
  EqualStrings(String s1) {
    this.s1 = s1;
  }

  // tests whether two strings are equal to each other
  public boolean test(String s2) {
    return s1.equals(s2);
  }
}

// to determine if two ints are equal
class EqualInts implements Predicate<Integer> {
  int i1;

  // Main Constructor
  EqualInts(int i1) {
    this.i1 = i1;
  }

  // tests whether two integers are equal to each other
  public boolean test(Integer i2) {
    return i1 == i2;
  }
}

//---------------------------EXAMPLES--------------------------------//

// Examples class
class ExamplesDeque {

  // EXAMPLES:

  // Variable Names

  // Type: String

  ANode<String> node1s;
  ANode<String> node2s;
  ANode<String> node3s;
  ANode<String> node4s;

  Sentinel<String> sentinels;

  Deque<String> deque1;
  Deque<String> deque2;

  // Type: Integer

  ANode<Integer> node1i;
  ANode<Integer> node2i;
  ANode<Integer> node3i;
  ANode<Integer> node4i;
  ANode<Integer> node5i;

  Sentinel<Integer> sentineli;

  Deque<Integer> deque3;
  Deque<Integer> deque4;

  // Initializes data to starting values

  void initData() {

    // Initializing Sentinels ---------------------------

    sentinels = new Sentinel<String>();

    sentineli = new Sentinel<Integer>();

    // Initializing Nodes -------------------------------

    // str next prev

    node1s = new Node<String>("abc", this.sentinels, this.sentinels);

    // sent --- abc --- sent

    node2s = new Node<String>("bcd", this.sentinels, this.node1s);

    // sent --- abc --- bcd --- sent

    node3s = new Node<String>("cde", this.sentinels, this.node2s);

    // sent --- abc --- bcd --- cde --- sent

    node4s = new Node<String>("def", this.sentinels, this.node3s);

    // sent --- abc --- bcd --- cde --- def --- sent

    // int next prev

    node1i = new Node<Integer>(123, this.sentineli, this.sentineli);

    // sent --- 123 --- sent

    node2i = new Node<Integer>(234, this.sentineli, this.node1i);

    // sent --- 123 --- 234 --- sent

    node3i = new Node<Integer>(456, this.sentineli, this.node2i);

    // sent --- 123 --- 234 --- 456 --- sent

    node4i = new Node<Integer>(678, this.sentineli, this.node3i);

    // sent --- 123 --- 234 --- 456 --- 678 --- sent

    node5i = new Node<Integer>(999, this.sentineli, this.node4i);

    // sent --- 123 --- 234 --- 456 --- 678 --- 999 --- sent

    // Initializing Deques ------------------------------

    // The Empty List

    deque1 = new Deque<String>(); // Uses One Argument Constructor

    // sent --- sent

    // List of Strings with the values

    // ("abc", "bcd", "cde", and "def")

    deque2 = new Deque<String>(this.sentinels);

    // sent --- abc --- bcd --- cde --- def --- sent

    // A list with (at least) four values

    // that are not ordered lexicographically

    deque3 = new Deque<Integer>(this.sentineli);

    // sent --- 123 --- 234 --- 456 --- 678 --- sent

    deque4 = new Deque<Integer>();

  }

  // TESTS:

  // size Method

  void testSize(Tester t) {

    this.initData();

    t.checkExpect(this.sentinels.size(), 4);

    t.checkExpect(this.sentineli.size(), 5);

    t.checkExpect(this.deque1.size(), 0);

    t.checkExpect(this.deque2.size(), 4);

    t.checkExpect(this.deque3.size(), 5);

  }

  // addAtTail Method

  void testaddAtTail(Tester t) {

    this.initData();

    this.deque4.addAtTail(123);

    this.deque4.addAtTail(234);

    this.deque4.addAtTail(456);

    this.deque4.addAtTail(678);

    this.deque4.addAtTail(999);

    t.checkExpect(deque4, deque3);

    this.deque1.addAtTail("abc");

    this.deque1.addAtTail("bcd");

    this.deque1.addAtTail("cde");

    this.deque1.addAtTail("def");

    t.checkExpect(deque1, deque2);

  }

  // addAtHead Method

  void testaddAtHead(Tester t) {

    this.initData();

    this.deque4.addAtHead(999);

    this.deque4.addAtHead(678);

    this.deque4.addAtHead(456);

    this.deque4.addAtHead(234);

    this.deque4.addAtHead(123);

    t.checkExpect(deque4, deque3);

    this.deque1.addAtHead("def");

    this.deque1.addAtHead("cde");

    this.deque1.addAtHead("bcd");

    this.deque1.addAtHead("abc");

    t.checkExpect(deque1, deque2);

  }

  // removeFromHead Method

  void testremoveFromHead(Tester t) {

    this.initData();

    this.deque2.removeFromHead();

    this.deque2.removeFromHead();

    this.deque2.removeFromHead();

    this.deque2.removeFromHead();

    t.checkExpect(deque2, deque1);

    this.deque3.removeFromHead();

    this.deque3.removeFromHead();

    this.deque3.removeFromHead();

    this.deque3.removeFromHead();

    this.deque3.removeFromHead();

    t.checkExpect(deque3, deque4);

    t.checkException(new RuntimeException(

        "You cannot remove the sentinel!!"), sentinels, "removeFromHead");

  }

  // removeFromTail Method

  void testremoveFromTail(Tester t) {

    this.initData();

    this.deque2.removeFromTail();

    this.deque2.removeFromTail();

    this.deque2.removeFromTail();

    this.deque2.removeFromTail();

    t.checkExpect(deque2, deque1);

    this.deque3.removeFromTail();

    this.deque3.removeFromTail();

    this.deque3.removeFromTail();

    this.deque3.removeFromTail();

    this.deque3.removeFromTail();

    t.checkExpect(deque3, deque4);

    t.checkException(new RuntimeException(

        "You cannot remove the sentinel!!"), sentinels, "removeFromTail");

  }

  // removeNode Method

  void testremoveNode(Tester t) {

    this.initData();

    this.deque2.removeNode(node4s);

    this.deque2.removeNode(node3s);

    this.deque2.removeNode(node2s);

    this.deque2.removeNode(node1s);

    this.deque3.removeNode(node5i);

    this.deque3.removeNode(node4i);

    this.deque3.removeNode(node3i);

    this.deque3.removeNode(node2i);

    this.deque3.removeNode(node1i);
    

    t.checkExpect(deque2, deque1);

    t.checkExpect(deque3, deque4);

  }
  
  // test removeNode from empty list
  
  void testremoveNodefromEmpty(Tester t) {

    this.initData();
    
    this.deque1.removeNode(node2s);
    
    t.checkExpect(deque1, new Deque<String>());
  }

  // test constructor exception

  void testNodeConstructorException(Tester t) {

    this.initData();

    t.checkConstructorException(new IllegalArgumentException("You cannot add a null into the node"),

        "Node", "abc", null, this.node1i);

    t.checkConstructorException(new IllegalArgumentException("You cannot add a null into the node"),

        "Node", "ghi", this.node1s, null);

    t.checkConstructorException(new IllegalArgumentException("You cannot add a null into the node"),

        "Node", "ghi", null, null);

  }

  // test the method find
  void testFind(Tester t) {
    // initialize
    initData();
    t.checkExpect(this.deque2.find(new EqualStrings("abc")), this.node1s);
    t.checkExpect(this.deque2.find(new EqualStrings("def")), this.node4s);
    t.checkExpect(this.deque2.find(new EqualStrings("sophinilinguini")), this.sentinels);
    t.checkExpect(this.deque1.find(new EqualStrings("burnt chicken nugget")),
        new Sentinel<String>());
    t.checkExpect(this.deque3.find(new EqualInts(123)), this.node1i);
    t.checkExpect(this.deque3.find(new EqualInts(999)), this.node5i);
    t.checkExpect(this.deque3.find(new EqualInts(31415926)), this.sentineli);
    t.checkExpect(this.deque4.find(new EqualInts(1111)), new Sentinel<Integer>());
  }
}