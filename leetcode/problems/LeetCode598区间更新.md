

## 区间更新

 由题意知道对于一个矩阵，进行若干次操作，每次操作是对从(0,0)到(a,b)的矩阵内各个元素数值加一。

问你最终最大元素有几个？

不要傻傻的模拟然后去遍历求和，简单题蕴涵很多小技巧。

那个位置元素最大？

> （0,0）肯定最大啊

为什么？

> 因为每次区间必定覆盖(0,0)啊

那么问题就转化为：维护一个最大一直被覆盖的区间。

怎么维护？

> 刚开始可以为(m,n)区间，后序每次来一个(a,b)跟当前最大区间边界比较，如果小那么进行更新。最终返回两者乘积即可。

![image-20211107142011130](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20211107142011130.png)



实现代码：

```java
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if(ops.length==0)
            return m*n;
        int right=n,down=m;
        for(int arr[]:ops){
            if(arr[0]<down){
              down=arr[0];  
            }
            if(arr[1]<right){
                right=arr[1];
            }
        }
        return (right)*(down);
    }
}
```

