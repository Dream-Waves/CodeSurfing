from collections import deque

N, M, R = map(int, input().split())
li = []
for i in range(N):
    li.append(list(map(int, input().split())))
answer = [[0]*M for _ in range(N)]
deq = deque()
# input

for i in range(min(N, M)//2):
    # 1차원 배열로 변환
    deq.clear()
    deq.extend(li[i][i:M-i]) # 위쪽
    deq.extend([row[M-i-1] for row in li[i+1:N-i-1]]) # 오른쪽
    deq.extend(li[N-i-1][i:M-i][::-1]) # 아래쪽
    deq.extend([row[i] for row in li[i+1:N-i-1]][::-1]) # 왼쪽
    # i번째 껍데기를 왼쪽 위 요소부터 시계 방향으로 뜯어 1차원 배열로 만든 다음

    deq.rotate(-R)
    # 반시계방향으로 R번 회전

    for j in range(i, M-i):
        answer[i][j] = deq.popleft() # 위쪽
    for j in range(i+1, N-i-1):
        answer[j][M-i-1] = deq.popleft() # 오른쪽
    for j in range(M-i-1, i-1, -1):
        answer[N-i-1][j] = deq.popleft() # 아래쪽
    for j in range(N-i-2, i, -1):
        answer[j][i] = deq.popleft() # 왼쪽
    # 회전된 1차원 배열을 다시 2차원 배열로 변환하여 저장
# opeartion

for i in range(N):
    for j in range(M):
        print(answer[i][j], end = ' ')
    print()
# output
