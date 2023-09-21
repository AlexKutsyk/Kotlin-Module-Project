import java.util.*

interface Menu {
    val itemMake: String
    val itemExit: String
    val intro: String
    val introInput: String
    val inputName: String
    val listItemsMenu: MutableList<String>


    fun <V> prepareListItemMenu(map: MutableMap<String, V?>): MutableList<String> {

        listItemsMenu.clear()

        listItemsMenu.add(itemMake)
        val listKeysMap = map.keys.toList()

        for (i in listKeysMap) {
            listItemsMenu.add(i)
        }

        listItemsMenu.add(itemExit)
        return listItemsMenu
    }


    fun <K : String, V> listMenu(map: MutableMap<K, V?>, console: Scanner): MutableMap<K, V> {
        val myNote = Note()
        val mapNote: MutableMap<String, String?> = mutableMapOf()
        while (true) {
            prepareListItemMenu(map)
            println(intro)
            for (i in listItemsMenu) {
                println("${listItemsMenu.indexOf(i)}. $i")
            }
            println("\nВыберите пункт меню, для этого введите его номер\n")
            if (console.hasNextInt()) {
                val command: Int = console.nextInt()
                if (command >= 0 && command < listItemsMenu.size) {
                    when (command) {
                        listItemsMenu.indexOf(itemMake) -> inputItem(map, console)
                        listItemsMenu.indexOf("Выход") -> {
                            println("Выходим на предыдущее меню\n")
                            break
                        }

                        else -> {
                            if (itemMake == "Создать заметку") {
                                println("\nЗаметка: ${listItemsMenu[command].toUpperCase()}\nСодержание:\n${map[listItemsMenu[command]]}\n")
                            } else {
                                map[listItemsMenu[command] as K] = myNote.listMenu(mapNote, console) as V?
                            }
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
        return mutableMapOf()
    }

    fun <K : String, V> inputItem(map: MutableMap<K, V?>, console: Scanner): MutableMap<K, V> {
        println(introInput)
        console.nextLine()
        while (true) {
            val name: String = console.nextLine()
            if (name.length == 0 || map.containsKey(name)) {
                println("\nКажется, вы ничего не ввели или такое название уже существует\nПопробуйте ещё раз\n")
            } else {
                map.put(name as K, null)
                println("\n${name.toUpperCase()} $inputName\n")
                if (itemMake == "Создать заметку") {
                    println("Введите содержание заметки\n")
                    while (true) {
                        val text: String = console.nextLine()
                        if (text.length == 0) {
                            println("\nКажется, вы ничего не ввели\nПопробуйте ещё раз\n")
                        } else {
                            map[name] = text as V?
                            println("\n${name.toUpperCase()} создание заметки завершено\n")
                            break
                        }
                    }
                }
            }
            break
        }
        return mutableMapOf()
    }
}

