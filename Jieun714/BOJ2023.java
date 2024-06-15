package Jieun714;
/**
 * 문제: 수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 가지고 노는 것이다. 요즘 수빈이가 가장 관심있어 하는 소수는 7331이다.
 *      7331은 소수인데, 신기하게도 733도 소수이고, 73도 소수이고, 7도 소수이다. 즉, 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수이다! 수빈이는 이런 숫자를 신기한 소수라고 이름 붙였다.
 *      수빈이는 N자리의 숫자 중에서 어떤 수들이 신기한 소수인지 궁금해졌다. N이 주어졌을 때, 수빈이를 위해 N자리 신기한 소수를 모두 찾아보자.
 * 입력: 첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.
 * 출력: N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.
 * 해결: DFS로 각 자릿수를 체크
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {
    public static int N;
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int num, int cnt) {
        if(cnt == N) {
            sb.append(num).append("\n");
            return;
        }
        for(int i=1; i<=9; i++) {
            int now = 10*num+i; //왼쪽부터 1자리, 2자리, 3자리... 판별하기 때문에, 기존 수에 x10 해줌
            if(isPrime(now)) dfs(now, cnt+1);
        }
    }

    //소수 판별하는 함수
    public static boolean isPrime(int num) {
        if(num == 1) return false;
        for(int i=2; i<=Math.sqrt(num); i++) { //2부터 제곱근까지 계산
            if(num%i==0) return false; //소수가 아니라면 false
        }
        return true; //소수라면 true
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //N자리 수
        dfs(0, 0);
        System.out.println(sb);
    }
}