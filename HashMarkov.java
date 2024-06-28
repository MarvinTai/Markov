import java.util.*;

public class HashMarkov implements MarkovInterface {

    public String[] myWords;		// Training text split into array of words 
	public Random myRandom;		// Random number generator
	public int myOrder;			// Length of WordGrams used
	public HashMap<WordGram,List<String>> myMap;
    public static String END_OF_TEXT = "*** ERROR ***"; 
	
    public HashMarkov(int order) {
	myOrder = order;
    myMap = new HashMap<>();
    myRandom = new Random ();
    }
	

    @Override
    public void setTraining(String text) {
        // TODO Auto-generated method stub
        myWords = text.split("\\s+");
        myMap.clear();
        WordGram currentWG = new WordGram(myWords, 0, myOrder);
        for (int i = myOrder; i<myWords.length; i++)
        {
           String currentword = myWords[i];
           if (!myMap.containsKey(currentWG))
           {
                myMap.put(currentWG, new ArrayList<>());
           }
            myMap.get(currentWG).add(currentword);
            //now shift every word by calling shift add
            currentWG = currentWG.shiftAdd(currentword);
        }
    }

    @Override
    public List<String> getFollows(WordGram wgram) {
        // TODO Auto-generated method stub
        return myMap.get(wgram);
        //throw new UnsupportedOperationException("Unimplemented method 'getFollows'");
    }

    private String getNextWord(WordGram wgram) {
		List<String> follows = getFollows(wgram);
		if (follows == null || follows.size() == 0) {
			return END_OF_TEXT;
		}
		else {
			int randomIndex = myRandom.nextInt(follows.size());
			return follows.get(randomIndex);
		}
	}

    @Override
    public String getRandomText(int length) {
        ArrayList<String> randomWords = new ArrayList<>(length);
		int index = myRandom.nextInt(myWords.length - myOrder + 1);
		WordGram current = new WordGram(myWords,index,myOrder);
		randomWords.add(current.toString());

		for(int k=0; k < length-myOrder; k += 1) {
			String nextWord = getNextWord(current);
			if (nextWord.equals(END_OF_TEXT)) {
				break;
			}
			randomWords.add(nextWord);
			current = current.shiftAdd(nextWord);
		}
		return String.join(" ", randomWords);
        // TODO Auto-generated method stub

        //throw new UnsupportedOperationException("Unimplemented method 'getRandomText'");
    }

    @Override
    public int getOrder() {
        return myOrder;
        //throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
    }

    @Override
    public void setSeed(long seed) {
        // TODO Auto-generated method stub
        myRandom.setSeed(seed);
        //throw new UnsupportedOperationException("Unimplemented method 'setSeed'");
    }
    
}
