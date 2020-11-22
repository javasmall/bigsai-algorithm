## LeetCode 58最后一个单词长度
题目描述
>给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
>如果不存在最后一个单词，请返回 0 。
>说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。

示例:
>输入: "Hello World"
>输出: 5

分析
这题其实很简单，就是三个步骤模拟这个过程就可以了：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114163538324.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

具体代码：

```java
public int lengthOfLastWord(String s) {
    if(s.length()==0)return 0;
	char va[]=s.toCharArray();
	int index=va.length-1;
	while (index>=0&&va[index]==' ') {
		index--;
	}
	int i;
	for( i=index;i>=0;i--)
	{
		if(va[i]==' ')
		{
			break;
		}
	}
	 
	 return index-i;
   }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114114727288.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)