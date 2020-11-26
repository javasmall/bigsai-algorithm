##  LeetCode 15三数之和(双指针)
**题意：**

> 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
>
> 注意：答案中不可以包含重复的三元组。


示例：

>给定数组 nums = [-1, 0, 1, 2, -1, -4]，
>满足要求的三元组集合为：
>[
>[-1, 0, 1],
>[-1, -1, 2]
>]

**分析：**
从数值的分析上，a+b+c=0一定有数字大于等于0，有数值小于等于0，如果无序，那么暴力枚举各个数O(n3)并且还需要考虑**去重**。

可以采取动静的思路，首先**对序列进行排序**，三个数字，一个在左面，一个在左面，一个在右面，三个中以其中**一个为定点**，如果以中间为临时定，左右两个分别从两边向中间指针试探，如果其中遇到相等的即加入结果集。但是这样的一个结果需要去重，因为无法判断唯一，具体实现代码为：

```java
public List<List<Integer>> threeSum(int[] nums) {
          List<List<Integer>> list=new ArrayList<List<Integer>>();
		 Set<String>set=new HashSet<String>();
		 Arrays.sort(nums);
		 
		 for(int i=1;i<nums.length-1;i++)
		 {	 
			int left=0;int right=nums.length-1;
			
			while (left<i&&right>i) {
				//System.out.println(i+" "+left+" "+i+" "+right+" "+list.toString());
				if(nums[left]>0)break;
				int sum=nums[left]+nums[i]+nums[right];
				//System.out.println(nums[left]+" "+nums[i]+" "+nums[right]);
				if(sum==0)
				{
				
					String teamString=nums[left]+" "+nums[i]+" "+nums[right];
					if(!set.contains(teamString)) {
					List<Integer>list2=new ArrayList<Integer>();
					list2.add(nums[left]);list2.add(nums[i]);list2.add(nums[right]);
					list.add(list2);
					set.add(teamString);
					}
					left++;right--;
				}
				else if (sum>0) {
					right--;
				}
				else {
					left++;
				}
			}
			 	 
		 }
		 return list;	 
    }
```

而如果**循环一次每次确定左侧**的，中间的和右侧的分别向右侧和左侧拓展，那么这样可以实现一个去重，并且时间复杂度为O(n2)但需要注意以下细节：
- 当前最左侧数字sums[i]如果和sums[i-1]（i>1）相等，那么跳出本次计算，因为当前数字如果作为最左侧数字那么前面有相同的数字可以组成这个集合，所以**不需要这部的计算**。
- 同时在确定最左侧，中间left和最右侧right向中间进行时如果三数之和大于0，那么right--；如果三数之和小于0，那么left++；同时你可以进行部分剪枝优化，把不可能的情况直接过滤掉。

ac代码为：

```java
 public List<List<Integer>> threeSum(int[] nums) {
     List<List<Integer>> list=new ArrayList<List<Integer>>();
		 Arrays.sort(nums);
		 for(int i=0;i<nums.length-2;i++)
		 {	 
			int left=i+1;int right=nums.length-1;
			if(i>0&&nums[i]==nums[i-1])continue;
			while (left<right) {
				//System.out.println(i+" "+left+" "+i+" "+right+" "+list.toString());
				if(nums[left]+nums[i]>0)break;
				int sum=nums[left]+nums[i]+nums[right];
				//System.out.println(nums[left]+" "+nums[i]+" "+nums[right]);
				if(sum==0)
				{
					List<Integer>list2=new ArrayList<Integer>();
					list2.add(nums[i]);
					while (left<right&&nums[left]==nums[left+1]) {left++;}
					list2.add(nums[left]);
					while (left<right&&nums[right]==nums[right-1]) {right--;}
					list2.add(nums[right]);
					list.add(list2);
					left++;right--;
				} 
				else if (sum>0) {
					right--;
				}
				else {
					left++;
				}
			}

	}
	return list;
 }
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)