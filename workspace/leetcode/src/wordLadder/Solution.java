package wordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	private HashSet<String> allConjunctedWords(String str){
        char[] strArr = str.toCharArray();
        HashSet<String> conjuncted = new HashSet<String>();
        for (int i = 0; i < strArr.length; i++){
            char cur = strArr[i];
            for (int j = (int)'a'; j <= (int)'z'; j++){
                strArr[i] = (char)j;
                conjuncted.add(new String(strArr));
            }
            strArr[i] = cur;
        }
        
        conjuncted.remove(str);
        return conjuncted;
	}

}