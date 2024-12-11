package id.zulvani.math.basic.combinatorial;

public class Basic {

    public static void main(String[] args) throws Exception {
//        Basic b = new Basic();
//        String[] r = b.enumeration(new String[]{"A", "B", "C"}, (byte) 2);
//        for(String s:r) {
//            System.out.println(s);
//        }

        int x = 3;
        int y = 2;

        int c = 0;
        for(int i=0; i < y; i++) {
            for(int j=0; j < x; j++) {
                System.out.println(c);
                c++;
            }
        }

    }

    /**
     * <p>Enumerate characters based on provided characters.
     * For example: provided chars: A,B,C then construct 2 combination of these characters, will return:
     * AA, AB, AC
     * BA, BB, BC
     * CA, CB, CC
     * </p>
     * @param chars array of character
     * @param length length of string will be constructed by chars
     * @return String[] list of constructed chars
     */
    public String[] enumeration(String[] chars, byte length) throws Exception {
        if (length > chars.length) {
            throw new Exception("Invalid length");
        }

        int n = (int) Math.pow(chars.length, length);
        String[] result = new String[n];

        System.out.println(n);

        int c = 0;
        int r = 0;
        StringBuilder sb;
        for (int i =0 ;i < n; i++) {
            if (c == (chars.length)) {
                c = 0;
            }

            if (r == length) {
                r = 0;
            }
            String pred = chars[c];
            sb = new StringBuilder(pred);

//                sb.append(chars[j]);

                if (sb.length() == length) {
                    result[i] = sb.toString();
                    System.out.println(sb + ";" + i);
                    sb = new StringBuilder(pred);
                }

            c++;
                r++;
        }

        return result;
    }

}
