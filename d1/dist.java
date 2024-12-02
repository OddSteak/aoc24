import java.io.*;
import java.util.*;

class S1 {
    public static int procList(ArrayList<ArrayList<Integer>> list) {
        Collections.sort(list.get(0));
        Collections.sort(list.get(1));

        int sim = 0, curNum = -1, curSim = -1;

        for (int i = 0; i < list.get(0).size(); i++) {
            int newNum = list.get(0).get(i);
            if (newNum == curNum) {
                sim += curSim;
                continue;
            }

            int count = 0;
            for (int j = 0; j < list.get(1).size(); j++) {
                int simNum = list.get(1).get(j);
                if (simNum > newNum) {
                    break;
                } else if (simNum == newNum) {
                    count++;
                }
            }

            int res = count * newNum;
            sim += res;
            curSim = res;
            curNum = newNum;
        }

        return sim;
    }

    public static ArrayList<ArrayList<Integer>> procfile(String name) throws IOException {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());

        BufferedReader br = new BufferedReader(new FileReader(name));
        String line;
        while ((line = br.readLine()) != null) {
            String[] nums = line.split("   ");
            res.get(0).add(Integer.parseInt(nums[0]));
            res.get(1).add(Integer.parseInt(nums[1]));
        }

        br.close();
        return res;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(procList(procfile("s1_input")));
    }
}
