## LeetCode 50Pow(x,n)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201101102327278.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
很明显的快速幂算法，强烈推荐自己写的快速幂介绍：**[数据结构与算法—这可能是最易懂的快速幂讲解了](https://bigsai.blog.csdn.net/article/details/109355327)**
但是你需要注意一些地方：
- 负数处理，并且**负数可能是int最小值加个符号转换数值越界出错**。所以负数转正数的时候，将负数次幂拆分一个出来就可以转正数幂进行计算了。例如`5^-2147483648`=`5^-1`  x ` 5 ^-2147483647` =`（1/5 )` x`（1/5）^2147483647`  。int 范围为[-2147483648,2147483647].
- 注意好停止条件，这里理论上可以稍微重写个函数优化一下，但是由于快速幂logn级别的复杂度比较低，这里就不进行优化直接写了：

```java
 public double myPow(double x, int n) {
     if(n<0)
         return  (1.0/x)*myPow(1.0/x,-(n+1));
     if(n==0)
         return 1;
     else if(n%2==0)
         return myPow(x*x,n/2);
     else
         return x*myPow(x*x,n/2);
 }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201101103616993.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

