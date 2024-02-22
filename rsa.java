import java.util.Scanner;

public class rsa {

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int p, q, e, d, n, x;

        System.out.println("ENTER TWO PRIME NUMBERS P & Q ::");
        p = sc.nextInt();
        q = sc.nextInt();
        System.out.println("ENTER VALUE OF e AS PUBLIC KEY ::");
        e = sc.nextInt();

        if (isPrime(p) && isPrime(q)) {
            n = p * q;
            x = (p - 1) * (q - 1);

            if (e > 1 && e < x && gcd(e, x) == 1) {
                // Calculate private key (d)
                d = modInverse(e, x);

                int ptext;
                System.out.println("ENTER PLAIN TEXT TO CONVERT INTO CIPHER TEXT :: ");
                ptext = sc.nextInt();

                // Encrypt
                int cipher = (int) Math.pow(ptext, e) % n;

                System.out.println("CIPHERTEXT IS :: " + cipher);
                System.out.println("PRIVATE KEY IS :: " + d);
            } else {
                System.out.println("Invalid public key (e).");
            }
        } else {
            System.out.println("Both P and Q must be prime numbers.");
        }
    }
}
