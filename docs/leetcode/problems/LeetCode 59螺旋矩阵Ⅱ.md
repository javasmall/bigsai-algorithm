## LeetCode 59螺旋矩阵Ⅱ
给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

示例:

>输入: 3
>输出:
>[
> [ 1, 2, 3 ],
> [ 8, 9, 4 ],
> [ 7, 6, 5 ]
>]

按照数学方法遍历即可，遍历同时维护一个常数自增赋值，考虑奇数偶数即可。
最小边为偶数情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114115841670.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
最小边长为奇数需要特殊考虑下。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114115435142.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
具体代码为：

```java
public int[][] generateMatrix(int n) {
    int val[][]=new int [n][n];
	int value=1;
	for(int index=0;index<n/2;index++)
	{
		for(int i=index;i<n-index-1;i++)
		{
			val[index][i]=value++;
		}
		for(int i=index;i<n-index-1;i++)
		{
			val[i][n-index-1]=value++;
		}
		for(int i=n-index-1;i>index;i--)
		{
			val[n-index-1][i]=value++;
		}
		for(int i=n-index-1;i>index;i--)
		{
			val[i][index]=value++;
		}
	}
	if(n%2==1)
		val[n/2][n/2]=value;
	return val;
   }
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)