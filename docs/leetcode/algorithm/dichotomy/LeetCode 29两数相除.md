## LeetCode 29两数相除
**题目描述**
![image-20201117210815772](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117210815772.png)

**分析**
需要计算除法的数值是多少，并且还遇到以下问题：
- 数值可能越界
- 不能使用乘除法

数值越界问题可以特殊情况考虑即可。但是不能使用乘除法怎么去知道除法的结果是多少呢？

**法一：加法累加**
这可能是最笨的方法了，除以几，就用这个数去叠加找到结果。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919202832878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
当然这样如果数字很大，除数为1，2这种的会很慢，**只能勉强ac**。

```java
public static int divide(int dividend, int divisor) {
		int zhengfu=1;
		long divd=dividend,divs=divisor;
		if(dividend>0&&divisor<0)
		{
			divs=-divisor;zhengfu=-1;
		}
		else if(dividend<0&&divisor>0)
		{
			divd=-divd;zhengfu=-1;
		}
		else if (dividend<0&&divisor<0) {
			divd=-divd;divs=-divs;
		}
	
		if(Math.abs(divd)<Math.abs(divs))return 0;
		long i=0,index=0;
		if(divs==1)i=divd+1;
		else
		while (index<=divd) {
			i++;index+=divs;
		}
		long va=(zhengfu==1?(i-1):(1-i));
		if(va>Integer.MAX_VALUE)return Integer.MAX_VALUE;
		if(va<Integer.MIN_VALUE)return Integer.MIN_VALUE;
		return (int) va;
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919202955514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
用什么方法可以优化算法呢？这就涉及到二进制的问题了。在二进制中：
- 2=0010 表示有2个1
- 4=0100表示有4个1
- 6=0110表示有4+2个1

同样任何一个数都可以用二进制来表示，1101表示8+4+1.同理我们将这种思想带到本题进行计算，**只不过基础单位不为1而已**：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919204139871.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
因为我们使用加法实现数值乘以二的效果，所以利用这个每次找到范围内的最大个数(数值叠加同时需要其他变量统计次数)。然后处理完这部分数据继续操作剩下的数值直到停止。当然具体实现上可以考虑剪枝(除数为+-1的时候，同时要妥善解决正负数和越界问题，这里就用long处理，全部转成正数然后用一个标记正负)。

实现代码为：

```java
public static int divide(int dividend, int divisor)
	{
		if(divisor==1)return dividend;
		if(divisor==-1)return dividend==Integer.MIN_VALUE?Integer.MAX_VALUE:-dividend;

		long value=0;//记录总次数结果
		int time=1;//临时每次的次数
		long divd=dividend,divs=divisor;//转成long处理
		
		int zhengfu=1;//判断是正数还是负数
		if(divd<0)
		{
			divd=-divd;zhengfu=-zhengfu;
		}
	    if(divs<0)
	    {
	    	divs=-divs;zhengfu=-zhengfu;
	    }
	    long team=divs;//临时数据2倍叠加
		while (team<=divd) {
			if(team+team>divd)
			{
				value+=time;
				divd-=team;
				team=divs;
				time=1;
				
				continue;
			}
			team+=team;
			time+=time;
			
		}
		return (int) (zhengfu==1?value:-value);
	}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919204907482.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语
原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)