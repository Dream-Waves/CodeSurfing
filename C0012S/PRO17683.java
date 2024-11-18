/*
Lv. 2 #17683 - [3차] 방금그곡

    문제 설명
        방금그곡
            라디오를 자주 듣는 네오는 라디오에서 방금 나왔던 음악이 무슨 음악인지 궁금해질 때가 많다. 그럴 때 네오는 다음 포털의 '방금그곡' 서비스를 이용하곤 한다. 방금그곡에서는 TV, 라디오 등에서 나온 음악에 관해 제목 등의 정보를 제공하는 서비스이다.

            네오는 자신이 기억한 멜로디를 가지고 방금그곡을 이용해 음악을 찾는다. 그런데 라디오 방송에서는 한 음악을 반복해서 재생할 때도 있어서 네오가 기억하고 있는 멜로디는 음악 끝부분과 처음 부분이 이어서 재생된 멜로디일 수도 있다. 반대로, 한 음악을 중간에 끊을 경우 원본 음악에는 네오가 기억한 멜로디가 들어있다 해도 그 곡이 네오가 들은 곡이 아닐 수도 있다. 그렇기 때문에 네오는 기억한 멜로디를 재생 시간과 제공된 악보를 직접 보면서 비교하려고 한다. 다음과 같은 가정을 할 때 네오가 찾으려는 음악의 제목을 구하여라.
                · 방금그곡 서비스에서는 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공한다.
                · 네오가 기억한 멜로디와 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개이다.
                · 각 음은 1분에 1개씩 재생된다. 음악은 반드시 처음부터 재생되며 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복해서 재생된다. 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생된다.
                · 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
                · 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
                · 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환한다.


    입력 형식
        입력으로 네오가 기억한 멜로디를 담은 문자열 m과 방송된 곡의 정보를 담고 있는 배열 musicinfos가 주어진다.
            · m은 음 1개 이상 1439개 이하로 구성되어 있다.
            · musicinfos는 100개 이하의 곡 정보를 담고 있는 배열로, 각각의 곡 정보는 음악이 시작한 시각, 끝난 시각, 음악 제목, 악보 정보가 ','로 구분된 문자열이다.
            · 음악의 시작 시각과 끝난 시각은 24시간 HH:MM 형식이다.
            · 음악 제목은 ',' 이외의 출력 가능한 문자로 표현된 길이 1 이상 64 이하의 문자열이다.
            · 악보 정보는 음 1개 이상 1439개 이하로 구성되어 있다.


    출력 형식
        조건과 일치하는 음악 제목을 출력한다.


    입출력 예시
        m	                    musicinfos	                                                    answer
        "ABCDEFG"	            ["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]	    "HELLO"
        "CC#BCC#BCC#BCC#B"	    ["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"]	    "FOO"
        "ABC"	                ["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]	    "WORLD"


    설명
        첫 번째 예시에서 HELLO는 길이가 7분이지만 12:00부터 12:14까지 재생되었으므로 실제로 CDEFGABCDEFGAB로 재생되었고, 이 중에 기억한 멜로디인 ABCDEFG가 들어있다.
        세 번째 예시에서 HELLO는 C#DEFGABC#DEFGAB로, WORLD는 ABCDE로 재생되었다. HELLO 안에 있는 ABC#은 기억한 멜로디인 ABC와 일치하지 않고, WORLD 안에 있는 ABC가 기억한 멜로디와 일치한다.

        해설 보러가기


    ※ 공지 - 2024년 2월 21일 테스트 케이스가 추가되었습니다. 기존에 제출한 코드가 통과하지 못할 수도 있습니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.65ms, 73.9MB)
        테스트 2 〉	통과 (0.60ms, 74.7MB)
        테스트 3 〉	통과 (0.80ms, 71.6MB)
        테스트 4 〉	통과 (0.57ms, 75.8MB)
        테스트 5 〉	통과 (0.97ms, 74.6MB)
        테스트 6 〉	통과 (0.80ms, 76.1MB)
        테스트 7 〉	통과 (1.13ms, 74.3MB)
        테스트 8 〉	통과 (1.41ms, 71.2MB)
        테스트 9 〉	통과 (1.76ms, 78.8MB)
        테스트 10 〉	통과 (2.76ms, 79.1MB)
        테스트 11 〉	통과 (1.15ms, 73.4MB)
        테스트 12 〉	통과 (1.93ms, 73.1MB)
        테스트 13 〉	통과 (1.80ms, 78.5MB)
        테스트 14 〉	통과 (1.16ms, 70.6MB)
        테스트 15 〉	통과 (1.94ms, 77.5MB)
        테스트 16 〉	통과 (1.84ms, 71.8MB)
        테스트 17 〉	통과 (1.93ms, 73.5MB)
        테스트 18 〉	통과 (1.44ms, 83.6MB)
        테스트 19 〉	통과 (3.10ms, 70.7MB)
        테스트 20 〉	통과 (1.59ms, 89.2MB)
        테스트 21 〉	통과 (1.95ms, 77.9MB)
        테스트 22 〉	통과 (0.86ms, 73.8MB)
        테스트 23 〉	통과 (1.20ms, 77.1MB)
        테스트 24 〉	통과 (0.72ms, 76.1MB)
        테스트 25 〉	통과 (0.68ms, 78.7MB)
        테스트 26 〉	통과 (0.75ms, 78.8MB)
        테스트 27 〉	통과 (0.61ms, 77.2MB)
        테스트 28 〉	통과 (0.64ms, 79.9MB)
        테스트 29 〉	통과 (5.40ms, 71.6MB)
        테스트 30 〉	통과 (8.82ms, 81.3MB)
        테스트 31 〉	통과 (1.02ms, 74.7MB)
        테스트 32 〉	통과 (0.60ms, 67.3MB)
        테스트 33 〉	통과 (0.55ms, 73.6MB)
        테스트 34 〉	통과 (0.59ms, 76.4MB)
        테스트 35 〉	통과 (0.73ms, 74.5MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO17683 {
    static ArrayList<String[]> musicList; // 네오가 기억하고 있는 멜로디를 포함하고 있는 방송된 곡 리스트

    public static String filter(String music) { // 해당 음악의 #이 붙은 음을 치환하는 메서드
        return music.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#", "g").replace("B#", "b");
    }

    public static int calculateTime(String[] startTime, String[] endTime) { // 방송된 곡이 재생된 시간을 구하는 메서드
        return 60 * (Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]))
                + (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]));
    }

    public static void check(String m, String[] musicInfo, int order) { // 해당 방송된 곡이 네오가 기억하고 있는 멜로디를 포함하고 있는지 구하는 메서드
        m = filter(m); // 네오가 기억하고 있는 멜로디 #이 붙은 음 처리
        musicInfo[3] = filter(musicInfo[3]); // 해당 방송된 곡의 #이 붙은 음 처리

        int musicTime = calculateTime(musicInfo[0].split(":"), musicInfo[1].split(":")); // 해당 방송된 곡이 재생된 시간
        int musicLength = musicInfo[3].length(); // 해당 방송된 곡의 음의 개수
        int musicToneIndex = 0; // 해당 방송된 곡의 음의 인덱스
        int neoMusicLength = m.length(); // 네오가 기억하고 있는 멜로디의 음의 개수
        int neoMusicToneIndex = 0; // 네오가 기억하고 있는 멜로디의 음의 인덱스

        boolean flag = false; // 해당 방송된 곡과 네오가 기억하고 있는 멜로디의 음의 일치 여부
        for (int t = 0; t < musicTime; t++) {
            musicToneIndex = t % musicLength; // 해당 방송된 곡의 재생된 시간의 초의 값에 따라 해당 방송된 곡의 음의 인덱스 값 조정

            if (m.charAt(neoMusicToneIndex) == musicInfo[3].charAt(musicToneIndex)) { // 네오가 기억하고 있는 멜로디의 음과 해당 방송된 곡의 음이 같을 경우
                neoMusicToneIndex += 1;
                flag = true;
            }
            else { // 네오가 기억하고 있는 멜로디의 음과 해당 방송된 곡의 음이 다를 경우
                neoMusicToneIndex = 0;

                if (flag) { // 해당 방송된 곡의 현재 음의 직전까지, 해당 방송된 곡과 네오가 기억하고 있는 멜로디의 음이 일치했을 경우
                    t -= 1; // 해당 방송된 곡의 현재 음과 네오가 기억하고 있는 멜로디의 첫 번째 음과 비교할 수 있도록 해당 방송된 곡의 재생된 시간의 초의 값 조정
                    flag = false;
                }
            }

            if (neoMusicToneIndex == neoMusicLength) { // 네오가 기억하고 있는 멜로디가 포함되어 있는 곡일 경우
                musicList.add(new String[] {String.valueOf(musicTime), musicInfo[2], String.valueOf(order)});

                return;
            }
        }
    }

    public static void find(String m, String[] musicinfos) { // 방송된 곡 중 네오가 기억하고 있는 멜로디를 포함하고 있는 곡을 찾는 메서드
        musicList = new ArrayList<>();
        for (int r = 0, num = musicinfos.length; r < num; r++) {
            check(m, musicinfos[r].split(","), r);
        }
    }

    public static String select() { // 네오가 기억하고 있는 멜로디를 포함하고 있는 방송된 곡 리스트 중 라디오에서 재생된 시간이 제일 길고, 재생된 시간이 같을 경우 먼저 입력된 곡의 제목을 구하는 메서드
        if (musicList.isEmpty()) { // 조건이 일치하는 음악이 없을 경우
            return "(None)";
        }

        // 조건이 일치하는 음악이 있을 경우
        musicList.sort((o1, o2) -> Integer.parseInt(o1[0]) == Integer.parseInt(o2[0]) ? Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]) : Integer.parseInt(o2[0]) - Integer.parseInt(o1[0])); // 라디오에서 재생된 시간을 기준으로 내림차순으로 정렬하고, 재생된 시간이 같을 경우 입력된 순서를 기준으로 오름차순으로 정렬

        return musicList.get(0)[1];
    }

    public String solution(String m, String[] musicinfos) {
        find(m, musicinfos);

        return select();
    }
}
