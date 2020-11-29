## LeetCode 60排列序列
![image-20201118223043291](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201118223043291.png)

**分析：**
本题的话需要考虑数据排列的规律。先从整体宏观思路来分析：

n个数的排列组合的个数，有`n!`(n阶乘)种情况。**你要知道当前层次有多少个元素可以参加排列组合**，如果当前层有n个元素参加排列组合，第K个排列的第一个元素就是第`k/(n-1)!`个，锁定第一个元素后再将k求余(n-1)！再去锁定下一个元素直到结束。中途可以借助boolean数组标记元素使用过和未使用的。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114173049835.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


具体实现的代码为：

```java
public String getPermutation(int n, int k) {
     int jiecheng[]=new int[10];
	 jiecheng[0]=1;
	 for(int i=1;i<10;i++)
	 {
		 jiecheng[i]=jiecheng[i-1]*i;
	 }
	 boolean jud[]=new boolean[n+1];
	 StringBuilder sBuilder=new StringBuilder();
	 int len=n-1;k--;
	 while (len>=0) {
		int va=k/jiecheng[len];//在剩下的第几个区间
		int team=0;
		for(int i=1;i<=n;i++)
		{
			if(jud[i])
				continue;
			if(team==va)//找到有效的第几个
			{
				jud[i]=true;
				sBuilder.append(i);
				break;
			}
			team++;
		}
	
		k=k%jiecheng[len];
		len--;
	}
	return sBuilder.toString();
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114162714447.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)