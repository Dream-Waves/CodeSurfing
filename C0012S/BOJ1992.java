/*
1992. Silver 1 - 쿼드트리

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           44522	    27531     21280	         61.941%


    문제
        흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다. 흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.
        주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
            □ □ □ □ □ □ □ □         0 0 0 0 0 0 0 0
            □ □ □ □ □ □ □ □         0 0 0 0 0 0 0 0
            □ □ □ □ ■ ■ ■ ■         0 0 0 0 1 1 1 1
            □ □ □ □ ■ ■ ■ ■    →    0 0 0 0 1 1 1 1
            □ □ □ ■ ■ ■ ■ ■         0 0 0 1 1 1 1 1
            □ □ ■ ■ ■ ■ ■ ■         0 0 1 1 1 1 1 1
            □ □ ■ ■ ■ ■ ■ ■         0 0 1 1 1 1 1 1
            □ □ ■ ■ ■ ■ ■ ■         0 0 1 1 1 1 1 1

        위 그림에서 왼쪽의 영상은 오른쪽의 배열과 같이 숫자로 주어지며, 이 영상을 쿼드 트리 구조를 이용하여 압축하면 "(0(0011)(0(0111)01)1)"로 표현된다. N ×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.


    입력
        첫째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다. N 은 언제나 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다. 두 번째 줄부터는 길이 N의 문자열이 N개 들어온다. 각 문자열은 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.


    출력
        영상을 압축한 결과를 출력한다.


    예제 입력 1
        8
        11110000
        11110000
        00011100
        00011100
        11110000
        11110000
        11110011
        11110011
    예제 출력 1
        ((110(0101))(0010)1(0001))


    알고리즘 분류
        분할 정복
        재귀
*/


// 메모리 : 14240KB
// 시간 : 132ms
// 코드 길이 : 2500B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    static int N; // 영상의 크기 N (2의 제곱수, 1 ≤ N ≤ 64)
    static int binaryNum[][]; // 영상의 데이터 구조를 담는 2 차원 배열
    static StringBuilder sb;

    public static boolean canZip(int x, int y, int size) { // 압축이 가능한지 검사하는 함수  // true : 압축 가능, false : 압축 불가능
        int firstValue = binaryNum[x][y]; // 해당 공간의 왼쪽 위의 첫 번째 숫자

        for (int r = x; r < x + size; r++) {
            for (int c = y; c < y + size; c++) {
                if (firstValue != binaryNum[r][c]) { // 해당 공간의 왼쪽 위의 첫 번째 숫자와 공간 내의 숫자가 같지 않을 경우
                    return false;
                }
            }
        }

        return true; // 해당 공간의 왼쪽 위의 첫 번째 숫자와 공간 내의 모든 숫자가 같을 경우 true 반환
    }

    public static void videoZip(int x, int y, int size) { // 압축한 결과를 구하는 함수  // 가장 왼쪽 위의 숫자(binaryNum[x][y])를 기준으로 압축한 결과를 구한다.
        if (canZip(x, y, size)) { // 압축이 가능할 경우
            sb.append(binaryNum[x][y]); // 해당 공간을 구성하는 숫자로 압축
            return;
        }

        // 압축이 불가능할 경우  // 압축할 사이즈를 기존의 압축 사이즈의 절반으로 설정한 후, 압축해야 한다.
        int nextZipSize = size / 2; // 다음 압축 사이즈

        sb.append("(");

        videoZip(x, y, nextZipSize); // 왼쪽 위 압축
        videoZip(x, y + nextZipSize, nextZipSize); // 오른쪽 위 압축
        videoZip(x + nextZipSize, y, nextZipSize); // 왼쪽 아래 압축
        videoZip(x + nextZipSize, y + nextZipSize, nextZipSize); // 오른쪽 아래 압축

        sb.append(")");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        binaryNum = new int[N][N];
        for (int n = 0; n < N; n++) {
            String binary = bf.readLine();

            for (int m = 0; m < N; m++) {
                binaryNum[n][m] = binary.charAt(m) - '0';
            }
        }

        sb = new StringBuilder();
        videoZip(0, 0, N);
        System.out.println(sb);
    }
}
