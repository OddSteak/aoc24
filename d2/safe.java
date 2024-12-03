import java.io.*;
import java.nio.file.*;
import java.util.*;

class S1 {
    static long procFile(String name) throws IOException {
        return Files.lines(Paths.get(name)).map(line -> Arrays.stream(line.split("\\s")).mapToInt(Integer::parseInt).boxed().toList())
        .filter(S1::verifyReport).count();
    }

    public static boolean isP1Safe(List<Integer> arr, Integer ign) {
        Set<Integer> hst = new HashSet<>();

        for (int i = 1; i < arr.size(); i++) {
            if (ign != null && ign == i) {
                continue;
            } else if (ign != null && i == ign + 1) {
                if (i - 2 >= 0)
                    hst.add(arr.get(i) - arr.get(i - 2));
            } else {
                hst.add(arr.get(i) - arr.get(i - 1));
            }
        }

        return hst.stream().allMatch(x -> x >= 1 && x <= 3) || hst.stream().allMatch(x -> x >= -3 && x <= -1);
    }

    public static boolean verifyReport(List<Integer> arr) {
        if (isP1Safe(arr, null))
            return true;
        for (int i = 0; i < arr.size(); i++) {
            if (isP1Safe(arr, i))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(procFile("s1_input"));
    }
}
