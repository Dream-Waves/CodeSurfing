import java.util.Arrays;
import java.util.Comparator;

public class PRO42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[0]));
        int left = routes[0][0];
        int right = routes[0][1];
        int answer = 1;
        for (var r: routes) {
            if(r[0]>right||left>right){
                right = r[1];
                left = r[0];
                answer++;
            } else {
                left = Math.max(r[0],left);
                right = Math.min(r[1],right);
            }
        }

        return answer;
    }
}
