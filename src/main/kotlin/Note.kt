class Note : Menu {
    override val itemMake = "Создать заметку"
    override val itemExit = "Выход"
    override val intro = "- СПИСОК ЗАМЕТОК -"
    override val introInput = "- СОЗДАТЬ ЗАМЕТКУ -\nВведите название заметки"
    override val inputName = "название заметки успешно создано!"
    override val listItemsMenu: MutableList<String> = mutableListOf()

}