/*
Lv. 3 #42579 - 베스트앨범

    문제 설명
        스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
            1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
            2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
            3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

        노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.


    제한사항
        · genres[i]는 고유번호가 i인 노래의 장르입니다.
        · plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
        · genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
        · 장르 종류는 100개 미만입니다.
        · 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
        · 모든 장르는 재생된 횟수가 다릅니다.


    입출력 예
        genres	                                            plays	                        return
        ["classic", "pop", "classic", "classic", "pop"]	    [500, 600, 150, 800, 2500]	    [4, 1, 3, 0]


    입출력 예 설명
        classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.
            · 고유 번호 3: 800회 재생
            · 고유 번호 0: 500회 재생
            · 고유 번호 2: 150회 재생

        pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
            · 고유 번호 4: 2,500회 재생
            · 고유 번호 1: 600회 재생

        따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
            · 장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.


    ※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (6.90ms, 91.7MB)
        테스트 2 〉	통과 (5.23ms, 80.1MB)
        테스트 3 〉	통과 (8.46ms, 82.8MB)
        테스트 4 〉	통과 (5.56ms, 80.5MB)
        테스트 5 〉	통과 (6.00ms, 92.4MB)
        테스트 6 〉	통과 (7.72ms, 74.9MB)
        테스트 7 〉	통과 (6.58ms, 88.3MB)
        테스트 8 〉	통과 (7.29ms, 90.9MB)
        테스트 9 〉	통과 (6.95ms, 86.4MB)
        테스트 10 〉	통과 (6.05ms, 91.6MB)
        테스트 11 〉	통과 (4.87ms, 88.9MB)
        테스트 12 〉	통과 (6.05ms, 87.7MB)
        테스트 13 〉	통과 (5.87ms, 78.3MB)
        테스트 14 〉	통과 (5.53ms, 89.3MB)
        테스트 15 〉	통과 (5.24ms, 84.6MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO42579 {
    int musicNum; // 모든 노래의 개수
    Map<String, Integer> genreMap; // 각 장르의 장르 내 모든 노래의 재생 횟수를 저장하는 Map
    PriorityQueue<Music> queue; // 노래의 정보(장르, 장르 내 모든 노래의 재생 횟수, 고유 번호, 재생 횟수)를 저장하는 우선 순위 큐
    ArrayList<Integer> bestAlbum; // 장르별로 가장 많이 재생된 노래를 두 개씩 모아 저장한 리스트

    class Music { // 노래의 정보를 저장하는 클래스
        String genre; // 노래의 장르
        int genrePlayCount; // 장르 내 모든 노래의 재생 횟수
        int musicId; // 노래의 고유 번호
        int musicPlayCount; // 노래의 재생 횟수

        public Music(String genre, int genrePlayCount, int musicId, int musicPlayCount) {
            this.genre = genre;
            this.genrePlayCount = genrePlayCount;
            this.musicId = musicId;
            this.musicPlayCount = musicPlayCount;
        }
    }

    public void release() { // 장르별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하는 메서드
        bestAlbum = new ArrayList<>();
        String prevGenre = ""; // 이전 노래의 장르
        int count = 0; // 해당 장르의 노래가 수록된 개수

        for (int r = 0; r < musicNum; r++) {
            Music nowMusic = queue.poll(); // 현재 검사하는 노래

            if (prevGenre.equals(nowMusic.genre)) { // 이전에 수록한 노래의 장르가 현재 검사하는 노래의 장르와 같을 경우
                if (count < 2) { // 해당 장르의 노래가 2 개 미만으로 수록되었을 경우
                    bestAlbum.add(nowMusic.musicId);
                    count += 1;
                }
            }
            else { // 이전에 수록한 노래의 장르가 현재 검사하는 노래의 장르와 같지 않을 경우
                prevGenre = nowMusic.genre;
                bestAlbum.add(nowMusic.musicId);
                count = 1;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        musicNum = genres.length;
        genreMap = new HashMap<>();
        queue = new PriorityQueue<>(
                Comparator.comparingInt((Music m) -> m.genrePlayCount).reversed()
                        .thenComparing(Comparator.comparingInt(
                                (Music m) -> m.musicPlayCount).reversed()
                        )
                        .thenComparingInt(m -> m.musicId)
        ); // 장르 내 모든 노래의 재생 횟수를 기준으로 내림차순 정렬, 노래의 재생 횟수를 기준으로 내림차순 정렬, 노래의 고유 번호를 기준으로 오름차순 정렬

        for (int g = 0; g < musicNum; g++) { // 각 장르의 장르 내 모든 노래의 재생 횟수를 구한다.
            genreMap.put(genres[g], genreMap.getOrDefault(genres[g], 0) + plays[g]);
        }

        for (int p = 0; p < musicNum; p++) { // 각 노래의 정보를 담아 우선 순위 큐에 저장한다.
            queue.offer(new Music(genres[p], genreMap.get(genres[p]), p, plays[p]));
        }

        release();

        return bestAlbum.stream().mapToInt(Integer::intValue).toArray();
    }
}
