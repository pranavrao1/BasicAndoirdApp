# **Design Manual -- assignment5 "TC Cart System" by prao43**
### 1. Purpose
This is the design manual for the UML class diagram for the *TC Cart Payment and Rewards Management System.*

### 2. Design Break Down
There are two main classes in the whole UML. The first is the "Customer" class that is initiated when the user is created.

The second is the "Event" class which registers all transactional events on the system. This is an interface class and requires any implementations to state the customer ID (hexadecimal id) that generated that particular event and the date it occured.

There two implementations of the "Event" class and "Credit" and "Transaction". The "Credit" class creates an object of a certain credit value. This stores, in addition to the inherited values, the amount and the expiration date of that credit.

The "Transaction" class stores a transaction request by a customer along with the transaction's amount, which is the cost of the products prior to discounts and credit.

The "Purchase" class inheritances many properties form the "Transaction" class.
When this class is initiated  calculations are made to determine the purchase amount ( final charge). It determines the amount of generated credit, based on logic derived from the requirements and whether the customer can become a VIP member for next year.

There are two association classes. The first is called "Request" and occurs when the customer requests a transaction. This class is able to provide the transaction with information on the amount of VIP discounts, additional discounts and credit the customer has.

The second association class is the "Initiate" and occurs when the customer initiated a purchase following a transaction. This class is able to provide the credit card information entered by the customer and the annual purchases to date. This class is also dependent on the "Credit Card" class, which is an object created when the user scans their credit card. This systems is designed as per the requirements to not accept cash payment.

There are three utility libraries.
1. Electronic System
2. Hardware
3. Date

### 3. Flow of Activity
Once a customer is created the card printer library is used to print the card.
The customer can be accessed now using a card reader.

Once a customer decides to buy an item they 'request' their first event, a transaction. This will collect the necessary information about the customer as well the amount of goods they are trying to buy.

Once satisfied with the transaction they 'initiate' a purchase of it. The polymorphic properties allow much of the customers purchase and transaction information to be used to determine how much to charge their credit card and whether they should be promoted to VIP membership. Once the purchase is complete the necessary information is emailed out to the customer.

### 4. Conclusion

This meets all the requirements the customer has asked. Taking advantages of Java's polymorphic capabilities enabled me to create a elegant UML diagram.