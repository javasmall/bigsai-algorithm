## LeetCode 77组合

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:
```
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```
所有可能的k个数，经典回溯算法，要考虑两点：
- k个数，可以通过一个数字类型来记录回溯到当前层的数字数量。
- 重复，避免 ab和ba的情况，对于重复的情况，可以借助一个**下标**只遍历下标后面的数字，就可以避免重复。

至于回溯算法的具体设计，老生常谈的问题这里就不作过多的复述啦。实现代码为：

```java
public List<List<Integer>> combine(int n, int k) {
	List<List<Integer>> valueList=new ArrayList<List<Integer>>();
	int num[]=new int[n];
	boolean jud[]=new boolean[n];
	for(int i=0;i<n;i++)
	{
		num[i]=i+1;
	}

	List<Integer>team=new ArrayList<Integer>();
	dfs(num,-1,k,valueList,jud,n);
	return valueList;
   }
private void dfs(int[] num,int index, int count,List<List<Integer>> valueList,boolean jud[],int n) {
	if(count==0)
	{
		List<Integer>list=new ArrayList<Integer>();
		for(int i=0;i<n;i++)
		{
			if (jud[i]) {
				list.add(i+1);
			}
		}
		valueList.add(list);
	}
	else {
		for(int i=index+1;i<n;i++)
		{
			jud[i]=true;
			dfs(num, i, count-1, valueList,jud,n);
			jud[i]=false;
		
		}
	}
	
}
```