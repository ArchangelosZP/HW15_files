import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("tolstoy_voyna-i-mir__xoraa_436421.txt");
        FileInputStream input = new FileInputStream(file);
        Scanner in = new Scanner(input);
        String st;
        String[] strT;
        Stream<String> strS = Stream.of();
        Map<String, Integer> map;
        Map<String, Integer> combinedMap = new HashMap<>();
        Integer ii =0;
        while (in.hasNext() ) {

            st = in.nextLine();
            st = clearSt(st);

            if (!st.equals("") & !st.equals(" ")) {
                strT = st.split(" ");
                strS = Stream.concat(strS, Arrays.stream(strT));
            }

            ii++;
            if (ii==10000) { // if not in portions then falls
                map = strS.collect(Collectors.toMap(s -> s, s -> 1, (a, b) -> a + 1));
                combinedMap = Stream.concat(combinedMap.entrySet().stream(), map.entrySet().stream())
                        .collect(Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.summingInt(Map.Entry::getValue)));
                strS = Arrays.stream(new String[] {});
                ii=0;
            }

        }

        File fileR = new File("result.txt");
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileR, false);
        Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        writer.write(combinedMap.toString());

       // System.out.println(combinedMap);

    }

    public static String clearSt(String st){
        st = st.replaceAll("русс", "");
        st = st.replaceAll("рассия", "");
        st = st.replaceAll("\\pP", "");
        st = st.replaceAll("  ", " ");
        st = st.replaceAll("   ", " ");
        return st;
    }

}