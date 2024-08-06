# Final Practicum CSCI 3155 Summer 2024 (language testing)
Welcome to the CSCI 3155 Summer 2024 final practicum. In this exercise you will work on teams to design, develop and test a small domain specific language (DSL) to the specification provided. Throughout this practicum, if you have questions, you are actively encouraged to ask the course staff. The document below lists "**`minimum`**" features for your language. You are of course welcome to create a more complex language than what is described below.

## Language Design
Your language design team has defined an operational semantic to the following specification. Your task is to review the specification and author extensive test for the intended semantic.

A language with:
* at least 5 emoticons
    * At least one emoticon must be happy
* Either 
    * A collection of expressions (e.g. list) 
    * The ability to define and use variables 
    * AND/OR
    * The ability to define and call functions
* 3 operations

### Concrete Syntax

$$\begin{array}{rrll}
e & \Rightarrow & v \\
& | & (e) \\
& | & [\; e\color{red}{*}\; ] & \texttt{a list of 0-or-many expressions}\\
& | & e_1 + e_2 \\
& | & ! e_1 \\
& | & e_1\; count\; e_2 \\
\\
v & \Rightarrow & T.T \\
& | & :) \\
& | & UwU \\
& | & -.-\; zzZ \\
& | & \circ\; . \circ \\
& | & [\; v\color{red}{*}\; ] & \texttt{a list of 0-or-many values}\\
\end{array}$$

### Abstract Syntax

$$\begin{array}{rrll}
Expr & \Rightarrow & Value \\
& | & ManyExprs(Expr\color{red}{*}) & \;[\; e\color{red}{*}\; ] \\
& | & Plus(Expr_1,\; Expr_2) & e_1 + e_2 \\
& | & Not(Expr_1) & ! e_1 \\
& | & Count(Expr_1,\; Expr_2) & e_1\; count\; e_2 \\
\\
Value & \Rightarrow & Cry & T.T \\
& | & Happy & :) \\
& | & VeryHappy & UwU \\
& | & Sleepy & -.-\; zzZ \\
& | & Stun & \circ\; . \circ \\
& | & ManyVals(Value\color{red}{*}) & \;[\; v\color{red}{*}\; ] \\
\end{array}$$

### Judgment Form(s)
* eval(e) = v

### Inference Rules

#### Values
$$\begin{array}{c}
\\ \hline
eval(v) = v
\end{array}\texttt{( eval-value )}$$
<br /><br /><br />

#### ManyExprs
$$\begin{array}{c}
\forall\; i \in [1, n]
,\;\;\;
eval(e_i) = v_i
,\;\;\;
v_i \neq ERROR
\\ \hline
eval(ManyExprs(e_1,\; \dots,\; e_n)) = MayVals(v_1,\; \dots,\; v_n)
\end{array}\texttt{( eval-many-exprs )}$$
<br /><br /><br />

$$\begin{array}{c}
\exists \; i \in [1, n]
,\;\;\;
eval(e_i) = ERROR
\\ \hline
eval(ManyExprs(e_1,\; \dots,\; e_n)) = ERROR
\end{array}\texttt{( eval-many-exprs-error )}$$
<br /><br /><br />

