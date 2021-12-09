import tester.Tester;

//-----------------------------LIST-------------------------------//

// To represent a list
interface IList<T> {

  // To determine if the given object is present in this list
  boolean present(Comparator<T> comparator, T t);

  // To determine if any element in this list satisfies the predicate
  boolean ormap(Predicate<T> predicate);

  // To compute how many elements in this list satisfy the predicate
  int count(Predicate<T> predicate);
}

// To represent an empty list
class MtList<T> implements IList<T> {

  // Main Constructor
  MtList() {
  }

  public boolean present(Comparator<T> comparator, T t) {
    return false;
  }

  public boolean ormap(Predicate<T> predicate) {
    return false;
  }

  public int count(Predicate<T> predicate) {
    return 0;
  }

}

// To represent a non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  // Main Constructor
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public boolean present(Comparator<T> comparator, T t) {
    return comparator.compare(this.first, t) || this.rest.present(comparator, t);
  }

  public boolean ormap(Predicate<T> predicate) {
    return predicate.test(this.first) || this.rest.ormap(predicate);
  }

  public int count(Predicate<T> predicate) {
    if (predicate.test(this.first)) {
      return 1 + this.rest.count(predicate);
    }
    else {
      return this.rest.count(predicate);
    }
  }

}

//-----------------------------COURSE-------------------------------//

// To represent a registrar
interface IRegistrar {
}

// To represent a course in the registrar
class Course implements IRegistrar {
  String name;
  Instructor prof;
  IList<Student> students;

  // Main Constructor
  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;
  }

  // Convenience Constructor that takes in course name and professor
  Course(String name, Instructor prof) {
    this(name, prof, new MtList<Student>());
    prof.teaches(this);
  }

  // Convenience Constructor that takes in course name
  Course(String name) {
    this(name, null, new MtList<Student>());
  }

  // Adds the given student to this course's list of students
  void enrollHelper(Student s) {
    this.students = new ConsList<Student>(s, this.students);
  }

  // Denotes the given instructor as the professor for this course
  void teachesHelper(Instructor i) {
    this.prof = i;
  }

}

//-----------------------------INSTRUCTOR-------------------------------//

// To represent an instructor in the registrar
class Instructor implements IRegistrar {
  String name;
  IList<Course> courses;

  // Main Constructor
  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = courses;
  }

  // Convenience Constructor
  Instructor(String name) {
    this(name, new MtList<Course>());
  }

  // Adds the given courses to this instructor's list of courses
  // and denotes this instructor as the course's instructor
  void teaches(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
    c.teachesHelper(this);
  }

  // Determines whether the given student is in more than one
  // of this instructor’s courses
  boolean dejavu(Student c) {
    return this.courses.count(new ContainsStudent(c)) > 1;
  }

}

//-----------------------------STUDENT-------------------------------//

// To represent a student in the registrar
class Student implements IRegistrar {
  String name;
  int id;
  IList<Course> courses;

  // Main Constructor
  Student(String name, int id, IList<Course> courses) {
    this.name = name;
    this.id = id;
    this.courses = courses;
  }

  // Convenience Construct that takes in student name and id
  Student(String name, int id) {
    this(name, id, new MtList<Course>());
  }

  // Adds the given course to this student's list of courses
  // and adds this student to the given course's list of students
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
    c.enrollHelper(this);
  }

  // Determines whether the given student is in any of the
  // same classes as this student
  boolean classmates(Student c) {
    return this.courses.ormap(new ContainsStudent(c));
  }

}

//-----------------------------METHOD OBJECTS-------------------------------//

// To represent a predicate
interface Predicate<T> {
  boolean test(T t);
}

// To represent a method object that determines if a list contains 
// a student
class ContainsStudent implements Predicate<Course> {
  Student student;

  // Main Constructor
  ContainsStudent(Student student) {
    this.student = student;
  }

  // Determines if the given course's list of students contains this student
  public boolean test(Course c) {
    return c.students.present(new EqualStudent(), this.student);
  }
}

//------------------------------------------------------------//

// To represent a comparator of two objects
interface Comparator<T> {
  boolean compare(T t1, T t2);
}

// To represent a method object that determines if two students are the same
class EqualStudent implements Comparator<Student> {

