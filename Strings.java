import tester.*;

// to represent a list of Strings
interface ILoString {

  // combine all Strings in this list into one
  String combine();

  // sorts this list into alphabetical order, treating all strings as if they 
  // were lower-case
  ILoString sort();

  // inserts the given string into the appropriate place in this list
  ILoString insert(String s);

  // calculates whether this string comes before the given string lexicographically
  //produce value < 0   --- if this String comes before that String
  //produce value zero  --- if this String is the same as that String
  //produce value > 0   --- if this String comes after that String
  int compareTo(String s);

  // determines whether this list is sorted in alphabetical order, in a case-insensitive way
  boolean isSorted();

  // accumulates whether the list has been sorted thus far 
  boolean isSortedHelper(boolean isSorted, String s);

  // calculates the number of elements in the list
  int count();

  // produces a list where the 1st, 3rd, 5th... elements are from this list, and
  // the 2nd, 4th, 6th... elements are from the given list. Any leftover elements
  // (when one list is longer than the other) should just be left at the end
  ILoString interleave(ILoString given);

  // helps interleave this list with the given list using an accumulator of the 
  // interleaved list thus far
  ILoString interleaveHelper(ILoString list, ILoString acc);

  // takes this sorted list of Strings and a given sorted list of Strings, and produces 
  // a sorted list of Strings that contains all items in both original lists, including duplicates
  ILoString merge(ILoString list2);

  // combines this list and the given list into one list
  ILoString combineLists(ILoString acc);

  // produces this list in the reverse order
  ILoString reverse();

  // helps reverse this list using an accumulator of the reversed list thus far
  ILoString reverseAcc(ILoString acc);

  // determines if this list contains pairs of identical strings, that is, 
  // the first and second strings are the same, the third and fourth are the same, etc.
  boolean isDoubledList();

