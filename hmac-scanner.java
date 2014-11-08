

package hmacimplementation;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;


public class hmac{

	private static final String SEP = "*"; //Specify a separator for the Hash
	
	//Hashing happens in this method
	public static String simpleHmac(String secret_key, String timestamp, String random_number, String message, String message_id) throws Exception {
		SecretKey sk = new SecretKeySpec(secret_key.getBytes(), "HmacMD5");
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(sk);
		String concat = timestamp + SEP + random_number + SEP + message + SEP + message_id;
		byte[] result = mac.doFinal(concat.getBytes());
		StringBuffer sb = new StringBuffer(result.length * 2);
		for(int i=0; i< result.length; i++) {
			if(((int) result[i] & 0xff) < 0x10)
			sb.append("0");
			sb.append(Long.toString((int) result[i] & 0xff, 16));
		}
		return sb.toString();
	}
	
	//Values are taken from the user and the encrypted Hash is printed 
	public static void main(String args[]) {
	
		String signature;
		try {
			Scanner scanner = new Scanner(System.in);
		    String a = scanner.nextLine();
		    String b = scanner.nextLine();
		    String c = scanner.nextLine();
		    String d = scanner.nextLine();
		    String e = scanner.nextLine();
			signature = hmac.simpleHmac(a,b,c,d,e);
 
			
			System.out.println(signature);
		}
		catch (Exception e) { e.printStackTrace(); }
	}
}
