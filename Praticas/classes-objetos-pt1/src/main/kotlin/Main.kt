// Deve-se comentar as partes que serão rodadas - 09/03/2024

// Questão 1
//class Produto {
//    var nome: String = ""
//    var preco: Double = 0.0
//
//    constructor() {
//        nome = "Produto"
//        preco = 0.0
//    }
//
//    constructor(nome: String) {
//        this.nome = nome
//        preco = 0.0
//    }
//
//    constructor(nome: String, preco: Double) {
//        this.nome = nome
//        this.preco = preco
//    }
//
//}
//
//fun main() {
//    val p1 = Produto()
//    val p2 = Produto("Cocada")
//    val p3 = Produto("Biscoito", 10.0)
//    println(p1.nome)
//    println(p1.preco)
//    println(p2.nome)
//    println(p2.preco)
//    println(p3.nome)
//    println(p3.preco)
//}

// Questão 2
//class Cliente {
//    var nome: String = ""
//    private var idade: Int = 0
//
//    fun mostrarIdade(): Int {
//        return this.idade
//    }
//}
//
//fun main() {
//    val c1 = Cliente()
//    println(c1.mostrarIdade())
//}

// Questão 3
//abstract class Personagem {
    //abstract fun atacar(): String
//}
//
//class Guerreiro : Personagem() {
//    override fun atacar(): String {
//        return "Corte com espada"
//    }
//}
//
//class Mago : Personagem() {
//    override fun atacar(): String {
//        return "Feitiço lançado"
//    }
//}
//
//fun main() {
//    val gue1 = Guerreiro()
//    val mag1 = Mago()
//    println(gue1.atacar())
//    println(mag1.atacar())
//}

// Questão 4
//interface FormaGeometrica {
//    abstract fun calcularArea(): Double
//}
//
//class Retangulo(val altura: Double, val largura: Double) : FormaGeometrica {
//    override fun calcularArea(): Double {
//        return this.altura * this.largura
//    }
//}
//
//class Circulo(val raio: Double) : FormaGeometrica {
//    override fun calcularArea(): Double {
//        return 3.1415 * this.raio * this.raio
//    }
//}
//
//fun main() {
//    val c1 = Circulo(10.0)
//    val r1 = Retangulo(10.0, 20.0)
//    println(r1.calcularArea())
//    println(c1.calcularArea())
//}

// Questão 5
class ContaBancaria {
    var numeroConta: String = ""
    var nomeTitular: String = ""
    private var saldo: Double = 0.0
    var nomeCapitalizado: String = ""
        get() = nomeTitular.replaceFirstChar { it.uppercase() }
        set(value) {
            field = if (value >= 0.0.toString()) value else 0.0.toString()
        }

    override fun toString(): String {
        return "Conta: $numeroConta\n" +
                "Titular: $nomeCapitalizado\n" +
                "Saldo: R$$saldo"
    }
}

fun main() {
    val cb1 = ContaBancaria()
    println(cb1.nomeTitular)
}