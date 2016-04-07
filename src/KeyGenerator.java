import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class KeyGenerator {
	private BigInteger s;
	private BigInteger p;
	private final BigInteger g = new BigInteger("5");//
	private final int numBits = 500;
	
	private final SecureRandom rnd = new SecureRandom();
	private final int certainty = 10;
	
	public KeyGenerator() {
		this.s = getRandomProbablePrime();// generateRandomNum();
		this.p = getRandomProbablePrime();// getRandomSecurePrime();
	}
	
	private BigInteger generateRandomNum() {
		
		BigInteger temp = BigInteger.ZERO;
		while (temp.toString(2).length() < 500) {
			temp = new BigInteger(numBits, rnd);
		}
		return temp;
	}
	
	private BigInteger getRandomProbablePrime() {
		BigInteger temp = null;
		do {
			temp = new BigInteger(numBits, rnd);
			temp = temp.setBit(numBits - 1);
		} while(!temp.isProbablePrime(certainty));
		return temp;
	}
	
	public BigInteger getP() {
		return p;
	}
	
	public BigInteger getS() {
		return s;
	}
	
	public BigInteger getGToSModP() {
		return modularExp(g, s, p);
	}
	
	private BigInteger modularExp(BigInteger base, BigInteger exp, BigInteger mod) {
		BigInteger result = BigInteger.ONE;
		String bits = exp.toString(2);
		for (int i = bits.length() - 1; i >= 0; --i) {
			if(bits.charAt(i) == '1') {
				result = result.multiply(base);
				result = result.mod(mod);
			}
			base = base.multiply(base);
			base = base.mod(mod);
		}
		return result;
	}

	public static void main(String[] args) {
		KeyGenerator keyGen = new KeyGenerator();
		System.out.println(keyGen.getP());
		System.out.println(keyGen.getS());
		BigInteger result1 = keyGen.getGToSModP();
		System.out.println(result1);
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		String n = reader.next();
		BigInteger t = new BigInteger(n);
	}

}
