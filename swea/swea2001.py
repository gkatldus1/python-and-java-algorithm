T = int(input())
for tc in range(1,T+1):
    N, M = map(int,input().split())
    arr = [list(map(int,input().split())) for _ in range(N)]
    n = M-1
    _max = 0
    #N-M+1 범위의 idx만큼 순회
    for i in range(N-n):
        for j in range(N-n):
            _sum = 0
            #해당 인덱스에서 MxM 범위 내 원소합을 저장
            for di in range(M):
                for dj in range(M):
                    _sum += arr[i+di][j+dj]
            #최대값과 비교하며 최대값 저장
            if _sum > _max:
                _max = _sum
    print('#{} {}'.format(tc, _max))