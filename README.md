cookDish
========
TDD sample for an app. Write a class that takes available ingredients and returns left over ingredients after
making dish I want. See src/test/resources/TruthTableCookIt.xlsx

POJOs
	class Ingredients{
		int carrotQty;
		int iceBergLettuceQty;
		int chickenQty;
		int beansQty;
	}

	class Status{
 		Ingredients leftOverIngrediants;//Ingredients passed in minus what was used. Or same what was passed in case too few ingredients
 	 	boolean success;//false if could not make else true
	}


Class can make Ceasars salad or Russian salad.

Ingredients:
------------
Cesars:
2 carrot
4 ice burg
1 chicken
1 beans

Russian:
2 carrots
2 beans
2 chicken

If carrot is not there - 1 bean or 2 iceberg can replace
if beans is not there - 1 ice berg can replace
if chicken is not there 2 carrots or 2 beans can replace.


	package moh.test2;
	Class Cook{
		Status cookIt(Ingredients in, Dish what)...
	}

	public enum Dish {
		CEASERS,RUSSIAN;
	}



