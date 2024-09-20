/*
Lv. 2 #49993 - 스킬트리

    문제 설명
        선행 스킬이란 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬을 뜻합니다.

        예를 들어 선행 스킬 순서가 스파크 → 라이트닝 볼트 → 썬더일때, 썬더를 배우려면 먼저 라이트닝 볼트를 배워야 하고, 라이트닝 볼트를 배우려면 먼저 스파크를 배워야 합니다.
            위 순서에 없는 다른 스킬(힐링 등)은 순서에 상관없이 배울 수 있습니다. 따라서 스파크 → 힐링 → 라이트닝 볼트 → 썬더와 같은 스킬트리는 가능하지만, 썬더 → 스파크나 라이트닝 볼트 → 스파크 → 힐링 → 썬더와 같은 스킬트리는 불가능합니다.

        선행 스킬 순서 skill과 유저들이 만든 스킬트리1를 담은 배열 skill_trees가 매개변수로 주어질 때, 가능한 스킬트리 개수를 return 하는 solution 함수를 작성해주세요.


    제한 조건
        · 스킬은 알파벳 대문자로 표기하며, 모든 문자열은 알파벳 대문자로만 이루어져 있습니다.
        · 스킬 순서와 스킬트리는 문자열로 표기합니다.
            · 예를 들어, C → B → D 라면 "CBD"로 표기합니다
        · 선행 스킬 순서 skill의 길이는 1 이상 26 이하이며, 스킬은 중복해 주어지지 않습니다.
        · skill_trees는 길이 1 이상 20 이하인 배열입니다.
        · skill_trees의 원소는 스킬을 나타내는 문자열입니다.
            · skill_trees의 원소는 길이가 2 이상 26 이하인 문자열이며, 스킬이 중복해 주어지지 않습니다.


    입출력 예
        skill	skill_trees	return
        "CBD"	["BACDE", "CBADF", "AECB", "BDA"]	2


    입출력 예 설명
        · "BACDE": B 스킬을 배우기 전에 C 스킬을 먼저 배워야 합니다. 불가능한 스킬트립니다.
        · "CBADF": 가능한 스킬트리입니다.
        · "AECB": 가능한 스킬트리입니다.
        · "BDA": B 스킬을 배우기 전에 C 스킬을 먼저 배워야 합니다. 불가능한 스킬트리입니다.


    1. 스킬 트리: 유저가 스킬을 배울 순서 ↩
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.02ms, 71.2MB)
        테스트 2 〉	통과 (0.03ms, 74MB)
        테스트 3 〉	통과 (0.03ms, 73.9MB)
        테스트 4 〉	통과 (0.03ms, 76.8MB)
        테스트 5 〉	통과 (0.04ms, 72.1MB)
        테스트 6 〉	통과 (0.03ms, 75.8MB)
        테스트 7 〉	통과 (0.03ms, 75MB)
        테스트 8 〉	통과 (0.03ms, 81.7MB)
        테스트 9 〉	통과 (0.05ms, 78.8MB)
        테스트 10 〉	통과 (0.03ms, 77.1MB)
        테스트 11 〉	통과 (0.03ms, 75.2MB)
        테스트 12 〉	통과 (0.03ms, 74.8MB)
        테스트 13 〉	통과 (0.05ms, 72.9MB)
        테스트 14 〉	통과 (0.03ms, 73.4MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

class PRO49993 {
    static int skillTreeLength; // 스킬트리의 스킬 개수
    static int prevSkillLength; // 선행 스킬의 스킬 개수
    static int prevSkillIndex; // 선행 스킬 중 현재 배워야 하는 스킬의 인덱스
    static int answer; // 배울 수 있는 스킬트리의 개수

    public static boolean check(String skill, char prevSkill, char nowSkill) { // 스킬트리의 스킬을 배울 수 있는지 검사하는 메서드
        if (prevSkill == nowSkill) { // 선행 스킬 중 현재 배워야 하는 스킬과 스킬트리의 스킬이 같을 경우
            prevSkillIndex += 1;

            return true;
        }

        // 선행 스킬 중 현재 배워야 하는 스킬과 스킬트리의 스킬이 같지 않을 경우
        for (int i = prevSkillIndex + 1; i < prevSkillLength; i++) {
            if (skill.charAt(i) == nowSkill) { // 스킬트리의 스킬이 선행 스킬 중 현재 배워야 하는 스킬 이후 배울 수 있는 스킬일 경우
                return false;
            }
        }

        // 스킬트리의 스킬이 선행 스킬에 포함되어 있지 않을 경우
        return true;
    }

    public static void find(String skill, String[] skill_trees) { // 배울 수 있는 스킬트리의 개수를 구하는 메서드
        for (String skillTree : skill_trees) {
            skillTreeLength = skillTree.length();
            prevSkillIndex = 0;

            boolean flag = true; // 배울 수 있는 스킬트리 여부
            for (int l = 0; l < skillTreeLength; l++) {
                if (prevSkillIndex == prevSkillLength) { // 선행 스킬을 모두 배웠을 경우
                    break;
                }
                else if (!check(skill, skill.charAt(prevSkillIndex), skillTree.charAt(l))) { // 스킬트리의 스킬을 배울 수 없을 경우
                    flag = false;

                    break;
                }
            }

            if (flag) { // 배울 수 있는 스킬트리일 경우
                answer += 1;
            }
        }
    }

    public int solution(String skill, String[] skill_trees) {
        prevSkillLength = skill.length();
        find(skill, skill_trees);

        return answer;
    }
}
