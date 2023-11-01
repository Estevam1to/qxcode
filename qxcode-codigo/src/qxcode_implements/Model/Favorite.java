package qxcode_implements.Model;
import java.util.ArrayList;

public class Favorite {

    private ArrayList<Question> favorites;


    public Favorite(ArrayList<Question> favorites) {
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

    public ArrayList<Question> getFavorites() {
        return favorites;
    }

    public Question getFavorites(int id) {
        for (Question q : favorites) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }

}
