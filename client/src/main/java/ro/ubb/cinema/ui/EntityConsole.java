package ro.ubb.cinema.ui;

public interface EntityConsole {
    /**
     * Show all options from the submenu for the given entity
     */
    void showOptions();

    /**
     * Menu for adding a new instance of an entity
     */
    void add();

    /**
     * Menu for deleting an existing instance of an entity
     */
    void delete();

    /**
     * Menu for updating an existing instance of an entity
     */
    void update();

    /**
     * Menu for filtering all entities after a given criteria
     */
     void filter();

    /**
     * Menu for printing all the existing entities
     */
    void printAll();

    /**
     * Returns to main menu
     */
    void back();

}
