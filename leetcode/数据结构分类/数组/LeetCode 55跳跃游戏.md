## LeetCode 55跳跃游戏
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

>输入: [2,3,1,1,4]
>输出: true
>解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。

示例 2:
>输入: [3,2,1,0,4]
>输出: false
>解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

**分析**
这题我们可以使用一个O(n)的方法，和前面一些题的方法也很相似，遍历这个数组，遍历的同时用一个index标记最大值。如果可以更新最大值那么就更新，如果index比最大值还大，那就直接返回false。

**可到达的：**
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110815342897.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

**不可到达的：**
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020110815350693.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

具体ac代码：

```java
public boolean canJump(int[] nums) {
     boolean jud[]=new boolean[nums.length];
	 int maxlen=nums[0];
	 int index=0;
	 while (index<nums.length) {
		if(index>maxlen)
			return false;
		if(index+nums[index]>maxlen)
		{
			maxlen=index+nums[index];
		}
		else {
			index++;
		}
	}
	 return true;
   }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108103349181.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)