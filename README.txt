Title: GC Scheduler (Global Consulting Scheduler)

Purpose:
Provide a User Interface for Global Consulting Users to
create, read, update, and delete Appointments and Customers.

Author: Christopher Smith
Email: csm2180@wgu.edu or smithc238@gmail.com
Version: 1.0.0
AS OF 2021-10-01

Intellij Community 2021.1.3
Oracle Java SE 11.0.12
OpenJFX-SDK-11.0.2
mySQL-connector-java 8.0.25

Directions:
1. Launch Application.
2. Login, credentials are Username: test , Password: test
3. Select Tab you wish to view: Appointments, Customer, or Reports.

A. Appointments Tab - Default shows all appointments.
A.1 Filter view using All, Month, and Week Radio buttons.
While in Month and Week view you can change Month and Week
with < and > Buttons on the Upper left hand side.
A.2 Obtain a Contact Schedule by selecting a Contact in the
Combo on the upper right. You can further filter with
Month and Week as stated in A.1. To clear the contact filter,
select the contact combo box as before and press clear.
A.3 To add an appointment press the add button and complete the form.
A.4 To update an appointment select the row/record you wish to update
and press the update button and complete the form.
A.5 To delete an appointment select the row/record you wish to delete
and press delete. When prompted to confirm press ok to delete record.
You will receive a notification when it is deleted.

B. Customers Tab - Shows all customers.
B.1 To add a customer press the add button and complete the form.
B.2 To update a customer select the row/record you wish to update
and press the update button and complete the form.
B.3 To delete a customer select the row/record you wish to delete
and press delete. When prompted to confirm press ok to delete record.
You will receive a notification when it is deleted.

C. Reports Tab - Displays two charts or reports.
C.1. The left bar chart shows number of customer appointments by
type and month.
C.2. The right pie chart shows number of customers by country.
(Custom report for A3f)


---Additional Notes---

-Javadocs are located under the doc directory
-LAMBDA expressions are used in AppointmentsController initialize and deleteButtonListener methods.