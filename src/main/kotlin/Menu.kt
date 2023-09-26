import java.util.*

abstract class Menu {

    val listItemsMenu: MutableList<String> = mutableListOf()

    var listNote: MutableList<Note> = mutableListOf()
    var listArchive: MutableList<Archive> = mutableListOf()

    fun inputArchive(console: Scanner): MutableList<Archive> {
        println("- СОЗДАТЬ АРХИВ -\nВведите название архива")
        console.nextLine()
        while (true) {
            val name = console.nextLine()
            if (name.isEmpty()) {
                println("\nКажется, вы ничего не ввели или такое название уже существует\nПопробуйте ещё раз\n")
            } else {
                listArchive.add(Archive(name, mutableListOf()))
                println("\n${name.uppercase()} архив успешно создан!\n")
                break
            }
        }
        return listArchive
    }

    fun listArchiveMenu(console: Scanner) {
        while (true) {
            prepareListItemMenuArchive()
            println("- СПИСОК АРХИВОВ -")
            for (i in listItemsMenu) {
                println("${listItemsMenu.indexOf(i)}. $i")
            }
            println("\nВыберите пункт меню, для этого введите его номер\n")
            if (console.hasNextInt()) {
                val commandMenuArchive: Int = console.nextInt()
                if (commandMenuArchive in 0 until listItemsMenu.size) {
                    when (commandMenuArchive) {
                        listItemsMenu.indexOf("Создать архив") -> {
                            inputArchive(console)
                        }

                        listItemsMenu.indexOf("Выход") -> {
                            println("Выходим на предыдущее меню\n")
                            break
                        }

                        else -> {
                            listNoteMenu(console, commandMenuArchive)
                        }
                    }
                } else {
                    println("\nТакой пункт в меню отсутствует\nВведите число от 0 до ${listItemsMenu.size - 1}\n")
                }
            } else {
                console.nextLine()
                println("\nВведите числовое значение\n")
            }
        }
    }

    fun inputNote(console: Scanner, commandMenuArchive: Int): MutableList<Archive> {
        println("- СОЗДАТЬ ЗАМЕТКУ -\nАрхив: ${listArchive[commandMenuArchive - 1].nameArchive.uppercase()}\n" +
                "Создать заметку\n")
        console.nextLine()
        while (true) {
            val name: String = console.nextLine()
            if (name.isEmpty()) {
                println("\nКажется, вы ничего не ввели или такое название уже существует\nПопробуйте ещё раз\n")
            } else {
                println("\n${name.uppercase()} название заметки успешно создано!\n")
                println("Введите содержание заметки\n")
                while (true) {
                    val text: String = console.nextLine()
                    if (text.isEmpty()) {
                        println("\nКажется, вы ничего не ввели\nПопробуйте ещё раз\n")
                    } else {
                        println("Введите содержание заметки\n")

                        val listNoteTemp: MutableList <Note> = mutableListOf()
                        for(i in listArchive[commandMenuArchive-1].valueArchive) {
                            listNoteTemp.add(i)
                        }
                        listNote = listNoteTemp
                        listNote.add(Note(name, text))
                        listArchive.set(commandMenuArchive-1, Archive(listArchive[commandMenuArchive-1].nameArchive, listNote))
                        println("\n${name.uppercase()} создание заметки завершено\n")
                        break
                    }
                }
            }
            break
        }
        return listArchive
    }

    fun listNoteMenu(console: Scanner, commandMenuArchive: Int) {
        while (true) {
            prepareListItemMenuNote(commandMenuArchive)
            println("- СПИСОК ЗАМЕТОК -")
            for (i in listItemsMenu) {
                println("${listItemsMenu.indexOf(i)}. $i")
            }
            println("\nВыберите пункт меню, для этого введите его номер\n")
            if (console.hasNextInt()) {
                val commandMenuNote: Int = console.nextInt()
                if (commandMenuNote >= 0 && commandMenuNote < listItemsMenu.size) {
                    when (commandMenuNote) {
                        listItemsMenu.indexOf("Создать заметку") -> {
                            inputNote(console, commandMenuArchive)
                        }

                        listItemsMenu.indexOf("Выход") -> {
                            println("Выходим на предыдущее меню\n")
                            break
                        }

                        else -> {
                            println("\nЗаметка: ${listNote[commandMenuNote - 1].nameNote.uppercase()}\nСодержание:\n${listNote[commandMenuNote - 1].valueNote}\n")
                        }
                    }
                } else {
                    println("\nТакой пункт в меню отсутствует\nВведите число от 0 до ${listItemsMenu.size - 1}\n")
                }
            } else {
                console.nextLine()
                println("\nВведите числовое значение\n")
            }
        }
    }

    fun prepareListItemMenuArchive(): MutableList<String> {
        listItemsMenu.clear()
        listItemsMenu.add("Создать архив")
        for (i in listArchive) {
            listItemsMenu.add(i.nameArchive)
        }
        listItemsMenu.add("Выход")
        return mutableListOf()
    }

    fun prepareListItemMenuNote(commandMenuArchive: Int): MutableList<String> {
        listItemsMenu.clear()
        listItemsMenu.add("Создать заметку")
        for (i in listArchive[commandMenuArchive-1].valueArchive) {
            listItemsMenu.add(i.nameNote)
        }
        listItemsMenu.add("Выход")
        return listItemsMenu
    }

}