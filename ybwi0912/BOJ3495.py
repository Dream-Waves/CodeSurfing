h, w = map(int, input().split())
ans = 0
for i in range(h): # 한 줄씩 확인
    l = list(input())
    t = False # 다각형 영역인지 확인하기 위한 boolean 변수
    a=0 # 해당 줄에 위치하는 다각형의 넓이를 카운트할 변수
    for j in range(w):
        if t==False and (l[j]=='/' or l[j]=='\\'): # 만약 다각형 영역이 시작되지 않았고 /나 \가 존재한다면
            t=True # 이 위치부터 다각형이 시작되었다고 간주
            a += 0.5 # 1*1 크기의 삼각형 넓이만큼 더해준다
        elif t==True and (l[j]!='/' and l[j]!='\\'): # 다각형의 외곽선 내의 영역이라면
            a += 1
        elif t==True and (l[j]=='/' or l[j]=='\\'): # 다각형이 끝나는 부분이라면
            a += 0.5
            t=False # 다각형 영역이 끝났음을 표시
    ans += a # 한 줄 전체 검사 후 카운트한 영역을 답에 더해준다

print(int(ans))