  // Determines if the first given student and the second given student
  // are the same student
  public boolean compare(Student s1, Student s2) {
    return s1.name.equals(s2.name) && s1.id == s2.id;
  }

}

//-----------------------------EXAMPLES-------------------------------//
// To represent examples and tests of the registrar
class ExamplesRegistrar {
  // Students
  Student erik = new Student("erik", 1);
  Student susie = new Student("susie", 2);
  Student shiniquoa = new Student("shiniquoa", 3);
  Student anna = new Student("anna", 4);
  Student shayna = new Student("shayna", 6);
  Student fartguy = new Student("nathan", 7);
  Student leeloo = new Student("leeloo", 8);
  // Instructor
  Instructor ahmed = new Instructor("ahmed");
  Instructor durant = new Instructor("durant");
  Instructor angela = new Instructor("angela");
  Instructor jake = new Instructor("jake");
  Instructor aoun = new Instructor("aoun");
  // Course
  Course fundies = new Course("fundies");
  Course accounting = new Course("accounting");
  Course beekeeping = new Course("beekeeping");
  Course ood = new Course("ood");
  Course gym = new Course("gym");
  Course french = new Course("french");

  IList<Course> emptyCourses = new MtList<Course>();
  IList<Student> emptyStudents = new MtList<Student>();

  IList<Student> fundiesStudents = new ConsList<Student>(this.erik,
      new ConsList<Student>(this.susie, new ConsList<Student>(this.shiniquoa, this.emptyStudents)));

  IList<Student> oodStudents = new ConsList<Student>(this.erik, new ConsList<Student>(
      this.shiniquoa, new ConsList<Student>(this.shayna, this.emptyStudents)));

  IList<Student> accountingStudents = new ConsList<Student>(this.susie, new ConsList<Student>(
      this.shiniquoa, new ConsList<Student>(this.leeloo, this.emptyStudents)));

  IList<Course> angelaCourses = new ConsList<Course>(this.beekeeping,
      new ConsList<Course>(this.french, new ConsList<Course>(this.gym, this.emptyCourses)));

  IList<Course> durantCourses = new ConsList<Course>(this.accounting,
      new ConsList<Course>(this.ood, this.emptyCourses));

  // Initializes data
  void initData() {
    // Students
    this.erik.courses = this.emptyCourses;
    this.susie.courses = this.emptyCourses;
    this.shiniquoa.courses = this.emptyCourses;
    this.anna.courses = this.emptyCourses;
    this.shayna.courses = this.emptyCourses;
    this.fartguy.courses = this.emptyCourses;
    this.leeloo.courses = this.emptyCourses;
    // Professors
    this.ahmed.courses = this.emptyCourses;
    this.durant.courses = this.emptyCourses;
    this.angela.courses = this.emptyCourses;
    this.aoun.courses = this.emptyCourses;
    // Courses
    this.fundies.students = this.emptyStudents;
    this.accounting.students = this.emptyStudents;
    this.beekeeping.students = this.emptyStudents;
    this.ood.students = this.emptyStudents;
    this.gym.students = this.emptyStudents;
    this.french.students = this.emptyStudents;
  }

  void testTeachesHelper(Tester t) {
    this.initData();

    this.fundies.teachesHelper(this.durant);
    this.beekeeping.teachesHelper(this.angela);
    this.gym.teachesHelper(this.aoun);
    this.french.teachesHelper(this.durant);
    this.french.teachesHelper(this.ahmed);

    t.checkExpect(this.fundies.prof, this.durant);
    t.checkExpect(this.beekeeping.prof, this.angela);
    t.checkExpect(this.gym.prof, this.aoun);
    t.checkExpect(this.french.prof, this.ahmed);

  }

  void testClassmates(Tester t) {
    this.initData();

    this.erik.enroll(this.fundies);
    this.erik.enroll(this.ood);
    this.erik.enroll(this.accounting);

    this.fartguy.enroll(this.fundies);
    this.fartguy.enroll(this.ood);
    this.fartguy.enroll(this.beekeeping);

    this.shayna.enroll(this.accounting);
    this.shayna.enroll(this.gym);

    t.checkExpect(this.shayna.classmates(this.erik), true);
    t.checkExpect(this.shayna.classmates(this.fartguy), false);
    t.checkExpect(this.fartguy.classmates(this.erik), true);
    t.checkExpect(this.shayna.classmates(this.leeloo), false);
  }

}
