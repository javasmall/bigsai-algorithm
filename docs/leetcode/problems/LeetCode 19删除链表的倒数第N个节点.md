## LeetCode 19删除链表的倒数第N个节点

>给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

>给定一个链表: 1->2->3->4->5, 和 n = 2.
>当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：
>给定的 n 保证是有效的。

进阶：
>你能尝试使用一趟扫描实现吗？


**分析：**
可以扫描两次，第一次获取总长度，知道倒数第N是正数第几个，第二次扫描真正的找到节点删除。

如何扫描一次呢？

可以使用数组(集合)，将所有节点地址存入ArrayList中，根据n找到正数的编号，直接编号前一个next指向编号后一个节点，最终返回头即可，当然要考虑特殊情况比如删除头之类。实现代码：

```java
public ListNode removeNthFromEnd2(ListNode head, int n) {		
		List<ListNode>list=new ArrayList<ListNode>();
		ListNode team=head;
		while (team!=null) {
			list.add(team);
			team=team.next;
		}
		list.add(null);
		if(list.size()==n)
			return head.next;
		if(list.size()<1)return null;
		int index=list.size()-1-n;
		list.get(index-1).next=list.get(index+1);
		return head;  	
    }
```

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200905191736689.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

还可以怎么考虑？

**用两个指针**，一个先走N步，然后两个同时向下寻找。一直到右侧的到最尽头即可找到待删除节点。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200905160855734.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

这样删除并不方便，在具体操作上，要找到待删除的前一个节点，用这个节点删除他的后面节点。而如果待删除的是头节点可能还需要特殊讨论，为了避免这种情况，我们可以使用一个头节点放在最前侧，这样就可以把链表中每一个节点都当作普通节点来处理。

具体代码为：

```java
 public ListNode removeNthFromEnd(ListNode head, int n) {		
		ListNode value=new ListNode(0);
		value.next=head;
		head=value;
		ListNode team=value;
    	for(int i=0;i<n;i++)
    	{
    		team=team.next;
    	}
    	while (team.next!=null) {
			team=team.next;
			head=head.next;
		}
    	if(head.next!=null)
    	head.next=head.next.next;
    	return value.next;  	
    }
```
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200905161212198.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)