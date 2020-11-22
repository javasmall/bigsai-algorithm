## LeetCode 27移除元素
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:
>给定 nums = [3,2,2,3], val = 3,
>函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
>你不需要考虑数组中超出新长度后面的元素。

示例 2:
>给定 nums = [0,1,2,2,3,0,4,2], val = 2,
>函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
>注意这五个元素可为任意顺序。
>你不需要考虑数组中超出新长度后面的元素。


说明:
>为什么返回数值是整数，但输出的答案是数组呢?
>请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
>你可以想象内部操作如下:
>// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
>int len = removeElement(nums, val);
>// 在函数里修改输入数组对于调用者是可见的。
>// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
>for (int i = 0; i < len; i++) {
>print(nums[i]);
>}

**分析：**
用一个index标记当前真正的位置，在遍历的过程中如果当前位置数值和目标数值不相等那么就赋值，如果和待删除数据相等那么跳过不赋值。这就是遍历一次重新赋值的过程。

ac代码为：

```java
public int removeElement(int[] nums, int val) {
	 int index=0;
	 
	 for(int i=0;i<nums.length;i++)
	 {
		 if(nums[i]==val)
			 continue;
		 nums[index++]=nums[i];
	 }
	 return index;
  }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919140246503.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

