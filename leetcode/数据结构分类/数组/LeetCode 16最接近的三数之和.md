## LeetCode 16最接近的三数之和
![image-20201117000432306](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117000432306.png)

**分析:**
有了上题的思路，本题**依然使用双指针**，不过最接近sum需要你用数字记录它的大小并且还需要考虑正负的问题。

具体的处理上，和上一题整体思想一致，首先排序，**循环一次确定最左侧的**,这层为O(n),同时每个最左侧确定之和中间和右侧分别向中间靠拢，如果三数之和大于target，那么right--，否则left++，同时要和最接近的数进行比较是否更接近，这样执行完毕即可获得最接近target的三个数字之和。

具体实现代码为：

```java
 public static int threeSumClosest(int[] nums, int target) {	
	     Arrays.sort(nums);
	     int value=nums[0]+nums[1]+nums[2];
		 for(int i=0;i<nums.length-2;i++)
		 {	 
			int left=i+1;int right=nums.length-1;
			
			while (left<right) {
				int sum=nums[left]+nums[i]+nums[right];
				int gap1=Math.abs(value-target);
				int gap2=Math.abs(sum-target);
				 if(target>=0&&nums[left]+nums[i]-target>gap1)break;
				if(gap2<gap1)
				{
					value=sum;
				} 
			    if (sum==target) {
					return target;
				}
				else if(sum>target) {
					right--;
				}
			    else {
					left++;
				}  
			}
	    }
		 return value;
	 }
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)