  // determines whether this list contains the same words reading the list in either order
  boolean isPalindromeList();  
}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString() {}
  
  /*TEMPLATE
   * FIELDS:
   * 
   * METHODS:
   * this.combine ... String
   * this.insert(String s) ... ILoString
   * this.sort ... ILoString
   * this.compareTo(String s) ... int
   * this.isSorted ... boolean
   * this.isSortedHelper(boolean isSorted, String s) ... boolean
   * this.interleave(ILoString given) ... ILoString
   * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
   * this.combineLists(ILoString acc) ... ILoString
   * this.count ... int
   * this.merge(ILoString list) ... ILoString
   * this.reverse ... ILoString
   * this.reverseAcc(ILoString acc) ... ILoString
   * this.isDoubledList ... boolean
   * this.isPalidromeList ... boolean
   * 
   * METHODS ON FIELDS:
   * 
   */

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  // inserts the given string into the appropriate place in this list
  public ILoString insert(String s) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    return new ConsLoString(s, this);
  }

  // sorts this list into alphabetical order, treating all strings as if they 
  // were lower-case
  public ILoString sort() {
    return this;
  }

  //calculates whether this string comes before the given string lexicographically
  //produce value < 0   --- if this String comes before that String
  //produce value zero  --- if this String is the same as that String
  //produce value > 0   --- if this String comes after that String
  public int compareTo(String s) {
    /*
     * TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    return this.compareTo(s);
  }

  // determines whether this list is sorted in alphabetical order, in a case-insensitive way
  public boolean isSorted() {
    return true;
  }

  // accumulates whether the list has been sorted thus far 
  public boolean isSortedHelper(boolean isSorted, String s) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * isSorted ... boolean
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    return isSorted;
  }

  // produces a list where the 1st, 3rd, 5th... elements are from this list, and
  // the 2nd, 4th, 6th... elements are from the given list. Any leftover elements
  // (when one list is longer than the other) should just be left at the end
  public ILoString interleave(ILoString given) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * given ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return given;
  }

  // helps interleave this list with the given list using an accumulator of the 
  // interleaved list thus far
  public ILoString interleaveHelper(ILoString list, ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * list ... ILoString
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    if (list.count() == 0) {
      return acc;
    }
    else {
      return list.combineLists(acc);
    }
  }

  // combines this list and the given list into one list
  public ILoString combineLists(ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return acc;
  }

  // calculates the number of elements in the list
  public int count() {
    return 0;
  }

  // takes this sorted list of Strings and a given sorted list of Strings, and produces 
  // a sorted list of Strings that contains all items in both original lists, including duplicates
  public ILoString merge(ILoString list) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * list ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return list;
  }

  // produces this list in the reverse order
  public ILoString reverse() {
    return this;
  }

  // helps reverse this list using an accumulator of the reversed list thus far
  public ILoString reverseAcc(ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return acc;
  }

  // determines if this list contains pairs of identical strings, that is, 
  // the first and second strings are the same, the third and fourth are the same, etc.
  public boolean isDoubledList() {
    return true;
  }

  // determines whether this list contains the same words reading the list in either order
  public boolean isPalindromeList() {
    return true;
  }

}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * TEMPLATE 
   * FIELDS:
   * this.first ... String 
   * this.rest ... ILoString
   * 
   * METHODS
   * this.combine ... String
   * this.insert(String s) ... ILoString
   * this.sort ... ILoString
   * this.compareTo(String s) ... int
   * this.isSorted ... boolean
   * this.isSortedHelper(boolean isSorted, String s) ... boolean
   * this.interleave(ILoString given) ... ILoString
   * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
   * this.combineLists(ILoString acc) ... ILoString
   * this.count ... int
   * this.merge(ILoString list) ... ILoString
   * this.reverse ... ILoString
   * this.reverseAcc(ILoString acc) ... ILoString
   * this.isDoubledList ... boolean
   * this.isPalidromeList ... boolean
   * this.getFirst ... String
   * this.returnRest ... ILoString
   * 
   * METHODS FOR FIELDS
   * this.first.concat(String) ... String
   * this.first.compareTo(String) ... int 
   * this.rest.combine ... String
   * this.rest.insert(String s) ... ILoString
   * this.rest.sort ... ILoString
   * this.rest.compareTo(String s) ... int
   * this.isSorted ... boolean
   * this.rest.isSortedHelper(boolean isSorted, String s) ... boolean
   * this.rest.interleave(ILoString given) ... ILoString
   * this.rest.interleaveHelper(ILoString list, ILoString acc) ... ILoString
   * this.rest.combineLists(ILoString acc) ... ILoString
   * this.rest.count ... int
   * this.rest.merge(ILoString list) ... ILoString
   * this.rest.reverse ... ILoString
   * this.rest.reverseAcc(ILoString acc) ... ILoString
   * this.rest.isDoubledList ... boolean
   * this.rest.isPalidromeList ... boolean
   * this.rest.getFirst ... String
   * this.rest.returnRest ... ILoString
   * 
   */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }

  // calculates whether this string comes before the given string lexicographically
  // produce value < 0   --- if this String comes before that String
  // produce value zero  --- if this String is the same as that String
  // produce value > 0   --- if this String comes after that String
  public int compareTo(String s) {
    /*
     * TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    return this.compareTo(s);
  }

  // sorts this list into alphabetical order, treating all strings as if they 
  // were lower-case
  public ILoString sort() {
    return this.rest.sort().insert(this.first);
  }

  // inserts the given string into the appropriate place in this list
  public ILoString insert(String s) {
    /*
     * TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    if (this.first.toLowerCase().compareTo(s.toLowerCase()) <= 0) {
      return new ConsLoString(this.first, this.rest.insert(s));
    }
    else {
      return new ConsLoString(s, this);
    }
  }

  // determines whether this list is sorted in alphabetical order, in a case-insensitive way
  public boolean isSorted() {
    return this.rest.isSortedHelper(true, this.first);
  }

  // accumulates whether the list has been sorted thus far 
  public boolean isSortedHelper(boolean isSorted, String s) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * isSorted ... boolean
     * s ... String
     * 
     * METHODS ON PARAMETERS:
     * this.first.concat(String) ... String
     * this.first.compareTo(String) ... int 
     */
    return isSorted && this.rest.isSortedHelper(
        isSorted && this.first.toLowerCase().compareTo(s.toLowerCase()) >= 0, this.first);
  }

  // produces a list where the 1st, 3rd, 5th... elements are from this list, and
  // the 2nd, 4th, 6th... elements are from the given list. Any leftover elements
  // (when one list is longer than the other) should just be left at the end
  public ILoString interleave(ILoString given) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * given ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return this.interleaveHelper(given, new MtLoString());
  }

  // helps interleave this list with the given list using an accumulator of the 
  // interleaved list thus far
  public ILoString interleaveHelper(ILoString list, ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * list ... ILoString
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return new ConsLoString(this.first, list.interleaveHelper(this.rest, acc));
  }

  // calculates the number of elements in the list
  public int count() {
    return 1 + this.rest.count();
  }

  // combines this list and the given list into one list
  public ILoString combineLists(ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return new ConsLoString(this.first, this.rest.combineLists(acc));
  }

  // takes this sorted list of Strings and a given sorted list of Strings, and produces 
  // a sorted list of Strings that contains all items in both original lists, including duplicates
  public ILoString merge(ILoString list2) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * list2 ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return this.combineLists(list2).sort();
  }

  // produces this list in the reverse order
  public ILoString reverse() {
    return this.reverseAcc(new MtLoString());
  }

  // helps reverse this list using an accumulator of the reversed list thus far
  public ILoString reverseAcc(ILoString acc) {
    /* TEMPLATE:
     * refer to class template
     * 
     * PARAMETERS:
     * acc ... ILoString
     * 
     * METHODS ON PARAMETERS:
     * this.combine ... String
     * this.insert(String s) ... ILoString
     * this.sort ... ILoString
     * this.compareTo(String s) ... int
     * this.isSorted ... boolean
     * this.isSortedHelper(boolean isSorted, String s) ... boolean
     * this.interleave(ILoString given) ... ILoString
     * this.interleaveHelper(ILoString list, ILoString acc) ... ILoString
     * this.combineLists(ILoString acc) ... ILoString
     * this.count ... int
     * this.merge(ILoString list) ... ILoString
     * this.reverse ... ILoString
     * this.reverseAcc(ILoString acc) ... ILoString
     * this.isDoubledList ... boolean
     * this.isPalidromeList ... boolean
     * this.getFirst ... String
     * this.returnRest ... ILoString
     */
    return this.rest.reverseAcc(new ConsLoString(this.first, acc));
  }

  //determines if this list contains pairs of identical strings, that is, 
  // the first and second strings are the same, the third and fourth are the same, etc.
  public boolean isDoubledList() {
    return this.first.equals(((ConsLoString) this.rest).getFirst())
        && ((ConsLoString) this.rest).returnRest().isDoubledList();
  }

  // returns the first element of this list
  public String getFirst() {
    return this.first;
  }

  // returns the rest of the elements of this list
  public ILoString returnRest() {
    return this.rest;
  }

  // determines whether this list contains the same words reading the list in either order
  public boolean isPalindromeList() {
    return this.reverse().combine().equals(this.combine());
  }
  
}

