## LeetCode 10正则表达式匹配(动态规划)
![image-20201115193811962](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115193811962.png)

## 递归(超时)
这题刚开始见到，还以为遇到原题了，因为跟剑指offer的其中一题非常像，[剑指offer第52题](https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&&tqId=11205&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)正则表达式，只不过那题给的两个char类型的数组，当时弱弱的用递归暴力过了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819190925723.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
然后一顿操作把上次递归的方法重写过来，结果超时了……
但是还是把这种递归的思路讲一下，递归主要进行匹配所有情况，主要是看当前位置两个串串能不能匹配。

需要考虑`*`情况可以匹配，因为`*`可以出现0次，1次多次。那么在遇到使用`*`的如果匹配了，可以通过递归实现下面三者方式
- 它可以使用0次(相当于跟字符串下一部分匹配  `a*aa`和`aa`这个第一个`a*`可以看成0次)
- 也可以使用1次(在当前往后移例如`a*aa`和`aaa`转成`aa`和`aa`的匹配)
- 也可以使用多次(例如`a*`和`aaa`转成`a*`和`aa`的匹配)

同样，如果遇到`*`不可以匹配，那么就使用0次就行了(`b*aa`和`aa`匹配转换成`aa`和`aa`匹配)

如果下一个不是`*`，那就考虑是否相当或者模式字符是否为`.`进行继续匹配或者终止就可以，在考虑一些开始结束情况就可以了，一个大概的思维导图可以看一下。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819193707736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

这部分实现的代码如下：

```java
public static boolean isMatch2(String s, String p) {
		//System.out.println(s+" "+p);
		if (p.length() == 0)// 模式串为false
		{
			if (s.length() == 0)
				return true;
			return false;
		} else if (s.length() == 0) {// 匹配串为0
			if (p.length() % 2 == 1)
				return false;
			else {
				for (int i = 1; i < p.length(); i += 2) {
					if (p.charAt(i) != '*')
						return false;
				}
				return true;
			}
		} else if (p.length() == 1) {//匹配串长度为1
			if((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')&&s.length()==1)//可以匹配
				return  true;
			else {
				return false;
			}
		} else {// 两个串串正常长度
			if(p.charAt(1)=='*')//下一个为*
			{
				if(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')//可以匹配 分别用0次 用若1次 用若干次
				{
					return isMatch(s.substring(1), p)||isMatch(s.substring(1), p.substring(2))||isMatch(s, p.substring(2));
				}
				else {//不匹配只能用0次
					return isMatch(s, p.substring(2));
				}	
			}
			else {
				if(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')
					return isMatch(s.substring(1), p.substring(1));
				else {//完全失败
					return false;
				}
			}
		}
```
很遗憾的超时了，不过在剑指offer是可以过的，主要遇到这种字符就会很麻烦：

```java
isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c")
```
因为这里面匹配中的a*任意一个都可以使用若干次导致递归种类太多爆栈。嘤嘤嘤。

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819194059117.gif#pic_center)



## 动态规划
这题正确而大众的解法当然是动态规划了，我们知道动态规划重在**动态的**规划方程。并且当前结果是基于**父结果的**。这题刚好就可以使用动态规划来解答。

我们使用我们声明一个`dp[][]=new boolean[匹配串长度+1][模式串长度+1]` 的二位数组用来储存结果， 其中`dp[i][j]`表示匹配串前i个和模式串前j个是否匹配。最终匹配串和模式串是否匹配就是返回`dp[匹配串长度][模式串长度]`.

对于动态规划的问题，我们一般会空余出0号位放在越界等特殊情况，所以我们声明的二维数组大小长宽都大1，因为**0号**在`dp[][]`表示的是**空串的结果**而不是一号位置串的结果。然后我们在搞动态规划题一般需要以下几步：
- 声明dp数组，理解其含义
- 声明一些初始情况(一般为0)
- 找正常情况动态方程式

这里的初始我们是`dp[0][0]=true`表示两个空串可以匹配。

我们分析这个`dp[i][j]` **匹配串前i个，模式串前j个是否匹配**.其实这个分析和之前递归还是有点相似的：

首先如果模式串pattern第j个如果是`*`，**以下两种情况任意一种匹配成功即可。**
- 如果`dp[i][j-2]==true`那么`dp[i][j]`肯定为true，因为可以把它看成一个空串。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819200458348.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

- 如果`dp[i][j-2]`不为true也不要紧,如果匹配串和模式串前一个字符可以匹配并且`dp[i-1][j]`为true，那么也可以匹配(`a*`和`a` )

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819200847204.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

如果模式串第j个不为`*`那么就是常规匹配了，如果当前位置字符不匹配，那么就为false，如果当前位置匹配且`dp[i-1][j-1]==true`那么`dp[i][j]`就为true：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819201255885.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

当然，以上所有考虑i-1的情况i不能等于0.

综上分析得到dp方程为：

```java
if(模式串当前为*)
dp[i][j]==dp[i][j-2]||(dp[i-1][j]&&两串当前字符可以匹配)
else
dp[i][j]=dp[i-1][j-1]&&两串当前字符可以匹配
```

具体实现需要**注意下标编号在字符串位置和dp下标的含义**，具体实现的代码为：

```java
public static boolean isMatch(String s, String p) {
	boolean dp[][]=new boolean[s.length()+1][p.length()+1];//默认为false
	dp[0][0]=true;
	for(int i=0;i<=s.length();i++)
	{
		for(int j=1;j<=p.length();j++)
		{
			if(p.charAt(j-1)=='*')//该位置为*
			{
				dp[i][j]=dp[i][j-2];//模式用了0次的看看是否能够匹配，能匹配最好，不能匹配继续
				if(!dp[i][j])//不能匹配
				{
					if(i==0) {continue;}
					else if(s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.')//可以匹配
					{
						dp[i][j]=dp[i-1][j];
					}
				}
			}
			else {//正常字符
				if(i==0){continue;}
				else if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.') {//这个位置可以匹配
					dp[i][j]=dp[i-1][j-1];
				}
			}				
		}
	}
	return dp[s.length()][p.length()];	
}
```

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200819202458627.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201114211553660.png)