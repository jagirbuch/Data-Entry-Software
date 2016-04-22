# Data-Entry-Software

This is small desktop application for Data Entry , built using Java & MySQL.

To run this application in your system you need to ensure that JRE 1.8 & MYSQL is properly installed.

This Desktop application is useful for the users who have some small business & have many regular customers who uses services on regular basis. 
This Application will store your customer records into database , Notify you about the birth date or anniversary of the customer , You can send greetings to the customer through this application via mail.

To run this application you need to make some changes in source code given below

Database
--------

Apply your database credential in following file :
\src\com\des\dbo\DbManager.java 
private final String	USER_NAME	= "<Your user name>";
private final String	PASSWORD	= "<Your password>";

You can directly execute the \src\script\des.sql to configure your database.

Default Username & password is admin/admin.

Email
------

We are providing a feature through which you can send email to the customer on their special day.

For this you need to add your working current credential in the following file :
src\com\des\util\MailUtil.java
private static final String USERNAME = "";
private static final String PASSWORD = "";

In email content is added from \src\specialday.vm file.
You can modify this file. You can put any html content in this file & this content will send to customer.

Make sure when you are using this feature specialday.vm file is present at \src\specialday.vm path.

If this file is missing then this feature will not work