// to represent examples for lists of strings
class ExamplesStrings {

  // EXAMPLES
  ILoString mary = new ConsLoString("Mary ", new ConsLoString("had ", new ConsLoString("a ",
      new ConsLoString("little ", new ConsLoString("lamb.", new MtLoString())))));

  ILoString empty = new MtLoString();
  ILoString simpleUnsorted = new ConsLoString("soph", new ConsLoString("jt", this.empty));
  ILoString simpleSorted = new ConsLoString("jt", new ConsLoString("soph", this.empty));
  ILoString complexSorted = new ConsLoString("australianshepherd",
      new ConsLoString("Boerboel",
          new ConsLoString("frenchbulldog", new ConsLoString("germanshepherd",
              new ConsLoString("Pug", new ConsLoString("Rottweiler", this.empty))))));
  ILoString complexUnsorted = new ConsLoString("frenchbulldog",
      new ConsLoString("australianshepherd", new ConsLoString("Pug", new ConsLoString("Boerboel",
          new ConsLoString("germanshepherd", new ConsLoString("Rottweiler", this.empty))))));
  ILoString cities = new ConsLoString("boston",
      new ConsLoString("losangeles",
          new ConsLoString("newyorkcity",
              new ConsLoString("paris", new ConsLoString("barcelona", new ConsLoString("rome",
                  new ConsLoString("melbourne", new ConsLoString("lisbon", this.empty))))))));
  ILoString interleaveExample = new ConsLoString("frenchbulldog",
      new ConsLoString("boston",
          new ConsLoString("australianshepherd",
              new ConsLoString("losangeles",
                  new ConsLoString("Pug",
                      new ConsLoString("newyorkcity",
                          new ConsLoString("Boerboel",
                              new ConsLoString("paris",
                                  new ConsLoString("germanshepherd",
                                      new ConsLoString("barcelona", new ConsLoString("Rottweiler",
                                          new ConsLoString("rome", new ConsLoString("melbourne",
                                              new ConsLoString("lisbon", this.empty))))))))))))));
  ILoString interleaveExample2 = new ConsLoString("boston",
      new ConsLoString("frenchbulldog",
          new ConsLoString("losangeles",
              new ConsLoString("australianshepherd",
                  new ConsLoString("newyorkcity", new ConsLoString("Pug",
                      new ConsLoString("paris", new ConsLoString("Boerboel",
                          new ConsLoString("barcelona",
                              new ConsLoString("germanshepherd",
                                  new ConsLoString("rome",
                                      new ConsLoString("Rottweiler", new ConsLoString("melbourne",
                                          new ConsLoString("lisbon", this.empty))))))))))))));
  ILoString mergeExample = new ConsLoString("australianshepherd",
      new ConsLoString("barcelona",
          new ConsLoString("Boerboel", new ConsLoString("boston", new ConsLoString("frenchbulldog",
              new ConsLoString("germanshepherd", new ConsLoString("lisbon",
                  new ConsLoString("losangeles", new ConsLoString("melbourne",
                      new ConsLoString("newyorkcity",
                          new ConsLoString("paris", new ConsLoString("Pug", new ConsLoString("rome",
                              new ConsLoString("Rottweiler", this.empty))))))))))))));

