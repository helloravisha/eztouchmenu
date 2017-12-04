# Eztouchmenu     [![Build Status](https://travis-ci.org/lil-boat/playtest-set-game.svg?branch=development)](http://54.183.149.36:8080/job/ezmenutouch/)   [![Logo](https://i.imgur.com/hgm3Beit.png)](http://ezmenutouch.com)


Eztouchmenu has been designed to address many of the difficulties encountered by both the business owners and the customers  in the dining and entertainment industry.Business owner wants to maximize the revenues by providing quality products and a good and efficient service, all in all provide an enjoyable atmosphere for the customers.  
Customers want quality and good service. They don't want to wait around to order, to receive their food, drink or the bill. They want to look through the menu at their leisure and make the best choice.Eztouchmenu is an interactive menu, visualized on a wireless or seamless LCD screen located on each table of the bar or restaurant, offering customers a full range of ordering and interactive entertainment services. This includes dynamic menu management and instant communication between the chef/kitchen and the customers. 


## Table of content

- [Details](#details)
- [Introduction](#introduction)
- [Feature List](#feature-list)
    - [Web Module](#web-module)
    - [Mobile Module](#mobile-module)
- [Demo](#demo)
    - [Order Management Process](#order-management-process)
    - [Chat Bots](#chat-bots)
    - [Menu Management](#menu-management)
    - [Quicksight](#quicksight)
- [Pre requisites Set Up](#pre-requisites-set-up)
    - [AWS Configuration](#aws-configuration)
    - [Local Configuration](#local-configuration)
- [Set up and run project](#set-up-and-run-project)
- [Links](#links)

## Details

|Name | Detail|
|---|---|
| University | [SJSU UNIVERSITY]( http://www.sjsu.edu/) |
| Course | [CLOUD TECHNOLOGIES](info.sjsu.edu/web-dbgen/catalog/courses/CMPE281.html)|
|Professor| [Mr.SANJAY GARJE](https://www.linkedin.com/in/sanjaygarje/) |
|ISA|[DIVYANKITHA Urs](https://www.linkedin.com/in/divyankithaurs/) |
|Team | [Harini Balakrishnan](https://www.linkedin.com/in/harini-balakrishnan/) 
|Team | [Ravi Katta](https://www.linkedin.com/in/ravi-shanker-katta/)  
|Team | [Rajalakshmi Babu](https://www.linkedin.com/in/rajalakshmi-babu-79a100102/) 
|Team | [Sunder Thyagarajan](https://www.linkedin.com/in/sunderthyagarajan/)

## Introduction
Ezmenutouch is a Software-as-a-service cloud born application that has been designed to address many of the difficulties encountered by both the business owners and the customers in the dining and entertainment industry. In one hand the business owner wants to maximize the revenues by providing quality products and efficient service, all in all provide an enjoyable atmosphere for the customers. They may want to track the order details, build  and modify the menu dynamically. On the other hand, the customers want quality and good service as well. They don't want to wait around to place an order, get their order status or to receive their bill. They want to look through the menu at their leisure and make the best choice. 
Ezmenutouch makes the entire procedure is automated. Ezmenutouch has three modules. The mobile application is for the customers to visualize the dynamic menu at anytime on a wireless or seamless tablet/mobile located on each table of the bar or restaurant, offering customers a full range of ordering and interactive entertainment services. The kitchen will have access to respond to the orders placed by the customers which is instantly updated and notified to the customers. The supervisor/manager will have access to the website to monitor/track the entire ordering process and manage the menu details. The manager or supervisor can present the menu with different categories and items at any time and it is immediately reflected on the mobile application. This all in one application has various advantages that has been explained in details in this document. 

### Feature List
#### Web Module  
( Further classified into two  components )       
> Supervisor            
- He can cancel request from any  device
- Map devices with tables
- Updations on menu and menu items(insert or delete) 
- Updations on available items
- To acknowledge the waiter request
- To observe order status

> Kitchen
- The kitchen module is installed in the kitchen so that the orders can be received from  the  customers directly.
- To acknowledge the customer’s order and to send the time needed for their delivery.
- To update the customer about unavailable items even after order confirmation by user.
- To observe the order status.
- To respond the request coming from the customer regarding un served items      

#### Mobile Module 
> Customer
- Can select the items in the menu.
- Place the order.
- Get status of the ordered items
- Call the waiter
- Estimate the bill before confirming the order

## Demo
### Order Management Process
**Step 1**: The customer's need to enter the authentication code or the table number in order to access the restaurant’s mobile application. 
[![Mobile app front page](https://i.imgur.com/YWfK6Ysm.png)](http://ezmenutouch.com/)

**Step 2**: Customers can visualize various categories available in the Menu and can view the items with detail description and price. They can add the item to the order by selecting the ‘Add to Order’ button. 
[![Add to Order](https://i.imgur.com/ZU0sehDm.png)](http://ezmenutouch.com/)

**Step 3**: Customers can view their final order and confirm their order. Once they place the order, the status of the order is changed to “Placed” and instantly appended to the list of orders in the web application. 
[![Make order](https://i.imgur.com/jJn9Z2mm.png)](http://ezmenutouch.com/)

**Step 4**: The kitchen can see the complete details of the customer’s order and can respond to the order with the current status like “In Progress” while the chef prepares food, “Ready” when the food is prepared and “Served” when the ordered items are served to the customers.

[![Response From the Kitchen Image](https://i.imgur.com/yl5uBwsm.png)](http://ezmenutouch.potobooth.com/)

**Step 5**: The customers can track their order status at anytime after they have placed the order. 
[![Order status](https://i.imgur.com/qDyBLYzm.png)](http://ezmenutouch.com/)

### Chat Bots
**step 1**: When the customer clicks on the waiter button, the application provides a flexibility to communicate with voice for any information.

### Menu Management
**Step 1**: The Supervisor has the authentication to modify the menu categories and items. 
[![Supervisor Menu Management Image](https://i.imgur.com/GTivxxCm.png)](http://ezmenutouch.potobooth.com/)

**Step 2**: The application is structured to perform complete CRUD (create, read, update and delete) both the categories and the items in the Menu. 
[![Create new Item in the Menu](https://i.imgur.com/fDBJuqlm.png)](http://ezmenutouch.potobooth.com/)

**Step 3**: The Supervisor can update the available items in the menu which dynamically reflects in the mobile application and in the Kitchen webapplication. 
[![Supervisor update items](https://i.imgur.com/LYCINntm.png)](http://ezmenutouch.potobooth.com/)

**Step 4**: A copy of the order details of all the tables will be provided to the Supervisor as well.
[![Supervisor Orders Lis](https://i.imgur.com/W66IIb7m.png)](http://ezmenutouch.potobooth.com/)

### Quicksight

**Step 1**: The manager can have access to the AWS quicksight service which provides a graphical analysis of the list of orders and their status. So that the manager can get notified if the ratio of orders that are not yet responded and can immediately acknowledge the kitchen or the customer about the situation. 
[![QuickSight Graphical View](https://i.imgur.com/mcq4Yhlm.png)](https://us-east-1.quicksight.aws.amazon.com)

## Pre requisites Set Up
### AWS Configuration
Need to setup various AWS services like EC2, ECS, ECR, Auto scaling, ELB, Lambda, CloudFront, S3,  S3 Static Web hosting, Route53, SNS, CloudWatch, Lex, Polly, Congnito, RDS, IAM, QuickSight, API Gateway.  

### Local Configuration
Need to setup Android Application SDK tool kit, Java, Angular4, NPM, WebStrom/Eclipse, Jenkins, Docker, Springboot.

## Set up and run project
- Need to install the android mobile application
- Supervisor must login to the [Web application](http://ezmenutouch.com/) with credentials **Username**: Supervisor and **password**: Password.
- Kitchen(chef) must login to the [Web application](http://ezmenutouch.potobooth.com/)with credentials **Username**: Kitchen and **password**: Password.
Note: All this applications are deployed in cloud. So prefere to run it in the cloud

### Locally: 
- Need to clone the web module from [Github](https://github.com/CMPE281Cloud/eztouchmenu) and run it in Webstrom with npm.
- Need to clone the mobile module from [Github](https://github.com/CMPE281Cloud/eztouchmenu) and run it with android SDK.
- Need to clone the server module from [Github](https://github.com/CMPE281Cloud/eztouchmenu) and run it in tomcat server.
- Need to  clone the test module from [Github](https://github.com/CMPE281Cloud/eztouchmenu) and run it in Eclipse with maven.

## Links

* [Kitchen Module Web site](http://ezmenutouch.potobooth.com/)
* [Supervisor Module Web site](http://ezmenutouch.com/)
* [Documentation](https://github.com/CMPE281Cloud/eztouchmenu/master/README.md)
* [Source code](https://github.com/CMPE281Cloud/eztouchmenu)
