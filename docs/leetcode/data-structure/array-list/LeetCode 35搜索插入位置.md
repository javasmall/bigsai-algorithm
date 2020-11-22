## LeetCode 35搜索插入位置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927182942404.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
这题需要注意的就是插入位置或者查找到的编号。经典二分不多说你懂的/

```java
 public int searchInsert(int[] nums, int target) {
            if(nums[0]>=target)return 0;//剪枝
			if(nums[nums.length-1]==target)return nums.length-1;//剪枝
			if(nums[nums.length-1]<target)return nums.length;
			int left=0,right=nums.length-1;
			while (left<right) {
				int mid=(left+right)/2;
				if(nums[mid]==target)
					return mid;
				else if (nums[mid]>target) {
					right=mid;
				}
				else {
					left=mid+1;
				}
			}
			return left;
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927183125115.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

本次打卡结束拉，下周国庆暂停一次(就一次)。欢迎其他小哥哥小姐姐加入打卡，微信搜索bigsai，回复进群加入打卡力扣！
