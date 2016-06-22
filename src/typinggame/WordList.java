package typinggame;
import java.util.*;

public class WordList {

    private int wordNumber;
    private String[] wordList;
    private List<Integer> usedNumbers;
    private String currentWord;
    
    public WordList(){
        usedNumbers = new ArrayList<Integer>();
        DiffSelect diffselect = new DiffSelect();
        diffselect.setVisible(true);
        try{
            synchronized(diffselect.diffChosen){
                while(diffselect.diffClicked != true){
                    diffselect.diffChosen.wait();
                }
            }
        } catch (InterruptedException e){
            }
        wordNumber = 0;
        int listNum = diffselect.getDifficulty();
        System.out.println("Got list number " + listNum);
        switch(listNum){
            case 1: easyWords(); break;
            case 2: mediumWords(); break; 
            case 3: hardWords(); break;
        }
        diffselect.dispose();
        
    }
    
    public String getWord(){
        boolean getRandom = false;
        while (getRandom == false){
        Random random = new Random();
        int listLength = wordList.length;
        int thisNumber = random.nextInt(listLength);
        if (usedNumbers.contains(thisNumber)){
            System.out.println(thisNumber + " matches number in list.  getting new number.");
            //do nothing and just loop back to get a new number
            }
        else {
            this.currentWord = wordList[thisNumber];
            usedNumbers.add(thisNumber);
            getRandom = true;
        }}
        return currentWord;
    }
 
    public void easyWords(){
        wordList = new String[]{"CAT","DOG","FROG","KID","BEAR","DUCK","CAR","SAM","TREE","TOY","BAT","PAT","PUP","POP","SEE","HOP","PIG","ANT","DOOR","KIA","FIRE","EGG","RAM","JEEP","FORD","MOM","DAD","BABY","NAP","PARK"};
    }
    
    public void mediumWords(){
        wordList = new String[]{"HONDA","HOUSE","SCHOOL","MOVIE","TABLE","GRASS","PILLOW","BUNNY","POLICE","TRUCK","PICTURE","PHONE","CAMERA","GAME","SHEEP","REMOTE","HONK","EMMA","PLATE","DODGE","BROTHER","SISTER","FAMILY","GRANDPA","DRIVE","SLIDE"};
    }
    
    public void hardWords(){
        wordList = new String[]{"TELEVISION","GARAGE","ZOOTOPIA,","TOYOTA","BATHROOM","BEDROOM","TIMBUKTU","ELEPHANT","THEATER","BASEMENT","BICYCLE","CONTROLLER","MITSUBISHI","LINCOLN","HIGHWAY","AIRPLANE","BATAVIA","SIDEWALK","DRIVEWAY","ELEVATOR","LIBRARY"};
    }    
    
}

