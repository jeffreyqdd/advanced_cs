# advanced_cs
# Welcome to this repository
I am taking this class in my 11th grade year. I am currently new to Java and will be recording my notes from the class in this README


# Java Basics
## I am new to Java. Here are some personal notes to myself.
## basic syntax
1. Java is a strictly object orientated language. It is also a compiled langauge. 
2. public static void main() serves as a starting point for the program.
3. always create an instance of the driver class to access methods. 
4. class A implements interface. class Child extends BaseClass

## generic classes
1. Makes a class that works for any data type. Class A<T>{};
2. If initiating an array of type T, use an object arr. Java will cast it. When returning
	a T value, cast the object to T. Suppress warnings bc it is guarenteed to work. Invalid
	push calls are checked during compilation.

```Java
@SuppressWarnings("unchecked")
public T peek()
{
	return (T) arr[i];
}
```

## Exceptions: Checked vs unchecked - August 28 2019
https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/

1. Checked are the exceptions checked at compile time. Method must handle the exception or 
it must specify it using *throws*. Exception is thrown to the method that called it. It must 
be prepared to handle the exception. If thrown to java virtual machine your program will crash.

2. Unchecked are not checked at compile time; therefore, you are not required to handle or 
specify it. Exceptions under *Error* and *RuntimeException* are unchecked.

# Projects
## Stacks - August 26 2019

Stacks are FIFO. Sort of like a plate dispenser. You push items to the top and you pop items from the top. 

1. push() //adds to the top of the arrayStack. Increases size by 1.
2. pop() //returns the top value and moves the top down. Size is decreased by 1.
3. peek() //gets the top value. Does not change the size/top.
4. isFull(), isEmpty(), size() //self-explanatory.

this concept is implemented in RPN calculator







