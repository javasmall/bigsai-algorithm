## LeetCode 47全排列Ⅱ

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

>输入: [1,1,2]
>输出:
>[
>[1,1,2],
>[1,2,1],
>[2,1,1]
>]


**法一 哈希**
这题相比之前的就是有重复的情况，最笨的方法就是用哈希将各种序列存到Set中最后返回，但是这也是一种方法和策略：

```java
 Set<String>set=new HashSet<String>();
	public List<List<Integer>> permuteUnique(int[] nums) {
		 List<List<Integer>> list=new ArrayList<List<Integer>>();
		 Arrays.sort(nums);
		 arrange(nums, 0, nums.length-1, list);
		 return list;
	 }
	
	private void arrange(int[] nums, int start, int end, List<List<Integer>> list) {
		  if(start==end)
		  {
			  List<Integer>list2=new ArrayList<Integer>();
			  StringBuilder sBuilder=new StringBuilder();
			  for(int a:nums)
			  {
				  sBuilder.append(a);
				  list2.add(a);
			  }
			  if(!set.contains(sBuilder.toString()))
			  {
				  list.add(list2);  
				  set.add(sBuilder.toString());
			  }
			  
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

但是效果比较差：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201031171219836.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


**法二 递归剪枝**

第一个是真的很慢，但是有什么可以优化的方案吗？答案当然是有的，我们在执行递归全排列的时候，主要考的是要把重复的情况搞下去，怎么思考这个问题呢？

我们在进行交换swap的时候从前往后，前面的确定之后就不会在动，所以我们要**慎重考虑和谁交换**。剩下所有数默认不重复会排列所有情况，所以`1 2 3`和`3 2 1 `在这个问题上等价，但是`1 1 2 3`第一个数**有三种情况而不是四种情况**：
>1 1 2 3
>2 1 1 3
>3 1 2 1

另外比如`3 1 1`,3和自己交换，和后面两个1只能和其中一个进行交换，我们这里可以约定和第一个出现的进行交换，我们看一个图解部分过程：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020103119595885.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


所以，当我们从一个index开始的时候要记住以下的规则：`同一个数只交换一次`(包括自己的数)。
在判断的时候，你可以遍历，也可以使用hashSet().



具体实现的代码为：

```java

public List<List<Integer>> permuteUnique(int[] nums) {
		 List<List<Integer>> list=new ArrayList<List<Integer>>();
		 arrange(nums, 0, nums.length-1, list);
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
	  Set<Integer>set=new HashSet<Integer>();	  
	  for(int i=start;i<=end;i++)
	  {
		  if(set.contains(nums[i]))
			  continue;
             set.add(nums[i]);			 
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
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020103118542873.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)

**法三 回溯剪枝**
我是压根没考虑用回溯，因为回溯完整的比直接递归慢，但是这里用回溯剪枝比较好，剪枝条的规则如下：
- 先对序列进行排序
- 试探性将数据放到当前位置
  - 如果当前位置数字已经被使用，那么不可使用
  - **如果当前数字和前一个相等但是前一个没有被使用，那么前当前不能使用，需要使用前一个数字。**

思路很简单，实现起来也很简单：

```java
List<List<Integer>> list;
public List<List<Integer>> permuteUnique(int[] nums) {
	list=new ArrayList<List<Integer>>();
	List<Integer> team=new ArrayList<Integer>();
	boolean jud[]=new boolean[nums.length];
	Arrays.sort(nums);
	dfs(jud, nums, team, 0);
	return list;
}
private  void dfs(boolean[] jud, int[] nums, List<Integer> team, int index) {
	// TODO Auto-generated method stub
	int len = nums.length;
	if (index == len)// 停止
	{
		list.add(new ArrayList<Integer>(team));
	} else
		for (int i = 0; i < len; i++) {
		    if (jud[i]||(i>0&&nums[i]==nums[i-1]&&!jud[i-1])) //当前数字被用过 或者前一个相等的还没用，当前即不可用
		    	continue;
			team.add(nums[i]);
			jud[i]=true;
			dfs(jud, nums, team, index + 1);
		    jud[i] = false;// 还原
		    team.remove(index);
		}
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201031204856233.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)