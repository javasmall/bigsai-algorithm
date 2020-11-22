## LeetCode 22括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例：
>输入：n = 3
>输出：[
> "((()))",
> "(()())",
> "(())()",
> "()(())",
> "()()()"
>]

**分析**
这一题刚拿到手可能没啥思路解决，不知道采用什么数学方法，但是你要看到**所有可能的括号组合**。所有二字，摆明是搜索题，可以dfs或者bfs，这里采用dfs完成。

dfs搜索需要考虑遍历所有情况，还有就是初始和停止的条件，我们知道dfs是回溯的，如果字符串频繁创建删除效率很低，所以利用回溯回来过程将字符串还原(StringBuilder)这样可以大大提升效率。而具体需要这样思考：
- n对括号，说明有n个`(`和n个`)`，可用两个数字标记两个个数。
- `(`个数不能小于`)`的个数，否则不满足有效括号。
- 如果`(`用完，那么只能添加`)`，如果`(`未用完且不满足小于`)`个数，那么当前既可添加`(`也可添加`)`。
- 注意作用域，参数等问题

具体实现代码为：

```java
 List<String>list;
	public List<String> generateParenthesis(int n) {
		list=new ArrayList<String>();
		StringBuilder sBuilder=new StringBuilder();
		dfs(sBuilder,0,0,n);
		return list;	

    }
	private void dfs(StringBuilder sBuilder, int i, int j,int n) {
		if(j==n) {list.add(sBuilder.toString());return;}
		if(i<j)return;
		if(i<n)
		{
			sBuilder.append('(');
			dfs(sBuilder, i+1, j, n);
			sBuilder.deleteCharAt(i+j);
		}
		if(i>j)
		{
			sBuilder.append(')');
			dfs(sBuilder, i, j+1, n);
			sBuilder.deleteCharAt(i+j);
		}		
	}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020090616420760.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)



## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)