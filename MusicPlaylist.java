import java.util.Scanner;

// This class represents each song
class Song {
    int id; // song ID
    String title; // song title
    String artist; // artist name
    int duration; // duration in seconds

    Song next; // link to the next song
    Song prev; // link to the previous song

    // This constructor creates a new song
    Song(int id, String title, String artist, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
}

public class MusicPlaylist {
    static Song current = null; // the song we are currently on

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // this line lets me get input from the keyboard

        int choice; // variable for user choice

        // this loop keeps asking the user what to do
        do {
            System.out.println("\n1. Add song after current");
            System.out.println("2. Go to next song");
            System.out.println("3. Go to previous song");
            System.out.println("4. Remove current song");
            System.out.println("5. Show current song");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                // when user wants to add a new song
                System.out.print("Enter song ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // to consume the newline after the number

                System.out.print("Enter song title: ");
                String title = scanner.nextLine();

                System.out.print("Enter artist name: ");
                String artist = scanner.nextLine();

                System.out.print("Enter duration in seconds: ");
                int duration = scanner.nextInt();

                Song newSong = new Song(id, title, artist, duration); // create the new song

                if (current == null) {
                    // if playlist is empty, make this the first song
                    current = newSong;
                } else {
                    // link the new song after current song
                    newSong.prev = current;
                    newSong.next = current.next;

                    if (current.next != null) {
                        current.next.prev = newSong;
                    }

                    current.next = newSong;
                    current = newSong; // make the new song the current song
                }

                System.out.println("Song added.");

            } else if (choice == 2) {
                // go to next song
                if (current != null && current.next != null) {
                    current = current.next;
                    System.out.println("Moved to next song.");
                } else {
                    System.out.println("No next song.");
                }

            } else if (choice == 3) {
                // go to previous song
                if (current != null && current.prev != null) {
                    current = current.prev;
                    System.out.println("Moved to previous song.");
                } else {
                    System.out.println("No previous song.");
                }

            } else if (choice == 4) {
                // remove current song
                if (current == null) {
                    System.out.println("No song to remove.");
                } else {
                    if (current.prev != null) {
                        current.prev.next = current.next;
                    }

                    if (current.next != null) {
                        current.next.prev = current.prev;
                    }

                    if (current.next != null) {
                        current = current.next;
                    } else {
                        current = current.prev;
                    }

                    System.out.println("Song removed.");
                }

            } else if (choice == 5) {
                // print current song title and artist
                if (current != null) {
                    System.out.println("Now playing: " + current.title + " by " + current.artist);
                } else {
                    System.out.println("No song is currently selected.");
                }
            }

        } while (choice != 0);

        System.out.println("Exiting playlist...");
    }
}
