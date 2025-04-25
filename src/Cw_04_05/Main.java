import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;


// Klasa główna BibliotekaMuzyczna


// Klasa z metodą main
public class Main {
    public static void main(String[] args) {
        BibliotekaMuzyczna biblioteka = new BibliotekaMuzyczna();

        biblioteka.dodajUtwor("Imagine");
        biblioteka.dodajUtwor("Yesterday");
        biblioteka.dodajUtwor("Bohemian Rhapsody");
        biblioteka.dodajUtwor("Imagine");
        biblioteka.dodajUtwor("AC/DC - Back in Black");
        biblioteka.usunUtwor("Imagine");
        
        biblioteka.utworzPlayliste("Kultowe");
        biblioteka.utworzPlayliste("Rock");

        biblioteka.dodajUtworDoPlaylisty("Kultowe", "Imagine");
        biblioteka.dodajUtworDoPlaylisty("Kultowe", "Yesterday");
        biblioteka.dodajUtworDoPlaylisty("Rock", "Bohemian Rhapsody");
        biblioteka.dodajUtworDoPlaylisty("Rock", "AC/DC - Back in Black");

        biblioteka.wyswietlUtwory();
        biblioteka.wyswietlWszystkiePlaylisty();
        biblioteka.wyswietlPlayliste("Rock");
        biblioteka.wyszukajUtwory("day");

        biblioteka.wyswietlUtwory();
        biblioteka.wyswietlPlayliste("Kultowe");
    }
}
class BibliotekaMuzyczna {

    private ArrayList<String> utwory;
    private ArrayList<Playlista> playlisty;

    public BibliotekaMuzyczna() {
        this.utwory = new ArrayList<>();
        this.playlisty = new ArrayList<>();
    }

    public BibliotekaMuzyczna(ArrayList<String> utwory, ArrayList<Playlista> playlisty) {
        this.utwory = utwory != null ? utwory : new ArrayList<>();
        this.playlisty = playlisty != null ? playlisty : new ArrayList<>();
    }

    public void dodajUtwor(String utwor) {
        if (utwor != null && !utwor.isBlank() && !utwory.contains(utwor)) {
            utwory.add(utwor);
        }
    }


    public void usunUtwor(String utwor) {
        utwory.remove(utwor);
        for (Playlista playlista : playlisty) {
            playlista.usunUtwor(utwor);
        }
    }

    public void wyswietlUtwory() {
        System.out.println("Wszystkie utwory:");
        for (String utwor : utwory) {
            System.out.println(utwor);
        }
    }

    public void wyszukajUtwory(String fraza) {
        System.out.println("#####################");
        System.out.println("Utwory zawierające: " + fraza);
        for (String utwor : utwory) {
            if (fraza != null && !fraza.isBlank() && utwor.contains(fraza)) {
                System.out.println(utwor);
                System.out.println("#####################");
            }
        }
    }

    public void utworzPlayliste(String nazwa) {
        if (nazwa == null || nazwa.isBlank()) return;
        if (znajdzPlayliste(nazwa) == null) {
            playlisty.add(new Playlista(nazwa));
        }
    }

    public Playlista znajdzPlayliste(String nazwa) {
        for (Playlista playlista : playlisty) {
            if (playlista.getNazwa().equals(nazwa)) {
                return playlista;
            }
        }
        return null;
    }

    public void dodajUtworDoPlaylisty(String nazwaPlaylisty, String utwor) {
        Playlista playlista = znajdzPlayliste(nazwaPlaylisty);
        if (playlista != null && utwory.contains(utwor)) {
            playlista.dodajUtwor(utwor);
        }
    }

    public void wyswietlPlayliste(String nazwaPlaylisty) {
        Playlista playlista = znajdzPlayliste(nazwaPlaylisty);
        if (playlista != null) {
            playlista.wyswietlPlayliste();
        }
    }

    public void wyswietlWszystkiePlaylisty() {
        System.out.println("Wszystkie playlisty:");
        for (Playlista playlista : playlisty) {
            System.out.println(playlista.getNazwa());
        }
    }
} 
