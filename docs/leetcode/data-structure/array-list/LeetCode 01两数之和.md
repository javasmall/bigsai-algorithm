## LeetCode01两数之和
**题目描述**：
>给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

**示例**:

>给定 nums = [2, 7, 11, 15], target = 9
>因为 nums[0] + nums[1] = 2 + 7 = 9
>所以返回 [0, 1]

**分析**：
题意就是让你从数组中找到两个位置他们对应位置的和为`target`。

本题主要有暴力和哈希两种方法：

**法一：暴力法**
把所有两两配对的问题全部遍历出来，直到找到满足题意的结果为止，时间复杂度O(n2)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020080515351661.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
代码：
```java
/*
 * 时间60ms 
 */
 public int[] twoSum(int[] nums, int target) {
       int a[]=new int[2];
	  for(int i=0;i<nums.length;i++)
	  {
		  for(int j=i+1;j<nums.length;j++)
		  {
			  if(nums[i]+nums[j]==target)
			  {
				  a[0]=i;
				  a[1]=j;
				  return a;
			  }
		  }
	  }
	  return a;
    }
```
**法二：借助哈希(一次)**

对于上述问题，你可能会疑问：能不能快速的直接知道这个数据是否存在呢？本题得目标是求得**两个位置的和为target**。这种问题当然可以使用哈希帮忙处理啊：

- 在第一次遍历你可以用一个HashMap先把各个位置的`值-位置`当成`Key-Value`存储，然后进行第二次遍历只需要判断这个HashMap中是否存在(target-a[index])值就可以判断是否存在了。(这种情况要先遍历一遍，n个元素都要)




![在这里插入图片描述](https://img-blog.csdnimg.cn/20200805155904874.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
**但是这种情况遇到两个相同的元素，只会储存一个Hash，就会被替代而出现错误！**
**能不能正确且再简单一点？**
答案是可以的，其实我们不需要用hash存储所有，边走边存即可。为什么我们可以这么考虑？因为如果从数的特性来看：

- 数是一对形式出现的
- 一对有**前后位置之分**，在遍历到前的时候不一定会找到后面的元素，但是遍历到后面的元素前面一定被我们存储了。



![在这里插入图片描述](https://img-blog.csdnimg.cn/20200805161620184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

ac代码为：

```java
 /*
  * 3ms
  */
public int[] twoSum2(int[] nums, int target) {
    int a[]=new int[2];
    Map<Integer, Integer>map=new HashMap<Integer, Integer>();
    for(int i=0;i<nums.length;i++)
    {
	  if(map.containsKey(target-nums[i]))
	  {
		  a[0]=i;
		  a[1]=map.get(target-nums[i]);
		  return a;
	  }
	  else {
	      map.put(nums[i], i);	
	  }
    }
	  return a;
  }
```

## 结语



原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

