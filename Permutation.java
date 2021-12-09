import java.util.*;
import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {

  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  // A copy of original list of characters to be encoded
  ArrayList<Character> alphabet2 = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  // Creates a new list of 26 characters
  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Create a new instance of the encoder/decoder with the given code and a random
  // seed for TESTING
  PermutationCode(ArrayList<Character> code, Random rand) {
    this.code = code;
    this.rand = rand;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    return this.initEncoderHelper(new ArrayList<Character>(), this.alphabet2);
  }

  // Helper function of initEncoder
  ArrayList<Character> initEncoderHelper(ArrayList<Character> permutation,
      ArrayList<Character> alph) {
    if (alph.isEmpty()) {
      return permutation;
    }
    else {
      // get the character at the index then add it
      // to the permutation
      int i = this.rand.nextInt(alph.size());
      permutation.add(alph.get(i));
      alph.remove(i);
      return this.initEncoderHelper(permutation, alph);
    }
  }

  // Abstract Encode and Decode
  String codeAbstraction(String input, ArrayList<Character> l1, ArrayList<Character> l2) {
    if (input.isEmpty()) {
      return "";
    }
    else {
      return (l1.get(l2.indexOf(input.charAt(0))) + codeAbstraction(input.substring(1), l1, l2));
    }
  }

  // Produce an encoded String from the given String
  String encode(String source) {
    return codeAbstraction(source, this.code, this.alphabet);
  }

  // Produce a decoded String from the given String
  String decode(String encoded) {
    return codeAbstraction(encoded, this.alphabet, this.code);
  }
}

// Examples Class
class ExamplesPermutation {
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> alphabet2 = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> alphabet3 = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code1 = new ArrayList<Character>(
      Arrays.asList('z', 'l', 'm', 'n', 'o', 'p', 'c', 'd', 'e', 'f', 'a', 'g', 'j', 'k', 'r', 's',
          'h', 'q', 'i', 't', 'u', 'v', 'w', 'x', 'b', 'y'));

  ArrayList<Character> code2 = new ArrayList<Character>(
      Arrays.asList('h', 'q', 'i', 't', 'u', 'v', 'w', 'z', 'l', 'm', 'd', 'e', 'f', 'a', 'g', 'j',
          'k', 'n', 'o', 'p', 'c', 'r', 's', 'x', 'b', 'y'));

  ArrayList<Character> code3 = new ArrayList<Character>(
      Arrays.asList('h', 'q', 'a', 'g', 'j', 'i', 't', 'u', 'l', 'm', 'd', 'e', 'f', 'v', 'w', 'z',
          'k', 'c', 'r', 's', 'x', 'b', 'y', 'n', 'o', 'p'));

  ArrayList<Character> code3a = new ArrayList<Character>(
      Arrays.asList('p', 'f', 'x', 'n', 'v', 'q', 'w', 'r', 'd', 'i', 'h', 'z', 'b', 't', 'g', 'l',
          'o', 'c', 'y', 's', 'u', 'm', 'e', 'k', 'a', 'j'));

  ArrayList<Character> code4 = new ArrayList<Character>(
      Arrays.asList('s', 'v', 'w', 'm', 'e', 'u', 'o', 'l', 'i', 'y', 'd', 'q', 't', 'n', 'z', 'h',
          'c', 'a', 'g', 'f', 'b', 'x', 'k', 'p', 'r', 'j'));

  ArrayList<Character> code5 = new ArrayList<Character>(
      Arrays.asList('j', 'v', 's', 'g', 'h', 'x', 'l', 'a', 'n', 'w', 'm', 'f', 'd', 't', 'y', 'z',
          'u', 'r', 'c', 'o', 'p', 'i', 'k', 'b', 'q', 'e'));

  ArrayList<Character> code6 = new ArrayList<Character>(
      Arrays.asList('l', 'z', 'w', 't', 'u', 'j', 'i', 'm', 'y', 's', 'h', 'k', 'r', 'f', 'd', 'q',
          'o', 'p', 'a', 'c', 'b', 'x', 'e', 'v', 'n', 'g'));

  ArrayList<Character> code7 = new ArrayList<Character>(
      Arrays.asList('n', 'c', 'w', 't', 'g', 'm', 'p', 'b', 'z', 'y', 'k', 'f', 'e', 'j', 'r', 'h',
          'v', 'l', 'd', 'a', 's', 'o', 'x', 'i', 'q', 'u'));

