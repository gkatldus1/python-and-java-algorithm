import itertools

def combine(self, n: int, k: int) -> List[List[int]]:
    results = []

    def dfs(elements, start: int, k: int):
        if k == 0:
            results.append(elements[:])

        for i in range(start, n + 1):
            elements.append(i)
            dfs(elements, i+1, k-1)
            elements.pop()
    
    dfs([], 1, k)
    return results

def combine(self, n: int, k: int) -> List[List[int]]:
    return list(itertools.combinations(range(1, N+1), k))
