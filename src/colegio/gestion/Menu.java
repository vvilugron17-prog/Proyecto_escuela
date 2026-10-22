package colegio.gestion;
import java.util.Scanner;

import colegio.modelo.Persona;
import colegio.modelo.Profesor;
import colegio.modelo.Alumno;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;

public class Menu {
    private static final long serialVersionOID = 1L;
    // [REQUISITO: Persistencia]
    

    private Colegio colegio;

    public Menu(Colegio colegio) {
        this.colegio = colegio;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do{
            System.out.println("------------MENU--------------");
            System.out.println("1. Crear un nuevo curso");
            System.out.println("2. Ingresar un nuevo alumno");
            System.out.println("3. Ingresar un nuevo profesor ");
            System.out.println("4. Mostrar cursos creados");
            System.out.println("5. Mostrar alumnos ingresados");
            System.out.println("6. Mostrar profesores ingresados");
            System.out.println("7. Gestionar los cursos ya creados");
            System.out.println("9. Búsqueda de alumno por ID");
            System.out.println("10. Búsqueda de profesor por ID");
            System.out.println("11. Mostrar Ranking");
            System.out.println("12. Mostrar Historial");
            System.out.println("0. Salir y guardar");
            System.out.println("//// Ingrese una opción:");
            String opcion = 
        }
        
        try{
            opcion = Integer.parseInt(sc.nextLine()); //Tomará como valor lo que coloque el usuario en la última línea 
            
            switch(opcion){
                
                case 1:
                    //codigo
                    break;
                case 2:
                    //codigo
                    break;
                case 3:
                    //codigo
                    break;
                case 4:
                    //codigo
                    break;
                case 5:
                    //codigo
                    break;
                case 6:
                    //codigo
                    break;
                case 7:
                    //codigo
                    break;
                case 8:
                    //codigo
                    break;
                case 9:
                    //codigo
                    break;
                case 10:
                    //codigo
                    break;
                case 11:
                    //codigo
                    break;
                case 12:
                    //codigo
                    break;
                    
            }
        }
        
        catch (NumberFormatException e){
            System.out.println("Dato inválido, por favor ingrese un número válido");
            
        }
        
        while (opcion != 0);
        
        sc.close();
        
        
    }
  
    public void crearNuevoCurso(){
        System.out.println("-------BIENVENIDO A LA CREACION DE CURSOS-------");
        System.out.println("///Tendrá solo 3 materias para crear un nuevo curso, las cuales son///");
        System.out.println("MATEMÁTICAS              CIENCIAS              ARTES");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de su curso");
        String nombre = sc.nextLine();
        System.out.println("Ingrese");
        
    } 
}