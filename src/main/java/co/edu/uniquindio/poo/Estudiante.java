package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;


/**
 * Clase para manejar la información de un Estudiante
 * 
 * @author Área de programación UQ
 * @since 2024-01
 * 
 *        Licencia GNU/GPL V3.0
 *        (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
public class Estudiante {
    private final String nombres;
    private final String apellidos;
    private final String numeroIdentificacion;
    private final String correo;
    private final String telefono;
    private final int edad;
    private final Collection<NotaObtenida> notasObtenidas;

    public Estudiante(String nombres, String apellidos, String numeroIdentificacion, String correo, String telefono, int edad) {
        assert nombres != null && !nombres.isBlank() : "El nombre es tiene valores invalidos";
        assert apellidos != null && !apellidos.isBlank() : "El numero de Id es invalido";
        assert numeroIdentificacion != null && !numeroIdentificacion.isBlank() : "El apellido es tiene valores invalidos";
        assert correo != null && correo.contains("@") : "El correo ingresado debe tener un @";
        assert telefono != null : "el numero de telefono es invalido";
        assert edad > 0 : "La edad ingresada debe ser positiva y mayor a 0";


        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroIdentificacion = numeroIdentificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;

        this.notasObtenidas = new LinkedList<>();
    }

    /**
     * Método para obtener los nombres del estudiante
     * 
     * @return los nombres del estudiante
     */
    public String getNombres() {
        return nombres;
    }
    
    /**
     * Método para obtener los apellidos del estudiante
     * 
     * @return los apellidos del estudiante
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método para obtener el número de identificación del estudiante
     * 
     * @return número de identificación del estudiante
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Método para obtener el correo electrónico del estudiante
     * 
     * @return correo electrónico del estudiante
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método para obtener el teléfono del estudiante
     * 
     * @return teléfono del estudiante
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método para obtener la edad del estudiante
     * 
     * @return edad del estudiante
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método para adicionar una nota obtenida al estudiante
     * 
     * @param notaObtenida nota obtenida por el estudiante
     */
    public void adicionarNotaObtenida(NotaObtenida notaObtenida) {
        verificarExistenciaNotaObtenida(notaObtenida);
        notasObtenidas.add(notaObtenida);
    }

    /**
     * Método de apoyo (privado) para verificar si existe o no la nota obtenida que
     * se desea adicionar
     * 
     * @param notaObtenida nota obtenida que se quiere verificar que NO exista.
     */
    private void verificarExistenciaNotaObtenida(NotaObtenida notaObtenida) {
        for (NotaObtenida nota : notasObtenidas) {
            if (nota.getNotaParcial().nombre().equals(notaObtenida.getNotaParcial().nombre())) {
                throw new IllegalArgumentException("Ya existe una nota para esta nota parcial");
            }
        }
    }

    /**
     * Método para obtener la nota obtenida dado el nombre de la nota parcial
     * 
     * @param nombreNotaParcial nombre de la nota parcial que se desea buscar.
     * @return la nota obtenida asociada a la nota parcial
     */
    public NotaObtenida getNotaObtenida(String nombreNotaParcial) {
        for (NotaObtenida nota : notasObtenidas) {
            if (nota.getNotaParcial().nombre().equals(nombreNotaParcial)) {
                return nota;
            }
        }
        throw new IllegalArgumentException("No se encontró una nota para la nota parcial");
    }
    /**
     * Método para obtener una colección NO modificable de las notas parciales del
     * curso.
     * 
     * @return colección no modificable de las notas parciales del curso
     */
    public Collection<NotaObtenida> getNotasObtenidas() {
        return Collections.unmodifiableCollection(notasObtenidas);
    }

    /**
     * Método para actualizar la nota que tiene una nota obtenida por el estudiante
     * 
     * @param nombreNotaParcial nombre de la nota parcial a la que la nota está
     *                          asociada
     * @param notaObtenida      nueva nota obtenida
     */
    public void setNotaObtenida(String nombreNotaParcial, double notaObtenida) {
        if (notaObtenida < 0.0 || notaObtenida > 5.0) {
            throw new IllegalArgumentException("La nota Obtenida debe estar entre 0.0 y 5.0");
        }

        for (NotaObtenida nota : notasObtenidas) {
            if (nota.getNotaParcial().nombre().equals(nombreNotaParcial)) {
                nota.setNotaObtenida(notaObtenida);
                return;
            }
        }
        throw new IllegalArgumentException("No se encontro una nota para la nota pacial especificada");
    }
    /**
     * Método para obtener la nota definitiva usando un promedio ponderado suma de
     * todas (nota * porcentaje)
     * 
     * @return nota definitiva
     */
    public double getDefinitiva() {
        validarNotas100Porciento();

        double definitiva = 0.0;
        for (NotaObtenida nota : notasObtenidas)  {
            definitiva += (nota.getNotaObtenida() * nota.getNotaParcial().porcentaje());
        }
        return definitiva;
    }

    /**
     * Método de apoyo (privado) para validar que la suma del porcentaje de las
     * notas obtenidas sea 1.0 (100%)
     */
    private void validarNotas100Porciento() {
        double pesoNotas = 0.0;
        for (NotaObtenida nota : notasObtenidas) {
            pesoNotas += nota.getNotaParcial().porcentaje();
        }
        if (Math.abs(1.0 - pesoNotas)>App.PRECISION) {
            throw new IllegalStateException("Las notas parciales no suman 1.0 (100%)");
        }
    }

}
