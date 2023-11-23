N, d, k, c = map(int, input().split())
# 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호
li = [] # 초밥 벨트의 상태

for i in range(N):
    li.append(int(input()))

m = 0 # 초밥 가짓수의 최대값
idx = k-1 # 끝 인덱스
for i in range(N): # 시작 인덱스 i
    if idx >= N:
        idx -= N

    if i < idx:
        s = li[i:idx+1]
    else:
        s = li[i:] + li[:idx+1]
    # 리스트의 i번째 초밥부터 idx번째 초밥까지 새로운 리스트에 저장

    s = set(s) # 중복되는 요소를 한 번만 포함시키기 위해 세트로 전환
    s.add(c) # 세트에 쿠폰으로 먹을 수 있는 초밥 추가

    if m < len(s):
        m = len(s) # 중복된 초밥을 제거한 실제 먹을 수 있는 초밥의 가짓수를 확인해 최대값 갱신

    idx += 1 # 끝 인덱스 +1

print(m)