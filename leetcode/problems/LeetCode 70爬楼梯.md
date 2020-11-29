# LeetCode 70爬楼梯

**题目描述：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201122153358363.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
**分析：**
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

