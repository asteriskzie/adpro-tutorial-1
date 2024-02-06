# adpro-tutorial-1

## Reflection 1 

I have tried to implement every clean code principles I knew. I tried to make every variable and function names meaningful, make every function as small as possible and do only one thing, and avoid bad comments. As for secure coding principles, I didn't really consider it initially. After reviewing my code, I realize it's not a food practice to use ordered natural numbers as the id for the product. It's better to use UUID or some other random string. For that reason, I changed my implementation a bit and used UUID as the id for the product. Beside that, I also just realized that Lombok has provided a getter and setter, so I remove my setProductId method and use Lombok's @Setter instead.


## Reflection 2

1. I think the appropriate number of unit test depends on the complexity of the class. The simpler it is, the fewer unit tests we need. As for code coverage, I think it's a good practice to test all our code. However, it is not a guarantee that our code is bug free. We might miss some edge cases or some other bugs that are not covered by our test. 

2. I think the new functional test suite might potentially reduce the code quality. It's because the new functional test suite might have a lot of duplicate code as the previous functional test suite. We can improve the code quality by making a helper class that contains several basic methods that are used by both test suites. This way, we do not repeat ourselves and make the code more maintainable. 