package ik.koresh.service;

import java.util.Scanner;

public class Menu {
    private final static Menu instance = new Menu();
    private Menu(){}
    public static Menu getInstance(){return instance;}

    Scanner scanner = new Scanner(System.in);

    public Integer menuStart(){
        try {
            String choosing = scanner.nextLine();
            switch (choosing){
                case "Q", "q" -> System.exit(0);
                case "1" -> {
                    return 1;
                }
                case "2" -> {
                    return 2;
                }
                default -> throw new IllegalArgumentException("Неверно выбран путкт меню. Выбран: " + choosing);
            }
        }catch (IllegalArgumentException e){
            System.err.println(e);
        }
        return 0;
    }
}
