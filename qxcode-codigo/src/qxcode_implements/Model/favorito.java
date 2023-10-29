package model;

import java.util.ArrayList;

public class favorito {

    ArrayList<Questao> favoritos;

    public favorito(ArrayList<Questao> favoritos) {
        this.favoritos = favoritos;
    }

    public boolean addFavorito(Questao favorito) {
        try {
            favoritos.add(favorito);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFavorito(Questao questao) {
        if (favoritos.contains(questao)) {
            favoritos.remove(questao);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Questao> getFavoritos() {
        return favoritos;
    }

    public Questao getFavorito(int id) {
        for (Questao q : favoritos) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }

}
