
class ConsultaMedica(
    val mascota: Mascota,
    val diagnostico: String,
    val incluyeMedicacion: Boolean
) {

    fun calcularCosto(): Double {
        val costoBase = 50.0
        return if (incluyeMedicacion) costoBase * 1.15 else costoBase
    }


    fun describirConsulta(): String {
        return "Mascota: ${mascota.nombre}, Diagnóstico: $diagnostico, Costo: ${calcularCosto()}"
    }
}