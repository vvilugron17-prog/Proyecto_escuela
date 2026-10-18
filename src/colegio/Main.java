package colegio;

import colegio.gestion.Colegio;
import colegio.gestion.Menu;

public class Main {

    public static void main(String[] args) {

        Colegio colegio = new Colegio();
        Menu menu = new Menu(colegio);

        menu.iniciar();
    }
}
