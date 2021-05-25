//NAME:VASUNDHARA EAGA
//ROLL NO:2414
package mini;

import java.util.*;

class queue extends LinkedList<node>		//queue used for placing orders of items which are out of stock

{

	void enqueue(node temp,int q)

	{

		node temp2=new node(temp);

		temp2.stock=q;

		addLast(temp2);

	}

	node dequeue()

	{

		node temp=removeFirst();

		return temp;

	}

	void display()

	{

		if(!this.isEmpty())

		{

			for(node n:this)

				n.data();

		}

	}

	

}

class node				//node

{

	String name;

	int price,stock;

	double size;

	node left,right;

	node()

	{

		left=null;

		right=null;

	}

	node(String n,int y,int copy,double c)

	{

		left=null;

		right=null;

		stock=copy;

		name=n;

		price=y;

		size=c;

	}

	node(node t)

	{

		left=null;

		right=null;

		stock=t.stock;

		name=t.name;

		price=t.price;

		size=t.size;

	}

	void data()

	{

		System.out.println(" NAME :"+name+"\n PRICE: "+price+" Rs ."+"\n STOCK :"+stock+"\n SIZE: "+size);

	}

}




class tree1								//trees are created range wise

{

	Scanner sc=new Scanner(System.in);

	node root;

	tree1()

	{

		root=null;

	}

	public void create(int a,int b)			//entering stock

	{

			

		String name,ans;

		int price,stock;

		double size;

		node ptr;

	    do

	    {

	    	

	    		System.out.println("\nENTER THE NAME OF OUTFIT:");

	    		name=sc.next();

	    		do
	    		{
	    		System.out.println("\nENTER THE PRICE:");

		    	price=sc.nextInt();
	    		}while(price<=a || price>=b);

	    		
		    	System.out.println("\nENTER AVAILABLE STOCK:");

		    	stock=sc.nextInt();

		    	

		    	System.out.println("\nENTER THE SIZE OF THE OUTFIT:\n1.XS\t2.S\t3.M\t4.L\t5.XL\t6.XLL");

		    	do
		    	{
		    	size=sc.nextDouble();
		    	}while(size<1 && size>6);

	            node temp=new node(name,price,stock,size);

	            if(root==null)

	            {

	            	root=temp;

	            }

	    		else

	    		{

	    			ptr=root;

	    			while(ptr!=null)

	    			{

	    				if(temp.name.compareToIgnoreCase(ptr.name)<0)

	    				{

	    				     if(ptr.left==null)

	    				     {

	    				    	ptr.left=temp;

	    				    	break;

	    				     }

	    				     else

	    				     {

	    					     ptr=ptr.left;

	    					}

	    				}

	    				if(temp.name.compareToIgnoreCase(ptr.name)>0)

	    				{

	    					if(ptr.right==null)

	    					{

	    						ptr.right=temp;

	    						break;

	    					}

	    					else

	    					{

	    						ptr=ptr.right;

	    					}

	    				}

	    			}

	    			

	    		}    

	            System.out.println("DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)");

	            ans=sc.next();

	           // sc.nextLine();

	           // System.out.println();

	    	}while(ans.equalsIgnoreCase("y"));

	  }

	

	public node search(queue my_queue,String wrd)				//searching in tree

