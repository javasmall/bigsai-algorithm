# LeetCode 72编辑距离(dp)



## 题目描述

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：

```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

示例 2：

```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```

提示：

```
0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成
```



## 分析



这题其实是有难度，笔者刚开始做的时候以为是最小公众子序列(LCS),但是后来发现并不是但是还是有点联系的，dp的思想很相似。

分析一下目的：

- word1字符串转成word2字符串

分析一下操作：

- 插入一个字符
- 删除一个字符
- 替换一个字符

即很可能两个字符向上或者向下可能转换成三种状态(有三种方式至少)。如果从递归的思路思考这道题，从后往前递推。

- 如果两个字符相等，操作的次数直接向前推。
- 如果不相等，分别递归取最小的(修改，插入，删除)

![image-20201128155501890](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201128155501890.png)



但是这样明显有很多次**重复计算**，超时，你可以使用记忆化：即用数组将对应递归编号的值记录下来，如果数组有值，那么不需要递归重复计算，否则计算完将值赋值到该位置。这样可以避免大量重复计算。



但是我们这题可以使用动态规划的思路，从前往后看。用`dp[i][j]`表示word1的前i个转成word2的前j个需要转动的次数。动态规划的核心就是初始化和状态方程。

- 对于初始化，如果一个串串长度为0，编程另一个串串，那么肯定只有插入和删除这两种操作。**并且初始化次数和字符串的长度一致。**

- 对于状态转移方程

  如果`a[i]==b[j]`那么说明这个字符相等不需要操作，总次数还是前面`a[0-(i-1)]`和`b[0-(j-1)]`串操作的次数。

  如果`a[i]！=b[j]`那么就有三种可能取最小的啦并且加一 `dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;`

具体可以参考下图：

![image-20201128160518159](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201128160518159.png)



实现代码：

```java
public int minDistance(String word1, String word2) {
  char ch1[]=word1.toCharArray();
  char ch2[]=word2.toCharArray();
  if(word1.length()==0)return word2.length();
  if(word2.length()==0)return word1.length();
  int dp[][]=new int[ch1.length+1][ch2.length+1];
  for(int i=1;i<ch1.length+1;i++)
  {
    dp[i][0]=i;
  }
  for(int j=1;j<ch2.length+1;j++)
  {
    dp[0][j]=j;
  }
  for(int i=1;i<ch1.length+1;i++)
  {
    for(int j=1;j<ch2.length+1;j++)
    {
      if(ch1[i-1]==ch2[j-1])
      {
        dp[i][j]=dp[i-1][j-1];
      }
      else {
        dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
      }
    }
  }
  return dp[ch1.length][ch2.length];	
}
```

![image-20201128160822080](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201128160822080.png)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)