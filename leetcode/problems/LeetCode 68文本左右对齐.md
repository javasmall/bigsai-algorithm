## LeetCode 68文本左右对齐
描述
>给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
>你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
>要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
>文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:
>单词是指由非空格字符组成的字符序列。
>每个单词的长度大于 0，小于等于 maxWidth。
>输入单词数组 words 至少包含一个单词。
>示例:
```
输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
示例 2:

输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```
**分析**
字符串处理的贪心题，首先要知道以下几点要求：
 - 如果不是一个单词，不是最后一行，每一行最左侧和最右侧都有单词。
- 每一行尽量长，能够包容够多的单词，但每两个单词之间至少有一个空格
- 空格要匀称，如果无法绝对相等那么偏左的要多一点

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201122202525300.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


在具体处理上也很容易，可以用一个数字统计当前List已存字符长度。如果可以存下就加进来，存不下那就处理当前集合然后初始话添加进来。在处理当前集合的时候需要注意空格情况，首先空格要均匀，其次如果不能绝对均匀左侧要比右侧多。


实现代码为：

```java
public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String>vaList=new ArrayList<String>();
        List<String>teamList=new ArrayList<String>();
        int strlen=0;
        for(int i=0;i<words.length;i++)
        {
            String str=words[i];
            //System.out.println(teamlen+teamList.toString());
            if(strlen+str.length()+teamList.size()<=maxWidth)
            {
                teamList.add(str);
            }
            else {
                String team=addstr(teamList,words,maxWidth,strlen,false);
                vaList.add(team);
                teamList.clear();
                teamList.add(str);
                strlen=0;
            }
            strlen+=str.length();
        }
        String team=addstr(teamList,words,maxWidth,strlen,true);
        vaList.add(team);
        return vaList;

    }

    private static  String addstr(List<String> teamList,String words[], int maxWidth,int strlen,boolean isLast) {//组合成一个字符串返回
        //System.out.println(teamList.toString());
        StringBuilder sb=new StringBuilder();
        if(isLast)
        {
            for(String str:teamList)
            {
            	sb.append(str);
            	sb.append(' ');
            }
            if(sb.length()>maxWidth)
            {
                return sb.deleteCharAt(maxWidth).toString();
            }
            for(int i=sb.length();i<maxWidth;i++)
            {
                sb.append(' ');
            }
        }
        else {
            //计算空格总数量
            int spaceNum=maxWidth-strlen;
               int aveNum;
               if(teamList.size()==1)
               aveNum=1;
            else  aveNum=spaceNum/(teamList.size()-1);
               
            int more=spaceNum-aveNum*(teamList.size()-1);//不能平均分 多出来的空格

            sb.append(teamList.get(0));
            for(int i=1;i<teamList.size();i++)
            {
                int spaceAdd=aveNum;
                if(more-->0)
                    spaceAdd++;

                for(int j=0;j<spaceAdd;j++)
                    {sb.append(' ');}
                sb.append(teamList.get(i));
            }
               for(int i=sb.length();i<maxWidth;i++)
            {
                sb.append(' ');
            }
        }
        return sb.toString();
    }	
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/2e5d203d7825d0eea79b027654dc996d.png)