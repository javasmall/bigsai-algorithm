## LeetCode 24两两交换链表中的节点

![image-20201117202402020](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117202402020.png)
**分析：**
本题的要求就是交换奇偶节点。不能直接交换值也就意味不能直接赋值要通过链表插入删除来实现。而具体的流程也很简单：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020091219460881.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

具体实现代码为：

```java
public ListNode swapPairs(ListNode head) {
	 if(head==null)return head;
	 ListNode value=new ListNode(0);
	 value.next=head;
	 ListNode team=value;
	 ListNode first;
	 ListNode second;
	 while (team!=null&&team.next!=null) {
		if(team.next.next==null)
			return value.next;
		first=team.next;
	    second=team.next.next;
		
	    team.next=second;
	    first.next=second.next;
	    second.next=first;
	    
		team=first;//team=team.next.next
	}
	 return value.next;
 }
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200912194732152.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)