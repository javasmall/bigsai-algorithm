## LeetCode 06Z字形变换
### 题意
题目描述
>将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

```
L   C   I   R
E T O E S I I G
E   D   H   N
```
>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
>请你实现这个将字符串进行指定行数变换的函数：
>string convert(string s, int numRows);

**示例 1:**
>输入: s = "LEETCODEISHIRING", numRows = 3
>输出: "LCIRETOESIIGEDHN"

**示例 2:**
>输入: s = "LEETCODEISHIRING", numRows = 4
>输出: "LDREOEIIECIHNTSG"
>解释:
```
L     D     R
E   O E   I I
E C   I H   N
T     S     G
```

### 分析
对于这题该如何处理呢？首先要理解题意，它就是本来给一个字符串，然后要按照Z字形排列等到一个形状，根据这个形状按照从左往右的顺序取值得到一个新的字符串。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814174519496.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

**法一模拟法：**
既然这个字符串是固定的，那么我们是否可以模拟这个过程？
- of course you can 模拟。你可以定义一个二位数组，根据Z字形的这个排列规律，先向下(同时横坐标不变)，再向上(同时考虑横坐标增加)一直到最后，然后对二维数组进行遍历取值(不空的值。)

这样当然可以，但是模拟真的有必要这么搞嘛？当然没必要。二维数组占据太多无用的空间浪费内存。我们其实可以对内存进行优化考虑：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814180244683.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
这样只需要考虑上下两个方向往集合中添加元素，最终就可以实现啦。不过在字符串叠加时候尽量不要使用String直接加，会很慢。这种方法只能干掉40%的人，一般般。
**ac代码为：**

```java
public String convert(String s, int numRows) {
		  List<Character> list[]=new ArrayList[numRows];
		  for(int i=0;i<numRows;i++)
		  {
			  list[i]=new ArrayList<Character>();
		  }
		  int index=0;
		  boolean up=false;//方向 初始向下
		  for(int i=0;i<s.length();i++)
		  {
			  list[index].add(s.charAt(i));
			  if(numRows==1) {continue;}
			  if(up)//向上
			  {
				  if(index==0)
				  {
					  index++;up=false;
				  }
				  else {
					index--;
				}
			  }
			  else {
				if(index==numRows-1)
				{
					index--;up=true;
				}
				else {
					index++;
				}
			  }
		  }
		  StringBuilder builder=new StringBuilder();
		  for(int i=0;i<numRows;i++)
		  {
			  for(int j=0;j<list[i].size();j++)
			  {
				  builder.append(list[i].get(j));
			  }
		  }
		  return builder.toString();	
	}
```

**法二数学分析**
上面的一种方法为模拟Z字形操作的整个流程，需要往里添加，取值也需要遍历取值。我们能不能用另一种角度去思考问题呢？

因为每次只加一个字符，我们如果按照以下的思路看待这个问题(原字符串弯曲)，从每一层看，能不能找到每一层有什么规律呢？
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814181620649.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
- 第一层是 `0  6 12`也就是 `0   0+(n-1)*2   0+(n-1)*3`
- 第二层两个求位置关系，可不可以看成**第一层每个位置-1和+1两个位置(越界不考虑)？** 
- 第三层和第二层同理，**看成第一层的-2和+2不越界的位置**。
- 最后一层单独考虑

这样整个逻辑分析就完成了，可以根据位置添加元素进去再取值。ac的代码如下：

```java
public String convert(String s, int numRows) {
        if(numRows==1)return s;
		 List<Character> list[]=new ArrayList[numRows];
		 for(int i=0;i<numRows;i++)
		 {
			list[i]=new ArrayList<Character>();
		 }
		 //处理第一行最后一行
		 for(int i=0;i<s.length();i+=(numRows-1)*2)
		 {
			 list[0].add(s.charAt(i));
			 if(i+numRows-1<s.length())
			 {
				 list[numRows-1].add(s.charAt(i+numRows-1));
			 }
		 }
		 for(int i=0;i<s.length()+numRows;i+=(numRows-1)*2)
		 {
			 for(int j=1;j<numRows-1;j++)//中间所有行
			 {
				 int index1=i-j;
				 int index2=i+j;
				 if(index1>=0&&index1<s.length())
					 list[j].add(s.charAt(index1));
				 if(index2>=0&&index2<s.length())
					 list[j].add(s.charAt(index2));
			 }
		 }
		  StringBuilder builder=new StringBuilder();	 
		  for(int i=0;i<numRows;i++)
		  {
			  for(int j=0;j<list[i].size();j++)
			  {
				  builder.append(list[i].get(j));
			  }
		  }
		  return builder.toString();
    }
```
但这种方法也只能干掉40%+的人，**该如何优化呢?**

- 首先，该方法先存到List[]再取，其实是遍历两次，其实大可不必这样，**我们可以在进行计算每一层的同时加入到结果中。**
- 其次，由于边界的问题我们需要考虑太多的边界问题，我们对此对中间层的考虑优化，两个节点位置通过计算这样组合，可以优化边界的if else判断。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814183414257.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)

注意一些细节情况，最终ac的代码为：

```java
public static String convert2(String s, int numRows) {
		 if(numRows==1)return s;
		 //处理第一行
		 StringBuilder builder=new StringBuilder();
		 for(int i=0;i<s.length();i+=(numRows-1)*2)
		 {
			 builder.append(s.charAt(i));
		 }
		 for(int i=1;i<numRows-1;i++)
		 {
			 for(int j=0;j<s.length()+numRows;j+=(numRows-1)*2)//中间所有行
			 {
				 int index1=j+i;
				 int index2=j+(numRows-1)*2-i;
				 if(index1<s.length())
					builder.append(s.charAt(index1));
				 if(index2<s.length())
					builder.append(s.charAt(index2));
			 }
		 }
		 for(int i=numRows-1;i<s.length();i+=(numRows-1)*2)
		 {	
			 builder.append(s.charAt(i));
		 }
		  return builder.toString();
	}
```
最终的效果还行(偶尔三毫秒)：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814183631936.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

