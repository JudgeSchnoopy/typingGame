package typinggame;

public class Level {
private final String targetWord;
private String inputWord;
private int missed;
public int levelScore;

    // initializes the level, sets starting variables
    public Level(int levelNumber, GameWindow gui, String word) {
        this.targetWord = word;
        this.missed = 0;
        gui.setNewWord(targetWord);
    }
    
    // Runs getInput and compareWords methods, returns 1 if the level is complete
    // or 0 if game over is reached (0 chances left).
    public int startLevel(GameWindow gui){
        System.out.println(targetWord);
        getInput(gui);
        while (this.compareWords() < 1){
            if (this.missed == 3){
                System.out.println("GAME OVER MAN");
                return 0;
            }
            getInput(gui);
        }
        levelScore = targetWord.length() - (missed*2);
        return 1;        
    }

    // retrieves input from GameWindow
    public void getInput(GameWindow gui){
        try {
            synchronized(gui.inputNow){
                while(gui.inputYes != true){
                    gui.inputNow.wait();
                }}
        } catch (InterruptedException e){
                }
        this.inputWord = gui.getInput().toUpperCase();
    }
    
    // compares input with original word.  returns 1 if match, 0 if not.
    public int compareWords(){
        if (inputWord.equals(targetWord)) {
            return 1;
        }
        else {
            Wrong wrong = new Wrong();
            wrong.setVisible(true);
            wrong.triesLeft.setText("You have " + (2-missed) + " chances left!");
            missed++;
            try{
                Thread.sleep(1700);
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            wrong.dispose();
            return 0;
        }
    }
}
