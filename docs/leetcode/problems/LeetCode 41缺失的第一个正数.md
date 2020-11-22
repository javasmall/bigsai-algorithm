## LeetCode 41缺失的第一个正数
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017185913146.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

对于这题，如果不要求其他的，给一个数组空间的话，可以使用数组作为索引，直接干0ms：

```java
public int firstMissingPositive(int[] nums) {
	int time[]=new int[nums.length+1];
	for(int i=0;i<nums.length;i++)
	{
		if(nums[i]>0&&nums[i]<nums.length)
		{
			time[i]++;
		}
		
	}
	for(int i=1;i<time.length;i++)
	{
		if(time[i]==0)
			return i;
	}
	return nums.length;
   }
```
但问题是人家要求的是**常数级别空间**。还是没想出来忍不住看了下题解恍然大悟：

**数组妙用**。分析问题，求未出现的最小正数，**也就是最理想的情况是1**.否则就找个未出现的最小的。这题核心的想法是复用数组，**我们只需标记这个位置的数字有没有出现过！** 所以可以用负数表示。

按照以下三点分析，首先我们知道这个数组中可能有负数或者大于数组长度数值的数存在，我们第一次遍历要将这些数置为1，并且看看1是否存在。如果不存在直接返回1即可。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101719323092.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

第二次遍历，将对应编号位置数改为负数(注意初始正负)。注意取值。

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017194018912.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

第三次遍历找到第个为正数的那个位置，返回编号加1.如果为正数说明这个数未出现。

具体ac代码为：

```java
 public int firstMissingPositive(int[] nums) {
		boolean jud =false;
		//第一轮循环，将无用的数字置为1 如果整个数列中没有1 那么将返回1
		for(int i=0;i<nums.length;i++)
		{
			if(nums[i]<=1||nums[i]>nums.length)
			{
				if(nums[i]==1)jud=true;
				nums[i]=1;
			}
		}
		if(!jud)return 1;
		//第二轮循环，所有数字现在目前的状态是1 - nums.length区间内
		for(int i=0;i<nums.length;i++)
		{
			int index=Math.abs(nums[i])-1;//该位置对应的索引
			
			if(nums[index]>0) {nums[index]=-nums[index];}
		}
		for(int i=0;i<nums.length;i++)
		{
			if(nums[i]>0)return i+1;
		}
		return nums.length+1;
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017194526630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)