import heapq
# 우선순위 큐 알고리즘이 구현된 모듈
# heapq.heappush(heap, item) : item을 heap에 추가
# heapq.heappop(heap) : heap에서 가장 작은 원소를 pop하여 반환
# heapq.heapify(li) : 리스트 li를 heap으로 변환한다
import sys

n = int(sys.stdin.readline())
heap = [] # 빈 리스트를 생성한 후 heapq의 함수를 호출할 때마다 매개변수로 전달

for i in range(n):
    a = int(sys.stdin.readline())
    if a==0: # 0이 입력되었을 때
        if not heap: # 힙이 비어있다면
            print(0) # 0을 출력
        else: # 힙이 비어있지 않다면
            now = heapq.heappop(heap) # 힙에서 가장 작은 값을 pop
            print(now) # pop한 값 출력
    else:
        heapq.heappush(heap, a) # heap에 a를 push