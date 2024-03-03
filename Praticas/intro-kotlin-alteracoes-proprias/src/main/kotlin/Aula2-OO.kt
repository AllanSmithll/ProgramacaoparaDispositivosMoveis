/*
Codigo do professor Gustavo Wagner Diniz, com alteracoes minhas
Disciplina: Programacao para Dispositivos Moveis
Data: 02/03/2024
 */

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    var idade = 0
    var nome: String?
    var cor: Cor = Cor.RED
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            0 -> {
                print("Nome do cachorro: ")
                nome = readlnOrNull()
                print("Idade do cachorro: ")
                idade = readlnOrNull()?.toInt() ?: 0
                println("Cor do cachorro: ")
                menuCores()
                print("Digite a opcao: ")
                opcao = readlnOrNull()?.toInt() ?: 1
                when (opcao) {
                    1 -> {
                        cor = Cor.RED
                    }

                    2 -> {
                        cor = Cor.GREEN
                    }

                    3 -> {
                        cor = Cor.BLUE
                    }

                    4 -> {
                        cor = Cor.WHITE
                    }
                }
                if (nome != null) {
                    val cachorro = Cachorro(idade, cor)
                    cachorro.nome = nome
                    repositorioAnimal.adicionar(cachorro)
                }
            }

            1 -> {
                print("Nome do gato: ")
                nome = readlnOrNull()
                print("Idade do gato: ")
                idade = readlnOrNull()?.toInt() ?: 0
                println("Cor do gato: ")
                menuCores()
                print("Digite a opcao: ")
                opcao = readlnOrNull()?.toInt() ?: 1
                when (opcao) {
                    1 -> {
                        cor = Cor.RED
                    }

                    2 -> {
                        cor = Cor.GREEN
                    }

                    3 -> {
                        cor = Cor.BLUE
                    }

                    4 -> {
                        cor = Cor.WHITE
                    }
                }
                if (nome != null) {
                    val gato = Gato(idade, cor)
                    gato.nome = nome
                    repositorioAnimal.adicionar (gato)
                }
            }

            2 -> {
                print("Nome do passaro: ")
                nome = readlnOrNull()
                print("Idade do passaro: ")
                idade = readlnOrNull()?.toInt() ?: 0
                println("Cor do passaro: ")
                menuCores()
                print("Digite a opcao: ")
                opcao = readlnOrNull()?.toInt() ?: 1
                when (opcao) {
                    1 -> {
                        cor = Cor.RED
                    }

                    2 -> {
                        cor = Cor.GREEN
                    }

                    3 -> {
                        cor = Cor.BLUE
                    }

                    4 -> {
                        cor = Cor.WHITE
                    }
                }
                if (nome != null) {
                    val passaro = Passaro(idade, cor)
                    passaro.nome = nome
                    repositorioAnimal.adicionar(passaro)
                }
            }

            3 -> {
                print("Nome da pessoa: ")
                nome = readlnOrNull()
                print("Idade da pessoa: ")
                idade = readlnOrNull()?.toInt() ?: 0
                println("Cor do pessoa: ")
                menuCores()
                print("Digite a opcao: ")
                opcao = readlnOrNull()?.toInt() ?: 1
                when (opcao) {
                    1 -> {
                        cor = Cor.RED
                    }

                    2 -> {
                        cor = Cor.GREEN
                    }

                    3 -> {
                        cor = Cor.BLUE
                    }

                    4 -> {
                        cor = Cor.WHITE
                    }
                }
                if (nome != null) {
                    val pessoa = Homem(idade, cor)
                    pessoa.nome = nome
                    repositorioAnimal.adicionar(pessoa)
                }
            }

            4 -> {
                repositorioAnimal.listar()
            }

            5 -> {
                println("Escolha uma cor para os animais: ")
                menuCores()
                print("Digite a opcao: ")
                opcao = readlnOrNull()?.toInt() ?: 0
                var varCor: Cor
                when (opcao) {
                    1 -> {
                        varCor = Cor.RED
                        repositorioAnimal.listarPorCor(varCor)
                    }

                    2 -> {
                        varCor = Cor.GREEN
                        repositorioAnimal.listarPorCor(varCor)
                    }

                    3 -> {
                        varCor = Cor.BLUE
                        repositorioAnimal.listarPorCor(varCor)
                    }
                    4 -> {
                        varCor = Cor.WHITE
                        repositorioAnimal.listarPorCor(varCor)
                    }
                }
            }

            6 -> {
                print("Idade: ")
                idade =  readlnOrNull()?.toInt() ?: 0
                repositorioAnimal.listarPorIdade(idade)
            }

            7 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
            }

            8 -> {
                print("Nome: ")
                val nomeAnimal: String? = readlnOrNull()
                if (nomeAnimal?.let { repositorioAnimal.buscar(it) } != null) {
                    println("Animal encontrado")
                } else println("Animal nao encontrado")
            }

            9 -> {
                print("Nome: ")
                val nomeAnimal: String? = readlnOrNull()
                if (nomeAnimal != null) {
                    if (repositorioAnimal.remover(nomeAnimal)) println("Animal removido") else println("Animal nao removido")
                }
            }
        }

    }
}

enum class Cor(val descricao: String) {
    RED("Vermelho"),
    GREEN("Verde"),
    BLUE("Azul"),
    WHITE("Branco")
}

abstract class Animal(var idade: Int, val cor: Cor) {
    open var nome: String = ""

    abstract fun emitirSom()
    open fun idadeEmAnosHumanos(): Int {
        return this.idade * 7
    }
}

class Homem(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Ola, mundo!")
    }

    override fun idadeEmAnosHumanos(): Int {
        return this.idade
    }
}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Au au")
    }
}

class Gato(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

fun menu() {
    println("0 - Cadastrar cachorro")
    println("1 - Cadastrar gato")
    println("2 - Cadastrar passaro")
    println("3 - Cadastrar pessoa")
    println("4 - Listar Animais")
    println("5 - Listar animais por cor")
    println("6 - Listar animais por idade")
    println("7 - Emitir som")
    println("8 - Buscar animal")
    println("9 - Excluir animal")
    println("10 - Sair")
}

fun menuCores() {
    println("1 - Vermelho")
    println("2 - Verde")
    println("3 - Azul")
    println("4 - Branco")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun listarPorCor(cor: Cor) {
        val animais = animais.filter { a -> a.cor === cor }
        for (a in animais) println(a.toString())
    }

    fun listarPorIdade(idade: Int) {
        val animais = animais.filter { a -> a.idade == idade }
        for (a in animais) println(a.toString())
    }

    fun buscar(nome: String): Animal? {
        val animal: Animal? = animais.find { a -> a.nome.equals(nome, ignoreCase = true) }
        return if (animal != null) {
            animal
        } else null
    }

    fun remover(nomeAnimal: String): Boolean {
        val animalEncontrado = animais.find { a ->
            a.nome.equals(nomeAnimal, ignoreCase = true)
        }
        return if (animalEncontrado != null) { animais.remove(animalEncontrado) }
        else false
    }
}
