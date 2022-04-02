Steps to run in local machine

1. Install java-8.0
2. Download any ide of your choice - Maybe Intellij or eclipse
3. Clone the project or either fork and clone
4. Open project as maven project and re-fresh all dependencies
5. Go to singh.ritesh.dream11combinations.Main class and run
6. You must see "[SUCCESS] Application start .." in console
7. Open browser or postman 
   1. Go to http://localhost:8080/
   2. You must see "Yes its working fine..!! Go ahead"
8. If you see above message this application is running fine on your machine
9. Now open project You will see an input.xlsx file, open and add players details sheet wise (only 2 sheet allowed)
   1. Put "No" if you don't want to include this player to dream team
   2. Put "Yes" if you want to include in dream team based on combination
   3. Put "Mandatory" if you 100% want this player in your dream team 
10. Save the excel and Go to http://localhost:8080/download
11. If everything is fine you will see an excel with combinations sheet wise