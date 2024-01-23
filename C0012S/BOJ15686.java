/*
15686. Gold 5 - 치킨 배달

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           82148	    40336     24366	         46.064%


    문제
        크기가 N×N인 도시가 있다. 도시는 1×1크기의 칸으로 나누어져 있다. 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 도시의 칸은 (r, c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸, 왼쪽에서부터 c번째 칸을 의미한다. r과 c는 1부터 시작한다.
        이 도시에 사는 사람들은 치킨을 매우 좋아한다. 따라서, 사람들은 "치킨 거리"라는 말을 주로 사용한다. 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
        임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.

        예를 들어, 아래와 같은 지도를 갖는 도시를 살펴보자.
            0 2 0 1 0
            1 0 1 0 0
            0 0 0 0 0
            0 0 0 1 1
            0 0 0 1 2

        0은 빈 칸, 1은 집, 2는 치킨집이다.
        (2, 1)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |2-1| + |1-2| = 2, (5, 5)에 있는 치킨집과의 거리는 |2-5| + |1-5| = 7이다. 따라서, (2, 1)에 있는 집의 치킨 거리는 2이다.
        (5, 4)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |5-1| + |4-2| = 6, (5, 5)에 있는 치킨집과의 거리는 |5-5| + |4-5| = 1이다. 따라서, (5, 4)에 있는 집의 치킨 거리는 1이다.

        이 도시에 있는 치킨집은 모두 같은 프랜차이즈이다. 프렌차이즈 본사에서는 수익을 증가시키기 위해 일부 치킨집을 폐업시키려고 한다. 오랜 연구 끝에 이 도시에서 가장 수익을 많이 낼 수 있는  치킨집의 개수는 최대 M개라는 사실을 알아내었다.
        도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
        둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.
        도시의 정보는 0, 1, 2로 이루어져 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다. 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.


    출력
        첫째 줄에 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.


    예제 입력 1
        5 3
        0 0 1 0 0
        0 0 2 0 1
        0 1 2 0 0
        0 0 1 0 0
        0 0 0 0 2
    예제 출력 1
        5

    예제 입력 2
        5 2
        0 2 0 1 0
        1 0 1 0 0
        0 0 0 0 0
        2 0 0 1 1
        2 2 0 1 2
    예제 출력 2
        10

    예제 입력 3
        5 1
        1 2 0 0 0
        1 2 0 0 0
        1 2 0 0 0
        1 2 0 0 0
        1 2 0 0 0
    예제 출력 3
        11

    예제 입력 4
        5 1
        1 2 0 2 1
        1 2 0 2 1
        1 2 0 2 1
        1 2 0 2 1
        1 2 0 2 1
    예제 출력 4
        32


    알고리즘 분류
        구현
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 17144KB
// 시간 : 200ms
// 코드 길이 : 4292B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {
    static int N; // 도시의 가로와 세로 길이 (2 ≤ N ≤ 50)
    static int M; // 도시에서 가장 수익을 많이 낼 수 있는 치킨집의 최대 개수 (1 ≤ M ≤ 13)
    static int city[][]; // 도시의 정보를 저장하는 배열
    static ArrayList<int[]> homeLocation; // 현재 도시에 있는 집의 좌표를 저장하는 리스트
    static ArrayList<int[]> chickenMarketLocation; // 현재 도시에 있는 치킨집의 좌표를 저장하는 리스트
    static int chickenMarketNum; // 현재 도시에 있는 치킨집의 개수
    static boolean isSelected[]; // 현재 도시에 있는 치킨집 중 폐업시키지 않을 치킨집으로 선택한 여부를 저장하는 배열
    static int chickenDistanceSum; // 도시의 치킨 거리
    static int minCityChickenDistance; // 도시의 치킨 거리의 최솟값

    public static void calculateIndividualChickenDistance(int chickenLocationList[][]) { // 각 집에서 치킨 거리의 최솟값(선택한 치킨집들과의 거리를 구해서 나온 거리들 중 가장 작은 값)을 구한 후, 도시의 치킨 거리를 구하는 메서드
        for (int h = 0, homeNum = homeLocation.size(); h < homeNum; h++) {
            int chickenDistance = Integer.MAX_VALUE; // 한 집의 치킨 거리
            int home[] = homeLocation.get(h);

            for (int c = 0; c < M; c++) { // 한 집의 치킨 거리 최솟값 구하기
                chickenDistance = Math.min(chickenDistance, Math.abs(chickenLocationList[c][0] - home[0]) + Math.abs(chickenLocationList[c][1] - home[1]));
            }

            chickenDistanceSum += chickenDistance;
        }
    }

    public static void selectChickenMarket(int start, int selectedNum) { // 현재 도시에 있는 치킨집 중 M 개를 선택한 치킨집 조합을 구하고, 그중 도시의 치킨 거리의 최솟값을 구하는 메서드
        if (selectedNum >= M) {
            int chickenLocationList[][] = new int[M][2]; // 선택한 치킨집들의 좌표를 저장하는 배열
            int selectedIndex = 0;
            for (int s = 0; s < chickenMarketNum; s++) {
                if (isSelected[s]) {
                    chickenLocationList[selectedIndex++] = chickenMarketLocation.get(s);
                }
            }

            calculateIndividualChickenDistance(chickenLocationList); // 선택한 치킨집들이 존재하는 도시의 치킨 거리 계산
            minCityChickenDistance = Math.min(minCityChickenDistance, chickenDistanceSum); // 도시의 치킨 거리의 최솟값 구하기
            chickenDistanceSum = 0; // 도시의 치킨 거리 초기화

            return;
        }

        for (int k = start; k < chickenMarketNum; k++) {
            if (!isSelected[k]) {
                isSelected[k] = true;
                selectChickenMarket(k + 1, selectedNum + 1);
                isSelected[k] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        city = new int[N][N];
        homeLocation = new ArrayList<>();
        chickenMarketLocation = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(token.nextToken());

                if (city[i][j] == 1) {
                    homeLocation.add(new int[] {i, j});
                }

                if (city[i][j] == 2) {
                    chickenMarketLocation.add(new int[] {i, j});
                }
            }
        }

        chickenMarketNum = chickenMarketLocation.size();
        isSelected = new boolean[chickenMarketNum];
        minCityChickenDistance = Integer.MAX_VALUE;
        selectChickenMarket(0, 0);

        System.out.println(minCityChickenDistance);
    }
}
