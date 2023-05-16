DementiaScheduler
=========
**DementiaScheduler**: Use Java Design Andriod App to help paitents and caretaker to manage time and contect their family.
# About this Project

SFU-CMPT276 Introduction to Software Engineering

Project Name: Dementia Scheduler

**Group 07**

**Team Member**:

Chenzheng Li            cla429@sfu.ca

Garvit Sardana         gsardana@sfu.ca

Gursahib Singh Badwal   gsb18@sfu.ca

Hongrui Fan             hongrui_fan@sfu.ca

Ozafa Mahmood           oym@sfu.ca

# Our Server
http://williamoverflow.com:8080/

# Our Server Code
https://csil-git1.cs.surrey.sfu.ca/hongruif/dementiascheduler_server.git

# Abstract:

What we will design is a self management app that is used to remind or help dementia patients with their basic daily tasks such as reminders for hygiene, meals and medication administration. The app would consist of two interfaces one for the patient itself and the other one for the healthcare worker who would be looking upon and maintaining the schedules for the patient. The caretaker can schedule in some reminders for the patient depending upon the daily routine of the patient.
This project is written in Java


**Start by cloning the repository with the following command in the desired folder**:

**Clone with SSH**:

    $git clone git@csil-git1.cs.surrey.sfu.ca:cla429/dementiascheduler.git
**Clone with Http**:

    $git clone https://csil-git1.cs.surrey.sfu.ca/cla429/dementiascheduler.git


**You can use your Google account to access normal applications. In addition, we have built our own server to store user data. We have not completed the registration channal ar present, but we will do it larer. You can now use: User: admin, Password:123 to test our login server.**.

User: admin

password: 123

# Some important file describe:

**dementiascheduler/Sprint_1/app/src/main/java/com/example/dementiademo/**:

**MainActivity.java**:

Login interface, integrated with Google API login, user can use their own password or choose a Google account to logIn
(Can use this account to log in temporarily

Username: admin

password: 123)

**scondActivity.java**:

Used to allow users to choose different interfaces, including "careTaker" interface and dementia interface. Locker is used to prevent               patients from choosing the wrong interface

**DementiaPageActivity.java**:


Patients Page, show time, emergency button and text show box which is can get message from other care taker


**CareTakerPageActivity.java**:


Care taker page, show message box to send other patients.


**emergency.java**:

Emergency Page, include a Navigation Fragment


**dementiascheduler/Sprint_1/app/src/main/res/layout/**:


**activity_care_taker_page.xml**;

Care Taker page layout

**activity_dementia_page.xml**:

Dementia page layout

**activity_main.xml**:

Login page layout

**activity_second.xml**

Select Dementia Page of Care Taker Page

    
        
    




