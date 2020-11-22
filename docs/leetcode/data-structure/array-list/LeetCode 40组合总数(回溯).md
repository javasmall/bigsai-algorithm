## LeetCode 40组合总数(回溯)
**题目描述：**
>给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

>andidates 中的每个数字在每个组合中只能使用一次。

说明：

>所有数字（包括目标数）都是正整数。
>解集不能包含重复的组合。 
```
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```
分析，这题和[组合总数Ⅰ有所不同](https://bigsai.blog.csdn.net/article/details/109014600)，上一题是任意一个数可以出现任意多次并且形式上没有重复的。

但是这题**有重复的但是每个数只能使用一次**。如果按照上一题的分析思路就是就是回溯的过程需要进行下一个，**并且需要一个哈希对结果去重。**

但是我们都知道哈希虽然效率还行但是频繁的判断很影响算法的效率，有什么好的方法去解决？我们在回溯算法上进行合理剪枝。**用一个策略去重**。

首先对于这个序列，我们先进行排序，排完序之后我们进行一下分析：

怕重复？无非是怕以下的这种情况嘛：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017174843441.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
而我们在实际处理的时候，同一个元素如果用多次，只能从第一个元素顺序使用而不能跳跃使用。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017183120433.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

在具体的处理方式我，刚开始我用了比较笨的方法：**每次找到这个元素找到它相同的区间**。然后遍历这个区间排列所有个数(从左向右)，同时判断满足条件(数值总和不超出的情况下)，递归下一个节点到下一个数值的编号上。当然不使用该元素在最后特殊进行一次dfs即可。

```java
List<Integer> list = new ArrayList<Integer>();// list用来回溯过程中储存元素
List<List<Integer>> val = new ArrayList<List<Integer>>();// 结果

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	Arrays.sort(candidates);
	dfs(0, candidates, target);
	return val;
}

/*
 * index 表示当前编号 count 表示当前数值的和
 */
private void dfs(int index, int[] candidates, int target) {
	// System.out.println(list.toString());
	if (target == 0) {
		val.add((new ArrayList<Integer>(list)));
		return;
	} else if (index == candidates.length) {
		return;
	} else {
		int r = index;
		while (r < candidates.length && candidates[index] == candidates[r]) {
			r++;
		}
		int i;
		for (i = index; i < r; i++) {
			target -= candidates[i];
			list.add(candidates[i]);
			if (target>=0) {
				dfs(r, candidates, target);

			} else {//没有执行dfs但是数值需要恢复 因为i还是变了
				target += candidates[index];
				list.remove(list.size() - 1);
				break;
			}
		}
		for (; i > index; i--) {
			target += candidates[index];
			list.remove(list.size() - 1);
		}
		dfs(r,  candidates, target);// 不使用该元素的
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017183946852.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
算法 4ms不太行，因为提前计算出右r的位置的时候进行一部分重复量挺大的运算。参考了别人的代码，才发现具体处理上可以这么处理：

遍历时候正常遍历，只不过进行遍历的时候判断**如果该元素不是这次首元素的话和前面元素相等那么直接跳过**。否则正常进行dfs。这样就可以过滤掉所有重复情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017185153943.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
最终ac的代码为：

```java
List<Integer> list = new ArrayList<Integer>();// list用来回溯过程中储存元素
List<List<Integer>> val = new ArrayList<List<Integer>>();// 结果

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	Arrays.sort(candidates);
	dfs(0, candidates, target);
	return val;
}

/*
 * index 表示当前编号 count 表示当前数值的和
 */
private void dfs(int index, int[] candidates, int target) {

	if (target == 0) {
		val.add((new ArrayList<Integer>(list)));
		return;
	}
	for(int i=index;i<candidates.length;i++)
	{
		if(target-candidates[i]<0)break;
		if(i!=index&&candidates[i]==candidates[i-1])continue;
		list.add(candidates[i]);
		dfs(i+1, candidates, target-candidates[i]);
		list.remove(list.size()-1);
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101718583382.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)