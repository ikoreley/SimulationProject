package ik.koresh.view;

public class ViewMenu {
    private final static ViewMenu instance = new ViewMenu();
    private ViewMenu(){}
    public static ViewMenu getInstance(){
        return instance;
    }

    public void printMenu(){
        System.out.println("""
                Проект "Симуляция"
                настройки полей и сущностей находятся в папке resources
                Выберете пункт меню:
                1 - запустить полный цикл пока все животные не вымрут
                2 - запустить пошагово, для продолжения шага введите любой символ
                Q - Выход
                """);
    }
}
