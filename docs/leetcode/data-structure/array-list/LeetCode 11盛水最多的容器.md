## LeetCode 11盛水最多的容器
### 题目描述
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821154903587.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
示例：

输入：[1,8,6,2,5,4,8,3,7]
输出：49


### 分析
对于这题来说，要求是求能够组成容器最大面积(忽略宽度)。直白的讲就是从若干个数字中找到一对数字，它们的**距离和较小的数字成绩最大**。

越好的情况就是数字很大(高度很高)，并且距离也足够长！但是也很可能出现最大的在中间的情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821160452481.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
**思路一：**
这题可以使用暴力的方法求解，枚举所有可能的情况，我们知道每个容器需要两根柱子组成，我们每次遍历柱子让当前柱子成为最矮的，去找最长的那个计算结果。当然这个结果可能是左侧，也可能是右侧。所以要找到远的那个比当前数字大的结果与max进行比较即可。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821161232823.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
在具体的实现方面，注意一下相邻距离为1就可以啦：

```java
public int maxArea(int[] height) {

		int max = 0;
		for (int i = 0; i < height.length; i++) {
			int left = 0, right = 0;
			for (int j = i; j >= 0; j--) {
				if (height[i] <= height[j]) {
					left = j;
				}
			}
			for (int j = i; j < height.length; j++) {
				if (height[i] <= height[j]) {
					right = j;
				}
			}
			max = Math.max(max, Math.max((i - left) * height[i], (right - i) * height[i]));
		}
		return max;
	}
```
不过效果很差……
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821161558530.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)
这种方法就是没任何进步的方法。

**思路二：**
这题其实可以从两侧双指针动态试探，初始情况没什么疑问，但是下一次到底是左面指针向右移动还是右侧指针向左移动呢？你要清楚：
- 无论左移还是右移动，数字之间的区间都是减小的(距离缩小)
- 如果移动较大的那个，下一次移动结果一定不可能大于这一次结果！(就算你的下一个很高，但是要根据最矮的来)
- 所以我们每次移动，把较小的那个指针向中间移动，寻找更大的可能

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821163254310.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

你可能会担心，这样看从一个位置元素来看确实满足，会不会你这样漏了更好的，当然不会，我说了当前情况是这个矮个子能够到达的最大情况，已经是一个极限，想要突破这个极限，就只能去找更高的组合。

实现代码为(具体可以看我另一篇优化的思路)：

```java
public int maxArea(int[] height) {
		int max = 0;
		int left = 0;
		int right = height.length - 1;
		int team = 0;
		int len = height.length;
		int leftvalue=0;int rightvalue=0;
		while (len-- > 0) {
			leftvalue=height[left];rightvalue=height[right];
			if (leftvalue < rightvalue) {
				team = leftvalue * len;
				if(max<team) {max=team;}
				left++;
			} else {
				team = rightvalue * len;
				if(max<team) {max=team;}
				right--;
			}

		}
		return max;
	}
```
效果良好：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200821163605635.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai我请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201114211553660.png)