	{

		node ptr=new node();

		node temp=null;

		ptr=root;

		int flag=0,flag2=0;

		while(ptr!=null && flag!=1)

		{

			

			if(wrd.compareToIgnoreCase(ptr.name)==0)		//data found

			{

				System.out.println("\nOUTFIT AVAILABLE!");

				ptr.data();

				flag=1;

				flag2=0;

				System.out.println("\nDO YOU WANT TO BUY OUTFITS ?:(Yes=y / No=n) ");

				String ans=sc.next();

				

				if(ans.compareToIgnoreCase("y")==0)				//proceeding to buying

				{

					int q=Integer.MAX_VALUE,val=0;


					while(val==0)

					{

						System.out.println("ENTER THE QUANTITY TO BE PURCHASE:");

						q=sc.nextInt();

						val=bill(ptr,q);

						if(val==0)		//bill function returns 0 if item out of stock

						{

							System.out.println(q+" THIS MUCH STOCK NOT AVAILABLE.");
							continue;

						}

						//end out of stock
						else
						{
							System.out.println("\n 1->PLACE ORDER");

							int opt=sc.nextInt();

							if(opt==1)      //Placng order
							{
								flag2++;

							my_queue.enqueue(ptr, q);			//order enqued in queue

							System.out.println("YOUR ORDER FOR"+ptr.name+" OUTFIT FOR "+q+" STOCK HAS BEEN PLACED");

							break;			//done with searching and buying

						   }

					}//end while quantity checking

					if(flag2==0)				//if customer hasn't bought but only placed order

						temp=new node(wrd,ptr.price,q,ptr.size);

					}//end if buying

							break;

				}//end if data found

			if(wrd.compareToIgnoreCase(ptr.name)>0)

			{

				ptr=ptr.right;

			}

			else

			{

				ptr=ptr.left;

			}

	}

		if(flag==0 && flag2==0)

		{

			System.out.println("OUTFIT NOT AVAILABLE.");

		}

		return temp;
		}
		return temp;

	}

	



	int bill(node ptr,int quantity)

	{




		if(quantity<=ptr.stock)

		{

			ptr.stock=ptr.stock-quantity;					//updating

			System.out.format("%1s",ptr.name);

			System.out.format("%16s", Double.toString(ptr.price));

			System.out.format("%10s", Integer.toString(quantity));

			System.out.format("%14s", Double.toString(ptr.price*quantity));

			System.out.println();

				return 1;

		}

		return 0;//item present but not in stock

	}




}

class boutique			//contains trees for each range

{

	Scanner sc=new Scanner(System.in);

	String category;

	int range1=500,range2=3000,range3=5000;

	tree1 r1,r2,r3;

	node n;

	boutique(String cat)

	{

		category=cat;

		r1=new tree1();

		r2=new tree1();

		r3=new tree1();

	}

	public void accept()

	{

		Scanner so=new Scanner(System.in);

		System.out.println("SELECT THE RANGE YOU WANT TO ACCEPT:");

		System.out.println(" 1->500-3000 \n 2->3000-5000 \n 3->5000-7000 \n 4->All ranges");

		int opt=so.nextInt();

		switch(opt)

		{

		case 1:

			System.out.println("FOR RANGE 500-3000:");

			r1.create(500,3000);

			break;

		case 2:

			System.out.println("FOR RANGE 3000-5000");

			r2.create(3000,5000);

			break;

		case 3:

			System.out.println("FOR RANGE 5000-7000");

			r3.create(5000,7000);

			break;

		case 4:

			System.out.println("FOR RANGE 500-3000:");

			r1.create(7000,99999);

			System.out.println("FOR RANGE 3000-5000");

			r2.create(7000,999999);

			System.out.println("FOR RANGE 5000-7000");

			r3.create(7000,999999);

			break;

			default:

				System.out.println("ENTER THE VALID OPTION");

		}//end of switch case










	}

	private void inorder_display(node localRoot)

	{

		if(localRoot!=null)

		{

			inorder_display(localRoot.left);

			System.out.println("\nOUTFIT NAME :"+localRoot.name+"\nPRICE:"+localRoot.price+"\nSTOCK AVAILABLE:"+localRoot.stock+"\nSIZE:"+localRoot.size);

			inorder_display(localRoot.right);

		}

	}

	public void disp()

	{
		int flag=0;

		if(r1.root!=null)

			{
			System.out.println("\n For range 500-3000 \n");
			flag=1;

			inorder_display(r1.root);}

		if(r2.root!=null)

		{System.out.println("\n For range 3000-5000\n");
		flag=1;

		inorder_display(r2.root);}

		if(r3.root!=null)

		{System.out.println("\n For range 5000-7000\n");
		flag=1;

		inorder_display(r3.root);}

	}

	node search(queue my_queue,String wrd)		//returns purchased item's details

	{

		System.out.println("Enter price range \n 1)500-3000 \n 2)3000-5000 \n 3)5000-7000");

		int opt=sc.nextInt();

		node temp=new node();

		switch(opt)

		{

		case 1:

			temp=r1.search(my_queue,wrd);			//my_queue passed to add orders to the queue

			break;

		case 2:

			temp=r2.search(my_queue,wrd);

			break;

		case 3:

			temp=r3.search(my_queue,wrd);

			break;

			default:

				System.out.println("ENTER THE VALID OPTION");

		}//end switch

		return temp;

	}

	


	

}

