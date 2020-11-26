## LeetCode 38外观数列
![image-20201117222234551](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117222234551.png)

**分析：**
水题，我们只需要模拟流程即可，这个流程也很简单，每次直接从前往后枚举即可，枚举的每个小间断寻找一个元素，知道元素内容和个数之后添加到新串后面即可。

对于实现的代码，别用String就行，这里先用了StringBuilder：

```java
public String countAndSay(int n) {
    if(n==1)return "1";
	StringBuilder sBuilder=new StringBuilder("1");
	while (--n>0) {
		StringBuilder s2=new StringBuilder();
		int index=1;
		int i=0;
		for(i=0;i<sBuilder.length()-1;i++)
		{
			if(sBuilder.charAt(i)==sBuilder.charAt(i+1))
				index++;
			else {
				s2.append(index).append(sBuilder.charAt(i));
				index=1;
			}
		}
		s2.append(index).append(sBuilder.charAt(i));
		//System.out.println(sBuilder.toString()+" "+i);
		sBuilder=s2;    
	}
	
	return sBuilder.toString();
   }
```
效果的话一般般3ms
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101119065414.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
使用数组存储直接操作会更快一些：

```java
public  String countAndSay(int n) {
		if(n==1)return "1";
		int a[]=new int[5000];
		a[0]=1;
		int b[]=new int[5000];
		int len=1;
		int index=0;
		while (--n>0) {
			int num=1;
			int i=0;
			for(i=0;i<len-1;i++)
			{
				if(a[i]==a[i+1])
					num++;
				else {
					b[index++]=num;
					b[index++]=a[i];
					num=1;
				}
			}
			b[index++]=num;
			b[index++]=a[i];
			//交换
			int team[]=a;
			a=b;
			b=team;
			len=index;
			index=0;
		}
		StringBuilder sBuilder=new StringBuilder("");
		for(int j=0;j<len;j++)
		{
			sBuilder.append(a[j]);
		}
		return sBuilder.toString();
   }
```



![在这里插入图片描述](https://img-blog.csdnimg.cn/20201011190857807.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)