## LeetCode 69x的平方根实现 int sqrt(int x) 函数。

>计算并返回 x 的平方根，其中 x 是非负整数。
>由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:
>输入: 4
>输出: 2

示例 2:
>输入: 8
>输出: 2
>说明: 8 的平方根是 2.82842..., 
>由于返回类型是整数，小数部分将被舍去。

**分析**
本题有三个方法求解分别为：
- 暴力求解(不推荐）
- 二分查找(推荐)
- 牛顿迭代法

对于本题，笔者就实现一个二分查找找到这个数字，而牛顿迭代法有空可以自行学习(也不是很难原理可以找专门文章看一看)。

```java
  public int mySqrt(int x) {
   //二分
   int l=0,r=x/2+1;
   while (l<r) {
	 long mid=(l+r)/2;
	 if(mid*mid<=x)
	 {
		 if((mid+1)*(mid+1)<=x)
		 {
			 l=(int) (mid+1);
		 }
		 else {
			return (int) mid;
		}
	 }
	 else {
		r=(int) mid;
	}
}
   return l; 
}
```
## 爬楼梯
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201122153358363.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
分析：
入门dp，状态转移方程为：初始赋值好后，`dp[i]=dp[i-1]+dp[i-2];`

```java
 public int climbStairs(int n) {
     if(n<3)return n;
	 int dp[]=new int[n+1];
	 dp[1]=1;
	 dp[2]=2;
	 for(int i=3;i<n+1;i++)
	 {
		 dp[i]=dp[i-1]+dp[i-2];
	 }
	 return dp[n];
 }
```
## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/2e5d203d7825d0eea79b027654dc996d.png)