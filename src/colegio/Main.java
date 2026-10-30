package colegio;

// [REQUISITO: Package] [REQUISITO: Menu-Scanner]

import colegio.gestion.Colegio;
import colegio.gestion.DatosPrecargados;
import colegio.gestion.Menu;

public class Main {

    public static void main(String[] args) {
        Colegio colegio = Colegio.cargar();

        if (colegio == null) {
            colegio = new Colegio();
            DatosPrecargados.cargar(colegio);
        }

        Menu menu = new Menu(colegio);
        menu.iniciar();
    }
}
