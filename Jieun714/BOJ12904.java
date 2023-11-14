package Jieun714;
/**
 * 문제: 수빈이는 A와 B로만 이루어진 영어 단어가 존재한다는 사실에 놀랐다. 대표적인 예로 AB (Abdominal의 약자), BAA (양의 울음 소리), AA (용암의 종류), ABBA (스웨덴 팝 그룹)이 있다.
 *      이런 사실에 놀란 수빈이는 간단한 게임을 만들기로 했다. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
 *      1. 문자열의 뒤에 A를 추가한다.
 *      2. 문자열을 뒤집고 뒤에 B를 추가한다.
 *      주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 S가 둘째 줄에 T가 주어진다. (1 ≤ S의 길이 ≤ 999, 2 ≤ T의 길이 ≤ 1000, S의 길이 < T의 길이)
 * 출력: S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.
 *
 * 해결: 그리디 알고리즘. S -> T가 아닌 T -> S로 접근
 *      두 가지 연산만 가능하다보니, 맨 뒤에 문자를 기준으로 연산 선택하는 방식
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine(); //주어진 문자열
        String T = br.readLine(); //완성할 문자열
        List<Character> sList = new ArrayList<>();
        List<Character> tList = new ArrayList<>();

        for(int i =0; i<S.length(); i++){
            sList.add(S.charAt(i));
        }
        for(int i=0; i<T.length(); i++){
            tList.add(T.charAt(i));
        }

        while(tList.size() != sList.size()){
            if(tList.get(tList.size()-1).equals('A')){ //맨 뒤가 A라면
                tList.remove(tList.size()-1); //A 제거
            } else { //B라면
                tList.remove(tList.size()-1); //B 제거
                Collections.reverse(tList); //반대로 정렬
            }
        }

        int answer = Arrays.equals(tList.toArray(), sList.toArray()) ? 1: 0;
        System.out.println(answer);
    }
}