# adpro-tutorial-1
Ester Gracia 
22060041991 
Adpro C 

Deployed app: https://eshop-asteriskzie.koyeb.app/

## Module 1

### Reflection 1 

I have tried to implement every clean code principles I knew. I tried to make every variable and function names meaningful, make every function as small as possible and do only one thing, and avoid bad comments. As for secure coding principles, I didn't really consider it initially. After reviewing my code, I realize it's not a food practice to use ordered natural numbers as the id for the product. It's better to use UUID or some other random string. For that reason, I changed my implementation a bit and used UUID as the id for the product. Beside that, I also just realized that Lombok has provided a getter and setter, so I remove my setProductId method and use Lombok's @Setter instead.


### Reflection 2

1. I think the appropriate number of unit test depends on the complexity of the class. The simpler it is, the fewer unit tests we need. As for code coverage, I think it's a good practice to test all our code. However, it is not a guarantee that our code is bug free. We might miss some edge cases or some other bugs that are not covered by our test. 

2. I think the new functional test suite might potentially reduce the code quality. It's because the new functional test suite might have a lot of duplicate code as the previous functional test suite. We can improve the code quality by making a helper class that contains several basic methods that are used by both test suites. This way, we do not repeat ourselves and make the code more maintainable. 

## Module 2 

### Reflection 1 

- In my Main.html, I put the title tag inside the body tag when it's supposed to be inside the head tag. I moved the title tag to the head tag. 
- In my ProductList.html, I didn't provide caption for the table element. I added the caption to the table element.
- I haven't implemented unit test for many of my classes and method. I added more unit tests so that it covers 100% of my code. 

### Reflection 2
Yes it has, the workflows have run the tests automatically on every push, which implements Continuous Integration. Since it automate the tests, developers don't have to bother with testing every changes they made. It also has deployed the application to the server automatically after the tests have passed, which implements Continuous Deployment. It makes the deployment process easier. Additionally, it has code scanning to ensure the code quality. It helps to find the bugs and security vulnerabilities in the code.


## Module 3

1) Explain what principles you apply to your project!
2) Explain the advantages of applying SOLID principles to your project with examples.
3) Explain the disadvantages of not applying SOLID principles to your project with examples.


### Reflection 1
Single Responsibility Principle (SRP): 

Open Closed Principle (OCP):

Liskov Substitution Principle (LSP):

Interface Segregation Principle (ISP):

Dependency Inversion Principle (DIP):

### Reflection 2 


### Reflection 3 