  ILoString complexReverse = new ConsLoString("Rottweiler",
      new ConsLoString("Pug", new ConsLoString("germanshepherd", new ConsLoString("frenchbulldog",
          new ConsLoString("Boerboel", new ConsLoString("australianshepherd", this.empty))))));

  ILoString isDoubledExample = new ConsLoString("a", new ConsLoString("a", new ConsLoString("b",
      new ConsLoString("b", new ConsLoString("c", new ConsLoString("c", this.empty))))));
  ILoString isNotDoubleExample = new ConsLoString("a", new ConsLoString("b",
      new ConsLoString("b", new ConsLoString("c", new ConsLoString("c", this.empty)))));
  ILoString palindromelist = new ConsLoString("m", new ConsLoString("a",
      new ConsLoString("d", new ConsLoString("a", new ConsLoString("m", this.empty)))));

  // TESTS
  boolean testSort(Tester t) {
    return t.checkExpect(this.empty.sort(), this.empty)
        && t.checkExpect(this.simpleUnsorted.sort(), this.simpleSorted)
        && t.checkExpect(this.complexUnsorted.sort(), this.complexSorted);
  }

  boolean testCombine(Tester t) {
    return t.checkExpect(this.empty.combine(), "")
        && t.checkExpect(this.palindromelist.combine(), "madam") && t.checkExpect(
            this.cities.combine(), "bostonlosangelesnewyorkcityparisbarcelonaromemelbournelisbon");
  }

  boolean testIsSorted(Tester t) {
    return t.checkExpect(this.empty.isSorted(), true)
        && t.checkExpect(this.simpleUnsorted.isSorted(), false)
        && t.checkExpect(this.simpleSorted.isSorted(), true)
        && t.checkExpect(this.complexUnsorted.isSorted(), false)
        && t.checkExpect(this.complexSorted.isSorted(), true);
  }

  boolean testInterleave(Tester t) {
    return t.checkExpect(this.empty.interleave(this.complexSorted), this.complexSorted)
        && t.checkExpect(this.complexUnsorted.interleave(this.cities), this.interleaveExample)
        && t.checkExpect(this.cities.interleave(this.complexUnsorted), this.interleaveExample2);
  }

  boolean testMerge(Tester t) {
    return t.checkExpect(this.empty.merge(this.simpleSorted), this.simpleSorted)
        && t.checkExpect(this.complexSorted.merge(this.cities), this.mergeExample);
  }

  boolean testReverse(Tester t) {
    return t.checkExpect(this.empty.reverse(), this.empty)
        && t.checkExpect(this.simpleUnsorted.reverse(), this.simpleSorted)
        && t.checkExpect(this.complexSorted.reverse(), this.complexReverse)
        && t.checkExpect(this.palindromelist.reverse(), this.palindromelist);
  }

  boolean testIsDoubledList(Tester t) {
    return t.checkExpect(this.empty.isDoubledList(), true)
        && t.checkExpect(this.isDoubledExample.isDoubledList(), true);
  }

