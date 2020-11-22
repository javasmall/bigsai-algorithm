## LeetCode 30串联所有单词得字串
题目描述：
>给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
>注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

 

示例 1：

>输入：
>s = "barfoothefoobarman",
>words = ["foo","bar"]
>输出：[0,9]
>解释：
>从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
>输出的顺序不重要, [9,0] 也是有效答案。

示例 2：
>输入：
>s = "wordgoodgoodgoodbestword",
>words = ["word","good","best","word"]
>输出：[]

**分析：**
这题讲真还是挺有技巧和方案的，刷这道题也花了不少心思，需要考虑的点也稍微多一点。题意就是要找到字符串s的某个字串可以由words中所有单词组成。返回满足匹配s子串的首位编号。

**递归法：**
从处理的方式上理论是可以使用递归的，但是由于层数太多并且个别比较特殊的数据可能导致爆栈TL。这里就有个教训：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921161859357.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
主要当时没仔细读题，没有在意每个单词长度都相同所以以为是搜索题，后来仔细看题之后才发现问题。

**普通哈希法(滑动窗口)**
对于有些人叫啥滑动窗口啥稀奇古怪的漂亮名称，这里我就简称为Hash法。如何去分析和处理这个问题呢？我们可以看看一些重要的条件：
- words中所有单词长度都相同
- 必须使用所有words中的单词一次

也就是说在进行匹配的时候可以根据单词进行匹配。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921170323630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

但每个字符串进行判断的时候，可以进行**分割成若干单词数判断**。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921180920807.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
用两个HashMap储存单词数即可，存储途中进行判断如果有不满足直接停止。如果能跑到最后说明可以添加这个标记。

具体实现的代码为：

```java
public List<Integer> findSubstring(String s, String[] words) 
{
	    List<Integer>value=new ArrayList<Integer>();
		Map<String, Integer>map=new HashMap<String, Integer>();
		for(int i=0;i<words.length;i++)
		{
			int num=map.getOrDefault(words[i], 0);
			map.put(words[i], num+1);
		}
		int wordlen=words[0].length();
		int len=words[0].length()*words.length;
		StringBuilder sBuilder=new StringBuilder(" ");
		sBuilder.append(s.substring(0, len-1));
		for(int i=0;i<s.length()-len+1;i++)
		{
			sBuilder.deleteCharAt(0);
			sBuilder.append(s.charAt(i+len-1));
			
			int num=0;//统计总共满足的单词数量
			Map<String, Integer>map2=new HashMap<String, Integer>();
			//map2.putAll(map);
			int index=0;
			while (index<len) {
				String team=sBuilder.substring(index,index+wordlen);
				int number=map2.getOrDefault(team, 0);//次数
				map2.put(team, number+1);
				if(number+1>map.getOrDefault(team, 0))
					break;
				
				index+=wordlen;
			}
			
			if(index==len)
				value.add(i);
				
		}
		return value;
}
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921182701622.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

**Hash滑动窗口优化**
可以发现在上面所涉及的的滑动处理中每次都需要重新计算当前字符串的HashMap情况并重新匹配。这样就有很多已经匹配的情况就浪费了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921195226927.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
所以就需要优化来去掉重复的计算，**首先要将字符串分成单词长度的组数来分别计算。**
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020092120042270.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
然后每组在详细进行的时候需要两个指针动态向右表示区间匹配。而Map通常不需要每次都刷新可以重新利用。**一个坐标j代表开头一个index表示当前结尾**。

你可能会遇到以下几种情况：
- 遇到新单词不存在，此时j和index都移动到单词后重新开始，且储存的动态map需要clear；


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921201219949.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
- 遇到的单词存在，但是多了，需要将j右移一直到消除这个多余单词，同时修改动态map。


![在这里插入图片描述](https://img-blog.csdnimg.cn/2020092120162062.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
- 正常情况，叠加匹配更新index和map。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921201815783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

上面步骤完成之后，如果j+len==index那么就说明完成匹配，添加此个编号。但在此同时，可以判断下个单词是否与当前首单词相等，如果相等直接更新对应j和index顺便加入结果集中，不过不需要更新动态的map。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921202224920.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

有了上述优化的思路，就可以码代码了。注意细节实现，前开后闭等等，具体代码为：

```java
  public List<Integer> findSubstring(String s, String[] words) {
      List<Integer>value=new ArrayList<Integer>();//返回的结果
	Map<String, Integer>map=new HashMap<String, Integer>();//统计单词个数
	for(String team:words)//进行统计
	{
		int num=map.getOrDefault(team, 0);
		map.put(team, num+1);
	}
	int wordlen=words[0].length();//单个单词的长度
	int len=words[0].length()*words.length;//总长度
	for(int i=0;i<wordlen;i++)//分组分别进行
	{
		int j=i,index=j;
	    Map<String, Integer>map2=new HashMap<String, Integer>();
		while (j<=s.length()-len&&index+wordlen<=s.length()) {
			 String word=s.substring(index,index+wordlen);
			 
			 int num=map2.getOrDefault(word, 0);
			 map2.put(word, num+1);
			 if(!map.containsKey(word))//不包含该元素，直接跳过
			 {
				 
				 j=index+wordlen;
				 map2.clear(); 
			 }
			 else if(map.get(word)<num+1)//元素存在但次数过多
			 {
				 String teamstr="";
				 while (!(teamstr=s.substring(j,j+wordlen)).equals(word)) {//找到第一个不相等得
					 map2.put(teamstr, map2.get(teamstr)-1);
					 j+=wordlen;
				}
				 map2.put(teamstr, map2.get(teamstr)-1);
				 j+=wordlen;
			 }
			 index+=wordlen;
			 if(index==j+len)
			 {
				 value.add(j);
				 while (index+wordlen<=s.length()&&s.substring(j, j+wordlen).equals(s.substring(index, index+wordlen))) {
					 value.add(j+wordlen);
					 j+=wordlen;index+=wordlen;
				} 
				 String teamstr=s.substring(j,j+wordlen);
				 map2.put(teamstr, map2.get(teamstr)-1);
				 j+=wordlen;
			 }
			
		}
	}
	return value;
}
```


 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921202406893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)