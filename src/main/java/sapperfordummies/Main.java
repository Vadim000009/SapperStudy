package sapperfordummies;

/**
 * Надо сдать до дедлайна, поэтому такое говорящее название. Надо было сделать
 * до дедлайна.
 *
 * @author Vadim
 */
public class Main {

    public static void main(String[] args) {
        SettingsAndGame play = new SettingsAndGame();
        String str = new String();
        System.out.println("\tGreetings"
              + "\nPlease enter:"
              + "\n 'rules' - to read rules"
              + "\n 'settings' - to access to rules and set size field or mines"
              + "\n 'play' - to play the game"
              + "\n 'qqq' - to exit from game");
        str = play.strEntered();
        do {
            switch (str) {
                case "rules" -> {
                    play.rules();
                    str = "";
                }
                case "settings" -> {
                    play.callSettings(str);
                    str = "";
                }
                case "play" -> play.startGame();
                default -> {
                    System.out.print("Please, Re-enter: ");
                    str = play.strEntered();
                }
            }
        } while (!str.equals("qqq"));
    }
}
