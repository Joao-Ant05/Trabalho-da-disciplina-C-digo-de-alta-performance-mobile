class Cliente(
    val nome: String,
    val telefone: String,
    val preferenciasOpcionais: String? = null
)


class Profissional(
    val nome: String,
    val especialidade: String
)


fun obterProfissional(opcao: Int): Profissional {
    return when (opcao) {
        1 -> Profissional("Paulo", "Corte com a tesoura")
        2 -> Profissional("Carlos", "Degradê top")
        3 -> Profissional("Ana", "Cabelo Feminino")
        else -> Profissional("Indisponível", "Sem especialidade")
    }
}


class Agendamento(
    val cliente: Cliente,
    val profissional: Profissional,
    val data: String,
    val horario: String,
    val valorFinal: Double
)


class Servico(
    val nome: String,
    val preco: Double
)


fun obterServico(opcao: Int): Servico {
    return when (opcao) {
        1 -> Servico("Corte Masculino", 45.00)
        2 -> Servico("Barboterapia", 35.00)
        3 -> Servico("Combo Corte + Barba", 70.00)
        else -> Servico("Opção inválida", 0.00)
    }
}


fun calcularTaxa(valor: Double, antecedenciaHoras: Int): Double {
    return if (antecedenciaHoras < 24) valor * 0.5 else 0.0
}


fun main() {
    val cliente = Cliente("João Brito", "(92)91234-5678", "Preferência que seja cortado todo na tesoura!")

    val opcaoProfissional = 1
    val profissional = obterProfissional(opcaoProfissional)
    println("Profissional escolhido: ${profissional.nome} - ${profissional.especialidade}")

    val opcaoServico = 3
    val servico = obterServico(opcaoServico)
    println("Serviço escolhido: ${servico.nome} -> R$ %.2f".format(servico.preco))

    val agendamento = Agendamento(cliente, profissional, "10/10/2026", "11h", servico.preco)


    println(
        "Agendado: ${agendamento.cliente.nome} com ${agendamento.profissional.nome} " +
                "em ${agendamento.data} às ${agendamento.horario}"
    )


    val horarios = listOf("09h", "10h", "12h", "13h", "14h", "15h")
    val horarioBuscado = agendamento.horario


    var ocupado = false
    for (horario in horarios) {
        println("Horário analisado: $horario")
        if (horario == horarioBuscado) {
            ocupado = true
        }
    }
    if (ocupado) {
        println("$horarioBuscado -> Horário Ocupado")
    } else {
        println("$horarioBuscado -> Horário Livre")
    }


    println("\n--- Cancelamento ---")
    println("Cancelar com 48h de antecedência: taxa R$ %.2f".format(calcularTaxa(servico.preco, 48)))
    println("Cancelar com 5h de antecedência: taxa R$ %.2f".format(calcularTaxa(servico.preco, 5)))


    println("\n--- Null Safety ---")
    val produtoFinalizador: String? = "Pomada finalizadora"

    val preferencia = produtoFinalizador ?: "Cliente não informou preferência"
    println("Produto finalizador: $preferencia")
}
