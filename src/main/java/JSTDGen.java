import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class helps to generate a JSTD file to be used by JSTestDrive
 * http://code.google.com/p/js-test-driver/
 */
public class JSTDGen {

	private static String SERVE_PARAMETER = "-serve";
	
	public static void main(String args[]) {
		
		// check if no arguments
		if(args.length == 0 || Arrays.binarySearch(args, "-h")>=0 || Arrays.binarySearch(args, "--help")>=0) {
			ShowHelp();
			return;
		}
		
		// check required arguments
		if(!AreRequiredArgumentsPresent(args)) {
			ShowHelp();
			return;
		}
		
		// now continue to work.
		generateJSTD(GetArgumentValue(args, SERVE_PARAMETER));
	}
	
	

	private static void generateJSTD(String serveValues[]) {
		for(int i = 0;i<serveValues.length;i++) {
			System.out.println(serveValues[i]);
		}
	}
	
	private static boolean AreRequiredArgumentsPresent(Object[] args){
		int serveParamPos = Arrays.binarySearch(args, SERVE_PARAMETER);
		boolean serveParameterPresent = serveParamPos >= 0;
		boolean serveValuePresent = serveParamPos+1<args.length;
		return serveParameterPresent && serveValuePresent;
	}
	
	private static String[] GetArgumentValue(String[] args, String string) {
		int pos = Arrays.binarySearch(args, string);
		if(pos<0){
			return new String[]{};
		}
		
		if(pos+1>args.length-1) {
			return new String[]{};
		}
		
		ArrayList<String> values = new ArrayList<String>();
		int count=0;
		for(int i=pos+1;i<args.length;i++) {
			String value = args[i];
			if(value.startsWith("-")) { // looking for other parameter
				break;
			}
			count++;
			values.add(value);
		}
		
		String [] result = new String[count];
		values.toArray(result);
		return result;
	}

	
	public static void ShowHelp(){
		System.out.println("JSTD Help");
		System.out.println("---------");
	}
}
