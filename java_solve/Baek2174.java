import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2174 {
    static int[][] map;
    static int A, B, N, M;
    static ArrayList<robot> r_list = new ArrayList<>();
    static ArrayList<order> o_list = new ArrayList<>();
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static boolean flag;
    static class robot{
        int x;
        int y;
        char dir;
        public robot(int x, int y, char dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static class order{
        int num;
        char alpa;
        int repeat;
        public order(int num, char alpa, int repeat){
            this.num = num;
            this.alpa = alpa;
            this.repeat = repeat;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B+1][A+1];

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //로봇 데이터 받기
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y= B+1-Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            robot temp = new robot(x, y, dir);
            r_list.add(temp);
            map[y][x] = i;
        }
        for(int i=0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            char alpa = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());
            order temp = new order(num, alpa, repeat);
            o_list.add(temp);
        }
        // for(int[] arr: map){
        //     System.out.println(Arrays.toString(arr));
        // }
        check();
        if(!flag){
            System.out.println("OK");  
        }
        // System.out.println("========================");
        // for(int[] arr: map){
        //         System.out.println(Arrays.toString(arr));
        //     }

    }  
    static void check(){
        int[][] copyMap = new int[B+1][A+1];
        for(int i=0; i<B+1; i++)
            copyMap[i] = Arrays.copyOf(map[i], A+1);

        for(int i=0; i<M; i++){//명령어의 수만큼 
            int r_num = o_list.get(i).num-1;
            int repeat = o_list.get(i).repeat;
            char alpa = o_list.get(i).alpa;
            //로봇 위치와 방향 파악
            int x = r_list.get(r_num).x;
            int y = r_list.get(r_num).y;
            char dir = r_list.get(r_num).dir;
            // System.out.println("===========");
            // System.out.println("x: "+x+"y: "+y+"dir: "+dir);
            
            //명령어 시행 가능성 탐색
            while(repeat!=0){
                impl(r_list.get(r_num).dir, alpa, r_num, r_list.get(r_num).x, r_list.get(r_num).y);//계속 같은 x,y만 보내진다.
                // System.out.println("dir: "+r_list.get(r_num).dir);
                if(flag)    return;
                repeat--;
            }
        }
    }
    
    
    static void impl(int dir, char alpa, int r_num, int x, int y){
        switch(dir){
            case 'N':
                if(alpa=='L'){
                    r_list.get(r_num).dir = 'W';
                    }
                if(alpa=='R'){
                    r_list.get(r_num).dir = 'E';
                }
                if(alpa=='F'){
                    int dy = y+move[0][0];
                    int dx = x+move[0][1];
                    if(dy<1||dy>=B+1||dx<1||dx>=A+1){
                        System.out.println("Robot "+(r_num+1)+" crashes into the wall");
                        flag=true;
                        return;
                    }
                    if(map[dy][dx] !=0){
                        System.out.println("Robot "+ (r_num+1) +" crashes into robot "+map[dy][dx]);
                        flag=true;
                        return;
                    }
                    map[y][x] = 0;
                    map[dy][dx] = r_num+1;
                    r_list.get(r_num).x = dx;
                    r_list.get(r_num).y = dy;
                    // for(int[] arr: map){
                    //     System.out.println(Arrays.toString(arr));
                    // }
                    // System.out.println("===========");
            }
                break;
            case 'E':
                if(alpa=='L'){
                    r_list.get(r_num).dir = 'N';
                }
                if(alpa=='R'){
                    r_list.get(r_num).dir = 'S';
                }
                if(alpa=='F'){
                    int dy = y+move[1][0];
                    int dx = x+move[1][1];
                    if(dy<1||dy>=B+1||dx<1||dx>=A+1){
                        System.out.println("Robot "+(r_num+1)+" crashes into the wall");
                        flag=true;
                        return;
                    }
                    if(map[dy][dx] !=0){
                        System.out.println("Robot "+ (r_num+1) +" crashes into robot "+map[dy][dx]);
                        flag=true;
                        return;
                    }
                    map[y][x] = 0;
                    map[dy][dx] = r_num+1;
                    r_list.get(r_num).x = dx;
                    r_list.get(r_num).y = dy;
                    // for(int[] arr: map){
                    //     System.out.println(Arrays.toString(arr));
                    // }
                    // System.out.println("===========");
                }
                break;
            case 'S':
                if(alpa=='L'){
                    r_list.get(r_num).dir = 'E';
                }
                if(alpa=='R'){
                    r_list.get(r_num).dir = 'W';
                }
                
                if(alpa=='F'){
                    int dy = y+move[2][0];
                    int dx = x+move[2][1];
                    if(dy<1||dy>=B+1||dx<1||dx>=A+1){
                        System.out.println("Robot "+(r_num+1)+" crashes into the wall");
                        flag=true;
                        return;
                    }
                    if(map[dy][dx] !=0){
                        System.out.println("Robot "+ (r_num+1) +" crashes into robot "+map[dy][dx]);
                        flag=true;
                        return;
                    }
                    map[y][x] = 0;
                    map[dy][dx] = r_num+1;
                    r_list.get(r_num).x = dx;
                    r_list.get(r_num).y = dy;
                    // for(int[] arr: map){
                    //     System.out.println(Arrays.toString(arr));
                    // }
                    // System.out.println("===========");
                }
                break;
            case 'W':
                if(alpa=='L'){
                    r_list.get(r_num).dir = 'S';
                }
                if(alpa=='R'){
                    r_list.get(r_num).dir = 'N';
                }
                if(alpa=='F'){
                    int dy = y+move[3][0];
                    int dx = x+move[3][1];
                    if(dy<1||dy>=B+1||dx<1||dx>=A+1){
                        System.out.println("Robot "+(r_num+1)+" crashes into the wall");
                        flag=true;
                        return;
                    }
                    if(map[dy][dx] !=0){
                        System.out.println("Robot "+ (r_num+1) +" crashes into robot "+map[dy][dx]);
                        flag=true;
                        return;
                    }
                    map[y][x] = 0;
                    map[dy][dx] = r_num+1;
                    r_list.get(r_num).x = dx;
                    r_list.get(r_num).y = dy;
                    // for(int[] arr: map){
                    //     System.out.println(Arrays.toString(arr));
                    // }
                    // System.out.println("===========");
                }
            
        }
    }
}