  boolean testPalindromeList(Tester t) {
    return t.checkExpect(this.palindromelist.isPalindromeList(), true)
        && t.checkExpect(this.simpleUnsorted.isPalindromeList(), false);
  }

  boolean testInsert(Tester t) {
    return t.checkExpect(this.empty.insert("hi"), new ConsLoString("hi", this.empty))
        && t.checkExpect(this.palindromelist.insert("d"),
            new ConsLoString("d", new ConsLoString("m", new ConsLoString("a",
                new ConsLoString("d", new ConsLoString("a", new ConsLoString("m", this.empty)))))));
  }

  boolean testIsSortedHelper(Tester t) {
    return t.checkExpect(this.empty.isSortedHelper(true, "a"), true)
        && t.checkExpect(this.simpleUnsorted.isSortedHelper(true, "derp"), false)
        && t.checkExpect(this.simpleSorted.isSortedHelper(true, "hello"), true)
        && t.checkExpect(this.complexUnsorted.isSortedHelper(false, "hi"), false)
        && t.checkExpect(this.complexSorted.isSortedHelper(true, ""), true);
  }

  boolean testInterleaveHelper(Tester t) {
    return t.checkExpect(this.empty.interleaveHelper(this.complexSorted, this.empty),
        this.complexSorted)
        && t.checkExpect(this.simpleSorted.interleaveHelper(this.simpleSorted, this.simpleUnsorted),
            new ConsLoString("jt",
                new ConsLoString("jt", new ConsLoString("soph", new ConsLoString("soph",
                    new ConsLoString("soph", new ConsLoString("jt", new MtLoString())))))));
  }

  boolean testCombineLists(Tester t) {
    return t.checkExpect(this.empty.combineLists(this.simpleSorted), this.simpleSorted)
        && t.checkExpect(this.simpleSorted.combineLists(this.simpleUnsorted),
            new ConsLoString("jt",
                new ConsLoString("soph",
                    new ConsLoString("soph", new ConsLoString("jt", new MtLoString())))))
        && t.checkExpect(this.cities.combineLists(this.simpleUnsorted), new ConsLoString("boston",
            new ConsLoString("losangeles", new ConsLoString("newyorkcity",
                new ConsLoString("paris", new ConsLoString("barcelona",
                    new ConsLoString("rome", new ConsLoString("melbourne", new ConsLoString(
                        "lisbon",
                        new ConsLoString("soph", new ConsLoString("jt", new MtLoString())))))))))));

  }
  
  boolean testCount(Tester t) {
    return t.checkExpect(this.empty.count(), 0) && t.checkExpect(this.simpleSorted.count(), 2)
        && t.checkExpect(this.palindromelist.count(), 5) && t.checkExpect(this.cities.count(), 8);
  }

  boolean testReverseAcc(Tester t) {
    return t.checkExpect(this.empty.reverseAcc(this.empty), this.empty)
        && t.checkExpect(this.palindromelist.reverseAcc(this.empty), this.palindromelist)
        && t.checkExpect(this.simpleSorted.reverseAcc(new ConsLoString("jt", this.empty)),
            new ConsLoString("soph",
                new ConsLoString("jt", new ConsLoString("jt", new MtLoString()))));
  }

  boolean testGetFirst(Tester t) {
    return t.checkExpect(((ConsLoString) this.simpleSorted).getFirst(), "jt")
        && t.checkExpect(((ConsLoString) this.palindromelist).getFirst(), "m")
        && t.checkExpect(((ConsLoString) this.cities).getFirst(), "boston");
  }

  boolean testReturnRest(Tester t) {
    return t.checkExpect(((ConsLoString) this.simpleSorted).returnRest(),
        new ConsLoString("soph", this.empty))
        && t.checkExpect(((ConsLoString) this.palindromelist).returnRest(),
            new ConsLoString("a",
                new ConsLoString("d", new ConsLoString("a", new ConsLoString("m", this.empty)))))
        && t.checkExpect(((ConsLoString) this.cities).returnRest(),
            new ConsLoString("losangeles",
                new ConsLoString("newyorkcity",
                    new ConsLoString("paris", new ConsLoString("barcelona", new ConsLoString("rome",
                        new ConsLoString("melbourne", new ConsLoString("lisbon", this.empty))))))));
  }

}
