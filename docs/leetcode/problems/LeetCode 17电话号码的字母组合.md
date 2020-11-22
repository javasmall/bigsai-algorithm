## LeetCode 17电话号码的字母组合
![image-20201117001956997](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117001956997.png)

**分析**

这种问题，明显就是搜索类的问题。你可以使用广度优先搜索bfs，借助一个队列储存字符串进行操作，也可以使用深度优先搜素。

在这里就用深度优先搜索，而使用dfs时候我们通常借助递归来实现，而递归又是一个回溯的过程，你可以每次创建新的字符串但是也可以使用StringBuilder进行重复使用。对于电话号码，你需要预储存，可以使用HashMap也可以使用List []数组完成。

具体代码为：

```java
 List<String>value=new ArrayList<String>();
	 String digitString;
	 List<Character> list[];
	public  List<String> letterCombinations(String digits) {
		if(digits==null||"".equals(digits))return value;
		digitString=digits;
		list=new ArrayList[8];
		//初始化
		for(int i=0;i<list.length;i++)
		{
			list[i]=new ArrayList<Character>();
		}
		for(int i=0;i<5;i++)
		{
        	for(int j=0;j<3;j++)
        	{
        		list[i].add((char) ('a'+i*3+j));
        	}
            
		}
		list[5].add('p');list[5].add('q');list[5].add('r');list[5].add('s');
		list[6].add('t');list[6].add('u');list[6].add('v');
		list[7].add('w');list[7].add('x');list[7].add('y');list[7].add('z');
		
		
		dfs(new StringBuilder(""),0);
		
//		for(List<Character> a:list)//打印测试
//		{
//			System.out.println(a.toString());
//		}
		return value;
		
    }
	private  void dfs(StringBuilder stringBuilder, int index) {
		if(index==digitString.length()) 
		{value.add(stringBuilder.toString());return;}
		int va=digitString.charAt(index)-'2';
		for(int i=0;i<list[va].size();i++)
		{
			
			dfs(stringBuilder.append(list[va].get(i)), index+1);
			stringBuilder.deleteCharAt(index);
		}	
	}
```




## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)