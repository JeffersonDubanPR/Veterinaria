//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val mascotas = mutableListOf<Mascota>()
    val consultas = mutableListOf<ConsultaMedica>()

    while (true) {
        println("\n Menú Veterinaria ")
        println("1. Crear mascota")
        println("2. Registrar consulta médica")
        println("3. Mostrar historial de consultas")
        println("4. Modificar datos de mascota")
        println("5. Calcular costo total de consultas por mascota")
        println("6. Salir")
        print("Seleccione una opción ")
        when (scanner.nextInt()) {
            1 -> crearMascota(scanner, mascotas)
            2 -> registrarConsulta(scanner, mascotas, consultas)
            3 -> mostrarHistorialConsultas(consultas)
            4 -> modificarDatosMascota(scanner, mascotas)
            5 -> calcularCostoTotalConsultas(scanner, mascotas, consultas)
            6 -> {
                println("salir")
                return
            }
            else -> println("Opción inválida. Intente nuevamente.")
        }
    }
}


fun crearMascota(scanner: Scanner, mascotas: MutableList<Mascota>) {
    scanner.nextLine()
    print("Ingrese el nombre de la mascota ")
    val nombre = scanner.nextLine()
    print("Ingrese la especie de la mascota ")
    val especie = scanner.nextLine()
    print("Ingrese la edad de la mascota ")
    val edad = scanner.nextInt()
    print("Ingrese el peso de la mascota ")
    val peso = scanner.nextDouble()

    val mascota = Mascota(nombre, especie, edad, peso)
    mascotas.add(mascota)
    println("Mascota creada")
}


fun registrarConsulta(scanner: Scanner, mascotas: List<Mascota>, consultas: MutableList<ConsultaMedica>) {
    if (mascotas.isEmpty()) {
        println("No hay mascotas registradas")
        return
    }
    println("\nSeleccione una mascota:")
    mascotas.forEachIndexed { index, mascota ->
        println("${index + 1}. ${mascota.describirMascota()}")
    }
    print("Ingrese el número de la mascota: ")
    val mascotaIndex = scanner.nextInt() - 1
    if (mascotaIndex !in mascotas.indices) {
        println("Selección inválida.")
        return
    }
    scanner.nextLine()
    print("Ingrese el diagnóstico: ")
    val diagnostico = scanner.nextLine()
    print("¿Incluye medicación? (1 si /2 no ): ")
    val incluyeMedicacion = scanner.nextLine().equals("1", ignoreCase = true)

    val consulta = ConsultaMedica(mascotas[mascotaIndex], diagnostico, incluyeMedicacion)
    consultas.add(consulta)
    println("Consulta registrada con éxito.")
}


fun mostrarHistorialConsultas(consultas: List<ConsultaMedica>) {
    if (consultas.isEmpty()) {
        println("No hay consultas registradas.")
        return
    }
    println("\nHistorial de consultas:")
    consultas.forEach { consulta ->
        println(consulta.describirConsulta())
    }
}


fun modificarDatosMascota(scanner: Scanner, mascotas: List<Mascota>) {
    if (mascotas.isEmpty()) {
        println("No hay mascotas registradas.")
        return
    }
    println("\nSeleccione una mascota para modificar:")
    mascotas.forEachIndexed { index, mascota ->
        println("${index + 1}. ${mascota.describirMascota()}")
    }
    print("Ingrese el número de la mascota: ")
    val mascotaIndex = scanner.nextInt() - 1
    if (mascotaIndex !in mascotas.indices) {
        println("Selección inválida.")
        return
    }
    val mascota = mascotas[mascotaIndex]
    println("\nSeleccione qué desea modificar:")
    println("1. Peso")
    println("2. Edad")
    when (scanner.nextInt()) {
        1 -> {
            print("Ingrese el nuevo peso: ")
            val nuevoPeso = scanner.nextDouble()
            mascota.actualizarPeso(nuevoPeso)
            println("Peso actualizado con éxito.")
        }
        2 -> {
            mascota.incrementarEdad()
            println("Edad incrementada con éxito.")
        }
        else -> println("Opción inválida.")
    }
}


fun calcularCostoTotalConsultas(scanner: Scanner, mascotas: List<Mascota>, consultas: List<ConsultaMedica>) {
    if (mascotas.isEmpty()) {
        println("No hay mascotas registradas.")
        return
    }
    println("\nSeleccione una mascota para calcular el costo total de consultas:")
    mascotas.forEachIndexed { index, mascota ->
        println("${index + 1}. ${mascota.describirMascota()}")
    }
    print("Ingrese el número de la mascota: ")
    val mascotaIndex = scanner.nextInt() - 1
    if (mascotaIndex !in mascotas.indices) {
        println("Selección inválida.")
        return
    }
    val mascota = mascotas[mascotaIndex]
    val costoTotal = consultas.filter { it.mascota == mascota }.sumOf { it.calcularCosto() }
    println("El costo total de las consultas para ${mascota.nombre} es: $costoTotal")
}