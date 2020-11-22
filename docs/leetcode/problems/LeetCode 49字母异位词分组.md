## LeetCode 49字母异位词分组

![image-20201118215801897](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201118215801897.png)

**分析**

题目的意思就是给若干个字符串单词，然后将含有全部相同的字母放到一个`List<String>`中。我们的核心问题是将这个放到哪里？

你可以使用暴力枚举，每次遍历判断，但是那样效率太低，所以我们可以进行**哈希** 存储。创建一个`Map<String,List<String>>`类型的HashMap进行存储。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201101190232659.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


实现代码为：

```java
public List<List<String>> groupAnagrams(String[] strs) {
       List<List<String>>lists=new ArrayList<>();
       Map<String,List<String>>map=new HashMap<>();
       for(String str: strs)
       {
           char vachar[]=str.toCharArray();
           Arrays.sort(vachar);
           String team=String.copyValueOf(vachar);
           List<String>list=map.getOrDefault(team,new ArrayList<>());
           list.add(str);
           map.put(team,list);
       }
//        for(List<String> list:map.values())
//        {
//            lists.add(list);
//        }
       lists.addAll(map.values());
       return  lists;
   }
```
执行结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201101101916183.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)