## LeetCode 81搜索旋转排序数组 II
题目描述
>假设按照升序排序的数组在预先未知的某个点上进行了旋转。
>( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
>编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:
```
输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
```
示例 2:
```
输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
```
>进阶:
>这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
>这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

**分析**
在做之前还是要 看下这道题：[搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/) 。这题和那题的区别就是需要**考虑重复的情况**。

而这题最好的方法当然也是二分，但是二分的过程还是需要分类考虑一些情况的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206180301505.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)


这题我的方法和其他人的题解不太一样吧，大部分人是按照几种情况进行分类。但是核心也都很相似，要指导当前的mid在那个区间里面(每个区间可能出现的结果)。进行分类讨论情况。

个人ac的代码为：

```java
public static boolean search(int[] nums, int target) {
	 if(nums.length==0)return false;
	
	 if(nums[0]==target||nums[nums.length-1]==target)return true;
	 int l=0,r=nums.length-1;
	 while (l<r) {
		 //处理重复数字
		 while (l<r&&nums[l]==nums[l+1]) {
			l++;
		}
		 while (r>l&&nums[r]==nums[r-1]) {
			r--;
		}
		int mid=(l+r)/2;
		//System.out.println(l+" "+r+" "+mid);
		if(nums[mid]==target)
			return true;
		else if (nums[mid]>target) {//中间的比目标值大
			if(target>nums[r])//只可能出现在左侧
			{
				r=mid;
			}
			else if(target<nums[r])//目标小于最右侧  当前位置可能在左半区域 也可可能在右半区域
			{
				if(nums[mid]>=nums[r])//在左侧区域
				{
					l=mid+1;
				}
				else//在右侧区域
				{
					r=mid;
				}
			}
		}
		else if(nums[mid]<target){//nums[mid]<target 中间的比目标值小
			if(target<nums[r])//目标值比最右侧的小
			{
				l=mid+1;
			}
			else if(target>nums[r])//目标值比最右侧大 只能往左侧才有希望
			{
				//但是需要分析当前在哪里
				if(nums[mid]<nums[l])//在左侧区域
				{
					r=mid;
				}
				else//在右侧区域
				{
					l=mid+1;
				}
			}	
		}
	}
	 return false;
  }
```
大众方法为：

```java
public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
        	
            int mid = left + (right-left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            //前半部分有序
            if (nums[left] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {  //否则，去后半部分找
                    left = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {  //否则，去后半部分找
                    right = mid - 1;
                }
            }
        }
        //一直没找到，返回false
        return false;	    
 }
```