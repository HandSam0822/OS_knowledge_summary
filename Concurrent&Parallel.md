Concurrent(並發) - 同時存在, 輪流存在

多個 task 不一定要同時執行，只要能在重疊的時間段內執行就好  
Example:  
task1 執行 ---- 執行 ----  
task2 ---- 執行 ---- 執行

從頭到尾，任一時間點都只有一段 code 在執行，但是由於 task1 和 task2 在重疊時間段內執行，這是一個 concurrent 的設計。值得注意的是，不像 parallel 一定要 multi-thread，single thread 就可以支持 concurrent

Parallel(並行) - 同時執行
即便 init 了 2 個 thread(在同個 process 中),如果 1 個 process 只有 1 核(CPU)，那這兩個 thread 並不會並行運算，而是交互執行。因此 parallel 一定是 multi-thread。然而即使達成並行運算，thread 之間仍有可能因為爭搶資源而 block 住對方，如果雙方互相需要對方的資源，甚至可能造成 deadlock

`TODO`

- 假設 task2 的 input 是 task1 的 output aka task2 depends on task1，今天我開 2 個 thread 分別跑 task1 和 task2 希望達成並行運算，結果會是哪一個?
  - task2 照常運行但是因為 task1 的 output 還沒出來，因此以 input=null 來處理
  - task2 一直等待直到 task1 完成才繼續執行

## Reference

[还在疑惑并发和并行？](https://laike9m.com/blog/huan-zai-yi-huo-bing-fa-he-bing-xing,61/)

[并发与并行的区别是什么？](https://www.zhihu.com/question/33515481)  
[Parallel and asynchronous programming with Java](https://medium.com/nerd-for-tech/parallel-and-asynchronous-programming-with-java-54278db2d839)
