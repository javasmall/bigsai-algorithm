## LeetCode 57插入区间

给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。


示例 1：

>输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
>输出：[[1,5],[6,9]]

示例 2：
>输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
>输出：[[1,2],[3,10],[12,16]]
>解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。

和上一题的思想差不多，先排序是肯定的。只不过这里有一点变化：给一个新的数组区间插进来然后再进行合并，在处理上你可以每次遍历的时候和我们数组进行比较，但是那无疑是一个比较差的方法。**所以在这里我先用二分定位到添加的那个区间在原数组中的位置，然后分两次遍历进行操作就可以啦**。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108154425668.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


具体代码为：

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
	if(intervals.length==0)
	{
		int val[][]= {{newInterval[0],newInterval[1]}};
		return val;
	}
	List<Integer>list=new ArrayList<Integer>();
	//二分找到位置
	int l=0,r=intervals.length-1;
	int index=0;
	while (l<r) {
		int mid=(l+r)/2;
		if(intervals[mid][0]==newInterval[0])
		{
			l=mid+1;r=mid-1;
		}
		else if(intervals[mid][0]<newInterval[0])
		{
			l=mid+1;
		}
		else {
			r=mid-1;
		}
	}
	index=l;
	int left=intervals[0][0],right=intervals[0][1];
	if(index==0) {
		left=newInterval[0];right=newInterval[1];
	}
	for(int i=0;i<index;i++)
	{
		if(intervals[i][0]<=right)
		 {
			 if(intervals[i][1]>right)
				 right=intervals[i][1];
		 }
		 else {
			list.add(left);
			list.add(right);
			left=intervals[i][0];
			right=intervals[i][1];
		}
	}
	if(newInterval[0]<=right)
	 {
		 if(newInterval[1]>right)
			 right=newInterval[1];
	 }
	 else {
		list.add(left);
		list.add(right);
		left=newInterval[0];
		right=newInterval[1];
	}
	for(int i=index;i<intervals.length;i++)
	{
		if(intervals[i][0]<=right)
		 {
			 if(intervals[i][1]>right)
				 right=intervals[i][1];
		 }
		 else {
			list.add(left);
			list.add(right);
			left=intervals[i][0];
			right=intervals[i][1];
		}
	}
	list.add(left);
	list.add(right);
	 int val[][]=new int [list.size()/2][2];
	 for(int i=0;i<list.size();i+=2)
	 {
		 val[i/2][0]=list.get(i);
		 val[i/2][1]=list.get(i+1);
	 }
	 return val;
   }
```

欢迎关注公众号：`bigsai`,一起打卡力扣。还给大家准备一些干货，一起进步！
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108160344662.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)