class customer

{

	Scanner sc=new Scanner(System.in);

	private String name;

	private ArrayList<node> items=new ArrayList<node>();

	customer()

	{

		name="";

	}

	customer(String n)

	{

		name=n;

	}

	void create(queue my_queue,boutique m)		
	{

		//sc.nextLine();

		System.out.println("ENTER THE OUTFIT NAME TO SEARCH :");

		String name=sc.nextLine();

		node temp=m.search(my_queue,name);

		if(temp!=null)

		items.add(temp);

		//my_queue.display();

	}

	void cust_bill()

	{

		double sum=0;

		if(!items.isEmpty())		//

		{

			System.out.println("YOUR TOTAL BILL:\n");

			System.out.println("ITEM\t\tQUANTITY\t\tPRICE\t\t TOTAL");

			
			for(node temp:items)

			{

				System.out.print(String.format( temp.name,4));

				String cop=Integer.toString(temp.stock);

				cop=String.format("%19s", cop);

				System.out.print(String.format( cop,19));

				String c=Double.toString(temp.size);

				c=String.format("%21s", c);

				System.out.print(String.format( c,21));

				String t=Double.toString(temp.size*temp.stock);

				t=String.format("%18s", t);

				System.out.print(String.format( t,18));

				sum=sum+temp.stock*temp.size;

				System.out.println("\n");

			}

			System.out.println("TOTAL :"+sum);

		}

		System.out.println("THANK YOU FOR VISITING!!");




	}//end bill function

	String name()

	{

		return name;

	}

	

}

