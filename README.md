# ms-banking-app-feign
Customers, Loans, Accounts, Cards microservices communication using feign client 

Steps to register a card:
1. Customer registers 
2. Loan is creating with field, outstanding amount calculating in the background
3. Account uses outstanding amount field of loan to
4. Card uses Account outstanding loan amount data, checks whether it is bigger than 500 or not.
   it registers only whose outstaning loan is less than 500.
#  Behind the scene, 
   when new account is registered by customer id, it automatically binds loan's field to its appropriate field (outstanding loan amount)

