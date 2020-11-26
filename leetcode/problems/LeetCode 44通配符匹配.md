## LeetCode 44通配符匹配
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020102320233083.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023202406441.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

这题的话其实和前面的正则表达式很像，但是又略有区别。如果`dp[i][j]`表示匹配串前i个和模式串j个匹配情况(boolean类型)。核心的状态转移方程为：
```
dp[i][j]=dp[i-1][j-1] (s[i]==p[j]||p[j]=='?')
dp[i][j]=dp[i][j-1]||dp[i-1][j] (p[j]=='*');
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023204703539.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
```java
public boolean isMatch(String s, String p) {
       int slen=s.length();
		int plen=p.length();
		char p1[]=p.toCharArray();
		char s1[]=s.toCharArray();
		boolean dp[][]=new boolean[slen+1][plen+1];
		dp[0][0]=true;
        for(int i=0;i<plen;i++)
        {
        	if(p1[i]=='*')
        	{
        		dp[0][i+1]=true;
        	}
        	else 
        		break;
        }
		
		for(int j=1;j<=plen;j++)//遍历模式串
		{
			for(int i=1;i<=slen;i++)//遍历匹配串
			{
				//相等可以匹配
				if(p1[j-1]=='?'||s1[i-1]==p1[j-1])//当前单词可以匹配
				{
					dp[i][j]=dp[i-1][j-1];
				}
				else if(p1[j-1]=='*')//可以匹配任意多个
				{
					dp[i][j]=dp[i][j-1]||dp[i-1][j];
				}
			}
		}
		//System.out.println(dp[s.length()][p.length()]);
		return dp[slen][plen];
   }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023210232290.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
至于还有贪心算法和其他优化方案，今天就不更了，下次可能会补更。



本次就到这里啦，**感觉不错记得点赞或一键三连哦**，个人公众号：`bigsai` 回复 **bigsai** 更多精彩和资源与你分享。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201021104144791.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)