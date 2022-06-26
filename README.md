
# Hashmap implementation

By reading info from file of text, the program saves into hashmap the words and the amount, that they were used in. As a result it prints it into console.

In this project were achieved following objectives:

• Implementaion of a hash table with collisions resolution by chaining.

• Implementation of a mechanism to dynamically expand hash tables.

• Learnining how to use inner classes to improve encapsulation.

• Implementation of an array that dynamically expands.

• Reading a text file and register in which positions appears each word.


## Authors

* **Daria Melnyk** - *Initial work* - [DashulyaMelnyk](https://github.com/DashulyaMelnyk)

## Useful theory

### Algorithms of hashmap's work :confused:

HashMap uses multiple buckets and each bucket points to a Singly Linked List where the entries (nodes) are stored. Once the bucket is identified by the hash function using hashcode, then hashCode is used to check if there is already a key with the same hashCode or not in the bucket(singly linked list).

### HashMap expansion :ghost:

To decide when to increase the number of buckets you should use the concept of the
load factor α of the hash table:
α = n / m
where n is the number of elements contained in the table, and m is the number of
available buckets.
So we can calculate the maximum number of elements for the hash table to work
properly:
n = m * α

### Reading a file with extention .txt 	:speech_balloon:

For reading from file was used the object of class BufferReader. The example of usage:

*BufferedReader in = new BufferedReader(new FileReader("file.txt"));*

To get the information line by line:

*String line = in.readLine();*


## Files processed by the program

The program processes files of txt.extention. File example:

I hate (java) hate hate

it is really good

java hate me

## Result example:

me : [(3:11)]

i : [(1:1)]

good : [(2:14)]

is : [(2:4)]

it : [(2:1)]

really : [(2:7)]

java : [(1:8), (3:1)]

hate : [(1:3), (1:13), (1:18), (3:6)]
