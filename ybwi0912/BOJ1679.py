import sys

N = int(input()) # 숫자 게임에서 사용하는 정수의 수
li = list(map(int, input().split())) # 사용하는 정수
K = int(input()) # 최대 사용 횟수
# input

arr = [sys.maxsize for i in range(li[-1]*K+1)] # 리스트의 길이 = 만들어질 수 있는 수의 최댓값 (=사용하는 정수 중 가장 큰 숫자를 K번 사용했을 때)
# 1~만들어질 수 있는 수의 최댓값 길이의 리스트. 각 인덱스를 만드는 데에 필요한 정수의 수의 최솟값을 구해 저장한다
arr[1] = 1
# 사용하는 정수에는 반드시 1이 포함. 1을 만드는 데 필요한 정수의 수는 1개
b = 0
for i in range(1, li[-1]*K+1):
    if i in li:
        arr[i]=1 # 사용하는 정수 리스트에 포함되어 있다면 해당 위치의 값을 1로 정한다(해당 정수를 만드는 데 필요한 정수의 수는 1개이기 때문)
    else:
        for j in range(1, i//2+1):
            arr[i] = min(arr[i], arr[j] + arr[i-j])
            # 예: arr[5] = arr[1]+arr[4], arr[2]+arr[3] 중 최소값

    if arr[i] > K: # 정수 i를 만드는 데에 필요한 정수의 수가 최대 사용 횟수 K를 초과하는 순간
        if i%2==0:
            a = "holsoon"
        else:
            a = "jjaksoon"
        # 승자 판정

        b = i # 몇 번째 수에서 이겼는가

        break # 반복 종료
# operation

print("%s win at %d"%(a, b))
# output