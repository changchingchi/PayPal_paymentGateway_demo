PayPal_paymentGateway_demo
==========================

This is a Demo using PayPal MPL and Braintree V.zero SDK. 

==========================

In this Demo, you will see payment gateway using Paypal as well as Braintree. You will need to apply you own PayPal REST API credential as well as Braintree credential. 

you will need to also create you own server to run Oauth2.0 in order to submit request to PayPal server as well as braintree server. 

Delete unnecessary files. Library conflicts since Braintree SDK includes PayPal android SDK already, there is no need to "manually include PayPal android SDK", the version of PayPal SDK depends on Braintree server, as today, it is 2.7.3. ( latest is 2.8.4)
