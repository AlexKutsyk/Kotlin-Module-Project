import java.util.*

fun main() {
    val console = Scanner(System.`in`)
    println("--= ЗАМЕТКИ =--\n")

    val menuArchive = ArchiveMenu()

    menuArchive.listArchiveMenu(console)

    console.close()
}