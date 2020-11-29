# LeetCode 71 简化路径



## 题目描述

以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径

请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。

 

示例 1：

```
输入："/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。
```

示例 2：

```
输入："/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
```

示例 3：

```
输入："/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
```

示例 4：

```
输入："/a/./b/../../c/"
输出："/c"
```

示例 5：

```
输入："/a/../../b/../c//.//"
输出："/c"
```

示例 6：

```
输入："/a//b////c/d//././/.."
输出："/a/b/c"
```

## 分析

这题就是栈的应用，通过栈遍历放入目录，在遍历字符串的同时如果遇到`/` 那么就考虑进行操作。逻辑如下：

![image-20201128154125060](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201128154125060.png)

具体编写代码的时候，需要注意是否为最后一个字符和一些特殊情况(栈为空则别抛出)。

具体代码为：

```java
public String simplifyPath(String path) {
  Stack<String>stack=new Stack<String>();
  char ch[]=path.toCharArray();
  StringBuilder sBuilder=new StringBuilder();

  for(int i=0;i<ch.length;i++)
  {

    if(ch[i]=='/'||i==ch.length-1)
    {
      if(i==ch.length-1&&ch[i]!='/')
      {
        sBuilder.append(ch[i]);
      }
      if(sBuilder.length()==0||sBuilder.toString().equals("."))
      {}
      else if (sBuilder.toString().equals("..")) {
        if(!stack.isEmpty())
          stack.pop();
      }
      else if(sBuilder.length()>0){
        stack.push(sBuilder.toString());
      }
      sBuilder=new StringBuilder();
    }	
    else 
    {
      sBuilder.append(ch[i]);
    }
  }

  sBuilder=new StringBuilder("");
  for(String s:stack)
  {
    sBuilder.append('/');
    sBuilder.append(s);
  }
  if(stack.isEmpty())
    return "/";
  return sBuilder.toString();

}
```

![image-20201128154318877](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201128154318877.png)