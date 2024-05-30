package Jieun714;
/**
 * 문제: 블라인드 공채를 통과한 신입 사원 라이언은 신규 게임 개발 업무를 맡게 되었다. 이번에 출시할 게임 제목은 "프렌즈4블록".
 *      같은 모양의 카카오프렌즈 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임이다.
 *      같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.
 *      블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
 *      만약 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복하게 된다.
 *      각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)을 의미한다
 *      입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.
 * 입력: 입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
 *      2 ≦ n, m ≦ 30
 *      board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.
 * 출력: 입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.
 * 해결: 구현
 * */
import java.util.*;

public class PRO17679 {
    public char[][] newBoard; //새로운 보드 배열

    public int friends(int m, int n, boolean[][] isChecked) {
        //같은 모양 만족하는 지 체크
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                char now = newBoard[i][j]; //현재 모양
                if (now == '0') continue; //만약 '0'이라면 다음으로

                if (now == newBoard[i + 1][j + 0] && now == newBoard[i + 1][j + 1] && now == newBoard[i + 0][j + 1]) { // 같은 모양으로 2×2 형태가 만족할 때
                    isChecked[i][j] = true;
                    isChecked[i + 1][j + 0] = true;
                    isChecked[i + 1][j + 1] = true;
                    isChecked[i + 0][j + 1] = true;
                }
            }
        }

        int cnt = 0; //지워지는 블록 갯수
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isChecked[i][j]) { //지울 블록일 때
                    cnt++; //지워지는 블록 갯수 추가
                    newBoard[i][j] = '0'; //빈공간 표시
                }
            }
        }

        //빈공간 채우기
        for (int x = 0; x < n; x++) {
            for (int y = m - 1; y >= 0; y--) {
                for (int i = y - 1; i >= 0; i--) {
                    if (!isChecked[y][x] && newBoard[y][x] != '0') continue; //만약 빈 배열이라면 넘어가기
                    isChecked[y][x] = isChecked[i][x]; //빈 배열 상태를 현재 배열 상태로 변경
                    newBoard[y][x] = newBoard[i][x]; //빈 배열로 블록 채우기
                    newBoard[i][x] = '0'; //이전 배열은 빈 배열로 변경
                }
            }
        }
        return cnt;
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0; //지워지는 블록개수
        newBoard = new char[m][n];
        for (int i = 0; i < m; i++) newBoard[i] = board[i].toCharArray(); //주어진 문자열 배열을 2차원 문자 배열로 변환

        boolean[][] isChecked = new boolean[m][n]; //지워지는 블록인지 체크하는 배열
        answer += friends(m, n, isChecked); //처음 누적
        while (true) {
            isChecked = new boolean[m][n]; //boolean 배열 초기화
            int now = friends(m, n, isChecked);
            if (now == 0) break; //만약 지워지는 블록이 없다면 while문 종료
            else answer += now; //지워지는 블록 개수 누적
        }
        return answer; //결과 출력
    }
}