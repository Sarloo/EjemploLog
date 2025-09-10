import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        logWriter.println(mensaje);
        logWriter.flush();
    }

    // Cerrar log
    public void cerrarLog() {
        if (logWriter != null) {
            logWriter.close();
        }
    }
}