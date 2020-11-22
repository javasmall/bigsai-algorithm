## LeetCode 37解数独
**题目描述：**

编写一个程序，通过填充空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201010164800465.png)
一个数独。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101016481899.png)


答案被标成红色。

提示：

>给定的数独序列只包含数字 1-9 和字符 '.' 。
>你可以假设给定的数独只有唯一解。
>给定数独永远是 9x9 形式的。


**分析**
此题相比上一题难度稍微大一些，是一个**八皇后问题**的变种，对于上一题我想我们已经知道怎么判断这个是否满足规则——**使用三个boolean数组判断。**

但是，这一题有难度的就是需要我们手动放数据进去，手动去试探，**并且每一步放置之后对后面都有影响**。我们该如何思考这种问题呢？

dfs回溯，八皇后问题其实就是典型的回溯过程，而这种二维的回溯需要考虑一些问题，**我们对于每一行每一行考虑。** 每一行已经预有一些数据事先标记，在从开始试探放值，满足条件后向下递归试探。一直到结束如果都满足那么就可以结束返回数组值。

对于八皇后问题后面还会出一篇细讲，这里的话有两点需要注意的在这里提一下：
- 用二维两个参数进行递归回溯判断起来谁加谁减比较麻烦，所以我们用一个参数`index`用它来计算横纵坐标进行转换，这样就减少二维递归的一些麻烦。
- 回溯是一个来回的过程，在回来的过程正常情况需要将数据改回去，但是如果已经知道结果就没必要再该回去可以直接停止放置回溯造成值的修改(这里我用了一个isfinish的boolean类型进行判断)。

具体ac代码为：

```java
boolean isfinish=false;
boolean hang[][]=new boolean[9][10];//第一个是 9个坑，第二个是数字位置
boolean lie[][]=new boolean[9][10];
boolean fangge[][]=new boolean[9][10];
public  void solveSudoku(char[][] board) {
	 //首先遍历一遍 将已有元素的行列信息提前做处理
	 for(int i=0;i<board.length;i++)
	 {
		 for(int j=0;j<board[0].length;j++)
		 {
			 if(board[i][j]=='.') {continue;}
			 int k=board[i][j]-'0';		
			 hang[i][k]=true;
			 lie[j][k]=true;
			 fangge[(i/3)*3+j/3][k]=true;
		 }
	 }	
	 dfs(0,board);	 
   }
private void dfs( int index, char[][] board) {
	if(isfinish) return;//已有结果不需要再计算
	if(index==81) {//到达最后一个前面都满足条件说明可以停止了
		isfinish=true;
		return;
	}
	int i=index/9;//行
	int j=index%9;//列
	if(board[i][j]!='.')//已经有数字
	{
		dfs( index+1, board);
	}
	else {//此处需要补充数字
		for(int k=1;k<10;k++)
		{	
			 //如果不满足直接跳过
			 if(hang[i][k]||lie[j][k]||fangge[(i/3)*3+j/3][k]) {continue;}
			 //满足临时试探修改 进行回溯
			 board[i][j]=(char) (k+'0'); 
			 hang[i][k]=true;
				 lie[j][k]=true;
			 fangge[(i/3)*3+j/3][k]=true;
			 //递归回溯
			 dfs( index+1, board);
			 //递归完成后需要复原，如果结束了不需要复原直接停止
			 if(isfinish)return;
			 board[i][j]='.'; 
			 hang[i][k]=false;
			 lie[j][k]=false;
			 fangge[(i/3)*3+j/3][k]=false;
		}
	}
}
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201010171943181.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

