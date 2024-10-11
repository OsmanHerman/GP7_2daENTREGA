package proyectotransversal.vista;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import proyectotransversal.modelo.Alumno;
import proyectotransversal.modelo.Conexion;
import proyectotransversal.persistencia.AlumnoData;

/**
 *
 * @author Osman Herman
 * @author Ulises Perez
 * @author Nahuel Alegre
 * @author Nicolas Dominguez
 */
public class ProyectoTransversal {

    public static void main(String[] args) {
        
        Connection conexion = Conexion.getConexion();
        
        Scanner scanner = new Scanner(System.in);
        AlumnoData alumnoData = new AlumnoData();

        boolean continuar = true;

        while (continuar) {
            System.out.println("=== Gestión de Alumnos ===");
            System.out.println("1. Ingresar un nuevo alumno");
            System.out.println("2. Mostrar todos los alumnos");
            System.out.println("3. Buscar alumno por ID");
            System.out.println("4. Buscar alumno por DNI");
            System.out.println("5. Modificar un alumno");
            System.out.println("6. Eliminar un alumno (baja lógica)");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("=== Ingresar un nuevo alumno ===");
                    System.out.print("DNI: ");
                    int dni = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
                    String fechaNacimientoStr = scanner.nextLine();
                    LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

                    Alumno nuevoAlumno = new Alumno(dni, apellido, nombre, fechaNacimiento, true);
                    alumnoData.guardarAlumno(nuevoAlumno);
                    break;

                case 2:
                    System.out.println("=== Lista de Alumnos ===");
                    List<Alumno> alumnos = alumnoData.listarAlumnos();
                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno);
                    }
                    break;

                case 3:
                    System.out.println("=== Buscar Alumno por ID ===");
                    System.out.print("ID del Alumno: ");
                    int id = scanner.nextInt();
                    Alumno alumno = alumnoData.buscarAlumno(id);
                    if (alumno != null) {
                        System.out.println(alumno);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("=== Buscar Alumno por DNI ===");
                    System.out.print("DNI del Alumno: ");
                    dni = scanner.nextInt();
                    alumno = alumnoData.buscarAlumnoPorDni(dni);
                    if (alumno != null) {
                        System.out.println(alumno);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("=== Modificar Alumno ===");
                    System.out.print("ID del Alumno a modificar: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    alumno = alumnoData.buscarAlumno(id);
                    if (alumno != null) {
                        System.out.println("Modificando alumno: " + alumno);
                        System.out.print("Nuevo DNI: ");
                        dni = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nuevo Apellido: ");
                        apellido = scanner.nextLine();

                        System.out.print("Nuevo Nombre: ");
                        nombre = scanner.nextLine();

                        System.out.print("Nueva Fecha de Nacimiento (yyyy-mm-dd): ");
                        fechaNacimientoStr = scanner.nextLine();
                        fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

                        alumno.setDni(dni);
                        alumno.setApellido(apellido);
                        alumno.setNombre(nombre);
                        alumno.setFechaNacimiento(fechaNacimiento);

                        alumnoData.modificarAlumno(alumno);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("=== Eliminar Alumno (Baja lógica) ===");
                    System.out.print("ID del Alumno a eliminar: ");
                    id = scanner.nextInt();
                    alumnoData.eliminarAlumno(id);
                    break;

                case 7:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println();
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }
    
}
