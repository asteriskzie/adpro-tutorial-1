# adpro-tutorial-1

## Reflection 1 

I have tried to implement every clean code principles I knew. I tried to make every variable and function names meaningful, make every function as small as possible and do only one thing, and avoid bad comments. As for secure coding principles, I didn't really consider it initially. After reviewing my code, I realize it's not a food practice to use ordered natural numbers as the id for the product. It's better to use UUID or some other random string. For that reason, I changed my implementation a bit and used UUID as the id for the product. Beside that, I also just realized that Lombok has provided a getter and setter, so I remove my setProductId method and use Lombok's @Setter instead.
