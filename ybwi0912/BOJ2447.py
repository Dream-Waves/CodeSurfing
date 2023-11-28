N=int(input())

def func(l):
    if l==1:
        return ['*']

    stars = func(l//3) # 길이를 3씩 나눠가며 재귀함수

    li=[]

    for s in stars:
        li.append(s*3) # 윗부분
    for s in stars:
        li.append(s + " " * (l//3) + s) # 중간 부분
    for s in stars:
        li.append(s*3) # 아랫부분

    return li # 해당 리스트에 길이가 3인 별부터 차근차근 붙여나간다

print('\n'.join(func(N))) # 리스트의 각 요소 사이에 \n을 넣어 출력