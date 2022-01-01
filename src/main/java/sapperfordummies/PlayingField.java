package sapperfordummies;

/**
 * Класс, в которм конструктор игрового поля, генерация мин на нём, установка
 * флагов, хождение по полю и вывод на печать.
 *
 * @author Vadim
 */
public class PlayingField {

    private int xPos;
    private int yPos;
    private int[][] gamePoly;

    /**
     * Конструктор, в который передаются 2 параметра.
     *
     * @param xPosPoly - размер поля по X.
     * @param yPosPoly - размер поля по Y.
     */
    public PlayingField(int xPosPoly, int yPosPoly) {
        this.xPos = xPosPoly;
        this.yPos = yPosPoly;
        this.gamePoly = new int[xPos][yPos];
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setGamePoly(int[][] gamePoly) {
        this.gamePoly = gamePoly;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int[][] getGamePoly() {
        return gamePoly;
    }

    /**
     * Генерация мин сбоит
     */
    public void generateMines() {
        int counter = 0;
        int xPosMine = 0;
        int yPosMine = 0;
        do {
            xPosMine = (int) (Math.random() * xPos);
            yPosMine = (int) (Math.random() * yPos);
            if (xPosMine == 0) {
                xPosMine++;
            }
            if (xPosMine == xPos - 1) {
                xPosMine--;
            }
            if (yPosMine == 0) {
                yPosMine++;
            }
            if (yPosMine == yPos - 1) {
                yPosMine--;
            }
            if (gamePoly[xPosMine][yPosMine] != 9) {
                counter++;
            }
        } while (counter == 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gamePoly[xPosMine - 1 + i][yPosMine - 1 + j] = 1;
            }
        }
        gamePoly[xPosMine][yPosMine] = 9;
    }

    /**
     * Метод, устанавливающий флаг на выбранной игроком позиции.
     *
     * @param xChoose - выбранная игроком координата X.
     * @param yChoose - выбранная игроком координата Y.
     * @return counter - возвращяет счётчик мин.
     */
    public int setFlag(int xChoose, int yChoose) {
        int counter = 0;
        if (gamePoly[xChoose][yChoose] == 9) {
            gamePoly[xChoose][yChoose] = 7;
            System.out.println("\nKEEP CALM AND SET FLAGS!");
            counter++;
        } else {
            System.out.println("But in the land is not mine!");
        }
        return counter;
    }

    /**
     * Метод, благодаря которому можно ходить по полю. Если в массиве число 9 -
     * Заменяем его на 55 (Что означает ВЗРЫВ) и выходим с true, во всех
     * остальных случаях, а именно 33 (Рядом мина) и 5 (Чисто) возвращяем false.
     *
     * @param xChoose - выбранная игроком координата X.
     * @param yChoose - выбранная игроком координата Y.
     * @return - true или false. Если true - игра окончена.
     */
    public boolean runOnPoly(int xChoose, int yChoose) {
        boolean result = false;
        switch (gamePoly[xChoose][yChoose]) {
            case 9:
                gamePoly[xChoose][yChoose] = 55;
                result = true;
                break;
            case 1:
                gamePoly[xChoose][yChoose] = 33;
                result = false;
                break;
            case 0:
                gamePoly[xChoose][yChoose] = 5;
                result = false;
                break;
            default:
                break;
        }
        return result;
    }

//    /**
//     * Метод, печатающий поле. Вначале пишеться по Х, потом по У. Сверху и сбоку
//     * цифры для более точного наступания на мину. Если попадается значение 7
//     * (!) - Рисуем Флаг; 5 (*) - Чисто; 33 (1) - Рядом мина; 55 (В) - Мина
//     * сдетонировала;Или же, если игроком ничего не было выбрано - рисуем #
//     */
//    public void cheatPoly() {
//        System.out.print("/  ");
//        for (int i = 0; i < xPos; i++) {
//            System.out.print((i + 1) + " ");
//        }
//        for (int j = 0; j < yPos; j++) {
//            if (j != 9) {
//                System.out.print("\n" + (j + 1) + "  ");
//            } else {
//                System.out.print("\n" + (j + 1) + " ");
//            }
//            for (int k = 0; k < xPos; k++) {
//                switch (gamePoly[k][j]) {
//                    case 7:
//                        System.out.print("!" + " ");
//                        break;
//                    case 5:
//                        System.out.print("*" + " ");
//                        break;
//                    case 33:
//                        System.out.print("1" + " ");
//                        break;
//                    case 55:
//                        System.out.print("B" + " ");
//                        break;
//                    default:
//                        System.out.print("#" + " ");
//                        break;
//                }
//            }
//        }
//        System.out.println();
//    }

    /**
     * Метод, печатающий поле. Вначале пишеться по Х, потом по У. Сверху и сбоку
     * цифры для более точного наступания на мину. Если попадается значение 7
     * (!) - Рисуем Флаг; 5 (*) - Чисто; 33 (1) - Рядом мина; 55 (В) - Мина
     * сдетонировала;Или же, если игроком ничего не было выбрано - рисуем #
     */
    public void printField() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("/  ");
        for (int k = 0; k < xPos; k++) {
            strBuffer.append((k + 1) + " ");
        }
        for (int i = 0; i < xPos; i++) {
            if (i != 9) {
                strBuffer.append("\n" + (i + 1) + "  ");
            } else {
                strBuffer.append("\n" + (i + 1) + " ");
            }
            for (int j = 0; j < yPos; j++) {
                switch (gamePoly[j][i]) {
                    case 7:
                        strBuffer.append("!" + " ");
                        break;
                    case 5:
                        strBuffer.append("*" + " ");
                        break;
                    case 33:
                        strBuffer.append("1" + " ");
                        break;
                    case 55:
                        strBuffer.append("B" + " ");
                        break;
                    default:
                        strBuffer.append("#" + " ");
                        break;
                }
            }
        }
        System.out.println(strBuffer);
    }
}
