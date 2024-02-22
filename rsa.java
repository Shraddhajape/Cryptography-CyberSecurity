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

// output
// Choose p = 3 and q = 11
// Compute n = p * q = 3 * 11 = 33
// Compute φ(n) = (p - 1) * (q - 1) = 2 * 10 = 20
// Choose e such that 1 < e < φ(n) and e and φ (n) are coprime. Let e = 7
// Compute a value for d such that (d * e) % φ(n) = 1. One solution is d = 3 [(3 * 7) % 20 = 1]
// Public key is (e, n) => (7, 33)
// Private key is (d, n) => (3, 33)
// The encryption of m = 2 is c = 27 % 33 = 29
// The decryption of c = 29 is m = 293 % 33 = 2