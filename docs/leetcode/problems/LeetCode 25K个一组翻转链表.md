## LeetCode 25K个一组翻转链表
**题目要求**
>给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
>k 是一个正整数，它的值小于或等于链表的长度。
>如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例：
>给你这个链表：1->2->3->4->5
>当 k = 2 时，应当返回: 2->1->4->3->5
>当 k = 3 时，应当返回: 3->2->1->4->5

 说明：
>你的算法只能使用常数的额外空间。
>**你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。**

分析：
本题是K个一组进行翻转，相信大家都有遇到翻转链表实现起来可能容易一些，但是这里面要求K个一组进行翻转，不满足K个就不翻转。并且要求必须交换节点不能创建新的节点。如果K=3，从宏观来看需要交换的可能是这样的：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200913200155429.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

而具体的一个交换流程该如何考虑呢？一组一组的交换，**当组是否还满足K个？如何实现？**
- 用两个指针，一个指针**tail**预先走K步，如果能正常行走说明可以翻转。

每一组翻转需要整个联立起来，该如何实现呢？可以预先记录首尾，然后再翻转局部将整个链表串联起来。在具体的交换步骤，可以使用一个prenode记录前驱节点以实现交换。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200913203559203.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
具体实现的代码为：

```java
 public  ListNode reverseKGroup(ListNode head, int k) {
		  if(head==null)return null;
          ListNode value=new ListNode(0);
		  value.next=head;
		  ListNode pretail=value;//前一个区间末尾
		  ListNode pretailNext=value.next;//交换区间的第一个(交换后会变成最后一个需要连接下一个区间)
		  ListNode tail=head;//末尾用来试探
		  
		  ListNode preNode=null;//记录前驱节点
		  ListNode node=head;//当前节点
		  int index=0;
		  while(tail!=null)
		  {
			  while (index++<k) {//拉开K个程度
               if(tail==null)//不满足反转条件直接返回
				  return value.next;
					tail=tail.next;
                    
			  }//tail到达固定位置
			  
			  while (index-->1) {//开始反转node
				  //System.out.println(index+" "+head.val);
				node=head;
				head=head.next;
				node.next=preNode;
				preNode=node;
			  }
			  //连接
			  pretail.next=node;
			  pretailNext.next=tail;
			  pretail=pretailNext;
			  pretailNext=tail;
			  
			  index=0;
		  }
		  return value.next;	
	  }
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)