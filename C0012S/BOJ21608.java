/*
21608. Gold 5 - 상어 초등학교

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        1024 MB          19813	    8572      5878	         40.656%


    문제
        상어 초등학교에는 교실이 하나 있고, 교실은 N×N 크기의 격자로 나타낼 수 있다. 학교에 다니는 학생의 수는 N^2명이다. 오늘은 모든 학생의 자리를 정하는 날이다. 학생은 1번부터 N^2번까지 번호가 매겨져 있고, (r, c)는 r행 c열을 의미한다. 교실의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.

        선생님은 학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명도 모두 조사했다. 이제 다음과 같은 규칙을 이용해 정해진 순서대로 학생의 자리를 정하려고 한다. 한 칸에는 학생 한 명의 자리만 있을 수 있고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다.
            1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
            2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
            3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

        예를 들어, N = 3이고, 학생 N^2명의 순서와 각 학생이 좋아하는 학생이 다음과 같은 경우를 생각해보자.
            학생의 번호	좋아하는 학생의 번호
                4	        2, 5, 1, 7
                3	        1, 9, 4, 5
                9	        8, 1, 2, 3
                8	        1, 9, 3, 4
                7	        2, 3, 4, 8
                1	        9, 2, 5, 7
                6	        5, 2, 3, 4
                5	        1, 9, 2, 8
                2	        9, 3, 1, 4

        가장 먼저, 4번 학생의 자리를 정해야 한다. 현재 교실의 모든 칸은 빈 칸이다. 2번 조건에 의해 인접한 칸 중에서 비어있는 칸이 가장 많은 칸인 (2, 2)이 4번 학생의 자리가 된다.
            □   □   □
            □ 	4   □
            □   □   □

        다음 학생은 3번이다. 1번 조건을 만족하는 칸은 (1, 2), (2, 1), (2, 3), (3, 2) 이다. 이 칸은 모두 비어있는 인접한 칸이 2개이다. 따라서, 3번 조건에 의해 (1, 2)가 3번 학생의 자리가 된다.
            □ 	3   □
            □ 	4   □
            □   □   □

        다음은 9번 학생이다. 9번 학생이 좋아하는 학생의 번호는 8, 1, 2, 3이고, 이 중에 3은 자리에 앉아있다. 좋아하는 학생이 가장 많이 인접한 칸은 (1, 1), (1, 3)이다. 두 칸 모두 비어있는 인접한 칸이 1개이고, 행의 번호도 1이다. 따라서, 3번 조건에 의해 (1, 1)이 9번 학생의 자리가 된다.
            9	3   □
            □ 	4   □
            □   □   □

        이번에 자리를 정할 학생은 8번 학생이다. (2, 1)이 8번 학생이 좋아하는 학생과 가장 많이 인접한 칸이기 때문에, 여기가 그 학생의 자리이다.
            9	3   □
            8	4   □
            □   □   □

        7번 학생의 자리를 정해보자. 1번 조건을 만족하는 칸은 (1, 3), (2, 3), (3, 1), (3, 2)로 총 4개가 있고, 비어있는 칸과 가장 많이 인접한 칸은 (2, 3), (3, 2)이다. 행의 번호가 작은 (2, 3)이 7번 학생의 자리가 된다.
            9	3   □
            8	4	7
            □   □   □

        이런식으로 학생의 자리를 모두 정하면 다음과 같다.
            9	3	2
            8	4	7
            5	6	1

        이제 학생의 만족도를 구해야 한다. 학생의 만족도는 자리 배치가 모두 끝난 후에 구할 수 있다. 학생의 만족도를 구하려면 그 학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해야 한다. 그 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.
        학생의 만족도의 총 합을 구해보자.


    입력
        첫째 줄에 N이 주어진다. 둘째 줄부터 N^2개의 줄에 학생의 번호와 그 학생이 좋아하는 학생 4명의 번호가 한 줄에 하나씩 선생님이 자리를 정할 순서대로 주어진다.
        학생의 번호는 중복되지 않으며, 어떤 학생이 좋아하는 학생 4명은 모두 다른 학생으로 이루어져 있다. 입력으로 주어지는 학생의 번호, 좋아하는 학생의 번호는 N2보다 작거나 같은 자연수이다. 어떤 학생이 자기 자신을 좋아하는 경우는 없다.


    출력
        첫째 줄에 학생의 만족도의 총 합을 출력한다.


    제한
        · 3 ≤ N ≤ 20


    예제 입력 1
        3
        4 2 5 1 7
        3 1 9 4 5
        9 8 1 2 3
        8 1 9 3 4
        7 2 3 4 8
        1 9 2 5 7
        6 5 2 3 4
        5 1 9 2 8
        2 9 3 1 4
    예제 출력 1
        54

    예제 입력 2
        3
        4 2 5 1 7
        2 1 9 4 5
        5 8 1 4 3
        1 2 9 3 4
        7 2 3 4 8
        9 8 4 5 7
        6 5 2 3 4
        8 4 9 2 1
        3 9 2 1 4
    예제 출력 2
        1053


    알고리즘 분류
        구현
*/


