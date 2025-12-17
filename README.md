Pattern Matching Algorithm Comparison:

This project explores how common “find” and “replace” functionality is implemented through classical string pattern-matching algorithms. The goal is to compare the performance and behavior of three algorithms—**Brute Force**, **Boyer–Moore**, and **Knuth–Morris–Pratt (KMP)**—when searching for substrings within large text inputs.

All algorithms were implemented from scratch, including custom data structures and performance analysis.



Overview:

The project reads a long string from an input file and stores it as a **custom linked list**, with one character per node. Given a user-provided substring, the program searches for **all occurrences** of the substring using three different pattern-matching algorithms.

Each algorithm is timed independently to evaluate performance differences across multiple scenarios.



Implemented Algorithms:

  Brute Force Search:
    A straightforward approach that compares the pattern to the text at every             possible starting position.
      - Simple to implement
      - Serves as a baseline for performance comparison
      - Least efficient for large inputs



  Boyer–Moore Search:
    An optimized algorithm that skips unnecessary comparisons using preprocessing         rules.
      - Bad Character Rule: implemented
      - Good Suffix Rule: implemented
      - Both rules work together to improve performance
      - Particularly efficient for long patterns and large alphabets


  Knuth–Morris–Pratt (KMP) Search:
    An algorithm that avoids redundant comparisons by preprocessing the pattern.
      - Failure function (prefix table) constructed
      - Failure function used during search
      - Guarantees linear-time performance



Data Structures:
  - Custom **Linked List** implementation
      - One character per node
      - No use of Java’s built-in linked list
  - Used to store the input text for all search algorithms



Input Scenarios:
  - The algorithms are tested using two input files and four search cases:
    - 1. Input 1 — search for `"Ickle"`
    - 2. Input 1 — search for `"toot"`
    - 3. Input 2 — search for `"mmmmm"`
    - 4. Input 2 — search for `"mmmom"`
  - Each scenario records execution time for all three algorithms.



Performance Analysis:

Execution time is measured for each algorithm independently
Timing is sensitive enough to detect differences between approaches
Results are compared and analyzed to determine the fastest algorithm per scenario



Conclusions:

  - The final analysis evaluates:
      - Which algorithm performs best under different conditions
      - Why certain algorithms outperform others based on pattern length and input            structure
      - When it is appropriate to use Brute Force, Boyer–Moore, or KMP in real-world          applications.



  - Technologies Used:
      - Language: Java
      - Data Structures: Custom Linked List
      - Concepts:
        - String pattern matching
        - Algorithm analysis
        - Time complexity comparison



Notes:

   This project demonstrates understanding of classical pattern-matching algorithms, custom data structure implementation, and empirical performance analysis. It highlights trade-offs between simplicity, preprocessing overhead, and runtime efficiency.

