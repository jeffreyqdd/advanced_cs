# advanced_cs
# Welcome to this repository
I am taking this class in my 11th grade year. I am currently new to Java and will be recording my notes from the class in this README.


# Java Basics
I am new to Java. Here are some personal notes to myself.
## Basic Syntax
1. Java is a strictly object orientated language. It is also a compiled language. 
2. public static void main() serves as a starting point for the program.
3. always create an instance of the driver class to access methods. 
4. class A implements interface. class Child extends BaseClass

## Generic Classes
1. Makes a class that works for any data type. 
```Java
public Class A<T>{};
```

2. Only time it gets confusing: If initiating an array of type T, use an object arr. Java will cast it. When returning a T value, cast the object to T. Suppress warnings bc it is guaranteed to work. Invalid push calls are checked during compilation.
3. "new" is run time.
4. Using the angle bracket yields a advantage. Makes casting a compiler error rather than a runtime error.
5. Can cast primitive to object.
```Java
@SuppressWarnings("unchecked")
public T peek()
{
	return (T) arr[i];
}
```

## Exceptions: Checked vs unchecked - August 28 2019
https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/

1. Checked are the exceptions checked at compile time. Method must handle the exception or it must specify it using *throws*. Exception is thrown to the method that called it. It must be prepared to handle the exception. If thrown to java virtual machine, your program will crash.

2. Unchecked are not checked at compile time; therefore, you are not required to handle or specify it. Exceptions under *Error* and *RuntimeException* are unchecked.


## Interclasses: October 18 2019
1. Essentially a class within a class
2. Denoted by a $ sign in the .class files

```Java
public class LinkedList<T>
{
	//linked List code
	private class Node<U>
	{
		//node code
	}
}
```

# Projects
## Stacks - August 26 2019

Stacks are FILO, sort of like a plate dispenser. You push items to the top and you pop items from the top. 

1. push() //adds to the top of the arrayStack. Increases size by 1.
2. pop() //returns the top value and moves the top down. Size is decreased by 1.
3. peek() //gets the top value. Does not change the size/top.
4. isFull(), isEmpty(), size() //self-explanatory.

this concept is implemented in RPN calculator

## Queues - September 18 2019

Queues are FIFO, sort of like a lunch line. You push the items to the back and pop from the from. The wrap around is done by % ArrayQueue size

1. enqueue() //adds to the queue
2. dequeue() //subtracts from the queue
3. peek() //gets from. Does not pop
4. isFull(), isEmpty(), size(), length() //size is current array, length is total length of array


## Linked Lists - September 24 2019

1. Have a node that contains data and a reference to the next node
2. To traverse a single linked list, start at head and set current pointer to current pointer.next();
3. Many different types of constructors
4. addFront();
5. peekFront();
6. popFront();
7. addBack();
8. peekBack();
9. popBack();
10. get(int index); //O(n)
11. add(int index); //O(n)
12. Anything to do with the back as a time complexity of O(n) while everything to do with the front has a time complexity of O(1);

## HashTables - November 1 2019

Imagine this. You are in charge of designing a data center to store all the student records by ID number. You have a brilliant idea...use an array, with each index corresponding to each ID number. However, the student IDs are 8 digits long meaing that you will have an array size of 1 MILLION! Unfortunately, only .06% of this space will be used as there are 60,000 students in the district...what a waste of RAM.

*problem* Sparse arrays, or low memory usage

*solution* Cut out the ID numbers that are not used via a Hash Method H(key ) -> k, where k is the hash index, or index in the hash table.
* Should maintain O(1) time for insertion and lookup.
* We should also use a high percentage of the array.





