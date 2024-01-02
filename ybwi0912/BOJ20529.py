from itertools import combinations

# 두 mbti 간의 거리를 반환
def diff(str1, str2):
    ans = 0
    for i in range(4):
        if str1[i] != str2[i]:
            ans += 1
    return ans

# 세 mbti 간의 거리의 합을 반환
def distance(str1, str2, str3):
    return diff(str1, str2) + diff(str2, str3) + diff(str1, str3)


T = int(input())
for _ in range(T):
    N = int(input())
    li = list(map(str, input().split())) # mbti 입력을 리스트에 저장
    length = len(li)
    if length>32: # mbti는 총 16종류. 리스트에 항목이 32개 이상 존재한다면 같은 항목이 무조건 3개 이상 존재할 수밖에 없다
        print(0) # 같은 mbti 3개의 심리적 거리의 합은 0
        continue

    ans = 10000

    # itertools 라이브러리 사용하지 않는 풀이
    # 38964 KB, 516 ms
    for i in range(length):
        for j in range(i+1, length):
            for k in range(j+1, length): # 중복되는 인덱스의 요소는 조회하지 않도록 범위 수정
                # if i==j or j==k or i==k:
                #     continue
                now = distance(li[i], li[j], li[k]) # 세 mbti 간의 심리적 거리를 구한 뒤 
                ans = min(ans, now) # 최소값과 비교 후 최소값 갱신
                if ans == 0:
                    break

    # itertools 라이브러리 사용하는 풀이
    # 38964 KB, 496 ms
    # for x, y, z in combinations(li, 3): # li 내 요소 중 3개를 선택하는 조합
    #     now = distance(x, y, z)
    #     ans = min(ans, now)
    #     if ans==0:
    #         break

    print(ans)