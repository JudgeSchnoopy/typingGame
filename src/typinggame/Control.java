package typinggame;


public class Control {

    private int levelNo;
    private int gameScore;
    
    public Control(){
        createMenu();
    }
    
    public void createMenu(){
        MainMenu main = new MainMenu();
        main.setVisible(true);
        try {
            synchronized(main.menuSelect){
                while(main.menuClick != true){
                    main.menuSelect.wait();
                }}
        } catch (InterruptedException e){
                    }
        switch(main.selection){
            case 1: main.dispose();
                    
                    this.startGame();
            case 2: ;
            case 3: System.exit(0);
        }
    }

    public void startGame(){
        WordList words = new WordList();  
        GameWindow gui = new GameWindow();
        gui.setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);
        gui.setVisible(true);          
        this.levelNo = 1;
        while (levelNo < 6){
            gui.levelNumber.setText("Level Number " + levelNo);
            String levelWord = words.getWord();
            Level current = new Level(levelNo, gui, levelWord);
            if (current.startLevel(gui) > 0){
                Correct correct = new Correct();
                correct.setVisible(true);
                try{
                    Thread.sleep(1400);
                } catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
                gameScore = gameScore + current.levelScore;
                correct.dispose();
                System.out.println("Level " + levelNo + " complete.");
                levelNo++;
                if (levelNo > 5){
                    gui.dispose();
                    Winner winner = new Winner();
                    winner.setScore(gameScore);
                    winner.setVisible(true);
                    try{
                        synchronized(winner.backToMenu){
                            while(winner.clicked != true){
                                winner.backToMenu.wait();
                            }}
                    } catch (InterruptedException e){
                        }
                    winner.dispose();
                    gameScore = 0;
                    createMenu();
                }
            }
            else {
                GameOver gameOver = new GameOver();
                gameOver.setVisible(true);
                gui.dispose();
                try{
                    synchronized(gameOver.backToMenu){
                        while(gameOver.clicked != true){
                            gameOver.backToMenu.wait();
                        }}
                } catch (InterruptedException e){
                    }
                gameOver.dispose();
                gameScore = 0;
                createMenu();
                System.out.println("Better luck next time.");
            }
        }
    }


}