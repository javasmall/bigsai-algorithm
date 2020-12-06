## LeetCode 78子集
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:
```
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

分析，和上面的思想差不多，不能重复的处理方式是一致的，但是元素个数的话就是在每次回溯的时候都要添加到结果集中。ps：List频繁插入删除效率并不高我使用boolean数组进行优化。

具体代码为

```java
public List<List<Integer>> subsets(int[] nums) {
	List<List<Integer>> valueList=new ArrayList<List<Integer>>();
	boolean jud[]=new boolean[nums.length];
	List<Integer>team=new ArrayList<Integer>();
	dfs(nums,-1,valueList,jud);
	return valueList;
   }
private void dfs(int[] num,int index,List<List<Integer>> valueList,boolean jud[]) {
	{
		List<Integer>list=new ArrayList<Integer>();
		for(int i=0;i<num.length;i++)
		{
			if (jud[i]) {
				list.add(num[i]);
			}
		}
		valueList.add(list);
	}
	{
		for(int i=index+1;i<num.length;i++)
		{
			jud[i]=true;
			dfs(num, i, valueList,jud);
			jud[i]=false;
		
		}
	}
}
```