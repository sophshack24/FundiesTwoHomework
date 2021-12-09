// to represent a soup
interface ISoup {}

// to represent a broth as a soup
class Broth implements ISoup { 
  String type;
  
  Broth(String type) {
    this.type = type;
  }
}

/* fields:
 * this.type ... String
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// to represent an ingredient as a soup
class Ingredient implements ISoup { 
  ISoup more;
  String name;
  
  Ingredient(ISoup more, String name) {
    this.more = more;
    this.name = name;
  }
}

/* fields:
 * this.more ... Soup
 * this.name ... String
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// examples of soup
class ExamplesSoup {
  ISoup chicken = new Broth("chicken");
  ISoup carrots = new Ingredient(this.chicken, "carrots");
  ISoup celery = new Ingredient(this.carrots, "celery");  
  ISoup yummy = new Ingredient(this.celery, "noodles");
  
  ISoup vanilla = new Broth("vanilla");
  ISoup horseradish = new Ingredient(this.vanilla, "horseradish");
  ISoup hotDogs = new Ingredient(this.horseradish, "hot dogs");
  ISoup noThankYou = new Ingredient(this.hotDogs, "plum sauce");
}