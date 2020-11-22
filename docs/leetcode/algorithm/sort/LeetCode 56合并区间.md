## LeetCode 56合并区间
 合并区间
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

>输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
>输出: [[1,6],[8,10],[15,18]]
>解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:
>输入: intervals = [[1,4],[4,5]]
>输出: [[1,5]]
>解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

**分析**

 思路都是一个思路，先把数组进行排序(根据左侧数据)，排完序的数组遍历从左往右记录left，right。
 - left，right初始为`left=intervals[0][0],right=intervals[0][1]`。
 - 遍历的时候如果当前元素`intervals[index][0]<right` 那么将left，right添加到结果集中，重新赋值left，right初始为当前元素值。
 - 如果`intervals[index][0]>right`条件下，如果 `intervals[index][1]>right`那么更新right为`intervals[index][1]`继续，否则继续向下。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108154014270.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

在代码实现上提供两个版本，一个使用List的，一个用数组复用空间，使用List：

```java
public int[][] merge(int[][] intervals) {
	 if(intervals.length==0)return new int[0][0];
	 Arrays.sort(intervals,new Comparator<int []>() {//排序
		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[0]-o2[0];
		}
	});
	 List<Integer>list=new ArrayList<Integer>();
	 
	 int left=intervals[0][0],right=intervals[0][1];
	 for(int i=1;i<intervals.length;i++)
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
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108113457720.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
而复用数组实现思路如下：

```java
 public int[][] merge(int[][] intervals) {
	 if(intervals.length==0)return new int[0][0];
	 Arrays.sort(intervals,new Comparator<int []>() {//排序
		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[0]-o2[0];
		}
	});
	
	 int index=0;
	 int left=intervals[0][0],right=intervals[0][1];
	 for(int i=1;i<intervals.length;i++)
	 {
		 if(intervals[i][0]<=right)
		 {
			 if(intervals[i][1]>right)
				 right=intervals[i][1];
		 }
		 else {
			 intervals[index][0]=left;
			 intervals[index][1]=right;
			index++;
			 left=intervals[i][0];
			 right=intervals[i][1];
		}
	 }
	 intervals[index][0]=left;
	 intervals[index][1]=right;
	
	 return Arrays.copyOf(intervals, index+1);
}
```
时间差不多6ms。

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)