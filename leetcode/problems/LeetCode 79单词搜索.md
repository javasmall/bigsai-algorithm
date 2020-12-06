## LeetCode 79单词搜索
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:
```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
```

提示：
```
board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
```
**分析**
经典迷宫搜索题，这里我用dfs实现，对于迷宫搜索题需要注意以下几点：
- 定义四个方向数组，用来坐标模拟行走
- 走过的坐标需要临时标记(回溯)，回来的时候在清除标记
- 注意起始和结尾情况，尽量避免使用String频繁创建新的对象

而本题在搜索途中判断当前字符没有被使用且和目标位置字符相等即可模拟遍历。有一个注意的是一旦找到答案则不需要再进行搜索(需要停止)。

具体代码为：

```java
int x[]= {-1,0,1,0};
int y[]= {0,-1,0,1};
boolean isvalue=false;
boolean jud[][];
public boolean exist(char[][] board, String word) {
	jud=new boolean[board.length][board[0].length];
	if(word.length()>board.length*board[0].length)return false;
	char words[]=word.toCharArray();
	for(int i=0;i<board.length;i++)
	{
		for(int j=0;j<board[i].length;j++)
		{
			jud[i][j]=true;
			if(words[0]==board[i][j])
			  dfs(board, 1, words, i, j);
			jud[i][j]=false;
			if(isvalue)return true;
		}
	}	
	return false;
   }
private void dfs(char[][] board,int index, char words[],int x1,int y1) {
	// TODO Auto-generated method stub
	if(isvalue)return;
	if(index==words.length)
	{
		isvalue=true;
	}
	else 
	{
		for(int i=0;i<4;i++)
		{
			int xnew=x1+x[i],ynew=y1+y[i];
			if(xnew>=0&&xnew<board.length&&ynew>=0&&ynew<board[0].length)
			{
				if(!jud[xnew][ynew]&&words[index]==board[xnew][ynew])
				{
					jud[xnew][ynew]=true;
					dfs(board, index+1, words, xnew, ynew);
					jud[xnew][ynew]=false;
				}
			}
		}
	}
}
```