import java.util.Arrays;

class CaeserCypher {
    public static char[] caeserEncrypt(char[] text, int shift) {
    int n = text.length;
    char[] encrypted = new char [n];
    int base = (int) 'a';
    for(int i = 0 ; i < n; i++) {
      int offset = (int)text[i] - base;
      int shifted = (offset + shift) %26;
      encrypted[i] = (char) (base+shifted);
    }

    return encrypted;
  }
 
  public static char[] caeserDecrypt(char[] text, int shift) {
    int n = text.length;
    char[] decrypted = new char [n];
    int base = (int) 'a';
    for(int i = 0 ; i < n; i++) {
      encrypted[i] = (char) ((int)text[i]-shift);
    }

    return decrypted;
  }
 
  public static void main(String[] args) {
    char[] plain = {'a', 't', 't', 'a', 'c','k', 't', 'h', 'e','m'};
    int shift = 2; 
    char[] encrypted = caeserEncrypt(plain, shift);
    System.out.println(String.copyValueOf(encrypted));

    char[] decrypt = caeserDecrypt(encrypted, shift);
    System.out.println(String.copyValueOf(decrypt));
  }
}