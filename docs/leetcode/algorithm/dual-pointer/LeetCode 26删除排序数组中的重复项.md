### LeetCode 26删除排序数组中的重复项
![image-20201117204631926](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117204631926.png)

**分析：**
这题很简单，用一个index标记当前位置，用i遍历判断是否和前一个相等，如果相等不操作，不相等就重新存入，最后这个index返回即可，当然，虽然是个int类型的函数，但是题目要求存到数组中会判定数组的，需要存一下。

实现代码为：

```java
public int removeDuplicates(int[] nums) {
		 int index=1;
		 for(int i=1;i<nums.length;i++)
		 {
			 if(nums[i]!=nums[i-1])
			 {
				  nums[index++]=nums[i];
			 }
		 }
		 return index;
	 }
```



finish！