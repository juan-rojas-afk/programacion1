package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Registro que agrupa los datos de un Curso
 * 
 * @author Área de programación UQ
 * @since 2024-01
 * 
 *        Licencia GNU/GPL V3.0
 *        (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

public class Curso {
    private final String nombre;
    private final Collection<Estudiante> estudiantes;
    private final Collection<ClaseCurso> clases;

    /**
     * Método constructor de la clase Curso
     * 
     * @param nombre Nombre del curso
     */
    public Curso(String nombre) {
        assert nombre != null : "El nombre no puede ser nulo";
        this.nombre = nombre;
        estudiantes = new LinkedList<>();
        clases = new LinkedList<>();
    }

    /**
     * Método para obtener el nombre del curso
     * 
     * @return Nombre del curso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para agregar a un estudiante al curso
     * 
     * @param estudiante Estudiante que se desea agregar
     */
    public void agregarEstudiante(Estudiante estudiante) {
        assert !validarNumeroIdentificacionExiste(estudiante.getNumeroIdentificacion()) : "El número de identificación ya existe.";
        estudiantes.add(estudiante);
    }

    /**
     * Método para buscar un estudiante dado el número de indicación
     * 
     * @param numeroIdenficacion Número de identificación del estudiante a buscar
     * @return Estudiante con el número de indicación indicado o null
     */
    public Estudiante getEstudiante(String numeroIdenficacion) {
        Estudiante estudianteInteres = null;

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroIdentificacion().equals(numeroIdenficacion)) {
                estudianteInteres = estudiante;
                break;
            }
        }
        return estudianteInteres;
    }

    /**
     * Método para obtener la colección NO modificable de los estudiantes del curso
     * 
     * @return la colección NO modificable de los estudiantes del curso
     */
    public Collection<Estudiante> getEstudiantes() {
        return Collections.unmodifiableCollection(estudiantes);
    }

    /**
     * Método privado para determinar si ya existe un estudiante registro en el
     * mismo número de identificación
     * 
     * @param numeroIdentificacion Número de identificación a buscar
     * @return Valor boolean que indica si el número de identificación ya está o no
     *         registrado.
     */
    private boolean validarNumeroIdentificacionExiste(String numeroIdentificacion) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Método para programar (agregar) una clase a un curso.
     * TODO evitar agregar más de una vez una misma clase.
     * 
     * @param claseCurso claseCurso que se desea programar
     */
    public void programarClase(ClaseCurso claseCurso) {
        clases.add(claseCurso);
    }

    /**
     * Método para obtener la colección NO modificable de los estudiantes del curso
     * 
     * @return la colección NO modificable de las clases del curso
     */
    public Collection<ClaseCurso> getClases() {
        return Collections.unmodifiableCollection(clases);
    }

    /**
     * Método que obtiene la colección de estudiantes que asistieron a una clase
     * 
     * @param claseCurso la clase de interés
     * @return colección de los estudiantes que asistieron a una clase interés
     */
    public Collection<Estudiante> getAsistentes(ClaseCurso claseCurso) {
        Collection<Estudiante> asistentes = new LinkedList<>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.asistioClase(claseCurso)) {
                asistentes.add(estudiante);
            }
        }
        return Collections.unmodifiableCollection(asistentes);
    }

    /**
     * Método que obtiene la colección de estudiantes que estaban ausentes a una
     * clase
     * 
     * @param claseCurso la clase de interés
     * @return colección de los estudiantes que estuvieron ausentes a una clase
     *         interés
     */
    public Collection<Estudiante> getAusentes(ClaseCurso claseCurso) {
        Collection<Estudiante> ausentes = new LinkedList<>();
        for (Estudiante estudiante : estudiantes) {
            if (!estudiante.asistioClase(claseCurso)) {
                ausentes.add(estudiante);
            }
        }
        return Collections.unmodifiableCollection(ausentes);
    }


    public double calcularPorcentajeAsistencia(ClaseCurso claseCurso) {
        int cantidadEstudiantes = estudiantes.size();
        int cantidadAsistentes = 0;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.asistioClase(claseCurso)) {
                cantidadAsistentes++;
            }
        }
        return (double) cantidadAsistentes / cantidadEstudiantes;
    }

}
