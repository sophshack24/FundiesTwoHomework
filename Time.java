import tester.Tester;

// To represent time
class Time {
  static Utils u = new Utils();
  /*
   * FIELDS: 
   * this.hour ... int 
   * this.minute ... int 
   * this.second ... int
   * 
   * METHODS: 
   * this.sameTime(Time that) ... boolean
   */
  int hour;
  int minute;
  int second;

  Time(int hour, int minute, int second) {
    this.hour = u.checkRange(hour, 0, 23, "Invalid hour: " + Integer.toString(hour));
    this.minute = u.checkRange(minute, 0, 59, "Invalid minute: " + Integer.toString(minute));
    this.second = u.checkRange(second, 0, 59, "Invalid second: " + Integer.toString(second));
  }

  Time(int hour, int minute) {
    this(hour, minute, 0);
  }

  Time(int hour, int minute, boolean isAM) {
    this(u.convert(u.checkRange(hour, 0, 12, "Invalid hour: " + Integer.toString(hour)), isAM),
        minute);
  }

  // Compares two times and determines if they are the same
  boolean sameTime(Time that) {
    /*
     * Refer to class template
     * 
     * PARAMETERS: 
     * that ... Time
     * 
     * METHODS ON PARAMETERS: 
     * that.sameTime(Time that) ... boolean
     */
    return (this.hour == that.hour && this.minute == that.minute && this.second == that.second);
  }
}

// Acts as a container for functions implemented in constructors 
class Utils {
  
  /*
   * METHODS:
   * checkRange(int val, int min, int max, String msg) ... int
   * convert(int hour, boolean isAM) ... int
   */
  
  // Determines if the given value is in a range of numbers
  int checkRange(int val, int min, int max, String msg) {
    if (val >= min && val <= max) {
      return val;
    }
    else {
      throw new IllegalArgumentException(msg);
    }
  }

  // Converts the hour to the proper value depending on whether its AM or PM
  int convert(int hour, boolean isAM) {
    if (isAM) {
      return hour % 12;
    }
    else {
      return hour % 12 + 12;
    }
  }
}

// To represent examples and tests of time 
class ExamplesTime {
  ExamplesTime() {
  }

  // EXAMPLES
  Time zeroMil = new Time(0, 0, 0);
  Time twelveMil = new Time(12, 0, 0);
  Time sixMil = new Time(6, 0, 0);
  Time sixPM = new Time(6, 0, false);
  Time eighteenMil = new Time(18, 0, 0);
  Time noon = new Time(12, 0, false);
  Time midnight = new Time(12, 0, true);

  Time twelveFifteenAM = new Time(12, 15, true);
  Time twelveFifteenPM = new Time(12, 15, false);
  Time twelveFifteenMil = new Time(12, 15, 0);

  Time example1 = new Time(20, 13, 0);
  Time example1Same = new Time(20, 13, 0);
  Time example1Same2 = new Time(20, 13);
  Time example1Same3 = new Time(8, 13, false);
  Time example1Diff1 = new Time(20, 13, 50);
  Time example1Diff1b = new Time(20, 20, 50);
  Time example1Diff2 = new Time(20, 40);

  Time example2 = new Time(18, 59);
  Time example2Same = new Time(18, 59);
  Time example2Same1 = new Time(18, 59, 0);
  Time example2Same3 = new Time(6, 59, false);
  Time example2Diff1 = new Time(18, 59, 10);
  Time example2Diff2 = new Time(18, 0);
  Time example2Diff2b = new Time(0, 59);

  Time example3 = new Time(11, 7, false);
  Time example3Same = new Time(11, 7, false);
  Time example3Same1 = new Time(23, 7, 0);
  Time example3Same2 = new Time(23, 7);
  Time example3Diff1 = new Time(11, 15, false);
  Time example3Diff1b = new Time(23, 7, 20);
  Time example3Diff2 = new Time(23, 11);

  // TESTS
  
  // sameTime Tests
  boolean testFirstConstructorSameTime(Tester t) {
    return t.checkExpect(this.example1.sameTime(this.example1Same), true)
        && t.checkExpect(this.example1.sameTime(this.example1Same2), true)
        && t.checkExpect(this.example1.sameTime(this.example1Same3), true)
        && t.checkExpect(this.example1.sameTime(this.example1Diff1), false)
        && t.checkExpect(this.example1.sameTime(this.example1Diff1b), false)
        && t.checkExpect(this.example1.sameTime(this.example1Diff2), false);
  }

