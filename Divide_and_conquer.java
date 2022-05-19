import java.math.BigInteger;
import java.util.Scanner;

public class Divide_and_conquer {

	public static String mod_fibonacci(int N, BigInteger K) {
		
		final BigInteger[] fib_sequence = fib_num(N);
		
		String letter = get_letter(N, K, fib_sequence);
		
		return letter;
	}
	
	
	public static String get_letter(int N, BigInteger K, BigInteger[] fib_sequence) {
		
		if (N == 1) {
			return "X";
		}else if (N == 2) {
			return "Y";
		}
		
		BigInteger first_substring_size = fib_sequence[N-2];
		
		int compare_value = K.compareTo(first_substring_size);
		
		if (compare_value == 0 || compare_value == -1) {
			return get_letter(N-2, K, fib_sequence);
		}else {
			BigInteger J = K.subtract( first_substring_size );
			return get_letter(N-1, J, fib_sequence);
		}
		
	}
	
	
	public static BigInteger[] fib_num(int N) {
		BigInteger[] fib_sequence = new BigInteger[N+2];
		
		fib_sequence[1] = BigInteger.valueOf(1);
		fib_sequence[2] = BigInteger.valueOf(1);
		
		for (int i = 3; i <= N; i++) {
			fib_sequence[i] = fib_sequence[i-1].add(fib_sequence[i-2]);
		}
		
		return fib_sequence;
		
	}
	
	public static void main(String[] args) {

	}

}