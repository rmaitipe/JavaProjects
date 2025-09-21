package LeetCode;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Text_Justification_068 {
	/*
	 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left-justified, and no extra space is inserted between words.
Note:
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.
	 */
	public List<String> fullJustify(String[] word, int maxWidth) {
		//2 pointer approach
		int left=0;
		int right=1;
		int n = word.length;
		List<String> retVal=new ArrayList();

		while (left<n-1){
			int count=0;
			right=left+1;
			while ((word[left].length() + word[right].length()+ count)< maxWidth && right<n){
				count+=word[right].length()+1;//pre space
				right++;
			}
			// add left-> right to entry
			// pad -> uniform distribution f that
			StringBuilder sb = new StringBuilder(word[left]);
			StringBuilder temp = new StringBuilder();
			while (sb.length()+temp.length()<maxWidth){
				int extraMargin= maxWidth-(word[left].length()+count);
				int wordCount = right-1-left;
				int perMargin = extraMargin/wordCount;
				int modMargin =0;
				if (perMargin !=0){
					modMargin= extraMargin% perMargin;
				}

				for (int i=left+1;i<right;i++) {
					temp.append(" ");//guaranteed included in count
					int delta=perMargin;
					while (delta>0){
						temp.append(" ");
						delta--;
						if (modMargin>0){
							temp.append(" ");
						}
					}
					temp.append(word[i]);
				}
			}
			String abc= temp.toString();
			retVal.add(sb.append(abc).toString());
			left= right;
		}
		//left justify last line
		String last= retVal.remove(retVal.size()-1);
		String [] strArr= last.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int j=0;j<strArr.length-1;j++){
			sb.append(strArr[j].trim());
			if (j<strArr.length-2){
				sb.append(" ");
			}
		}
		return retVal;
	}

	public List<String> fullJustify2(String[] words, int maxWidth) {
		int currWidth =0;
		List<String> retList= new ArrayList<>();
		List<String> prevList= new ArrayList<>();
		for (int j=0;j<words.length;j++){
			String str= words[j];
			if (currWidth+str.length()+1> maxWidth) {
				//(write a line . empty prevList .add curr)
				int delta= maxWidth -(currWidth-prevList.size());// extra 16- This4 is2 an2 +3-3=8
				// divide by 0 proof
				int extra =0;
				int cascade = 0;
				if (prevList.size()>2) {
					extra = delta / (prevList.size() - 1);
					cascade = delta % (prevList.size() - 1);
				} else{
					extra = delta;
				}
				StringBuilder sb= new StringBuilder();
				for (String strIn: prevList){
					sb.append(strIn);
					for (int k=0;k<extra;k++){
						sb.append(" ");
					}
					if (cascade>0){
						sb.append(" ");
						cascade--;
					}
				}
				retList.add(sb.toString());
				prevList.clear();
				//delta/ listSize if not =0 extra space
				//delta%listSize add space to first iterations
				if (j==words.length-1){
					StringBuilder sb2= new StringBuilder();
					sb2.append(str);
					for (int i=str.length();i< maxWidth-str.length();i++){
						sb2.append(" ");
					}
					retList.add(sb2.toString());
				} else{
					prevList.add(str);
					currWidth=str.length()+1;
				}
			}
			else{
				prevList.add(str);
				currWidth+=str.length()+1;
				if (j==words.length-1){
					//write last line 1 space only
					StringBuilder sb= new StringBuilder();
					for (String strIn: prevList){
						sb.append(strIn);
						sb.append(" ");
					}
					retList.add(sb.toString());
					prevList=new ArrayList<>();
				}
			}
		}
		return retList;
	}

	public static void main(String args[]){
		String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth = 16;
		Text_Justification_068 ob = new Text_Justification_068();
        System.out.println(ob.fullJustify(words, maxWidth));
		List<String> out =ob.fullJustify2(words, maxWidth);
		out.stream().forEach(System.out::println);
    }

}
