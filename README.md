## 로그 개수별 소요 시간

|로그 개수/분석 방식 |	싱글 스레드 |	멀티 스레드 (경쟁 상태) | synchronized | Atomic |
| - | - | - | - | - |
100	| 0ms	| 3ms | 3ms |	3ms |
1,000	| 3ms |	11ms |	10ms |	10ms |
10,000 |	12ms |	44ms |	54ms |	50ms |
100,000	 | 73ms | 	124ms | 	171ms | 	119ms | 
1,000,000	| 294ms | 479ms |	962ms |	509ms
10,000,000 |	2280ms |	2923ms |	6995ms |	3478ms |
100,000,000	| 22451ms	| OutOfMemoryError	| OutOfMemoryError | 	OutOfMemoryError
1,000,000,000	| 224313ms |	OutOfMemoryError |	OutOfMemoryError |	OutOfMemoryError
