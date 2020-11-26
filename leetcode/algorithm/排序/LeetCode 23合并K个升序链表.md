## LeetCode 23合并K个升序链表
 **题目描述**
![image-20201117201152714](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117201152714.png)

**分析**
这题合并多个链表，可以遍历当前有效的(不为null)链表头找到最小的那个，逐一插入到新的链表中。也可以从前往后两两合并链表，使用上面[合并两个有序链表](https://bigsai.blog.csdn.net/article/details/108432953)的方法。但是这两种需要比较的次数。时间复杂度都为O(n*k)其中n为节点总个数，K为链表个数。

进行优化主要有两种具体实现思路 ，一种就是利用一种排序每次找到最小的节点，而这种更适合堆排序动态维护。另一种就是利用**类似归并的思想**。将两两归并，最终归并为一个链表。从效率上看每个比较次数本来从k次由归并变成了log k，所以时间复杂度为O(nlogk);而合并具体操作也有直接迭代和递归两种方式，这里就使用迭代的方式。

实现代码为：

```java
public ListNode mergeKLists(ListNode[] lists) {
		if(lists.length==0)return null;
		if(lists.length==1)return lists[0];
		List<ListNode>nodes1=new ArrayList<ListNode>();
		List<ListNode>nodes2=new ArrayList<ListNode>();
		int i=0;//归并
		for(;i<lists.length-1;i+=2)
		{
			nodes1.add(mergeTwoLists(lists[i],lists[i+1]));
		}
		if(i==lists.length-1)nodes1.add(lists[i]);
		while (true) {
			for(i=0;i<nodes1.size()-1;i+=2)
			{
				nodes2.add(mergeTwoLists(nodes1.get(i), nodes1.get(i+1)));
			}
			if(i==nodes1.size()-1) nodes2.add(nodes1.get(nodes1.size()-1));
			nodes1.clear();
			nodes1.addAll(nodes2);
			nodes2.clear();
			if(nodes1.size()==1)return nodes1.get(0);
		}   
	  }
	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		  if(l1==null)return l2;
		  if(l2==null)return l1;
		  if(l1.val>l2.val)//l1更小
		  {
			  ListNode team=l1;
			  l1=l2;
			  l2=team;
		  }
	      ListNode value=l1;
	      while (l2!=null) {
			if(l1.next==null)
			{
				l1.next=l2;break;
			}
			else if (l1.next.val<l2.val) {
				l1=l1.next;
			}
			else {
				ListNode node=l1.next;
				l1=l1.next=l2;
				l2=node;
				//l1=l1.next;
			}
		   }
	       return value;  
	  }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200912191538939.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
当然这样编写对数组的利用比较差，每次重新赋值浪费一定效率，可以直接复用数组每次两两合并的时候赋值到对应位置。具体代码为：

```java
public ListNode mergeKLists(ListNode[] lists) {
		if(lists.length==0)return null;
		if(lists.length==1)return lists[0];
		int k=lists.length-1;
		while (k>0) {
			int i;
			for(i=0;i<k;i+=2)
			{
				lists[i/2]=mergeTwoLists(lists[i], lists[i+1]);
			}
			if(i==k)
			{
				lists[i/2]=lists[i];
                   k=(k+1)/2; 
			}
               else
               {
                   k/=2;
               }
             
		}   
			return lists[0];
		  }
	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	 //此处省略
	 }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200912204654912.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)