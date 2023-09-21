import java.util.*

fun main() {
    val console = Scanner(System.`in`)
    println("--= ЗАМЕТКИ =--\n")

    val myArchive = Archive()
    val mapArchive : MutableMap<String, Note?> = mutableMapOf()

    myArchive.listMenu(mapArchive, console)


    console.close()
}