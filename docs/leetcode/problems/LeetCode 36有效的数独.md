## LeetCode 36有效的数独

![image-20201117214712667](/Users/a1/Library/Application Support/typora-user-images/image-20201117214712667.png)

**分析**
本题的话就是要选择合理的方式去判断是否有效。是否有效需要满足三个维度：
- 行是否有效
- 列是否有效
- 3x3的格子内是否有效。

而每一个维度考虑的问题都是有相关性的，例如这一行的1-9每个数字只能出现一次，这一列也是，这个3x3也是。这样的话我们就可以通过三个boolean数组来判断各自对应的区间是否满足。**因为每个区间每个数字最多只能出现一次，所以判断所有次数满足条件即可，一旦不满足就停止返回false。**

当然这个有点麻烦的就是需要求当前是在第几个3x3的小方格内，经过思考`(i/3)*3+j/3`这个表达式可以很好的表示。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201010140225405.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)实现代码为：

```java
public boolean isValidSudoku(char[][] board) {
     boolean hang[][]=new boolean[9][9];//第一个是 9个坑，第二个是数字位置
	 boolean lie[][]=new boolean[9][9];
	 boolean fangge[][]=new boolean[9][9];
	 for(int i=0;i<board.length;i++)
	 {
		 for(int j=0;j<board[0].length;j++)
		 {
			 if(board[i][j]=='.') {continue;}
			 int num=board[i][j]-'1';
			 if(hang[i][num]||lie[j][num]||fangge[(i/3)*3+j/3][num])
			 {
				 return false;
			 }
			 else {
				 hang[i][num]=true;
				 lie[j][num]=true;
				 fangge[(i/3)*3+j/3][num]=true;
			}
		 }
	 }
	 return true;
   }
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201010140412686.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)