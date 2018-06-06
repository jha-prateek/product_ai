# product.ai
    An image classifier for Android using Tensorflow Lite
    
### TEAM : MUSK MELON
    
#####    PROBLEM 
    As a customer there comes a moment when you go to a retail store 
    and find two or more products that looks similar to each other but 
    are different in terms of the ingredient used and usability. Customers 
    have a hard time finding the same type of product. P & G offers a 
    wide range of products which customers can look out for if they don't 
    have a particular product in store. 
    
#####    IDEA/SOLUTION 
    We have come up with a system that will help customer to find a way out of 
    their problem by just using his/her hand-held device. Our idea is to build an 
    Application for phone which can take a picture of the product and recognize it 
    by using a Convolutional Neural Network trained on top of Google Inception v3 
    model. Once the system identifies the product it can find the details of the 
    product and present it before the customer. Details will include a short 
    description, where it should be used and how it should be used. Apart from 
    this feature the system would also help the customer to find more products 
    similar to the one he needs and show a detailed comparison between the two. 
    By presenting this comparison table user can decide by himself, weather he 
    should go for this one or look for something else. 

    We will even use the data collected from the user's phone and produce useful 
    insights for the supply-chain. This information will include which are the 
    products which is purchased very often by customers. 
    
#####    DESCRIPTION 
        Our app aims to simplify the work of customers by just taking a picture of the given 
    product. For the prototype we have added total of 4 products. Users can compare any 
    two at given time or even get the details about only one from the given list. Customers 
    can scan the product using their smartphone and get the required details regarding the 
    same, then they can infer from the given information and decide whether to go for one 
    or the other. The comparison between the two are shown in a way that will provide a gist 
    and differences. 

    We achieved this goal by using a technique of deep learning known as Transfer Learning. 
    In Transfer learning we use a already trained neural network to retrain for new classes, in 
    this way the network does not have to re-learn the primitive features of an image which 
    is common for every image whether it is a dog or car. This primitive features include 
    edges, color tone etc. The neural network now only needs to learn the high level features 
    of an image such as shapes and color channels. 

    This is a highly accurate training method for any deep learning classifier. In our case we 
    used Google’s Inception-v3 to train its final layers which are known as bottlenecks for 
    P&G products.  
    
#####    FEATURES 
    1. Scan product and find out the details 
    2. Look for similar products 
    3. Comparison between different products 
    4. Collect information form user's phone and use it further analysis. 
    
#####    CLASSES 
    1. TIDE PLUS ORIGINAL 
    2. TIDE PLUS JASMINE AND ROSE 
    3. TIDE NATURALS 
    4. ARIEL COMPLETE 
    
#####    TOOLS & FRAMEWORKS USED 
    1. Tensor-flow Lite 
    2. Google Inceptionv3 (Transfer Learning) 
    3. Python (Numpy, Pandas) 
    4. Android Studio 
    5. Google Compute Engine 
    6. GIT VCS 
    7. Bazel 