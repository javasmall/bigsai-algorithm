## LeetCode 39组合总和
题目
>给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
>candidates 中的数字可以无限制重复被选取。

说明：
>所有数字（包括 target）都是正整数。
>解集不能包含重复的组合。 
```
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 
```
提示：
>1 <= candidates.length <= 30
>1 <= candidates[i] <= 200
>candidate 中的每个元素都是独一无二的。
>1 <= target <= 500

分析，这题的话首先搞懂题意：
- 数组元素不相等
- 数组中任意凑使得数据等于target
- 每个数值可以用任意多次
- 最终的序列不能重复，需要**去重**或者**采取合理措施**。


对于这种需要不断试探列举的题，肯定是**回溯算法**没错了，回溯也是经典dfs算法的一种，在本题的回溯过程中，	需要用一个List储存数值，在回溯向下的过程就加入合法元素，回来之后要把对应元素删除复原。如果当前列表中数值总和和target相等，那么需要将它添加到结果集中。

对于回溯的过程我们应该如何考虑呢，初试状态从0开始，下一次从何开始呢？从当前index开始。因为最终序列不能有重复，所以**下一次回溯递归需要从它当前编号开始不能从0开始**。

比如 `2 3 8` 组成8，从2 开始有`2 2 2 2`，`2 3 3`,从3开始如果`3 2 3`或者`3 3 2`其实都已经重复了，换言之2这个元素作为元素首位已经被使用过，其他在它后面的元素再回溯的时候不需要再经过它，所以从3开始正确应该是`3 3 3`试探失败`3 8`试探失败而回去。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201011193923299.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
思路搞明白之后，直接giao代码就可以了，附上代码：

```java
List<Integer>list=new ArrayList<Integer>();//list用来回溯过程中储存元素
List<List<Integer>> val=new ArrayList<List<Integer>>();//结果
public List<List<Integer>> combinationSum(int[] candidates, int target) {
 dfs(0,0,candidates,target);
 return val;
}

/*
 * index 表示当前编号  count 表示当前数值的和
 */
private void dfs(int index,int count, int[] candidates, int target) {
if(target==count) {
	List<Integer>vaList=new ArrayList<Integer>();
	vaList.addAll(list);
	val.add(vaList);
}
for(int i=index;i<candidates.length;i++)
{
	if(count+candidates[i]<=target)
	{
		list.add(candidates[i]);
		dfs(i, count+candidates[i], candidates, target);
		list.remove(list.size()-1);
	}
}
}
```
一发AC还是可以的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201011194622404.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


好了，本周的打卡介绍了，感觉不错欢迎一键三联,有其他方法还请留言，如果有兴趣加入打卡欢迎加笔者微信`q1315426911`拉你进群.

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201011195023346.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)