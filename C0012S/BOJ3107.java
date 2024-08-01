/*
3107. Gold 5 - IPv6

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           4918	    1724      1385	         36.874%


    문제
        IPv6은 길이가 128비트인 차세대 인터넷 프로토콜이다.
        IPv6의 주소는 32자리의 16진수를 4자리씩 끊어 나타낸다. 이때, 각 그룹은 콜론 (:)으로 구분해서 나타낸다.

        예를 들면, 다음과 같다.
            2001:0db8:85a3:0000:0000:8a2e:0370:7334

        32자리의 16진수는 사람이 읽고 쓰기에 불편하고, 대부분의 자리가 0이기 때문에 아래와 같이 축약할 수 있다.
            1. 각 그룹의 앞자리의 0의 전체 또는 일부를 생략 할 수 있다. 위의 IPv6을 축약하면, 다음과 같다
                2001:db8:85a3:0:00:8a2e:370:7334

            2. 만약 0으로만 이루어져 있는 그룹이 있을 경우 그 중 한 개 이상 연속된 그룹을 하나 골라 콜론 2개(::)로 바꿀 수 있다.
                2001:db8:85a3::8a2e:370:7334

            2번째 규칙은 모호함을 방지하기 위해서 오직 한 번만 사용할 수 있다.

        올바른 축약형 IPv6주소가 주어졌을 때, 이를 원래 IPv6 (32자리의 16진수)로 복원하는 프로그램을 작성하시오.


    입력
        첫째 줄에 올바른 IPv6 주소가 주어진다. 이 주소는 최대 39글자이다. 또한, 주소는 숫자 0-9, 알파벳 소문자 a-f, 콜론 :으로만 이루어져 있다.


    출력
        첫째 줄에, 입력으로 주어진 IPv6의 축약되지 않은 형태를 출력한다.


    예제 입력 1
        25:09:1985:aa:091:4846:374:bb
    예제 출력 1
        0025:0009:1985:00aa:0091:4846:0374:00bb

    예제 입력 2
        ::1
    예제 출력 2
        0000:0000:0000:0000:0000:0000:0000:0001


    알고리즘 분류
        구현
        문자열
*/


// 메모리 : 14316KB
// 시간 : 128ms
// 코드 길이 : 2780B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3107 {
    static String IPv6[]; // IPv6의 주소의 각 그룹의 정보를 저장하는 배열
    static StringBuilder sb;

    public static void make(int num, String addressGroup) { // IPv6의 주소의 각 그룹의 앞 자리의 생략한 0을 복원하는 메서드
        for (int s = 0; s < num; s++) {
            sb.append("0");
        }

        sb.append(addressGroup);
        sb.append(":");
    }

    public static void decode() { // 올바른 축약형 IPv6 주소가 주어졌을 때, 이를 원래 IPv6의 주소(32자리의 16진수)로 복원하는 메서드
        boolean flag = false; // 0으로만 이루어져 있는 그룹의 존재 여부  // false : 존재 X, true : 존재 O
        int zeroStartIndex = Integer.MAX_VALUE; // 0으로만 이루어져 있는 그룹 중 첫 번째 그룹의 인덱스
        int zeroNum = 0; // 0으로만 이루어져 있는 그룹의 개수
        sb = new StringBuilder();

        for (int n = 0, len = IPv6.length; n < len; n++) {
            if (IPv6[n].equals("")) { // 0으로만 이루어져 있는 그룹이 한 개 이상 연속되어 콜론 2 개(::)로 변경되었을 경우
                flag = true;
                zeroStartIndex = Math.min(zeroStartIndex, n);
            }
            else if (flag) { // 현재 검사하고 있는 그룹이 0으로만 이루어져 있는 그룹이 아니고, 0으로만 이루어져 있는 그룹의 직후 그룹일 경우
                zeroNum = 8 - (len - n) - zeroStartIndex;
                for (int z = 0; z < zeroNum; z++) {
                    sb.append("0000:");
                }

                make(4 - IPv6[n].length(), IPv6[n]);

                flag = false; // 0으로만 이루어져 있는 그룹이 있을 경우 그 중 한 개 이상 연속된 그룹을 하나 골라 콜론 2개(::)로 바꾸는 규칙은 한 번만 사용
            }
            else { // 현재 검사하고 있는 그룹이 0으로만 이루어져 있는 그룹이 아니고, 0으로만 이루어져 있는 그룹의 직후 그룹이 아닐 경우
                make(4 - IPv6[n].length(), IPv6[n]);
            }
        }

        if (sb.length() <= 39) { // 0으로만 이루어져 있는 그룹이 IPv6의 주소의 끝에 위치할 경우
            for (int p = 0, size = 40 - sb.length() / 5; p < size; p++) {
                sb.append("0000:");
            }
        }

        System.out.println(sb.substring(0, 39));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        IPv6 = bf.readLine().split(":");

        decode();
    }
}