#### Plus
$$\begin{array}{c}
eval(e_{left}) = MayVals(v_1,\; \dots,\; v_n)
,\;\;\;
eval(e_{right}) = v_{right}
\\
\forall\; i \in [1, n]
,\;\;\;
eval(Plus(v_i, v_{right})) = v_i^{new}
,\;\;\;
v_i^{new} \neq ERROR
\\ \hline
eval(Plus(e_{left},\; e_{right})) = MayVals(v_1^{new},\; \dots,\; v_n^{new})
\end{array}\texttt{( eval-plus-many-values )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_{left}) = MayVals(v_1,\; \dots,\; v_n)
,\;\;\;
eval(e_{right}) = v_{right}
\\
\exists\; i \in [1, n]
,\;\;\;
eval(Plus(v_i, v_{right})) = ERROR
\\ \hline
eval(Plus(e_{left},\; e_{right})) = ERROR
\end{array}\texttt{( eval-plus-many-values-error )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = VeryHappy
\\ \hline
eval(Plus(e_1,\; e_2)) = VeryHappy
\end{array}\texttt{( eval-stay-uwu )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_2) = VeryHappy
\\ \hline
eval(Plus(e_1,\; e_2)) = VeryHappy
\end{array}\texttt{( eval-become-uwu )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = Cry
,\;\;\;
eval(e_2) = v_2
\\ \hline
eval(Plus(e_1,\; e_2)) = v_2
\end{array}\texttt{( eval-move-on )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = v_1
,\;\;\;
v_1 \in \{\; Happy,\; Stun\; \}
,\;\;\;
eval(e_2) = Cry
\\ \hline
eval(Plus(e_1,\; e_2)) = Cry
\end{array}\texttt{( eval-hard-day )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = v_1
,\;\;\;
v_1 \notin ERROR
,\;\;\;
eval(e_2) = v_2
,\;\;\;
v_2 \notin \{\; VeryHappy,\; Cry\; \}
\\ \hline
eval(Plus(e_1,\; e_2)) = v_1
\end{array}\texttt{( eval-meh )}$$
<br /><br /><br />

#### Not
$$\begin{array}{c}
eval(e_1) = Stun
\\ \hline
eval(Not(e_1)) = Sleepy
\end{array}\texttt{( eval-not-stun )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = Sleepy
\\ \hline
eval(Not(e_1)) = Stun
\end{array}\texttt{( eval-not-sleepy )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) \in \{\; Happy,\; VeryHappy\; \}
\\ \hline
eval(Not(e_1)) = Cry
\end{array}\texttt{( eval-not-(very)-happy )}$$
<br /><br /><br />

$$\begin{array}{c}
eval(e_1) = Cry
\\ \hline
eval(Not(e_1)) = VeryHappy
\end{array}\texttt{( eval-not-cry )}$$
<br /><br /><br />

NOTE: The inference rule below states that `![v*]` will actually reduce the list of values by applying the plus operation from left to right. e.g. 
* `![a, b, c, d]`
* will evaluate to the value of `((a + b) + c) + d`

$$\begin{array}{c}
eval(e_1) = ManyVals(v_1,\; \dots,\; v_n)
,\;\;\;
\\
n \geq 2
,\;\;\;
\forall\; i \in [2, n]
,\;\;\;
v_{acc}^1 = v_1
,\;\;\;
\\
eval(Plus(v_{acc}^{i-1}, v_i)) = v_{acc}^{i}
\\ \hline
eval(Not(e_1)) =  v_{acc}^{i}
\end{array}\texttt{( eval-not-many-values )}$$
<br /><br /><br />

### E.g.
* `[ T.T, UwU, `$\circ$.$\circ$ `, -.- zzZ, :) ] + ![ T.T, `$\circ$.$\circ$`, -.- zzZ, :) ]`

    * left is valued, look at the right, reduce the array by the `!` logic
        * `![ T.T, `$\circ$.$\circ$`, -.- zzZ, :) ]`
            * T.T + $\circ$.$\circ$ =  $\circ$.$\circ$
            * $\circ$.$\circ$ +  -.- zzZ = $\circ$.$\circ$ 
            * $\circ$.$\circ$  + :) = $\circ$.$\circ$ 
    
    * intermediate `[ T.T, UwU, `$\circ$.$\circ$ `, -.- zzZ, :) ] + `$\circ$.$\circ$

        * left is manyVals, right is something I can add validly
        * T.T + $\circ$.$\circ$ = **$\circ$.$\circ$**
        * UwU + $\circ$.$\circ$ = **`UwU`**
        * $\circ$.$\circ$ + $\circ$.$\circ$ = **$\circ$.$\circ$**
        * -.- zzZ + $\circ$.$\circ$ = **`-.- zzZ`**
        * :) + $\circ$.$\circ$ = **`:)`**

    * final value `[ `$\circ$.$\circ$ `, UwU, `$\circ$.$\circ$ `, -.- zzZ, :) ]`
        
# Language Testing

Your task is as follows:

1. You must translate the abstract syntax provided above to Scala code. 
2. Then you must implement at least 2 unit tests for each of the operational semantics described (1 if only 1 is possible). Label each test with a comment or a description string as seen in class.
3. Then you must implement at least 5 integration tests on complex sentences. Label each test with a comment or a description string as seen in class.
4. You must identify any potential cases of evaluation that the language designers failed to specify (recommend doing this while working on the above tasks)

### The following were not identified by the language designers
* **`_YOUR_SOLUTION_HERE`**

## STRETCH: Language Implementation
Only if you have successfully completed the above task (with course staff sign off), should you move on to the following task

1. Please SAVE off the original test(s) and interpreter(s) in some way so we can see the evolution of your work.
2. You must translate the abstract syntax provided above to Scala code. 
3. Run this result against your created tests.
4. Identify any potential cases of evaluation that the language designers failed to specify (recommend doing this while working on the above tasks). **highlight whatever you find this time around that you did not find the first time through**

## STRETCH: Language Extension
Only if you have successfully completed the above task (with course staff sign off), should you move on to the following task.

* Extend the language with any of the following and provide a complete operational semantic
    * additional values
    * additional expressions
    * additional collections
    * features such as variables, functions, mutabiles and loops
    
* Once you have completed the operational semantics for your change to the language, you may extend the interpreter. Be sure to test this new work. Please SAVE off the original interpreter(s) in some way so we can see the evolution of the interpreter.