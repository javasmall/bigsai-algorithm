## LeetCode 21合并两个有序列表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：
>输入：1->2->4, 1->3->4
>输出：1->1->2->3->4->4


**分析：**
思路，这题思路比较简单，合并两个有序链表，可以创建一个新的链表，然后两个子链表进行遍历比较插入当前较小的那个。

具体代码为：

```java
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode value=new ListNode(Integer.MIN_VALUE);
      ListNode team=value;
      while (l1!=null||l2!=null) {
		if(l1==null)
		{
			team.next=l2;break;
		}
		else if (l2==null) {
			team.next=l1;break;
		}
		else {
			if(l1.val<l2.val)
			{
				team.next=new ListNode(l1.val);
				team=team.next;
				l1=l1.next;
			}
			else {
				team.next=new ListNode(l2.val);
				team=team.next;
				l2=l2.next;
			}
		  }
	   }
       return value.next;
  }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020090616231233.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

当然，也可以将其中一个链表L1设为定的，然后另一个L2插入进来合并。具体实现是每次向下一步，如果需要插入则把另一个链表整个都插入进来，相当于交换L1，L2节点位置。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200906163142326.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
具体实现为：

```java
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
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200906163446511.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)