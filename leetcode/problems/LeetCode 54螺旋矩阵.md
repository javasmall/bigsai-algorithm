## LeetCode 54螺旋矩阵
给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:
```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
示例 2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```
**分析**
本题是顺时针返回矩阵中的所有数字，而大致有两个方法。
法一用一个坐标点进行移动维护，右下左上四个方向循环，并且用**boolean**数组标记。最后返回输出结果，当然这种方法我没有实现因为比较麻烦。


**法二数学方法**
可以把矩形最外圈分成四份，每次可以根据数学规律找到数字。
如果最短的那边是偶数没啥问题：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107181920624.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

但是如果是奇数的话需要特殊考虑最后的剩余情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107182150569.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
具体怎么处理看个人，我这里是在循环之外特殊处理的。

具体代码为：


```java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer>list=new ArrayList<Integer>();
    if(matrix==null||matrix.length==0)return list;
	int m=matrix.length;
	int n=matrix[0].length;
	int min=Math.min(m, n);
    int index;
	for( index=0;index<min/2;index++)
	{
		for(int i=index;i<n-index-1;i++)//最上面
		{
			list.add(matrix[index][i]);
		}
		for(int i=index;i<m-index-1;i++)
		{
			list.add(matrix[i][n-index-1]);
		}
		for(int i=n-index-1;i>index;i--)
		{
			list.add(matrix[m-index-1][i]);
		}
		for(int i=m-index-1;i>index;i--)
		{
			list.add(matrix[i][index]);
		}
		
	}
       if(min%2==1)
       {
           for(int i=index;i<=n-index-1;i++)//最上面
		{
			list.add(matrix[index][i]);
		}
		for(int i=index+1;i<=m-index-1;i++)
		{
			list.add(matrix[i][n-index-1]);
		}
       }
	return list;
   }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201107183205144.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)