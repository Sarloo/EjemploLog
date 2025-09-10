package act;


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
// Métodos para los ejercicios
class Ejercicios {
    
    // Método para convertir nombre a código ASCII
    public static int nombreAAscii(String nombre) {
        int codigo = 0;
        for (char c : nombre.toCharArray()) {
            codigo += (int) c;
        }
        return codigo;
    }
    
    // Ejercicio 1: 20 números aleatorios
    public static void ejercicio1() {
        System.out.println("\n=== EJERCICIO 1: 20 números aleatorios ===");
        try {
            ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda("log_numeros.txt");
            Random random = new Random();
            
            // Insertar 20 números aleatorios entre 1 y 100
            System.out.println("Insertando 20 números aleatorios...");
            int[] numerosInsertados = new int[20];
            for (int i = 0; i < 20; i++) {
                int numero = random.nextInt(100) + 1;
                numerosInsertados[i] = numero;
                arbol.insertar(numero);
            }
            
            // Mostrar números insertados
            System.out.print("Números insertados: ");
            for (int num : numerosInsertados) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            // Realizar búsquedas hasta encontrar uno exitoso
            int busquedasFallidas = 0;
            boolean encontrado = false;
            int numeroEncontrado = 0;
            
            System.out.println("Realizando búsquedas...");
            while (!encontrado) {
                int numeroBuscar = random.nextInt(100) + 1;
                encontrado = arbol.buscar(numeroBuscar);
                if (!encontrado) {
                    busquedasFallidas++;
                } else {
                    numeroEncontrado = numeroBuscar;
                }
            }
            
            System.out.println("✓ Número encontrado: " + numeroEncontrado);
            System.out.println("✓ Búsquedas fallidas antes de encontrar uno correcto: " + busquedasFallidas);
            arbol.cerrarLog();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Ejercicio 2: Nombres de alumnos
    public static void ejercicio2() {
        System.out.println("\n=== EJERCICIO 2: Nombres de alumnos ===");
        try {
            ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda("log_nombres.txt");
            Scanner scanner = new Scanner(System.in);
            
            String[] nombres = {
                "Juan", "Maria", "Pedro", "Ana", "Luis", 
                "Carmen", "Jose", "Isabel", "Miguel", "Laura",
                "Carlos", "Sofia", "Francisco", "Elena", "Antonio",
                "Rosa", "Manuel", "Teresa", "David", "Paula"
            };
            
            // Insertar nombres convertidos a ASCII
            System.out.println("Insertando nombres convertidos a códigos ASCII...");
            for (String nombre : nombres) {
                int codigo = nombreAAscii(nombre);
                arbol.insertar(codigo);
                System.out.println(nombre + " -> " + codigo);
            }
            
            // Buscar un nombre específico
            System.out.print("\nIngrese el nombre a buscar: ");
            String nombreBuscar = scanner.nextLine();
            int codigoBuscar = nombreAAscii(nombreBuscar);
            
            System.out.println("Buscando: " + nombreBuscar + " (código: " + codigoBuscar + ")");
            boolean encontrado = arbol.buscar(codigoBuscar);
            
            if (encontrado) {
                System.out.println("✓ El nombre '" + nombreBuscar + "' está en el árbol");
            } else {
                System.out.println("✗ El nombre '" + nombreBuscar + "' NO está en el árbol");
            }
            
            arbol.cerrarLog();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Ejercicio 3: Años de nacimiento
    public static void ejercicio3() {
        System.out.println("\n=== EJERCICIO 3: Años de nacimiento ===");
        try {
            ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda("log_anios.txt");
            Random random = new Random();
            
            // Generar 20 años de nacimiento entre 1980 y 2005
            System.out.println("Generando años de nacimiento...");
            int[] aniosNacimiento = new int[20];
            
            for (int i = 0; i < 20; i++) {
                int anio = 1980 + random.nextInt(26); // 1980-2005
                aniosNacimiento[i] = anio;
                arbol.insertar(anio);
            }
            
            // Mostrar años insertados
            System.out.print("Años insertados: ");
            for (int anio : aniosNacimiento) {
                System.out.print(anio + " ");
            }
            System.out.println();
            
            // Buscar quién nació en 1990
            System.out.println("Buscando personas que nacieron en 1990...");
            boolean encontrado1990 = arbol.buscar(1990);
            
            if (encontrado1990) {
                System.out.println("✓ Se encontró al menos una persona que nació en 1990");
                
                // Mostrar todas las ocurrencias de 1990
                System.out.println("Personas que nacieron en 1990:");
                for (int i = 0; i < aniosNacimiento.length; i++) {
                    if (aniosNacimiento[i] == 1990) {
                        System.out.println("  - Persona " + (i+1));
                    }
                }
            } else {
                System.out.println("✗ No se encontró ninguna persona que naciera en 1990");
            }
            
            arbol.cerrarLog();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
