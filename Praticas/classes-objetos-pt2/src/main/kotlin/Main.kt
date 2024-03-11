open class Funcionario(val nome: String, val idade: Int) {
    init {
        println("Novo funcionário registrado - Nome: $nome, Idade: $idade")
    }

    open fun apresentar() {
        println("Funcionário: Nome - $nome, Idade - $idade")
    }
}

class Gerente(nome: String, idade: Int, val setor: String) : Funcionario(nome, idade) {

    override fun apresentar() {
        println("Gerente: Nome - $nome, Idade - $idade, Setor - $setor")
    }
}

class Desenvolvedor(nome: String, idade: Int, val linguagem: String) : Funcionario(nome, idade) {

    override fun apresentar() {
        println("Desenvolvedor: Nome - $nome, Idade - $idade, Linguagem - $linguagem")
    }
}

class Analista(nome: String, idade: Int, val area: String) : Funcionario(nome, idade) {

    override fun apresentar() {
        println("Analista: Nome - $nome, Idade - $idade, Área - $area")
    }
}

fun main() {
    val listaFuncionarios = listOf(
        Gerente("João", 35, "RH"),
        Desenvolvedor("Maria", 28, "Java"),
        Analista("Carlos", 40, "Financeiro")
    )

    for (funcionario in listaFuncionarios) {
        when (funcionario) {
            is Gerente -> {
                println("Gerente encontrado:")
                funcionario.apresentar()
            }

            is Desenvolvedor -> {
                println("Desenvolvedor encontrado:")
                funcionario.apresentar()
            }

            is Analista -> {
                println("Analista encontrado:")
                funcionario.apresentar()
            }

            else -> {
                println("Funcionário encontrado:")
                funcionario.apresentar()
            }
        }
        println()
    }
}