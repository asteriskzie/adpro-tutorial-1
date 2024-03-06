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
Single Responsibility Principle (SRP): I moved the UUID generation in CarRepository to CarServiceImpl. The responsibility of repository class is to store and retrieve data. The UUID generation is not part of it, but part of the business logic (how we want to identify the car), thus it should be in the service class.

Open Closed Principle (OCP): To handle the id generation, I created the CarIdentifier interface. It's open for extension since we can add more id generation method in the future (other than UUID), but closed for modification since we don't need to modify the existing code to add more id generation method.

Liskov Substitution Principle (LSP): CarController was initially a subclass of ProductController. I changed it to be a separate class since the two classes are not related. The CarController also could not substitute ProductController, so it shouldn't be a subclass of ProductController.

Interface Segregation Principle (ISP): - 

Dependency Inversion Principle (DIP): I used the CarService interface, instead of it's implementation, in the CarController class.

### Reflection 2 
The SOLID principles make the code more maintainable and scalable. For example, the Dependency Inversion Principle is applied in the CarController class where it used CarService (interface) instead of CarServiceImpl (implementation). If we want to change the implementation of service layer, we don't need to modify the CarController class. We just need to change the implementation of the service class. This means that if we want to change the business logic of the application, we don't need to modify the other classes that depend on it. 

### Reflection 3 
If we don't apply the SOLID principles, the code will be hard to maintain and scale. For example, suppose the Single Responsibility Principle is not applied in CarRepository so that it still handles the UUID generation. When there's something wrong with ID generation, it will be hard to find the bug since it's placed in a class that's not responsible for it. 

## Module 4 

### Reflection 

1. I do think this TDD workflow is useful enough for me personally, as I get to try the TDD workflow for the first time. However, based on the objectives by Percival, there're rooms for improvement. For correctness, I believe the unit tests have covered enough reasonable cases, but if we want to add more edge cases we can try some malicious user's input such as a very big number that causes overflow. Besides, we haven't implemented functional tests yet, so we can't be sure that the application works as expected from the point of view of the user. For maintainability and productive workflow, I think the tests are all good. They're clean and fast enough. UPD: when I was doing exercise, I realized we can improve the OrderRepositoryTest, precisely in testSaveCreate method where we don't need to find the result by id because we already got the result object.  
    
   
2. The tests have successfully followed F.I.R.S.T principle. They are fast, took only several seconds so I don't get bored waiting them running :D. They are isolated, everything is re-initialized in the setUp method so they don't depend on each other. They are repeatable, since they produce the same result every time. They are self-validating, able to auto-detect if it passed or not. They are timely, since they are written before the implementation (thus using TDD).