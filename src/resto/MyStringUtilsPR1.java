package resto;

public class MyStringUtilsPR1 {
	
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}
	
	public static String centre(String tablero, int len){
		String out = String.format(" %"+len+"s %s %"+len+"s", "",tablero,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		return out.substring((int)start, (int)end);
	}
}
