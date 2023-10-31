package qxcode_implements.Model;
import java.util.ArrayList;

public class Favorite {

    private ArrayList<Questao> favorites;


    public Favorite(ArrayList<Questao> favorites) {
        this.favorites = favorites;
    }

    public boolean addFavorite(Question favorite) {
        try {
            favorites.add(favorite);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFavorite(Question question) {
        if (favorites.contains(question)) {
            favorites.remove(question);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Questao> getFavorites() {
        return favorites;
    }

    public Questao getFavorites(int id) {
        for (Questao q : favorites) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }

}
