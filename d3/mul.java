import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

class Day03 {
    public static void main(String[] args) throws IOException {
        System.out.println(procFile("input"));
    }

    public static int procline(String line) {
        int sum = 0;

        for (int i = 0; i < line.length(); i++) {
            int index = line.indexOf("mul(", i);
            if (index == -1)
                break;
            int endIdx = line.indexOf(")", index);

            int indexDo = line.lastIndexOf("do()", index);
            int indexDont = line.lastIndexOf("don't()", index);

            if (indexDo < indexDont) {
                i = index + 3;
                continue;
            }
            int res = mul(line.substring(index + 4, endIdx));
            if (res == -1) {
                i = index + 3;
                continue;
            }
            sum += res;

            i = endIdx;
        }

        return sum;
    }

    public static int mul(String sub) {
        List<Integer> nums;
        try {
            nums = Arrays.stream(sub.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        } catch (Exception e) {
            return -1;
        }
        if (nums.size() != 2)
            return -1;

        return nums.get(0) * nums.get(1);
    }

    public static int procFile(String name) throws IOException {
        return procline(new String(Files.readAllBytes(Paths.get(name)), StandardCharsets.UTF_8));
    }
}