  String source1 = "hello";
  String source2 = "mynameislilu";
  String source3 = "gronkisthegoat";
  String source4 = "fundiesgodmode";
  String source5 = "ripmysummerbecauseood";

  Random randomSeed = new Random(10);

  PermutationCode permutation1 = new PermutationCode();
  PermutationCode permutation2 = new PermutationCode(this.alphabet);
  PermutationCode permutation3 = new PermutationCode(this.code1);
  PermutationCode permutation4 = new PermutationCode(this.code2);
  PermutationCode permutation5 = new PermutationCode(this.code3);
  PermutationCode permutation6 = new PermutationCode(this.code1, this.randomSeed);
  PermutationCode permutation8 = new PermutationCode(this.alphabet, this.randomSeed);
  PermutationCode permutation9 = new PermutationCode(this.code1, this.randomSeed);
  PermutationCode permutation10 = new PermutationCode(this.code2, this.randomSeed);

  // test initEncoder size
  boolean testInitEncoderSize(Tester t) {
    return t.checkExpect(permutation2.initEncoder().size(), 26)
        && t.checkExpect(permutation3.initEncoder().size(), 26);
  }

  // test initEncoderHelper size
  boolean testInitEncoderHelperSize(Tester t) {
    return t.checkExpect(
        this.permutation1.initEncoderHelper(new ArrayList<Character>(), this.alphabet).size(), 26)
        && t.checkExpect(
            this.permutation2.initEncoderHelper(new ArrayList<Character>(), this.alphabet2).size(),
            26)
        && t.checkExpect(
            this.permutation3.initEncoderHelper(new ArrayList<Character>(), this.alphabet3).size(),
            26);
  }

  // test encode
  void testEncode(Tester t) {
    t.checkExpect(this.permutation3.encode(this.source1), "doggr");
    t.checkExpect(this.permutation4.encode(this.source2), "fbahfuloelec");
    t.checkExpect(this.permutation5.encode(this.source3), "tcwvdlrsujtwhs");
    t.checkExpect(this.permutation3.encode(this.source4), "pukneoicrnjrno");
    t.checkExpect(this.permutation4.encode(this.source5), "nljfbocffunquihcouggt");

  }

  // test decode
  void testDecode(Tester t) {
    t.checkExpect(this.permutation3.decode("doggr"), this.source1);
    t.checkExpect(this.permutation4.decode("fbahfuloelec"), this.source2);
    t.checkExpect(this.permutation5.decode("tcwvdlrsujtwhs"), this.source3);
    t.checkExpect(this.permutation3.decode("pukneoicrnjrno"), this.source4);
    t.checkExpect(this.permutation4.decode("nljfbocffunquihcouggt"), this.source5);
  }

  //test codeAbstraction
  void testcodeAbstraction(Tester t) {
    t.checkExpect(this.permutation3.codeAbstraction("doggr", this.code1, this.alphabet),
        this.source1);
    t.checkExpect(this.permutation4.codeAbstraction("fbahfuloelec", this.code2, this.alphabet),
        this.source2);
    t.checkExpect(this.permutation5.codeAbstraction(this.source3, this.alphabet, this.code3),
        "tcwvdlrsujtwhs");
    t.checkExpect(this.permutation3.codeAbstraction(this.source4, this.alphabet, this.code1),
        "pukneoicrnjrno");
    t.checkExpect(this.permutation4.codeAbstraction(this.source5, this.alphabet, this.code2),
        "nljfbocffunquihcouggt");
  }

  // test initEncoder method
  void testInitEncoder(Tester t) {
    t.checkExpect(permutation8.initEncoder(), this.code3a);
    t.checkExpect(permutation9.initEncoder(), this.code4);
    t.checkExpect(permutation10.initEncoder(), this.code5);
  }

  // test initEncoder helper method
  void testInitEncoderHelepr(Tester t) {
    t.checkExpect(this.permutation8.initEncoderHelper(new ArrayList<Character>(), this.alphabet),
        this.code6);
    t.checkExpect(this.permutation9.initEncoderHelper(new ArrayList<Character>(), this.code1),
        this.code7);
  }

}
