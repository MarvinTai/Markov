# Markov
## Introduction

Random Markov processes are widely used in Computer Science and in analyzing different forms of data. This project offers an occasionally amusing look at a *generative model* for creating realistic looking text in a data-driven way. To do so, you will implement two classes: First `WordGram` which represents immutable sequences of words, then `HashMarkov` which will be an efficient model for generating random text that uses `WordGram`s and `HashMap`s.

Generative models of the sort you will build are of great interest to researchers in artificial intelligence and machine learning generally, and especially those in the field of *natural language processing* (the use of algorithmic and statistical AI/ML techniques on human language). One recent and powerful example of such text-generation model via statistical machine learning program is the [OpenAI GPT project](https://openai.com/blog/chatgpt).

<details>
<summary>Historical details of this assignment (optional)</summary>

This assignment has its roots in several places: a story named _Inflexible Logic_ now found in pages 91-98 from [_Fantasia Mathematica (Google Books)_](http://books.google.com/books?id=9Xw8tMEmXncC&printsec=frontcover&pritnsec=frontcover#PPA91,M1) and reprinted from a 1940 New Yorker story called by Russell Maloney. 
The true mathematical roots are from a 1948 monolog by Claude Shannon, [A Mathematical Theory of Communication](https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxtaWNyb3JlYWRpbmcxMmZhbGx8Z3g6MThkYzkwNzcyY2U5M2U5Ng) which discusses in detail the mathematics and intuition behind this assignment. This assignment has its roots in a Nifty Assignment designed by Joe Zachary from U. Utah, assignments from Princeton designed by Kevin Wayne and others, and the work done at Duke starting with Owen Astrachan and continuing with Jeff Forbes, Salman Azhar, Branodn Fain, and the UTAs from Compsci 201.
</details>

## General Work for this project

Your goal is to create a more efficient Markov-Generating program/class than the provided `BaseMarkov` class. As you'll see below, you'll call this new class `HashMarkov` and it will generate the same random text as `BaseMarkov`, but it will generate that text much more efficiently. You will be able to run `BaseMarkov` only after you've implemented the `WordGram` class completely. So that's the first step in creating `HashMarkov`.

You're given several classes to test the `WordGram` class you'll develop. It's likely that when you've developed the `WordGram` class and it passes all tests that you'll be able to run `BaseMarkov` to see random text generated. Then you'll be able to develop the more efficient `HashMarkov` class.


## What is a `WordGram`

You will implement a class called `WordGram` that represents a sequence of words (represented as strings), just like a Java String represents a sequence of characters. Just as the Java String class is an immutable sequence of characters, the `WordGram` class you implement will be an immutable sequence of strings. Immutable means that once a WordGram object has been created, it cannot be modified. You cannot change the contents of a `WordGram` object. However, you can create a new WordGram from an existing `WordGram`.

For details about the `WordGram` class and the concepts in it, see the [details document](docs/details.md) -- the explanation below assumes you have a *very good* understanding of the `WordGram` class.

## Running Driver Code

The primary driver code for this assignment is located in `MarkovDriver.java`. You should be able to run the `public static void main` method of `MarkovDriver` immediately after cloning the starter code, and should see something like the output shown in the expandable section below (noting that your exact runtimes will likely be different / machine dependent). Note that *there is no random text generated* because you must implement `WordGram` before the code in `BaseMarkov` works.

<details><summary>Expand for example output of MarkovDriver with starter code</summary>

```

Trained on text in data/alice.txt with T=28196 words
Training time = 0.011 s
Generated N=100 random words with order 2 Markov Model
Generating time = 0.002 s
----------------------------------
 
----------------------------------
```

</details>

This initial output is blank now because the `WordGram` class is not correctly implemented; that will be your first coding task. Before starting to code however, you are encouraged to inspect `MarkovDriver` a little more closely to understand what it is doing. You will find details about the `MarkovDriver` class in the class and its comments as well 
as in the [details document](docs/details.md) that is part of this project.


## JUnit Tests

To help test your `WordGram` and `HashMarkov` implementations, you are given some *unit tests* in `WordGramTest.java` and `MarkovTest.java`, both located in the `src` folder. A unit test specifies a given input and asserts an expected outcome of running a method, then runs your code to confirm that the expected outcome occurs. You can see the exact tests inside of the two files. the JUnit library used by these testing classes is a very widely-used industry standard for unit testing.

Note that by default (to avoid compiler errors in the starter code), `MarkovTest` is testing the `BaseMarkov` implementation. When you are ready to test your `HashMarkov` implementation, you will want to change which model is created in the `getModel` method of `MarkovTest` at the position shown in the screenshow below (if the image does not render for you, you can find them in the `figures` folder).

<details>
<summary>Expand here for a screenshot of getModel in MarkovTest</summary>

<div align="center">
  <img src="figures/markovTest2.png">
</div>

</details>

In order **to run these tests** inside VS Code, click the [Test Explorer](https://code.visualstudio.com/docs/java/java-testing#_test-explorer) (beaker) icon on the left side of VS Code (it should be the lowest icon on the panel). You can expand the arrow for `p2-markov` and the default package to see two sets of tests: One for `MarkovTest` and another for `WordGramTest`. You can click the run triangle next to each test package to run the tests. See the screenshot example in the expandable section below. *Note that JUnit programs are run by the JUnit library and the beaker-icon, not be running them as Java programs.*

You can test all the tests in `WordGramTest` by hovering over that label in the _TestExplorer_ panel which is active when you click the Beaker-Icon, and is shown in the screenshot when you expand the image below. You can also run each individual unit test by hovering and clicking on each test's run triangle. The results of the tests are in the VSCode _TEST RESULTS_ panel, not in the other panels where output is shown. Deciphering error JUnit error messages is not always straightforward -- but when the tests pass? You'll get all green.

<details>
<summary>Expand here for screenshot running JUnit test in VS Code</summary>
