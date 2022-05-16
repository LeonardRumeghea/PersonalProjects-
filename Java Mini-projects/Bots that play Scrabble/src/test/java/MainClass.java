public class MainClass {

    public static void main(String[] args) {

        int index = 0;
        Game game = new Game();
        game.addPlayer(new Player("Player 1", ++index));
        game.addPlayer(new Player("Player 2", ++index));
        game.addPlayer(new Player("Player 3", ++index));

        game.play();
    }
}