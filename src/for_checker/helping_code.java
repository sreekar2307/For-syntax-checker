package for_checker;

import java.util.regex.Pattern;
/**
 * DATE: 09-10-2018
 * @author Sreekar
 * @version 1.0v
 * This class contains all the helping methods to help for_checker
 *
 */
public class helping_code {

	/**
	 * a string in format .*[fF][oO][rR][{(\\[].* 
	 * @param str any string from the input.txt 
	 * @return  checks if the string is in the format for(exp1;exp2;exp3)
	 */
	public boolean check_initial(String str) {
		if(Pattern.matches("[f][o][r][(].*;.*;.*[)][{;]?", str))
			return true;
		return false;
	}
	/**
	 * exp3 of the for loop returned from the initial check is the parameter for this function
	 * @param str exp3
	 * @return if exp3 is in any of the one format (valid_declaration++/--) or (valid_declaration +/-/* = integer)
	 */
	public boolean check_exp3(String str) {
		if(str.length() == 0)
			return true;
		String params[] = str.split(",");
		boolean flag=false;

		for(int i =0;i<params.length;i++) {
			if(Pattern.matches("\\s*[a-zA-Z][a-zA-Z_0-9$]*[+-]{2}\\s*",params[i])||Pattern.matches("\\s*[a-zA-Z][a-zA-Z_0-9$]*\\s*[=]\\s*[a-zA-Z][a-zA-Z_0-9$]*\\s*[+-/*]\\s*[0-9]+\\s*",params[i])) {
				flag  = true;
			}else {
				flag = false;
				break;
			}
		}
		if(flag)
			return true;
		return false;
	}
	/**
	 * exp1 of the for loop returned from the initial check is the parameter for this function
	 * @param str exp1
	 * @return if exp1 is in any of the one format (int valid_declaration=1,...) or (valid_declaration=1,..)
	 */
	public boolean check_exp1(StringBuilder str) {
		if(str.length() == 0)
			return true;
		if(Pattern.matches("[i][n][t].*", str)){
			str.delete(0,3);
			String params[] = str.toString().split(",");
			for(int i=0;i<params.length;i++)
			{
				if(!check_params_1(params[i])) {
					return false;
				}
			}
			return true;
		}
		String params[] = str.toString().split(",");
		for(int i=0;i<params.length;i++)
		{
			if(!check_params_1(params[i])) {
				return false;
			}
		}
		return false;
	}
	/**
	 * int valid_declaration=1,... are the check cases for exp1 this function single declaration after splitting at "," separation 
	 * @param param valid_declaration
	 * @return  checks weather declaration is valid or not 
	 */
	public boolean check_params_1(String param) {
		if(Pattern.matches("\\s*[a-zA-Z][a-zA-Z_0-9$]*\\s*[=]\\s*[0-9]+\\s*", param))
			return true;
		return false;
	}
	/**
	 * exp2 of the for loop returned from the initial check is the parameter for this function
	 * @param string exp2
	 * @return if exp2 is in any of the one format (valid_declaration boolean_operator integer) or (integer boolean_operator integer)
	 */
	public boolean check_exp2(String string){
		if(string.length()==0)
			return true;
		if(Pattern.matches("\\s*[a-zA-Z][a-zA-Z_0-9$]*\\s*[><][=]?\\s*[0-9]+\\s*",string))
			return true;
		if(Pattern.matches("\\s*[0-9]*\\s*[><][=]?\\s*[0-9]+\\s*",string))
			return true;
		if(Pattern.matches("\\s*[0-9]*\\s*[=][=]\\s*[0-9]+\\s*",string))
			return true;
		if(Pattern.matches("\\s*[a-zA-Z][a-zA-Z_0-9$]*\\s*[=][=]?\\s*[0-9]+\\s*",string))
			return true;
		return false;
	}
}
