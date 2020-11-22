## LeetCode 34在排序数组中查找元素的第一个和最后一个位置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927182748701.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
入门二分，注意找到一个值后进行左右方向寻找边界问题。ac代码为：

```java
 public int[] searchRange(int[] nums, int target) {
           int a[]= {-1,-1};
		 if(nums.length==1&&nums[0]==target) {a[0]=0;a[1]=0;return a;}
		 if(nums.length==0)return a;
		 int leftindex,rightindex;
		 int left=0,right=nums.length-1;
		 while (left<right) {
			 //System.out.println(left+" "+right);
			int mid=(left+right)/2;
			if(nums[mid]==target)
			{
				leftindex=mid;
				rightindex=mid;
				while (leftindex>=0&&nums[leftindex]==target) {leftindex--;}
				while (rightindex<nums.length&&nums[rightindex]==target) {rightindex++;}
				a[0]=leftindex+1;a[1]=rightindex-1;
				return a;
			}
			else if (nums[mid]<target) {
				left=mid+1;
			}
			else {
				right=mid;
			}
		}	
		 if(nums[left]==target)
		 {
			 a[0]=left;a[1]=left;
		 }
		 return a;
        }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927182903953.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)