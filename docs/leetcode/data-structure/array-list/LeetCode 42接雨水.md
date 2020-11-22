## LeetCode 42接雨水

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018144227454.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
**题目分析**
遇到这种题总有那么种似曾分析的感觉，**我来谈谈如何分析这道题。**

我的个人思路来说，我觉得这道题的核心就是看看能不能找到中间最高的元素。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018163008793.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

**找到这个最高点后**：
- 左右进行相等操作，从左来看，需要一个`l`和`lteam`,让lteam往右试探.
- 试探途中如果height[lteam]<height[l],继续前进，后面一定会有更大的。
- 试探途中如果height[lteam]>height[l],计算途中雨点和，然后l赋值为lteam，继续试探。

而同样右侧是进行一个从右往左的操作。

不过再具体实现上，本人**初次**只考虑从左往右然后遇到没有比它更大的找个次大的计算雨水。然后重新进行。具体代码为：

```java
public int trap(int[] height) {
    int l=0,r=l+1;
	int count=0;
	int max=0,maxindex=0;
	while (l<height.length&&r<height.length) {
		if(height[r]>=height[l])//找到更大的靠山
		{
			for(int i=l;i<r;i++)
			{
				count+=height[l]-height[i];
			}
			l=r;
			r++;
			max=0;maxindex=0;
		}
		else {//不大于
			if(height[r]>=max)
			{
				max=height[r];
				maxindex=r;
			}
	        
			r++;
			if(r==height.length)
			{
				for(int i=l+1;i<maxindex;i++)
				{
					count+=max-height[i];
				}
				l=maxindex;r=l+1;
				max=0;maxindex=0;
			}
		}
	}
	return count;
   }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018164618572.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
效果一般般因为遇到逆序形的会O(n2)算法。最好情况O(n)。

而换一种O(n)的写法之后，找到最大后右侧往左一次即可：

```java
public int trap(int[] height) {
int l=0,r=l+1;
int count=0;

while (l<height.length&&r<height.length) {
	if(height[r]>=height[l])//找到更大的靠山
	{
		for(int i=l;i<r;i++)
		{
			count+=height[l]-height[i];
		}
		l=r;r=l+1;
	}
	else {//不大于
		r++;
		if(r==height.length)//说明当前的left已经是天底下最大的值了
		{
			int max=0;
			for(int i=r-1;i>l;i--)
			{
				if(height[i]>max)
				{
					max=height[i];
				}
				else
				{
					count+=max-height[i];
				}
			}
			l=r;
		}
	}
}
return count;
```
效果还行：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018165038973.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)