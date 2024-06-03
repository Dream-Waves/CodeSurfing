/*
1911. Gold 5 - 흙길 보수하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           9533	    3692      2843	         38.481%


    문제
        어젯밤 겨울 캠프 장소에서 월드 본원까지 이어지는, 흙으로 된 비밀길 위에 폭우가 내려서 N(1 ≤ N ≤ 10,000)개의 물웅덩이가 생겼다. 월드학원은 물웅덩이를 덮을 수 있는 길이가 L(1 ≤ L ≤ 1,000,000)인 널빤지들을 충분히 가지고 있어서, 이들로 다리를 만들어 물웅덩이들을 모두 덮으려고 한다. 물웅덩이들의 위치와 크기에 대한 정보가 주어질 때, 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 구하여라.


    입력
        첫째 줄에 두 정수 N과 L이 들어온다.
        둘째 줄부터 N+1번째 줄까지 총 N개의 줄에 각각의 웅덩이들의 정보가 주어진다. 웅덩이의 정보는 웅덩이의 시작 위치와 끝 위치로 이루어진다. 각 위치는 0 이상 1,000,000,000 이하의 정수이다. 입력으로 주어지는 웅덩이는 겹치지 않는다.


    출력
        첫째 줄에 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 출력한다.


    예제 입력 1
        3 3
        1 6
        13 17
        8 12
    예제 출력 1
        5


    힌트
        아래와 같이 5개의 널빤지가 필요하다.
            111222..333444555.... // 길이 3인 널빤지
            .MMMMM..MMMM.MMMM.... // 웅덩이
            012345678901234567890 // 좌표


    알고리즘 분류
        그리디 알고리즘
        정렬
        스위핑
*/


// 메모리 : 20188KB
// 시간 : 400ms
// 코드 길이 : 2167B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1911 {
    static int N; // 물웅덩이의 개수 (1 ≤ N ≤ 10000)
    static int L; // 물웅덩이를 덮을 수 있는 길이 (1 ≤ L ≤ 1000000)
    static int puddle[][]; // 물웅덩이의 시작 위치와 끝 위치를 저장하는 배열

    public static void find() { // 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 구하고 출력하는 메서드
        Arrays.sort(puddle, (o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0])); // 물웅덩이의 시작 위치를 기준으로 오름차순 정렬, 물웅덩이의 시작 위치가 같을 경우 물웅덩이의 끝 위치를 기준으로 오름차순 정렬

        int location = puddle[0][0]; // 덮을 널빤지의 시작 위치
        int minCount = 0; // 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수
        for (int n = 0; n < N; n++) {
            if (location < puddle[n][0]) { // 덮을 널빤지의 시작 위치가 물웅덩이의 시작 위치 전일 경우
                location = puddle[n][0];
            }

            while (location < puddle[n][1]) { // 덮을 널빤지의 끝 위치가 물웅덩이의 끝 위치 전일 동안
                location += L;
                minCount += 1;
            }
        }

        System.out.println(minCount);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        L = Integer.parseInt(token.nextToken());

        puddle = new int[N][2];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            puddle[i][0] = Integer.parseInt(token.nextToken()); // 물웅덩이의 시작 위치
            puddle[i][1] = Integer.parseInt(token.nextToken()); // 물웅덩이의 끝 위치
        }

        find();
    }
}
