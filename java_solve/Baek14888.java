import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek14888 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] nums;
    static int[] ops;
    static int[] selected = new int[4];
    static int[] ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ans = new int[N-1];
        nums = new int[N];
        ops = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            ops[i] = Integer.parseInt(st.nextToken());
        }
        oper(0, N);

        System.out.println(max);
        System.out.println(min);
    }
    static void oper(int idx, int N){
        if(Arrays.stream(selected).sum() == Arrays.stream(ops).sum()){
            int calc = nums[0];
            for(int i=0; i<ans.length; i++){
                int num = nums[i+1];
                switch(ans[i]){
                    case 0:
                        calc += num;
                        break;
                    case 1:
                        calc -= num;
                        break;
                    case 2:
                        calc *= num;
                        break;
                    case 3:
                        calc /= num;
                        break;
                }
            }
            if(calc>max)    max = calc;
            if(calc < min)   min = calc;
            return;
        }

        for(int i=0; i<4; i++){
            if(selected[i] == ops[i])   continue;
            selected[i]++;
            ans[idx] = i;
            oper(idx+1, N);
            selected[i]--;

        }

    }
}
