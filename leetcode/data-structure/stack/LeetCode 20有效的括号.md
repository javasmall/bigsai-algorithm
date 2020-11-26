## LeetCode 20有效的括号

![image-20201117115935343](/Users/a1/Library/Application Support/typora-user-images/image-20201117115935343.png)

**分析：**
括号类的问题是经典栈类问题，肯定要想到用栈处理。判断一个字符串满不满足一个有效的字符串，就要看它是不是都能组成对。
- 从单个对来说，`((`,`))`都是不满足的，只有`()`才可满足，即一左一右。
- 从多个对来说`{[(`字符串还可接受任意无限`(`，`[`,`{`的括号。但是只能接收`)`的向左的括号。

 从上面可以看作一种相消除的思想。例如`(({[()()]}))`字符串遍历时候可以这样处理：
 - `(({[(`下一个`)`消掉成`(({[`
 - `(({[(`下一个`)`消掉成`(({[`
 - `(({[`下一个`]`消掉成`(({`
 - `(({`下一个`}`消掉成`((`
 - `((`下一个`)`消掉成`(`
 - `(`下一个`)`消掉成`` 这样就满足题意

所以这个过程利用栈判断当前是加入栈还是消除顶部，到最后如果栈为空说明满足，否则不满足，当然具体括号要对应，具体实现代码为：

```java
 public boolean isValid(String s) {
		 Stack<Character>stack=new Stack<Character>();
		 for(int i=0;i<s.length();i++)
		 {	
			 char te=s.charAt(i);
			 if(te==']')
			 {
				 if(!stack.isEmpty()&&stack.pop()=='[')
					 continue;
				 else {
					return false;
				}
			 }
			 else if(te=='}')
			 {
				 if(!stack.isEmpty()&&stack.pop()=='{')
					 continue;
				 else {
					return false;
				}
			 }
			 else if(te==')')
			 {
				 if(!stack.isEmpty()&&stack.pop()=='(')
					 continue;
				 else {
					return false;
				 }
			 }
			 else
				 stack.push(te);
		 }
		 return stack.isEmpty(); 
	 }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200905163126294.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
用自带的栈并不是很快，所以我们使用**数组模拟栈**的实现，这样速度就快了一点：

```java
 public boolean isValid(String s) {
		char a[]=new char[s.length()];
		int index=-1;
		 for(int i=0;i<s.length();i++)
		 {	
			 char te=s.charAt(i);
			 if(te==']')
			 {
				 if(index>=0&&a[index]=='[')
					 index--;
				 else {
					return false;
				}
			 }
			 else if(te=='}')
			 {
				 if(index>=0&&a[index]=='{')
					 index--;
				 else {
					return false;
				}
			 }
			 else if(te==')')
			 {
				 if(index>=0&&a[index]=='(')
					 index--;
				 else {
					return false;
				 }
			 }
			 else
				 a[++index]=te;
		 }
		 return index==-1; 
	 }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200905163250996.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)



## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)