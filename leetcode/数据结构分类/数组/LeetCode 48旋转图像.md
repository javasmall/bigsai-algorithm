## LeetCode 48旋转图像
![image-20201118214849351](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201118214849351.png)

**分析：**

这题思路可能比较清晰，但实现起来磕磕绊绊的地方可能比较多。每个人按照自己的思维找到一个好的方法就可以了，我把我自己的方法分享一下：

首先要看到重要信息：
- n*n的矩阵
- 不可创建新的矩阵
- 顺时针旋转

旋转其实就是一个中心对称问题找点的问题，我们可以将这个矩阵分为任意四部分，其中一个部分某个点作为起始点开始交换操作。明显看的出来是左开右闭的操作。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201031201502154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


在具体确定好(i,j)后，找到对应其他三个点的坐标。坐标其实也是很有规律的，需要自己相通就行，这里就不带大家推导了，点和对面的点中点是矩阵中心，可以进行验算。

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/2020103120160354.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

实现代码为：

```java
public void rotate(int[][] matrix) {
	 int len=matrix.length;
	 for(int i=0;i<len/2;i++)//第i行
	 {
		 int team=0;
		 for(int j=i;j<len-i-1;j++)//每个数进行四次操作
		 {
			 int x1=i,y1=j;
			 int x2=j,y2=len-i-1;
			 int x3=len-i-1,y3=len-j-1;
			 int x4=len-j-1,y4=i;
			 team=matrix[x1][y1];
			 matrix[x1][y1]=matrix[x4][y4];
			 matrix[x4][y4]=matrix[x3][y3];
			 matrix[x3][y3]=matrix[x2][y2];
			 matrix[x2][y2]=team;
		 }
	 }
 }
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201031193516197.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

结语：好了今天就到这里了，欢迎关注原创技术公众号：【**bigsai**】，回复**进群**加笔者微信一起加入打卡！