  boolean testSecondConstructorSameTime(Tester t) {
    return t.checkExpect(this.example2.sameTime(this.example2Same), true)
        && t.checkExpect(this.example2.sameTime(this.example2Same1), true)
        && t.checkExpect(this.example2.sameTime(this.example2Same3), true)
        && t.checkExpect(this.example2.sameTime(this.example2Diff1), false)
        && t.checkExpect(this.example2.sameTime(this.example2Diff2), false)
        && t.checkExpect(this.example2.sameTime(this.example2Diff2b), false);
  }

  boolean testThirdConstructorSameTime(Tester t) {
    return t.checkExpect(this.example3.sameTime(this.example3Same), true)
        && t.checkExpect(this.example3.sameTime(this.example3Same1), true)
        && t.checkExpect(this.example3.sameTime(this.example3Same2), true)
        && t.checkExpect(this.example3.sameTime(this.example3Diff1), false)
        && t.checkExpect(this.example3.sameTime(this.example3Diff1b), false)
        && t.checkExpect(this.example3.sameTime(this.example3Diff2), false);
  }

  boolean testEdgeCasesrSameTime(Tester t) {
    return t.checkExpect(this.zeroMil.sameTime(this.noon), false)
        && t.checkExpect(this.zeroMil.sameTime(this.midnight), true)
        && t.checkExpect(this.twelveMil.sameTime(this.noon), true)
        && t.checkExpect(this.twelveMil.sameTime(this.midnight), false)
        && t.checkExpect(this.sixMil.sameTime(this.sixPM), false)
        && t.checkExpect(this.eighteenMil.sameTime(this.sixPM), true)
        && t.checkExpect(this.twelveFifteenAM.sameTime(this.twelveFifteenPM), false)
        && t.checkExpect(this.twelveFifteenPM.sameTime(this.twelveFifteenMil), true);
  }

  // Utility Tests
  boolean testCheckRange(Tester t) {
    Utils u = new Utils();
    return t.checkExpect(u.checkRange(10, 0, 20, "blerp"), 10)
        && t.checkException(new IllegalArgumentException("boop"), u, "checkRange", 50, 0, 20,
            "boop")
        && t.checkException(new IllegalArgumentException("bop"), u, "checkRange", -100, 0, 20,
            "bop");
  }

  boolean testUtil(Tester t) {
    Utils u = new Utils();
    return t.checkExpect(u.convert(0, true), 0) && t.checkExpect(u.convert(8, true), 8)
        && t.checkExpect(u.convert(12, true), 0) && t.checkExpect(u.convert(14, true), 2)
        && t.checkExpect(u.convert(0, false), 12) && t.checkExpect(u.convert(8, false), 20)
        && t.checkExpect(u.convert(12, false), 12) && t.checkExpect(u.convert(14, false), 14);
  }

  // Exception Tests
  boolean testBadHours(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Invalid hour: -10"), "Time",
        -10, 50, 12)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 24"), "Time", 24,
            11, 13)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: -10"), "Time",
            -10, 25)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 24"), "Time", 24,
            32)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: -10"), "Time",
            -10, 52, true)
        && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 13"), "Time", 13,
            20, false);
  }

  boolean testBadMinutes(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Invalid minute: -20"), "Time",
        5, -20, 7)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 100"), "Time",
            14, 100, 18)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: -20"), "Time",
            15, -20)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 213"), "Time",
            22, 213)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: -20"), "Time",
            8, -20, true)
        && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 116"), "Time",
            10, 116, false);
  }

  boolean 
        && t.checkConstructorException(new IllegalArgumentException("Invalid second: 100"), "Time",
            14, 18, 100);
  }

  /* How does your sameness checker interact with your constructor(s?) for Time? 
   * 
   * Our sameness checker compares the fields of this and that from our main constructor:
   * hour, minute, and second. In this way, each of our constructors and the utility methods they
   * implement are doing most of the work. We used the keyword "this" to invoke the main 
   * constructor of our time class for both the convenience and isAM constructors and therefore,
   * our sameness checker compares these three fields during every test.
   */
  
  
  
}
