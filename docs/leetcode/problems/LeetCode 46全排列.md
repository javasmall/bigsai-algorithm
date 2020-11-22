## LeetCode 46全排列


 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201025111213633.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



全排列的两种实现方式这里就不累述，大家可以参考：[全排列两种实现方式](https://blog.csdn.net/qq_40693171/article/details/90299747)


实现方式为：

```java
 public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>>list=new ArrayList<List<Integer>>();
		arrange(nums,0,nums.length-1,list);
		return list;
	 }

	private void arrange(int[] nums, int start, int end, List<List<Integer>> list) {
		  if(start==end)
		  {
			  List<Integer>list2=new ArrayList<Integer>();
			  for(int a:nums)
			  {
				  list2.add(a);
			  }
			  list.add(list2);
		  }
		  for(int i=start;i<=end;i++)
		  {
			  swap(nums,i,start);
			  arrange(nums, start+1, end, list);
			  swap(nums, i, start);
		  }
		
	}

	private void swap(int[] nums, int i, int j) {
		int team=nums[i];
		nums[i]=nums[j];
		nums[j]=team;
	}
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201025111518942.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

本次就到这里啦，**感觉不错记得点赞或一键三连哦**，个人公众号：`bigsai` 回复 **bigsai** 更多精彩和资源与你分享。


