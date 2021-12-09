// to represent the parties involved in a meeting in the application
interface IParty { }

// to represent a course as a party
class Course implements IParty {
  String name;
  int enrollment; // represents current count
  
  Course(String name, int enrollment) {
    this.name = name;
    this.enrollment = enrollment; 
  }
}

/* fields:
 * this.name ... String
 * this.enrollment ... int
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent a committee as a party
class Committee implements IParty {
  String name;
  String mission; // represents mission of committee
  int members; // represents current count
  
  Committee(String name, String mission, int members) {
    this.name = name;
    this.mission = mission; 
    this.members = members; 
  }
}

/* fields:
 * this.name ... String
 * this.mission ... String
 * this.members ... int
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent a person in a party
class Person implements IParty {
  String name;
  int id; // represents person's ID number assigned by app
  
  Person(String name, int id) {
    this.name = name;
    this.id = id;
  }
}

/* fields:
 * this.name ... String
 * this.id ... int
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent the types of meetings in the application
interface IMeeting { }

// to represent a type of meeting known as a squad
class Squad implements IMeeting {
  IParty from;
  IParty to;
  String name;
  Person owner;
  
  Squad(IParty from, IParty to, String name, Person owner) {
    this.from = from;
    this.to = to;
    this.name = name;
    this.owner = owner;
  }
}

/* fields:
 * this.from ... IParty
 * this.to ... IParty
 * this.name ... String
 * this.owner ... Person
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent a type of meeting known as a zing
class Zing implements IMeeting {
  IParty from;
  IParty to;
  boolean hasWaiting;
  
  Zing(IParty from, IParty to, boolean hasWaiting) {
    this.from = from;
    this.to = to;
    this.hasWaiting = hasWaiting;
  }
}

/* fields:
 * this.from ... IParty
 * this.to ... IParty
 * this.hasWaiting ... boolean
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent examples of parties
class ExamplesMeeting {
  ExamplesMeeting() { }
  
  IParty alice = new Person("Alice", 12345);
  IParty social = new Committee("Social Committee", "Socially Distant Social Activities", 23);
  IParty cs2510 = new Course("Fundies 2", 650);
  IParty jacob = new Person("Jacob", 11268);
  IParty health = new Committee("Health Committee", "Improving Physical and Mental Health", 40);
  IParty phil1145 = new Course("Technology and Human Values", 125);
  
  Person shayna = new Person("Shayna", 10682);
  
  IMeeting squad1 = new Squad(this.alice, this.cs2510, "Squad Un", this.shayna);
  IMeeting squad2 = new Squad(this.jacob, this.phil1145, "Squad Deux", this.shayna);
  IMeeting zing1 = new Zing(this.alice, this.social, false);
  IMeeting zing2 = new Zing(this.jacob, this.health, true);
}

/* Response to Question:
 * I believe the data design could have been improved. The from parties of both zing and squad are
 * both persons and therefore, when creating a meeting, an IParty example cannot be implemented in
 * the IMeeting example (because the term is too broad) if the data type for this.from is coded to 
 * be a Person as the restriction states. An alternative way to approach this would be to define a
 * Person as its own class so that it becomes not "stuck" within the general confines of an IParty.
 */