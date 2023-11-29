import java.util.Scanner;
import java.util.Arrays;

public class Question {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int r = in.nextInt();
		int p = in.nextInt();

		int total = n;
		int dias = 0;

		while (total < p) {
		    n *= r;
		    total += n;
		    dias += 1;
		}

		System.out.printf("%d\n", dias);

	}
}