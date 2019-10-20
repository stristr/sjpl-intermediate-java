class Decrypter {
    static char[] decrypt(char[] characters, int offset) {
        char[] result = characters.clone();
        for (int i = 0; i < result.length; ++i) {
            if (result[i] >= 'A' && result[i] <= 'Z') {
                result[i] = (char) ('A' + (result[i] - 'A' - offset + 26) % 26);
            }
        }

        return result;
    }
}
