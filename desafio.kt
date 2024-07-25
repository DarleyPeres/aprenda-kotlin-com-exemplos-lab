// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
import java.time.LocalDate
import kotlin.jvm.Throws

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Aluno(val nome: String, val dataIngresso: LocalDate = LocalDate.now())

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel,
                    val conteudos: MutableList<ConteudoEducacional> = mutableListOf()) {

    private val inscritos = mutableListOf<Aluno>()
    private val inscritosLeitura: List<Aluno> = inscritos

    @Throws(IllegalArgumentException::class)
    fun matricular(aluno: Aluno) {
        if (inscritos.any {it.nome == aluno.nome} ) {
                    throw IllegalArgumentException(
                            "Aluno: ${aluno.nome} já inscrito nesta formação: $nome em ${aluno.dataIngresso}!")
                }
                inscritos.add(aluno)
    }

    fun getInscritos() = inscritosLeitura

    fun add(vararg conteudo: ConteudoEducacional) {
        conteudos.addAll(conteudo)
    }

    fun getTempoTotalHorasFormacao(): Int {
        val totalMinutos = conteudos.sumOf { it.duracao }
        return totalMinutos / 60
    }

}


    fun matricular(formacao: Formacao, aluno: String) {
        try {
            formacao.matricular(Aluno(aluno))
            println("Aluno $aluno matriculado com sucesso para a formacao ${formacao.nome}")
        }
        catch(e: IllegalArgumentException) {
            println(e.message)
        }
    }

    fun listarFormacoes(formacoes: List<Formacao>) {
        println ("== Relatorio das Formacoes ==")
        println("---------")
        formacoes.forEach {
            println ("Formacao: ${it.nome}, Nivel: ${it.nivel}")
            println ("\t Tempo Total em Horas: ${it.getTempoTotalHorasFormacao()}")
            println ("\t Conteudos: ${it.conteudos}")
            println ("\t Inscritos: ${it.getInscritos()}")
            println("---------")
        }
    }



fun main() {
    //Criando os conteudos
    val logicaProgramacao = ConteudoEducacional("Logica de Programacao",90)
    val javaBasico = ConteudoEducacional("Java Basico",120)
    val kotlin = ConteudoEducacional("Kotlin",120)
    val html = ConteudoEducacional("HTML")
    val css = ConteudoEducacional("CSS")
    val javaScript = ConteudoEducacional("Java Script")
    val kotlinIntermediario = ConteudoEducacional("Kotlin Intermediario",160)
    val bancoDeDados = ConteudoEducacional("Banco de Dados",160)
    val springFramework = ConteudoEducacional("Spring Framework",200)

    //Criando as formacoes
    val formacaoProgramacao = Formacao("Programação", Nivel.BASICO)
    formacaoProgramacao.add(logicaProgramacao, javaBasico, kotlin)

    val formacaoFrontEnd = Formacao("Formação Front End", Nivel.INTERMEDIARIO, mutableListOf(html,css,javaScript))

    val formacaoBackEnd = Formacao("Formação Back End", Nivel.DIFICIL)
    formacaoBackEnd.add(kotlinIntermediario)
    formacaoBackEnd.add(bancoDeDados)
    formacaoBackEnd.add(springFramework)

    //Fazendo as matriculas
    matricular(formacaoProgramacao,"Antonio")
    matricular(formacaoProgramacao,"Ana")
    matricular(formacaoProgramacao,"Bruna")

    matricular(formacaoFrontEnd,"Carlos")
    matricular(formacaoFrontEnd,"Carol")
    matricular(formacaoFrontEnd,"Daniel")

    matricular(formacaoBackEnd,"Pedro")
    matricular(formacaoBackEnd,"Gabriel")
    matricular(formacaoBackEnd,"Carlos")
    matricular(formacaoBackEnd,"Pedro")

    listarFormacoes(listOf(formacaoProgramacao,formacaoFrontEnd,formacaoBackEnd))
}