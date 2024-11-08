package Jieun714;
/**
 * 문제: 라디오를 자주 듣는 네오는 라디오에서 방금 나왔던 음악이 무슨 음악인지 궁금해질 때가 많다. 그럴 때 네오는 다음 포털의 '방금그곡' 서비스를 이용하곤 한다. 방금그곡에서는 TV, 라디오 등에서 나온 음악에 관해 제목 등의 정보를 제공하는 서비스이다.
 *      네오는 자신이 기억한 멜로디를 가지고 방금그곡을 이용해 음악을 찾는다. 그런데 라디오 방송에서는 한 음악을 반복해서 재생할 때도 있어서 네오가 기억하고 있는 멜로디는 음악 끝부분과 처음 부분이 이어서 재생된 멜로디일 수도 있다.
 *      반대로, 한 음악을 중간에 끊을 경우 원본 음악에는 네오가 기억한 멜로디가 들어있다 해도 그 곡이 네오가 들은 곡이 아닐 수도 있다. 그렇기 때문에 네오는 기억한 멜로디를 재생 시간과 제공된 악보를 직접 보면서 비교하려고 한다. 다음과 같은 가정을 할 때 네오가 찾으려는 음악의 제목을 구하여라.
 *
 *      - 방금그곡 서비스에서는 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공한다.
 *      - 네오가 기억한 멜로디와 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개이다.
 *      - 각 음은 1분에 1개씩 재생된다. 음악은 반드시 처음부터 재생되며 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복해서 재생된다. 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생된다.
 *      - 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
 *      - 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
 *      - 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환한다.
 * 입력: 입력으로 네오가 기억한 멜로디를 담은 문자열 m과 방송된 곡의 정보를 담고 있는 배열 musicinfos가 주어진다.
 *      - m은 음 1개 이상 1439개 이하로 구성되어 있다.
 *      - musicinfos는 100개 이하의 곡 정보를 담고 있는 배열로, 각각의 곡 정보는 음악이 시작한 시각, 끝난 시각, 음악 제목, 악보 정보가 ','로 구분된 문자열이다.
 *      - 음악의 시작 시각과 끝난 시각은 24시간 HH:MM 형식이다.
 *      - 음악 제목은 ',' 이외의 출력 가능한 문자로 표현된 길이 1 이상 64 이하의 문자열이다.
 *      - 악보 정보는 음 1개 이상 1439개 이하로 구성되어 있다.
 * 출력: 조건과 일치하는 음악 제목을 출력한다.
 * 해결: 구현
 * */
import java.util.*;
public class PRO17683 {
    public class Solution {
        public String solution(String m, String[] musicinfos) {
            String answer = "";
            ArrayList<String> mList = new ArrayList<>(); //멜로디를 담을 리스트
            int idx = 0;
            while(idx < m.length()) {
                if(idx != m.length()-1 && m.charAt(idx+1) == '#') {
                    mList.add(m.substring(idx, idx+2));
                    idx++;
                } else mList.add(String.valueOf(m.charAt(idx)));
                idx++;
            }

            int max = Integer.MIN_VALUE; //최장 음악 재생 시간을 담을 변수
            for(String str : musicinfos) {
                String [] sArr = str.split(",");
                String [] startArr = sArr[0].split(":");
                String [] endArr = sArr[1].split(":");
                int start = Integer.parseInt(startArr[0])*60 + Integer.parseInt(startArr[1]); //시작 시간
                int end = Integer.parseInt(endArr[0])*60 + Integer.parseInt(endArr[1]); //끝나는 시간
                int time = end - start;

                ArrayList<String> music = new ArrayList<>(); //음을 담을 리스트
                idx = 0; //초기화
                while(idx < sArr[3].length()) {
                    if(idx != sArr[3].length()-1 && sArr[3].charAt(idx+1) == '#') {
                        music.add(sArr[3].substring(idx, idx+2));
                        idx++;
                    } else music.add(String.valueOf(sArr[3].charAt(idx)));
                    idx++;
                }

                idx = 0; //초기화
                for(int i=0; i<=time-mList.size(); i++) {
                    if(!mList.get(idx).equals(music.get(i%music.size()))) continue; //일치하지 않으면 다음으로 이동
                    boolean flag = false; //일치여부를 구분하기 위한 boolean 변수
                    idx++;
                    for(int j=i+1; j<=i+mList.size()-1; j++) {
                        if(!mList.get(idx).equals(music.get(j%music.size()))) {
                            flag = true;
                            break;
                        }
                        idx++;
                    }

                    if(!flag) { //일치하는 음악 제목이라면
                        if(time > max) { //재생 시간이 긴 음악이라면
                            max = time; //최장 재생 시간 변경
                            answer = sArr[2]; //음악 제목 저장
                            break;
                        }
                    }
                    idx = 0;
                }
            }
            return answer == "" ? "(None)" : answer; //조건이 일치하는 음악이 없다면 “(None)”을, 있다면 음악 제목을 반환
        }
    }
}