# Binary Tree and it's Variants

The 4 Tree Data structures – Binary Search Tree (unoptimized), AVL Tree, Treap, Splay Tree have been implemented in the JAVA programming language.

All the Tree Data structures implement the ITree interface which includes the methods – search, insert, delete, print.

The Node class defines the structure of the object that is used for forming the trees. 
- It has an integer value, a pointer to the left and right nodes. 
- It also has a float priority used for the implementation of the Treap and an integer height used for the implementation of the AVL tree.

Build instructions:
- cd into the folder src/trees
- Compile the files using the command: `javac *.java`
- To test the tree data structures use the following commands:

`java Main dataPoints intervalSize numTrials DataStructure RandomOrSequential`

For example: 

`java Main 10 10000 10 1 R`

where,
- dataPoints – No. of different element numbers to run the tests for 
- intervalSize – The step by which the input size increases
- numTrials – All operations are averaged over a no. of trials
- RandomOrSequential - R – Random initialization of input
- DataStructure -> \
1 – BST (Binary Search Tree) \
2 – Treap \
3 – Splay Tree \
4 – AVL Tree