package TheMonocycle10047;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-5-18.
 */
public class Main {
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {-1, 0, 1, 0};
    public static class State{
        int x;
        int y;
        int dir;
        int time;
        int color;
        public State(int x, int y, int dir, int time, int color){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
            this.color = color;
        }
    }
    public static void solve(Scanner sc, int caseCount){
        int n = sc.nextInt();
        int m = sc.nextInt();
        if(n == 0 && m == 0){
            return;
        }

        System.out.println("Case #" + caseCount);
        char[][] board = new char[n][m];
        boolean[][][][] mark = new boolean[n][m][4][5]; // 0 North, 1 East, 2 South, 3 West
        for(int i = 0; i < n; i++){
            board[i] = sc.next().toCharArray();
        }
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'S'){
                    startX = i;
                    startY = j;
                }
                if(board[i][j] == 'T'){
                    endX = i;
                    endY = j;
                }
            }
        }

        Queue<State> q = new LinkedList<State>();
        q.add(new State(startX, startY, 0, 0, 0));
        mark[startX][startY][0][0] = true;

        while(!q.isEmpty()){
            State curState = q.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curDir = curState.dir;
            int curTime = curState.time;
            int curColor = curState.color;
            if(curX == endX && curY == endY && curColor == 0){
                System.out.println("minimum time = " + curTime + " sec");
                return;
            }

            for(int dif = -1; dif <=1; dif++){
                int newDir = (curDir + dif) % 4;
                if(newDir < 0){
                    newDir += 4;
                }
                int newX = curX;
                int newY = curY;
                int newColor = curColor;
                if(dif == 0){
                    newX += dx[newDir];
                    newY += dy[newDir];
                    newColor ++;
                }
                newColor = newColor % 5;
                if(!isOutOfRange(newX, newY, n, m) && board[newX][newY] != '#' &&
                        !mark[newX][newY][newDir][newColor]){
                    q.add(new State(newX, newY, newDir, curTime+1, newColor));
                    mark[newX][newY][newDir][newColor] = true;
                }
            }
        }

        System.out.println("destination not reachable");
    }

    public static boolean isOutOfRange(int x, int y, int n, int m){
        if(x < 0 || x >=n || y < 0 || y >=m){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = 1;
        while(sc.hasNext()){
            if(count != 1){
                System.out.println();
            }
            solve(sc, count);
            count++;
        }
        sc.close();
    }
}
