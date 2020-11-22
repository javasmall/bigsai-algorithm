## 二分查找

二分查找我想大家都很熟悉，二分查找每次判断并比较元素所在区间进行压缩，每次都可以压缩一半的区间，所以压到1个大小把它你想来看就是(最坏)扩散了n次到达原始长度。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927180142604.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

很多题就是原始的二分，但很多题就是二分变种。

## 33搜索旋转排序数组
![image-20201117214235872](/Users/a1/Library/Application Support/typora-user-images/image-20201117214235872.png)

这题其实就是一个二分变种，加了一些其他的条件。每次的mid要根据判断如何移动.一个正常序列分成左右两个序列，并且都是递增的，没有相同的。

就拿中间**mid的值大于target**就有以下几种情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927182457127.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
按照这样思路同理分析另一半一直求解即可。

ac代码为：

```java
 public int search(int[] nums, int target) {
       if(nums[0]==target)return 0;
	 if(nums[nums.length-1]==target)return nums.length-1;
	  int l=0;int r=nums.length-1;
	  while (l<r) {
		int mid=(l+r)/2;
		//System.out.println(mid+" "+l+" "+r);
		if(nums[mid]==target)return mid;
		//  8 9 2 3 4 5 6 7 
		if(nums[mid]>target)//中间大于目标值
		{
			if(nums[0]>target) {//最左侧都大于 只可能在右侧最小区域
				
				if(nums[mid]<nums[0])//当前在右区域
				{
					r=mid;
				}
				else {
					l=mid+1;	
				}
			}
			else {////最左侧小于目标值  向左
				r=mid;
			}
			
		}
		// 8 9 2 3 4 5 6 7 
		else {//中间小于目标值
			//如果在右侧区域往左
			if(nums[nums.length-1]<target)//最右侧小于target  需要向左侧去
			{
				if(nums[mid]<nums[nums.length-1])//当前
				{
					r=mid;
				}
				else {
					l=mid+1;
				}
				
			}
			else //最右侧大于target 在小的区域内
			{
				l=mid+1;
			}
			//System.out.println(1);
			
		}
	  }
	  
	  return -1;
   }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200927182630422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)