## LeetCode 80删除排序数组中的重复项 II
给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。


**说明：**

为什么返回数值是整数，但输出的答案是数组呢？

请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下：
```
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

示例 1：
```
输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
你不需要考虑数组中超出新长度后面的元素。
```
示例 2：
```
输入：nums = [0,0,1,1,1,1,2,3,3]
输出：7, nums = [0,0,1,1,2,3,3]
解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
你不需要考虑数组中超出新长度后面的元素。
 
```
提示：
```
0 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums 按递增顺序排列
```
**分析：**
刷这题前回顾26题  [删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) 
而这一题同样也是要用双指针原地修改数组。但是每个元素最多出现两次。对于两次我们应该如何考虑呢？

 枚举每个数的个数？然后大于二只使用两个？其实更好的方式是**这个数和它前面第二个数进行比较**，如果相等那么可以使用，如果不相等那么无法使用。如下图的比较有效数字就可以看出：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206180346599.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

但是我们在原地如何操作这个数组呢？- **双指针**

可以用一个fast用来遍历，用slow来表示存储真实有效的区域。
- 在具体的比较上，**fast只需要和slow前面第二个元素**比较即可(因为slow是真实储存的结果标记)，如果相等，那么直接fast继续，如果不等，那么将数据复制到slow且slow右移一位。
- slow 初始为2，fast初始为2(前面两个即使相同也会放到结果中)

具体看一次模拟过程：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206180427872.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206180513544.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


实现代码：

```java
public int removeDuplicates(int[] nums) {
	if(nums.length<2)
		return nums.length;
	int slow=2;
	for(int fast=2;fast<nums.length;fast++)
	{
		if(nums[fast]==nums[slow-2])
		{
			continue;
		}
		else {
			nums[slow++]=nums[fast];
		}
	}
	return slow;
  }
```