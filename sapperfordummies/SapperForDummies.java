package sapperfordummies;

/**
 * Надо сдать до дедлайна, поэтому такое говорящее название. Надо было сделать
 * до дедлайна.
 *
 * @author Vadim
 */
public class SapperForDummies {

    public static void main(String[] args) {
        SettingsAndGame play = new SettingsAndGame();
        String str = new String();
        System.out.println("\tGreetings\nPlease enter:"
              + "\n 'rules' - to read rules"
              + "\n 'settings' - to access to rules and set size field or mines"
              + "\n 'play' - to play the game"
              + "\n 'qqq' - to exit from game");
        str = play.strEntered();
        do {
            if (str.equals("rules")) {
                play.rules();
                str = "";
            }
            if (str.equals("settings")) {
                play.callSettings(str);
                str = "";
            }

            if (str.equals("play")) {
                play.startGame();
            }
            if (!str.equals("settings") && !str.equals("play")
                    && !str.equals("qqq") && !str.equals("rules")) {
                System.out.print("Please, Re-enter: ");
                str = play.strEntered();
            }
        } while (!str.equals("qqq"));
    }
}