// 메모리 : 25004KB
// 시간 : 348ms
// 코드 길이 : 8425B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21608 {
    static int N; // 교실의 가로와 세로 길이 (3 ≤ N ≤ 20)
    static int studentNum; // 학생의 수  // N^2
    static int seat[][]; // 상어 초등학교 좌석
    static int studentOrder[]; // 자리를 정할 학생의 순서를 저장하는 배열
    static ArrayList<Integer> favoriteStudent[]; // 해당 인덱스의 학생이 좋아하는 학생 리스트를 저장하는 배열
    static ArrayList<Integer[]> where; // 자리를 정할 학생이 앉을 수 있는 좌석의 정보를 저장하는 리스트  // {행, 열, 좋아하는 학생의 수, 비어 있는 인접한 칸의 수}의 정보를 가진 배열을 저장하는 리스트
    static int dr[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dc[] = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static boolean check(int r, int c) { // 해당 좌표가 좌석의 범위를 벗어났는지 검사하는 메서드
        if (r >= 0 && r < N && c >= 0 && c < N) {
            return true;
        }

        return false;
    }

    public static int calculateFavoriteSeatNum(int studentIndex, int nr, int nc, int favoriteSeatNum) { // (nr, nc) 좌표의 학생이 studentIndex 학생의 인접한 칸에 있는 좋아하는 학생일 경우, 인접한 칸에 있는 좋아하는 학생의 수를 증가시키는 메서드
        if (check(nr, nc) && (seat[nr][nc] != 0) && favoriteStudent[studentIndex].contains(seat[nr][nc])) { // 인접한 칸의 좌표가 좌석의 범위를 벗어나지 않고, 선택된 좌석이며, studentIndex 학생이 좋아하는 학생일 경우
            favoriteSeatNum += 1;
        }

        return favoriteSeatNum;
    }

    public static int calculateNearSeatNum(int nr, int nc, int nearSeatNum) { // 해당 좌표가 비어 있는 인접한 칸의 좌표일 경우, 비어 있는 인접한 칸의 개수를 증가시키는 메서드
        if (check(nr, nc) && (seat[nr][nc] == 0)) { // 해당 인접한 칸의 좌표가 좌석의 범위를 벗어나지 않고, 비어 있는 칸일 경우
            nearSeatNum += 1;
        }

        return nearSeatNum;
    }

    public static void calculateSeatInformation(int studentIndex) { // 학생의 자리를 정하기 위해 좌석의 정보를 구하는 메서드
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (seat[k][l] == 0) { // 선택되지 않은 좌석일 경우
                    int favoriteSeatNum = 0; // 인접한 칸에 있는 좋아하는 학생의 수
                    int nearSeatNum = 0; // 비어 있는 인접한 칸의 수
                    for (int d = 0; d < 4; d++) {
                        int nr = k + dr[d];
                        int nc = l + dc[d];

                        favoriteSeatNum = calculateFavoriteSeatNum(studentIndex, nr, nc, favoriteSeatNum);

                        nearSeatNum = calculateNearSeatNum(nr, nc, nearSeatNum);
                    }

                    where.add(new Integer[] {k, l, favoriteSeatNum, nearSeatNum});
                }
            }
        }
    }

    public static void sortCanBeSelectedSeat() { // 자리를 정할 학생이 앉을 수 있는 좌석의 정보 리스트를 문제의 조건에 맞게 정렬하는 메서드
        Collections.sort(where, new Comparator<Integer[]>() { // 인접한 칸에 있는 좋아하는 학생의 수가 많고, 비어 있는 인접한 칸의 수가 많고, 행의 번호가 작고, 열의 번호가 작은 순으로 정렬
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                int favoriteNum1 = o1[2]; // 인접한 칸에 있는 좋아하는 학생의 수
                int favoriteNum2 = o2[2];

                if (favoriteNum1 > favoriteNum2) {
                    return -1;
                }
                else if (favoriteNum1 < favoriteNum2) {
                    return 1;
                }
                else {
                    int nearNum1 = o1[3]; // 비어 있는 인접한 칸의 수
                    int nearNum2 = o2[3];

                    if (nearNum1 > nearNum2) {
                        return -1;
                    }
                    else if (nearNum1 < nearNum2) {
                        return 1;
                    }
                    else {
                        int row1 = o1[0]; // 행
                        int row2 = o2[0];

                        if (row1 > row2) {
                            return 1;
                        }
                        else if (row1 < row2) {
                            return -1;
                        }
                        else {
                            int column1 = o1[1]; // 열
                            int column2 = o2[1];

                            if (column1 > column2) {
                                return 1;
                            }
                            else if (column1 < column2) {
                                return -1;
                            }
                            else {
                                return 0;
                            }
                        }
                    }
                }
            }
        });
    }

    public static void decideSeat() { // 모든 학생들의 자리를 결정하는 메서드
        for (int n = 1; n < studentNum; n++) {
            where = new ArrayList<>();
            calculateSeatInformation(studentOrder[n]); // 학생의 자리를 정하기 위해 좌석의 정보 구하기
            sortCanBeSelectedSeat(); // 학생이 앉을 수 있는 좌석들을 문제의 조건에 맞게 정렬

            Integer nowStudentSeatInformation[] = where.get(0); // 문제의 조건에 가장 적합한 학생이 앉을 수 있는 좌석 정보
            seat[nowStudentSeatInformation[0]][nowStudentSeatInformation[1]] = studentOrder[n]; // 학생의 자리 결정
        }
    }

    public static void calculateSatisfaction() { // 학생의 만족도 총합을 계산하고 출력하는 메서드
        int satisfaction = 0; // 학생의 만족도 총합
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int studentIndex = seat[r][c]; // 만족도를 계산하려는 학생
                int favoriteSeatNum = 0; // 해당 학생의 인접한 칸에 있는 좋아하는 학생의 수
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    favoriteSeatNum = calculateFavoriteSeatNum(studentIndex, nr, nc, favoriteSeatNum);
                }

                switch (favoriteSeatNum) {
                    case 1:
                        satisfaction += 1;
                        break;
                    case 2:
                        satisfaction += 10;
                        break;
                    case 3:
                        satisfaction += 100;
                        break;
                    case 4:
                        satisfaction += 1000;
                        break;
                }
            }
        }

        System.out.println(satisfaction);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        studentNum = (int) Math.pow(N, 2);
        studentOrder = new int[studentNum];
        favoriteStudent = new ArrayList[studentNum + 1];
        for (int s = 0; s < studentNum + 1; s++) {
            favoriteStudent[s] = new ArrayList<>();
        }

        StringTokenizer token;
        for (int i = 0; i < studentNum; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    studentOrder[i] = Integer.parseInt(token.nextToken());
                }
                else {
                    favoriteStudent[studentOrder[i]].add(Integer.parseInt(token.nextToken()));
                }
            }
        }

        seat = new int[N][N];
        seat[1][1] = studentOrder[0];
        decideSeat(); // 학생의 자리 결정

        calculateSatisfaction(); // 학생의 만족도 총합 계산 및 출력
    }
}
