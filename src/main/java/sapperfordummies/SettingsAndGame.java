package sapperfordummies;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * В данном классе происходит настройка игры и её последующий запуск.
 *
 * @author Vadim
 */
public class SettingsAndGame {

    private int xPosSet = 0;
    private int yPosSet = 0;
    private int mineSet = 0;

    private Scanner in = new Scanner(System.in);
    private PlayingField game;

    /**
     * Метод установки размера игрового поля (матрицы).
     *
     * @param xPos - размер матрицы по Х
     * @param yPos - размер матрицы по У
     */
    private void setPos(int xPos, int yPos) {
        this.xPosSet = xPos;
        this.yPosSet = yPos;
        game = new PlayingField(xPos, yPos);
    }

    /**
     * Метод установки мин. Для сверки записывает в mineSet.
     *
     * @param mines - Значение (количество мин), передаваемое методу.
     */
    private void setMines(int mines) {
        this.mineSet = mines;
        int countMines = mines;
        while (countMines > 0) {
            game.generateMines();
            countMines--;
        }
    }

    /**
     * Правила игры.
     */
    public void rules() {
        System.out.println("A flat field is divided into adjacent cells "
                + "(squares),\nsome of which are 'mined'; the number of 'mined'"
                + " cells is known.\nThe goal of the game is to open all the "
                + "cells that do not contain mines.\nThe player opens the cells"
                + ", trying not to open the cell with the mine.\nOpening a "
                + "cell with a mine, it loses.\n");
    }

    /**
     * Метод, вызывающий настройки. Сразу после попадания в настройки
     * потребуется по новой ввсети команду. 
     * setc - установка размера поля. 
     * setm - установка количества мин. 
     * qqq - выход из настроек.
     *
     * @param strEntered - Строка, переданая из main'a.
     */
    public void callSettings(String strEntered) {
        String strEnter = strEntered;
        System.out.println("\tSettings\n\n'setc' - to set coords of poly"
                + "\n 'setm' - to set mines"
                + "\n 'qqq' - to out from settings");
        do {
            if (strEnter.equals("setc")) {
                int xPos = 10;
                int yPos = 10;
                System.out.print("Default Size - 10 x 10 \n"
                        + "Please, enter coord for X: ");
                xPos = intEntered();
                System.out.print("And now, coord for Y: ");
                yPos = intEntered();
                if (xPos < 10 | yPos < 10) {
                    System.out.println("Too small size of field\n");
                    xPos = 10;
                    yPos = 10;
                }
                setPos(xPos, yPos);
                strEnter = "";
            }
            if (strEnter.equals("setm")) {
                int mines = 3;
                System.out.print("Now, enter how many mines you need: ");
                mines = intEntered();
                setMines(mines);
                strEnter = "";
            }
            if (!strEnter.equals("setc") && !strEnter.equals("setm")) {
                System.out.print("\nPlease, re-enter: ");
                strEnter = strEntered();
            }
        } while (!strEnter.equals("qqq"));
        System.out.println("Exit from settings...");
    }

    /**
     * Метод, начинающий игру. Если игрок решил не ставить своё поле и\или мины,
     * то игра подставить значения 10 х 10 для поля и 2 мины. Так же в данном
     * методе содержится 2 счётчика, результат (булева ф-ция) и строковый флаг.
     * Счётчики для сверки мин, и в случае их совпадения - игра заканчивается.
     * Результат - если false, то игра продолжается, иначе - игрок проиграл.
     * Флаг - для ввода и установки флага.
     */
    public void startGame() {
        if (xPosSet == 0 && yPosSet == 0) {
            setPos(10, 10);
        }
        if (mineSet == 0) {
            setMines(2);
        }
        int counter = 0;
        boolean result = false;
        String flag = new String();
//        game.cheatPoly();
        do {
            game.printField();
            System.out.print("Enter X cell: ");
            int x = intEntered();
            System.out.print("Enter Y cell: ");
            int y = intEntered();
            System.out.println("Set flag (y/n)?");
            flag = strEntered();
            if (flag.equals("y")) {
                int xMine, yMine = 0;
                System.out.println("Please, enter the coords of Mine");
                System.out.print("Enter X cell: ");
                xMine = intEntered();
                System.out.print("Enter Y cell: ");
                yMine = intEntered();
                counter = game.setFlag(xMine - 1, yMine - 1);
                counter += counter;
            } else if (flag.equals("n")) {
                flag = "";
                result = game.runOnPoly(x - 1, y - 1);
            } else if (!flag.equals("y") && !flag.equals("n")) {
                flag = "";
                result = game.runOnPoly(x - 1, y - 1);
            }
        } while (result != true || counter == mineSet);
        if (result == true) {
            System.out.println("\nBOOM. You lose. Better next time...");
            System.exit(0);
        } 
        if (counter == mineSet){
            System.out.println("Congration! You win!");
            System.exit(0);
        }
    }

    /**
     * Метод ввода числ
     *
     * @return - возвращяет введённое число
     */
    public int intEntered() {
        int number = 0;
        while(number <= 0){ 
            try {
                number = in.nextInt();
                if (number <= 0) {
                    System.out.print("Bad number. Re-enter: ");
                }
            } catch(InputMismatchException ex) {
                System.out.println("Enter NOT number");
            }
        }
        return number;
    }

    /**
     * Метод ввода строки
     *
     * @return - возвращяет введённую строку
     */
    public String strEntered() {
        String strNull = "";
        String strChoose = new String();
        do {
            strChoose = in.nextLine();
        } while (strChoose.equals(strNull));
        return strChoose;
    }
}
