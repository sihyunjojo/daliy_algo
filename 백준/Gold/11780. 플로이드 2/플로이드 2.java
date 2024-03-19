import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int MAX = 10000001;
        int[][] graph = new int[n+1][n+1];
        ArrayList<Integer>[][] last = new ArrayList[n+1][n+1];

        int a = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                last[i][j] = new ArrayList<>();
                if(i==j) {
                    graph[i][j] = 0;
                    continue;
                }
//                last[i][j].add(i);
                graph[i][j] = MAX;
            }
        }



        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (graph[from][to] > w) {
                graph[from][to] = w;
                last[from][to].clear();
                last[from][to].add(from);
            }
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i==j) continue;
                for (int k = 1; k <= n; k++) {
                    if (j==k) continue;
                    if (graph[j][k] > graph[j][i] + graph[i][k]){
                        graph[j][k] = graph[j][i] + graph[i][k];
                        last[j][k].clear();
                        for (int l = 0; l < last[j][i].size(); l++) {
                            last[j][k].add(last[j][i].get(l));
                        }
                        for (int l = 0; l < last[i][k].size(); l++) {
                            last[j][k].add(last[i][k].get(l));
                        }
                    }
                }
            }
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == MAX) {
                    System.out.print(0 + " ");
                    continue;
                }
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(last[i][j].isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.print(last[i][j].size() + 1 + " ");
                    for (Integer integer : last[i][j]) {
                        System.out.print(integer + " ");
                    }
                    System.out.println(j);
            }
        }
            }

    }
}