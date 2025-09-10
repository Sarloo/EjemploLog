package act;

import java.util.Scanner;
// Clase principal
public class MainCompleto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🌳 SISTEMA DE ÁRBOLES BINARIOS DE BÚSQUEDA");
        System.out.println("==========================================");
        
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\nSeleccione el ejercicio a ejecutar:");
            System.out.println("1. 20 números aleatorios");
            System.out.println("2. Nombres de alumnos");
            System.out.println("3. Años de nacimiento");
            System.out.println("4. Ejecutar todos los ejercicios");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    Ejercicios.ejercicio1();
                    break;
                case 2:
                    Ejercicios.ejercicio2();
                    break;
                case 3:
                    Ejercicios.ejercicio3();
                    break;
                case 4:
                    Ejercicios.ejercicio1();
                    Ejercicios.ejercicio2();
                    Ejercicios.ejercicio3();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
            if (continuar && opcion != 4) {
                System.out.print("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
}