public class mini {




	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);

		int ansf=0;

		queue my_queue=new queue();

		boutique m1=new boutique("TRADITIONAL");

		boutique m2=new boutique("WESTERN");

		boutique m3=new boutique("INDO-WESTERN");

		boutique m4=new boutique("SEASONAL");

		int ans=1;

		String newpassword="password";

		System.out.println("\t\t********************** FASHION BOUTIQUE ***********************.");

		do

		{

			System.out.println("\nENTER THE CHOICE \n 1.OWNER \n 2.CUSTOMER");

			int opt=sc.nextInt();

			if(opt==1)			//owner

			{

				String ans2;

				String password;

			

				System.out.println("\nENTER THE PASSWORD (INITIAL PASSWORD : password ):");

				password=sc.next();

				if(password.compareToIgnoreCase(newpassword)==0)

				{

					do

					{

						int ans1;

						System.out.println("1.ENTER THE QUANTITY OF OUTFITS.\n2.SEE THE ORDER UPDATES.\n3.CHANGE PASSWORD.\n4.DISPLAY STOCK.");

						ans1=sc.nextInt();

						switch(ans1)

						{

						case 1:

							System.out.println("ENTER THE CATEGORY YOU WANT TO ADD ");

							System.out.println(" 1->TRADITIONAL \n 2->WESTERN \n 3->INDO-WESTERN \n 4->SEASONAL");

							int opt1=sc.nextInt();

							switch(opt1)

							{

							case 1:

								System.out.println("TRADITIONAL");

								m1.accept();

								break;

							case 2:

								System.out.println("WESTERN");

								m2.accept();

								break;

							case 3:

								System.out.println("INDO-WESTERN");

								m3.accept();

								break;

							case 4:

								System.out.println("SEASONAL");

								m4.accept();

								break;

							default:

								System.out.println("INVALID OPTION");

								break;	

							}//end of switch case

							break;//end of entering stock

						case 2:

							if(!my_queue.isEmpty())

							{

								System.out.println("ORDERS BY CUSTOMER:");		//reminds owner to place orders as per customers' request

								for(node e:my_queue)

								{

									e.data();

								}

							}

							else

								System.out.println("NO ORDERS PLACED");

							break;

						case 3:		//password

							System.out.println("ENTER THE NEW PASSWORD:");

							newpassword=sc.next();

							System.out.println("\nPASSWORD CHANGED SUCCESSFULLY !.");

							break; //end of changing password

						case 4:		//display

							System.out.println("STOCK DETAILS");

							System.out.println("TRADITIONAL");

							m1.disp();

							System.out.println("WESTERN");

							m2.disp();

							System.out.println("INDO-WESTERN");

							m3.disp();

							System.out.println("SEASONAL");

							m4.disp();

							

							break;


						}//end of switch case

						System.out.println("DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)");

						ans2=sc.next();

						}while(ans2.compareToIgnoreCase("y")==0);

						

				}

				

				else

				{

					System.out.println("ENTER THE CORRECT PASSWORD.");

				}

				

			}

			else if(opt==2)			//customer

			{

				int ch;

				System.out.println("\nENTER THE NAME OF THE NAME OF THE OUTFIT : ");

				String n=sc.next();

				customer cust=new customer(n);

				ans=1;

			

				while(ans==1)

				{

					System.out.println("1.TRADITIONAL.\n2.WESTERN.\n3.INDO-WESTERN\n4.SEASONAL.\nENTER THE CHOICE:");

					ch=sc.nextInt();

					switch(ch)

					{

					case 1:

						cust.create(my_queue, m1);

						break;

					case 2:

						cust.create(my_queue, m2);

						break;

					case 3:

						cust.create(my_queue, m3);

						break;

					case 4:

						cust.create(my_queue, m4);

						break;

					default:

						System.out.println("\nENTER THE VALID CHOICE :");

					}

					

					System.out.println("\nPRESS 1: CONTINUE AS CUSTOMER:");

					ans=sc.nextInt();

				}

				System.out.println(cust.name());

				cust.cust_bill();

				

			}

			System.out.println("PRESS 1:CONTINUE AS SHOP:");

			ansf=sc.nextInt();

		}while(ansf==1);

	}




}//end of program
/*	********************** FASHION BOUTIQUE ***********************.

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
password
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
1
ENTER THE CATEGORY YOU WANT TO ADD 
 1->TRADITIONAL 
 2->WESTERN 
 3->INDO-WESTERN 
 4->SEASONAL
1
TRADITIONAL
SELECT THE RANGE YOU WANT TO ACCEPT:
 1->500-3000 
 2->3000-5000 
 3->5000-7000 
 4->All ranges
1
FOR RANGE 500-3000:

ENTER THE NAME OF OUTFIT:
salwar

ENTER THE PRICE:
600

ENTER AVAILABLE STOCK:
20

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
3
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
ghagra

ENTER THE PRICE:
900

ENTER AVAILABLE STOCK:
20

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
4
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
saree

ENTER THE PRICE:
2000

ENTER AVAILABLE STOCK:
30

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
4
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
patiyala

ENTER THE PRICE:
700

ENTER AVAILABLE STOCK:
40

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
3
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
kurti

ENTER THE PRICE:
600

ENTER AVAILABLE STOCK:
10

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
4
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
n
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
y
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
1
ENTER THE CATEGORY YOU WANT TO ADD 
 1->TRADITIONAL 
 2->WESTERN 
 3->INDO-WESTERN 
 4->SEASONAL
2
WESTERN
SELECT THE RANGE YOU WANT TO ACCEPT:
 1->500-3000 
 2->3000-5000 
 3->5000-7000 
 4->All ranges
2
FOR RANGE 3000-5000

ENTER THE NAME OF OUTFIT:
jeans

ENTER THE PRICE:
4000

ENTER AVAILABLE STOCK:

10

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
2
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
croptop

ENTER THE PRICE:
3500

ENTER AVAILABLE STOCK:
20

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
3
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
jumpsuite

ENTER THE PRICE:
4000

ENTER AVAILABLE STOCK:
20

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
4
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
y

ENTER THE NAME OF OUTFIT:
onepiece

ENTER THE PRICE:
4500

ENTER AVAILABLE STOCK:
10

ENTER THE SIZE OF THE OUTFIT:
1.XS	2.S	3.M	4.L	5.XL	6.XLL
1
DO YOU WANT TO ADD MORE OUTFITS :? yes(y)/no(n)
n
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
n
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
password
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
4
STOCK DETAILS
TRADITIONAL

 For range 500-3000 


OUTFIT NAME :ghagra
PRICE:900
STOCK AVAILABLE:20
SIZE:4.0

OUTFIT NAME :kurti
PRICE:600
STOCK AVAILABLE:10
SIZE:4.0

OUTFIT NAME :patiyala
PRICE:700
STOCK AVAILABLE:40
SIZE:3.0

OUTFIT NAME :salwar
PRICE:600
STOCK AVAILABLE:20
SIZE:3.0

OUTFIT NAME :saree
PRICE:2000
STOCK AVAILABLE:30
SIZE:4.0
WESTERN

 For range 3000-5000


OUTFIT NAME :croptop
PRICE:3500
STOCK AVAILABLE:20
SIZE:3.0

OUTFIT NAME :jeans
PRICE:4000
STOCK AVAILABLE:10
SIZE:2.0

OUTFIT NAME :jumpsuite
PRICE:4000
STOCK AVAILABLE:20
SIZE:4.0

OUTFIT NAME :onepiece
PRICE:4500
STOCK AVAILABLE:10
SIZE:1.0
INDO-WESTERN
SEASONAL
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
n
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
2

ENTER THE NAME OF THE NAME OF THE OUTFIT : 
salwar
1.TRADITIONAL.
2.WESTERN.
3.INDO-WESTERN
4.SEASONAL.
ENTER THE CHOICE:
1
ENTER THE OUTFIT NAME TO SEARCH :
salwar
Enter price range 
 1)500-3000 
 2)3000-5000 
 3)5000-7000
1

OUTFIT AVAILABLE!
 NAME :salwar
 PRICE: 600 Rs .
 STOCK :20
 SIZE: 3.0

DO YOU WANT TO BUY OUTFITS ?:(Yes=y / No=n) 
y
ENTER THE QUANTITY TO BE PURCHASE:
5
salwar           600.0         5        3000.0

 1->PLACE ORDER
1
YOUR ORDER FORsalwar OUTFIT FOR 5 STOCK HAS BEEN PLACED

PRESS 1: CONTINUE AS CUSTOMER:
1
1.TRADITIONAL.
2.WESTERN.
3.INDO-WESTERN
4.SEASONAL.
ENTER THE CHOICE:
0

ENTER THE VALID CHOICE :

PRESS 1: CONTINUE AS CUSTOMER:
1
1.TRADITIONAL.
2.WESTERN.
3.INDO-WESTERN
4.SEASONAL.
ENTER THE CHOICE:
1
ENTER THE OUTFIT NAME TO SEARCH :
salwar
Enter price range 
 1)500-3000 
 2)3000-5000 
 3)5000-7000
1

OUTFIT AVAILABLE!
 NAME :salwar
 PRICE: 600 Rs .
 STOCK :15
 SIZE: 3.0

DO YOU WANT TO BUY OUTFITS ?:(Yes=y / No=n) 
y
ENTER THE QUANTITY TO BE PURCHASE:
20
20 THIS MUCH STOCK NOT AVAILABLE.
ENTER THE QUANTITY TO BE PURCHASE:
2
salwar           600.0         2        1200.0

 1->PLACE ORDER
1
YOUR ORDER FORsalwar OUTFIT FOR 2 STOCK HAS BEEN PLACED

PRESS 1: CONTINUE AS CUSTOMER:
0
salwar
THANK YOU FOR VISITING!!
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
PASSWORD
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
0
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
n
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
asf
ENTER THE CORRECT PASSWORD.
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
password
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
2
ORDERS BY CUSTOMER:
 NAME :salwar
 PRICE: 600 Rs .
 STOCK :5
 SIZE: 3.0
 NAME :salwar
 PRICE: 600 Rs .
 STOCK :2
 SIZE: 3.0
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
n
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
password
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.
3
ENTER THE NEW PASSWORD:
password123

PASSWORD CHANGED SUCCESSFULLY !.
DO YOU WANT TO ADD MORE OUTFITS :(Yes:y/No:n)
n
PRESS 1:CONTINUE AS SHOP:
1

ENTER THE CHOICE 
 1.OWNER 
 2.CUSTOMER
1

ENTER THE PASSWORD (INITIAL PASSWORD : password ):
password123
1.ENTER THE QUANTITY OF OUTFITS.
2.SEE THE ORDER UPDATES.
3.CHANGE PASSWORD.
4.DISPLAY STOCK.

