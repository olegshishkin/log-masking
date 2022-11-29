# log-masking

```
Benchmark                              (dataSize)  Mode  Cnt    Score     Error  Units
MaskBenchmark.maskedRecursionToString          10  avgt    5    6,566 ±  16,548  ms/op
MaskBenchmark.maskedRecursionToString         100  avgt    5   13,080 ±  21,432  ms/op
MaskBenchmark.maskedRecursionToString        1000  avgt    5   39,595 ± 149,399  ms/op
MaskBenchmark.maskedRecursionToString        5000  avgt    5   90,748 ±  22,648  ms/op
MaskBenchmark.patternMatcherToString           10  avgt    5    5,306 ±   8,647  ms/op
MaskBenchmark.patternMatcherToString          100  avgt    5   23,328 ±  51,820  ms/op
MaskBenchmark.patternMatcherToString         1000  avgt    5  130,911 ±  68,901  ms/op
MaskBenchmark.patternMatcherToString         5000  avgt    5  593,911 ±   9,957  ms/op
MaskBenchmark.recursionToString                10  avgt    5    3,174 ±   3,043  ms/op
MaskBenchmark.recursionToString               100  avgt    5    7,471 ±   8,497  ms/op
MaskBenchmark.recursionToString              1000  avgt    5   27,448 ±  21,399  ms/op
MaskBenchmark.recursionToString              5000  avgt    5   64,977 ±  84,329  ms/op
MaskBenchmark.toString                         10  avgt    5    1,292 ±   1,864  ms/op
MaskBenchmark.toString                        100  avgt    5    3,141 ±   9,739  ms/op
MaskBenchmark.toString                       1000  avgt    5    9,922 ±  14,189  ms/op
MaskBenchmark.toString                       5000  avgt    5   34,008 ±  24,725  ms/op
```