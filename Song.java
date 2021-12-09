// to represent a song
class Song {
  String title;
  String artist;
  int year;
  double length;
  
  Song(String title, String artist, int year, double length) {
    this.title = title;
    this.artist = artist;
    this.year = year;
    this.length = length;
  }
}

/* fields:
 * this.title ... String
 * this.artist ... artist
 * this.year ... int
 * this.length ... double
 * 
 * methods:
 * 
 * methods of fields:
 * 
 */

// examples of songs
class ExamplesSong {  
  Song levitating = new Song("Levitating", "Dua Lipa", 2020, 3.5);
  Song rhiannon = new Song("Rhiannon", "Fleetwood Mac", 1975, 4.25);
  Song anchor = new Song("Anchor", "Novo Amor", 2015, 4.2);
}