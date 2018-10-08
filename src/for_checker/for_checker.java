package for_checker;
/**
 @mainpage For syntax checker
 @section intro_sec Introduction
 This java code checks the syntax of the for loop and gives output were is the mistake
 @bug Works only for certain cases of the input 
 @section compile_sec Compilation
 @sub_section STEP 1: 
 Enter the input in the input.txt file 

 @sub_section STEP 2: 
 compile as javac or_checker.java

 @sub_section STEP 3:
 run as java for_checker

*/

import java.io.*;
import java.util.regex.Pattern;
/**
 * DATE: 09-10-2018
 * @author Sreekar
 * @version 1.0v
 * This is the main class
 *
 */
public class for_checker {
   /**
    * @param args Array of environmental String arguments
    * @throws IOException this IOException because of the readLine method
    */
	public static void main(String[] args) throws IOException {	
		/**
		 * This field is used to read each line from the file input.txt
		 */
		 BufferedReader br  = new BufferedReader( new FileReader( new File("input.txt")));
	    /**
	     * This stores a line read by BufferedReader from input.txt
	     */
		 String str=br.readLine();
		 helping_code lg =new helping_code();
		for(int i=0;str!=null;i++) {
			if(Pattern.matches(".*[fF][oO][rR][{(\\[].*", str))
			{
				if(!lg.check_initial(str)) {
					System.out.print(str + " at line number " + (i+1));
					System.out.println(":  is not in the form for(exp1;exp2;exp3)\n");
					str = br.readLine();
					continue;
				}
				String[] expressions = str.split(";");
				StringBuilder exp1= new StringBuilder(expressions[0]);
				exp1.delete(0, 4);
				if(!lg.check_exp1(exp1)){
					System.out.println(str + " at line number " + (i+1)+ ":  error in Expression one\n");
					str = br.readLine();
					continue;
				}
				if(!lg.check_exp2(expressions[1])){
					System.out.println(str + " at line number " + (i+1)+ ":  error in Expression two\n");
					str = br.readLine();
					continue;
				}
				if(!lg.check_exp3(expressions[2].substring(0, expressions[2].length()-1))){
					System.out.println(str + " at line number " + (i+1)+ ":  error in Expression three\n");
					str = br.readLine();
					continue;
				}
				System.out.println(str + " at line number " + (i+1)+ ": is a valid for statement\n");
			}
			str = br.readLine();
		}
		br.close();
	}
}
