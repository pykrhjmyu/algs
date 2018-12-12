/* exercise for the KMP substring search method
implement this method based on 1.next function() 2.DFA

now only consider R=26('a'~'z')
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.*;

class KMP
{
	private static String txt;
	private static String mode;

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			txt = br.readLine();
			mode = br.readLine();
		}catch(IOException e){System.out.println("wrong!");}

		DFA test = new DFA(mode);
		for(int i=0,j=0;i<txt.length();i++){
			j = test.getNextStatus(txt.charAt(i),j);
			if(j == mode.length()){
				System.out.println("find!");break;}
		}
	}
}

class DFA
{
	String s;
	int[][] dfa;

	DFA(String str){
		s = str;
		dfa = new int[26][str.length()];
		build();
	}

	void build(){
		dfa[s.charAt(0)-'a'][0]=1;
		for(int j = 1, X = 0;j<s.length();j++){
			for(int i=0;i<26;i++){
				dfa[i][j] = dfa[i][X];
			}
			dfa[s.charAt(j)-'a'][j] = j+1;
			X = dfa[s.charAt(j)-'a'][X];
		}
	}

	public int getNextStatus(char c, int j){
		return dfa[c-'a'][j];
	}
}