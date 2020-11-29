## LeetCode 75颜色分类
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。


>进阶：
>你可以不使用代码库中的排序函数来解决这道题吗？
>你能想出一个仅使用常数空间的一趟扫描算法吗？


示例 1：
```
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```
示例 2：
```
输入：nums = [2,0,1]
输出：[0,1,2]
```
示例 3：
```
输入：nums = [0]
输出：[0]
```
示例 4：
```
输入：nums = [1]
输出：[1]
```

提示：
```
n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
```
**分析：**
方法比较灵活，可以用双指针，一个在左，一个在右，遍历的时候遇到0和2分别分配在左侧和右侧。

具体实现可用一个left，right标记左右往中间扩散，left左侧都是0，right右侧都是2，遍历的时候遇到需要交换的则交换，和快排的思想有些相似。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201129190007772.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



实现代码：

```java
class Solution {
    public void sortColors(int[] nums) {
         int left=0;
		 int right=nums.length-1;
		 for(int i=0;i<right+1;i++)
		 {
			 if(nums[i]==0)
			 {
				 int team=nums[left];
				 nums[left++]=0;
				 nums[i]=team;
			 }
			 else if(nums[i]==2)
			 {
				 int team=nums[right];
				 nums[right--]=2;
				 nums[i]=team;
				 i--;
			 }		 
		 }
    }
}
```
## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/2e5d203d7825d0eea79b027654dc996d.png)