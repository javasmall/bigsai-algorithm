## 四数之和
描述
>给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

>答案中不可以包含重复的四元组。

示例：

>给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
>满足要求的四元组集合为：
>[
>[-1,  0, 0, 1],
>[-2, -1, 1, 2],
>[-2,  0, 0, 2]
>]

分析，前面我们做过[三数之和](https://bigsai.blog.csdn.net/article/details/108299163)。这里四数之和也采用比较相似的方法。

三数之和固定循环一层i，然后left和right从右侧向中间双指针试探
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020090121370362.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

**分析**
四个数之和可以转换为三数之和。`a+b+c+d=target` 即`a+b+c=target-d`。
在具体实现上，**先排序**，只需要i,j遍历然后用left，right双指针试探。双指针可以减少一层循环，使得最终复杂度为O(n3)；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200901213844136.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

具体实现上还需要考虑相同去重等细节：
- nums[i]==nums[i-1]时候跳过，因为该数字作过最左侧。
- nums[j]==nums[j-1]跳过，因为该数字作过第二个数字。
- nums[i]+nums[j]+nums[left]+nums[right]==target时候left要向右到和右侧不同为止，right向左到左侧不同为止(去掉相同情况)。
- **剪枝优化**，开始时候**过滤一些不可能情况直接返回结果减少计算**。

其他细节详细看代码。ac代码为：

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
		 List<List<Integer>> list = new ArrayList<List<Integer>>();
		 if(nums.length<4)return list;
		 if(nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]+nums[nums.length-4]<target)return list;	
		 for(int i=0;i<nums.length-3;i++)
		 {
			 if(i>0&&nums[i]==nums[i-1]) {continue;}//去重，这种情况不需要
			 if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target) return list;//过滤不可能满足的
			 for(int j=i+1;j<nums.length-2;j++)
			 {
				 if(j>i+1&&nums[j]==nums[j-1]) {continue;}//去重，这种情况不需要
				 if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target)break;//过滤不可能满足的
				 int left=j+1; int right=nums.length-1;
				 while (left<right) { 
					int sum=nums[i]+nums[j]+nums[left]+nums[right];
					if(nums[i]+nums[j]+nums[left]+nums[left+1]>target)break;
					if(sum==target)
					{
						while (left<nums.length-1&&nums[left]==nums[left+1]) {left++;}
						while (right>0&&nums[right]==nums[right-1]) {right--;}
						List<Integer>list2=new ArrayList<Integer>();
						list2.add(nums[i]);list2.add(nums[j]);
						list2.add(nums[left]);list2.add(nums[right]);
						list.add(list2);
					}
					if(left>right)break;
					if(sum>target)
					{
						right--;
					}
					else {
						left++;
					}					
				}
			 }
		 }
		 return list;
    }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200901214638292.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)