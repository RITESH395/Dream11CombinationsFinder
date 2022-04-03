Steps to run in local machine

1. Install java-8.0 
       1. https://www.oracle.com/java/technologies/downloads/
2. Download any ide of your choice - Maybe eclipse or Intellij 
   1. https://www.jetbrains.com/idea/download/
3. Clone the project or either fork and clone
   1. <img width="473" alt="Screenshot 2022-04-03 at 10 23 37 AM" src="https://user-images.githubusercontent.com/19280946/161412129-4eee5aee-b4e9-4eac-8c93-2d8a1458abbc.png">

4. Open project as maven project and re-fresh all dependencies
5. Go to singh.ritesh.dream11combinations.Main class and run
6. You must see "[SUCCESS] Application start .." in console
7. Open browser or postman 
   1. Go to http://localhost:8080/
   2. <img width="318" alt="Screenshot 2022-04-03 at 10 25 54 AM" src="https://user-images.githubusercontent.com/19280946/161412172-ae718ec3-76dc-472e-baa9-71d2bbc1c550.png">   
   3. 3. You must see "Yes its working fine..!! Go ahead"
8. If you see above message this application is running fine on your machine
9. Now open project You will see an input.xlsx file, open and add players details sheet wise (only 2 sheet allowed)
   1. Put "No" if you don't want to include this player to dream team
   2. Put "Yes" if you want to include in dream team based on combination
   3. Put "Mandatory" if you 100% want this player in your dream team 
   4. <img width="399" alt="image" src="https://user-images.githubusercontent.com/19280946/161395655-adc0dab6-b486-4ea0-a750-eeaff738cca3.png">

10. Save the excel and Go to http://localhost:8080/download
11. If everything is fine you will see an excel with combinations sheet wise
12. <img width="612" alt="image" src="https://user-images.githubusercontent.com/19280946/161395604-c760cc1f-67ea-4130-9254-3f77c635a70e.png">
