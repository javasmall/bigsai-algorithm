# LeetCode 04寻找两个正序数组的中位数(困难)二分法

>公众号：bigsai

**题目描述：**
>给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
>请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
>你可以假设 nums1 和 nums2 不会同时为空。


**示例 1:**
>nums1 = [1, 3]
>nums2 = [2]
>则中位数是 2.0

**示例 2:**
>nums1 = [1, 2]
>nums2 = [3, 4]
>则中位数是 (2 + 3)/2 = 2.5

## 归并法(O(m+n))
分析之前小吐槽一句，这题自己真的没想到O(log(m+n))的方法，只能想到O(m+n)的归并，没想到怎么去使用二分，后来看了题解也是才明白。但也算自己理解了和大家分享一下。

对于这个问题或许本身不难，但是可能难在O(log(m+n))的时间复杂度上。

如果真的无法想到好的方法，先想着过关，该用什么方法呢？

**法一暴力法：**
可以将两个数组添加到一个总的数组中，然后给这个数组进行排序。正常的排序是**O(nlogn)**的时间复杂度。排序之后根据奇数偶数取中位数即可。

**法二归并法：**
给的两个数组本身是有序的，想必熟悉归并排序的朋友们应该能一下就想出来这个方法，两个有序的.只需按照以下流程即可完成归并：
- 待归并的两个数组分别设置两个指针leftindex，rightindex均从0开始。新数组也设置游标index。
- 比较两数组leftindex和rightindex位置的值，较小的那个赋值到新数组中同时新数组游标和较小的那个游标均加一。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809175822685.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

- 重复到其中一个数组遍历完，另一个数组剩余值直接加入后面即可。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809180028805.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

**实现代码：**

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 int a[]=new int[nums1.length+nums2.length];
		 int lindex=0,rindex=0;
		 int index=0;
		 while (lindex<nums1.length&&rindex<nums2.length) {
			if(nums1[lindex]<nums2[rindex])
			{
				a[index++]=nums1[lindex++];
			}
			else {
				a[index++]=nums2[rindex++];
			}
		}
		 while (lindex<nums1.length) {
				a[index++]=nums1[lindex++];
		}
		 while (rindex<nums2.length) {
				a[index++]=nums2[rindex++];
		}
		 if(a.length%2==0)
			 return (double)(a[a.length/2-1]+a[a.length/2])/2;
		 else {
			return a[a.length/2];
		}
		 
	 }
```



## 二分法(O(log(m+n)))

想到有序的，又是O(log(m+n))的时间复杂度，估计大部分人都能想到二分，我当时也是一样，但是该怎么想呢这就是一个问题。记录下我当初错误的想法：
>二分，二分找到两个中间的。然后正常有个长的，有个短的，根据两个数值比较分类推测中位数应该在哪个区间……然后大脑就断电了。

**对于中位数的简单分析**：
- 如果两个数组长度和为奇数，那么最终这个中位数是由**一位数**确定的。
- 如果两个数组长度和为偶数，那么最终这个中位数是由**两位数取平均值**确定的。

**对两个数组的简单分析**：
- 两个数组应该有一个长一点，另一个点一点(等长也不影响)。
- 中位数可能让两个数组都分成两部分：一部分小于中位数，一部分大于中位数。**但两个部分合起来总数量应该一致**。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809195634325.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)

**对两数组和中位数位置分析**：
- 我们知道两数组虽然可能等长(不影响)，**但正常情况应该是一个长(m)一个短(n)**。长短数组分别对应的坐标m1和n1和中位数坐标有什么关系？
- 无论总和奇数偶数，都满足(m1+n1)=(m+n)/2;因为两个数组都是有序的所以总共小于中位数的占一半。其中m和n是定值。也就是不管你怎么变动，**这两个坐标编号是总和为定值得**！

**如何分析为定值得坐标**
- 既然两个坐标的总和为定值，那么可不可以把其中一个当为自变量，一个看成自变量呢？比如x+y=5你不好分析但是y=5-x，你分析x同时y就确定了。对吧？
- 那么选择长的那个作为变量还是短的那个作为变量呢？**短的，为啥，主要因为如果从长的当成变量咱们有些区域无法对应到短的(因为长度即使加上短的所有也到不了一半，处理起来麻烦)**：
![在这里插入图片描述](https://img-blog.csdnimg.cn/202008092016389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
- 但是短的就可以很好避免这种情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809202452204.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

所以我们就用二分去查找小的这个区间，找到最终的结果，你可能会问：**什么样情况能够满足确定这条线的附近就是产生中位数的**？
- 二分进行查找编号的时候，满足左侧都比线右侧小才行。这种情况在二分查找就是一个平衡的结果。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809203823189.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


**最后找到这个index线了。取值比较你还要有注意的地方**：
- 取左侧的时候左侧如果有index为0，取右侧的时候index为最大值：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200809204559299.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
- 所以这种在你最后取值的时候，需要考虑左右侧是否有值。同时取长的那个也要比较，因为可能出现等长情况例如：`1 2 3 4`,和`5 6 7 8`这种去到临界。需要判断**当然在实现过程用三目运算简化！**



**总的来说：**
- **根据短的进行二分查找位置**，先找到线index，说明中位数在附近产生。（奇数偶数在查找因为要除2可以通用表达式）
- 如果总个数**奇数**，那么就是线左侧最大的那个(两个比较或只有一个)
- 如果总个数**偶数**，那么就是线左侧最大的那个(两个比较或只有一个)和线右侧最小的那个(两个比较或只有一个)的值取平均，**注意是double类型**。
- 其他注意点，搞清index从0开始，**搞清逻辑上的第几个和数组显示使用的第几个的index的区别**。

附上代码：

```java
public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
	if(nums1.length>nums2.length)//保证num1长度小，如果不小我交换一下
	{
		int team[]=nums2.clone();
		nums2=nums1;
		nums1=team;
	}	
	 int k=(nums1.length+nums2.length+1)/2;//理论中位数满足的位置
	 int left=0,right=nums1.length;//二分查找短的
	 
	 while (left<right) {//找到对应位置
		int m1=(left+right)/2;//在短的位置
		int m2=k-m1;//在长的第几个
		//System.out.println(m1+" "+m2);
		if(nums1[m1]<nums2[m2-1])//left右移
			left=m1+1;
		else {//right左移
			right=m1;
		}
	}
	//System.out.println(left+" "+k);
	//左侧最大和右侧最小那个先算出来再说，根据奇偶再使用
	double leftbig= Math.max(left==0?Integer.MIN_VALUE:nums1[left-1], k-left==0?Integer.MIN_VALUE:nums2[k-left-1]);
	double rightsmall=Math.min(left==nums1.length?Integer.MAX_VALUE:nums1[left],k-left==nums2.length?Integer.MAX_VALUE:nums2[k-left]);
	//System.out.println(rightsmall);
	if((nums1.length+nums2.length)%2==0)
	{
		return (leftbig+rightsmall)/2;
	}
	else {
		return leftbig;
	}		
 }
```


## 结语
至于其他方法暂时先不学了，感觉这题还是挺有难度的，需要搞明白要点时候。

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201114211553660.png)

