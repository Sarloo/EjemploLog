import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

// Clase para el nodo del árbol
class Nodo {
    int valor;
    Nodo izquierda, derecha;

    public Nodo(int valor) {
        this.valor = valor;
        izquierda = derecha = null;
    }
}

// Clase para el árbol binario de búsqueda
class ArbolBinarioBusqueda {
    Nodo raiz;
    private PrintWriter logWriter;

    public ArbolBinarioBusqueda(String logFile) throws IOException {
        raiz = null;
        logWriter = new PrintWriter(new FileWriter(logFile, true));
        log("=== INICIO DE SESIÓN: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ===");
    }

    // Método para insertar un valor
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
        log("Insertado valor: " + valor);
    }

    private Nodo insertarRec(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }
        if (valor < raiz.valor)
            raiz.izquierda = insertarRec(raiz.izquierda, valor);
        else if (valor > raiz.valor)
            raiz.derecha = insertarRec(raiz.derecha, valor);
        return raiz;
    }

    // Método para buscar un valor
    public boolean buscar(int valor) {
        boolean encontrado = buscarRec(raiz, valor);
        log("Búsqueda de " + valor + ": " + (encontrado ? "ENCONTRADO" : "NO ENCONTRADO"));
        return encontrado;
    }

    private boolean buscarRec(Nodo raiz, int valor) {
        if (raiz == null) return false;
        if (raiz.valor == valor) return true;
        return valor < raiz.valor ? buscarRec(raiz.izquierda, valor) : buscarRec(raiz.derecha, valor);
    }

    // Método para registrar en el log
    private void log(String mensaje) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        logWriter.println("[" + timestamp + "] " + mensaje);
        logWriter.flush();
    }

    // Cerrar log
    public void cerrarLog() {
        if (logWriter != null) {
            log("=== FIN DE SESIÓN ===");
            logWriter.close();
        }
    }
}

// Métodos para los ejercicios
class Main {
    
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
                    Main.ejercicio1();
                    break;
                case 2:
                    Main.ejercicio2();
                    break;
                case 3:
                    Main.ejercicio3();
                    break;
                case 4:
                    Main.ejercicio1();
                    Main.ejercicio2();
                    Main.ejercicio3();
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