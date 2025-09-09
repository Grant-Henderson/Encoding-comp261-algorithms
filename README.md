# Encoding-comp261-algorithms

# 🔎 String Search & Compression Algorithms (Java)

Implementations of fundamental **string searching** and **lossless compression algorithms** in Java — including Boyer–Moore, Knuth–Morris–Pratt (KMP), Huffman Coding, and Lempel–Ziv — with provided unit test handouts.

## 📂 Project Structure
- **Assignment4_Interface.java** → Interface/driver provided for assignment consistency  
- **BoyerMoore.java** → Boyer–Moore string search algorithm  
- **BMSearchTest_Handout.java** → Test harness for Boyer–Moore  
- **KMP.java** → Knuth–Morris–Pratt (KMP) string search algorithm  
- **KMPSearchTest_Handout.java** → Test harness for KMP  
- **HuffmanCoding.java** → Huffman encoding and decoding  
- **HuffmanTest_Handout.java** → Test harness for Huffman  
- **LempelZiv.java** → Lempel–Ziv compression (basic variant)  
- **LempelZivTest_Handout.java** → Test harness for Lempel–Ziv  

## ⚙️ Features
- **Efficient string search algorithms**  
  - **Boyer–Moore**: Uses bad character & good suffix heuristics  
  - **Knuth–Morris–Pratt (KMP)**: Uses prefix function for linear-time searching  
- **Lossless compression techniques**  
  - **Huffman Coding**: Builds an optimal prefix-free binary tree  
  - **Lempel–Ziv**: Dictionary-based text compression  
- Includes **test harnesses** for validating correctness  

## 🛠️ Build & Run
1. Compile all `.java` files:
   ```bash
   javac *.java
