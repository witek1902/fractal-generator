# Fractal generation

## About
This project was created in 2013. In January 2018 I decided to little refactoring and publishing it for open source. \
If you want to trace the refactoring process - look at commit history. \
In folder **old-src** you can see the old code written in **student times** with comment/code in polish language :)

## Requirements
 - Java 8
 - Maven 3
## How to use
In root folder with project:

    mvn package

After, in the *target* folder will be executable jar **FractalGenerator.jar**. <br/>
That's all!

## TODO
1. Write tests.
2. Split Generator, because has too much responsibilities (FractalMouseListener, FractalMouseMotionListener, FractalKeyListener).

## References
 - https://en.wikipedia.org/wiki/Fractal
